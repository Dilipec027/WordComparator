package sample.sample;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class WordComparatorDTONew1 {

	private XWPFDocument onedocument;
	private XWPFDocument otherdocument;
	List<XWPFParagraph> otherparagraphs;
	List<XWPFParagraph> oneparagraphs;
	String eol = System.getProperty("line.separator");
	static List<String> mismatch = new ArrayList<String>();
	static List<String> mismatchFirstDoc = new ArrayList<String>();
	static List<String> mismatchSecondDoc = new ArrayList<String>();
	static Boolean pictureDifference;
	static List<String> picpath = new ArrayList<String>();

	private Boolean fontCompare;
	private Boolean underlineCompare;
	private Boolean bold_italicizedCompare;

	// public static void main(String[] args) throws Exception {
	// ExtractDatafromTable test = new ExtractDatafromTable();
	// test.wordCompare();
	// }

	public List<String> wordCompare(String doclocation1, String doclocation2, Boolean headerFooterCheck,
			boolean fontCompare, boolean underlineCompare, boolean bold_italicizedCompare, boolean imageCompare) {

		try {

			this.fontCompare = fontCompare;
			this.underlineCompare = underlineCompare;
			this.bold_italicizedCompare = bold_italicizedCompare;

			pictureDifference = false;

			if (!mismatch.isEmpty())
				mismatch.clear();

			if (!mismatchFirstDoc.isEmpty())
				mismatchFirstDoc.clear();

			if (!mismatchSecondDoc.isEmpty())
				mismatchSecondDoc.clear();

			if (!picpath.isEmpty())
				picpath.clear();

			File onefile = new File(doclocation1);
			FileInputStream onefis = new FileInputStream(onefile.getAbsolutePath());
			File otherfile = new File(doclocation2);
			FileInputStream otherfis = new FileInputStream(otherfile.getAbsolutePath());
			onedocument = new XWPFDocument(onefis);
			otherdocument = new XWPFDocument(otherfis);

			if (imageCompare)
				picComparator();
			if (headerFooterCheck) {
				headerCompare();
				footCompare();
			}
			hyperlinkCompare();

			oneparagraphs = onedocument.getParagraphs();
			otherparagraphs = otherdocument.getParagraphs();

			if (!(paragrpahSizeCompare().equalsIgnoreCase("Equal"))) {
				// mismatch.add("Pargraphs are not equal,Cant be compared");
				// mismatchFirstDoc.add("");
				// mismatchSecondDoc.add("");
				return mismatch;
			}
			ParagraphContentCompare();

			if (!(tableComparator().equalsIgnoreCase("Equal"))) {
				// mismatch.add("Tables are not equal,Cant be compared");
				// mismatchFirstDoc.add("");
				// mismatchSecondDoc.add("");

				return mismatch;
			}

			System.out.println("Mismatch are" + eol + mismatch);

			onefis.close();
			otherfis.close();

			if (mismatch.isEmpty()) {
				mismatch.add("Documents are equal");
				mismatchFirstDoc.add("");
				mismatchSecondDoc.add("");

				return mismatch;
			}

		} catch (java.lang.IndexOutOfBoundsException e) {
			mismatch.add("Documents paragrapahs or tables not matched");
			mismatchFirstDoc.add("");
			mismatchSecondDoc.add("");
			e.printStackTrace();
			return mismatch;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			mismatch.add("File not found");
			mismatchFirstDoc.add("");
			mismatchSecondDoc.add("");
			e.printStackTrace();
			return mismatch;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			mismatch.add("I/O exception");
			mismatchFirstDoc.add("");
			mismatchSecondDoc.add("");
			e.printStackTrace();
			return mismatch;
		}

		return mismatch;

	}

	public String paragrpahSizeCompare() {
		if (oneparagraphs.size() != otherparagraphs.size()) {
			mismatch.add("Paragraphs size is not equal" + eol);
			mismatchFirstDoc.add("Paragrpahs in first doc:" + oneparagraphs.size() + eol);
			mismatchSecondDoc.add("Paragraphs in second doc:" + otherparagraphs.size() + eol);
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
				mismatch.add("Paragraph is not equal" + eol);
				mismatchFirstDoc.add("Paragrpah in First doc:" + oneparagraphs.get(i).getText() + eol);
				mismatchSecondDoc.add("Paragraph in Seond Doc :" + otherparagraphs.get(i).getText() + eol);
			}

			System.out.println("paragrpahstyle:" + onestyle);
			System.out.println("paragrpahstyle:" + otherstyle);

			if (onestyle != otherstyle) {

				mismatch.add("Style is not equal in the paragrpah" + eol);
				mismatchFirstDoc.add("style in First doc:" + onestyle + eol);
				mismatchSecondDoc.add("style in Seond Doc :" + otherstyle + eol + "Paragrpah text:"
						+ otherparagraphs.get(i).getText() + eol);
			}

			System.out.println("Number of Line for the current Paragrpahs:" + oneparagraphs.get(i).getRuns().size());
			System.out.println("Number of Line for the current Paragrpahs:" + otherparagraphs.get(i).getRuns().size());

			if (oneparagraphs.get(i).getRuns().size() != otherparagraphs.get(i).getRuns().size()) {

				mismatch.add(
						"Mismatch might be due to some text is bold/Italic/Fontsizemismatch/FontNamemismatch/Underlinedmismatch"
								+ eol + "Paragraph lines is not equal" + eol);
				mismatchFirstDoc.add("Paragrpahlines in first doc:" + oneparagraphs.get(i).getRuns().size() + eol
						+ "Paragrpah text in first doc:" + eol + oneparagraphs.get(i).getText() + eol);
				mismatchSecondDoc.add("Paragraphlines in second doc:" + otherparagraphs.get(i).getRuns().size() + eol
						+ "Paragraph text in second doc:" + eol + otherparagraphs.get(i).getText() + eol + eol);

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

					if (bold_italicizedCompare) {
						if (onerun.get(j).isBold() != otherrun.get(j).isBold()) {

							mismatch.add("Paragraph Line is not Bold in both document" + eol);
							mismatchFirstDoc.add("Paragrpah is Bold in first doc :" + onerun.get(j).isBold() + eol);
							mismatchSecondDoc.add("Paragraph is Bold in second doc:" + otherrun.get(j).isBold() + eol
									+ "Paragrpah text:" + otherparagraphs.get(i).getText() + eol
									+ "Line number mismtached" + pos + eol);

						}

						if (onerun.get(j).isItalic() != otherrun.get(j).isItalic()) {

							mismatch.add("Paragraph Line is not Italic in both document" + eol);
							mismatchFirstDoc.add("Paragrpah is italic in first doc:" + onerun.get(j).isItalic() + eol);
							mismatchSecondDoc.add("Paragraph is italic in second doc:" + otherrun.get(j).isItalic()
									+ eol + "Paragrpah text:" + otherparagraphs.get(i).getText() + eol
									+ "Line number mismtached" + pos + eol);

						}
					}
					if (fontCompare) {
						if (onerun.get(j).getFontSize() != otherrun.get(j).getFontSize()) {

							mismatch.add("Paragraph Line font size is not matching in both document" + eol);
							mismatchFirstDoc.add("FontSize  in first doc:" + onerun.get(j).getFontSize() + eol);
							mismatchSecondDoc.add("FontSize in second doc:" + otherrun.get(j).getFontSize() + eol
									+ "Paragrpah text:" + otherparagraphs.get(i).getText() + eol
									+ "Line number in the paragrpah where  mismtach is:" + pos + eol);

						}

						if (onerun.get(j).getFontName() != null
								&& !((onerun.get(j).getFontName().equalsIgnoreCase(otherrun.get(j).getFontName())))) {

							mismatch.add("Paragraph Line Fontname is not matching in both document" + eol);
							mismatchFirstDoc.add("Fontname  in first doc" + onerun.get(j).getFontName() + eol);
							mismatchSecondDoc.add("Fontname in second doc" + otherrun.get(j).getFontName() + eol
									+ "Paragrpah text:" + otherparagraphs.get(i).getText() + eol
									+ "Line number mismtached" + pos + eol);

						}
					}
					if (underlineCompare) {
						if (onerun.get(j).getUnderline() != otherrun.get(j).getUnderline()) {

							mismatch.add("Paragraph Line is underlined  in one document" + eol);
							mismatchFirstDoc.add("underlined  in first doc:" + onerun.get(j).getUnderline() + eol);
							mismatchSecondDoc.add("underline in second doc:" + otherrun.get(j).getUnderline() + eol
									+ "Paragrpah text:" + otherparagraphs.get(i).getText() + eol
									+ "Line number mismtached" + pos + eol);

						}
					}

				}
			}
		}
	}

	public void picComparator() throws IOException {

		// HWPFDocument docB = new HWPFDocument(fileInputStream);
		// PicturesTable picB = onedocument.getPicturesTable();
		// List picturesB = picB.getAllPictures();
		// for (Object o : picturesB) {
		// Picture pic = (Picture) o;
		// int height = pic.getHeight();
		// int width = pic.getWidht();

		List<XWPFPictureData> onepiclist = onedocument.getAllPictures();
		List<XWPFPictureData> otherpiclist = otherdocument.getAllPictures();

		if (onepiclist.size() != otherpiclist.size()) {
			mismatch.add("Picture numbers are not equal" + eol);
			mismatchFirstDoc.add("pictures in first doc:" + onepiclist.size() + eol);
			mismatchSecondDoc.add("pictures in second doc:" + otherpiclist.size() + eol);
		} else {

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
						temp = 0;
						break;
					}
				}

				if (temp == (otherpiclist.size() - 1)) {
					BufferedImage imag = ImageIO.read(new ByteArrayInputStream(onepic));
					foldercreation();

					String temp1 = "imagefromword" + System.currentTimeMillis() + ".jpg";
					// System.out.println(temp1);
					// File file = new File(temp1);
					ImageIO.write(imag, "jpg", new File("ResultFolder\\" + temp1));
					System.out.println("Image is created");
					// String eol = System.getProperty("line.separator");
					// mismatch.add("Picture is not equal" + eol + "pictures in first doc:" +
					// onepiclist.get(i).getData()
					// + eol + "Check this picture in screenshot folder" + eol);
					pictureDifference = true;
					picpath.add(temp1);

				}
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
			mismatch.add("Tables are not equal in doc" + eol);
			mismatchFirstDoc.add("Tables in first doc:" + onetables.size() + eol);
			mismatchSecondDoc.add("Tables in second doc:" + othertables.size() + eol);
			return "Tables are not equal,document cannot be compared";
		}

		for (int i = 0; i < onetables.size(); i++) {
			onezeile = onetables.get(i).getRows();
			otherzeile = othertables.get(i).getRows();
			if (onezeile.size() != otherzeile.size()) {
				mismatch.add("Rows in tables are not equal in doc" + eol);
				mismatchFirstDoc.add("Tables in first doc:" + onetables.size() + eol);
				mismatchSecondDoc.add("Tables in second doc:" + othertables.size() + eol);
			}
			for (int j = 0; j < onezeile.size(); j++) {
				onexWPFTableCell = onezeile.get(j).getTableCells();
				otherxWPFTableCell = otherzeile.get(j).getTableCells();
				if (onexWPFTableCell.size() != otherxWPFTableCell.size()) {
					mismatch.add("Cells in tables are not equal in doc" + eol);
					mismatchFirstDoc.add("cells in first doc:" + onetables.size() + eol);
					mismatchSecondDoc.add("cells in second doc:" + othertables.size() + eol);
				}

				for (int k = 0; k < onexWPFTableCell.size(); k++) {
					celloneparagraphs = onexWPFTableCell.get(k).getParagraphs();
					cellotherparagraphs = otherxWPFTableCell.get(k).getParagraphs();
					if (celloneparagraphs.size() != cellotherparagraphs.size()) {
						mismatch.add("cellParagraphs size is not equal" + eol);
						mismatchFirstDoc.add("cellParagrpahs in first doc:" + oneparagraphs.size() + eol);
						mismatchSecondDoc.add("cellsParagraphs in second doc:" + otherparagraphs.size() + eol);
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
							mismatch.add("cellParagraph is not equal" + eol);
							mismatchFirstDoc
									.add("cellParagraph in First doc:" + celloneparagraphs.get(l).getText() + eol);
							mismatchSecondDoc
									.add("cellParagraph in Seond Doc :" + cellotherparagraphs.get(l).getText() + eol);
						}

						System.out.println("cellParagraphstyle:" + onestyle);
						System.out.println("cellparagrpahstyle:" + otherstyle);

						if (onestyle != otherstyle) {

							mismatch.add("Style is not equal in the paragrpah" + eol);
							mismatchFirstDoc.add("style in First doc:" + onestyle + eol);
							mismatchSecondDoc.add("style in Seond Doc :" + otherstyle + eol + "cellParagraph text:"
									+ cellotherparagraphs.get(l).getText() + eol);
						}

						System.out.println("Number of Line for the current cellParagraph:"
								+ celloneparagraphs.get(l).getRuns().size());
						System.out.println("Number of Line for the current cellParagraph:"
								+ cellotherparagraphs.get(l).getRuns().size());

						if (celloneparagraphs.get(l).getRuns().size() != cellotherparagraphs.get(l).getRuns().size()) {

							mismatch.add(
									"Mismatch migh be due to some text is bold/Italic/Fontsizemismatch/FontNamemismatch/Underlinedmismatch"
											+ eol + "cellParagraph lines are not equal" + eol);
							mismatchFirstDoc.add("cellParagrpahline in first doc:"
									+ celloneparagraphs.get(l).getRuns().size() + eol
									+ "cellParagrpah text in first doc:" + celloneparagraphs.get(l).getText() + eol);
							mismatchSecondDoc.add(
									"cellParagraphline in second doc:" + cellotherparagraphs.get(l).getRuns().size()
											+ eol + "cellParagrpah text in second doc:"
											+ cellotherparagraphs.get(l).getText() + eol + eol);

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
								if (bold_italicizedCompare) {
									if (onerun.get(m).isBold() != otherrun.get(m).isBold()) {

										mismatch.add("cellParagraph Line is not Bold in both document" + eol);
										mismatchFirstDoc.add(
												"cellParagraph is Bold in first doc:" + onerun.get(m).isBold() + eol);
										mismatchSecondDoc.add(
												"cellParagraph is Bold in second doc:" + otherrun.get(m).isBold() + eol
														+ "cellParagraph text:" + cellotherparagraphs.get(m).getText()
														+ eol + "Line number mismtached" + pos + eol);

									}

									if (onerun.get(m).isItalic() != otherrun.get(m).isItalic()) {

										mismatch.add("cellParagraph Line is not Italic in both document" + eol);
										mismatchFirstDoc.add("cellParagraph is italic in first doc:"
												+ onerun.get(m).isItalic() + eol);
										mismatchSecondDoc.add("cellParagraph is italic in second doc:"
												+ otherrun.get(m).isItalic() + eol + "cellParagraph text:"
												+ cellotherparagraphs.get(m).getText() + eol + "Line number mismtached"
												+ pos + eol);

									}
								}
								if (fontCompare) {
									if (onerun.get(m).getFontSize() != otherrun.get(m).getFontSize()) {

										mismatch.add(
												"cellParagraph Line font size is not matching in both document" + eol);
										mismatchFirstDoc
												.add("FontSize  in first doc:" + onerun.get(m).getFontSize() + eol);
										mismatchSecondDoc.add("FontSize in second doc:" + otherrun.get(m).getFontSize()
												+ eol + "Paragrpah text:" + cellotherparagraphs.get(m).getText() + eol
												+ "Line number in the paragrpah where  mismtach is" + pos + eol);

									}

									if (onerun.get(m).getFontName() != null && !((onerun.get(m).getFontName()
											.equalsIgnoreCase(otherrun.get(m).getFontName())))) {

										mismatch.add(
												"cellParagraph Line Fontname is not matching in both document" + eol);
										mismatchFirstDoc
												.add("Fontname  in first doc:" + onerun.get(m).getFontName() + eol);
										mismatchSecondDoc.add("Fontname in second doc:" + otherrun.get(m).getFontName()
												+ eol + "Paragrpah text:" + cellotherparagraphs.get(m).getText() + eol
												+ "Line number mismtached:" + pos + eol);

									}
								}
								if (underlineCompare) {
									if (onerun.get(m).getUnderline() != otherrun.get(m).getUnderline()) {

										mismatch.add("Paragraph Line is underlined  in one document" + eol);
										mismatchFirstDoc
												.add("underlined  in first doc:" + onerun.get(m).getUnderline() + eol);
										mismatchSecondDoc
												.add("underline in second doc:" + otherrun.get(m).getUnderline() + eol
														+ "Paragrpah text:" + otherparagraphs.get(m).getText() + eol
														+ "Line number mismtached" + pos + eol);

									}
								}

							}
						}
					}
				}
			}

		}

		return "Equal";
	}

	public void foldercreation() {
		String currentDir = System.getProperty("user.dir");
		System.out.println(currentDir);
		Path path = Paths.get(currentDir + "\\ResultFolder");
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void headerCompare() {
		// List<XWPFHeader> header = onedocument.getHeaderList();

		List<XWPFPictureData> oneheaderpic = null;
		List<XWPFPictureData> otherheaderpic = null;
		Boolean oneHeaderHasPic = true;
		Boolean otherHeaderHasPic = true;
		Boolean oneHeaderHasText = true;
		Boolean otherHeadHasText = true;

		// read Header

		XWPFHeaderFooterPolicy onepolicy = new XWPFHeaderFooterPolicy(onedocument);
		XWPFHeader oneheader = onepolicy.getDefaultHeader();
		// System.out.println(oneheader.getText());
		try {
			oneheaderpic = oneheader.getAllPictures();
		} catch (NullPointerException e) {
			oneHeaderHasPic = false;
		}
		// read header
		XWPFHeaderFooterPolicy otherpolicy = new XWPFHeaderFooterPolicy(otherdocument);
		XWPFHeader otherheader = otherpolicy.getDefaultHeader();

		try {
			otherheaderpic = otherheader.getAllPictures();
		} catch (NullPointerException e) {
			otherHeaderHasPic = false;
		}
		System.out.println();
		// System.out.println(otherheader.getText());
		if (oneHeaderHasPic != otherHeaderHasPic) {
			mismatch.add("Header Picture are not equal" + eol);
			mismatchFirstDoc.add("");
			mismatchSecondDoc.add("");

		} else if (oneHeaderHasPic == otherHeaderHasPic && otherHeaderHasPic == false) {

		} else {
			if (oneheaderpic.size() != otherheaderpic.size()) {
				mismatch.add("Header Pictures are not equal" + eol);
				mismatchFirstDoc.add("");
				mismatchSecondDoc.add("");

			} else {
				for (int i = 0; i < oneheaderpic.size(); i++) {
					byte[] oneimagedata = oneheaderpic.get(i).getData();
					byte[] otherimagedata = otherheaderpic.get(i).getData();
					if (!(Arrays.equals(oneimagedata, otherimagedata))) {
						mismatch.add("Header Pictures are not equal" + eol + eol);
						mismatchFirstDoc.add("");
						mismatchSecondDoc.add("");
						break;
					}
				}
			}
		}

		try {
			oneheader.getText();
		} catch (NullPointerException e) {
			oneHeaderHasText = false;
		}
		try {
			otherheader.getText();
		} catch (NullPointerException e) {
			otherHeadHasText = false;
		}
		if (oneHeaderHasText != otherHeadHasText) {
			mismatch.add("Header Text are not equal,one of the document is not having header text" + eol);
			mismatchFirstDoc.add("");
			mismatchSecondDoc.add("");

		} else if (oneHeaderHasText == otherHeadHasText && oneHeaderHasText == false) {
		} else {
			if (!(oneheader.getText().equals(otherheader.getText()))) {
				mismatch.add("Header Text is not Equal or default header might be different");
				mismatchFirstDoc.add(oneheader.getText());
				mismatchSecondDoc.add(otherheader.getText());
			}
		}
	}

	public void footCompare() {
		// List<XWPFHeader> header = onedocument.getHeaderList();
		List<XWPFPictureData> onefooterpic = null;
		Boolean oneFooterHasPic = true;
		List<XWPFPictureData> otherfooterpic = null;
		Boolean otherFooterHasPic = true;
		Boolean otherFooterHasText = true;
		Boolean oneFooterHasText = true;

		XWPFHeaderFooterPolicy onepolicy = new XWPFHeaderFooterPolicy(onedocument);

		// read footer
		XWPFFooter onefooter = onepolicy.getDefaultFooter();
		// System.out.println(onefooter.getText());

		try {
			onefooterpic = onefooter.getAllPictures();
		} catch (NullPointerException e) {
			oneFooterHasPic = false;
		}
		XWPFHeaderFooterPolicy otherpolicy = new XWPFHeaderFooterPolicy(otherdocument);

		// read footer
		XWPFFooter otherfooter = otherpolicy.getDefaultFooter();
		// System.out.println(otherfooter.getText());

		try {
			otherfooterpic = otherfooter.getAllPictures();
		} catch (NullPointerException e) {
			otherFooterHasPic = false;
		}
		if (oneFooterHasPic != otherFooterHasPic) {
			mismatch.add("Picture in Footers are not equal" + eol);
			mismatchFirstDoc.add("");
			mismatchSecondDoc.add("");

		} else if (oneFooterHasPic == otherFooterHasPic && otherFooterHasPic == false) {

		} else {
			if (onefooterpic.size() != otherfooterpic.size()) {
				mismatch.add("Footer Pictures are not equal" + eol);
				mismatchFirstDoc.add("");
				mismatchSecondDoc.add("");

			} else {
				for (int i = 0; i < onefooterpic.size(); i++) {
					byte[] oneimagedata = onefooterpic.get(i).getData();
					byte[] otherimagedata = otherfooterpic.get(i).getData();
					if (!(Arrays.equals(oneimagedata, otherimagedata))) {
						mismatch.add("Footer Pictures are not equal" + eol + eol);
						mismatchFirstDoc.add("");
						mismatchSecondDoc.add("");
						break;
					}
				}
			}
		}
		try {
			onefooter.getText();
		} catch (NullPointerException e) {
			oneFooterHasText = false;
		}
		try {
			otherfooter.getText();
		} catch (NullPointerException e) {
			otherFooterHasText = false;
		}

		if (oneFooterHasText != otherFooterHasText) {
			mismatch.add("Footer Text is not equal,one of the footer is not having text" + eol);
			mismatchFirstDoc.add("");
			mismatchSecondDoc.add("");

		} else if (oneFooterHasText == otherFooterHasText && otherFooterHasText == false) {

		} else {
			if (!(onefooter.getText().equals(otherfooter.getText()))) {
				mismatch.add("Footer Text is not Equal or default Footer might be different");
				mismatchFirstDoc.add(onefooter.getText());
				mismatchSecondDoc.add(otherfooter.getText());
			}
		}
	}

	public void hyperlinkCompare() {
		XWPFHyperlink[] onedocLink = onedocument.getHyperlinks();
		XWPFHyperlink[] otherdocLink = otherdocument.getHyperlinks();
		if (!Arrays.equals(onedocLink, otherdocLink)) {
			mismatch.add("Hyperlinks are not equal in the documents" + eol + eol);
			mismatchFirstDoc.add("Number of Hyperlinks in first doc:" + onedocLink.length);
			mismatchSecondDoc.add("Number of Hyperlinks in second doc:" + otherdocLink.length);

		}
	}
}