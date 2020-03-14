package org.dsacleveland.evictiontracker.controller.rest;

import org.dsacleveland.evictiontracker.model.evictiondata.dto.CaseDto;
import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.dsacleveland.evictiontracker.model.evictiondata.legacy.LegacyCase;
import org.dsacleveland.evictiontracker.service.evictiondata.CaseService;
import org.dsacleveland.evictiontracker.service.evictiondata.PdfUploadService;
import org.dsacleveland.evictiontracker.service.util.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cases")
public class CaseController extends AbstractRestController<CaseEntity, CaseDto, CaseService> {

    private PdfUploadService pdfUploadService;
    private EmailSenderService emailSenderService;

    private String alertEmailAddr;

    @Autowired
    public CaseController(CaseService entityService,
                          PdfUploadService pdfUploadService,
                          EmailSenderService emailSenderService,
                          @Value("${org.dsacleveland.evictiontracker.alert-email:#{null}}") String alertEmailAddr) {
        super(entityService);
        this.pdfUploadService = pdfUploadService;
        this.emailSenderService = emailSenderService;
        this.alertEmailAddr = alertEmailAddr;
    }

    @PostMapping("/upload_legacy")
    public List<CaseEntity> loadLegacyCases(@RequestBody List<LegacyCase> legacyCases) {
        return this.entityService.importFromLegacy(legacyCases);
    }

    @PostMapping("/upload-pdf")
    public void uploadPdf(@RequestParam("file") MultipartFile pdfFile) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        pdfUploadService.loadPdf(pdfFile.getBytes(), () ->
                Optional.ofNullable(this.alertEmailAddr).ifPresent(email ->
                        emailSenderService.sendMessage(
                                "gaovinal@gmail.com",
                                "Load started at " + now.toString() + " completed"
                        )
                )
        );
    }
}
