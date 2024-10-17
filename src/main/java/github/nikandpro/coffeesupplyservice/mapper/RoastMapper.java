package github.nikandpro.coffeesupplyservice.mapper;

import com.github.grpccommon.RoastRequest;
import github.nikandpro.coffeesupplyservice.dto.RoastDto;
import github.nikandpro.coffeesupplyservice.entity.Roast;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface RoastMapper {

    RoastDto toDto(RoastRequest roastRequest);

    Roast toEntity(RoastDto roastDto);
}
