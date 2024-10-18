package github.nikandpro.coffeesupplyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BrigadeDto {
    private UUID id;
    private Double lossesCoffee;
}
