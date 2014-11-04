import guis.*;
import algorithm_new.NewComputation;

public class SI
{
  public static void main(String[] args)
  {
    //int[][] PL;
  //int[][] SL;
  //
  //input the surface (only abAB and aAbB work at this point)
  String sw1;
  //input the curve
  String cw1;
  //the word bbAABaaB causes trouble with aAbB
  //the word BAbbaaBB has 6 different reps with min intersection
  //does cycling through the letters give all the different reps?
  //the word ABaabb has 4 different reps
  //the word ABaabbAB has 1,2,3,4,5,6,7 reps
   
  int ia;
  int ib;
  int[] XCurveCoord;
  int[] YCurveCoord;
  int Size;
 
  int FirstTime=0;
   if(FirstTime==1){sw1="abAB";cw1="a";return;}
   else{sw1=TextIO.getlnString();;cw1=TextIO.getlnString();}
   //change Size to make the picture bigger/smaller if you'd like. 
  // System.out.println(sw1+"  "+cw1);
   Size=300; 
   int PicSize=300;
   //this.setSize(Size+50,Size+100);
  // System.out.println("why is it running paint twice?");
   NewComputation C = new NewComputation(sw1,cw1);
   String c1=cw1.toLowerCase();
   ia=0;
   ib=0;
   XCurveCoord=new int[2*cw1.length()];
   YCurveCoord=new int[2*cw1.length()];
   
   for (int i=0;i<c1.length();i++)
   {
    if (c1.charAt(i)=='a') ia++;
    if (c1.charAt(i)=='b') ib++;
   } 
   while(C.getDone()==0)
   {
    //System.out.println("Length: "+C.combLength());
    C.findIntersection();   

   }
  // C.easierList();
   int[][] PL=C.getPL();
   int[][] SL=C.getSL();
   int Cursor; 
  if(sw1.contentEquals("abAB")||sw1.contentEquals("aAbB")){
   //System.out.println(sw1.contentEquals("abAB"));
   //initialize the coordinates for all the lines that will eventually be drawn
   if (sw1.contentEquals("abAB"))
   {
    System.out.println("hello1");
    for(int i=0;i<ia;i++)
    {
     XCurveCoord[i]=20;
     YCurveCoord[i]=(Size-40)*(ia-i)/(ia+1)+40;
     XCurveCoord[2*cw1.length()-ib-1-i]=Size+20;
     YCurveCoord[2*cw1.length()-ib-1-i]=(Size-40)*(ia-i)/(ia+1)+40;
       }
       for(int j=0;j<ib;j++)
       {
        YCurveCoord[j+ia]=20;
        XCurveCoord[j+ia]=(Size-40)*(j+1)/(ib+1)+40;
        YCurveCoord[2*cw1.length()-1-j]=Size+20;
        XCurveCoord[2*cw1.length()-1-j]=(Size-40)*(j+1)/(ib+1)+40;
       }
   }
   if (sw1.contentEquals("aAbB"))
   {
    for(int i=0;i<ia;i++)
    {
     XCurveCoord[i]=20;
     YCurveCoord[i]=(Size-40)*(ia-i)/(ia+1)+40;
     XCurveCoord[ia+i]=(Size-40)*(i+1)/(ia+1)+40;
     YCurveCoord[ia+i]=20;
       }
       for(int j=0;j<ib;j++)
       {
        XCurveCoord[2*cw1.length()-ib-1-j]=Size+20;
     YCurveCoord[2*cw1.length()-ib-1-j]=(Size-40)*(ib-j)/(ib+1)+40;
        YCurveCoord[2*cw1.length()-1-j]=Size+20;
        XCurveCoord[2*cw1.length()-1-j]=(Size-40)*(j+1)/(ib+1)+40;
       }
   }
   
   //g.drawString(""+sw1.charAt(0), 5, 20+Size/2);
   //g.drawString(""+sw1.charAt(1), 20+Size/2, 15);
   //g.drawString(""+sw1.charAt(2), Size+25, 20+Size/2);
   //g.drawString(""+sw1.charAt(3), 20+Size/2, Size+35);
   //g.drawString(cw1, 20, Size+50);
   //g.drawString(""+C.getMinInt()+" intersections", 20, Size+70);
   double euclength=0.0;
      for(int k=0;k<cw1.length();k++)
      {
       //g.drawLine(XCurveCoord[SL[k][0]],YCurveCoord[SL[k][0]],XCurveCoord[SL[k][1]],YCurveCoord[SL[k][1]]);
       int mpx=(XCurveCoord[SL[k][0]]+XCurveCoord[SL[k][1]])/2;
       int mpy=(YCurveCoord[SL[k][0]]+YCurveCoord[SL[k][1]])/2;
       double a=XCurveCoord[SL[k][0]];
       double b=YCurveCoord[SL[k][0]];
       double c=XCurveCoord[SL[k][1]];
       double d=YCurveCoord[SL[k][1]];
       double atheta=Math.toRadians(45);
       double ltheta=Math.atan((d-b)/(c-a));
       Double temp1=10*Math.sin(ltheta+Math.PI/2-atheta);
       Double temp2=10*Math.cos(ltheta+Math.PI/2-atheta);
       //SHOULD HAVE 8 CASES TOTAL
      if(XCurveCoord[SL[k][0]]==20 && ltheta>=0)
      {
       //g.drawLine(mpx,mpy,mpx-temp2.intValue(),mpy-temp1.intValue());
       //g.drawLine(mpx,mpy,mpx-temp1.intValue(),mpy+temp2.intValue());
      }
      if(XCurveCoord[SL[k][0]]==20 && ltheta<0)
      {
       //g.drawLine(mpx,mpy,mpx-temp2.intValue(),mpy-temp1.intValue());
       //g.drawLine(mpx,mpy,mpx-temp1.intValue(),mpy+temp2.intValue());
      }
      if(XCurveCoord[SL[k][0]]==Size+20 && ltheta>=0)
      {
       //g.drawLine(mpx,mpy,mpx+temp2.intValue(),mpy+temp1.intValue());
       //g.drawLine(mpx,mpy,mpx+temp1.intValue(),mpy-temp2.intValue());
      }
      if(XCurveCoord[SL[k][0]]==Size+20 && ltheta<0)
      {
       //g.drawLine(mpx,mpy,mpx+temp2.intValue(),mpy+temp1.intValue());
       //g.drawLine(mpx,mpy,mpx+temp1.intValue(),mpy-temp2.intValue());
      }
      if(YCurveCoord[SL[k][0]]==20 && ltheta>=0)
      { 
       //g.drawLine(mpx,mpy,mpx-temp2.intValue(),mpy-temp1.intValue());
       //g.drawLine(mpx,mpy,mpx-temp1.intValue(),mpy+temp2.intValue());
      }
      if(YCurveCoord[SL[k][0]]==20 && ltheta<0)
      { 
       //g.drawLine(mpx,mpy,mpx+temp2.intValue(),mpy+temp1.intValue());
       //g.drawLine(mpx,mpy,mpx+temp1.intValue(),mpy-temp2.intValue());
      }
      if(YCurveCoord[SL[k][0]]==Size+20 && ltheta>=0)
      {
       //g.drawLine(mpx,mpy,mpx+temp2.intValue(),mpy+temp1.intValue());
       //g.drawLine(mpx,mpy,mpx+temp1.intValue(),mpy-temp2.intValue());
      }
      if(YCurveCoord[SL[k][0]]==Size+20 && ltheta<0)
      {
       //g.drawLine(mpx,mpy,mpx-temp2.intValue(),mpy-temp1.intValue());
       //g.drawLine(mpx,mpy,mpx-temp1.intValue(),mpy+temp2.intValue());
      } 
     }
      int[] XArray = {20, 40, PicSize, 20+PicSize, 20+PicSize, PicSize,     40, 20};
      int[] YArray = {40, 20, 20,   40,      PicSize,    20+PicSize, PicSize+20,PicSize   };
      //g.drawPolygon (XArray, YArray, 8);     
  }
      String s1="";
   String s2="";

   for (int i=0;i<2*cw1.length();i++)
   {
    s1=s1+C.toLetter.get(PL[i][0])+""+PL[i][1]+" , ";
   }
    s1=s1+"  ";
   for (int i=0;i<cw1.length();i++)
   {
    s2=s2+C.toLetter.get(PL[SL[i][0]][0])+""+PL[SL[i][0]][1]+" , "+C.toLetter.get(PL[SL[i][1]][0])+""+PL[SL[i][1]][1]+"\n";
   }
   System.out.println(""+C.getMinInt());
   //seqTextArea.setText(s1+ " \n *************************** \n" + s2);
      Cursor = 0;
 //   
 }
}
  
  