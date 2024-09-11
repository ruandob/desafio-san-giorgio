package com.ruandob.challenge.infrastructure.dataproviders.payment.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruandob.challenge.domain.exceptions.SqsServiceException;
import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SqsSendPaymentServiceTest {

    @Mock
    private AmazonSQS amazonSQSClient;

    @Spy
    private ObjectMapper objectMapper;

    @InjectMocks
    private SqsSendPaymentService service;

    @Test
    @DisplayName("Deve lançar a exception quando ocorrer falha no serviço")
    void shouldThrowExceptionFailService() {
        when(amazonSQSClient.sendMessage(any(), any())).thenThrow(new RuntimeException("Erro service"));
        var exception = assertThrows(SqsServiceException.class, () -> service.sendMessage("", Mockito.mock(PaymentItemDomain.class)));
        assertThat(exception.getMessage()).isEqualTo("Ocorreu erro ao realizar o envio da mensagem: Erro service");
    }

    @Test
    @DisplayName("Deve enviar a mensagem para o serviço")
    void shouldSendService() {
        when(amazonSQSClient.sendMessage(any(), any())).thenReturn(mock(SendMessageResult.class));
        service.sendMessage("", mock(PaymentItemDomain.class));

        verify(amazonSQSClient, times(1)).sendMessage(any(), any());
    }
}
