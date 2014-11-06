/**
 * Nov 3rd, 2014
 */
public class Utilities {
	
	/*
	 * This function will take a string variable as parameter, and return the inverse of the twists
	 * For example, inverse("a0b0c1")=="C1B0A0" is true
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
		if(len==0){throw new RuntimeException( "Empty input! Enter a new one.") ;}
		if(len%2==1){throw new RuntimeException( "Wrong input! Enter a new one.") ;}
		
		for (int i=0;i<len;i=i+2){
			boolean isChar=Character.isLetter(arr[i]);
			if(!isChar){throw new RuntimeException ("Wrong input! Enter a new one.");}
		}
		
		for(int i=1;i<len;i=i+2){
			boolean isDigit=Character.isDigit(arr[i]);
			if(!isDigit){throw new RuntimeException( "Wrong input! Enter a new one.");}
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
			
				
		}
		
		return res;
	}
	
	
	/*
	 * This function will take a string variable as parameter, and return the reverse of the twist
	 * For example, reverse("")=="" is true
	 */
	
	 public static String reverse(String s)
	  {
	    int length=s.length();
	    char [] arr=new char [length];
	    String result="";
	    
	    //Create the array
	    for (int i=0;i<length;i++)
	    {
	      arr[i]=s.charAt(i);
	    }
	    
		//check whether the array is in the form we want
	    if(length==0){throw new RuntimeException( "Empty input! Enter a new one.") ;}
	    
		if(length%2==1){throw new RuntimeException( "Wrong input! Enter a new one.") ;}
		
		for (int i=0;i<length;i=i+2){
			boolean isChar=Character.isLetter(arr[i]);
			if(!isChar){throw new RuntimeException ("Wrong input! Enter a new one.");}
		}
		
		for(int i=1;i<length;i=i+2){
			boolean isDigit=Character.isDigit(arr[i]);
			if(!isDigit){throw new RuntimeException( "Wrong input! Enter a new one.");}
		}
		
		
	    //get the reverse of the twist
	    for(int i=length-2;i>=0;i=i-3)
	    {
	      if(i%2==0)
	      {
	        result=result+arr[i];
	        i++;
	        result=result+arr[i];
	      }
	    }
	    
	    return result;
	  }
	 
	 
	 /*
	  * test case
	  */
	 
	 
	 public static void main(String [] args){
		 //String a="";
		 //String b="test";
		 String c="a0b0c1D1b4A2";
		 String d="c1d2A1d3";
		 
		 //check a b c d respectively
		 System.out.println("Reverse: "+reverse(d));
		 System.out.println("Inverse: "+inverse(d));
		 
	 }
	 

}
