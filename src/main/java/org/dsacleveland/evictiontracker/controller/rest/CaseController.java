package org.dsacleveland.evictiontracker.controller.rest;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.LegacyCase;
import org.dsacleveland.evictiontracker.service.evictiondata.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CaseController extends AbstractRestController<CaseEntity, CaseDto, CaseService> {
    @Autowired
    public CaseController(CaseService entityService) {
        super(entityService);
    }

    @PostMapping("/upload_legacy")
    public List<CaseEntity> loadLegacyCases(@RequestBody List<LegacyCase> legacyCases) {
        return this.entityService.importFromLegacy(legacyCases);
    }
}
