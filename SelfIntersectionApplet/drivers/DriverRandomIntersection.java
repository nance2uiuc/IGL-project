package drivers;

import boundary.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.System;


public class DriverRandomIntersection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char x='a';
		char y='b';
		String ssw = "aAbB";
		String w;

		try {
		    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    while((w = in.readLine())!= null)
			{
			    si(ssw,w);
			}
		}
		catch (Exception e)
		    {
			e.printStackTrace();
		    }

	}

	 public static void si(String ssw, String w){
		 SurfaceWord sw = new SurfaceWord(ssw);
			int[] vw = sw.toNumber(w);
			WordVector  p = new WordVector(vw);
			p.printSI(sw);
	   }
	 
	 public static int count(String s, char x){
		 int answer=0;
		 for(int h= 0 ; h < s.length() ; h++){
			 if(s.charAt(h)==x) answer++;
		 }
		 return answer;
	 }
}
   