
/*
 * This class will return a randon DehnTwist with the length n in genus g;
 */

public class RandomGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String output;
		System.out.println("Enter the genus, e.g. 3 ...");
		int g=TextIO.getlnInt();
		System.out.println("Enter the length, e.g. 3 ...(If 3, one example is a0b2c2)");
		int n=TextIO.getlnInt();
		
		output=generate(g,n);
		System.out.println(output);

	}
	public static String generate(int g, int n){
		char [] arr=new char [8];
		arr[1]='a';arr[2]='b';arr[3]='c';arr[4]='d';arr[5]='A';arr[6]='B';arr[7]='C';arr[0]='D';
		
		String output="";
		for (int i=0;i<2*n;i++){
			if (i%2==0){
				output=output+arr[(int) (8*Math.random())];
				
			}
			else{
				output=output+(int)(g*Math.random());
			}
		}
		
		return output;
		
	}

}
