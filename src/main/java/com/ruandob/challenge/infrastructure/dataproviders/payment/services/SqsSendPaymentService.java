package com.ruandob.challenge.infrastructure.dataproviders.payment.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruandob.challenge.domain.exceptions.SqsServiceException;
import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SqsSendPaymentService {

    private final AmazonSQS amazonSQSClient;
    private final ObjectMapper objectMapper;

    public void sendMessage(String queueName, PaymentItemDomain domain) {
        try {
            var message = objectMapper.writeValueAsString(domain);
            amazonSQSClient.sendMessage(queueName, message);
            log.info("[SqsSendPaymentService] Mensagem enviada para a fila {} com a mensagem {}", queueName, message);
        } catch (Exception ex) {
            log.error("Ocorreu um erro ao processar envio para SQS: cause={}, message={}", ex.getCause(), ex.getMessage());
            throw new SqsServiceException(ex.getMessage());
        }
    }
}
