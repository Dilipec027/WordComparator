package sample.sample;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.util.List;
//
//import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//import org.apache.poi.xwpf.usermodel.XWPFRun;
//

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class App {
//
//	public static void main(String[] args) throws Exception {
//
//		// Blank Document
//		// XWPFDocument document = new XWPFDocument();
//
//		// Write the Document in file system
//		// FileOutputStream out = new FileOutputStream( new
//		// File("createdocument.docx"));
//
//		// create Paragraph
//		// XWPFParagraph paragraph = document.createParagraph();
//		// XWPFRun run = paragraph.createRun();
//		// run.setText("At tutorialspoint.com, we strive hard to " +
//		// "provide quality tutorials for self-learning " +
//		// "purpose in the domains of Academics, Information " +
//		// "Technology, Management and Computer Programming"+
//		// "Languages.");
//		//
//		// document.write(out);
//		// out.close();
//		// System.out.println("createdocument.docx written successully");
//
//		// C:\\Users\\dmuhbr\\eclipse-workspace\\sample\\createdocument.docx
//		XWPFDocument docx = new XWPFDocument(new FileInputStream(
//				"\\\\global.scd.scania.com\\home\\Se\\080\\dmuhbr\\Desktop\\New folder\\test5.docx"));
//		XWPFDocument docx1 = new XWPFDocument(new FileInputStream(
//				"\\\\global.scd.scania.com\\home\\Se\\080\\dmuhbr\\Desktop\\New folder\\test6.docx"));
//
//		// using XWPFWordExtractor Class
//		XWPFWordExtractor we = new XWPFWordExtractor(docx);
//		String temp = we.getText();
//		System.out.println(we.getText());
//		// System.out.println(we.getDocument());
//		we.close();
//
//		// //using XWPFWordExtractor Class
//		XWPFWordExtractor we1 = new XWPFWordExtractor(docx1);
//		String temp1 = we1.getText();
//		if (temp.equalsIgnoreCase(temp1))
//			System.out.println("Both are Equal");
////		System.out.println(we1.getText());
//		we1.close();
//
//		
//		String lines[] = temp.split("\\r?\\n");
//		String lines1[] = temp1.split("\\r?\\n");
////		List<XWPFParagraph> paragraphs = docx.getParagraphs();
//
//		// List<XWPFParagraph> paragraphs1 = docx1.getParagraphs();
////		for (XWPFParagraph paragraph : paragraphs) {
////			for (String  text : lines) {
//		if(!(lines.length==lines1.length))
//			System.out.println("Documnets are  same");
//		for(int i =0;i<lines.length;i++) {
////				System.out.println("Lines"+lines[i]);
//				if(!(lines[i].equalsIgnoreCase(lines1[i]))) {
//					System.out.println("Lines are not equal documnet1 text"+lines[i]);
//					System.out.println("Lines are not equal document2 text"+lines1[i]);
//				}
//			// System.out.println("Total no of paragraph in Docx : "+paragraphs.size());
//			// for(int i=0; i<paragraphs.size();i++) {
//			// if(!paragraphs.get(i).getText().equalsIgnoreCase(paragraphs1.get(i).getText()))
//			// {
//			// System.out.println("Text in this paragraph: " + paragraphs.get(i).getText());
//			// break;
//			// }
//			// System.out.println("Text in this paragraph: " + paragraph.getText());
//			// System.out.println("foot"+paragraph.getFootnoteText());
//			// System.out.println("picturetext"+paragraph.getPictureText());
//			// System.out.println("body"+paragraph.getBody());
//			// System.out.println("firstline"+paragraph.getIndentationFirstLine());
//			// System.out.println("hanglingline"+paragraph.getIndentationHanging());
//			// System.out.println("paragraphtext"+paragraph.getParagraphText());
//
//		}
//
//		// XWPFDocument docx1 = new XWPFDocument(new
//		// FileInputStream("\\\\global.scd.scania.com\\home\\Se\\080\\dmuhbr\\Desktop\\New
//		// folder\\test6.docx"));
//		//
//
//	}
	
	
}
//
//
//// import java.io.FileInputStream;
//// import java.util.List;
//// import org.apache.poi.openxml4j.opc.OPCPackage;
//// import org.apache.poi.xwpf.usermodel.XWPFDocument;
//// import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//// public class App {
//// public static void main(String[] args) {
//// try {
//// FileInputStream fis = new
//// FileInputStream("\\\\global.scd.scania.com\\home\\Se\\080\\dmuhbr\\Desktop\\New
//// folder\\test6.docx");
//// XWPFDocument xdoc=new XWPFDocument(OPCPackage.open(fis));
//// List<XWPFParagraph> paragraphList = xdoc.getParagraphs();
//// for (XWPFParagraph paragraph: paragraphList){
//// System.out.println(paragraph.getText());
//// }
//// } catch(Exception ex) {
//// ex.printStackTrace();
//// }
//// }
//// }
////
//// import java.io.File;
//// import java.io.FileInputStream;
//// import java.util.List;
//// import org.apache.poi.xwpf.usermodel.XWPFDocument;
//// import org.apache.poi.xwpf.usermodel.XWPFParagraph;
//// import java.io.File;
//// import java.io.FileInputStream;
//// import java.util.Iterator;
//// import java.util.List;
//// import org.apache.poi.hwpf.HWPFDocument;
//// import org.apache.poi.hwpf.extractor.WordExtractor;
//// import org.apache.poi.xwpf.usermodel.XWPFDocument;
//// import org.apache.poi.xwpf.usermodel.XWPFParagraph;
////
///// **
//// * Hello world!
//// *
//// */
//// public class App
//// {
//// public static void main( String[] args )
//// {
//// System.out.println( "Hello World!" );
//// App app = new App();
//// app.readDocxFile("\\\global.scd.scania.com\\home\\Se\\080\\dmuhbr\\Desktop\\New
//// folder\\test6.docx\");
//// }
////
////
////
////
//// public void readDocxFile(String fileName) {
//// try {
//// File file = new File(fileName);
//// FileInputStream fis = new FileInputStream(file.getAbsolutePath());
//// XWPFDocument document = new XWPFDocument(fis);
////// XWPFDocument document = new XWPFDocument(fis);
//// List<XWPFParagraph> paragraphs = document.getParagraphs();
//// for(int i=0;i<paragraphs.size();i++){
//// System.out.println(paragraphs.get(i).getParagraphText());
//// }
//// fis.close();
//// } catch (Exception e) {
//// e.printStackTrace();
//// }
//// }
////
//// }