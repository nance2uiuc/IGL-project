package guis;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JApplet;
import javax.swing.JTextField;

import algorithm_new.NewComputation;

public class SelfIntersectionAppletV2 extends JApplet implements ActionListener
{
/* public class SelfIntersectionApplet extends JFrame
   {
	public static void main(String[] args)
	{
		System.out.println("hello");
		SelfIntersectionApplet applet = new SelfIntersectionApplet();
		applet.setVisible(true);
	}

	public SelfIntersectionApplet()
	{
		setSize(900,600);
		setLocation(0,0);
		layoutGUI();
	}
*/	
	// TOP-LEVEL-PANEL
	private int FirstTime;
	private JPanel topPanel = new JPanel();
	GraphPanel graphPanel = new GraphPanel();

	// NORTH
	private JTextArea descTextArea = new JTextArea(10,10);
	private JScrollPane jsp = new JScrollPane(descTextArea);

	// EAST

	// WEST
	private static final int TEXT_FIELD_WIDTH = 20;
	private JPanel westPanel = new JPanel();
	private JPanel westNorthPanel = new JPanel();
	private JLabel swLabel = new JLabel("Surface Word:");
	private JTextField swTextField = new JTextField(TEXT_FIELD_WIDTH);
	private JLabel fcLabel = new JLabel("Reduced Cyclic Word:");
	private JTextField fcTextField = new JTextField(TEXT_FIELD_WIDTH);
	private JLabel minLabel = new JLabel("Min Self Intersection:");
	private JTextField minTextField = new JTextField(TEXT_FIELD_WIDTH);
	private JLabel seqLabel = new JLabel("Sequences:");
	private JPanel westSouthPanel = new JPanel();
	private JTextArea seqTextArea = new JTextArea(5,5);
	private JScrollPane seqScrollPane = new JScrollPane(seqTextArea);
	
	private String instructions =
		"This applet computes  minimal " +
			"number of self intersections of a free homotopy "+ 
			"class on a surface with boundary and constructs a representative with this minimal intersection. \n"+
			"The two sequences in the last text field contain "+
			"the information to construction this representative. "+
			"Each element in the first sequence represents a " 
			+"point on the boundary of the \n fundamental polygon. "+
			"Elements with the same index but inverse letters "+
			"represent the same point on the surface.  "+
			"For example, an element labeled 'a 2' represents a point on \n the 'a' side of the polygon, "+
			"while 'A 2' is the unique point on the 'A' side which is indentified with 'a 2'. "+"\n \n"+
			"The first sequence represents the ordering of " 
			+"these points, clockwise around the boundary of the polygon.  "+
			"The second sequence contains pairs of 'points'  which are to be \n connected by directed line segments from the first point to the second.  "+
			"Together, these two sequences determine a representative."+
			"\n"+"\n"+
			"Enter a surface word and a reduced word in the letters of the surface word and hit 'return'. " +
			"  Each arc of the circle represents an edge of the corresponding fundamental polygon. Each arc of the circle is separated by a puncture."
			;

	public void init()
	{
		FirstTime=0;
		setSize(1000,600);
		layoutGUI();
	}

	public void layoutGUI()
	{
		// TOP LEVEL PANEL
		descTextArea.setText(instructions);
		BorderLayout topPanelLayout = new BorderLayout();
		topPanel.setLayout(topPanelLayout);
		add(topPanel);

		// NORTH
		topPanel.add(jsp, BorderLayout.NORTH);
	
		// WEST
		BorderLayout westLayout = new BorderLayout();
		westPanel.setLayout(westLayout);
		
		// WEST-NORTH
		GridLayout westNorthLayout = new GridLayout(3,2);
		westNorthPanel.setLayout(westNorthLayout);
		westNorthPanel.add(swLabel);
		westNorthPanel.add(swTextField);
		westNorthPanel.add(fcLabel);
		westNorthPanel.add(fcTextField);
		westNorthPanel.add(minLabel);
		westNorthPanel.add(minTextField);
		swTextField.addActionListener(this);
		fcTextField.addActionListener(this);
		// WEST-SOUTH
		GridLayout westSouthLayout = new GridLayout(1,2);
		westSouthPanel.setLayout(westSouthLayout);
		westSouthPanel.add(seqLabel);
		westSouthPanel.add(seqScrollPane);
		westPanel.add(westNorthPanel,BorderLayout.NORTH);
		westPanel.add(westSouthPanel,BorderLayout.CENTER);
		topPanel.add(westPanel, BorderLayout.WEST);
		
		// CENTER
		topPanel.add(graphPanel, BorderLayout.CENTER);
		//graphPanel.addActionListener();
	}
	
	class GraphPanel extends JPanel 
	{//attempt to create pictures only for the punctured torus
		int[][] PL;
		int[][] SL;
		//
		//input the surface (only abAB and aAbB work at this point)
		String sw1;
		//input the curve
		String cw1;
		//the word bbAABaaB causes trouble with aAbB
		//the word BAbbaaBB has 6 different reps with min intersection
		//does cycling through the letters give all the different reps?
		//the word ABaabb has 4 different reps
		//the word ABaabbAB has 1,2,3,4,5,6,7 reps
			
		int ia;
		int ib;
		int[] XCurveCoord;
		int[] YCurveCoord;
		int Size;

		public void paintComponent(Graphics g) 
		{	
			if(FirstTime==0){sw1="abAB";cw1="a";return;}
			else{sw1=swTextField.getText();cw1=fcTextField.getText();}
			//change Size to make the picture bigger/smaller if you'd like.	
		//	System.out.println(sw1+"  "+cw1);
			Size=400;	
			int PicSize=400;
			this.setSize(Size+50,Size+100);
			
			//attempting to draw circle
			 int[] Circle1X=new int[sw1.length()];
			 int[] Circle1Y=new int[sw1.length()];
			 int[] Circle2X=new int[sw1.length()];
			 int[] Circle2Y=new int[sw1.length()];
			    
			    for(int i=0;i<sw1.length();i++)
			    {
			    	
			    	Circle1X[i]= 20+Math.round(Size/2+Math.round((Size/2-10)*Math.cos(Math.PI+2*Math.PI*i/(sw1.length()))));
			    	Circle1Y[i]= 20+Math.round(Size/2+Math.round((Size/2-10)*Math.sin(Math.PI+2*Math.PI*i/(sw1.length()))));
			    	
			    	Circle2X[i]= 20+Math.round(Size/2+Math.round((10+Size/2)*Math.cos(Math.PI+2*Math.PI*i/(sw1.length()))));
			    	Circle2Y[i]= 20+Math.round(Size/2+Math.round((10+Size/2)*Math.sin(Math.PI+2*Math.PI*i/(sw1.length()))));
			    	
			    	
			    	
			    	//	g.drawPolygon(CircleX,CircleY,sw1.length());
			    	g.drawString(""+sw1.charAt(i),20+Math.round(Size/2+Math.round((Size/2+10)*Math.cos(2*Math.PI/(2*sw1.length())+Math.PI+2*Math.PI*i/(sw1.length())))),20+Math.round(Size/2+Math.round((Size/2+10)*Math.sin(2*Math.PI/(2*sw1.length())+Math.PI+2*Math.PI*i/(sw1.length())))));
			    //	System.out.println(CircleX[i]+" , "+CircleY[i]);
			    }
			    for(int i=0;i<sw1.length();i++)
			    {
			    //	g.drawLine(CircleX[i-1],CircleY[i-1],CircleX[i],CircleY[i]);
			    	g.drawOval(20,20,Size,Size);
			    	g.drawLine(Circle1X[i],Circle1Y[i],Circle2X[i],Circle2Y[i]);
			    	System.out.println(Circle1X[i]+" , "+Circle1Y[i]+" , "+Circle2X[i]+" , "+Circle2Y[i]);
			
			    }
			    int[] PointsonEdge=new int[cw1.length()*2];
			 //   g.drawLine(CircleX[sw1.length()-1],CircleY[sw1.length()-1],CircleX[0],CircleY[0]);
			//
			
		//	System.out.println("why is it running paint twice?");
			NewComputation C = new NewComputation(sw1,cw1);
			String c1=cw1.toLowerCase();
			int ia=0;
			int ib=0;
			XCurveCoord=new int[2*cw1.length()];
			YCurveCoord=new int[2*cw1.length()];
			
			for (int i=0;i<c1.length();i++)
			{
				if (c1.charAt(i)=='a') ia++;
				if (c1.charAt(i)=='b') ib++;
			}	
			while(C.getDone()==0)
			{
				//System.out.println("Length: "+C.combLength());
				C.findIntersection();			

			}
		//	C.easierList();
			int[][] PL=C.getPL();
			int[][] SL=C.getSL();
			int Cursor; 
			int[] usednum=new int[sw1.length()];
			
			
			
		 // i = 0 is first letter, i = 1 is 2nd letter, etc.	
			int letterholder=PL[0][0];
			for (int i=0;i<2*cw1.length();i++)
			{
				//need to know whether an inverse letter has been "used up" already
				if(PL[i][0]!=letterholder)
				{
					usednum[letterholder]=1;
					letterholder=PL[i][0];
				}
				System.out.println(1.0*(1+PL[i][1])/(C.getNumPoints(PL[i][0])+2)*2*Math.PI/(sw1.length()));
				if(usednum[C.barN.get(PL[i][0])]==0)
				{
					XCurveCoord[i]=20+Math.round(Size/2+Math.round(Size/2*Math.cos(1.0*(1+PL[i][1])/(C.getNumPoints(PL[i][0])+1)*2*Math.PI/(sw1.length())+ Math.PI+2*Math.PI*PL[i][0]/(sw1.length()))));
					YCurveCoord[i]=20+Math.round(Size/2+Math.round(Size/2*Math.sin(1.0*(1+PL[i][1])/(C.getNumPoints(PL[i][0])+1)*2*Math.PI/(sw1.length())+ Math.PI+2*Math.PI*PL[i][0]/(sw1.length()))));
				}
				else {
					XCurveCoord[i]=20+Math.round(Size/2+Math.round(Size/2*Math.cos(1.0*(C.getNumPoints(PL[i][0])-PL[i][1])/(C.getNumPoints(PL[i][0])+1)*2*Math.PI/(sw1.length())+ Math.PI+2*Math.PI*PL[i][0]/(sw1.length()))));
					YCurveCoord[i]=20+Math.round(Size/2+Math.round(Size/2*Math.sin(1.0*(C.getNumPoints(PL[i][0])-PL[i][1])/(C.getNumPoints(PL[i][0])+1)*2*Math.PI/(sw1.length())+ Math.PI+2*Math.PI*PL[i][0]/(sw1.length()))));

				}				
					//System.out.println(XCurveCoord[i]+"  *  "+YCurveCoord[i]);
			}
			//System.out.println("*****************");
			for(int k=0;k<cw1.length();k++)
		    {
		    	g.drawLine(XCurveCoord[SL[k][0]],YCurveCoord[SL[k][0]],XCurveCoord[SL[k][1]],YCurveCoord[SL[k][1]]);
		    	int mpx=(XCurveCoord[SL[k][0]]+XCurveCoord[SL[k][1]])/2;
		    	int mpy=(YCurveCoord[SL[k][0]]+YCurveCoord[SL[k][1]])/2;
		    	double a=XCurveCoord[SL[k][0]];
		    	double b=YCurveCoord[SL[k][0]];
		    	double c=XCurveCoord[SL[k][1]];
		    	double d=YCurveCoord[SL[k][1]];
		    	double atheta=Math.toRadians(45);
		    	double ltheta=Math.atan((d-b)/(c-a));
		    	Double temp1=10*Math.sin(ltheta+Math.PI/2-atheta);
		    	Double temp2=10*Math.cos(ltheta+Math.PI/2-atheta);
		    	//SHOULD HAVE 8 CASES TOTAL
		    if(c>=a&& d>=b)
		    {
		    	g.drawLine(mpx,mpy,mpx-temp2.intValue(),mpy-temp1.intValue());
		    	g.drawLine(mpx,mpy,mpx-temp1.intValue(),mpy+temp2.intValue());
		    }
		    if(c<a && d<b)
		    {
		    	g.drawLine(mpx,mpy,mpx+temp2.intValue(),mpy+temp1.intValue());
		    	g.drawLine(mpx,mpy,mpx+temp1.intValue(),mpy-temp2.intValue());
		    }
		    if(c>=a && d<b)
		    {
		    	g.drawLine(mpx,mpy,mpx-temp2.intValue(),mpy-temp1.intValue());
		    	g.drawLine(mpx,mpy,mpx-temp1.intValue(),mpy+temp2.intValue());
		    }
		    if(c<a && d>=b)
		    {
		    	g.drawLine(mpx,mpy,mpx+temp2.intValue(),mpy+temp1.intValue());
		    	g.drawLine(mpx,mpy,mpx+temp1.intValue(),mpy-temp2.intValue());
		    }
		   
		    }
			for(int k=0;k<cw1.length();k++)
			    {
			    	g.drawLine(XCurveCoord[SL[k][0]],YCurveCoord[SL[k][0]],XCurveCoord[SL[k][1]],YCurveCoord[SL[k][1]]);
			    }

		    String s1="";
			String s2="";
			    
			for (int i=0;i<2*cw1.length();i++)
			{
				s1=s1+C.toLetter.get(PL[i][0])+""+PL[i][1]+" , ";
			}
				s1=s1+"  ";
			for (int i=0;i<cw1.length();i++)
			{
				s2=s2+C.toLetter.get(PL[SL[i][0]][0])+""+PL[SL[i][0]][1]+" , "+C.toLetter.get(PL[SL[i][1]][0])+""+PL[SL[i][1]][1]+"\n";
			}
			minTextField.setText(""+C.getMinInt());
			seqTextArea.setText(s1+ " \n *************************** \n" + s2);
		    Cursor = 0;
	//	  
	}
	}
	public void actionPerformed(ActionEvent arg0) 
	{
		FirstTime=1;
		String thisSurfaceWord = swTextField.getText();
		String curve1 = fcTextField.getText();

		this.repaint();
	}
}
