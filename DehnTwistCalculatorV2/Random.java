public class Random
{
    public static int random(int N)//random function: random(N)=n such that n is a random integer number in [0,N)
    {
      return (int) (Math.random() * N);
    }
    
    public static String Random(int genus, int length)
      //A = the "g" for a Genus-g surface, B = the length for a sequence of generating point-pushing maps in Genus-g surface. 
      //returns a string of sequence of generating point-pushing maps in Genus-g surface.
    {
      int B = length + random(2);
      String s = "";
      
      for(int i=0; i<B; i++)
      {
        int n = random(4);
        int A = random(genus);
        if (n==0) s = s + "z"+Integer.toString(A);
        if (n==1) s = s + "Z"+Integer.toString(A);
        if (n==2) s = s + "y"+Integer.toString(A);
        if (n==3) s = s + "Y"+Integer.toString(A);
      }
      return s;
    }
}