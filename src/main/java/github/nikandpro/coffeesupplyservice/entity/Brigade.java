package github.nikandpro.coffeesupplyservice.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "brigade")
public class Brigade {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
}
