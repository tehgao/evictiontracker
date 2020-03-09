package org.dsacleveland.evictiontracker.controller.rest;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.AddressDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.AddressEntity;
import org.dsacleveland.evictiontracker.service.evictiondata.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController extends AbstractRestController<AddressEntity, AddressDto, AddressService> {
    public AddressController(AddressService entityService) {
        super(entityService);
    }

    @GetMapping("/search")
    public List<AddressEntity> search(@RequestParam String neighborhood) {
        return this.entityService.findByNeighborhood(neighborhood);
    }
}
