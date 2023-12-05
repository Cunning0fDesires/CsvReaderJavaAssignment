package org.example.mappers;

import org.example.dto.CustomerDto;
import org.example.repository.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    Map<String, CountryEntity> countryEntityCache = new HashMap<>();
    Map<String, CountyEntity> countyEntityCache = new HashMap<>();
    Map<String, AddressLineEntity> addressLineEntityCache = new HashMap<>();
    Map<String, PostcodeEntity> postcodeEntityCache = new HashMap<>();
    Map<String, TownEntity> townEntityCache = new HashMap<>();

    default CustomerEntity toEntity(CustomerDto dto) {
        if (dto == null) return null;
        return mapToCustomerEntity(dto);
    }

    ;

    List<CustomerEntity> toEntityList(List<CustomerDto> dtos);

    @Mapping(source = "customerRef", target = "customerRef", qualifiedByName = "UUIDToString")
    @Mapping(source = "addressLine1.addressLine", target = "addressLine1")
    @Mapping(source = "addressLine2.addressLine", target = "addressLine2")
    @Mapping(source = "town.townName", target = "town")
    @Mapping(source = "county.countyName", target = "county")
    @Mapping(source = "country.countryName", target = "country")
    @Mapping(source = "postcode.postcode", target = "postcode")
    CustomerDto toDTO(CustomerEntity entity);

    List<CustomerDto> toDTOList(List<CustomerEntity> entities);

    @Named("UUIDToString")
    default String uuidToString(UUID value) {
        return value != null ? value.toString() : null;
    }

    @Named("mapToCustomerEntity")
    default CustomerEntity mapToCustomerEntity(CustomerDto dto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerRef(UUID.fromString(dto.getCustomerRef()));
        customerEntity.setCustomerName(dto.getCustomerName());

        TownEntity townEntity = getOrCreateTown(dto);
        CountryEntity countryEntity = getOrCreateCountry(dto);
        CountyEntity countyEntity = getOrCreateCounty(dto);
        AddressLineEntity addressLineEntity1 = getOrCreateAddress(dto.getAddressLine1());
        AddressLineEntity addressLineEntity2 = getOrCreateAddress(dto.getAddressLine2());
        PostcodeEntity postcodeEntity = getOrCreatePostcode(dto);

        customerEntity.setAddressLine1(addressLineEntity1);
        customerEntity.setAddressLine2(addressLineEntity2);
        customerEntity.setTown(townEntity);
        customerEntity.setCounty(countyEntity);
        customerEntity.setPostcode(postcodeEntity);
        customerEntity.setCountry(countryEntity);

        return customerEntity;
    }

    private CountryEntity getOrCreateCountry(CustomerDto dto) {
        if (!countryEntityCache.isEmpty() && countryEntityCache.get(dto.getCountry()) != null) {
            return countryEntityCache.get(dto.getCountry());
        }
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCountryId(UUID.randomUUID());
        countryEntity.setCountryName(dto.getCountry());
        countryEntityCache.put(dto.getCountry(), countryEntity);
        return countryEntity;
    }

    private CountyEntity getOrCreateCounty(CustomerDto dto) {
        if (!countyEntityCache.isEmpty() && countyEntityCache.get(dto.getCounty()) != null) {
            return countyEntityCache.get(dto.getCounty());
        }
        CountyEntity countyEntity = new CountyEntity();
        countyEntity.setCountyId(UUID.randomUUID());
        countyEntity.setCountyName(dto.getCounty());

        CountryEntity countryEntity = getOrCreateCountry(dto);
        countyEntity.setCountry(countryEntity);
        countyEntityCache.put(dto.getCounty(), countyEntity);
        return countyEntity;
    }

    private AddressLineEntity getOrCreateAddress(String addressLine) {
        if (!addressLineEntityCache.isEmpty() && addressLineEntityCache.get(addressLine) != null) {
            return addressLineEntityCache.get(addressLine);
        }
        AddressLineEntity addressLineEntity = new AddressLineEntity();
        addressLineEntity.setAddressLineId(UUID.randomUUID());
        addressLineEntity.setAddressLine(addressLine);

        addressLineEntityCache.put(addressLine, addressLineEntity);
        return addressLineEntity;
    }

    private TownEntity getOrCreateTown(CustomerDto dto) {
        if (!townEntityCache.isEmpty() && townEntityCache.get(dto.getTown()) != null) {
            return townEntityCache.get(dto.getTown());
        }

        CountryEntity countryEntity = getOrCreateCountry(dto);
        CountyEntity countyEntity = getOrCreateCounty(dto);
        TownEntity townEntity = new TownEntity();
        townEntity.setTownId(UUID.randomUUID());
        townEntity.setTownName(dto.getTown());
        townEntity.setCountry(countryEntity);
        townEntity.setCounty(countyEntity);
        townEntityCache.put(dto.getTown(), townEntity);

        return townEntity;
    }

    private PostcodeEntity getOrCreatePostcode(CustomerDto dto) {
        if (!postcodeEntityCache.isEmpty() && postcodeEntityCache.get(dto.getPostcode()) != null) {
            return postcodeEntityCache.get(dto.getPostcode());
        }
        TownEntity townEntity = getOrCreateTown(dto);
        PostcodeEntity postcodeEntity = new PostcodeEntity();
        postcodeEntity.setPostcodeId(UUID.randomUUID());
        postcodeEntity.setTown(townEntity);
        postcodeEntity.setPostcode(dto.getPostcode());

        postcodeEntityCache.put(dto.getPostcode(), postcodeEntity);
        return postcodeEntity;
    }
}
