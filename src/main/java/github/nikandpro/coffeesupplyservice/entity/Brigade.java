package github.nikandpro.coffeesupplyservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "brigade")
@AllArgsConstructor
@NoArgsConstructor
public class Brigade {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
}
