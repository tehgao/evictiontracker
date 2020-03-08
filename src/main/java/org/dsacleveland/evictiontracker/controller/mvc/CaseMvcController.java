package org.dsacleveland.evictiontracker.controller.mvc;

import org.dsacleveland.evictiontracker.model.evictiondata.mvc.CaseSummary;
import org.dsacleveland.evictiontracker.service.evictiondata.CaseMvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
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
                .getPaginatedCaseSummaries(PageRequest.of(page.orElse(1) - 1, size.orElse(10)));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("all_cases");
        modelAndView.getModel().put("cases", casePage);
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
