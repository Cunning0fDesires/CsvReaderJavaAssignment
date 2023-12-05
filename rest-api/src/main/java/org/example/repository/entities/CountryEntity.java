package org.example.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "public.countries")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID countryId;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    public UUID getCountryId() {
        return countryId;
    }

    public void setCountryId(UUID countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
