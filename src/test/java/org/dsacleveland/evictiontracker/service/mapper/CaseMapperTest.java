package org.dsacleveland.evictiontracker.service.mapper;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactoryImpl;

class CaseMapperTest {

    private CaseMapper classUnderTest;

    @BeforeEach
    void setUp() {
        this.classUnderTest = CaseMapper.INSTANCE;
    }

    @Test
    void toDto() {
        CaseEntity entity = new PodamFactoryImpl().manufacturePojo(CaseEntity.class);

        CaseDto actual = this.classUnderTest.toDto(entity);

        System.out.println(actual);
    }

    @Test
    void toEntity() {
        CaseDto dto = new PodamFactoryImpl().manufacturePojo(CaseDto.class);
        CaseEntity actual = this.classUnderTest.toEntity(dto);

        System.out.println(actual);
    }
}