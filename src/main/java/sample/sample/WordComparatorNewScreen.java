
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

public class WordComparatorNewScreen {
	private JFrame frame;
	private JFrame frame1;
	private boolean HeaderCompare;
	private String location;
	private String location1;

	// public WordComparatorNewScreen() {
	// prepareGUI();
	// }

	public static void main(String[] args) {
		WordComparatorNewScreen swingLayoutDemo = new WordComparatorNewScreen();
		swingLayoutDemo.prepareGUI();
		// swingLayoutDemo.result();
		// swingLayoutDemo.showGridLayoutDemo();
		JFrame.setDefaultLookAndFeelDecorated(true);
	}

	private void prepareGUI() {

		frame = new JFrame("Word Comparator");
		frame.setSize(600, 600);
		frame.setLayout(new GridLayout(10, 0));
		JLabel header = new JLabel("Please upload files for comparing", JLabel.CENTER);
		header.setFont(new Font("Calibri", Font.PLAIN, 24));

		header.setSize(400, 400);
		JLabel header1 = new JLabel(" ", JLabel.CENTER);
		header1.setFont(new Font("Calibri", Font.PLAIN, 24));
		header1.setSize(400, 400);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		// panel.setBorder(BorderFactory.createLineBorder(Color.black));

		// panel.setBounds(0, 1,400 , 100);
		panel.setLocation(0, 0);
		GridBagConstraints c = new GridBagConstraints();
		JLabel label = new JLabel("File1 Path");
		c.weightx = 0.25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(label, c);

		JTextArea text = new JTextArea("Please upload the file");
		c.weightx = 1;
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
		c1.weightx = 1;
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

		JCheckBox checkbox = new JCheckBox("Header Comparsion needed");
		checkbox.setSelected(true);
		if (checkbox.isSelected()) {
			HeaderCompare = true;
		} else {
			HeaderCompare = false;
		}

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
				// header1.setText("Please Wait!"); use Mutli threading to handle it
				result();
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
		frame.add(checkbox);
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

	public void result() {
		JTextArea result = new JTextArea();
		String doclocation1 = location.replace("\\", "\\\\");
		String doclocation2 = location1.replace("\\", "\\\\");
		System.out.println(doclocation1);
		System.out.println(doclocation2);
		ExtractDatafromTable cw = new ExtractDatafromTable();
		List<String> temp;
		try {
			temp = cw.wordCompare(doclocation1, doclocation2);
			StringBuffer temp1 = new StringBuffer();
			System.out.println("temp" + temp);
			for (String str : temp) {
				temp1.append(str);
			}
			result.setText(temp1.toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result.setText(e1.toString());
		}
		frame.dispose();
		frame1 = new JFrame("Comparison Result");
		frame1.setSize(600, 600);
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("Result is displayed below"));
		Box box = Box.createHorizontalBox();
		box.add(new JScrollPane(result));
		Box box2 = Box.createHorizontalBox();
		JButton returnButton = new JButton("Return");
		box2.add(returnButton);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				prepareGUI();
			}
		});
		frame1.add(box1, BorderLayout.PAGE_START);
		frame1.add(box);
		frame1.add(box2, BorderLayout.PAGE_END);//
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}