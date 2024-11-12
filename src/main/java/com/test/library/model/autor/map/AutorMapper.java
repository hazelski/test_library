package com.test.library.model.autor.map;

import com.test.library.model.autor.domain.AutorDomain;
import com.test.library.model.autor.dto.AutorRequestDTO;
import com.test.library.model.autor.dto.AutorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutorMapper {

    AutorMapper INSTANCE = Mappers.getMapper(AutorMapper.class);

    AutorDomain requestToDomain(AutorRequestDTO autor);

    AutorResponseDTO domainToResponse(AutorDomain autor);
}
