package reader.com.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static reader.com.client.CustomerMocks.setupMockBooksResponse;


@SpringBootTest
@ActiveProfiles("test")
@EnableFeignClients
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
public class CustomerClientIntegrationTest {
    @Autowired
    @Qualifier("mockCustomersService")
    private WireMockServer mockCustomerService;

    @Qualifier("mockCustomersService2")
    @Autowired
    private WireMockServer mockBooksService2;

    @Autowired
    private CsvCustomerClient customerClient;

    @BeforeEach
    void setUp() throws IOException {
        setupMockBooksResponse(mockCustomerService);
        setupMockBooksResponse(mockBooksService2);
    }

    @Test
    public void whenGetBooks_thenBooksShouldBeReturned() {
        assertTrue(customerClient.getCustomer("a18acba4-8fb6-11ee-b9d1-0242ac120002").hasBody());
    }
}
