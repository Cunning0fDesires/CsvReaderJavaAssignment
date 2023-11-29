package reader.com.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reader.com.dto.Customer;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureWebTestClient
public class CsvCustomerClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void uploadCsvFile() {
        List<Customer> customers = Arrays.asList(
                Customer.builder()
                        .customerRef("ref1")
                        .customerName("John X")
                        .build(),
                Customer.builder()
                        .customerRef("ref2")
                        .customerName("Jane Y")
                        .build()
        );

        webTestClient.post()
                .uri("/api/customers/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(customers)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }

    @Test
    public void getCustomerByReference() {
        String customerRef = "ref1";

        webTestClient.get()
                .uri("/api/customers/{customerRef}", customerRef)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Customer.class)
                .value(customer -> {
                    assertThat(customer.getCustomerName()).isEqualTo("John X");
                });
    }
}
