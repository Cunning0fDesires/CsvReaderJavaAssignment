package reader.com.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reader.com.dto.Customer;

import java.util.List;

@Component
public class CsvCustomerClient implements CustomerClient {

    private final WebClient webClient;

    public CsvCustomerClient(@Value("${base.url.property}")String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }
    @Override
    public void uploadFile(List<Customer> customers) {
        webClient.post()
                .uri("/api/customers/upload")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(customers))
                .retrieve()
                .toBodilessEntity()
                .block(); // Blocking for simplicity, use subscribe() in a real application
    }

    @Override
    public ResponseEntity<Customer> getCustomer(String customerRef) {
        return webClient.get()
                .uri("/api/customers/{customerRef}", customerRef)
                .retrieve()
                .toEntity(Customer.class)
                .block(); // Blocking for simplicity, use subscribe() in a real application
    }
}
