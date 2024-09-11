package com.ruandob.challenge.infrastructure.dataproviders.payment.listeners;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import com.ruandob.challenge.infrastructure.dataproviders.payment.providers.CreatePaymentProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SqsPaymentListener {

    @Value("${queueName.partial}")
    private String partialQueue;

    @Value("${queueName.full}")
    private String fullQueue;

    @Value("${queueName.over}")
    private String overQueue;

    private final ObjectMapper objectMapper;
    private final AmazonSQS amazonSQSClient;

    private final CreatePaymentProvider createPaymentProvider;

    @Scheduled(fixedDelay = 5000)
    public void queuePartialPaymentListener() {
        log.info("Inicio de verificação das listas");
        queueListener(partialQueue, "parcial");
        queueListener(fullQueue, "total");
        queueListener(overQueue, "excedente");
        log.info("Fim da verificação das listas");
    }

    private void queueListener(String queueName, String status) {
        try {
            log.info("Verificando lista {}", queueName);
            String queueUrl = amazonSQSClient.getQueueUrl(queueName).getQueueUrl();
            ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueName);
            if (!receiveMessageResult.getMessages().isEmpty()) {
                var message = receiveMessageResult.getMessages().get(0);
                var domain = objectMapper.readValue(message.getBody(), PaymentItemDomain.class);
                domain = domain.addStatus(status);
                log.info("Processando {}, payment={}", queueName, domain);
                createPaymentProvider.create(domain);
                amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
            }
        } catch (Exception ex) {
            log.error("[ErrorListener] Cause={}, message={}", ex.getCause(), ex.getMessage());
        }
    }

}
