package org.dsacleveland.evictiontracker.service.pdfreader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.Preconditions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
class PdfReaderImplTest {

    private PdfReaderImpl classUnderTest;

    @BeforeEach
    void setUp() {
        this.classUnderTest = new PdfReaderImpl();
    }

    @Test
    void readFromPdf() throws IOException {
        Preconditions.condition(Files
                .exists(Paths.get("src/test/resources/pdf_tests/pdf_output.txt")), "expected text file must exist");
        Preconditions
                .condition(Files.exists(Paths.get("src/main/resources/pdf/evictions_2.pdf")), "pdf file must exist");

        PDDocument pdDocument = PDDocument.load(new File("src/main/resources/pdf/evictions_2.pdf"));

        String pdf = this.classUnderTest.readFromPdf(pdDocument);

        String actual = Files.readString(Paths.get("src/test/resources/pdf_tests/pdf_output.txt"));

        assertEquals(actual, pdf);
    }
}