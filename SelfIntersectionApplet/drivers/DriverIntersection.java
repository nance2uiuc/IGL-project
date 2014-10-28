package drivers;

import boundary.*;

public class DriverIntersection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char x='a';
		char y='b';
		String surfaceString = "aAbB";
		SurfaceWord sw = new SurfaceWord(surfaceString);
		String w = "aab";
		String w1 = "abbbAAb";
		
		
		WordVector p = new WordVector(sw.toNumber(w));
		WordVector p1 = new WordVector(sw.toNumber(w1));
		
		int selfint = p.selfIntersection(sw);
		int inter = p.intersection(p1, sw);
		
		System.out.println("Self intersection of "+ w +" is " +selfint);
		System.out.println("Intersection of"+ w+" and " +w1+" is " +selfint);
		
		
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
   