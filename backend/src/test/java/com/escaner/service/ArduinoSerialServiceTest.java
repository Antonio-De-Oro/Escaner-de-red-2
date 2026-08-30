package com.escaner.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ArduinoSerialServiceTest {

    @Test
    void sendScanCommand_shouldWriteScanCommandToSerialOutput() throws Exception {
        SimpMessagingTemplate messagingTemplate = mock(SimpMessagingTemplate.class);
        ArduinoSerialService service = new ArduinoSerialService(messagingTemplate, new ObjectMapper());

        StringWriter stringWriter = new StringWriter();
        PrintWriter output = new PrintWriter(stringWriter);

        Field outputField = ArduinoSerialService.class.getDeclaredField("output");
        outputField.setAccessible(true);
        outputField.set(service, output);

        service.sendScanCommand();

        assertTrue(stringWriter.toString().contains("SCAN"), "Se esperaba que el comando SCAN se escribiera en el puerto serial");
    }
}
