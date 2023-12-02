package org.example.repository.entities;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "town_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID townId;

    @NonNull
    @Column(name = "town_name", nullable = false)
    private String townName;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "county_id", nullable = false)
    private CountyEntity county;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;
}

