package org.example;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.itextpdf.text.FontFactory.getFont;

public class Main {
    public static void main(String[] args) {
        Document document = new Document(PageSize.A4, 36, 36, 150, 36); // Establecer márgenes

        try {
            FileOutputStream fos = new FileOutputStream("formato_silabo1.pdf");
            PdfWriter writer = PdfWriter.getInstance(document, fos);

            // Establecer el evento del encabezado
            EncabezadoDos encabezado = new EncabezadoDos();
            writer.setPageEvent(encabezado);

            document.open();

                facultadEscuela(document);

                datosBasicosModulo(document);

            document.close();
            fos.close();

            System.out.println("PDF creado o modificado");

        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al crear o modificar el PDF");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void facultadEscuela(Document document) throws DocumentException {
        //Fuente
        Font font = FontFactory.getFont("Gill Sans MT", 10, Font.BOLD);
        Chunk facultad = new Chunk("FACULTAD DE: ", font);
        Chunk facultadDos = new Chunk("INGENIERÍA, DISEÑO E INNOVACIÓN BD", font);

        Paragraph parrafoFacu = new Paragraph();
        parrafoFacu.add(facultad);
        parrafoFacu.add(facultadDos);
        parrafoFacu.setAlignment(Element.ALIGN_CENTER); // Alineación centrada

        document.add(parrafoFacu);

        Chunk escuela = new Chunk("ESCUELA DE: ", font);
        Chunk escuelaDos = new Chunk("TECNOLOGÍAS DE LA INFORMACIÓN Y LAS COMUNICACIONES BD", font);

        Paragraph parrafoEscu = new Paragraph();
        parrafoEscu.add(escuela);
        parrafoEscu.add(escuelaDos);
        parrafoEscu.setAlignment(Element.ALIGN_CENTER); // Alineación centrada
        document.add(parrafoEscu);
    }
    public static void datosBasicosModulo(Document document) throws DocumentException {

        //Cuanto mide la hoja
        document.add(new Paragraph("  "));
        float pageWidth = document.getPageSize().getWidth();
        float pageHeight = document.getPageSize().getHeight();

        //creacion de la tabla
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(80); // Ancho de la tabla (en porcentaje del ancho de la página)
        table.setTotalWidth(pageWidth * 0.8f); // Ancho de la tabla (en puntos)
        table.setLockedWidth(true); // Bloquear el ancho de la tabla

        float[] columnWidths = {1, 2};
        table.setWidths(columnWidths);


        //agregar celdas a la tabla
        PdfPCell cell1 =  new PdfPCell(new Phrase("     I.                  DATOS BÁSICOS DEL MODULO", FontFactory.getFont("Gill Sans MT", 8, Font.BOLD, BaseColor.WHITE)));

        PdfPCell cell2 =  new PdfPCell(new Phrase("NOMBRE MODULO", FontFactory.getFont("Gill Sans MT", 8, Font.BOLD)));
        PdfPCell cell3 =  new PdfPCell(new Phrase("Medios de Transmisión", FontFactory.getFont("Gill Sans MT", 9)));

        PdfPCell cell4 =  new PdfPCell(new Phrase("CÓDIGO DEL MODULO", FontFactory.getFont("Gill Sans MT", 8, Font.BOLD)));
        PdfPCell cell5 =  new PdfPCell(new Phrase("TIC49999", FontFactory.getFont("Gill Sans MT", 9)));



        // Configurar celdas
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell5.setHorizontalAlignment(Element.ALIGN_LEFT);


        //Unificar celdas
        cell1.setColspan(2);
        BaseColor color = new BaseColor(16,52,92);
        cell1.setBackgroundColor(color);

        //
        // Agregar celdas a la tabla
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);


        document.add(table);
    }

}



class EncabezadoDos extends PdfPageEventHelper {
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            // Configurar imagen para el encabezado
            Image imagenEncabezado = Image.getInstance("D:\\Proyectos Java\\CreadordePdfDos\\pdf\\img\\logo.png"); // Ruta a la imagen
            imagenEncabezado.scaleToFit(100, 100); // Escalar la imagen


            //Cuanto mide la hoja
            float pageWidth = document.getPageSize().getWidth();
            float pageHeight = document.getPageSize().getHeight();

            //Cuanto mide la imagen
            float imageWidth = imagenEncabezado.getScaledWidth();
            float imageHeight = imagenEncabezado.getScaledHeight();
            //Calcular posicion imagen
            float positionX = 40;
            float positionY = pageHeight - imageHeight;
            //Ubicar la imagen
            imagenEncabezado.setAbsolutePosition((pageWidth-imageWidth)/2,  pageHeight - imageHeight - 20);
            PdfContentByte under = writer.getDirectContentUnder();
            under.addImage(imagenEncabezado);

            //tabla
            //Crear la tabla
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(80); // Ancho de la tabla (en porcentaje del ancho de la página)
            table.setTotalWidth(pageWidth * 0.8f); // Ancho de la tabla (en puntos)
            table.setLockedWidth(true); // Bloquear el ancho de la tabla

            float[] columnWidths = {1, 2, 1}; // las col 1 y 3 tienen el mismo ancho, Columna 2 tiene el doble
            table.setWidths(columnWidths);

            // Agregar celdas a la tabla
            PdfPCell cell1 = new PdfPCell(new Phrase("PROCESO:"+"\n"+"Diseño y Desarrollo de Programas Académicos",FontFactory.getFont("Gill Sans MT", 10, Font.BOLD)));
            PdfPCell cell2 = new PdfPCell(new Phrase("FORMATO",FontFactory.getFont("Gill Sans MT", 10, Font.BOLD)));
            PdfPCell cell3 = new PdfPCell(new Phrase("Código: " + "JD-RG-002",FontFactory.getFont("Gill Sans MT", 10, Font.BOLD)));
            PdfPCell cell5 = new PdfPCell(new Phrase("SÍLABO DE MÓDULOS PREGRADO Y POSGRADO \n" +"MODALIDAD VIRTUAL", FontFactory.getFont("Gill Sans MT", 10, Font.BOLD)));
            PdfPCell cell6 = new PdfPCell(new Phrase("Versión: 4", FontFactory.getFont("Gill Sans MT", 10, Font.BOLD)));

            // Configurar celdas
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
            // Ajustar celda 1 para abarcar dos filas
            cell1.setRowspan(2);


            // Agregar celdas a la tabla
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell5);
            table.addCell(cell6);



            // Posicionar la tabla debajo de la imagen
            float tablePosY = positionY - table.getTotalHeight()+20;
            table.writeSelectedRows(0, -1, (pageWidth - table.getTotalWidth()) / 2, tablePosY, writer.getDirectContent());

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}