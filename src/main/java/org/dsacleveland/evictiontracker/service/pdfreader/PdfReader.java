package org.dsacleveland.evictiontracker.service.pdfreader;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;

public interface PdfReader {
    String readFromPdf(PDDocument pdDocument) throws IOException;
}
