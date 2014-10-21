import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.io.StringReader;

import pbj.math.graph.*;
import pbj.math.graph.train.TrainTrack;
import pbj.math.numerical.IntMatrix;

/*
 * This program will use Xtrain and extract the "stretch factor" or "dilatation" from the output in an automated way.
 * Oct 6th,2014
 */

//Remember:
//1. when you use this program, import XTrain source code in your eclipse as an existing project.
//2. right click IGL_Project, click "properties", then "java build path". Choose "Projects" and add XTrain souce code that you imported.

public class DilatationCalculator {
	/**
	 * How many growthRate we have calculated
	 */
	static int count=0;
	
	public static void resetCount(){
		count=0;
	}
	/*
	 * This function will do the dehn twist by using Xtrain
	 */
	public static void main (String [] args) {
		
		//get the genus and twists
		String [] s=new String [2];
		System.out.println("Enter the genus, e.g. 3 ...");
		s[0]=TextIO.getlnString();
		System.out.println("Enter the twists, e.g. d0c0d1c1d2C2 ...");//better description??
		s[1]=TextIO.getlnString();
		
		GraphMap g=constructG(s);
		dilatation(g);
		//Use the program in XTrain: DehnTwist
		//DehnTwist.main(s);
		
		//A GraphMap is contructed a the same time
		//code from XTrain:DehnTwist.java
	}
	/**
	 * Construct the graphMap
	 */
	public static GraphMap constructG(String [] s){ 
		GraphMap g;
		String a,lab,inp,tw;
		int genus;
		StreamTokenizer st;
		////
		try {
			if (s.length==1) {
				st=new StreamTokenizer(new FileReader(s[0]));
				lab=s[0];
			}
			else if (s.length==0) {
				st=new StreamTokenizer(new InputStreamReader(System.in));
				lab="";
			}
			else {
				st=new StreamTokenizer(new StringReader(s[0]+" "+s[1]));
				lab="";
			}

			inp="// input: ";
			if (st.nextToken()==StreamTokenizer.TT_NUMBER) {
				genus=(int) st.nval;
				st.nextToken();
				tw=st.sval;
				if (tw==null)
					throw new RuntimeException("bad sequence of twists");

				st.nextToken();
				if (st.sval!=null)
					lab=st.sval;

				g=DehnTwist.stdGenerators(genus,tw,lab);
				inp=inp+genus+" "+tw+" "+lab;
			}
			else {
				a=st.sval;
				if (a==null)
					throw new RuntimeException("bad boundary word");

				st.nextToken();
				tw=st.sval;
				if (tw==null)
					throw new RuntimeException("bad sequence of twists."+
							"\nDid you use double quotes "+
					"(e.g., \"\'-c(bD)aab\'\")?");

				st.nextToken();
				if (st.sval!=null)
					lab=st.sval;

				if (a.startsWith("fix.")) {
					g=DehnTwist.twistWithFixedWord(a.substring(4),tw,lab);
				}
				else
					g=DehnTwist.twist(a,tw,lab);

				inp=inp+a+" "+tw+" "+lab;
				
			}
		} 
		catch (Exception e) {
			System.err.println(e.toString());
		
			return new GraphMap();
		}
		
		
		return g;
	}
	public static void dilatation(GraphMap g){
		//check whether it is a good map
		
		if (!g.isGoodMap()){
			System.out.println("bad map!");
		
		}
		//if it is a good map, dilatation is computed
		else {
			TrainTrack tt = new TrainTrack(g);
			tt.trainTrackMap();
			
			if (tt.isIrreducible()) {
				System.out.println( "Irreducible: yes, growth rate: "+IntMatrix.PFForm.format(tt.growthRate())+", char poly: "+IntMatrix.polyString(tt.transitionMatrix().reducedCharPoly()));
				//
				count++;
			} else {
				System.out.println(" Irreducible: no");
			}

			//			System.out.println(tt);
			//double q = tt.growthRate();
			//System.out.println("dilatation: "+q);
		}
	
		
	}
	

}
