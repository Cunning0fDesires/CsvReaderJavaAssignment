package resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CustomerDto;
import org.example.resource.CustomerResource;
import org.example.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class CustomerResourceTest {

    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerResource customerResource;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerResource).build();
    }

    @Test
    public void uploadCsvFile() throws Exception {
        CustomerDto customerDto = buildMockCustomerDto();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Collections.singletonList(customerDto))))
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomerByReference() throws Exception {
        CustomerDto mockCustomer = buildMockCustomerDto();
        UUID customerRef = UUID.fromString(mockCustomer.getCustomerRef());

        lenient().when(customerService.findByCustomerReference(eq(customerRef))).thenReturn(mockCustomer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{customerRef}", customerRef)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("customerRef").value(customerRef.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("customerName").value("Mock Customer"));
    }

    @Test
    public void getNonExistingCustomer() throws Exception {
        UUID customerRef = UUID.randomUUID();

        lenient().when(customerService.findByCustomerReference(eq(customerRef))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{customerRef}", customerRef)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));

    }

    private CustomerDto buildMockCustomerDto() {
        return CustomerDto.builder()
                .customerRef(UUID.randomUUID().toString())
                .customerName("Mock Customer")
                .county("London")
                .country("UK")
                .addressLine1("Address 1")
                .addressLine2("Address 2")
                .postcode("147bvc")
                .town("Putney")
                .build();
    }
}
