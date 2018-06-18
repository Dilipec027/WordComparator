/*************************************************************
Class Name: CompareWord
Purpose:Word Comparator
Methods: Compare
Owner: Dilip Kumar Muniraju
Created Date: 15-06-2018
Last updated Date: 15-06-2018
 ***********************************************************/
package sample.sample;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class CompareWord {

	public List<String> compareWord(String location1, String location2) throws Exception {
		List<String> result = new LinkedList<String>();
		XWPFDocument docx = new XWPFDocument(new FileInputStream(location1));
		XWPFDocument docx1 = new XWPFDocument(new FileInputStream(location2));
		// using XWPFWordExtractor Class
		XWPFWordExtractor we = new XWPFWordExtractor(docx);
		String temp = we.getText();
		we.close();

		// using XWPFWordExtractor Class
		XWPFWordExtractor we1 = new XWPFWordExtractor(docx1);
		String temp1 = we1.getText();		
		if (temp.equalsIgnoreCase(temp1))
			System.out.println("Both are Equal");
		we1.close();
		String lines[] = temp.split("\\r?\\n");
		String lines1[] = temp1.split("\\r?\\n");

		if (!(lines.length == lines1.length)) {
			System.out.println("Documnets are  same");
			result.add("Lines are not equal ");
			return result;
		}
		
		int tempint = 0;
		for (int i = 0; i < lines.length; i++) {
			if (!(lines[i].equalsIgnoreCase(lines1[i]))) {
				tempint = 1;
				System.out.println("Lines are not equal documnet1 text" + lines[i]);
				System.out.println("Lines are not equal document2 text" + lines1[i]);
				String eol = System.getProperty("line.separator");
				System.out.println("Line Number" + i + ":Mismatch," + eol + "line in first doc:  " + lines[i] + eol
						+ " ,line in second doc:" + lines1[i]);
				result.add("Line Number" + i + ":Mismatch," + eol + "line in first doc:  " + lines[i] + eol
						+ " ,line in second doc:" + lines1[i] + eol);
			}
		}
		if (tempint == 0) {
			result.add("Document is same by text");
			return result;
		}
		return result;
	}

	public String doc1Text(String location1) throws Exception {
		XWPFDocument docx = new XWPFDocument(new FileInputStream(location1));
		XWPFWordExtractor we = new XWPFWordExtractor(docx);
		String temp = we.getText();
		we.close();
		return temp;
	}

	public String doc2Text(String location2) throws Exception {
		XWPFDocument docx = new XWPFDocument(new FileInputStream(location2));
		XWPFWordExtractor we = new XWPFWordExtractor(docx);
		String temp = we.getText();
		we.close();
		return temp;
	}
}