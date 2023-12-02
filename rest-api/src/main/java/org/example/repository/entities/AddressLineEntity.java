package org.example.repository.entities;

import jakarta.persistence.*;
import lombok.*;

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

    @NonNull
    @Column(name = "address_line")
    private String addressLine;
}
