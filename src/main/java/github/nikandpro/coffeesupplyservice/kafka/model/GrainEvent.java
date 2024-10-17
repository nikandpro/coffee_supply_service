package github.nikandpro.coffeesupplyservice.kafka.model;

import lombok.Data;

@Data
public class GrainEvent {
    private Long id;
    private Integer weight;
    private String origin;
    private Double robustaPercentage;
    private Double arabicaPercentage;
    private String grainType;
}
