package org.dsacleveland.evictiontracker.controller.rest;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.service.type.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CaseController extends AbstractRestController<CaseDto> {
    @Autowired
    public CaseController(EntityService<CaseDto, UUID> entityService) {
        super(entityService);
    }
}
