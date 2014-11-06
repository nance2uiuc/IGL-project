import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.io.StringReader;

import java.io.*;
import java.util.*;
import pbj.math.graph.*;
import pbj.math.graph.train.*;
import pbj.math.numerical.*;
import gnu.getopt.*;
import java.util.*;
/**
 * Nov 5th, 2014
 */
public class main_calc {

	public static void main(String[] args) throws FileNotFoundException, IOException
    {
	    int genus =  2;
	    int len = 10;
	    
	    //	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

	    for (int x = 0; x < 200; x = x+1) {
		String w = Random.Random(genus, len);
		String o = "";
		o = o+"Word: "+w+" -- ";
		o = o+"Intersections: "+intersection_num.intNum(genus,w)+" -- ";
		o = o+"as DehnTwists: "+Pusher.Push(w)+" -- ";
		o = o+" dilatation: "+ pAextractor.dilatation(genus,Pusher.Push(w));
		System.out.println(o);
	    }	
	}

}
