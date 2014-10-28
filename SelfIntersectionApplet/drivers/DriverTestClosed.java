package drivers;

import boundary.*;

public class DriverTestClosed {
	public static void main(String[] args) {
		
		String stw = "aBcDAbCd";
		
		String v1= "aBcD";
		String v2="DcBa";
		String  v = "c"+v1+"ccdd"+v2+"c";
		String  w = "c"+v1+"ccdd"+v2+"c";

		SurfaceWord sw = new SurfaceWord(stw);
		int [] vv = sw.toNumber(v);
		int [] ww = sw.toNumber(w);
		
		WordVector www = new WordVector(ww);
		WordVector vvv = new WordVector(vv);
		
		System.out.println(www.selfIntersection(sw));
		System.out.println(vvv.selfIntersection(sw));
		
		
		int inter = vvv.intersection(www, sw);
		
		System.out.println(inter);
		
		
	}
}
