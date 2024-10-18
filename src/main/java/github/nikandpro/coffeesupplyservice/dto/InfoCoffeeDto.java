package github.nikandpro.coffeesupplyservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class InfoCoffeeDto {
    private String country;
    private List<String> typeGrain;
    private List<Long> weight_out;

    public InfoCoffeeDto(String country) {
        this.country = country;
    }
}
