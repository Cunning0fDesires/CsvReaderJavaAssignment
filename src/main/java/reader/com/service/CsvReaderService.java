package reader.com.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import reader.com.dto.Customer;
import reader.com.exceptioins.MissingInputDataException;
import reader.com.util.CsvHeaders;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.util.UUID;

@Service
public class CsvReaderService implements FileReaderService<Customer> {
    @Override
    public List<Customer> readFile(String filePath) {
        try (Reader in = new FileReader(filePath)) {
            List<Customer> customers = new ArrayList<>();
            CSVFormat csvFormat = createFormatWithHeaders();
            Iterable<CSVRecord> records = csvFormat.parse(in);
            checkIfInputFileIsEmpty(records);
            for (CSVRecord record : records) {
                Customer customer = buildCustomer(record);
                customers.add(customer);
            }
            return customers;
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

    public static Customer buildCustomer(CSVRecord record) {
        return Customer.builder()
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
