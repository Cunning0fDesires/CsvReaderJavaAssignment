package org.example.repository.entities;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "public.customers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_ref")
    private UUID customerRef;

    @Column(name = "customer_name")
    private String customerName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_line_1_id")
    private AddressLineEntity addressLine1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_line_2_id")
    private AddressLineEntity addressLine2;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id")
    private TownEntity town;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "county_id")
    private CountyEntity county;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postcode_id")
    private PostcodeEntity postcode;

    public UUID getCustomerRef() {
        return customerRef;
    }

    public void setCustomerRef(UUID customerRef) {
        this.customerRef = customerRef;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public AddressLineEntity getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(AddressLineEntity addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public AddressLineEntity getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(AddressLineEntity addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public TownEntity getTown() {
        return town;
    }

    public void setTown(TownEntity town) {
        this.town = town;
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

    public PostcodeEntity getPostcode() {
        return postcode;
    }

    public void setPostcode(PostcodeEntity postcode) {
        this.postcode = postcode;
    }
}
