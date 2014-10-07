/**
 * IGL Project
 * Dehn Twist
 * Sep 30th, 2014 
 */
/**
 * 1.T(ai) is written as ai
 * 2.T-1(ai) is written as Ai (-1 is the superscript)
 * 3.Previous gamma is written as z 
 * 4.Previous delta is written as y
 * 5.Define a helper line s
 */
public class DehnTwist_IGL {
	/**
	 * 1.Given si, show its Dehn Twist
	 * 2.Given zi, show its Dehn Twist
	 * 3.Given push(zi), show its Dehn Twist     
	 * 4.Given yi, show its Dehn Twist
	 * 5.Given push(yi), show its Dehn Twist     
	 * 
	 */
	public static void main(String[] args) {
				
		boolean notEnd=true; //whether continue
		String input;// the number from user
		String INPUT;
		String output;//Dehn Twist Output
		String yesOrNo;//y or n
		boolean valid=true;
		
		while(notEnd){
			//get the number from user
			System.out.println("Please enter in this form:[line]+[digit]+[L/R], e.g. \ns2, z2L, z3R, push(z3), y2R, y3L, push(y2) ...");
			input=TextIO.getlnString();       //lowercase input
			INPUT=input.toUpperCase();        //UPPERCASE input
			
			//check whether the input is valid
			valid=DehnTwist_IGL.validInput(input);
			
			//our output
			if (!valid){
				System.out.println("Wrong input! Enter a new one!");
			}
			
			else {
				//which function we should call
				
				//if the input is in the form "y3l", call y_l(s)
				if (INPUT.charAt(0)=='Y'&&INPUT.charAt(2)=='L'){
					output=y_l(input);
				}
				
				//if the input is in the form "z2R", call z_r(s)
				else if(INPUT.charAt(0)=='Z'&&INPUT.charAt(2)=='R'){
					output=z_r(input);
				}
				//if the input is in the form "s2", call s_(str)
				else if(INPUT.charAt(0)=='S'){
					output=s_(input);
				}
				
				//if the input is in the form of "z2l", call z_l(s)
				else if(INPUT.charAt(0)=='Z'&&INPUT.charAt(2)=='L'){
					output=z_l(input);
				}
				
				//if the input is in the form of "y2r", call y_r(s);
				else if(INPUT.charAt(0)=='Y'&&INPUT.charAt(2)=='R'){
					output=y_r(input);
				}
				
				//if the input is in the form of "push(z2)", call push_z(s);
				else if(INPUT.indexOf("PUSH(Z")>=0){
					output=push_z(input);////////////
				}
				
				//if the input is in the form of "push(y2)", call push_y(s);
				else if(INPUT.indexOf("PUSH(Y")>=0){
					output=push_y(input);
				}
				
				else {
					output="Sorry, this part is not completed. Or, there is sth wrong. Contact me";
				}
			
				System.out.println("Output: \n" +output);
			}
			//whether continue
			System.out.println("Continue? Y / N");
			yesOrNo=TextIO.getlnString();
		
			//end this program if the user enters 'N'
			if (yesOrNo.equalsIgnoreCase("N")){
				notEnd=false;
			}
			
			//Print copyright info if this is the end
			if(!notEnd){
				System.out.println("Thanks for using this program. \nIGL Project\nCopyright:\nProfessor: Spencer Dowdall \nGraduate Mentor: Joe Nance" +
						"\nUndergraduate Students: Yohan Kang, Xiaolong Han, Wenchao Yang");
			}
		}
	
		
	}
	/**
	 * This function is used to check whether the input is valid
	 * All possible input: s2, z2L, z3R, push(z3), y2R, y3L, push(y2)...
	 */
	public static boolean validInput(String s){
		String S=s.toUpperCase();
		if(S.indexOf("PUSH(")>=0){
			if(S.charAt(7)==')'&&(S.charAt(5)=='Y'||S.charAt(5)=='Z')&&(Character.isDigit(S.charAt(6)))){
				return true;
			}
			else {return false;}
		}
		else {
			if(S.charAt(0)=='S'&&S.length()==2&&Character.isDigit(S.charAt(1))){
				return true;
			}
			else if((S.charAt(0)=='Z'||S.charAt(0)=='Y')&&(Character.isDigit(S.charAt(1)))&&
					(S.charAt(2)=='R'||S.charAt(2)=='L')){
				return true;
			}
			else {
				return false;
			}
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
	public static String s_(String str){
		int i=str.charAt(1)-'0';
		String front="A"+i+"a"+(i+1)+"d"+(i+1)+"c"+i;
		String back=Inverse.inverse(front);
		return front+"d"+i+back;
	}
	
	//if the input is in the form of "z2l", call z_l(s)
	public static String z_l(String s){
		
		int i=s.charAt(1)-'0';
		String output=DehnTwist_IGL.z_l(s, i);
		return output;
	}
	
	//helper function of z_l(s)
	public static String z_l(String s, int i){
		if(i==0)return "a0";
		String front=DehnTwist_IGL.s_("s"+(i-1))+"C"+(i-1)+"D"+(i-1);
		String back=Inverse.inverse(front);
		return front+DehnTwist_IGL.z_l("s"+(i-1), i-1)+back;
	}
	
	//if the input is in the form of "y2r", call y_r(s);
	public static String y_r(String s){
		int i=s.charAt(1)-'0';
		String output=DehnTwist_IGL.y_r(s, i);
		return output;
	}
	
	//helper function of y_r(s)
	public static String y_r(String s, int i){
		if(i==0)return "B0a0d0A0b0";
		String front="c"+(i-1)+"d"+(i-1)+"d"+i+"c"+(i-1);
		String back=Inverse.inverse(front);
		return front+DehnTwist_IGL.y_r("y"+(i-1)+"r", i-1)+back;
	}
	
	//if the input is in the form of "push(z2)", call push_z(s);
	public static String push_z(String s){
		int i=s.charAt(6)-'0';
		if(i==0) return "b0A0";
		String si=DehnTwist_IGL.s_("s"+(i-1));
		String zil=DehnTwist_IGL.z_l("z"+(i-1)+"l");
		String i_zil=Inverse.inverse(zil);
		return "b"+i+si+"C"+(i-1)+"D"+(i-1)+i_zil+"d"+(i-1)+"c"+(i-1)+si;
	
	}
	
	//if the input is in the form of "push(y2)", call push_y(s);
	public static String push_y(String s){
		int i=s.charAt(6)-'0';
		if(i==0) return "B0a0d0A0b0D0";
		//String si=DehnTwist_IGL.s_("s"+(i-1));
		String yir=DehnTwist_IGL.y_r("y"+(i-1)+"r");
		//String i_zil=Inverse.inverse(yir);
		return "c"+(i-1)+"d"+(i-1)+"d"+i+"c"+(i-1)+yir+"C"+(i-1)+"D"+i+"D"+
				(i-1)+"C"+(i-1)+"D"+i;
	
	}
	

}
