package sample.sample;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.namespace.QName;

import org.apache.poi.POIXMLProperties.ExtendedProperties;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.ICell;
import org.apache.poi.xwpf.usermodel.IRunElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class ExtractDatafromTable {

	private XWPFDocument onedocument;
	private XWPFDocument otherdocument;
	List<XWPFParagraph> otherparagraphs;
	List<XWPFParagraph> oneparagraphs;
	String eol = System.getProperty("line.separator");
	List<String> mismatch = new ArrayList<String>();

	// public static void main(String[] args) throws Exception {
	// ExtractDatafromTable test = new ExtractDatafromTable();
	// test.wordCompare();
	// }

	public List<String> wordCompare(String doclocation1, String doclocation2) {

		try {
			File onefile = new File(doclocation1);
			FileInputStream onefis = new FileInputStream(onefile.getAbsolutePath());

			File otherfile = new File(doclocation2);
			FileInputStream otherfis = new FileInputStream(otherfile.getAbsolutePath());

			onedocument = new XWPFDocument(onefis);
			otherdocument = new XWPFDocument(otherfis);

			oneparagraphs = onedocument.getParagraphs();
			otherparagraphs = otherdocument.getParagraphs();

			if (!(paragrpahSizeCompare().equalsIgnoreCase("Equal"))) {
				mismatch.add("Pargraphs are not equal,Cant be compared");
				return mismatch;
			}
			ParagraphContentCompare();
			picComparator();

			if (!(tableComparator().equalsIgnoreCase("Equal"))) {
				mismatch.add("Tables are not equal,Cant be compared");
				return mismatch;
			}

			System.out.println("Mismatch are" + eol + mismatch);

			onefis.close();
			otherfis.close();

			if (mismatch.isEmpty()) {
				mismatch.add("Documents are equal");
				return mismatch;
			}
		} catch (java.lang.IndexOutOfBoundsException e) {
			mismatch.add("Documents paragrapahs or tables not matched");
			e.printStackTrace();
			return mismatch;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			mismatch.add("File not found");
			e.printStackTrace();
			return mismatch;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			mismatch.add("I/O exception");
			e.printStackTrace();
			return mismatch;
		}

		return mismatch;

	}

	public String paragrpahSizeCompare() {
		if (oneparagraphs.size() != otherparagraphs.size()) {
			mismatch.add("Paragraphs size is not equal" + eol + "Paragrpahs in first doc:" + oneparagraphs.size() + eol
					+ "Paragraphs in second doc:" + otherparagraphs.size() + eol);
			return "Paragraphs are not equal";
		}
		return "Equal";
	}

	public void ParagraphContentCompare() {
		System.out.println("Total no of paragraph in first Docx : " + oneparagraphs.size());
		System.out.println("Total no of paragraph in seond Docx : " + otherparagraphs.size());
		for (int i = 0; i < oneparagraphs.size(); i++) {
			XWPFStyle onestyle = onedocument.getStyles().getStyle(oneparagraphs.get(i).getStyleID());
			XWPFStyle otherstyle = otherdocument.getStyles().getStyle(otherparagraphs.get(i).getStyleID());
			System.out.println(oneparagraphs.get(i).getText());
			System.out.println(otherparagraphs.get(i).getText());

			if (!oneparagraphs.get(i).getText().equalsIgnoreCase(otherparagraphs.get(i).getText())) {
				mismatch.add("Paragraph is not equal" + eol + "Paragrpah in First doc:" + oneparagraphs.get(i).getText()
						+ eol + "Paragraph in Seond Doc :" + otherparagraphs.get(i).getText() + eol);
			}

			System.out.println("paragrpahstyle:" + onestyle);
			System.out.println("paragrpahstyle:" + otherstyle);

			if (onestyle != otherstyle) {

				mismatch.add("Style is not equal in the paragrpah" + eol + "style in First doc:" + onestyle + eol
						+ "style in Seond Doc :" + otherstyle + eol + "Paragrpah text:"
						+ otherparagraphs.get(i).getText() + eol);
			}

			System.out.println("Number of Line for the current Paragrpahs:" + oneparagraphs.get(i).getRuns().size());
			System.out.println("Number of Line for the current Paragrpahs:" + otherparagraphs.get(i).getRuns().size());

			if (oneparagraphs.get(i).getRuns().size() != otherparagraphs.get(i).getRuns().size()) {

				mismatch.add("Paragraph lines is not equal" + eol + "Paragrpahlines in first doc:"
						+ oneparagraphs.get(i).getRuns().size() + eol + "Paragraphlines in second doc:"
						+ otherparagraphs.get(i).getRuns().size() + eol + "Paragrpah text in first doc:" + eol
						+ oneparagraphs.get(i).getText() + eol + "Paragraph text in second doc:" + eol
						+ otherparagraphs.get(i).getText() + eol + eol
						+ "Mismatch migh be due to some text is bold/Italic/Fontsizemismatch/FontNamemismatch/Underlinedmismtach"
						+ eol+eol);

			} else {

				List<XWPFRun> onerun = oneparagraphs.get(i).getRuns();
				List<XWPFRun> otherrun = otherparagraphs.get(i).getRuns();

				int pos = 0;
				int temp = otherrun.size();
				if (onerun.size() < otherrun.size())
					temp = onerun.size(); // this will avoid the array index out of boundary, when if else condition is
											// removed
				for (int j = 0; j < temp; j++) {
					pos = 1 + pos;
					System.out.println("Paragraph Line number in first doc:" + pos);
					System.out.println("Paragraph Line number in second doc:" + pos);
					System.out.println("Current run IsBold : " + onerun.get(j).isBold());
					System.out.println("Current run IsBold : " + otherrun.get(j).isBold());
					System.out.println("Current run IsItalic : " + onerun.get(j).isItalic());
					System.out.println("Current run IsItalic : " + otherrun.get(j).isItalic());
					System.out.println("Current Font Size : " + onerun.get(j).getFontSize());
					System.out.println("Current Font Size : " + otherrun.get(j).getFontSize());
					System.out.println("Current Font Name : " + onerun.get(j).getFontName());
					System.out.println("Current Font Name : " + otherrun.get(j).getFontName());
					System.out.println("Current text underline:" + onerun.get(j).getUnderline());
					System.out.println("Current text underline:" + otherrun.get(j).getUnderline());

					if (onerun.get(j).isBold() != otherrun.get(j).isBold()) {

						mismatch.add("Paragraph Line is not Bold in both document" + eol
								+ "Paragrpah is Bold in first doc :" + onerun.get(j).isBold() + eol
								+ "Paragraph is Bold in second doc:" + otherrun.get(j).isBold() + eol
								+ "Paragrpah text:" + otherparagraphs.get(i).getText() + eol + "Line number mismtached"
								+ pos + eol);

					}

					if (onerun.get(j).isItalic() != otherrun.get(j).isItalic()) {

						mismatch.add("Paragraph Line is not Italic in both document" + eol
								+ "Paragrpah is italic in first doc:" + onerun.get(j).isItalic() + eol
								+ "Paragraph is italic in second doc:" + otherrun.get(j).isItalic() + eol
								+ "Paragrpah text:" + otherparagraphs.get(i).getText() + eol + "Line number mismtached"
								+ pos + eol);

					}
					if (onerun.get(j).getFontSize() != otherrun.get(j).getFontSize()) {

						mismatch.add("Paragraph Line font size is not matching in both document" + eol
								+ "FontSize  in first doc:" + onerun.get(j).getFontSize() + eol
								+ "FontSize in second doc:" + otherrun.get(j).getFontSize() + eol + "Paragrpah text:"
								+ otherparagraphs.get(i).getText() + eol
								+ "Line number in the paragrpah where  mismtach is:" + pos + eol);

					}

					if (onerun.get(j).getFontName() != null
							&& !((onerun.get(j).getFontName().equalsIgnoreCase(otherrun.get(j).getFontName())))) {

						mismatch.add("Paragraph Line Fontname is not matching in both document" + eol
								+ "Fontname  in first doc" + onerun.get(j).getFontName() + eol
								+ "Fontname in second doc" + otherrun.get(j).getFontName() + eol + "Paragrpah text:"
								+ otherparagraphs.get(i).getText() + eol + "Line number mismtached" + pos + eol);

					}
					if (onerun.get(j).getUnderline() != otherrun.get(j).getUnderline()) {

						mismatch.add("Paragraph Line is underlined  in one document" + eol + "underlined  in first doc:"
								+ onerun.get(j).getUnderline() + eol + "underline in second doc:"
								+ otherrun.get(j).getUnderline() + eol + "Paragrpah text:"
								+ otherparagraphs.get(i).getText() + eol + "Line number mismtached" + pos + eol);

					}

				}
			}
		}
	}

	public void picComparator() throws IOException {

		List<XWPFPictureData> onepiclist = onedocument.getAllPictures();
		List<XWPFPictureData> otherpiclist = otherdocument.getAllPictures();

		if (onepiclist.size() != otherpiclist.size()) {
			mismatch.add("Picture numbers are not equal" + eol + "pictures in first doc:" + onepiclist.size() + eol
					+ "pictures in second doc:" + otherpiclist.size() + eol);
		}

		int temp;
		for (int i = 0; i < onepiclist.size(); i++) {
			byte[] onepic = onepiclist.get(i).getData();
			temp = 0;
			for (int j = 0; j < otherpiclist.size(); j++) {
				temp = j;
				byte[] otherpic = otherpiclist.get(j).getData();
				System.out.println("first pic" + onepic.toString());
				System.out.println("second pic" + otherpic.toString());
				if (Arrays.equals(onepic, otherpic)) {
					break;
				}
			}
			if (temp == otherpiclist.size()) {
				BufferedImage imag = ImageIO.read(new ByteArrayInputStream(onepic));
				ImageIO.write(imag, "jpg", new File("target\\imagefromword" + i + ".jpg"));
				System.out.println("Image is created");
				String eol = System.getProperty("line.separator");
				mismatch.add("Picture is not equal" + eol + "pictures in first doc:" + onepiclist.get(i).getData() + eol
						+ "Check this picture in target folder" + eol);

			}
		}

	}

	public String tableComparator() {

		List<XWPFTableCell> onexWPFTableCell = null;
		List<XWPFTableRow> onezeile = null;
		List<XWPFTableCell> otherxWPFTableCell = null;
		List<XWPFTableRow> otherzeile = null;
		List<XWPFTable> onetables = onedocument.getTables();
		List<XWPFTable> othertables = otherdocument.getTables();
		List<XWPFParagraph> cellotherparagraphs;
		List<XWPFParagraph> celloneparagraphs;

		if (onetables.size() != othertables.size()) {
			mismatch.add("Tables are not equal in doc" + eol + "Tables in first doc:" + onetables.size() + eol
					+ "Tables in second doc:" + othertables.size() + eol);
			return "Tables are not equal,document cannot be compared";
		}

		for (int i = 0; i < onetables.size(); i++) {
			onezeile = onetables.get(i).getRows();
			otherzeile = othertables.get(i).getRows();
			if (onezeile.size() != otherzeile.size()) {
				mismatch.add("Rows in tables are not equal in doc" + eol + "Tables in first doc:" + onetables.size()
						+ eol + "Tables in second doc:" + othertables.size() + eol);
			}
			for (int j = 0; j < onezeile.size(); j++) {
				onexWPFTableCell = onezeile.get(j).getTableCells();
				otherxWPFTableCell = otherzeile.get(j).getTableCells();
				if (onexWPFTableCell.size() != otherxWPFTableCell.size()) {
					mismatch.add("Cells in tables are not equal in doc" + eol + "cells in first doc:" + onetables.size()
							+ eol + "cells in second doc:" + othertables.size() + eol);
				}

				for (int k = 0; k < onexWPFTableCell.size(); k++) {
					celloneparagraphs = onexWPFTableCell.get(k).getParagraphs();
					cellotherparagraphs = otherxWPFTableCell.get(k).getParagraphs();
					if (celloneparagraphs.size() != cellotherparagraphs.size()) {
						mismatch.add("cellParagraphs size is not equal" + eol + "cellParagrpahs in first doc:"
								+ oneparagraphs.size() + eol + "cellsParagraphs in second doc:" + otherparagraphs.size()
								+ eol);
					}

					System.out.println("Total no of paragraph in first Docx : " + celloneparagraphs.size());
					System.out.println("Total no of paragraph in seond Docx : " + cellotherparagraphs.size());

					for (int l = 0; l < celloneparagraphs.size(); l++) {
						XWPFStyle onestyle = onedocument.getStyles().getStyle(celloneparagraphs.get(l).getStyleID());
						XWPFStyle otherstyle = otherdocument.getStyles()
								.getStyle(cellotherparagraphs.get(l).getStyleID());

						System.out.println(celloneparagraphs.get(l).getText());
						System.out.println(cellotherparagraphs.get(l).getText());

						if (!celloneparagraphs.get(l).getText()
								.equalsIgnoreCase(cellotherparagraphs.get(l).getText())) {
							mismatch.add("cellParagraph is not equal" + eol + "cellParagraph in First doc:"
									+ celloneparagraphs.get(l).getText() + eol + "cellParagraph in Seond Doc :"
									+ cellotherparagraphs.get(l).getText() + eol);
						}

						System.out.println("cellParagraphstyle:" + onestyle);
						System.out.println("cellparagrpahstyle:" + otherstyle);

						if (onestyle != otherstyle) {

							mismatch.add("Style is not equal in the paragrpah" + eol + "style in First doc:" + onestyle
									+ eol + "style in Seond Doc :" + otherstyle + eol + "cellParagraph text:"
									+ cellotherparagraphs.get(l).getText() + eol);
						}

						System.out.println("Number of Line for the current cellParagraph:"
								+ celloneparagraphs.get(l).getRuns().size());
						System.out.println("Number of Line for the current cellParagraph:"
								+ cellotherparagraphs.get(l).getRuns().size());

						if (celloneparagraphs.get(l).getRuns().size() != cellotherparagraphs.get(l).getRuns().size()) {

							mismatch.add("cellParagraph lines are not equal" + eol + "cellParagrpahline in first doc:"
									+ celloneparagraphs.get(l).getRuns().size() + eol
									+ "cellParagraphline in second doc:" + cellotherparagraphs.get(l).getRuns().size()
									+ eol + "cellParagrpah text in first doc:" + celloneparagraphs.get(l).getText()
									+ eol + "cellParagrpah text in second doc:" + cellotherparagraphs.get(l).getText()
									+ eol + eol
									+ "Mismatch migh be due to some text is bold/Italic/Fontsizemismatch/FontNamemismatch/Underlinedmismtach"
									+ eol+eol);

						} else {
							List<XWPFRun> onerun = celloneparagraphs.get(l).getRuns();
							List<XWPFRun> otherrun = cellotherparagraphs.get(l).getRuns();
							int pos = 0;
							int temp = otherrun.size(); // This will avoid the array out of index error, when if else
														// condition is removed
							if (onerun.size() < otherrun.size())
								temp = onerun.size();
							for (int m = 0; m < temp; m++) {
								pos = 1 + pos;
								System.out.println("cellParagraph Line number in first doc:" + pos);
								System.out.println("cellParagraph Line number in second doc:" + pos);
								System.out.println("cellCurrent run IsBold : " + onerun.get(m).isBold());
								System.out.println("cellCurrent run IsBold : " + otherrun.get(m).isBold());
								System.out.println("cellCurrent run IsItalic : " + onerun.get(m).isItalic());
								System.out.println("cellCurrent run IsItalic : " + otherrun.get(m).isItalic());
								System.out.println("cellCurrent Font Size : " + onerun.get(m).getFontSize());
								System.out.println("cellCurrent Font Size : " + otherrun.get(m).getFontSize());
								System.out.println("cellCurrent Font Name : " + onerun.get(m).getFontName());
								System.out.println("cellCurrent Font Name : " + otherrun.get(m).getFontName());
								System.out.println("Current text underline:" + onerun.get(m).getUnderline());
								System.out.println("Current text underline:" + otherrun.get(m).getUnderline());

								if (onerun.get(m).isBold() != otherrun.get(m).isBold()) {

									mismatch.add("cellParagraph Line is not Bold in both document" + eol
											+ "cellParagraph is Bold in first doc:" + onerun.get(m).isBold() + eol
											+ "cellParagraph is Bold in second doc:" + otherrun.get(m).isBold() + eol
											+ "cellParagraph text:" + cellotherparagraphs.get(m).getText() + eol
											+ "Line number mismtached" + pos + eol);

								}

								if (onerun.get(m).isItalic() != otherrun.get(m).isItalic()) {

									mismatch.add("cellParagraph Line is not Italic in both document" + eol
											+ "cellParagraph is italic in first doc:" + onerun.get(m).isItalic() + eol
											+ "cellParagraph is italic in second doc:" + otherrun.get(m).isItalic()
											+ eol + "cellParagraph text:" + cellotherparagraphs.get(m).getText() + eol
											+ "Line number mismtached" + pos + eol);

								}
								if (onerun.get(m).getFontSize() != otherrun.get(m).getFontSize()) {

									mismatch.add("cellParagraph Line font size is not matching in both document" + eol
											+ "FontSize  in first doc:" + onerun.get(m).getFontSize() + eol
											+ "FontSize in second doc:" + otherrun.get(m).getFontSize() + eol
											+ "Paragrpah text:" + cellotherparagraphs.get(m).getText() + eol
											+ "Line number in the paragrpah where  mismtach is" + pos + eol);

								}

								if (onerun.get(m).getFontName() != null && !((onerun.get(m).getFontName()
										.equalsIgnoreCase(otherrun.get(m).getFontName())))) {

									mismatch.add("cellParagraph Line Fontname is not matching in both document" + eol
											+ "Fontname  in first doc:" + onerun.get(m).getFontName() + eol
											+ "Fontname in second doc:" + otherrun.get(m).getFontName() + eol
											+ "Paragrpah text:" + cellotherparagraphs.get(m).getText() + eol
											+ "Line number mismtached:" + pos + eol);

								}

								if (onerun.get(m).getUnderline() != otherrun.get(m).getUnderline()) {

									mismatch.add("Paragraph Line is underlined  in one document" + eol
											+ "underlined  in first doc:" + onerun.get(m).getUnderline() + eol
											+ "underline in second doc:" + otherrun.get(m).getUnderline() + eol
											+ "Paragrpah text:" + otherparagraphs.get(m).getText() + eol
											+ "Line number mismtached" + pos + eol);

								}

							}
						}
					}
				}
			}

		}

		return "Equal";

	}
}