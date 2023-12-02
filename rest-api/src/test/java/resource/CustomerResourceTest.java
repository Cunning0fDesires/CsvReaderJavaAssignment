package resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.repository.CustomerRepository;
import org.example.repository.entities.*;
import org.example.resource.CustomerResource;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerResourceTest {

    @Mock
    private CustomerRepository customerRepository;
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
        CustomerEntity customer = buildMockCustomer();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers/upload")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Collections.singletonList(customer))))
                .andExpect(status().isOk());
        
        verify(customerRepository).saveAll(Collections.singletonList(customer));
    }

    @Test
    public void getCustomerByReference() throws Exception {
            CustomerEntity mockCustomer = buildMockCustomer();
            UUID customerRef = mockCustomer.getCustomerRef();
        
          when(customerRepository.findById(eq(customerRef))).thenReturn(Optional.of(mockCustomer));
         
                 mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{customerRef}", customerRef)
                         .contentType(MediaType.APPLICATION_JSON))
                         .andExpect(MockMvcResultMatchers.status().isOk())
                         .andExpect(MockMvcResultMatchers.jsonPath("$.customerRef").value(customerRef.toString()))
                         .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value("Mock Customer"));
    }

    @Test
    public void getNonExistingCustomer() throws Exception {
        UUID customerRef = UUID.randomUUID();

        when(customerRepository.findById(eq(customerRef))).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{customerRef}", customerRef)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private CustomerEntity buildMockCustomer() {
        return CustomerEntity.builder()
                .customerRef(UUID.randomUUID())
                .customerName("Mock Customer")
                .addressLine1(AddressLineEntity.builder().addressLine("Address 11").build())
                .town(TownEntity.builder().townName("Putney").build())
                .country(CountryEntity.builder().countryName("UK").build())
                .county(CountyEntity.builder().countyName("London").build())
                .postcode(PostcodeEntity.builder().postcode("183389389hfj").build())
                .build();
    }
}
