import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.io.StringReader;

import pbj.math.graph.*;
import pbj.math.graph.train.TrainTrack;
import pbj.math.graph.train.*;
import pbj.math.numerical.*;
import gnu.getopt.*;
import java.util.*;
import pbj.Stopwatch;

/*
 * This program will use Xtrain and extract the "stretch factor" or "dilatation" from the output in an automated way.
 * Oct 6th,2014
 */

//Remember:
//1. when you use this program, import XTrain source code in your eclipse as an existing project.
//2. right click IGL_Project, click "properties", then "java build path". Choose "Projects" and add XTrain souce code that you imported.

public class FromXTrain {
  
 /*
  * This function will do the dehn twist by using Xtrain
  */
 public static void main (String [] args) throws FileNotFoundException, IOException{
  
   //System.out.println("If a given random map is non-pseudo Anosov, then it will print out '-1'.");
   //System.out.println("if a given random map is pseudo Anosov, then it will print out its Dilatation number."); 
   //System.out.println("");
   
  //get the genus and twists
  
  System.out.println("Enter the genus. e.g. 3 (But smaller than 10)");
  String S1 = TextIO.getlnString();
  System.out.println("Enter the length of a sequence of generating point-pushing maps, e.g. 5 ...");
  int s2 = TextIO.getlnInt();
  System.out.println("Enter the number of repetition of the process. e.g. 5...");
  int s3 = TextIO.getlnInt();
  
  char s0=S1.charAt(0);
  int s1=s0-'0';
  
      int A = s1-1;//Set the "g" for a Genus-g surface.
      
      int B = s2;//Pick the "L" for a sequence of length L of generating point-pushing maps in Genus-g surface.
      
  for(int j=0; j<s3; j++)
  {
    Stopwatch time = new Stopwatch();
    
      String w = "";
      
      for(int i=0; i<B; i++)
      {
        
        int m = uniform(4);//Pick a random number between [0,4).
        
        if (m==0) w = w + reverse(DehnTwist_IGL.push_z("push(z"+A+")"));
        if (m==1) w = w + reverse(Inverse.inverse(DehnTwist_IGL.push_z("push(z"+A+")")));
        if (m==2) w = w + reverse(DehnTwist_IGL.push_y("push(y"+A+")"));
        if (m==3) w = w + reverse(Inverse.inverse(DehnTwist_IGL.push_y("push(y"+A+")")));
        
      }//repeat this process b-1 times more to obtain a random sequence of length L of generating point-pushing homeomorphisms.
      
  String [] s=new String [2];
  s[0]=S1;
  s[1]=reverse("b0A0")+reverse("b1A0a1d1c0d0C0D1A1a0C0D0A0d0c0A0a1d1c0d0C0D1A1a0")+reverse(Inverse.inverse("b0A0"))+reverse(Inverse.inverse("B0a0d0A0b0D0"))+reverse("b1A0a1d1c0d0C0D1A1a0C0D0A0d0c0A0a1d1c0d0C0D1A1a0")+reverse(Inverse.inverse("b0A0"))+reverse("B0a0d0A0b0D0")+reverse(Inverse.inverse("b0A0"))+reverse("c0d0d1c0B0a0d0A0b0C0D1D0C0D1")+reverse("b0A0");
  //s[1]="d0c0d1c1d2C2";
  
  //Use the program in XTrain: DehnTwist
  DehnTwist.main(s);
  
  //A GraphMap is contructed at the same time
  //code from XTrain:DehnTwist.java
  GraphMap g;
  String a,lab,inp,tw;
  int genus;
  StreamTokenizer st;
  ////
  try {
   if (s.length==1) {
    st=new StreamTokenizer(new FileReader(s[0]));
    lab=s[0];
   }
   else if (s.length==0) {
    st=new StreamTokenizer(new InputStreamReader(System.in));
    lab="";
   }
   else {
    st=new StreamTokenizer(new StringReader(s[0]+" "+s[1]));
    lab="";
   }

   inp="// input: ";
   if (st.nextToken()==StreamTokenizer.TT_NUMBER) {
    genus=(int) st.nval;
    st.nextToken();
    tw=st.sval;
    if (tw==null)
     throw new RuntimeException("bad sequence of twists");

    st.nextToken();
    if (st.sval!=null)
     lab=st.sval;

    g=DehnTwist.stdGenerators(genus,tw,lab);
    inp=inp+genus+" "+tw+" "+lab;
   }
   else {
    a=st.sval;
    if (a==null)
     throw new RuntimeException("bad boundary word");

    st.nextToken();
    tw=st.sval;
    if (tw==null)
     throw new RuntimeException("bad sequence of twists."+
       "\nDid you use double quotes "+
     "(e.g., \"\'-c(bD)aab\'\")?");

    st.nextToken();
    if (st.sval!=null)
     lab=st.sval;

    if (a.startsWith("fix.")) {
     g=DehnTwist.twistWithFixedWord(a.substring(4),tw,lab);
    }
    else
     g=DehnTwist.twist(a,tw,lab);

    inp=inp+a+" "+tw+" "+lab;
   }
  } catch (Exception e) {System.err.println(e.toString());
  return;}
  
  //check whether it is a good map
  
  /*if (!g.isGoodMap()){
   throw new IllegalStateException("bad map!");
  
  }*/
  //if it is a good map, dilatation is computed
   TrainTrack tt = new TrainTrack(g);
   //tt.trainTrackMap();
   
   if (tt.trainTrackMap()) {
   //System.err.println("");
   //System.out.println(tt.toString());
      
   Gates gg;
   gg=new Gates(tt);
   if (gg.isPseudoAnosov()) {
    System.out.print("// Pseudo-Anosov Dilatation:"+IntMatrix.PFForm.format(tt.growthRate())
      );
   }
   else
    System.out.print("// Non-pseudo-Anosov.");
   
  }
   else {
   System.out.print("// Map is not irreducible.");
  }
   double T = time.elapsedTime();
   System.out.println(" // Time:"+T);
  }
  
    
 }
 

 public static int uniform(int N)
    {
      return (int) (Math.random() * N);
    }//random function: random(N)=n such that n is a random integer number in [0,N)
 
 public static String reverse(String s)
  {
    int length=s.length();
    char [] arr=new char [length];
    String result="";
    
    for (int i=0;i<length;i++)
    {
      arr[i]=s.charAt(i);
    }
    
    for(int i=length-2;i>=0;i=i-3)
    {
      if(i%2==0)
      {
        result=result+arr[i];
        i++;
        result=result+arr[i];
      }
    }
    
    return result;
  }
 
}
