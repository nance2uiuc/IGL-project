package guis;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import boundary.*;




public class IntersectionsPanel extends JPanel implements ActionListener {

	// panels to organize gui
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel allPanel = new JPanel();

	private JPanel inputPanel = new JPanel();
	private JPanel answerPanel = new JPanel();
	
	
	//labels
	private JLabel surfaceLabel = new JLabel("Surface Word");
	private JLabel curveLabel1 = new JLabel("First cyclic word");
	private JLabel curveLabel2 = new JLabel("Second cyclic word");
	private JLabel answerLabel = new JLabel("Geom. min.self intersection, first word");
	private JLabel answerLabel2 = new JLabel("Geom. min. self intersection,second word ");
	private JLabel answerLabel3 = new JLabel("Geom. min. intersection of both words.");
	
	private String instructions ="This applet computes minimal " +
			"intersection and self intersection number of curves in a surface with boundary "+
	     "following Cohen-Lustig algorithm.\n" +
		"Enter the two cyclically reduced words in the blanks.\n" +
		" A capitalized letter means the letter with a bar, that is, " +
		"the correspoinding generator in the opposite sense. \n" +      
		"The calculations will be done for a surface word abABcdCD if you do not change it.\n" +
		"If you do change the surface word, make sure you input a word in which every letter and its bar appear exactly once.\n" +
		"If the applet seems frozen , you need reload the webpage.";
		
	
	//input fields and instructions
	private JTextArea instructionsArea = new JTextArea(5,15);
	
	private JTextField inputSurface = new JTextField(12);
	
	private JTextField inputCurve1 = new JTextField(12);
	private JTextField inputCurve2 = new JTextField(12);
	//answer felds
	private JTextField answerField1 = new JTextField(2);

	private JTextField answerField2 = new JTextField(2);
	
	
	private JTextField answerField3 = new JTextField(2);
	
	//String surfaceWord;
	String curve1;
	String curve2;
	String thisSurfaceWord;
	

	/**
	 * @param args
	 */
 	
	public IntersectionsPanel() {

		//this.setSize(700,700);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //this.setTitle("Compute bracket");
		this.setUpGUI();
		// this.setVisible(true);
		// this.pack();
	}

	public void setUpGUI() {
		
		inputSurface.setText("abABcdCD");
		//inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		instructionsArea.setText(instructions);
		instructionsArea.setLineWrap(true);
		inputPanel.setLayout(new GridLayout(0,2));
		inputPanel.add(surfaceLabel);
		inputPanel.add(inputSurface);
		
		inputPanel.add(curveLabel1);
		inputPanel.add(inputCurve1);
		inputPanel.add(curveLabel2);	
		inputPanel.add(inputCurve2);

		answerPanel.setLayout(new GridLayout(0,2));
		//inputSurface.addActionListener(this);
		inputCurve1.addActionListener(this);
		inputCurve2.addActionListener(this);
		
		answerPanel.add(answerLabel);
		answerPanel.add(answerField1);
		answerPanel.add(answerLabel2);
		answerPanel.add(answerField2);
		answerPanel.add(answerLabel3);
		answerPanel.add(answerField3);
		
		
		allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.PAGE_AXIS));
		allPanel.add(instructionsArea, BorderLayout.NORTH);
		allPanel.add(inputPanel, BorderLayout.CENTER);
		allPanel.add(answerPanel, BorderLayout.SOUTH);
		this.add(allPanel);
		
	}

	/**
	 * Reads the curves
	 */
	public void actionPerformed(ActionEvent ae) {
		
		thisSurfaceWord = inputSurface.getText();
		SurfaceWord sw = new SurfaceWord(thisSurfaceWord);
		
		curve1 = inputCurve1.getText();
		curve2 = inputCurve2.getText();
		
		int[] v1 = sw.toNumber(curve1);
		int[] v2 = sw.toNumber(curve2);
		
		WordVector vv1 = new WordVector(v1);
		WordVector vv2 = new WordVector(v2);
		Integer answer1 = vv1.selfIntersection(sw);
		Integer answer2 = vv2.selfIntersection(sw);
		Integer answer3 =  vv1.intersection(vv2, sw);
		
		answerField1.setText(answer1.toString());
		answerField2.setText(answer2.toString());;
		answerField3.setText(answer3.toString());
		
	
	}

}
