package com.stock.DowJones.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stock.DowJones.entity.DowJonesRecord;
import com.stock.DowJones.exception.DowJonesException;
import com.stock.DowJones.service.DowJonesService;

@RestController
@RequestMapping("/api/dow-jones")
public class DowJonesController {

    @Autowired
    private DowJonesService service;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadDataset(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new DowJonesException("Uploaded file is empty");
            }
            service.uploadDataset(file);
            return ResponseEntity.ok("Dataset uploaded successfully");
        } catch (IOException e) {
            throw new DowJonesException("Error processing file: " + e.getMessage());
        }
    }

    @GetMapping("/query/{stock}")
    public ResponseEntity<List<DowJonesRecord>> getByStock(@PathVariable String stock) {
        List<DowJonesRecord> records = service.getByStock(stock);
        if (records.isEmpty()) {
            throw new DowJonesException("No records found for stock: " + stock);
        }
        return ResponseEntity.ok(records);
    }
      

    @GetMapping("/query")
    public ResponseEntity<List<DowJonesRecord>> findAllStocks() {
        List<DowJonesRecord> records = service.findAllStocks();
        if (records.isEmpty()) {
            throw new DowJonesException("No stocks available " );
        }
        return ResponseEntity.ok(records);
    }

    @PostMapping("/add")
    public ResponseEntity<DowJonesRecord> addRecord(@RequestBody DowJonesRecord record) {
        DowJonesRecord savedRecord = service.addRecord(record);
        return ResponseEntity.ok(savedRecord);
    }
}
