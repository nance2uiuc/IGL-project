package statics;

import java.io.*;


public class FileStuff {
	public static void fileMatrixTrans( int[][] matrix1 , String filename,  String legend){
		
		int[][] matrix = new int[matrix1[0].length][matrix1.length];
		for(int i = 0 ; i < matrix1[0].length ; i++){
			for(int j =0 ; j < matrix1.length ; j++){
				matrix[i][j]=matrix1[j][i];
			}
		}
		
        try
        {
            String fileName = filename+".txt";
            FileWriter fileWriter = new FileWriter( fileName,true);
            BufferedWriter bufferedWriter = new BufferedWriter( fileWriter );
            
            System.out.println( legend );
            bufferedWriter.write(filename+ legend );
            bufferedWriter.newLine();
            String matrixR = "n|s &";
            for ( int j = 1 ; j < matrix[0].length-1 ; j++ )
            {
                matrixR += j + " & ";
            //    System.out.println( matrixRow );
            }
            matrixR += matrix[0].length -1  + " *** ";
            System.out.println( matrixR );
            bufferedWriter.write( matrixR);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            for ( int i = 0 ; i < matrix.length ; i++ ){
                String matrixRow= i+" & ";
                
                for ( int j = 1 ; j < matrix[i].length-1 ; j++ )
                {
                    matrixRow += matrix[ i ][ j ] + " & ";
                //    System.out.println( matrixRow );
                }
                matrixRow += matrix[ i ][ matrix[i].length -1 ] + " *** ";
                System.out.println( matrixRow );
                bufferedWriter.write( matrixRow );
                bufferedWriter.newLine();
                bufferedWriter.flush();
                
                
            }
            bufferedWriter.write(filename + legend );
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
        
    }
   
	
	
	   public static void fileMatrix( int[][] matrix , String filename,  String legend){
	        try
	        {
	            String fileName = filename+".txt";
	            FileWriter fileWriter = new FileWriter( fileName,true);
	            BufferedWriter bufferedWriter = new BufferedWriter( fileWriter );
	            
	            System.out.println( legend );
	            bufferedWriter.write(filename+ legend );
	            bufferedWriter.newLine();
	            for ( int i = 0 ; i < matrix.length ; i++ ){
	                String matrixRow= "";
	                
	                for ( int j = 0 ; j < matrix[i].length ; j++ )
	                {
	                    matrixRow += matrix[ i ][ j ] + " ";
	                //    System.out.println( matrixRow );
	                }
	                System.out.println( matrixRow );
	                bufferedWriter.write( matrixRow );
	                bufferedWriter.newLine();
	                bufferedWriter.flush();
	                
	                
	            }
	            bufferedWriter.write(filename + legend );
	            bufferedWriter.newLine();
	            bufferedWriter.flush();
	            bufferedWriter.newLine();
	            bufferedWriter.close();
	        }
	        catch( IOException e )
	        {
	            e.printStackTrace();
	        }
	        
	    }
	   
	   public static void fileSentence(String filename,  String legend){
	        try
	        {
	            String fileName = filename+".txt";
	            FileWriter fileWriter = new FileWriter( fileName,true);
	            BufferedWriter bufferedWriter = new BufferedWriter( fileWriter );
	            
	        //    System.out.println( legend );
	            bufferedWriter.write(legend );
	            bufferedWriter.newLine();
	          
	          
	            bufferedWriter.flush();
	           
	            bufferedWriter.close();
	        }
	        catch( IOException e )
	        {
	            e.printStackTrace();
	        }
	        
	    }

	

	   
	public static void fileVector(int[] m, String filename,String legend){
        try
        {
            String fileName = filename+".txt";
            FileWriter fileWriter = new FileWriter( fileName,true);
            BufferedWriter bufferedWriter = new BufferedWriter( fileWriter );
            
           // System.out.println( legend );
            bufferedWriter.write( legend );
            bufferedWriter.newLine();
            for ( int i = 0; i < m.length ; i++ ){
                String matrixRow=  i+" " + m[ i ];
                //    System.out.println( matrixRow );
            
            //    System.out.println( matrixRow );
                bufferedWriter.write( matrixRow );
                bufferedWriter.newLine();
                bufferedWriter.flush();
                
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
        
    }



	public static void fileVectors(int[] m1, int[] m2, String filename, int min, double dist){	 
		try
	    {
	        String fileName = filename+".txt";
	        String matrixRow="[ ";
	        FileWriter fileWriter = new FileWriter( fileName,true);
	        BufferedWriter bufferedWriter = new BufferedWriter( fileWriter );
	
	        bufferedWriter.newLine();
	        for ( int i = 0; i < m1.length-1 ; i++ ){
	             matrixRow= matrixRow + m1[ i ]+", ";
	            
	        }
	        matrixRow=matrixRow+m1[m1.length-1]+" "+"] , [ ";
	        for ( int i = 0; i < m2.length - 1; i++ ){
	             matrixRow= matrixRow + m2[ i ]+", ";
	            
	        }
	        matrixRow=matrixRow+m2[m2.length-1]+" "+"] "+"  "+min+"  "+dist;
	       //System.out.println( matrixRow +" "+fileName);
            bufferedWriter.write( matrixRow );
            bufferedWriter.newLine();
            bufferedWriter.flush();
	        bufferedWriter.close();
	    }
	    catch( IOException e )
	    {
	        e.printStackTrace();
	    }
	    
	}
	

	public static void fileVectors(int[] m1, int[] m2, int[] v1, int[] v2, String filename, int min){	 
		try
	    {
	        String fileName = filename+".txt";
	        String matrixRow="[ ";
	        FileWriter fileWriter = new FileWriter( fileName,true);
	        BufferedWriter bufferedWriter = new BufferedWriter( fileWriter );
	
	        bufferedWriter.newLine();
	        //input m1
	        for ( int i = 0; i < m1.length-1 ; i++ ){
	             matrixRow= matrixRow + m1[ i ]+", ";
	            
	        }
	  
	        matrixRow=matrixRow+m1[m1.length-1]+" "+"] , [ ";
	        //input m2
	        for ( int i = 0; i < m2.length - 1; i++ ){
	             matrixRow= matrixRow + m2[ i ]+", ";
	            
	        }
	        matrixRow=matrixRow+m2[m2.length-1]+" "+"] "+"  "+min+"  [";
	        //input v1
	        for ( int i = 0; i < v1.length-1 ; i++ ){
	             matrixRow= matrixRow + v1[ i ]+", ";
	            
	        }
	        matrixRow=matrixRow+v1[v1.length-1]+" "+"]  ,  [";
	        
	      //input v2
	        for ( int i = 0; i < v2.length-1 ; i++ ){
	             matrixRow= matrixRow + v2[ i ]+", ";
	            
	        }
	        matrixRow=matrixRow+v2[v2.length-1]+" "+"]";
	        
	       //System.out.println( matrixRow +" "+fileName);
            bufferedWriter.write( matrixRow );
            bufferedWriter.newLine();
            bufferedWriter.flush();
	        bufferedWriter.close();
	    }
	    catch( IOException e )
	    {
	        e.printStackTrace();
	    }
	}
	
	
	public static void fileVectors(int[] m1, int[] m2, int[] v1, int[] v2, String filename, int minR,int minAll){	 
		try
	    {
	        String fileName = filename+".txt";
	        String matrixRow="[ ";
	        FileWriter fileWriter = new FileWriter( fileName,true);
	        BufferedWriter bufferedWriter = new BufferedWriter( fileWriter );
	
	        bufferedWriter.newLine();
	        //input m1
	        for ( int i = 0; i < m1.length-1 ; i++ ){
	             matrixRow= matrixRow + m1[ i ]+", ";
	            
	        }
	  
	        matrixRow=matrixRow+m1[m1.length-1]+" "+"] , [ ";
	        //input m2
	        for ( int i = 0; i < m2.length - 1; i++ ){
	             matrixRow= matrixRow + m2[ i ]+", ";
	            
	        }
	        matrixRow=matrixRow+m2[m2.length-1]+" "+"] "+"  "+minAll+"  "+minR+"  [";
	        //input v1
	        for ( int i = 0; i < v1.length-1 ; i++ ){
	             matrixRow= matrixRow + v1[ i ]+", ";
	            
	        }
	        matrixRow=matrixRow+v1[v1.length-1]+" "+"]  ,  [";
	        
	      //input v2
	        for ( int i = 0; i < v2.length-1 ; i++ ){
	             matrixRow= matrixRow + v2[ i ]+", ";
	            
	        }
	        matrixRow=matrixRow+v2[v2.length-1]+" "+"]";
	        
	       //System.out.println( matrixRow +" "+fileName);
            bufferedWriter.write( matrixRow );
            bufferedWriter.newLine();
            bufferedWriter.flush();
	        bufferedWriter.close();
	    }
	    catch( IOException e )
	    {
	        e.printStackTrace();
	    }
	}
	
	    public static void fileVectorsDifference(int[] m1, int[] m2, int[] v1, int[] v2, String filename, int min, int sum){	 
			try
		    {
		        String fileName = filename+".txt";
		        String matrixRow="[ ";
		        FileWriter fileWriter = new FileWriter( fileName,true);
		        BufferedWriter bufferedWriter = new BufferedWriter( fileWriter );
		
		        bufferedWriter.newLine();
		        //input m1
		        for ( int i = 0; i < m1.length-1 ; i++ ){
		             matrixRow= matrixRow + m1[ i ]+", ";
		            
		        }
		  
		        matrixRow=matrixRow+m1[m1.length-1]+" "+"] , [ ";
		        //input m2
		        for ( int i = 0; i < m2.length - 1; i++ ){
		             matrixRow= matrixRow + m2[ i ]+", ";
		            
		        }
		        matrixRow=matrixRow+m2[m2.length-1]+" "+"] "+"  "+min+" \n[";
		        //input v1
		        for ( int i = 0; i < v1.length-1 ; i++ ){
		             matrixRow= matrixRow + v1[ i ]+", ";
		            
		        }
		        matrixRow=matrixRow+v1[v1.length-1]+" "+"]  ,  [";
		        
		      //input v2
		        for ( int i = 0; i < v2.length-1 ; i++ ){
		             matrixRow= matrixRow + v2[ i ]+", ";
		            
		        }
		        matrixRow=matrixRow+v2[v2.length-1]+" "+"]\n[";
		        
		        int n = v1.length;
		        int[] u = new int[n];
		        
		        for(int h=0 ; h< n-1 ; h++){
					u[h]=(v1[h]-v1[h+1])/2;
				}
		        u[n-1]=(v1[n-1]-v1[0])/2;
		        for ( int i = 0; i < u.length-1 ; i++ ){
		             matrixRow= matrixRow + u[ i ]+", ";
		            
		        }
		        matrixRow=matrixRow+u[u.length-1]+" "+"]  , [";
		        for ( int i = 0; i < v2.length-1 ; i++ ){
		             matrixRow= matrixRow + (v2[ i ]-v2[i+1])+", ";
		            
		        }
		        matrixRow=matrixRow+(v2[v2.length-1]-v2[0])+" "+"]\n";
		        int aux=0;
		        for(int h=0 ; h< n ; h++){
		        	if(u[h]>=0){
		        		aux=aux+u[h];
		        	}
				}
		        matrixRow = matrixRow+"["+aux+"]";
		        
		       //System.out.println( matrixRow +" "+fileName);
	            bufferedWriter.write( matrixRow );
	            bufferedWriter.newLine();
	            bufferedWriter.flush();
		        bufferedWriter.close();
		    }
		    catch( IOException e )
		    {
		        e.printStackTrace();
		    }
	    
	}
}
