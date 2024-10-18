package github.nikandpro.coffeesupplyservice.mapper;

import com.github.grpccommon.RoastRequest;
import github.nikandpro.coffeesupplyservice.dto.RoastDto;
import github.nikandpro.coffeesupplyservice.entity.Brigade;
import github.nikandpro.coffeesupplyservice.entity.CountryStats;
import github.nikandpro.coffeesupplyservice.entity.Grain;
import github.nikandpro.coffeesupplyservice.entity.Roast;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface RoastMapper {

    RoastDto toDto(RoastRequest roastRequest);

    @Mapping(source = "grain", target = "grain", qualifiedByName = "idToGrain")
    @Mapping(source = "brigade", target = "brigade", qualifiedByName = "idToBrigade")
    @Mapping(source = "country", target = "country", qualifiedByName = "idToCountry")
    Roast toEntity(RoastDto roastDto);

    @Named("idToGrain")
    default Grain toGrain(Long id) {
        Grain grain = new Grain();
        grain.setId(id);
        return grain;
    }

    @Named("idToBrigade")
    default Brigade toBrigade(UUID id) {
        Brigade brigade = new Brigade();
        brigade.setId(id);
        return brigade;
    }

    @Named("idToCountry")
    default CountryStats toCountry(Long id) {
        CountryStats countryStats = new CountryStats();
        countryStats.setId(id);
        return countryStats;
    }

}
