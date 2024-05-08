package com.iText.IText.Utils;


import com.iText.IText.Entity.BankTransaction;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PdfUtils {

     static List<BankTransaction> transactions = fetchBankDetails();
    public static ResponseEntity<byte[]> generatePdf() {


        try {
            byte[] pdfBytes = generatePdfBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "BankStatement.pdf");
            headers.setContentLength(pdfBytes.length);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static byte[] generatePdfBytes() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Paragraph title = new Paragraph("Bank Account Statement", boldFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(4);

        Font headerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        PdfPCell cell = new PdfPCell(new Phrase("Date", headerFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Description", headerFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Amount", headerFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Balance", headerFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);
        table.addCell(cell);

        for (BankTransaction transaction : transactions) {
            table.addCell(transaction.getDate().toString());
            table.addCell(transaction.getDescription());
            table.addCell(String.valueOf(transaction.getAmount()));
            table.addCell(String.valueOf(transaction.getBalance()));
        }

        document.add(table);
        document.close();
        return outputStream.toByteArray();
    }

    public static List<BankTransaction> fetchBankDetails(){
        BankTransaction bankTransaction = new BankTransaction(LocalDate.of(2024, 4, 20), "Payment for House Rent", 10000.00, 50000.00);
        BankTransaction bankTransaction2 = new BankTransaction(LocalDate.of(2024, 4, 22), "Payment for Shop Rent", 10000.00, 40000.00);
        return Arrays.asList(bankTransaction, bankTransaction2);
    }
}
