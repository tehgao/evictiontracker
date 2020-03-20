package org.dsacleveland.evictiontracker.controller.mvc;

import org.dsacleveland.evictiontracker.model.evictiondata.mvc.PartySummary;
import org.dsacleveland.evictiontracker.service.evictiondata.PartyMvcService;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/parties")
public class PartyMvcController {

    private PartyMvcService partyMvcService;

    public PartyMvcController(PartyMvcService partyMvcService) {
        this.partyMvcService = partyMvcService;
    }

    @GetMapping
    public ModelAndView getAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        Page<PartySummary> partySummaryPage = this.partyMvcService.getPaginatedPartySummaries(
                PageRequest.of(page.orElse(1) - 1, size.orElse(10), Sort.by("name"))
        );

        List<String> indices = MvcControllerUtils.generateIndices(partySummaryPage);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("all_parties");
        modelAndView.getModel().put("parties", partySummaryPage);
        modelAndView.getModel()
                    .put("pageRange", indices);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getOne(@PathVariable UUID id) {
        return new ModelAndView(
                "party_detail",
                Map.of("party", this.partyMvcService
                        .readOne(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Party with ID " + id + " not found")
                        )
                )
        );
    }
}
