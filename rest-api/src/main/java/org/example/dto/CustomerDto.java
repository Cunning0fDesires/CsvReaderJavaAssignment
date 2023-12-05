package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class CustomerDto {
    @NonNull
    @NotBlank
    @JsonProperty("customerRef")
    private String customerRef;

    @NonNull
    @NotBlank
    @JsonProperty("customerName")
    private String customerName;

    @NonNull
    @NotBlank
    @JsonProperty("addressLine1")
    private String addressLine1;

    @JsonProperty("addressLine2")
    private String addressLine2;

    @NonNull
    @NotBlank
    @JsonProperty("town")
    private String town;

    @NonNull
    @NotBlank
    @JsonProperty("county")
    private String county;

    @NonNull
    @NotBlank
    @JsonProperty("country")
    private String country;

    @NonNull
    @NotBlank
    @JsonProperty("postcode")
    private String postcode;

}
