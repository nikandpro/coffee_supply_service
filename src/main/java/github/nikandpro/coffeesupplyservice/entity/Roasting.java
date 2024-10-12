package github.nikandpro.coffeesupplyservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "brigade")
public class Roasting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "grain_id", nullable = false)
    private Grain grain;

    @ManyToOne
    @JoinColumn(name = "brigade_id", nullable = false)
    private Brigade brigade;

    @Column(nullable = false)
    private Integer quantityTaken;

    @Column(nullable = false)
    private Integer weightOut;
}
