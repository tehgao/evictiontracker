package org.dsacleveland.evictiontracker.service.evictiondata;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.AddressDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.AddressEntity;
import org.dsacleveland.evictiontracker.service.type.EntityService;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressService extends EntityService<AddressEntity, AddressDto, UUID> {
    Optional<AddressEntity> findOne(Example<AddressEntity> addressEntity);

    List<AddressEntity> findByNeighborhood(String neighborhood);

    AddressEntity findOrCreateNew(AddressEntity addressEntity);
}
