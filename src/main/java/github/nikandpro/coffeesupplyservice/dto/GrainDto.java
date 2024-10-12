package github.nikandpro.coffeesupplyservice.dto;

public record GrainDto(
        Long id,
        Integer weight,
        String origin,
        Double robustaPercentage,
        Double arabicaPercentage,
        String grainType
) { }
