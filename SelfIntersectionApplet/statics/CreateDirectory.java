package statics;

import java.io.File;

public class CreateDirectory {
	
	public static void create(String direct){
		  try{
			    String strDirectoy =direct;
			    

			    // Create one directory
			    boolean success = (new File(strDirectoy)).mkdir();
			       
			  


			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }
	}
}
