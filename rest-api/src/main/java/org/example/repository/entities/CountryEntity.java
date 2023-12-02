package org.example.repository.entities;

import jakarta.persistence.*;
import lombok.*;

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

    @NonNull
    @Column(name = "country_name", nullable = false)
    private String countryName;
}
