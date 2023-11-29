package reader.com.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reader.com.dto.Customer;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CsvReaderServiceTest {
    private CsvReaderService csvReaderService;

    @BeforeEach
    void instantiate() {
        csvReaderService = new CsvReaderService();
    }

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
    public void readFile_EmptyFile_ThrowException() throws IOException {
        assertThatThrownBy(() -> csvReaderService.readFile("src/test/resources/empty.csv"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("There is no data in input CSV file");
    }

    @Test
    public void readFile_NoDataToRead_ThrowException() {
        assertThatThrownBy(() -> csvReaderService.readFile("src/test/resources/nodata.csv"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("There is no data in input CSV file");
    }

    @Test
    public void readFile_FileDoesNotExist_ThrowException() {
        assertThatThrownBy(() -> csvReaderService.readFile("non-existing-file.csv"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Error reading CSV file");
    }
}