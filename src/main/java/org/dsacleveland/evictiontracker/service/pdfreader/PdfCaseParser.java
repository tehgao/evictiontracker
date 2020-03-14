package org.dsacleveland.evictiontracker.service.pdfreader;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;

import java.util.List;

public interface PdfCaseParser {
    List<CaseDto> parseCasesFromPdfText(String pdfText);
}
