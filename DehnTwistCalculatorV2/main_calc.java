
/**
 * Nov 5th, 2014
 */
public class main_calc {

	public static void main(String[] args) {
	    int genus =  3;
	    int len = 20;
	    
	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

	    //	    for (int x = 0; x < 200; x = x+1) {
	    
	    String w = Random.Random(3,20);
	    String o = "";
	    o = o+"Word: "+w+" -- ";
	    o = o+"Intersections: "+intersection_num.intNum(genus,w)+" -- ";
	    o = o+"as DehnTwists: "+Pusher.Push(w);
	    System.out.println(o);
	    //}	
	}

}
