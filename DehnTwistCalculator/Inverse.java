/*
 * IGL Project
 * Professor: Spencer Dowdall
 * Graduate Mentor: Joe Nance
 * Undergraduate Students: Yohan Kang, Xiaolong Han, Wenchao Yang
 * This class will print the inverse
 * Sep 30th, 2014 
 */

public class Inverse {
	
	/*
	 * main function:
	 * print the inverse of user's input
	 */
	public static void main(String[] args) {
		//define the var
		String input;
		String output;
		boolean notEnd=true;
		String yesOrNo;//y or n
		
		System.out.println("This program will print the inverse of your input");
		
		while (notEnd){
			//Desrption:			
			System.out.println("Enther in this form: a0B2d1, a1B1C2d3 ...");
		
			//get the input and call the function
			input=TextIO.getlnString();
			output=Inverse.inverse(input);
			
			//Check whether the input is valid
			if(output.indexOf("Wrong")==-1){
				System.out.println("Inverse("+input+")= "+ output);}
			else {
				System.out.println(output);
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
	 * Inverse function
	 * 1. check whether the input is valid
	 * 2. create the inverse output
	 */
	public static String inverse(String s){
		int len=s.length();
		char [] arr=new char [len];
		String res="";
		
		//create the array
		for (int i=0;i<len;i++){
			arr[i]=s.charAt(i);
		}
		
		//check whether the array is in the form we want
		if(len%2==1){return "Wrong input! Enter a new one." ;}
		
		for (int i=0;i<len;i=i+2){
			boolean isChar=Character.isLetter(arr[i]);
			if(!isChar){return "Wrong input! Enter a new one.";}
		}
		
		for(int i=1;i<len;i=i+2){
			boolean isDigit=Character.isDigit(arr[i]);
			if(!isDigit){return "Wrong input! Enter a new one.";}
		}
		
		//get the inverse
		for(int i=len-2;i>=0;i=i-3){
			//if index is even, then it is a character, convert it to lowercase/uppercase
			if(i%2==0){
				if(Character.isLowerCase(arr[i])){
					res=res+Character.toUpperCase(arr[i]);
					i++;
					res=res+arr[i];
				}
				else{
					res=res+Character.toLowerCase(arr[i]);
					i++;
					res=res+arr[i];
				}
			}
			//else: index is odd, then it is a digit
			//else{
			//	res=res+arr[i];
			//	i--;
			//}
				
		}
		
		return res;
	}
	

}
