package com.ruandob.challenge.infrastructure.dataproviders.payment.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import org.junit.jupiter.api.BeforeEach;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SqsSendPaymentServiceIntegrationTest {

    @Container
    public static LocalStackContainer localStack = new LocalStackContainer(DockerImageName.parse("localstack/localstack:3.0"))
            .withServices(LocalStackContainer.Service.SQS)
            .withExposedPorts(4566)
            .waitingFor(Wait.forListeningPort())
            .withStartupTimeout(java.time.Duration.ofMinutes(2));

    @Autowired
    private AmazonSQS amazonSQSClient;

    @Autowired
    private SqsSendPaymentService sqsSendPaymentService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String queueName = "payment-queue";

    @DynamicPropertySource
    static void registerLocalStackProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.cloud.aws.endpoint", () -> localStack.getEndpointOverride(LocalStackContainer.Service.SQS).toString());
        registry.add("spring.cloud.aws.credentials.access-key", () -> localStack.getAccessKey());
        registry.add("spring.cloud.aws.credentials.secret-key", () -> localStack.getSecretKey());
    }

    @BeforeEach
    public void setUp() {
        amazonSQSClient.createQueue(queueName);
    }

    @Test
    void testSendMessageToSqs() throws Exception {
        PaymentItemDomain paymentItem = getPaymentDomain();
        sqsSendPaymentService.sendMessage(queueName, paymentItem);
        var messages = amazonSQSClient.receiveMessage(queueName).getMessages();

        assertNotNull(messages);
        assertEquals(1, messages.size());

        PaymentItemDomain receivedPaymentItem = objectMapper.readValue(messages.get(0).getBody(), PaymentItemDomain.class);
        assertEquals(paymentItem.id(), receivedPaymentItem.id());
        assertEquals(paymentItem.value(), receivedPaymentItem.value());
    }

    private PaymentItemDomain getPaymentDomain() {
        return new PaymentItemDomain(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), BigDecimal.valueOf(100), null);
    }
}
