package org.dsacleveland.evictiontracker.service.pdfreader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class PdfReaderImplTest {

    private PdfReaderImpl classUnderTest;

    @BeforeEach
    void setUp() {
        this.classUnderTest = new PdfReaderImpl();
    }

    @Test
    void readFromPdf() throws IOException {
        PDDocument pdDocument = PDDocument.load(new File("src/main/resources/pdf/evictions.pdf"));

        System.out.println(this.classUnderTest.readFromPdf(pdDocument));
    }
}