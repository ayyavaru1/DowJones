package com.stock.DowJones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.stock.DowJones.entity.DowJonesRecord;
import com.stock.DowJones.repository.DowJonesRepository;
import com.stock.DowJones.service.DowJonesService;

@SpringBootTest
public class DowJonesServiceTest {

    @Mock
    private DowJonesRepository repository;

    @InjectMocks
    private DowJonesService service;

    @Test
    void testGetByStock() {
        String stock = "AA";
       
        
        List<DowJonesRecord> mockRecords = List.of(new DowJonesRecord(1, "AA", null, 100.00, 120.00, 90.00, 110.00, 1000L, 5.00, 5.00, 5000L, 120.00, 125.00, 5.00, 10, 5.00));
        
        Mockito.when(repository.findByStock(stock)).thenReturn(mockRecords);

        List<DowJonesRecord> records = service.getByStock(stock);

        assertEquals(1, records.size());
        assertEquals("AA", records.get(0).getStock());
    }

    @Test
    void testAddRecord() {
        DowJonesRecord record = new DowJonesRecord(1, "AA", null, 100.00, 120.00, 90.00, 110.00, 1000L, 5.00, 5.00, 5000L, 120.00, 125.00, 5.0, 10, 5.0);
        Mockito.when(repository.save(record)).thenReturn(record);

        DowJonesRecord savedRecord = service.addRecord(record);

        assertEquals("AA", savedRecord.getStock());
    }
}
