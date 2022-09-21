package infoco.immo.files;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import infoco.immo.core.Apartment;
import infoco.immo.core.RentReceiptData;
import infoco.immo.core.Tenants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class FilesGenerator implements FilesGeneratorI {

    public static final String DESTINATION = "./rentReceipt.pdf";
    @Override
    public InputStream  generateRentReceipt(List<RentReceiptData> rentReceiptDataList, Apartment apartment, Tenants tenants, String from, String to) throws IOException {
        File file = new File(DESTINATION);
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(DESTINATION));
        Document document = new Document(pdfDocument);
        createHeader(document, tenants, apartment);
        Float calculateAmount = calculateAmount(rentReceiptDataList);
        createText(document, tenants, calculateAmount   );

        document.close();
        InputStream inputStream = Files.newInputStream(file.toPath());
        file.delete();
        return inputStream;

    }

    private void createHeader(Document document,Tenants tenants, Apartment apartment){

        Cell p = new Cell();
        p.add(new Paragraph("gestion immobilière infoco"));
        p.add(new Paragraph("20 rue de luxois 5700 lagardière"));
        p.add(new Paragraph());
        document.add(p);

        LocalDateTime date = LocalDateTime.now();

        Cell tenantParagraph = new Cell();
        tenantParagraph.add(new Paragraph(tenants.getName() + " " + tenants.getFirstName() + " " + apartment.getAddress()));
        tenantParagraph.add(new Paragraph("Fait à luxois, le " + date.getDayOfMonth()+ "/" + date.getMonthValue() + "/" + date.getYear()));
        tenantParagraph.add(new Paragraph());
        document.add(tenantParagraph);

        Cell address = new Cell();
        address.add(new Paragraph("Address de location"));
        address.add(new Paragraph(apartment.getAddress() + " " + apartment.getPostalCode() + " " + apartment.getCity()));
        address.add(new Paragraph());
        document.add(address);
    }

    private Float calculateAmount(List<RentReceiptData> rentReceiptDataList) {
        Float amount = 0F;
        for (RentReceiptData rentReceiptData: rentReceiptDataList) {
            if (rentReceiptData.getSens()){
                amount += rentReceiptData.getAmount();
            }else {
                amount -= rentReceiptData.getAmount();
            }
        }
      return amount;
    }

    private void createText(Document document, Tenants tenants, Float amount) {


        Paragraph text = new Paragraph();

        text.add("Je soussigné  infoco immo  gestionnaire  du logement désigné ci-dessus, déclare avoir reçu de Monsieur / Madame   " + tenants.getFirstName() + " " + tenants.getName()  +", la somme de " + amount + " euros, au titre du paiement du loyer et des charges  et lui en donne quittance, sous réserve de tous mes droits.");
        text.setTextAlignment(TextAlignment.JUSTIFIED);
        document.add(text);
    }

    @Override
    public void generateBanlanceSheet() {

    }
}
