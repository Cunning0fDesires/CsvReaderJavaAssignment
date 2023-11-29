package reader.com.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import reader.com.dto.Customer;
import reader.com.service.CsvReaderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;


public class CsvReaderServiceTest {

    private CsvReaderService csvReaderService;

    @BeforeEach
    void instantiate() {
        csvReaderService = new CsvReaderService();
    }

    //good data
    @Test
    public void readFile_SuccessfullyReadsFile_ReturnsCustomerList()  {
        List<Customer> customers = csvReaderService.readFile("src/test/resources/correct.csv");

        assertThat(customers).hasSize(1);
        assertThat(customers.get(0).getCustomerRef()).isEqualTo("123");
        assertThat(customers.get(0).getCustomerName()).isEqualTo("Yulia");
        assertThat(customers.get(0).getAddressLine1()).isEqualTo("Putney 43");
        assertThat(customers.get(0).getTown()).isEqualTo("Putney");
        assertThat(customers.get(0).getCounty()).isEqualTo("London");
        assertThat(customers.get(0).getCountry()).isEqualTo("UK");
        assertThat(customers.get(0).getPostcode()).isEqualTo("SW15 6QP");
    }

    @Test
    //empty file, throw exception
    public void readFile_EmptyFile_ReturnsEmptyCustomerList() throws IOException {
        // Assert
        assertThatThrownBy(() -> csvReaderService.readFile("src/test/resources/empty.csv"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("There is no data in input CSV file");    }

    @Test
    //no data but with headers, throw exception
    public void readFile_ErrorReadingFile_ThrowsRuntimeException() {
        assertThatThrownBy(() -> csvReaderService.readFile("src/test/resources/nodata.csv"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("There is no data in input CSV file");
    }

    @Test
    //non-existing file, throw exception
    public void readFile_ExceptionDuringDataProcessing_ReturnsPartialCustomerList() {
        assertThatThrownBy(() -> csvReaderService.readFile("non-existing-file.csv"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Error reading CSV file");
    }
}