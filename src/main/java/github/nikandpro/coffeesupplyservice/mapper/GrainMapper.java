package github.nikandpro.coffeesupplyservice.mapper;

import github.nikandpro.coffeesupplyservice.dto.GrainDto;
import github.nikandpro.coffeesupplyservice.entity.Grain;
import github.nikandpro.coffeesupplyservice.kafka.model.GrainEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface GrainMapper {

    Grain toEntity(GrainDto grainDto);

    GrainDto toDto(GrainEvent grainEvent);
}
