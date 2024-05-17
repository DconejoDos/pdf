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
        int x  =1;
        for (int i = 0; i < x; i++) {
            Document document = new Document(PageSize.A4, 36, 36, 150, 36); // Establecer márgenes

            try {
                FileOutputStream fos = new FileOutputStream(i+"_formato_silabo.pdf");
                PdfWriter writer = PdfWriter.getInstance(document, fos);

                // Establecer el evento del encabezado
                EncabezadoDos encabezado = new EncabezadoDos();
                writer.setPageEvent(encabezado);

                document.open();

                facultadEscuela(document);

                datosBasicosModulo(document);

                tableUnaColumna(document, "     II.                 DESCRIPCIÓN DEL MODULO", "La calidad y el uso de los medios de transmisión determinal en gran medida la calidad de la comunicación percibida por los usuarios, también presentan una gran influencia en los costos y capacidades de transmisión. Es el medio de transmisión el principal elemento a tener en cuenta en la generación de comunicaciones a nivel físico.\n" +                        "Este espacio brinda las herramientas necesarias para entender la aplicabilidad de los diferentes medios utilizados en el mercado de las comunicaciones, entendiendo los mecanismos que los gobiernan, junto con sus ventajas y desventajas, adicionalmente describe los efectos sobre el espacio debido a la presencia de cargas eléctricas y distribuciones variantes en el tiempo de carga eléctricas (electromagnetismo), al igual que las propiedades físicas emergentes de la dinámica de grupos de carga que definen propiedades eléctricas y magnéticas en un medio de transmisión guiado y son la base del funcionamiento de varios dispositivos como el UTP, FTP, STP, Par telefónico, coaxial, Guías de ondas entre otros\n");

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

        //Modificar tamaño celdas
        float[] columnWidths = {1, 2};
        table.setWidths(columnWidths);


        //agregar celda superior a la tabla
        PdfPCell cell1 =  new PdfPCell(new Phrase("     I.                  DATOS BÁSICOS DEL MODULO", FontFactory.getFont("Gill Sans MT", 8, Font.BOLD, BaseColor.WHITE)));

        //Unificar celdas superiores
        cell1.setColspan(2);
        BaseColor color = new BaseColor(16,52,92);
        cell1.setBackgroundColor(color);
        //Agregar celda uno a la tabla
        table.addCell(cell1);

        PdfPCell[] contenido = creatorCells("Medios de Transmisión","TIC49999","Pregrado", "Ingeniería en Telecomunicaciones", "Programa", "4", "16 semanas");
        for (int i = 0; i < contenido.length; i++) {
            table.addCell(contenido[i]);
        }
        document.add(table);
    }

    public static PdfPCell[] creatorCells(String nombreModulo, String codigoModulo, String cicloFormacion, String programa, String bloqueFormacion, String numeroCreditos, String duracion){



        PdfPCell cell1 = cellA("NOMBRE MODULO");
        PdfPCell cell2 = cellB(nombreModulo);

        PdfPCell cell3 = cellA("CÓDIGO DEL MODULO");
        PdfPCell cell4 = cellB(codigoModulo);

        PdfPCell cell5 = cellA("CICLO DE FORMACIÓN");
        PdfPCell cell6 = cellB(cicloFormacion);

        PdfPCell cell7 = cellA("PROGRAMA");
        PdfPCell cell8 = cellB(programa);

        PdfPCell cell9 = cellA("BLOQUE DE FORMACIÓN");
        PdfPCell cell10 = cellB(bloqueFormacion);

        PdfPCell cell11 = cellA("NÚMERO DE CRÉDITOS");
        PdfPCell cell12 = cellB(numeroCreditos);

        PdfPCell cell13 = cellA("DURACIÓN");
        PdfPCell cell14 = cellB(duracion);

        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell14.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPCell [] contenido = {cell1, cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,cell14};

        return contenido;


    }
    public static PdfPCell cellA(String con){
        PdfPCell cella =  new PdfPCell(new Phrase(con, FontFactory.getFont("Gill Sans MT", 8, Font.BOLD)));
        return cella;
    }
    public static PdfPCell cellB(String con){
        PdfPCell cellb =    new PdfPCell(new Phrase(con, FontFactory.getFont("Gill Sans MT", 9)));
        return cellb;
    }

    public static PdfPTable tableUnaColumna(Document document, String titulo, String contenido) throws DocumentException {
        document.add(new Paragraph("  "));document.add(new Paragraph("  "));

        //Tamaño de la hoja
        float pageWidth = document.getPageSize().getWidth();
        float pageHeight = document.getPageSize().getHeight();
        //creacion de la tabla de una columna
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(80); // Ancho de la tabla (en porcentaje del ancho de la página)
        table.setTotalWidth(pageWidth * 0.8f); // Ancho de la tabla (en puntos)
        table.setLockedWidth(true); // Bloquear el ancho de la tabla
        //creacion celdas
        PdfPCell cell1 =  new PdfPCell(new Phrase(titulo, FontFactory.getFont("Gill Sans MT", 8, Font.BOLD, BaseColor.WHITE)));
        PdfPCell cell2 =  new PdfPCell(new Phrase(contenido, FontFactory.getFont("Gill Sans MT", 9)));
        //cambiar el color de la celda uno
        BaseColor color = new BaseColor(16,52,92);
        cell1.setBackgroundColor(color);
        //Añadir celdas a la tabla
        table.addCell(cell1);
        table.addCell(cell2);
        //agragar la tabla al docu
        document.add(table);
        //retorna la tabla
        return table;


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