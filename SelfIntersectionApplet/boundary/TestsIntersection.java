package boundary;

public class TestsIntersection {
	public static void produceAllWordsLengthSI(int maxLength, String surfaceString,String dir){
		dir = dir+surfaceString+"_allWords/";
		statics.CreateDirectory.create(dir);
		String filename = dir+surfaceString+"all";
		SurfaceWord sw = new SurfaceWord(surfaceString);
	
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			System.out.println("Surface word is: "+surfaceString +" Start counting."+len);
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestAndPrimitive() || wn.isSmallestAndNotPrimitive()){			
					statics.FileStuff.fileSentence(filename+"_leng_"+len+"_si_"+wn.selfIntersection(sw), wn.toLetter(sw));
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
			
		}	
		
	}

	
	public static void produceAllWordsLengthSIOneDirection(int maxLength, String surfaceString,String dir){
		dir = dir+surfaceString+"_allWordsOneDirection/";
		statics.CreateDirectory.create(dir);
		String filename = dir+surfaceString+"allOneDirection";
		SurfaceWord sw = new SurfaceWord(surfaceString);
	
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			System.out.println("Surface word is: "+surfaceString +" Start counting."+len);
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestOfAllDirectionsAndPrimitive(sw) || wn.isSmallestInAllDirectionsAndNotPrimitive(sw)){			
					statics.FileStuff.fileSentence(filename+"_leng_"+len+"_si_"+wn.selfIntersection(sw), wn.toLetter(sw));
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
			
		}	
		
	}
	
	public static void produceNonPrimitivesWordsLengthSIOneDirection(int maxLength, String surfaceString,String dir, int si, int len){
		dir = dir+surfaceString+"_allWordsOneDirection/";
		statics.CreateDirectory.create(dir);
		String filename = dir+surfaceString+"allOneDirection";
		SurfaceWord sw = new SurfaceWord(surfaceString);
	
		boolean allZero=false;
	
			System.out.println("Surface word is: "+surfaceString +" Start counting."+len);
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if( wn.isSmallestInAllDirectionsAndNotPrimitive(sw) || wn.selfIntersection(sw)==si ){	
				String sentence = filename+"_leng_"+len+"_si_"+wn.selfIntersection(sw);
					statics.FileStuff.fileSentence(sentence,wn.toLetter(sw));
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
			
			
		
	}
	
	public static void produceWordsLengthSIOneDirection(int maxLength, String surfaceString,String dir,int si, int len){
		dir = dir+surfaceString+"_allWordsOneDirection/";
		statics.CreateDirectory.create(dir);
		String filename = dir+surfaceString+"allOneDirection";
		SurfaceWord sw = new SurfaceWord(surfaceString);
	
		boolean allZero=false;
	
			System.out.println("Surface word is: "+surfaceString +" Start counting."+len);
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if((wn.isSmallestOfAllDirectionsAndPrimitive(sw)|| wn.isSmallestInAllDirectionsAndNotPrimitive(sw)) & wn.selfIntersection(sw)==si ){	
				String sentence = filename+"_leng_"+len+"_si_"+wn.selfIntersection(sw);
					statics.FileStuff.fileSentence(sentence,wn.toLetter(sw));
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
			
			
		
	}
	
	public static String moveaAbBcC(String s){
		s=s.replaceAll("ab", "C");
		s=s.replaceAll("BA", "c");
		return s;
	}
	
	public static void produceAllWordsLengthSIOneDirectionaAbBcC(int maxLength,String dir){
		String surfaceString= "aAbB";
		dir = dir+surfaceString+"aAbBcC/";
		statics.CreateDirectory.create(dir);
		String filename = dir+surfaceString+"allOneDirRelator";
		SurfaceWord sw = new SurfaceWord(surfaceString);
	
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			System.out.println("Surface word is: "+surfaceString +" Start counting."+len);
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestOfAllDirectionsAndPrimitive(sw) || wn.isSmallestInAllDirectionsAndNotPrimitive(sw)){			
				int len1 = moveaAbBcC(wn.toLetter(sw)).length();	
				statics.FileStuff.fileSentence(filename+"_leng_"+len1+"_si_"+wn.selfIntersection(sw), 
						moveaAbBcC(wn.toLetter(sw))+" , "+wn.toLetter(sw));
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
			
		}	
		
	}

	
	public static void produceAllWordsLengthSIOneDirectionaAbBcC(int maxLength,String dir, int maxSI){
		int[][] total =     new int[maxLength][maxSI];
		String surfaceString= "aAbB";
		dir = dir+surfaceString+"aAbBcC/";
		statics.CreateDirectory.create(dir);
		String filename = dir+surfaceString+"allOneDirRelator";
		SurfaceWord sw = new SurfaceWord(surfaceString);
	
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			System.out.println("Surface word is: "+surfaceString +" Start counting."+len);
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestOfAllDirectionsAndPrimitive(sw) || wn.isSmallestInAllDirectionsAndNotPrimitive(sw)){			
				int si = wn.selfIntersection(sw);
				int len1 = moveaAbBcC(wn.toLetter(sw)).length();	
				statics.FileStuff.fileSentence(filename+"_leng_"+len1+"_si_"+si, 
						moveaAbBcC(wn.toLetter(sw))+" , "+wn.toLetter(sw));
				total[len1][si]++;
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
			String legend ="All words, in only one direction.";
			statics.FileStuff.fileMatrix(total, "aAbBcC"+len, legend);
			
		}
		
		
	}
	
	public static void countAllWordsLengthSIOneDirectionaAbBcC(int maxLength,String dir, int maxSI){
		int[][] total =     new int[maxLength][maxSI];
		String surfaceString= "aAbB";
		dir = dir+"aAbBcC/";
		statics.CreateDirectory.create(dir);
		String filename = dir+surfaceString+"allOneDirRelator";
		SurfaceWord sw = new SurfaceWord(surfaceString);
	
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			System.out.println("Surface word is: "+surfaceString +" Start counting."+len);
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestOfAllDirectionsAndPrimitive(sw) || wn.isSmallestInAllDirectionsAndNotPrimitive(sw)){			
				int si = wn.selfIntersection(sw);
				int len1 = moveaAbBcC(wn.toLetter(sw)).length();	
				
				total[len1][si]++;
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

			String legend ="All words, in only one direction.";
			statics.FileStuff.fileMatrix(total, dir+"aAbBcC"+len, legend);
			
		}
		
		
	}
	
	public static void producePrimitiveWordsLengthSI(int maxLength, String surfaceString,String dir){
		dir = dir+surfaceString+"_primitiveWords/";
		statics.CreateDirectory.create(dir);
		String filename = dir+surfaceString+"p";
		SurfaceWord sw = new SurfaceWord(surfaceString);
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			System.out.println("Surface word is: "+surfaceString +" Start counting."+len);
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestAndPrimitive()){			
					statics.FileStuff.fileSentence(filename+"_leng_"+len+"_si_"+wn.selfIntersection(sw), wn.toLetter(sw));
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
			
		}	
		
	}
	
	public static void produceOnlyPowerWordsLengthSI(int maxLength, String surfaceString,String dir){
		dir = dir+surfaceString+"_NonPrimitiveWords/";
		statics.CreateDirectory.create(dir);
		String filename = dir+surfaceString+"nonPrim";
		SurfaceWord sw = new SurfaceWord(surfaceString);
		boolean allZero=false;
		for(int len = 1 ; len < maxLength ; len++){
			System.out.println("Surface word is: "+surfaceString +" Start counting."+len);
			WordVector wn = new WordVector(new int[len]); 
		///	int numberOfWords = (int) Math.pow(surfaceString.length(),len);	
			allZero=false;
			while(allZero==false){	
			if(wn.isSmallestAndNotPrimitive()){			
					statics.FileStuff.fileSentence(filename+"_leng_"+len+"_si_"+wn.selfIntersection(sw), wn.toLetter(sw));
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
			
		}	
		
	}


	


}
