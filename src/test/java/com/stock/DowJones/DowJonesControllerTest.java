package com.stock.DowJones;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.stock.DowJones.controller.DowJonesController;
import com.stock.DowJones.entity.DowJonesRecord;
import com.stock.DowJones.service.DowJonesService;

@WebMvcTest(DowJonesController.class)
public class DowJonesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DowJonesService service;

    @Test
    void testQueryByStock() throws Exception {
        String stock = "AA";
        List<DowJonesRecord> mockRecords = List.of(new DowJonesRecord(1, "AA", null, 100.00, 120.00, 90.00, 110.00, 1000L, 5.00, 5.00, 5000L, 120.00, 125.00, 5.00, 10, 5.00));
        Mockito.when(service.getByStock(stock)).thenReturn(mockRecords);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/dow-jones/query/AA"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
    }

    @Test
    void testAddRecord() throws Exception {
        DowJonesRecord record = new DowJonesRecord(1, "AA", null, 100.00, 120.00, 90.00, 110.00, 1000L, 5.00, 5.00, 5000L, 120.00, 125.00, 5.0, 10, 5.0);
        Mockito.when(service.addRecord(Mockito.any())).thenReturn(record);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/dow-jones/add")
                        .contentType("application/json")
                        .content("{\"stock\": \"AA\", \"open\": 100, \"high\": 120, \"low\": 90, \"close\": 110, \"volume\": 1000}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
