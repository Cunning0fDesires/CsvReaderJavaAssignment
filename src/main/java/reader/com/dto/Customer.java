package reader.com.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;


import java.util.UUID;

@Data
@Entity
@Builder
public class Customer {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    @NonNull
    @NotBlank
    private String customerRef;
    @NonNull
    @NotBlank
    private String customerName;
    @NonNull
    @NotBlank
    private String addressLine1;
    private String addressLine2;
    @NonNull
    @NotBlank
    private String town;
    @NonNull
    @NotBlank
    private String county;
    @NonNull
    @NotBlank
    private String country;
    @NonNull
    @NotBlank
    private String postcode;
}