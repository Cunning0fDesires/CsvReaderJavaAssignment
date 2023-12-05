package org.example.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "public.counties")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "county_id", columnDefinition = "uuid", updatable = false)
    private UUID countyId;

    @Column(name = "county_name")
    private String countyName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    public UUID getCountyId() {
        return countyId;
    }

    public void setCountyId(UUID countyId) {
        this.countyId = countyId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }
}
