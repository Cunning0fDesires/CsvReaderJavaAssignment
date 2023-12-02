package org.example.mappers;

import org.example.dto.CustomerDto;
import org.example.repository.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "customerRef", target = "customerRef")
    CustomerEntity toEntity(CustomerDto dto);

    List<CustomerEntity> toEntityList(List<CustomerDto> dtos);

    @Mapping(source = "customerRef", target = "customerRef")
    CustomerDto toDTO(CustomerEntity entity);

    List<CustomerDto> toDTOList(List<CustomerEntity> entities);
}
