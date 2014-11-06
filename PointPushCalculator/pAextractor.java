import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.io.StringReader;

import java.io.*;
import java.util.*;
import pbj.math.graph.*;
import pbj.math.graph.train.*;
import pbj.math.numerical.*;
import gnu.getopt.*;
import java.util.*;

public class pAextractor
{
  public static double dilatation(int gGenus, String dDehnTwistWord) throws FileNotFoundException, IOException
  {    
    String [] s=new String [2];
    s[0] = Integer.toString(gGenus);
    s[1] = dDehnTwistWord;
    
    GraphMap g;
    String a,lab,inp,tw;
    int genus;
    StreamTokenizer st;
    
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
      return 0;}
    
    TrainTrack tt = new TrainTrack(g);
    Gates gg;
    
    if (tt.trainTrackMap()) {
      gg=new Gates(tt);
      
      if (gg.isPseudoAnosov()) {
        return Math.log(tt.growthRate());
      }
      else{
        return 0;
      }
    }
    return 0;
  }
  
  /*public static boolean pseudoAnosov(double dilatation)
  {
    if (dilatation == 0){ return false; }
    else return true;
  }*/
}
