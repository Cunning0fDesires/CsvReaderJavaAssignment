package org.example.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "public.postcodes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostcodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "postcode_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID postcodeId;

    @NonNull
    @Column(name = "postcode", nullable = false)
    private String postcode;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "town_id", nullable = false)
    private TownEntity town;
}