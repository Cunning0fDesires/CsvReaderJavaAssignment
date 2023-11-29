package reader.com.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import reader.com.dto.Customer;
import reader.com.service.CustomerService;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void uploadCsvFile() throws Exception {
        Customer customer1 = buildMockCustomer();
        Customer customer2 = buildMockCustomer();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers/upload")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Arrays.asList(customer1, customer2))))
                .andExpect(status().isOk());

        verify(customerService).saveCustomers(anyList());
    }

    @Test
    public void getCustomerByReference() throws Exception {
        Customer mockCustomer = buildMockCustomer();
        String customerRef = mockCustomer.getCustomerRef();

        when(customerService.getCustomerByRef(eq(customerRef))).thenReturn(mockCustomer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{customerRef}", customerRef))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerRef").value(mockCustomer.getCustomerRef()));

        verify(customerService).getCustomerByRef(eq(customerRef));
    }

    private Customer buildMockCustomer() {
        return Customer.builder()
                .id(UUID.randomUUID())
                .customerRef("mockCustomerRef")
                .customerName("Mock Customer")
                .build();
    }
}
