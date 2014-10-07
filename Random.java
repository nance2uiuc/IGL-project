public class Random
{
    public static int uniform(int N)
    {
      return (int) (Math.random() * N);
    }//random function: random(N)=n such that n is a random integer number in [0,N)
    
    public static void main(String[] args)
    {
      int a = Integer.parseInt(args[0]);//Pick the "g" for a Genus-g surface.
      
      int b = Integer.parseInt(args[1]);//Pick the "L" for a sequence of length L of generating point-pushing maps in Genus-g surface.
      
      String w = "word";
      
      for(int i=0; i<b ; i++)
      {
        
        int m = uniform(4);//Pick a random number between [0,4).
        
        if (m==0) w = "z";
        if (m==1) w = "Z";
        if (m==2) w = "y";
        if (m==3) w = "Y";//Pick a random alphabet either z,Z,y, or Y.
        
        int n = uniform(a);//Pick a random integer from [0,a) where a=g.
        
        System.out.print("push("+w+n+")");
        
      }//repeat this process b-1 times more to obtain a random sequence of length L of generating point-pushing homeomorphisms.
      System.out.println();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/master
