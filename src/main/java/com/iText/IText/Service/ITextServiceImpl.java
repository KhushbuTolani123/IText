package com.iText.IText.Service;

import com.iText.IText.Entity.BankTransaction;
import com.iText.IText.Utils.PdfUtils;
import com.itextpdf.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class ITextServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(ITextServiceImpl.class);

    public ResponseEntity<byte[]> DownloadPdf() throws DocumentException, FileNotFoundException {
        return PdfUtils.generatePdf();
    }

}
