package org.example.repository.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "county_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID countyId;

    @NonNull
    @Column(name = "county_name", nullable = false)
    private String countyName;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;
}
