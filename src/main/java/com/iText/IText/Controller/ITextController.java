package com.iText.IText.Controller;

import com.iText.IText.Service.ITextServiceImpl;
import com.itextpdf.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/pdf")
@AllArgsConstructor
public class ITextController {
    private final ITextServiceImpl iTextService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateDocument() throws DocumentException, FileNotFoundException {
       return iTextService.DownloadPdf();
    }
}
