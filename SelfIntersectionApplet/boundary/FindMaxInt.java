package boundary;

import statics.*;

public class FindMaxInt {
	
	
	public static void maxIntPrimitive(int len, int maxSelfInt, String surfaceString){
		SurfaceWord sw = new SurfaceWord(surfaceString);
		System.out.println("Surface word is: "+surfaceString +" Start counting.");
		String filename = surfaceString+"Length"+len;
		int[] total = new int[len+1];
		int maxSI = 0;
		boolean allZero=false;
		
		for(int thisLength = 2 ; thisLength < len ; thisLength++){
			WordVector wn = new WordVector(new int[thisLength]); 
			System.out.println(wn.toLetter(sw)+" "+thisLength);
			maxSI=0;
			while(allZero==false){	
				if(wn.isSmallestAndPrimitive()){
					int si = wn.selfIntersection(sw);
					if(si>maxSI) maxSI = si;
					System.out.println(wn.toLetter(sw)+" "+si+" "+maxSI);
				}
				//GET NEXT
				wn=wn.getNextCyclicallyReduced(sw);
				int[] v = wn.getP();
				allZero=true;
				int u=0;
				while(u<v.length && allZero){
					allZero = allZero && (v[u]==0);	
					u++;
				}
					
			}
			total[thisLength]=maxSI;
			filename = filename+thisLength;
			String legend=surfaceString+"Length"+len+"CycRedWordOFSI "+maxSelfInt+"length between" +len;
			statics.FileStuff.fileVector(total, filename, legend);
		}
		
	}
	
	




}
