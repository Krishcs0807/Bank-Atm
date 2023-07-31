package com.miniatm.okbankatm.Controller;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.miniatm.okbankatm.Model.StatementModel;
import com.miniatm.okbankatm.Services.NewAccountService;
import com.miniatm.okbankatm.Services.StatementService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class StatementController {

    @Autowired
    private StatementService statementService;

    private String AcNumber;
    private String pinNumber;

    @GetMapping("StatementView")
    public ModelAndView statementView() {
        ModelAndView mav = new ModelAndView("Statement");
        return mav;
    }

    @RequestMapping(value = "StatementDetail", method = RequestMethod.POST)
    public ModelAndView transactionHistory(
            @RequestParam(value = "AcNumber", required = false) String AcNumber,
            @RequestParam(value = "pinNumber", required = false) String pinNumber, Model model) {
        this.AcNumber = AcNumber;
        this.pinNumber = pinNumber;

        ModelAndView mav;
        if (statementService.accountStatement(AcNumber, pinNumber) == null) {
            mav = new ModelAndView("Statement");
            model.addAttribute("msg", "plese Enter correct pin or Ac-Number");
            return mav;

        } else {
            List<StatementModel> AcHistory = statementService.accountStatement(AcNumber, pinNumber);
            mav = new ModelAndView("Acstatements");
            model.addAttribute("Data", AcHistory);
            return mav;

        }

    }

    @Autowired
    NewAccountService newACservice;

    @GetMapping(value = "/download/pdf")
    @ResponseBody
    public void downloadPdf(HttpServletResponse response) throws IOException {
        List<StatementModel> Data = statementService.accountStatement(AcNumber, pinNumber);

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                float margin = 50;
                float yStart = page.getMediaBox().getHeight() - margin;
                float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
                float yPosition = yStart;
                float cellMargin = 5f;
                float fontSize = 13;
                float rowHeight = 25;
                float tableHeaderYPosition = yPosition;

                PDColor lightGray = new PDColor(new float[] { 0.8f, 0.8f, 0.8f }, PDDeviceRGB.INSTANCE);

                // Draw the headline at the top
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                float textWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Your Account Statement") / 1000 * 18;
                float textX = (page.getMediaBox().getWidth() - textWidth) / 2;
                float textY = page.getMediaBox().getHeight() - 30;
                contentStream.newLineAtOffset(textX, textY);
                contentStream.showText("Your Account Statement");
                contentStream.endText();

                // Draw the table header
                drawRectangle(contentStream, margin, tableHeaderYPosition - 10, tableWidth, rowHeight, lightGray);
                contentStream.beginText();
                PDColor black = new PDColor(new float[] { 0, 0, 0 }, PDDeviceRGB.INSTANCE);
                contentStream.setNonStrokingColor(black);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
                contentStream.newLineAtOffset(margin + cellMargin, yPosition - 10);
                contentStream.showText("  ");
                contentStream.newLineAtOffset(10, 5);
                contentStream.showText("Date");
                contentStream.newLineAtOffset(105, 0);
                contentStream.showText("Time");
                contentStream.newLineAtOffset(105, 0);
                contentStream.showText("Credit");
                contentStream.newLineAtOffset(105, 0);
                contentStream.showText("Debit");
                contentStream.newLineAtOffset(105, 0);
                contentStream.showText("Balance");
                contentStream.endText();
                yPosition -= rowHeight;

                int colorChange = 1;
                PDColor lightYellow;
                for (StatementModel data : Data) {
                    // Draw the table rows
                    if (colorChange % 2 == 0) {
                        lightYellow = new PDColor(new float[] { 1, 1, 0.5f }, PDDeviceRGB.INSTANCE);
                    } else {
                        lightYellow = new PDColor(new float[] { 1, 1, 1 }, PDDeviceRGB.INSTANCE);
                    }
                    colorChange++;
                    drawRectangle(contentStream, margin, yPosition - 10, tableWidth, 17, lightYellow);
                    contentStream.beginText();
                    PDColor blackColor1 = new PDColor(new float[] { 0, 0, 0 }, PDDeviceRGB.INSTANCE);
                    contentStream.setNonStrokingColor(blackColor1);
                    contentStream.newLineAtOffset(margin + cellMargin, yPosition - 10);
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize - 2);
                    contentStream.showText("  ");
                    contentStream.newLineAtOffset(5, 5);
                    contentStream.showText(data.getDate());
                    contentStream.newLineAtOffset(110, 0);
                    contentStream.showText(data.getTime());
                    contentStream.newLineAtOffset(110, 0);
                    contentStream.showText(String.valueOf(data.getCredit()));
                    contentStream.newLineAtOffset(110, 0);
                    contentStream.showText(String.valueOf(data.getDebit()));
                    contentStream.newLineAtOffset(110, 0);
                    contentStream.showText(String.valueOf(data.getBalance()));
                    contentStream.endText();

                    yPosition -= rowHeight;

                }
            }
            PDDocumentInformation info = document.getDocumentInformation();
            info.setAuthor("Ok Bank");

            StandardProtectionPolicy spp = new StandardProtectionPolicy("12345678", Data.get(0).getAccountNumber(),
                    new AccessPermission());
            spp.setEncryptionKeyLength(128);
            document.protect(spp);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + newACservice.findbyAc(AcNumber) + ".pdf");

            document.save(response.getOutputStream());
            document.close();
        }
    }

    private void drawRectangle(PDPageContentStream contentStream, float x, float y, float width, float height,
            PDColor color) throws IOException {
        contentStream.saveGraphicsState();
        contentStream.setNonStrokingColor(color);
        contentStream.addRect(x, y, width, height);
        contentStream.fill();
        contentStream.restoreGraphicsState();

    }

}
