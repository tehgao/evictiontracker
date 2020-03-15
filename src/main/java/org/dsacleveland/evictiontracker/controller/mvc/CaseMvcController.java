package org.dsacleveland.evictiontracker.controller.mvc;

import org.dsacleveland.evictiontracker.model.evictiondata.mvc.CaseSummary;
import org.dsacleveland.evictiontracker.service.evictiondata.CaseMvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/cases")
public class CaseMvcController {

    private CaseMvcService caseMvcService;

    @Autowired
    public CaseMvcController(CaseMvcService caseMvcService) {
        this.caseMvcService = caseMvcService;
    }

    @GetMapping
    public ModelAndView getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        Page<CaseSummary> casePage = this.caseMvcService
                .getPaginatedCaseSummaries(
                        PageRequest.of(
                                page.orElse(1) - 1,
                                size.orElse(10),
                                Sort.by("fileDate").descending()
                        )
                );

        return getAllCasesModelAndView(casePage, "");
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String neighborhood,
                               @RequestParam Optional<Integer> page,
                               @RequestParam Optional<Integer> size) {
        Page<CaseSummary> casePage = this.caseMvcService
                .getPaginatedCaseSummaryByNeighborhood(
                        neighborhood,
                        PageRequest.of(
                                page.orElse(1) - 1,
                                size.orElse(10),
                                Sort.by("fileDate").descending()
                        )
                );

        return getAllCasesModelAndView(casePage, neighborhood);
    }

    @GetMapping("/{id}")
    public ModelAndView getOne(@PathVariable UUID id) {
        return new ModelAndView(
                "case_detail",
                Map.of("case", this.caseMvcService
                        .readOne(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Case with ID " + id + " not found")
                        )
                )
        );
    }

    private ModelAndView getAllCasesModelAndView(Page<CaseSummary> casePage, String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("all_cases");
        modelAndView.getModel().put("cases", casePage);
        modelAndView.getModel().put("searchValue", searchString);
        modelAndView.getModel().put("numCases", casePage.getTotalElements());
        modelAndView.getModel()
                    .put("pageRange", IntStream
                            .rangeClosed(1, casePage.getTotalPages())
                            .boxed()
                            .collect(Collectors.toList())
                    );
        return modelAndView;
    }
}
