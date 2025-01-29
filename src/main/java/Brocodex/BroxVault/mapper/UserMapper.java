package Brocodex.BroxVault.mapper;

import Brocodex.BroxVault.dto.mq.MessageDTO;
import Brocodex.BroxVault.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public abstract class UserMapper {
    public abstract MessageDTO map(User user);
    public abstract User map(MessageDTO dto);
}
