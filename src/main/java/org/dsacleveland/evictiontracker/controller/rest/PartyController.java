package org.dsacleveland.evictiontracker.controller.rest;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.PartyDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.PartyEntity;
import org.dsacleveland.evictiontracker.service.evictiondata.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parties")
public class PartyController extends AbstractRestController<PartyEntity, PartyDto, PartyService> {
    @Autowired
    public PartyController(PartyService entityService) {
        super(entityService);
    }
}
