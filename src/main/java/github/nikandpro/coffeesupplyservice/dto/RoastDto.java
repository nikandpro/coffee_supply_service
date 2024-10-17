package github.nikandpro.coffeesupplyservice.dto;

import java.util.UUID;

public record RoastDto(
        Long id,
        Long grain,
        UUID brigade,
        Long country,
        Integer quantityTaken,
        Integer weightOut
) {}
