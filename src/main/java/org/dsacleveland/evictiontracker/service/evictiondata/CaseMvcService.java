package org.dsacleveland.evictiontracker.service.evictiondata;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.mvc.CaseSummary;
import org.dsacleveland.evictiontracker.repository.CaseRepository;
import org.dsacleveland.evictiontracker.service.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaseMvcService extends CaseServiceImpl {

    @Autowired
    public CaseMvcService(CaseRepository repository, DtoMapper<CaseEntity, CaseDto> caseMapper) {
        super(repository, caseMapper);
    }

    public List<CaseSummary> getAllCaseSummaries() {
        return super
                .readAll()
                .stream()
                .map(this::mapToCaseSummary)
                .collect(Collectors.toList());
    }

    public Page<CaseSummary> getPaginatedCaseSummaryByNeighborhood(String neighborhood, Pageable pageable) {
        return this.repository
                .findAllByNeighborhood(neighborhood, pageable)
                .map(this::mapToCaseSummary);
    }

    public Page<CaseSummary> getPaginatedCaseSummaries(Pageable pageable) {
        return this.repository.findAll(pageable).map(this::mapToCaseSummary);
    }

    private CaseSummary mapToCaseSummary(CaseEntity caseResponse) {
        return CaseSummary
                .builder()
                .id(caseResponse.getId().toString())
                .caseNumber(caseResponse.getCaseNumber())
                .fileDate(caseResponse.getFileDate())
                .plaintiffs(caseResponse.getPlaintiffs().stream()
                                        .map(party -> party.getName())
                                        .reduce((a, b) -> a.concat("; ").concat(b))
                                        .orElse(""))
                .defendants(caseResponse.getDefendants().stream()
                                        .map(party -> party.getName())
                                        .reduce((a, b) -> a.concat("; ").concat(b))
                                        .orElse(""))
                .build();
    }
}
