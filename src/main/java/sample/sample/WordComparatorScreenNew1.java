
package sample.sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;

public class WordComparatorScreenNew1 {
	private JFrame frame;
	private JFrame frame1;
	private boolean HeaderCompare;
	private boolean fontCompare;
	private boolean underlineCompare;
	private boolean bold_italicizedCompare;
	private String location;
	private String location1;
	private JLabel header1;
	private boolean imageCompare;

	public static void main(String[] args) {
		WordComparatorScreenNew1 swingLayoutDemo = new WordComparatorScreenNew1();
		swingLayoutDemo.prepareGUI();
		JFrame.setDefaultLookAndFeelDecorated(true);
	}

	private void prepareGUI() {

		frame = new JFrame("Word Document Comparator");
		frame.setSize(600, 600);
		frame.setLayout(new GridLayout(10, 0));
		JLabel header = new JLabel("Please upload files for comparing", JLabel.CENTER);
		header.setFont(new Font("Calibri", Font.PLAIN, 24));

		header.setSize(400, 400);
		header1 = new JLabel(" ", JLabel.CENTER);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setLocation(0, 0);
		GridBagConstraints c = new GridBagConstraints();
		JLabel label = new JLabel("File1 Path");
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(label, c);

		JTextArea text = new JTextArea("Please upload the file");
		c.weightx = 5.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		text.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(text, c);

		JButton button = new JButton("upload");
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		panel.add(button, c);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());

		GridBagConstraints c1 = new GridBagConstraints();
		JLabel label1 = new JLabel("File2 Path");
		c1.weightx = 0.25;
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridx = 0;
		c1.gridy = 0;
		panel1.add(label1, c1);

		JTextArea text1 = new JTextArea("Please upload the file");
		c1.weightx = 5.5;
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridx = 1;
		c1.gridy = 0;
		text1.setBorder(BorderFactory.createLineBorder(Color.black));
		panel1.add(text1, c1);

		JButton button1 = new JButton("Upload");
		c1.weightx = 0.25;
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridx = 2;
		c1.gridy = 0;
		panel1.add(button1, c1);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		GridBagConstraints c2 = new GridBagConstraints();
		JLabel label2 = new JLabel();
		c2.weightx = 0.25;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridx = 1;
		c2.gridy = 0;
		panel2.add(label2, c2);

		JButton submit = new JButton("Submit");
		c2.weightx = 0.25;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridx = 2;
		c2.gridy = 0;
		panel2.add(submit, c2);

		JButton clear = new JButton("Clear");
		c2.weightx = 0.25;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridx = 3;
		c2.gridy = 0;
		panel2.add(clear, c2);

		JLabel label3 = new JLabel();
		c2.weightx = 0.25;
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridx = 4;
		c2.gridy = 0;
		panel2.add(label3, c2);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridBagLayout());

		GridBagConstraints c3 = new GridBagConstraints();
		JCheckBox checkbox = new JCheckBox("Header/Footer Comparison needed");
		c3.weightx = 0.25;
		c3.fill = GridBagConstraints.HORIZONTAL;
		c3.gridx = 1;
		c3.gridy = 0;
		panel3.add(checkbox, c3);

		JCheckBox checkbox1 = new JCheckBox("Font Comparison needed");
		c3.weightx = 0.25;
		c3.fill = GridBagConstraints.HORIZONTAL;
		c3.gridx = 2;
		c3.gridy = 0;
		panel3.add(checkbox1, c3);

		JCheckBox checkbox2 = new JCheckBox("Underlined Comparison needed");
		c3.weightx = 0.25;
		c3.fill = GridBagConstraints.HORIZONTAL;
		c3.gridx = 3;
		c3.gridy = 0;
		panel3.add(checkbox2, c3);

		JCheckBox checkbox3 = new JCheckBox("Bold/Italicized Comparison needed");
		c3.weightx = 0.25;
		c3.fill = GridBagConstraints.HORIZONTAL;
		c3.gridx = 4;
		c3.gridy = 0;
		panel3.add(checkbox3, c3);

		JCheckBox checkbox4 = new JCheckBox("Image Comparison needed");
		c3.weightx = 0.25;
		c3.fill = GridBagConstraints.HORIZONTAL;
		c3.gridx = 5;
		c3.gridy = 0;
		panel3.add(checkbox4, c3);

		checkbox.setSelected(true);
		checkbox1.setSelected(true);
		checkbox2.setSelected(true);
		checkbox3.setSelected(true);
		checkbox4.setSelected(true);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText(getpath());
			}
		});

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text1.setText(getpath());
			}
		});
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				location = text.getText();
				location1 = text1.getText();

				System.out.println("HeaderCheck box is selected " + checkbox.isSelected());
				if (checkbox.isSelected()) {
					HeaderCompare = true;
				} else {
					HeaderCompare = false;
				}
				if (checkbox1.isSelected()) {
					fontCompare = true;
				} else {
					fontCompare = false;
				}
				if (checkbox2.isSelected()) {
					underlineCompare = true;
				} else {
					underlineCompare = false;
				}
				if (checkbox3.isSelected()) {
					bold_italicizedCompare = true;
				} else {
					bold_italicizedCompare = false;
				}

				if (checkbox4.isSelected()) {
					imageCompare = true;
				} else {
					imageCompare = false;
				}

				String doclocation1 = location.replace("\\", "\\\\");
				String doclocation2 = location1.replace("\\", "\\\\");
				try {
					WordComparatorDTONew1 cw = new WordComparatorDTONew1();
					List<String> temp = cw.wordCompare(doclocation1, doclocation2, HeaderCompare, fontCompare,
							underlineCompare, bold_italicizedCompare, imageCompare);

					HtmlReportGenerationNew report = new HtmlReportGenerationNew();
					report.reportGeneration(temp);
					header1.setText("Document comparision done and result is stored in Result folder created");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					header1.setText(e1.toString());
					e1.printStackTrace();
				}
			}
		});

		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text1.setText("");
				text.setText("");
			}
		});

		frame.add(header);
		frame.add(panel);
		frame.add(panel1);
		frame.add(panel3);
		frame.add(panel2);
		frame.add(header1);
		// frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public String getpath() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			return selectedFile.getAbsolutePath();
		}
		return "path not found";
	}

}