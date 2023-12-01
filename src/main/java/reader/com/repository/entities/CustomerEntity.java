package reader.com.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "customers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID customerRef;

    @NonNull
    @Column(nullable = false)
    private String customerName;

    @NonNull
    @Column(nullable = false)
    private String addressLine1;

    @Column
    private String addressLine2;

    @NonNull
    @Column(nullable = false)
    private String town;

    @NonNull
    @Column(nullable = false)
    private String county;

    @NonNull
    @Column(nullable = false)
    private String country;

    @NonNull
    @Column(nullable = false)
    private String postcode;
}

