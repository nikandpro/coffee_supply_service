package github.nikandpro.coffeesupplyservice.dto;

public record GrainDto(
        Long id,
        Integer weight,
        Long countryStats,
        Double robustaPercentage,
        Double arabicaPercentage,
        String grainType
) { }
