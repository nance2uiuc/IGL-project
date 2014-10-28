package guis;
import java.applet.Applet;
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

import computations.NewComputationTwo;
import computations.NewComputation;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SelfIntersectionAppletV4 extends JApplet implements ActionListener
{
/* public class SelfIntersectionApplet extends JFrame
   {
*/	public static void main(String args[])
{
	          Frame appletFrame = new Frame("Minimal Intersection Applet");
	          appletFrame.setLayout(new GridLayout(1,0));
	          appletFrame.resize(1000, 650);
	          appletFrame.show();
	          Applet myApplet = new SelfIntersectionAppletV3();
	          appletFrame.add(myApplet);
	          myApplet.init();
	      //    myApplet.start();
	        //  myApplet.resize(1000,650);

	     }

	// ****** NOTE ****** When multiple configurations are realizable, the picture only
	// changes when the segments involved are split (i.e. moved to the end)

	private int FirstTime;
	private JPanel topPanel = new JPanel();
	GraphPanel graphPanel = new GraphPanel();
	//GraphPanel.addWindowListener(new MyWindowListener());
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
	private JLabel fcLabel = new JLabel("Reduced Cyclic Word #1:");
	private JLabel fcLabel2 = new JLabel("Reduced Cyclic Word #2:");
	private JTextField fcTextField = new JTextField(TEXT_FIELD_WIDTH);
	private JTextField fc2TextField=new JTextField(TEXT_FIELD_WIDTH);
	private JLabel min1Label = new JLabel("Min SI #1:");
	private JLabel min2Label = new JLabel("Min SI #2:");
	private JLabel min12Label = new JLabel("Min Int of two curves:");
	private JLabel min12TotLabel = new JLabel("Min Int Total:");

	private JTextField min1TextField = new JTextField(TEXT_FIELD_WIDTH);
	private JTextField min2TextField = new JTextField(TEXT_FIELD_WIDTH);
	private JTextField min12TextField = new JTextField(TEXT_FIELD_WIDTH);
	private JTextField min12TotTextField = new JTextField(TEXT_FIELD_WIDTH);

	//private JLabel seqLabel = new JLabel("Sequences:");
	private JPanel westSouthPanel = new JPanel();
	private JTextArea seqTextArea = new JTextArea(5,5);
	//private JScrollPane seqScrollPane = new JScrollPane(seqTextArea);
	
	private String instructions =
		"This applet computes  minimal " +
			"number of intersections of a pair of free homotopy "+ 
			"classes on a surface with boundary and constructs a representative with this minimal intersection. \n"+
			"Enter a surface word and two reduced cyclic words in the letters of the surface word and hit 'return'. " +
			"  Each arc of the circle represents an edge of the corresponding fundamental polygon. \n Each arc of the circle is separated by a puncture."
			;
	public void init()
	{
		FirstTime=0;
		setSize(1000,650);
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
		GridLayout westNorthLayout = new GridLayout(8,2);
		westNorthPanel.setLayout(westNorthLayout);
		westNorthPanel.add(swLabel);
		westNorthPanel.add(swTextField);
		westNorthPanel.add(fcLabel);
		westNorthPanel.add(fcTextField);
		westNorthPanel.add(fcLabel2);
		westNorthPanel.add(fc2TextField);
		westNorthPanel.add(min1Label);
		westNorthPanel.add(min1TextField);
		westNorthPanel.add(min2Label);
		westNorthPanel.add(min2TextField);
		westNorthPanel.add(min12Label);
		westNorthPanel.add(min12TextField);
		westNorthPanel.add(min12TotLabel);
		westNorthPanel.add(min12TotTextField);
		swTextField.addActionListener(this);
		fcTextField.addActionListener(this);
		fc2TextField.addActionListener(this);
		// WEST-SOUTH
		GridLayout westSouthLayout = new GridLayout(1,2);
		westSouthPanel.setLayout(westSouthLayout);
	//	westSouthPanel.add(seqLabel);
	//	westSouthPanel.add(seqScrollPane);
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
		int[][] SL2;
		int[][] SL1;
		int[][] SLComb;
		//input the surface (only abAB and aAbB work at this point)
		String sw1;
		//input the curve
		String cw1;
		String cw2;
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

			if(FirstTime==0){sw1="abAB";cw1="a";cw2="a";return;}
			else{sw1=swTextField.getText();cw1=fcTextField.getText();cw2=fc2TextField.getText();}
	
			
			//change Size to make the picture bigger/smaller if you'd like.	
			System.out.println(sw1+"  "+cw1+"  "+cw2);
			Size=400;	
			//int PicSize=400;
			this.setSize(Size+50,Size+100);
			
			
			
			NewComputation C1=new NewComputation(sw1,cw1);
			NewComputation C2=new NewComputation(sw1,cw2);
			NewComputationTwo C = new NewComputationTwo(sw1,cw1,cw2);
//commented out for debuging
			//C.printPoints();
			String c1=cw1.toLowerCase();
			String c2=cw2.toLowerCase();
			String c12=c1+cw2.toLowerCase();
			int ia=0;
			int ib=0;
			int totlength=cw1.length()+cw2.length();
			XCurveCoord=new int[2*(cw1.length()+cw2.length())];
			YCurveCoord=new int[2*(cw1.length()+cw2.length())];
		//	System.out.println(c12);
			for (int i=0;i<totlength;i++)
			{
				if (c12.charAt(i)=='a') ia++;
				if (c12.charAt(i)=='b') ib++;
			}	
			//this might be where you need to check for nested bigons
		//	System.out.println(ia+"  "+ib);
			while(C1.getDone()==0)
			{
				//System.out.println("Length: "+C.combLength());
				C1.findIntersection();			
			}
			while(C2.getDone()==0)
			{
				//System.out.println("Length: "+C.combLength());
				C2.findIntersection();			
			}

			while(C.getDone()==0)
			{
				//System.out.println("Length: "+C.combLength());
				C.findIntersection();			
			}
		//	C.easierList();
			System.out.println("******************");
			C.printPoints();
			System.out.println("******************");
			C.printSegments();
			System.out.println("******************");

			//C.printSegments();
			//C.easierList();
			int[][] PL=C.getPL();
			int[][] SL1=C.getSL1();
			int[][] SL2=C.getSL2();
			C.updateSLComb();
			int[][] SLComb=C.getCombSL();
		//	int Cursor; 
			int[] usednum=new int[sw1.length()];
		 // i = 0 is first letter, i = 1 is 2nd letter, etc.	
			int letterholder=PL[0][0];
			
	
			//go back to old method if word = abAB or aAbB
			int PicSize=400;
		//	System.out.println(cw1.length());
			if(sw1.contentEquals("abAB")||sw1.contentEquals("aAbB"))
			{
				//initialize the coordinates for all the lines that will eventually be drawn
				if (sw1.contentEquals("abAB"))
				{
					for(int i=0;i<ia;i++)
					{
						XCurveCoord[i]=20;
						YCurveCoord[i]=(Size-40)*(ia-i)/(ia+1)+40;
						XCurveCoord[2*totlength-ib-1-i]=Size+20;
						YCurveCoord[2*totlength-ib-1-i]=(Size-40)*(ia-i)/(ia+1)+40;
					//	g.fillOval(XCurveCoord[i]-4,YCurveCoord[i]-4,8,8);
					}
				    for(int j=0;j<ib;j++)
				    {
				    	YCurveCoord[j+ia]=20;
				    	XCurveCoord[j+ia]=(Size-40)*(j+1)/(ib+1)+40;
				    	YCurveCoord[2*totlength-1-j]=Size+20;
				    	XCurveCoord[2*totlength-1-j]=(Size-40)*(j+1)/(ib+1)+40;
					//	g.fillOval(XCurveCoord[j+ia]-4,YCurveCoord[j+ia]-4,8,8);

				    }
				}
				if (sw1.contentEquals("aAbB"))
				{
					for(int i=0;i<ia;i++)
					{
						XCurveCoord[i]=20;
						YCurveCoord[i]=(Size-40)*(ia-i)/(ia+1)+40;
						XCurveCoord[ia+i]=(Size-40)*(i+1)/(ia+1)+40;
						YCurveCoord[ia+i]=20;
				    }
				    for(int j=0;j<ib;j++)
				    {
				    	XCurveCoord[2*totlength-ib-1-j]=Size+20;
						YCurveCoord[2*totlength-ib-1-j]=(Size-40)*(ib-j)/(ib+1)+40;
				    	YCurveCoord[2*totlength-1-j]=Size+20;
				    	XCurveCoord[2*totlength-1-j]=(Size-40)*(j+1)/(ib+1)+40;
				    }
				}
				
				g.drawString(""+sw1.charAt(0), 5, 20+Size/2);
				g.drawString(""+sw1.charAt(1), 20+Size/2, 15);
				g.drawString(""+sw1.charAt(2), Size+25, 20+Size/2);
				g.drawString(""+sw1.charAt(3), 20+Size/2, Size+35);
				g.drawString(cw1+" , "+cw2, 20, Size+50);
				g.drawString(""+C.getMinInt()+" intersections total", 20, Size+70);
				double euclength=0.0;
			    for(int k=0;k<totlength;k++)
			    {
			    	//System.out.println(k);
					//g.fillOval(XCurveCoord[SLComb[k][0]]-4,YCurveCoord[SLComb[k][0]]-4,8,8);
					if(k>=cw1.length()){ g.setColor(Color.red);}
			    	g.drawLine(XCurveCoord[SLComb[k][0]],YCurveCoord[SLComb[k][0]],XCurveCoord[SLComb[k][1]],YCurveCoord[SLComb[k][1]]);
			    	int mpx=(XCurveCoord[SLComb[k][0]]+XCurveCoord[SLComb[k][1]])/2;
			    	int mpy=(YCurveCoord[SLComb[k][0]]+YCurveCoord[SLComb[k][1]])/2;
			    	double a=XCurveCoord[SLComb[k][0]];
			    	double b=YCurveCoord[SLComb[k][0]];
			    	double c=XCurveCoord[SLComb[k][1]];
			    	double d=YCurveCoord[SLComb[k][1]];
			    	double atheta=Math.toRadians(45);
			    	double ltheta=Math.atan((d-b)/(c-a));
			    	Double temp1=10*Math.sin(ltheta+Math.PI/2-atheta);
			    	Double temp2=10*Math.cos(ltheta+Math.PI/2-atheta);
			    	//SHOULD HAVE 8 CASES TOTAL
			    if(XCurveCoord[SLComb[k][0]]==20 && ltheta>=0)
			    {
			    	g.drawLine(mpx,mpy,mpx-temp2.intValue(),mpy-temp1.intValue());
			    	g.drawLine(mpx,mpy,mpx-temp1.intValue(),mpy+temp2.intValue());
			    }
			    if(XCurveCoord[SLComb[k][0]]==20 && ltheta<0)
			    {
			    	g.drawLine(mpx,mpy,mpx-temp2.intValue(),mpy-temp1.intValue());
			    	g.drawLine(mpx,mpy,mpx-temp1.intValue(),mpy+temp2.intValue());
			    }
			    if(XCurveCoord[SLComb[k][0]]==Size+20 && ltheta>=0)
			    {
			    	g.drawLine(mpx,mpy,mpx+temp2.intValue(),mpy+temp1.intValue());
			    	g.drawLine(mpx,mpy,mpx+temp1.intValue(),mpy-temp2.intValue());
			    }
			    if(XCurveCoord[SLComb[k][0]]==Size+20 && ltheta<0)
			    {
			    	g.drawLine(mpx,mpy,mpx+temp2.intValue(),mpy+temp1.intValue());
			    	g.drawLine(mpx,mpy,mpx+temp1.intValue(),mpy-temp2.intValue());
			    }
			    if(YCurveCoord[SLComb[k][0]]==20 && ltheta>=0)
			    { 
			    	g.drawLine(mpx,mpy,mpx-temp2.intValue(),mpy-temp1.intValue());
			    	g.drawLine(mpx,mpy,mpx-temp1.intValue(),mpy+temp2.intValue());
			    }
			    if(YCurveCoord[SLComb[k][0]]==20 && ltheta<0)
			    { 
			    	g.drawLine(mpx,mpy,mpx+temp2.intValue(),mpy+temp1.intValue());
			    	g.drawLine(mpx,mpy,mpx+temp1.intValue(),mpy-temp2.intValue());
			    }
			    if(YCurveCoord[SLComb[k][0]]==Size+20 && ltheta>=0)
			    {
			    	g.drawLine(mpx,mpy,mpx+temp2.intValue(),mpy+temp1.intValue());
			    	g.drawLine(mpx,mpy,mpx+temp1.intValue(),mpy-temp2.intValue());
			    }
			    if(YCurveCoord[SLComb[k][0]]==Size+20 && ltheta<0)
			    {
			    	g.drawLine(mpx,mpy,mpx-temp2.intValue(),mpy-temp1.intValue());
			    	g.drawLine(mpx,mpy,mpx-temp1.intValue(),mpy+temp2.intValue());
			    }	
			   }
			    int[] XArray = {20, 40, PicSize, 20+PicSize, 20+PicSize, PicSize,     40, 20};
			    int[] YArray = {40, 20, 20,   40,      PicSize,    20+PicSize, PicSize+20,PicSize   };
			    g.setColor(Color.black);
			    g.drawPolygon (XArray, YArray, 8);	  
				g.fillOval(XCurveCoord[SLComb[0][0]]-4,YCurveCoord[SLComb[0][0]]-4,8,8);
			}
			
			
			
	if(!(sw1.contentEquals("abAB")||sw1.contentEquals("aAbB")))
	{
	
			//attempting to draw circle
			 int[] Circle1X=new int[sw1.length()];
			 int[] Circle1Y=new int[sw1.length()];
			 int[] Circle2X=new int[sw1.length()];
			 int[] Circle2Y=new int[sw1.length()];
			 int[] CircleDivX=new int[sw1.length()];
			 int[] CircleDivY=new int[sw1.length()];
			    for(int i=0;i<sw1.length();i++)
			    {
			    	Circle1X[i]= 20+Math.round(Size/2+Math.round((Size/2-10)*Math.cos(Math.PI+2*Math.PI*i/(sw1.length()))));
			    	Circle1Y[i]= 20+Math.round(Size/2+Math.round((Size/2-10)*Math.sin(Math.PI+2*Math.PI*i/(sw1.length()))));
			    	Circle2X[i]= 20+Math.round(Size/2+Math.round((10+Size/2)*Math.cos(Math.PI+2*Math.PI*i/(sw1.length()))));
			    	Circle2Y[i]= 20+Math.round(Size/2+Math.round((10+Size/2)*Math.sin(Math.PI+2*Math.PI*i/(sw1.length()))));
			    	CircleDivX[i]=20+Math.round(Size/2+Math.round((Size/2)*Math.cos(Math.PI+2*Math.PI*i/(sw1.length()))));
			    	CircleDivY[i]=20+Math.round(Size/2+Math.round((Size/2)*Math.sin(Math.PI+2*Math.PI*i/(sw1.length()))));
			    //	g.drawLine(20+Size/2,  20+Size/2,  CircleDivX[i],CircleDivY[i]);
			    	//	g.drawPolygon(CircleX,CircleY,sw1.length());
			   	g.drawString(""+sw1.charAt(i),20+Math.round(Size/2+Math.round((Size/2+10)*Math.cos(2*Math.PI/(2*sw1.length())+Math.PI+2*Math.PI*i/(sw1.length())))),20+Math.round(Size/2+Math.round((Size/2+10)*Math.sin(2*Math.PI/(2*sw1.length())+Math.PI+2*Math.PI*i/(sw1.length())))));
			    //	System.out.println(CircleX[i]+" , "+CircleY[i]);
			    }
			    
			    for(int i=0;i<sw1.length();i++)
			    {
			    //	g.drawLine(CircleX[i-1],CircleY[i-1],CircleX[i],CircleY[i]);
			    	g.drawOval(20,20,Size,Size);
			    	
			    	g.drawLine(Circle1X[i],Circle1Y[i],Circle2X[i],Circle2Y[i]);
			   // 	System.out.println(Circle1X[i]+" , "+Circle1Y[i]+" , "+Circle2X[i]+" , "+Circle2Y[i]);
			
			    }
			  //  int[] PointsonEdge=new int[cw1.length()*2];
			 //   g.drawLine(CircleX[sw1.length()-1],CircleY[sw1.length()-1],CircleX[0],CircleY[0]);
			//
			
		//	System.out.println("why is it running paint twice?");
		
			    
			    
	/*		   NewComputation C = new NewComputation(sw1,cw1);
			String c1=cw1.toLowerCase();
			int ia=0;
			int ib=0;
			XCurveCoord=new int[2*totlength];
			YCurveCoord=new int[2*totlength];
			
			for (int i=0;i<c1.length();i++)
			{
				if (c1.charAt(i)=='a') ia++;
				if (c1.charAt(i)=='b') ib++;
			}
			*/
	/*		    while(C1.getDone()==0)
				{
					//System.out.println("Length: "+C.combLength());
					C1.findIntersection();			
				}
				while(C2.getDone()==0)
				{
					//System.out.println("Length: "+C.combLength());
					C2.findIntersection();			
				}

				while(C.getDone()==0)
				{
					//System.out.println("Length: "+C.combLength());
					C.findIntersection();			
				}    
			*/	
			
			//	C.updateSLComb();
			//	SLComb=C.getCombSL();
			//	int Cursor; 
			//	usednum=new int[sw1.length()];
			 // i = 0 is first letter, i = 1 is 2nd letter, etc.	
				// letterholder=PL[0][0];
		/*	    System.out.println("+++++++++++++++++TEST++++++++++++++");
				 for (int j=0;j<2*(cw1.length()+cw2.length());j++)
					{
						System.out.println(C.toLetter.get(PL[j][0])+""+PL[j][1]);
					}
				 System.out.println("+++++++++++++++++TEST++++++++++++++");		 
			*/	 
			    C.easierList();
			for (int i=0;i<2*totlength;i++)
			{
				//need to know whether an inverse letter has been "used up" already
				if(PL[i][0]!=letterholder)
				{
				//	System.out.println("switching letters/sides");
					usednum[letterholder]=1;
					letterholder=PL[i][0];
				}
				
				//System.out.println(1.0*(1+PL[i][1])/(C.getNumPoints(PL[i][0])+2)*2*Math.PI/(sw1.length()));
				if(usednum[C.barN.get(PL[i][0])]==0)
				{
				//	System.out.println("fresh letter, has never been used");
				//	System.out.println((1+PL[i][1])/(C.getNumPoints(PL[i][0])+1)*2*Math.PI/(sw1.length())+ Math.PI+2*Math.PI*PL[i][0]/(sw1.length()));
					XCurveCoord[i]=20+Math.round(Size/2+Math.round(Size/2*Math.cos(1.0*(1+PL[i][1])/(C.getNumPoints(PL[i][0])+1)*2*Math.PI/(sw1.length())+ Math.PI+2*Math.PI*PL[i][0]/(sw1.length()))));
					YCurveCoord[i]=20+Math.round(Size/2+Math.round(Size/2*Math.sin(1.0*(1+PL[i][1])/(C.getNumPoints(PL[i][0])+1)*2*Math.PI/(sw1.length())+ Math.PI+2*Math.PI*PL[i][0]/(sw1.length()))));
				//	System.out.println(C.toLetter.get(PL[i][0])+""+PL[i][1]);
				//	g.drawString(C.toLetter.get(PL[i][0])+" "+PL[i][1], XCurveCoord[i], YCurveCoord[i]);
				}
				else {
					//words aB and c create an issue in this section.
				//	System.out.println((C.getNumPoints(PL[i][0])-PL[i][1])/(C.getNumPoints(PL[i][0])+1)*2*Math.PI/(sw1.length())+ Math.PI+2*Math.PI*PL[i][0]/(sw1.length()));

					XCurveCoord[i]=20+Math.round(Size/2+Math.round(Size/2*Math.cos(1.0*(C.getNumPoints(PL[i][0])-PL[i][1])/(C.getNumPoints(PL[i][0])+1)*2*Math.PI/(sw1.length())+ Math.PI+2*Math.PI*PL[i][0]/(sw1.length()))));
					YCurveCoord[i]=20+Math.round(Size/2+Math.round(Size/2*Math.sin(1.0*(C.getNumPoints(PL[i][0])-PL[i][1])/(C.getNumPoints(PL[i][0])+1)*2*Math.PI/(sw1.length())+ Math.PI+2*Math.PI*PL[i][0]/(sw1.length()))));
				//	System.out.println(C.toLetter.get(PL[i][0])+""+PL[i][1]);
				//	g.drawString(C.toLetter.get(PL[i][0])+" "+PL[i][1], XCurveCoord[i], YCurveCoord[i]);
					//	System.out.println("figuring out");
				}				
				//	System.out.println(XCurveCoord[i]+"  *  "+YCurveCoord[i]);
			}
			//System.out.println("*****************");
			
			g.fillOval(XCurveCoord[SL1[0][0]]-4,YCurveCoord[SL1[0][0]]-4,8,8);	
			for(int k=0;k<totlength;k++)  //should be totlength
		    {
				if(k<cw1.length()){g.setColor(Color.black);}
				if(k>=cw1.length()){ g.setColor(Color.red);}
		    	g.drawLine(XCurveCoord[SLComb[k][0]],YCurveCoord[SLComb[k][0]],XCurveCoord[SLComb[k][1]],YCurveCoord[SLComb[k][1]]);
		    	int mpx=(XCurveCoord[SLComb[k][0]]+XCurveCoord[SLComb[k][1]])/2;
		    	int mpy=(YCurveCoord[SLComb[k][0]]+YCurveCoord[SLComb[k][1]])/2;
		    	double a=XCurveCoord[SLComb[k][0]];
		    	double b=YCurveCoord[SLComb[k][0]];
		    	double c=XCurveCoord[SLComb[k][1]];
		    	double d=YCurveCoord[SLComb[k][1]];
		    	double atheta=Math.toRadians(45);
		    	double ltheta=Math.atan((d-b)/(c-a));
		    	Double temp1=10*Math.sin(ltheta+Math.PI/2-atheta);
		    	Double temp2=10*Math.cos(ltheta+Math.PI/2-atheta);
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
		/*	for(int k=0;k<cw1.length();k++)
			    {
			    	g.drawLine(XCurveCoord[SL1[k][0]],YCurveCoord[SL1[k][0]],XCurveCoord[SL1[k][1]],YCurveCoord[SL1[k][1]]);
			    }
		*/
			
	}
	
		    String s1="";
			String s2="";
			for (int i=0;i<2*totlength;i++)
			{
				s1=s1+C.toLetter.get(PL[i][0])+""+PL[i][1]+" , ";
			}
				s1=s1+"  ";
			for (int i=0;i<cw1.length();i++)
			{
				s2=s2+C.toLetter.get(PL[SL1[i][0]][0])+""+PL[SL1[i][0]][1]+" , "+C.toLetter.get(PL[SL1[i][1]][0])+""+PL[SL1[i][1]][1]+"\n";
			}
			min1TextField.setText(""+C1.getMinInt());
			min2TextField.setText(""+C2.getMinInt());
			min12TotTextField.setText(""+C.getMinInt());
			int just2=C.getMinInt()-C1.getMinInt()-C2.getMinInt();
			min12TextField.setText(""+just2);

			seqTextArea.setText(s1+ " \n *************************** \n" + s2);

			//it seems like all permutations give rise to the same length -> can this be provven easily?
		/*	double totlength=0.0;
			for (int i=0;i<cw1.length();i++)
			{
				totlength=totlength+Math.sqrt(Math.pow((XCurveCoord[i]*1.0),2)+Math.pow(YCurveCoord[i]*1.0,2));
			}
			//System.out.println(Math.pow(3, 2));
			System.out.println(totlength);
		*/
		}
		
	}
		
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		FirstTime=1;
		String thisSurfaceWord = swTextField.getText();
		String curve1 = fcTextField.getText();
		this.repaint();
	}
	private class MyWindowListener implements WindowListener {

		  public void windowClosing(WindowEvent arg0) {
		    System.exit(0);
		  }

		  public void windowOpened(WindowEvent arg0) {
		  }

		  public void windowClosed(WindowEvent arg0) {
		  }

		  public void windowIconified(WindowEvent arg0) {
		  }

		  public void windowDeiconified(WindowEvent arg0) {
		  }

		  public void windowActivated(WindowEvent arg0) {
		  }

		  public void windowDeactivated(WindowEvent arg0) {
		  }

	}
	
	
}