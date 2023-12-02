package org.example.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
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