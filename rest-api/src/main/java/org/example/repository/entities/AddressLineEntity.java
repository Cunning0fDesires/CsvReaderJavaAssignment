package org.example.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "public.address_lines")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_line_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID addressLineId;

    @Column(name = "address_line")
    private String addressLine;

    public UUID getAddressLineId() {
        return addressLineId;
    }

    public void setAddressLineId(UUID addressLineId) {
        this.addressLineId = addressLineId;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }
}
