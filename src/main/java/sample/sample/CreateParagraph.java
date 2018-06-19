package sample.sample;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.namespace.QName;

import org.apache.poi.POIXMLProperties.ExtendedProperties;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.IRunElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

public class CreateParagraph {

   public static void main(String[] args)throws Exception {
/*
      //Blank Document
//      XWPFDocument document = new XWPFDocument(); 
      
      //Write the Document in file system
//      FileOutputStream out = new FileOutputStream(new File("\\\\global.scd.scania.com\\home\\Se\\080\\dmuhbr\\Desktop\\New folder\\test5.docx"));
      FileInputStream fis= new FileInputStream(new File("\\\\global.scd.scania.com\\home\\Se\\080\\dmuhbr\\Desktop\\New folder\\test5.docx"));
//      POIFSFileSystem fs = new POIFSFileSystem(in);
      XWPFDocument document = new XWPFDocument(fis); 
      //create Paragraph
//      XWPFParagraph paragraph = document.createParagraph();
//      System.out.println(document.getAllEmbedds().get(0).getInputStream());
     System.out.println( document .getAllPackagePictures().get(1).getPictureType());
      System.out.println(document.getAllPictures().get(1).getData());
     System.out.println( document.getBodyElements().get(1).getBody());
//      document.get
      
      List<XWPFParagraph> paragraphs = document.getParagraphs();
      System.out.println(document.getStyles().getStyle(paragraphs.get(3).getStyle()));
      System.out.println(paragraphs.size());
      System.out.println(paragraphs.get(1).getParagraphText());
      System.out.println(paragraphs.get(1).getFontAlignment());
      XWPFRun run = paragraphs.get(1).createRun();
      System.out.println(run.getColor());
      System.out.println(run.getFontName());
      System.out.println(run.getFontSize());
//      System.out.println("Total no of paragraph "+paragraphs.size());
//		for (XWPFParagraph para : paragraphs) {
//			System.out.println(para.getText());
//		}
		fis.close();
      
      XWPFRun run1 = paragraphs.get(1).createRun();
      System.out.println(document.getStyles().getDefaultRunStyle().getFontSize());
//      List<IRunElement> run1 = paragraph1.get(1).getIRuns();
//      System.out.println(run1);
//      System.out.println(run1.getColor());
      
//      run.setText("At tutorialspoint.com, we strive hard to " +
//         "provide quality tutorials for self-learning " +
//         "purpose in the domains of Academics, Information " +
//         "Technology, Management and Computer Programming"+
//         "Languages.");
//			
//      document.write(out);
//      out.close();
      System.out.println("createparagraph.docx written successfully");
   }*/
	   
	   
	   File file = new File("\\\\global.scd.scania.com\\home\\Se\\080\\dmuhbr\\Desktop\\New folder\\test8.docx");
	   FileInputStream fis = new FileInputStream(file.getAbsolutePath());

	   XWPFDocument document = new XWPFDocument(fis);
	   List<XWPFParagraph> paragraphs = document.getParagraphs();

	   System.out.println("Total no of paragraph in Docx : "+paragraphs.size());
	   for (XWPFParagraph para : paragraphs) {
	       XWPFStyle style = document.getStyles().getStyle(para.getStyleID());
	       System.out.println(para.getText());
	       System.out.println(style);
	       int pos = 0;
	       for (XWPFRun run : para.getRuns()) {
	         System.out.println("Current run IsBold : " + run.isBold());
	         System.out.println("Current run IsItalic : " + run.isItalic());
	         System.out.println("Current Font Size : " + run.getFontSize());
	         System.out.println("Current Font Name : " + run.getFontName());
	       }
	     }
	   
//	   FileInputStream fs = new FileInputStream(file.getAbsolutePath());
	   //FileInputStream fs=new FileInputStream(src);
	    //create office word 2007+ document object to wrap the word file
//	    XWPFDocument doc1x=new XWPFDocument(fis);
	    //get all images from the document and store them in the list piclist
	    List<XWPFPictureData> piclist=document.getAllPictures();
	    //traverse through the list and write each image to a file
	    Iterator<XWPFPictureData> iterator=piclist.iterator();
	    int i=0;
	    while(iterator.hasNext()){
	     XWPFPictureData pic=iterator.next();
	     System.out.println(pic.getData());
	     byte[] bytepic=pic.getData();
	     BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytepic));
	            ImageIO.write(imag, "jpg", new File("\\\\global.scd.scania.com\\home\\Se\\080\\dmuhbr\\Desktop\\New folder\\imagefromword"+i+".jpg"));
	            i++;
	            System.out.println("Image is created");
	    }
	    ExtendedProperties ep = document.getProperties().getExtendedProperties();
	    int numberOfLines = ep.getUnderlyingProperties().getLines();
	    System.out.println(numberOfLines);
	    
	    

//XWPFDocument doc = new XWPFDocument(new FileInputStream(fileName));

    List<XWPFTable> table = document.getTables(); 

    for (XWPFTable xwpfTable : table) { 
        List<XWPFTableRow> row = xwpfTable.getRows();
        for (XWPFTableRow xwpfTableRow : row) { 
            List<XWPFTableCell> cell = xwpfTableRow.getTableCells();
            for (XWPFTableCell xwpfTableCell : cell) {
                if(xwpfTableCell != null){
                 List<XWPFTable> itable = xwpfTableCell.getTables(); 
                    if(itable.size()!=0){ 
                        for (XWPFTable xwpfiTable : itable) { 
                            List<XWPFTableRow> irow = xwpfiTable.getRows(); 
                            for (XWPFTableRow xwpfiTableRow : irow) { 
                                List<XWPFTableCell> icell = xwpfiTableRow.getTableCells(); 
                                for (XWPFTableCell xwpfiTableCell : icell) { 
                                    if(xwpfiTableCell!=null){ 
                                    	System.out.println("inside the cell"+xwpfiTableCell.getText());
                                    } 
                                } 
                            } 
                        } 
                    } 
                } 
            }
        } 
    }
    
	    
    CTBody ctbody = document.getDocument().getBody();

    XmlCursor xmlcursor = ctbody.newCursor();

    QName qnameTbl = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tbl", "w");
    QName qnameFallback = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "Fallback", "mc");

    List<CTTbl> allCTTbls = new ArrayList<CTTbl>();

    while (xmlcursor.hasNextToken()) {
     XmlCursor.TokenType tokentype = xmlcursor.toNextToken();
     if (tokentype.isStart()) {
      if (qnameTbl.equals(xmlcursor.getName())) {
       if (xmlcursor.getObject() instanceof CTTbl) {
        allCTTbls.add((CTTbl)xmlcursor.getObject());
       } else if (xmlcursor.getObject() instanceof XmlAnyTypeImpl) {
        allCTTbls.add(CTTbl.Factory.parse(xmlcursor.getObject().toString()));
       }
      } else if (qnameFallback.equals(xmlcursor.getName())) {
       xmlcursor.toEndToken();
      }
     } 
    }

    for (CTTbl cTTbl : allCTTbls) {
     StringBuffer tableHTML = new StringBuffer();
     tableHTML.append("<table>\n");
     for (CTRow cTRow : cTTbl.getTrList()) {
      tableHTML.append(" <tr>\n");
      for (CTTc cTTc : cTRow.getTcList()) {
       tableHTML.append("  <td>");
       for (CTP cTP : cTTc.getPList()) {
        for (CTR cTR : cTP.getRList()) {
         for (CTText cTText : cTR.getTList()) {
          tableHTML.append(cTText.getStringValue());
          System.out.println();
         }
        }
       }
       tableHTML.append("</td>");
      }
      tableHTML.append("\n </tr>\n");
     }
     tableHTML.append("</table>");

     System.out.println(tableHTML);

    } 
    
    
    List<XWPFTableRow> xWPFTableRow = new ArrayList<XWPFTableRow>();
    List<XWPFTableCell> xWPFTablecell = new ArrayList<XWPFTableCell>();
    List<XWPFTableRow> zeile = new ArrayList<XWPFTableRow>();
	List<XWPFTable> table1 = document.getTables();
	for(XWPFTable table2:table1) {
		zeile.addAll(table2.getRows());
	}
	for(int i1 =0;i< zeile.size();i++) {		
		xWPFTablecell =zeile.get(i1).getTableCells();
	}
	for(XWPFTableCell  cell :xWPFTablecell ) {
//		System.out.println(cell.getText());
//		System.out.println(cell.getColor());
//		System.out.println(cell.getTextRecursively());
//		System.out.println(cell.getParagraphs().get(0));
		List<XWPFParagraph>paragraphs1 =cell.getParagraphs();
		for (XWPFParagraph para : paragraphs1) {
		       XWPFStyle style = document.getStyles().getStyle(para.getStyleID());
		       System.out.println(para.getText());
		       System.out.println(style);
		       int pos = 0;
		       for (XWPFRun run : para.getRuns()) {
		         System.out.println("Current run IsBold : " + run.isBold());
		         System.out.println("Current run IsItalic : " + run.isItalic());
		         System.out.println("Current Font Size : " + run.getFontSize());
		         System.out.println("Current Font Name : " + run.getFontName());
		       }
		
//		document.getStyles().getStyle(para.getStyleID());
	}
	
	}
	   
//   List<XWPFTableRow> zeile = table1.getRows();
   System.out.println("Inside the row");
//    xWPFTableRow = document.getTables().get(0).getRow(1);
//    System.out.println(xWPFTableRow.getCell(1).getColor());
//    System.out.println(xWPFTableRow.getCell(1).getText());
//    System.out.println(xWPFTableRow.getCell(1).getBodyElements().get(1).getElementType());
	     fis.close();
   }  
   
     
}

// to get row data
/*private void adText2Table(XWPFDocument doc, String text, int row, int cell, int tableIdx) {
	           XWPFTableRow xWPFTableRow;
	           XWPFTable table = doc.getTables().get(tableIdx);
	           List<XWPFTableRow> zeile = table.getRows();
	    
	           if (zeile.size() >= row + 1) {
	               xWPFTableRow = zeile.get(row);
	           } else {
	               xWPFTableRow = doc.getTables().get(tableIdx).createRow();
	           }
	    
	           xWPFTableRow.getCell(cell).setText(text);
	       }
 
*/

/*
//    public static string TextFromWord(string filename)
{
    StringBuilder textBuilder = new StringBuilder();
    using (WordprocessingDocument wDoc = WordprocessingDocument.Open(filename, false))
    {
        var parts = wDoc.MainDocumentPart.Document.Descendants().FirstOrDefault();
        if (parts != null)
        {
            foreach (var node in parts.ChildElements)
            {
                if(node is Paragraph)
                {
                    ProcessParagraph((Paragraph)node, textBuilder);
                    textBuilder.AppendLine("");
                }

                if (node is Table)
                {
                    ProcessTable((Table)node, textBuilder);
                }
            }
        }
    }
    return textBuilder.ToString();
}

private static void ProcessTable(Table node, StringBuilder textBuilder)
{
    foreach (var row in node.Descendants<TableRow>())
    {
        textBuilder.Append("| ");
        foreach (var cell in row.Descendants<TableCell>())
        {
            foreach (var para in cell.Descendants<Paragraph>())
            {
                ProcessParagraph(para, textBuilder);
            }
            textBuilder.Append(" | ");
        }
        textBuilder.AppendLine("");
    }
}

private static void ProcessParagraph(Paragraph node, StringBuilder textBuilder)
{
    foreach(var text in node.Descendants<Text>())
    {
        textBuilder.Append(text.InnerText);
    }
}
*/