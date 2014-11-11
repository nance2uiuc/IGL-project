import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Nov 8th, 2014
 * follow step 1 to 5 to use this program
 */
public class DataCollector {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws FileNotFoundException,IOException {
		// change the parameter here
		int genus = 2;//1. change the genus here
		int len = 12;//2. change the length here
		int loop = 200;//3. number of data generated
		
		for (; len < 16; len++) {//4. you might want to change 16 so one larger number
			

			PrintWriter out = new PrintWriter(new FileWriter(
					"D:\\1UIUC\\6IGL\\data\\genus" + genus + "_length" + len//5. you will need to change the path "D:\\1UIUC\\6IGL\\data\\" to 
							+ ".txt"));										//one path in your computer

			out.println("Note: Intersecton number is replaced by a dash \"-\" ");
			out.println();
			out.println("Genus: " + genus);
			out.println("Length: " + len);
			out.println("Number of data: " + loop);
			out.println();
			out.println("Dilitation,isPseudoAnosov,Intersection_Number,Word");
			out.flush();

			/*
			 * for (int x = 0; x < loop; x = x + 1) 
			 * { String w =Random.Random(genus, len); 
			 * String o = ""; 
			 * o = o + "Word: " + w +" -- "; 
			 * //o = o + "Intersections: " +intersection_num.intNum(genus, w) 
			 * // + " -- "; 
			 * o = o +"as DehnTwists: " + Pusher.Push(w) + " -- "; 
			 * o = o +" dilatation: " + pAextractor.dilatation(genus, Pusher.Push(w));
			 * out.println(o);
			 * }
			 */
			for (int x = 0; x < loop; x = x + 1) {
				String w = Random.Random(genus, len);
				//StringBuffer is quicker
				StringBuffer o = new StringBuffer("");
				double dilatation = pAextractor.dilatation(genus,
						Pusher.Push(w));
				o = o.append( dilatation + ",");
				if (dilatation == 0) {
					o.append( "false" + ",");
				} else {
					o.append ( "true" + ",");
				}

				String intersection = "-";
				o .append(intersection + ",");
				o.append(w);
				out.println(o);
				out.flush();
			}

			out.close();
		}
	}

}
