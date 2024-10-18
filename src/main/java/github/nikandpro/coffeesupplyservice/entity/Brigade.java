package github.nikandpro.coffeesupplyservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "brigade")
public class Brigade {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
}
