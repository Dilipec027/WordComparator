//package sample.sample;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import org.apache.tika.Tika;
//import org.apache.tika.exception.TikaException;
//import org.apache.tika.metadata.Metadata;
//import org.apache.tika.parser.AutoDetectParser;
//import org.apache.tika.parser.ParseContext;
//import org.apache.tika.parser.Parser;
//import org.apache.tika.sax.BodyContentHandler;
//
//import org.xml.sax.SAXException;
//
//public class ParserExtraction {
//	
//   public static void main(final String[] args) throws IOException,SAXException, TikaException {
//
//	   //Assume sample.txt is in your current directory
//	      File file = new File("\\\\global.scd.scania.com\\home\\Se\\080\\dmuhbr\\Desktop\\New folder\\test6.docx");
//	      
//	 //Instantiating Tika facade class
//	      Tika tika = new Tika();
//	      String filecontent = tika.parseToString(file);
//	      System.out.println("Extracted Content: " + filecontent);
//    
//      
//      //parse method parameters
//      Parser parser = new AutoDetectParser();
//      BodyContentHandler handler = new BodyContentHandler();
//      Metadata metadata = new Metadata();
//      FileInputStream inputstream = new FileInputStream(file);
//      ParseContext context = new ParseContext();
//      
//      //parsing the file
//      parser.parse(inputstream, handler, metadata, context);
//      System.out.println("File content : " + handler.toString());
//   }
//}