package sample.sample;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class HtmlReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HtmlReport report = new HtmlReport();
		try {
			report.htmlreport();
			File htmlFile = new File("myfile.html");
			Desktop.getDesktop().browse(htmlFile.toURI());

		} catch (TransformerConfigurationException | IOException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void htmlreport() throws IOException, TransformerConfigurationException, SAXException {
		String encoding = "UTF-8";
		FileOutputStream fos = new FileOutputStream("myfile.html");
		OutputStreamWriter writer = new OutputStreamWriter(fos, encoding);
		StreamResult streamResult = new StreamResult(writer);

		SAXTransformerFactory saxFactory = (SAXTransformerFactory) TransformerFactory.newInstance();
		TransformerHandler tHandler = saxFactory.newTransformerHandler();
		tHandler.setResult(streamResult);

		Transformer transformer = tHandler.getTransformer();
		transformer.setOutputProperty(OutputKeys.METHOD, "html");
		transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		writer.write("<!DOCTYPE html>\n");
		writer.flush();
		tHandler.startDocument();
		tHandler.startElement("", "", "html", new AttributesImpl());
		tHandler.startElement("", "", "head", new AttributesImpl());
		tHandler.startElement("", "", "title", new AttributesImpl());
		tHandler.characters("Hello".toCharArray(), 0, 5);
		tHandler.endElement("", "", "title");
		tHandler.endElement("", "", "head");
		tHandler.startElement("", "", "body", new AttributesImpl());
		tHandler.startElement("", "", "p", new AttributesImpl());

		tHandler.characters("5 > 3".toCharArray(), 0, 5); // note '>' character
		tHandler.characters("Hello How r u".toCharArray(), 0, 12); // note '>' character
		tHandler.startElement("", "", "a href=\"http://youtube.com/howtoprogramwithjava\"",
				new AttributesImpl());
		String msgbody = "This is a reminder mail";

		tHandler.characters(msgbody.toCharArray(), 0, msgbody.length()); // note '>' character
		tHandler.endElement("", "", "a");

		tHandler.endElement("", "", "p");
				

		tHandler.endElement("", "", "body");
		tHandler.endElement("", "", "html");
		tHandler.endDocument();
		writer.close();
	}
}
