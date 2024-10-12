package github.nikandpro.coffeesupplyservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "brigade")
public class Grain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private String origin;

    @Column(name = "robusta_percentage", nullable = false)
    private Double robustaPercentage;

    @Column(name = "arabica_percentage", nullable = false)
    private Double arabicaPercentage;

    @Column(name = "grain_type", nullable = false)
    private String grainType; // VARCHAR

}
