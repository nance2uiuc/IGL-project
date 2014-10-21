import pbj.math.graph.GraphMap;


public class Distribution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter the genus, e.g. 3 ...");
		int g=TextIO.getlnInt();
		String []s=new String [2];
		s[0]=""+g;
		
		System.out.println("Enter the length, e.g. 3 ...(If 3, one example is a0b2c2)");
		int n=TextIO.getlnInt();
		
		System.out.println("How many times do you want to execute it? e.g. 1000...");
		int times=TextIO.getlnInt();
		
		DilatationCalculator.resetCount();
		
		for (int i=0;i<times;i++){
			String output=RandomGenerator.generate(g,n);
			s[1]=output;
			
			GraphMap graphMap=DilatationCalculator.constructG(s);
			
			DilatationCalculator.dilatation(graphMap);
		}
		
		System.out.println("How many growth rate that we have calculated?\n"+DilatationCalculator.count+"\n");
		System.out.println("What is the proportion?\n"+DilatationCalculator.count/(double)times+"\n");
	}

}
