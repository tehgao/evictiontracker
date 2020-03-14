package org.dsacleveland.evictiontracker.service.pdfreader;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.Preconditions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
class PdfCaseParserImplTest {

    private PdfCaseParser classUnderTest;

    @BeforeEach
    void setUp() {
        this.classUnderTest = new PdfCaseParserImpl();
    }

    @Test
    void parseCasesFromPdfText() throws IOException {
        Preconditions.condition(Files
                .exists(Paths.get("src/test/resources/pdf_tests/pdf_output.txt")), "expected text file must exist");
        String pdfText = Files.readString(Path.of("src/test/resources/pdf_tests/pdf_output.txt"));

        List<CaseDto> actual = this.classUnderTest.parseCasesFromPdfText(pdfText);

        assertNotNull(actual);
    }

    @Test
    void parseCasesFromPdfInjunctive() throws IOException {
        Preconditions.condition(Files
                .exists(Paths
                        .get("src/test/resources/pdf_tests/pdf_output_injunctive.txt")), "expected text file must exist");
        String pdfText = Files.readString(Path.of("src/test/resources/pdf_tests/pdf_output_injunctive.txt"));

        List<CaseDto> actual = this.classUnderTest.parseCasesFromPdfText(pdfText);

        assertNotNull(actual);
    }
}