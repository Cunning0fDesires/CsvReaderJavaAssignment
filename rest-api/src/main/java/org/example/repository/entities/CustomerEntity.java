package org.example.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "public.customers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_ref")
    private UUID customerRef;

    @NonNull
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "address_line_1_id")
    private AddressLineEntity addressLine1;

    @ManyToOne
    @JoinColumn(name = "address_line_2_id")
    private AddressLineEntity addressLine2;

    @ManyToOne
    @JoinColumn(name = "town_id", nullable = false)
    private TownEntity town;

    @ManyToOne
    @JoinColumn(name = "county_id", nullable = false)
    private CountyEntity county;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "postcode_id", nullable = false)
    private PostcodeEntity postcode;
}