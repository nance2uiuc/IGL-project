package drivers;
import algorithm_new.*;
import boundary.*;
import java.io.*;

import java.applet.Applet;
import java.awt.*;


public class AlgDriverNew
{
	public static void main(String[] args) 
	{
		/*SurfaceWord sw = new SurfaceWord("aAbB");
		String i = sw.getSurfaceWord();
		System.out.println(i);
		Intersections ii = new Intersections("aAbB","abAb","aaaB");
		System.out.println(ii.si());
		Intersections z = new Intersections("aAbB","aabb");
		System.out.println("glsjg "+z.si());
	*/
	//  prompt the user to enter their name
	/*      System.out.print("Enter the surface word: ");
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	      String surfaceword="";

	      try 
	      {
	         surfaceword = br.readLine();
	      } catch (IOException ioe) 
	      {
	         System.out.println("IO error trying to read the surface word");
	         System.exit(1);
	      }
	      System.out.print("Enter the curve word: ");
	      BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

	      String curveword="";

	      try 
	      {
	         curveword = br1.readLine();
	      } catch (IOException ioe) 
	      {
	         System.out.println("IO error trying to read the curve word");
	         System.exit(1);
	      }
	 */	
		
		
		
		
		
	//change the surface word here
	String s="abAB";
	
	
	
	
	//change the curve word here
	String c="aaBBAAb";
	
	//Tests T=new Tests();

	// T.countLengthSI(30,900,"abAB", true);
/*	NewComputation C = new NewComputation(s,c);

	int[][] PL=C.getPL();
	int[][] SL=C.getSL();
		for (int i=0;i<2*c.length();i++)
		{
			System.out.println(PL[i][0]+" "+PL[i][1]);
		}
		for (int i=0;i<c.length();i++)
		{
			System.out.println(PL[SL[i][0]][0]+" "+PL[SL[i][0]][1]+" , "+PL[SL[i][1]][0]+" "+PL[SL[i][1]][1]);
		}
		while(C.getDone()==0)
		{
			C.findIntersection();
		}
		//*/		
	}
}