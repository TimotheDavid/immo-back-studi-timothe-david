package infoco.immo.files;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.minmaxwidth.MinMaxWidth;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import infoco.immo.core.*;
import infoco.immo.database.SQL.tenant.TenantBalanceSheet;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class FilesGenerator implements FilesGeneratorI {

    public static final String DESTINATION = "./rentReceipt.pdf";

    public static final DateTimeFormatter parser = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    public InputStream generateRentReceipt(List<RentReceiptData> rentReceiptDataList, Apartment apartment, Tenants tenants, String from, String to) throws IOException {
        File file = new File(DESTINATION);
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(DESTINATION));
        Document document = new Document(pdfDocument);
        createHeader(document, tenants, apartment, "Quittance de Loyer");
        Float calculateAmount = calculateAmount(rentReceiptDataList);
        createText(document, tenants, calculateAmount);

        document.close();
        InputStream inputStream = Files.newInputStream(file.toPath());
        file.delete();
        return inputStream;

    }

    private void createHeader(Document document, Tenants tenants, Apartment apartment, String name) {
        Paragraph title = new Paragraph(name);
        title.setBold();
        title.setFontSize(25);
        title.setUnderline();
        title.setTextAlignment(TextAlignment.CENTER);

        document.add(title);
        Cell p = new Cell();
        p.add(new Paragraph("gestion immobilière infoco"));
        p.add(new Paragraph("20 rue de luxois 5700 lagardière"));
        p.add(new Paragraph("\n"));
        document.add(p);

        LocalDateTime date = LocalDateTime.now();

        Cell tenantParagraph = new Cell();
        tenantParagraph.add(new Paragraph(tenants.getName() + " " + tenants.getFirstName() + " " + apartment.getAddress()));
        tenantParagraph.add(new Paragraph("Fait à luxois, le " + date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear()));
        tenantParagraph.add(new Paragraph());
        tenantParagraph.add(new Paragraph("\n"));

        document.add(tenantParagraph);


        Cell address = new Cell();
        address.add(new Paragraph("Addresse de location"));
        address.add(new Paragraph(apartment.getAddress() + " " + apartment.getPostalCode() + " " + apartment.getCity()));
        address.add(new Paragraph());
        address.add(new Paragraph("\n\n"));

        document.add(address);


    }


    private Float calculateAmountSheet(List<TenantBalanceSheet> rentReceiptDataList) {
        Float amount = 0F;
        for (TenantBalanceSheet rentReceiptData : rentReceiptDataList) {
            if (rentReceiptData.getSens()) {
                amount += rentReceiptData.getAmount();
            } else {
                amount -= rentReceiptData.getAmount();
            }
        }
        return amount;
    }
    private Float calculateAmount(List<RentReceiptData> rentReceiptDataList) {
        Float amount = 0F;
        for (RentReceiptData rentReceiptData : rentReceiptDataList) {
            if (rentReceiptData.getSens()) {
                amount += rentReceiptData.getAmount();
            } else {
                amount -= rentReceiptData.getAmount();
            }
        }
        return amount;
    }

    private void createText(Document document, Tenants tenants, Float amount) {


        Paragraph text = new Paragraph();

        text.add("Je soussigné  infoco immo  gestionnaire  du logement désigné ci-dessus, déclare avoir reçu de Monsieur / Madame   " + tenants.getFirstName() + " " + tenants.getName() + ", la somme de " + amount + " euros, au titre du paiement du loyer et des charges  et lui en donne quittance, sous réserve de tous mes droits.");
        text.setTextAlignment(TextAlignment.JUSTIFIED);
        document.add(text);
    }

    @Override
    public InputStream generateBalanceSheet(List<TenantBalanceSheet> tenantBalanceSheets, Tenants tenants, Apartment apartment) throws IOException {
        File file = new File(DESTINATION);
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(DESTINATION));
        Document document = new Document(pdfDocument);
        createHeader(document, tenants, apartment, "Bilan des Comptes des loyers");
        List<TenantBalanceSheet> rents = getFromType(tenantBalanceSheets, FromType.LOYER);
        List<TenantBalanceSheet> caution = getFromType(tenantBalanceSheets, FromType.CAUTION);
        Float calculatRent = calculate(rents);
        Float calculateCaution = calculate(caution);

        createTemplate(document, tenantBalanceSheets, "Caution");


        document.close();
        InputStream inputStream = Files.newInputStream(file.toPath());
        file.delete();
        return inputStream;
    }

    private List<TenantBalanceSheet> getFromType(List<TenantBalanceSheet> tenantBalanceSheets, FromType fromType) {
        return tenantBalanceSheets.stream().filter(tenantBalanceSheet -> tenantBalanceSheet.getFromType().equals(fromType)).collect(Collectors.toList());
    }

    private List<TenantBalanceSheet> getOrigin(List<TenantBalanceSheet> tenantBalanceSheets, Origin origin) {
        return tenantBalanceSheets.stream().filter(tenantBalanceSheet -> tenantBalanceSheet.getOrigin().equals(origin)).collect(Collectors.toList());
    }


    private List<TenantBalanceSheet> geMonth(List<TenantBalanceSheet> tenantBalanceSheets, Month monthValue) {

        List<TenantBalanceSheet> results = new ArrayList<>();
        for (TenantBalanceSheet tenantBalanceSheet : tenantBalanceSheets) {
            LocalDate date = LocalDate.parse(tenantBalanceSheet.getDatePayment(), parser);
            Month month = Month.from(date.getMonth());
            if (month == monthValue) {
                results.add(tenantBalanceSheet);
            }
        }
        return results;
    }


    private Float calculate(List<TenantBalanceSheet> tenantBalanceSheets) {

        Float result = 0F;
        for (TenantBalanceSheet tenantBalanceSheet : tenantBalanceSheets) {
            if (tenantBalanceSheet.getSens()) {
                result += tenantBalanceSheet.getAmount();
            } else {
                result -= tenantBalanceSheet.getAmount();
            }
        }
        return result;
    }


    private String getSens(TenantBalanceSheet tenantBalanceSheet) {
        String result;

        if (tenantBalanceSheet.getSens()) {
            result = String.valueOf(tenantBalanceSheet.getAmount());
        } else {
            result = "- " + String.valueOf(tenantBalanceSheet.getAmount());
        }

        return result;

    }

    private Integer getNumberOfMonth(List<TenantBalanceSheet> tenantBalanceSheets, Integer beginMonthValue) {
        Integer numberOfMonth = 0;
        for (TenantBalanceSheet tenantBalanceSheet : tenantBalanceSheets) {
            LocalDate date = LocalDate.parse(tenantBalanceSheet.getDatePayment(), parser);

            if (date.getMonthValue() > beginMonthValue) {
                beginMonthValue++;
                numberOfMonth++;
            }
        }
        return numberOfMonth;
    }

    public Cell createCell(String content, float borderWidth, int colspan, TextAlignment alignment,boolean borderless) {
        Cell cell = new Cell(1, colspan).add(new Paragraph(content));
        cell.setTextAlignment(alignment);
        cell.setBorder(new SolidBorder(borderWidth));
        if (borderless) {
            cell.setBorder(Border.NO_BORDER);
        }
        return cell;
    }

    private void createTemplate(Document document, List<TenantBalanceSheet> tenantBalanceSheets, String name) {
        Cell caution = new Cell();
        Paragraph title = new Paragraph(String.format("%s", name));
        title.setBold();
        title.setFontSize(20);
        title.add(new Paragraph("\n"));
        document.add(title);


        caution.add(title);

        Table table = new Table(new float[]{1, 1, 1, 1});
        table.setWidth(550);
        table.addCell(createCell("Date", 2, 1, TextAlignment.LEFT, false));
        table.addCell(createCell("Details", 2, 1, TextAlignment.LEFT, false));
        table.addCell(createCell("Crédits", 2, 1, TextAlignment.LEFT, false));
        table.addCell(createCell("Débits", 2, 1, TextAlignment.LEFT, false));

        LocalDate dateBegin = LocalDate.parse(tenantBalanceSheets.get(0).getDatePayment(), parser);
        Integer numberOfMonth = getNumberOfMonth(tenantBalanceSheets, dateBegin.getMonthValue());
        for (int i = 0; i <= numberOfMonth; i++) {

            Month month = Month.of(dateBegin.getMonthValue() + i);
            List<TenantBalanceSheet> balanceSheetListOfMonth = geMonth(tenantBalanceSheets, month);
            String monthName = month.getDisplayName(TextStyle.FULL, Locale.FRANCE);
            table.addCell(createCell(String.format("1.%s", dateBegin.getMonthValue() + i), 2, 1, TextAlignment.LEFT, false));
            Paragraph rentDate = new Paragraph(String.format("Loyer %s", monthName));
            rentDate.setUnderline();
            rentDate.setFontSize(20);
            rentDate.setItalic();
            table.addCell(rentDate);

            table.addCell(createCell(" ", 2, 1, TextAlignment.LEFT, false));
            table.addCell(createCell(String.format("%s", tenantBalanceSheets.get(0).getRent()), 2, 1, TextAlignment.LEFT, false));
            List<TenantBalanceSheet> tenantBalanceSheetRent = getFromType(tenantBalanceSheets, FromType.LOYER);
            List<TenantBalanceSheet> getCafOrigin = getOrigin(balanceSheetListOfMonth, Origin.CAF);
            List<TenantBalanceSheet> getRentOrigin = getOrigin(balanceSheetListOfMonth, Origin.LOCATAIRE);

            for (TenantBalanceSheet tenantBalanceSheet : getRentOrigin) {
                LocalDate date = LocalDate.parse(tenantBalanceSheet.getDatePayment(), parser);
                Month monthHeaderRent = Month.of(date.getMonthValue() + i);
                String monthNameHeaderRent = monthHeaderRent.getDisplayName(TextStyle.FULL, Locale.FRANCE);
                table.addCell(createCell(String.format("%s.%s", date.getDayOfMonth(), date.getMonthValue()), 2, 1, TextAlignment.LEFT, false));
                table.addCell(createCell(String.format("Vos payment"), 2, 1, TextAlignment.LEFT, false));
                table.addCell(createCell(String.format("%s", tenantBalanceSheet.getAmount()), 2, 1, TextAlignment.LEFT, false));
                table.addCell(createCell(" ", 2, 1, TextAlignment.LEFT, false));
            }



            for (TenantBalanceSheet tenantBalanceSheet : getCafOrigin) {
                LocalDate date = LocalDate.parse(tenantBalanceSheet.getDatePayment(), parser);
                Month monthHeaderRent = Month.of(date.getMonthValue() + i);
                String monthNameHeaderRent = monthHeaderRent.getDisplayName(TextStyle.FULL, Locale.FRANCE);
                table.addCell(createCell(String.format("%s.%s", date.getDayOfMonth(), date.getMonthValue()), 2, 1, TextAlignment.LEFT, false));
                table.addCell(createCell(String.format("Payment CAF"), 2, 1, TextAlignment.LEFT, false));
                table.addCell(createCell(String.format("%s", tenantBalanceSheet.getAmount()), 2, 1, TextAlignment.LEFT, false));
                table.addCell(createCell(" ", 2, 1, TextAlignment.LEFT, false));
            }


        }

        Paragraph lineEnd = new Paragraph("\n");
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(table);
        document.add(lineEnd);

        LineSeparator line = new LineSeparator(new SolidLine());

        Table result = new Table(2);
        result.addCell(createCell("Total :", 0, 0, TextAlignment.LEFT, true));
        result.addCell(createCell(String.format("%s €", calculateAmountSheet(tenantBalanceSheets)), 0, 0, TextAlignment.RIGHT, true));
        result.setWidth(550);
        document.add(result);

    }


}

