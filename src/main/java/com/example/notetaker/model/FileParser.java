package com.example.notetaker.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Handles the conversion of uploaded learning material into plain text for the AI
 * Uses pdfbox and poi to parse .txt .pdf and .docx
 */
public class FileParser {

    public static String extractText(File file) throws IOException {

        // Make file names lowercase so file extensions are read consistently
        String filename = file.getName().toLowerCase();

        // If it's a .txt, use
        if (filename.endsWith(".txt")) {
            return parseTxt(file);
        }
        // If it's a .pdf file, use PDFBox method
        else if (filename.endsWith(".pdf")) {
            return parsePDF(file);
        }

        // If it's a word document, use Apache POI method
        else if (filename.endsWith(".docx")) {
            return parseDocx(file);
        }
        else {
            throw new UnsupportedOperationException("Unsupported file type: " + filename);
        }
    }

    /**
     * Reads a plain .txt file and returns content
     * @param file This is the .txt file to read
     * @return The content of the file as text
     * @throws IOException When the file can't be read (not accepted file type)
     */
    private static String parseTxt(File file) throws IOException {
        // Read all text content from the file and return it as
        // a single string for the AI to read
        return Files.readString(file.toPath());
    }

    /**
     * Parses PDF file and returns plain text content - PDFBox
     * @param file The .pdf file to parse
     * @return Extracted text from the PDF
     * @throws IOException When file can't be read
     */
    // Using older version of pdfbox (2.0.29) for compatibility
    // this method can apparently cause memory issues down the line
    private static String parsePDF(File file) throws IOException {

        // Open the PDF from given file
        try (PDDocument document = PDDocument.load(file)) {

            // PDFTextStripper reads text content from all PDF pages
            PDFTextStripper stripper = new PDFTextStripper();

            // Return extracted plaintext
            return stripper.getText(document);
        }
    }

    /**
     * Parses word document and returns plaintext - Apache POI
     * @param file The .docx to parse
     * @return Extracted text from the .docx file
     * @throws IOException When file can't be read
     */
    private static String parseDocx(File file) throws IOException {
        // Open a FileInputStream to read the .docx file
        try (FileInputStream fis = new FileInputStream(file);

             // Creates a XWPFDocument from the opened file stream
            XWPFDocument document = new XWPFDocument(fis)) {

            // Use XWPFWordExtractor function to extract the document's text content
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);

            // Return extracted plaintext
            return extractor.getText();
        }
    }
}
