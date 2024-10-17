package github.nikandpro.coffeesupplyservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roasting")
public class Roast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grain_id", nullable = false)
    private Grain grain;

    @ManyToOne
    @JoinColumn(name = "brigade_id", nullable = false)
    private Brigade brigade;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private CountryStats country;

    @Column(name = "quantity_taken", nullable = false)
    private Integer quantityTaken;

    @Column(name = "weight_out", nullable = false)
    private Integer weightOut;
}
