package github.nikandpro.coffeesupplyservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "brigade")
public class CountryStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;

    @Column(name = "loss_percentage", nullable = false)
    private Double lossPercentage;
}
