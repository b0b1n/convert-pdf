package org.skious;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static String resourcePath = new File("").getAbsolutePath().concat("\\src\\main\\resources\\");
    //public static String myPdf = "Sohaib Skious" ;
    public static void main( String[] args )
    {
        String myPdf = "Sohaib-Skious";
        // Create a file in local directory
        File f = new File(resourcePath+myPdf+".pdf");

        // Create a file input stream
        // on specified path with the created file
        try {

            //FIRST METHOD :::
            //***********************
            // Create a content handler
            BodyContentHandler contenthandler = new BodyContentHandler();
            FileInputStream fstream = new FileInputStream(f);
            // Create an object of type Metadata to use
            Metadata data = new Metadata();
            // Create a context parser for the pdf document
            ParseContext context = new ParseContext();
            // PDF document can be parsed using the PDFparser
            // class
            PDFParser pdfparser = new PDFParser();
            // Method parse invoked on PDFParser class
            pdfparser.parse(fstream, contenthandler, data, context);

            //*/

            // SECOND METHOD
            PDDocument document = PDDocument.load(new File(resourcePath+myPdf+".pdf"));
            // Create a PDFTextStripper object
            PDFTextStripper textStripper = new PDFTextStripper();
            String content = textStripper.getText(document);

            // Printing the contents of the pdf document
            // using toString() method in java
            FileWriter writer = new FileWriter(resourcePath + myPdf + ".txt");

            //if you use the First method
            writer.write(contenthandler.toString());

            //writer.write(content);
            writer.close();

        } catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TikaException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
