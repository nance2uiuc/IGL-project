import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.io.StringReader;

import pbj.math.graph.*;
import pbj.math.graph.train.TrainTrack;



/*
 * This program will use Xtrain and extract the "stretch factor" or "dilatation" from the output in an automated way.
 * Created by Wenchao Yang on Oct 6th,2014
 */

public class XTrain {

	/*
	 * This function will do the dehn twist by using Xtrain
	 */
	public static void main (String [] args) throws FileNotFoundException, IOException{
		
		String [] s=new String [2];
		for (int i =0;i<s.length;i++){
			s[i]=TextIO.getlnString();
		}
		
		
		//DehnTwist is done
		DehnTwist.main(s);
		
		
		GraphMap g= new GraphMap();
		/*
		try{
			if (s.length>0) {
				g.readFromFile(s[0]);
				if (g.getLabel().length()==0)
					g.setLabel(s[0]);
			}
			else
				g.readFromFile("");

		} catch(Exception e) {System.err.println(e.toString());
		return; }

		System.out.println(g.toString());
		System.out.println(g.showVertexMap());*/
		
		//how is g constructed
		TrainTrack tt = new TrainTrack(g);
		tt.trainTrackMap();

//		System.out.println(tt);
		double q = tt.growthRate();
     	System.out.println("dilatation: "+q);
		
		//TrainTrack.main(r);
		
		
		
	}

}
