package com.escaner.controller;

import com.escaner.service.ArduinoSerialService;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ScanControllerTest {

    @Test
    void startScan_shouldCallArduinoServiceAndReturnOkResponse() throws Exception {
        ArduinoSerialService arduinoService = mock(ArduinoSerialService.class);
        ScanController controller = new ScanController(arduinoService);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(post("/api/scan"))
                .andExpect(status().isOk())
                // .andExpect(jsonPath("$.status").value("Scan command sent to Arduino"));
                .andExpect(jsonPath("$.status").value("STATUS INCORRECTO"));

        verify(arduinoService).sendScanCommand();
    }
}
