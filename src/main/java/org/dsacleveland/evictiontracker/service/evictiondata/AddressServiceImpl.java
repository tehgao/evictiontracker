package org.dsacleveland.evictiontracker.service.evictiondata;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.AddressDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.AddressEntity;
import org.dsacleveland.evictiontracker.repository.AddressRepository;
import org.dsacleveland.evictiontracker.service.geo.GeocoderService;
import org.dsacleveland.evictiontracker.service.mapper.AddressMapper;
import org.dsacleveland.evictiontracker.service.type.AbstractEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl extends AbstractEntityService<AddressEntity, AddressDto, AddressRepository> implements AddressService {

    private GeocoderService geocoderService;

    @Autowired
    public AddressServiceImpl(AddressRepository repository,
                              GeocoderService geocoderService) {
        super(repository, AddressMapper.INSTANCE);
        this.geocoderService = geocoderService;
    }

    @Override
    public AddressEntity create(AddressDto obj) {
        AddressEntity entity = super.mapper.toEntity(obj);
        String address = entity.getStreetAddress() + " " + entity.getCity() + " "
                + entity.getState() + " " + entity.getZipCode();

        Optional.ofNullable(this.geocoderService.getAddressComponents(address))
                .map(addressComponents -> addressComponents.get("neighborhood"))
                .ifPresent(neighborhood -> entity.setNeighborhood(neighborhood));

        Optional.ofNullable(this.geocoderService.getCoordinates(address))
                .ifPresent(coordinates -> entity.setCoordinates(coordinates));

        return this.repository.save(entity);
    }

    @Override
    public Optional<AddressEntity> findOne(Example<AddressEntity> addressEntity) {
        return this.repository.findOne(addressEntity);
    }

    @Override
    public List<AddressEntity> findByNeighborhood(String neighborhood) {
        return this.repository.findByNeighborhood(neighborhood);
    }
}
