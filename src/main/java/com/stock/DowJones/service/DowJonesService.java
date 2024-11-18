package com.stock.DowJones.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.stock.DowJones.entity.DowJonesRecord;
import com.stock.DowJones.repository.DowJonesRepository;

@Service
public class DowJonesService {

    @Autowired
    private DowJonesRepository repository;

    public void uploadDataset(MultipartFile file) throws IOException {
        List<DowJonesRecord> records = parseCSV(file);
        repository.saveAll(records);
    }

    public List<DowJonesRecord> getByStock(String stock) {
        return repository.findByStock(stock);
    }
    public List<DowJonesRecord> findAllStocks() {
        return repository.findAll();
    }
    
    public DowJonesRecord addRecord(DowJonesRecord record) {
        return repository.save(record);
    }

    private List<DowJonesRecord> parseCSV(MultipartFile file) throws IOException {
    	try (Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
            // Read the entire content of the file
            String content = IOUtils.toString(reader);

            // Remove dollar signs and commas
            content = content.replaceAll("[$]", "");

            // Now, parse the cleaned content back into a CSV reader
            Reader cleanedReader = new java.io.StringReader(content);
            CsvToBean<DowJonesRecord> csvToBean = new CsvToBeanBuilder<DowJonesRecord>(cleanedReader)
                    .withType(DowJonesRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            
            List<DowJonesRecord> list = csvToBean.parse();
            return list;
        }
    }
}
