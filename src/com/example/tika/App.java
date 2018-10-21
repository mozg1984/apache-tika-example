package com.example.tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.PropertyConfigurator;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class App {
	private static final String TEST_FILE = "resources/pdf-test.pdf";

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		
		try {
			parsePDF();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void parsePDF() throws Exception {
		InputStream input = new FileInputStream(new File(TEST_FILE));
		ContentHandler textHandler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		ParseContext context = new ParseContext();

		// parsing the document using PDF parser
		PDFParser parser = new PDFParser();
		parser.parse(input, textHandler, metadata, context);
		
		// getting the content of the document
		System.out.println("Contents of the PDF :" + textHandler.toString());

		// getting metadata of the document
		System.out.println("Metadata of the PDF:");
		String[] metadataNames = metadata.names();

		for (String name : metadataNames) {
			System.out.println(name + " : " + metadata.get(name));
		}
		
		input.close();
	}
}