package sample.sample;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlReportGeneration {
	public void reportGeneration(List<String> resultString) throws IOException {
		WordComparatorDTO folderCreation = new WordComparatorDTO();
		folderCreation.foldercreation();
		StringBuffer temp = new StringBuffer();
		if (WordComparatorDTO.pictureDifference == true) {
			for (String str : WordComparatorDTO.picpath) {
				temp.append("  <div class=\"w3-row w3-padding-64\">\r\n"
						+ "    <div class=\"w3-twothird w3-container\">\r\n"
						+ "      <h1 class=\"w3-text-teal\">Difference Details</h1>\r\n" + "      <p>"
						+ "Below picture is missing in second documnet" + "</p>\r\n" + "<img src=" + str
						+ "  width=\"300\" height=\"300\">" + "    </div>\r\n" + "  </div>\r\n" + "\r\n");
			}
		}

		for (String str : resultString) {
			temp.append(
					"  <div class=\"w3-row w3-padding-64\">\r\n" + "    <div class=\"w3-twothird w3-container\">\r\n"
							+ "      <h1 class=\"w3-text-teal\">Difference Details</h1>\r\n" + "      <p>" + str
							+ "</p>\r\n" + "    </div>\r\n" + "  </div>\r\n" + "\r\n");
		}
		String report = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<title>W3.CSS Template</title>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"../StyleSheet/W3.css\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"../StyleSheet/W3-theme-black.css\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"../StyleSheet/family=Roboto\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"../StyleSheet/font-awesome.min.css\">\r\n" + "<style>\r\n"
				+ "html,body,h1,h2,h3,h4,h5,h6 {font-family: \"Roboto\", sans-serif;}\r\n" + ".w3-sidebar {\r\n"
				+ "  z-index: 3;\r\n" + "  width: 250px;\r\n" + "  top: 43px;\r\n" + "  bottom: 0;\r\n"
				+ "  height: inherit;\r\n" + "}\r\n" + "</style>\r\n" + "<body>\r\n" + "\r\n" + "\r\n"
				+ "<div class=\"w3-top\">\r\n" + "  <div class=\"w3-bar w3-theme w3-top w3-left-align w3-large\">\r\n"
				+ "    <a class=\"w3-bar-item w3-button w3-right w3-hide-large w3-hover-white w3-large w3-theme-l1\" href=\"javascript:void(0)\" onclick=\"w3_open()\"><i class=\"fa fa-bars\"></i></a>\r\n"
				+ "    <a href=\"#\" class=\"w3-bar-item w3-button w3-theme-l1\">Document compare Completed</a>\r\n"
				+ "  </div>\r\n" + "</div>\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n"
				+ "<div class=\"w3-overlay w3-hide-large\" onclick=\"w3_close()\" style=\"cursor:pointer\" title=\"close side menu\" id=\"myOverlay\"></div>\r\n"
				+ "\r\n" + "\r\n" + "<div class=\"w3-main\" style=\"margin-left:250px\">\r\n" + "\r\n" + temp
				/*
				 * "  <div class=\"w3-row w3-padding-64\">\r\n" +
				 * "    <div class=\"w3-twothird w3-container\">\r\n" +
				 * "      <h1 class=\"w3-text-teal\">Heading</h1>\r\n" +
				 * "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum\r\n"
				 * +
				 * "        dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>\r\n"
				 * + "    </div>\r\n" + "  </div>\r\n" + "\r\n" + "  <div class=\"w3-row\">\r\n"
				 * + "    <div class=\"w3-twothird w3-container\">\r\n" +
				 * "      <h1 class=\"w3-text-teal\">Heading</h1>\r\n" +
				 * "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum\r\n"
				 * +
				 * "        dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>\r\n"
				 * + "    </div>\r\n" + "  </div>\r\n" + "\r\n" +
				 * "  <div class=\"w3-row w3-padding-64\">\r\n" +
				 * "    <div class=\"w3-twothird w3-container\">\r\n" +
				 * "      <h1 class=\"w3-text-teal\">Heading</h1>\r\n" +
				 * "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum\r\n"
				 * +
				 * "        dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>\r\n"
				 * + "    </div>\r\n" + "  </div>\r\n" +
				 */
				+ "\r\n" + "  \r\n" + "\r\n" + "</div>\r\n" + "\r\n" + "<script>\r\n" + "\r\n"
				+ "var mySidebar = document.getElementById(\"mySidebar\");\r\n" + "\r\n" + "\r\n"
				+ "var overlayBg = document.getElementById(\"myOverlay\");\r\n" + "\r\n" + "\r\n"
				+ "function w3_open() {\r\n" + "    if (mySidebar.style.display === 'block') {\r\n"
				+ "        mySidebar.style.display = 'none';\r\n" + "        overlayBg.style.display = \"none\";\r\n"
				+ "    } else {\r\n" + "        mySidebar.style.display = 'block';\r\n"
				+ "        overlayBg.style.display = \"block\";\r\n" + "    }\r\n" + "}\r\n" + "\r\n" + "\r\n"
				+ "function w3_close() {\r\n" + "    mySidebar.style.display = \"none\";\r\n"
				+ "    overlayBg.style.display = \"none\";\r\n" + "}\r\n" + "</script>\r\n" + "\r\n" + "</body>\r\n"
				+ "</html>\r\n" + "";

		System.out.println(report);
		String path = "ResultFolder\\Report" + System.currentTimeMillis() + ".html";
		File file = new File(path);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(report);
		writer.close();
		Desktop.getDesktop().browse(file.toURI());

	}
}
