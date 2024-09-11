package com.ruandob.challenge.infrastructure.dataproviders.payment.listeners;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Testcontainers
@SpringBootTest
@ExtendWith(SpringExtension.class)
class SqsPaymentListenerIntegrationTest {

    private static final String PARTIAL = "partial";
    private static final String FULL = "full";
    private static final String OVER = "over";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AmazonSQS amazonSQSClient;

    @Autowired
    SqsPaymentListener sqsPaymentListener;

    @Container
    public static LocalStackContainer localStack = new LocalStackContainer(DockerImageName.parse("localstack/localstack:3.0"))
            .withServices(LocalStackContainer.Service.SQS)
            .withExposedPorts(4566)
            .waitingFor(Wait.forListeningPort())
            .withStartupTimeout(java.time.Duration.ofMinutes(2));

    @DynamicPropertySource
    static void registerLocalStackProperties(DynamicPropertyRegistry registry) {
        registry.add("queueName.partial", () -> PARTIAL);
        registry.add("queueName.full", () -> FULL);
        registry.add("queueName.over", () -> OVER);
        registry.add("spring.cloud.aws.endpoint", () -> localStack.getEndpointOverride(LocalStackContainer.Service.SQS).toString());
        registry.add("spring.cloud.aws.credentials.access-key", () -> localStack.getAccessKey());
        registry.add("spring.cloud.aws.credentials.secret-key", () -> localStack.getSecretKey());
    }

    @BeforeEach
    public void setUp() {
        amazonSQSClient.createQueue(PARTIAL);
        amazonSQSClient.createQueue(FULL);
        amazonSQSClient.createQueue(OVER);
    }

    @Test
    @DisplayName("Deve processar mensagens das filas SQS")
    void shouldProcessMessagesFromQueues() throws JsonProcessingException {
        var payment = objectMapper.writeValueAsString(new PaymentItemDomain(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), BigDecimal.TEN, null));

        amazonSQSClient.sendMessage(PARTIAL, payment);
        amazonSQSClient.sendMessage(FULL, payment);
        amazonSQSClient.sendMessage(OVER, payment);

        sqsPaymentListener.queuePartialPaymentListener();
        assertAll(() -> {
            assertQueueIsEmpty(PARTIAL);
            assertQueueIsEmpty(FULL);
            assertQueueIsEmpty(OVER);
        });
    }

    private void assertQueueIsEmpty(String queueName) {
        ReceiveMessageResult result = amazonSQSClient.receiveMessage(queueName);
        assertThat(result.getMessages()).isEmpty();
    }
}