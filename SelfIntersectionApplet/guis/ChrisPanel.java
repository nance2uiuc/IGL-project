package guis;


import algorithm_new.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



import boundary.*;



public class ChrisPanel extends JPanel implements ActionListener {

	// panels to organize gui
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8704713034915474521L;
	private JPanel allPanel = new JPanel();
	private JPanel inputPanel = new JPanel();
	private JPanel answerPanel = new JPanel();
	private JPanel bracketPanel = new JPanel();	
	//labels
	private JLabel surfaceLabel = new JLabel("Surface Word");
	private JLabel curveLabel1 = new JLabel("First Cyclic word");
	private JLabel answerLabel3 = new JLabel("Sequences");
//	private JLabel answerLabel2 = new JLabel("Sum of the abs.value of coef. ");
	private JLabel answerLabel1 = new JLabel("Min  intersecion number.");

	private String instructions =
		"This applet computes  minimal " +
			"number of self intersections of a free homotopy \n"+ 
			"class on a surface with boundary and constructs a representative with this minimal intersection. \n"+
			" The two sequences in the last text field contain "+
			"the information to construction this representative. \n \n"+
			"Each element in the first sequence represents a " 
			+"point on the boundary of the fundamental polygon. \n"+
			"Elements with the same index but inverse letters "+
			"represent the same point on the surface. \n"+
			"For example, an element labeled 'a 2' represents a point on the 'a' side of the polygon, "+
			"while 'A 2' is the unique point on \n the 'A' side which is indentified with 'a 2'. "+"\n \n"+
			"The first sequence represents the ordering of " 
			+"these points, clockwise around the boundary of the polygon. \n"+
			"The second sequence contains pairs of 'points'  which are to be connected by directed line segments from the first point \n to the second.  "+
			"Together, these two sequences determine a representative."+
			"\n"+"\n"+
			"If you want to use a surface symbol other than abABcdCD, \n" +
			"erase abABcdCD and type your favorite one.\n " +
			"Enter a cyclically reduced word in the letters\n " +
			"of the surface symbol and hit return."
			;
	
	
	
	//input fields and instructions
	private JTextArea instructionsArea = new JTextArea(10,10);
	
	private JTextField inputSurface = new JTextField(6);
	
	private JTextField inputCurve1 = new JTextField(6);
	//answer felds
	private JTextArea answerField3 = new JTextArea(10,30);

   private JScrollPane answer3 = new JScrollPane(answerField3);
	
	//private JTextField answerField2 = new JTextField(6);
		
	private JTextField answerField1 = new JTextField(6);
	
	//String surfaceWord;
	String curve1;

	String thisSurfaceWord;
	

	/**
	 * @param args
	 */
 	
	public ChrisPanel() {
		this.setUpGUI();
	}

	public void setUpGUI() {
		
		inputSurface.setText("aAbB");
		//inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		instructionsArea.setText(instructions);
		instructionsArea.setLineWrap(true);
		inputPanel.setLayout(new GridLayout(0,2));
		inputPanel.add(surfaceLabel);
		inputPanel.add(inputSurface);
//		inputPanel.add(answerLabel3);
		inputPanel.add(curveLabel1);
		inputPanel.add(inputCurve1);
		

		
		answerPanel.setLayout(new GridLayout(0,2));
		inputSurface.addActionListener(this);
		inputCurve1.addActionListener(this);		
		answerPanel.add(answerLabel1);
		answerPanel.add(answerField1);
	//	answerPanel.add(answerLabel2);
	//	answerPanel.add(answerField2);
	//	
		answerPanel.add(answerLabel3);
		answerPanel.add(answer3);
		
		
		
		
		allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.PAGE_AXIS));
		allPanel.add(instructionsArea, BorderLayout.NORTH);
		allPanel.add(inputPanel, BorderLayout.CENTER);
		allPanel.add(answerPanel, BorderLayout.SOUTH);
	//	allPanel.add(bracketPanel, BorderLayout.SOUTH);
		this.add(allPanel);
		
	}

	/**
	 * Reads the curves
	 */
	public void actionPerformed(ActionEvent ae) {
		String thisSurfaceWord = inputSurface.getText();
		
		curve1 = inputCurve1.getText();
		//Intersections sw = new Intersections(thisSurfaceWord,curve1,curve2);
		String s1="";
		String s2="";
	NewComputation C=new NewComputation(thisSurfaceWord,curve1);
	while(C.getDone()==0)
	{
		C.findIntersection();
	}
	int[][] P1=C.getPL();
	int[][] S1=C.getSL();

	for (int i=0;i<2*curve1.length();i++)
	{
		s1=s1+C.toLetter.get(P1[i][0])+""+P1[i][1]+" , ";
	}
		s1=s1+"  ";
	for (int i=0;i<curve1.length();i++)
	{
		s2=s2+C.toLetter.get(P1[S1[i][0]][0])+""+P1[S1[i][0]][1]+" , "+C.toLetter.get(P1[S1[i][1]][0])+""+P1[S1[i][1]][1]+"\n";
	}
	System.out.println(C.getMinInt());
	answerField1.setText(""+C.getMinInt());
	answerField3.setText(s1+ " \n *************************** \n" + s2);

	
	}

}
