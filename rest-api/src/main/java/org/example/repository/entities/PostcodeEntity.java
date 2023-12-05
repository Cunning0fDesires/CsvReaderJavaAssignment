package org.example.repository.entities;

import lombok.*;

import javax.persistence.*;
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
    @Column(name = "postcode_id", columnDefinition = "uuid", updatable = false)
    private UUID postcodeId;


    @Column(name = "postcode")
    private String postcode;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id")
    private TownEntity town;

    public UUID getPostcodeId() {
        return postcodeId;
    }

    public void setPostcodeId(UUID postcodeId) {
        this.postcodeId = postcodeId;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public TownEntity getTown() {
        return town;
    }

    public void setTown(TownEntity town) {
        this.town = town;
    }
}