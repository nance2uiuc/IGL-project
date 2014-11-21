/**
 * IGL Project
 * Dehn intersection_num
 * Sep 30th, 2014 
 *
 * provides method intNum to calculte self-intersection numbers
 * command line usage:
 * java intersection_num <genus> <word>
 */

import java.io.*;

public class intersection_num {

    public static void main(String[] args) {
 String genus = args[0];
 String word = args[1];
 int result = intNum(Integer.valueOf(genus),word);
 String trans = UsToBranch(word);
 // System.out.println("Word "+word+" on genus "+genus+": translates to "+trans+"; intersections: "+result);
 System.out.println(result);
    }

    /** 
     * intNum
     * input: a genus number, and a word in our generating set (eg. z1z2Y0y2Z0)
     * output: returns the intesection number
     */
    public static int intNum(int g, String w) {
 int numBounds = 0;  // we'll always work with closed surfaces
 String t = UsToBranch(w); // translate the word for format acceptableto branched
 // now call runBranched and return its output:
 return runBranched(g,numBounds,t);
    }

    /**
     * UsToBranch
     * accepts input word in "our" generators, z0, z1,y0,Z1,Y0,Y1, etc (e.g., z2Y0y1z0Z3y0)
     * translates this for Walker's program "branched"
     * returns result (a word of the form aBacDDe)
     */
    public static String UsToBranch(String word) {
 if ( (word.length() % 2) == 1 ){
     throw new IllegalArgumentException("Method 'UsToBranch' cannot except odd-length string '"+word);
 } else if ( word.length() > 2 ) {
     return UsToBranch(word.substring(0,2)) + UsToBranch(word.substring(2));
 } else if ( word.length() == 0 ){
     return "";
 } else { // this is the case where the length is 2
     /************************************************
      *      OLD CODE that was more general
      *      need to figure out how to adust to work for all genus
      ***************************************************************
      * int start = 0;
      * if ( word.substring(0,1).equals("z") ) { start = 97;}         // z0 starts at a = 97
      * else if ( word.substring(0,1).equals("y") ) { start = 98;}    // y0 starts at b = 98
      * else if ( word.substring(0,1).equals("Z") ) { start = 65;}    // Z0 starts at A = 65
      * else if ( word.substring(0,1).equals("Y") ) { start = 66;}    // Y0 starts at B = 66
      * else { 
      * String e ="'UsToBranch': input "+word+" doesn't start with 'z', 'y', 'Z', or 'Y'";
      * throw new IllegalArgumentException(e); 
      * }
      * return String.valueOf(Character.toChars(start+2*Integer.valueOf(word.substring(1))));
      */
     if ( word.equals("z0") ) {return "A";}
     else if ( word.equals("Z0") ) {return "a";}
     else if ( word.equals("z1") ) {return "dCD";}
     else if ( word.equals("Z1") ) {return "dcD";}
     else if ( word.equals("y0") ) {return "b";}
     else if ( word.equals("Y0") ) {return "B";}
     else if ( word.equals("y1") ) {return "d";}
     else if ( word.equals("Y1") ) {return "D";}
     else {
  String e ="this ver of 'UsToBranch' can ONLY excpt input words in the letters 'z0', 'z1', 'y0', or 'y1' (or their capitals)";
  throw new IllegalArgumentException(e); 
     }
 }
    }
    
   /**
    * runBranched
    * executes the branched program for the given genus, #boundary, and word word
    * takes input in the the form that branched likes!
    * returns the selfintersection number
    */
    public static int runBranched(int g, int n, String w) {
 String name_of_aux_program = "self-ints";
 String cmd = name_of_aux_program+" "+g+" "+n+" "+w;
 String s = null;
 int i = 0;
 try {
        // run the the command cmd using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader stdInput = new BufferedReader(new
                 InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                 InputStreamReader(p.getErrorStream()));
     i = Integer.valueOf(stdInput.readLine());
 
            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
                System.out.println("Error from running mybranched cmn: "+s);
            }
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
 return i;
    }
    
    // prints out list showing conversions of alphanueric chars to integers
    public static void charToIntConv() {
        for (int x = 65; x < 123; x = x+1) {
     System.out.print(x + " = ");
     System.out.print(Character.toChars(x));
     System.out.print("\n");
 }
    }
}





