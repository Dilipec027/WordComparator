/*************************************************************
Class Name: WordComparatorScreen
Purpose:Word Comparator Screen
Methods: indexscreen
Owner: Dilip Kumar Muniraju
Created Date: 15-06-2018
Last updated Date: 15-06-2018
 ***********************************************************/

package sample.sample;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WordComparatorScreen {
	private JTextArea result;
	private JLabel headerLabel;
	private JLabel headerLabel1;
	private JLabel statusLabel;
	private JButton submit;	
	private JTextField location;
	private JTextField location1;
	private JLabel headerLabel2;
	private JLabel headerLabel3;
	private JLabel headerLabel4;
	private JTextArea result1;
	private JTextArea result2;
	private JScrollPane scroll;
	private JScrollPane scroll1;
	private JScrollPane scroll2;

   public static void main(String[] args) {
	   WordComparatorScreen wcs= new WordComparatorScreen();
	   wcs.indexscreen();
}
	

	public void indexscreen() {
		final JFrame mainFrame = new JFrame("Word Comparator");
		mainFrame.setSize(800, 800);
		mainFrame.setLayout(new GridLayout(12, 1));
		
		submit = new JButton("submit");		
		headerLabel = new JLabel("", JLabel.CENTER);
		headerLabel.setText("Please provide the path in the below textbox for firstdoc");
		headerLabel1 = new JLabel("", JLabel.CENTER);
		headerLabel1.setText("Please provide the path in the below textbox for seconddoc");
		headerLabel2 = new JLabel("", JLabel.CENTER);
		headerLabel2.setText("The text read from the first doc is in the below textField");
		headerLabel3 = new JLabel("", JLabel.CENTER);
		headerLabel3.setText("The text read from the second doc is in the below textField");
		headerLabel4 = new JLabel("", JLabel.CENTER);
		headerLabel4.setText("Differneces are disaplyed in the below text");
		statusLabel = new JLabel("", JLabel.CENTER);
		location = new JTextField();
		location1 = new JTextField();
		result = new JTextArea();		
		result1 = new JTextArea();
		result2 = new JTextArea();
		scroll1 =  new JScrollPane(result1);
		scroll2 =  new JScrollPane(result2);
		scroll =  new JScrollPane(result);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				statusLabel.setText(" ");
                String doclocation1 = location.getText().replace("\\", "\\\\");
                String doclocation2 = location1.getText().replace("\\", "\\\\");
                System.out.println(doclocation1);
                System.out.println(doclocation2);
                
//                CompareWord cw = new CompareWord();
                ExtractDatafromTable cw = new ExtractDatafromTable();
                List<String> temp;
				try {
//					temp = cw.compareWord(doclocation1, doclocation2);
					temp = cw.wordCompare(doclocation1, doclocation2);
					StringBuffer temp1 = new StringBuffer();
					System.out.println("temp"+temp);
					for(String str : temp) {
						temp1.append( str);
					}
					result2.setText(temp1.toString());
//	                result.setText(cw.doc1Text(doclocation1));
//	                result1.setText(cw.doc2Text(doclocation2));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					statusLabel.setText(e1.toString());
					result2.setText(e1.toString());
				}
			}
		});
		mainFrame.add(headerLabel);
		mainFrame.add(location);
		mainFrame.add(headerLabel1);
		mainFrame.add(location1);
//		mainFrame.add(headerLabel2);
//		mainFrame.add(result);
//		mainFrame.add(scroll);
//		mainFrame.add(headerLabel3);
//		mainFrame.add(result1);
//		mainFrame.add(scroll1);
		mainFrame.add(headerLabel4);
//		mainFrame.add(result2);
		mainFrame.add(scroll2);
		mainFrame.add(statusLabel);		
		mainFrame.add(submit);		
		mainFrame.setVisible(true);
	}
}