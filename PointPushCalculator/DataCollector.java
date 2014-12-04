import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Nov 8th, 2014
 * Modified @ Nov 13th,2014
 * follow step 1 to 7 to use this program
 */
public class DataCollector {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws FileNotFoundException,IOException {
		// change the parameter here
		int genus = 2;//1. change the genus here
		int len = 8;//2. change the length here
		int loop = 200;//3. number of data generated
		boolean intersect=false;//4. calculate the intersection or not
		boolean verbose=true;//5. print the number of data or not
		
		//GET THE TIME
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("MdHms");
    	
		
		for (; len < 16; len++) {//6. you might want to change 16 so one larger number
			

			PrintWriter out = new PrintWriter(new FileWriter(
					"D:\\1UIUC\\6IGL\\data\\genus" + genus + "_length" + len+ "_"+sdf.format(cal.getTime()) + ".txt"));	
					//7. you will need to change the path "D:\\1UIUC\\6IGL\\data\\" to one path in your computer
																
			
			out.println("Genus: " + genus);
			out.println("Length: " + len);
			out.println("Intersection: "+intersect);
			out.println("Number of data: " + loop);
			out.println();
			out.println("Genus,Length,Dilitation,isPseudoAnosov,Intersection_Number,Word");
			out.flush();
			

			/*
			 * for (int x = 0; x < loop; x = x + 1) 
			 * { String w =Random.Random(genus, len); 
			 * String o = ""; 
			 * o = o + "Word: " + w +" -- "; 
			 * //
			 * // + " -- "; 
			 * o = o +"as DehnTwists: " + Pusher.Push(w) + " -- "; 
			 * o = o +" dilatation: " + pAextractor.dilatation(genus, Pusher.Push(w));
			 * out.println(o);
			 * }
			 */
			
			for (int x = 0; x < loop; x = x + 1) {
				StringBuffer o = new StringBuffer("");
				String w = Random.Random(genus, len);
				//StringBuffer is quicker
				//genus
				o.append(genus+",");
				
				//length
				o.append(len+",");
				
				//dilatation,
				double dilatation = pAextractor.dilatation(genus,
						Pusher.Push(w));
				o = o.append( dilatation + ",");
				
				//isPA,
				if (dilatation == 0) {
					o.append( "false" + ",");
				} else {
					o.append ( "true" + ",");
				}

				//intersection,
				String intersection = "-";
				
				if(intersect){
					o.append(intersection_num.intNum(genus, w)+","); 
				}		
				else{
					o .append(intersection + ",");
				}
				
				//word,
				o.append(w);
				
				out.println(o);
				
				
				out.flush();
				if(verbose){
					System.out.print(x+1+"\n");
					System.out.println(o);
				}
			}

			out.close();
		}
	}

}
