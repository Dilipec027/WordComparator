package sample.sample;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlReportGenerationNew {

	public static void main(String[] args) throws IOException {
		HtmlReportGenerationNew html = new HtmlReportGenerationNew();
		List<String> str = new ArrayList<String>();
		html.reportGeneration(str);
	}

	public void reportGeneration(List<String> resultString) throws IOException {
		WordComparatorDTONew1 folderCreation = new WordComparatorDTONew1();
		folderCreation.foldercreation();
		StringBuffer temp = new StringBuffer();
		StringBuffer temp1 = new StringBuffer();
		if (WordComparatorDTONew1.pictureDifference == true) {
			for (String str : WordComparatorDTONew1.picpath) {
				temp.append(" <h1 class=\"w3-text-teal\">Difference Details</h1>\r\n" + " <p>"
						+ "Below picture is missing in second documnet" + "</p>\r\n" + "<img src=" + str
						+ "  width=\"300\" height=\"300\">" + "\r\n");
			}
		}

		for (int i = 0; i < WordComparatorDTONew1.mismatch.size(); i++) {
			temp1.append("<tr> <td>" + WordComparatorDTONew1.mismatch.get(i) + "</td> <td>"
					+ WordComparatorDTONew1.mismatchFirstDoc.get(i) + "</td><td>"
					+ WordComparatorDTONew1.mismatchSecondDoc.get(i) + "</td> </tr>");
		}

		String report = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<style>\r\n" + "#p01 {\r\n"
				+ "    color: black;\r\n" + "    background-color: green;\r\n" + "    \r\n" + "}\r\n" + "\r\n"
				+ "table {\r\n" + "    font-family: arial, sans-serif;\r\n" + "    border-collapse: collapse;\r\n"
				+ "    width: 100%;\r\n" + "}\r\n" + "\r\n" + "td, th {\r\n" + "    border: 1px solid #dddddd;\r\n"
				+ "    text-align: left;\r\n" + "    padding: 8px;\r\n" + "}\r\n" + "\r\n" + "th{\r\n"
				+ "background-color: orange;\r\n" + "}\r\n" + "\r\n" + "\r\n" + "tr:nth-child(even) {\r\n"
				+ "    background-color: #dddddd;\r\n" + "}\r\n" + "</style>\r\n" + "</head>\r\n" + "<body>\r\n"
				+ "\r\n" + "<h2 id =\"p01\"  align =\"Center\">Document Comparison Completed</h2>\r\n" + "\r\n" + temp
				+ "<table>\r\n" + "  <tr>\r\n" + "    <th>Mismatch Details</th>\r\n"
				+ "    <th>Content in first documnet</th>\r\n" + "    <th>Content in second documnet</th>\r\n" + temp1
				// + " </tr>\r\n" + " <tr>\r\n" + " <td>Alfreds Futterkiste</td>\r\n"
				// + " <td>Maria Anders</td>\r\n" + " <td>Germany</td>\r\n" + " </tr>\r\n" + "
				// \r\n"
				+ "</table>\r\n" + "\r\n" + "</body>\r\n" + "</html>\r\n" + "";
		System.out.println(report);
		String path = "ResultFolder\\Report" + System.currentTimeMillis() + ".html";
		File file = new File(path);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(report);
		writer.close();
		Desktop.getDesktop().browse(file.toURI());

	}
}
