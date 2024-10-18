package github.nikandpro.coffeesupplyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryDto {
    private Long id;
    private String name;
    private Double lossesCoffee;
}
