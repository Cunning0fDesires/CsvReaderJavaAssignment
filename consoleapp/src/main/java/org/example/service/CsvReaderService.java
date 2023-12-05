package org.example.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.dto.CustomerDto;
import org.example.exceptioins.MissingInputDataException;
import org.example.util.CsvHeaders;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderService implements FileReaderService<CustomerDto> {
    @Override
    public List<CustomerDto> readFile(String filePath) {
        try (Reader in = new FileReader(filePath)) {
            List<CustomerDto> customerDtos = new ArrayList<>();
            CSVFormat csvFormat = createFormatWithHeaders();
            Iterable<CSVRecord> records = csvFormat.parse(in);
            checkIfInputFileIsEmpty(records);
            for (CSVRecord record : records) {
                CustomerDto customerDto = buildCustomer(record);
                customerDtos.add(customerDto);
            }
            return customerDtos;
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file", e);
        }
    }

    private static void checkIfInputFileIsEmpty(Iterable<CSVRecord> records) {
        if (!records.iterator().hasNext()) {
            throw new MissingInputDataException("There is no data in input CSV file");
        }
    }

    private static CSVFormat createFormatWithHeaders() {
        return CSVFormat.DEFAULT.builder()
                .setHeader(CsvHeaders.getAllHeaders())
                .setSkipHeaderRecord(true)
                .build();
    }

    public static CustomerDto buildCustomer(CSVRecord record) {
        return CustomerDto.builder()
                .customerRef(record.get(CsvHeaders.CUSTOMERREF.getHeaderName()))
                .customerName(record.get(CsvHeaders.CUSTOMERNAME.getHeaderName()))
                .addressLine1(record.get(CsvHeaders.ADDRESSLINE1.getHeaderName()))
                .addressLine2(record.get(CsvHeaders.ADDRESSLINE2.getHeaderName()))
                .town(record.get(CsvHeaders.TOWN.getHeaderName()))
                .county(record.get(CsvHeaders.COUNTY.getHeaderName()))
                .country(record.get(CsvHeaders.COUNTRY.getHeaderName()))
                .postcode(record.get(CsvHeaders.POSTCODE.getHeaderName()))
                .build();
    }
}
