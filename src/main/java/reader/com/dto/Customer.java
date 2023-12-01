package reader.com.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.lang.Nullable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
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