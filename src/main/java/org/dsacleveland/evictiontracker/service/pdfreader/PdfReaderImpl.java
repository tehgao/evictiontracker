package org.dsacleveland.evictiontracker.service.pdfreader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;

public class PdfReaderImpl implements PdfReader {
    @Override
    public String readFromPdf(PDDocument pdDocument) throws IOException {
        if (pdDocument.isEncrypted()) {
            throw new IllegalArgumentException("Document must not be encrypted");
        }

        PDFTextStripper stripper = new PDFTextStripper();
        return stripper.getText(pdDocument).replaceAll("\\r\\n?", "\n");
    }
}
