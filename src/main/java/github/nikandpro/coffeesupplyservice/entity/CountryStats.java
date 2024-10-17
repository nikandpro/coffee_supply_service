package github.nikandpro.coffeesupplyservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "country_stats")
public class CountryStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String country;
}
