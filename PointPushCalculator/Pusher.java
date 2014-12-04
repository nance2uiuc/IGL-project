
/**
 * Nov 5th, 2014
 */
public class Pusher {
	/**
	 * @param input
	 * Input: input (3-char string, e.g. z2L, z3R, y2R, y3L,...)
	 * Attention: Genus could not be more than 9, for example using "z10L" as input would be invalid
	 * Output: DehnTwist of input in order for Xtrain, which is in reverse order of the output from DehnTwist_IGL.java
	 */
	public static String PtwGtw (String input){
		String res="";
		
		//check whether the input is valid. If not, exception will be thrown.
		checkValid_PtwGtw(input);
		
		String INPUT=input.toUpperCase();
		
		//if the input is in the form "y3l", call y_l(s)
		if (INPUT.charAt(0)=='Y'&&INPUT.charAt(2)=='L'){
			res= y_l(input);
		}
		
		//if the input is in the form "z2R", call z_r(s)
		else if(INPUT.charAt(0)=='Z'&&INPUT.charAt(2)=='R'){
			res= z_r(input);
		}
		
		//if the input is in the form of "z2l", call z_l(s)
		else if(INPUT.charAt(0)=='Z'&&INPUT.charAt(2)=='L'){
			res= z_l(input);
		}
		
		//if the input is in the form of "y2r", call y_r(s);
		else if(INPUT.charAt(0)=='Y'&&INPUT.charAt(2)=='R'){
			res= y_r(input);
		}
		
		else throw new RuntimeException("Wrong input!");
		
		return Utilities.reverse(res);
		
	}
	
	//Check whether the input is valid for the function PtwGtw. Exception will be thrown is it is not valid input. 
	public static void checkValid_PtwGtw(String input){
		input=input.toUpperCase();
		
		if(input.length()==0){
			throw new RuntimeException("Empty input.");
		}
		
		if(input.length()!=3){
			throw new RuntimeException("Input is not a 3-char string.");
		}
		
		if(!   ((input.charAt(0)=='Z'||input.charAt(0)=='Y')&&(Character.isDigit(input.charAt(1)))&&
				(input.charAt(2)=='R'||input.charAt(2)=='L'))   ){
			throw new RuntimeException("Input is not in the correct form.");
		}
	}
	
	//if the input is in the form "y3l", call y_l(s)
	public static String y_l(String s){
		int i=s.charAt(1)-'0';
		return "d"+i;
	}
	
	//if the input is in the form "z2R", call z_r(s)
	public static String z_r(String s){
		int i=s.charAt(1)-'0';
		return "b"+i;
	}
	
	//if the input is in the form "s2", call s_(str)
	//Helper line
	public static String s_(String str){
		int i=str.charAt(1)-'0';
		String front="A"+i+"a"+(i+1)+"d"+(i+1)+"c"+i;
		String back=Utilities.inverse(front);
		return front+"d"+i+back;
	}
	
	//if the input is in the form of "z2l", call z_l(s)
	public static String z_l(String s){
		
		int i=s.charAt(1)-'0';
		String output=z_l(s, i);
		return output;
	}
	
	//helper function of z_l(s)
	public static String z_l(String s, int i){
		if(i==0)return "a0";
		String front=s_("s"+(i-1))+"C"+(i-1)+"D"+(i-1);
		String back=Utilities.inverse(front);
		return front+z_l("s"+(i-1), i-1)+back;
	}
	
	//if the input is in the form of "y2r", call y_r(s);
	public static String y_r(String s){
		int i=s.charAt(1)-'0';
		String output=y_r(s, i);
		return output;
	}
	
	//helper function of y_r(s)
	public static String y_r(String s, int i){
		if(i==0)return "B0a0d0A0b0";
		String front="c"+(i-1)+"d"+(i-1)+"d"+i+"c"+(i-1);
		String back=Utilities.inverse(front);
		return front+y_r("y"+(i-1)+"r", i-1)+back;
	}
	
	/**
	 * @param s
	 * Input: s, e.g, z0, y2, z0z1Y2y0Z1, ...
	 * If s.length()==2, return PtwGtw(s+"R")+ Utilities.inverse(PtwGtw(s+"L"));
	 * If s.length()>=2, such as s="z0z1Y2", it will return push("z0")+push("z1")+push("Y2");
	 */
	public static String Push(String s){
		//check whether the input is valid. If not, exception will be thrown.
		checkValid_push(s);
		
		//if s is in the form of something like "z0"
		if(s.length()==2){
			return PtwGtw(s+"R")+Utilities.inverse(PtwGtw(s+"L"));
		}
		else {
			String res="";
			
			for (int i=0;i<s.length();i=i+2){
				String input= s.substring(i, i+2);//The substring begins at beginIndex and extends to character at index endIndex - 1 
				res=res+Push(input);
			}
			return res;
		}
		
	}
	
	//Check whether the input is valid for the function push. Exception will be thrown is it is not valid input.
	public static void checkValid_push(String input){
		if(input.length()==0){
			throw new RuntimeException("Empty input.");
		}
		if(input.length()%2==1){
			throw new RuntimeException("Length of input shouldn't be odd.");
		}

		for (int i=0;i<input.length();i=i+2){
			if(! ( Character.isDigit(input.charAt(i+1))&&Character.isLetter(input.charAt(i)) ) ){
				throw new RuntimeException("Input is not in the correct form.");
			}
				
		}
		
		
	}
	
	/**
	 * @param args
	 * Test case
	 */
	public static void main(String[] args) {
		
		
		//Test for function PtwGtw
	
		//Exception should be thrown
		//String a="";		PtwGtw(a);
		//String b="test";	PtwGtw(b);
		//String c="z10L";	PtwGtw(c);
		
		
		//
		//System.out.println("If you want to test function PtwGtw, DehnTwist_IGL.java, Inverse.java, TextIO.java is needed!");
		/*String []test =new String [8];
		test[0]="z0l";
		test[1]="z0r";
		test[2]="z1l";
		test[3]="z1r";
		
		test[4]="y0l";
		test[5]="y0r";
		test[6]="y1l";
		test[7]="y1r";
		
		System.out.println("Output from PtwGtw should be in reverse order of output from DehnTwist_IGL");
		for(int i=0;i<test.length;i++){
			System.out.println("Input: "+test[i]);
			//System.out.println("DehnTwist_IGL: "+DehnTwist_IGL.call(test[i]));
			System.out.println("Output from PtwGtw: "+PtwGtw(test[i])+"\n");
		}
		
		*/
		//test for function Push
		//Exception should be thrown
		//String a="";		Push(a);
		//String b="test";	Push(b);
		//String c="z0y1z10";	Push(c);
		//String d="z0Y1y2yy";Push(d);
		//String e="z0y";		Push(e);
		
		//
		String []test2 =new String [7];
		test2[0]="z0";
		test2[1]="z1";
		test2[2]="y0";
		test2[3]="y1";
		
		test2[4]="z0y0";
		test2[5]="z0z1Y2y0Z1";
		test2[6]="Y0y2z2z1";
		
		for(int i=0;i<test2.length;i++){
			System.out.println("Input: "+test2[i]);
			System.out.println("Output from Push: "+Push(test2[i])+"\n");
		}
		
	}

}
