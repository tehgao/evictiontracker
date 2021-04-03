package org.dsacleveland.evictiontracker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.service.pdfreader.PdfCaseParser;
import org.dsacleveland.evictiontracker.service.pdfreader.PdfCaseParserImpl;
import org.dsacleveland.evictiontracker.service.pdfreader.PdfReader;
import org.dsacleveland.evictiontracker.service.pdfreader.PdfReaderImpl;

import java.io.IOException;
import java.util.List;

public class EvictionTrackerApplication {

    public static void main(String[] args) throws IOException  {
        PDDocument document = PDDocument.load(System.in);
        PdfCaseParser pdfCaseParser = new PdfCaseParserImpl();
        PdfReader pdfReader = new PdfReaderImpl();
        List<CaseDto> cases = pdfCaseParser.parseCasesFromPdfText(pdfReader.readFromPdf(document));
        document.close();

        // Write tocsv methods for all datatypes (this may depend on preferred csv format)
        // fix parser to be better
        createCSV(System.out, cases);
    }

    public static void createCSV(Appendable out, List<CaseDto> cases) throws IOException {
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(CaseDto.HEADERS))) {
            cases.forEach(c -> {
                try {
                    printer.printRecord(c.toCSV());
                } catch (IOException e) {
                    System.err.println("IOException printing record: " + e.getMessage());
                    e.printStackTrace();
                }
            });
        }
    }
}
