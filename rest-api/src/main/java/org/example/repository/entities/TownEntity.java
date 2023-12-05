package org.example.repository.entities;

import lombok.*;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "public.towns")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TownEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "town_id", columnDefinition = "uuid", updatable = false)
    private UUID townId;

    @Column(name = "town_name")
    private String townName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "county_id")
    private CountyEntity county;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    public UUID getTownId() {
        return townId;
    }

    public void setTownId(UUID townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public CountyEntity getCounty() {
        return county;
    }

    public void setCounty(CountyEntity county) {
        this.county = county;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }
}

