package github.nikandpro.coffeesupplyservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grain")
public class Grain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer weight;

    @JoinColumn(name = "origin_country", nullable = false)
    private Long countryStats;

    @Column(name = "robusta_percentage", nullable = false)
    private Double robustaPercentage;

    @Column(name = "arabica_percentage", nullable = false)
    private Double arabicaPercentage;

    @Column(name = "grain_type", nullable = false)
    private String grainType;

}
