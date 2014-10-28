package boundary;


public class Tests {




	/**
	 * Counts primitives. Produces length-SI matrix and max SI. and MaxSI.
	 * @param maxLength
	 * @param maxSelfInt
	 * @param surfaceString
	 */
	public static void countPrimitivesLengthSI(int maxLength, int maxSelfInt, String surfaceString){
		String dir = surfaceString+"primitives/";
		statics.CreateDirectory.create(dir);
		
		int[][] total = new int[maxLength][maxSelfInt];
		int[] totalMaxSI = new int[maxLength];
		SurfaceWord sw = new SurfaceWord(surfaceString);
		System.out.println("Surface word is: "+surfaceString +" Start counting.");
		String filename ;
		String filenameSI;
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestAndPrimitive() ){
					int si = wn.selfIntersection(sw);
					total[len][si]++;
					if (totalMaxSI[len]<si) totalMaxSI[len]=si;
					//System.out.println(wn.toLetter(sw)+" "+si);
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
			filename = dir+surfaceString+len;
			 filenameSI=filename+"total"+len;
			String legend=surfaceString+" Length including ONLY primitives";
			//file results (total, filename, legend);
			int[][] total1 = new int[len][maxSelfInt];
			for(int l = 1 ; l <=len ; l++){
				total1[l-1]=total[l];
			}
			
			int[][] total2 = new int[2][len];
			for(int l = 0 ; l <len ; l++){
				total2[0][l]=l;
				total2[1][l]=totalMaxSI[l];
				
				
				
			}
			statics.FileStuff.fileMatrix(total1, filename, "");
			statics.FileStuff.fileMatrix(total2, filenameSI, "");
		}	
		
	}
	
	
	
	
	/**
	 * Counts primitives. Produces length-SI matrix and max SI. and MaxSI.
	 * @param maxLength
	 * @param maxSelfInt
	 * @param surfaceString
	 */
	public static void countPrimitivesLengthSI_with_a(int minlength,int maxLength, int maxSelfInt, String surfaceString){
		String dir = surfaceString+"primitives/";
		statics.CreateDirectory.create(dir);
		
	//	int[][] total = new int[maxLength][maxSelfInt];
		int[] totalMaxSI = new int[maxLength];
		SurfaceWord sw = new SurfaceWord(surfaceString);
		System.out.println("Surface word is: "+surfaceString +" Start counting.");
		String filename ;
		String filenameSI;
		boolean allZero=false;
		for(int len = minlength ; len < maxLength ; len++){
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false & wn.firstisa()){	
			if(wn.isSmallestAndPrimitive() ){
					int si = wn.selfIntersection(sw);
			//		total[len][si]++;
					if (totalMaxSI[len]<si) totalMaxSI[len]=si;
				//	System.out.println(wn.toLetter(sw)+" "+si);
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
			filename = dir+surfaceString+len;
			 filenameSI=filename+"total"+len;
			String legend=surfaceString+" Length including ONLY primitives";
			//file results (total, filename, legend);
	//		int[][] total1 = new int[len][maxSelfInt];
		
			
			int[][] total2 = new int[2][len];
			for(int l = 0 ; l <len ; l++){
				total2[0][l]=l+1;
				total2[1][l]=totalMaxSI[l+1];
				
				
				
			}
		//	statics.FileStuff.fileMatrix(total1, filename, "");
			statics.FileStuff.fileMatrix(total2, filenameSI, "");
		}	
		
	}
	
	
	
	/**
	 * Counts number of classes interseting in each intersection number wth a givnen class. 
	 * Produces length-SI matrix and max SI. and MaxSI.
	 * @param maxLength
	 * @param maxSelfInt
	 * @param surfaceString
	 */
	public static void countIntersectionLengthSI(int maxLength, int maxSelfInt, String surfaceString, String curve){
		String dir = surfaceString+"countingIntersections_with_"+curve+"/";
		statics.CreateDirectory.create(dir);
		
		int[][] total = new int[maxLength][maxSelfInt];
		int[] totalMaxSI = new int[maxLength];
		
		SurfaceWord sw = new SurfaceWord(surfaceString);
		WordVector cur= new WordVector(sw.toNumber(curve));
		System.out.println("Surface word is: "+surfaceString +" Start counting.");
		String filename ;
		String filenameSI;
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestAndPrimitive() ){
					int si = wn.intersection(cur, sw);
					//if(si==4) System.out.println(wn.toLetter(sw));
					total[len][si]++;
					if (totalMaxSI[len]<si) totalMaxSI[len]=si;
					//System.out.println(wn.toLetter(sw)+" "+si);
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
			filename = dir+surfaceString+len+"_"+curve;
			 filenameSI=filename+"total"+len+curve;
			String legend=surfaceString+" Length including ONLY primitives";
			//file results (total, filename, legend);
			int[][] total1 = new int[len][maxSelfInt];
			for(int l = 1 ; l <=len ; l++){
				total1[l-1]=total[l];
			}
			
			int[][] total2 = new int[2][len];
			for(int l = 0 ; l <len ; l++){
				total2[0][l]=l;
				total2[1][l]=totalMaxSI[l];
				
				
				
			}
			statics.FileStuff.fileMatrix(total1, filename, "");
			statics.FileStuff.fileMatrix(total2, filenameSI, "");
		}	
		
	}
	
	
	/**
	 * Counts number of classes interseting in each intersection number wth a givnen class. 
	 * Produces length-SI matrix and max SI. and MaxSI.
	 * @param maxLength
	 * @param maxSelfInt
	 * @param surfaceString
	 */
	public static void countIntersectionSimples(int maxLength, int maxSelfInt, String surfaceString, String curve){
		String dir = surfaceString+"simples_with_"+curve+"/";
		statics.CreateDirectory.create(dir);
		
		int[][] total = new int[maxLength][maxSelfInt];
		int[] totalMaxSI = new int[maxLength];
		
		SurfaceWord sw = new SurfaceWord(surfaceString);
		WordVector cur= new WordVector(sw.toNumber(curve));
		System.out.println("Surface word is: "+surfaceString +" Start counting.");
		String filename ;
		String filenameSI;
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestAndPrimitive() & wn.selfIntersection(sw)==0){
					int si = wn.intersection(cur, sw);
					total[len][si]++;
					if (totalMaxSI[len]<si) totalMaxSI[len]=si;
					//System.out.println(wn.toLetter(sw)+" "+si);
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
			filename = dir+surfaceString+len;
			 filenameSI=filename+"total"+len;
			String legend=surfaceString+" Length including ONLY primitives";
			//file results (total, filename, legend);
			int[][] total1 = new int[len][maxSelfInt];
			for(int l = 1 ; l <=len ; l++){
				total1[l-1]=total[l];
			}
			
			int[][] total2 = new int[2][len];
			for(int l = 0 ; l <len ; l++){
				total2[0][l]=l;
				total2[1][l]=totalMaxSI[l];
				
				
				
			}
			statics.FileStuff.fileMatrix(total1, filename, "");
			statics.FileStuff.fileMatrix(total2, filenameSI, "");
		}	
		
	}
	
	

	
	/**
	 * This method computes MAX SI.
	 * @param maxLength
	 * @param maxSelfInt
	 * @param surfaceString
	 */
	
	public static void countPrimitivesMaxSI(int maxLength, String surfaceString){
		String dir = surfaceString+"primitivesMaxSI/";
		statics.CreateDirectory.create(dir);
		
		int[] total = new int[maxLength];
		SurfaceWord sw = new SurfaceWord(surfaceString);
		System.out.println("Surface word is: "+surfaceString +" Start counting.");
		String filename ;
		
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false ){	
			if(wn.isSmallestAndPrimitive() ){
					int si = wn.selfIntersection(sw);
					if (total[len]<si) total[len]=si;
					//System.out.println(wn.toLetter(sw)+" "+si);
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
			filename = dir+surfaceString+len;
			String legend="";
			//file results (total, filename, legend);
			
			statics.FileStuff.fileVector(total, filename, "");
	
		}
	}
	
	
	public static void countAllWordsLengthSI(int maxLength, int maxSelfInt, String surfaceString){
		String dir = surfaceString+"AllWords/";
		statics.CreateDirectory.create(dir);
		
		int[][] total = new int[maxLength][maxSelfInt];
		SurfaceWord sw = new SurfaceWord(surfaceString);
		System.out.println("Surface word is: "+surfaceString +" Start counting.");
		String filename ;
		
		
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestAndPrimitive() ||wn.isSmallestAndNotPrimitive()){
					int si = wn.selfIntersection(sw);
					total[len][si]++;
					//System.out.println(wn.toLetter(sw)+" "+si);
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
			filename = dir+surfaceString+len;
			
			String legend=surfaceString+" Length including primitives and not";
			//file results (total, filename, legend);
			int[][] total1 = new int[len][maxSelfInt];
			for(int l = 1 ; l <=len ; l++){
				total1[l-1]=total[l];
			}
			statics.FileStuff.fileMatrix(total1, filename, "");
			
		}	
		
	}
	
	


	public static int countLengthSI(int maxLength, int maxSelfInt, String surfaceString,boolean upto, boolean onlyPrimitives){
		if(upto==false || onlyPrimitives==false) return 0;
			
		SurfaceWord sw = new SurfaceWord(surfaceString);
		System.out.println("Surface word is: "+surfaceString +" Start counting.");
		String filename ;
		int[][] total =     new int[maxLength][maxSelfInt];
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestAndPrimitive()){
					int si = wn.selfIntersection(sw);
					total[len][si]++;
					//System.out.println(wn.toLetter(sw)+" "+si);
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
			filename = surfaceString+"output/"+surfaceString+len;
			String legend=surfaceString+"Length"+len+"CycRedWordOFSI "+maxSelfInt+"length up to " +len+"\n";
			legend = legend + "Couning only primitives words.";
			//file results (total, filename, legend);
			statics.FileStuff.fileMatrix(total, filename, legend);
		}	
		return 1;
	}

	
	public static int countLengthSIAllWords(int startLength, int maxLength, int maxSelfInt, String surfaceString,boolean upto, boolean onlyPrimitives){
		if(upto==false || onlyPrimitives==false) return 0;
		String dir = surfaceString+"allCurves/";
		statics.CreateDirectory.create(dir);
			
		SurfaceWord sw = new SurfaceWord(surfaceString);
		System.out.println("Surface word is: "+surfaceString +" Start counting.");
		
		int[][] total =     new int[maxLength][maxSelfInt];
		boolean allZero=false;
		for(int len = startLength ; len < maxLength ; len++){
			System.out.println("Surface word is: "+surfaceString +" Start counting."+len);
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestAndPrimitive() || wn.isSmallestAndNotPrimitive()){
					int si = wn.selfIntersection(sw);
					total[len][si]++;
					//System.out.println(wn.toLetter(sw)+" "+si);
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
			String filename = dir+surfaceString+"_allCurves"+len;
			
			String legend=surfaceString+"Length"+len+"CycRedWordOFSI "+ maxSelfInt +"length up to " +len+"\n";
			legend = legend + "Couning all cyclic reduced words (primitives and powers).";
			//file results (total, filename, legend);
			statics.FileStuff.fileMatrix(total, filename, legend);
		}	
		return 1;
	}


}
