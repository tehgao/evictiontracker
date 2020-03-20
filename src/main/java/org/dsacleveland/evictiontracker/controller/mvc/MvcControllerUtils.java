package org.dsacleveland.evictiontracker.controller.mvc;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MvcControllerUtils {
    public static List<String> generateIndices(Page<?> page) {
        List<String> indices = IntStream
                .rangeClosed(1, page.getTotalPages())
                .mapToObj(idx -> Integer.toString(idx))
                .collect(Collectors.toList());

        if (page.getTotalPages() > 15) {
            int lowerBound = max(0, page.getNumber() - 1);
            int upperBound = min(page.getNumber() + 2, page.getTotalPages());
            List<String> tempIndices = new ArrayList<>();
            if (lowerBound != 0) {
                tempIndices.add("1");
                tempIndices.add("...");
            }
            tempIndices.addAll(indices.subList(lowerBound, page.getNumber()));
            tempIndices.addAll(indices.subList(page.getNumber(), upperBound));
            if (upperBound != page.getTotalPages()) {
                tempIndices.add("...");
                tempIndices.add(Integer.toString(page.getTotalPages()));
            }

            indices = tempIndices;
        }

        return indices;
    }
}
