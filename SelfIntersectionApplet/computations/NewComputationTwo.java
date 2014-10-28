package computations;
import java.util.ArrayList;

	//ERROR WITH CURVES aBBab with b, but NOT with aBBab and A,B,a.
// also with		ABB, b   FIXED!!!!!


	//ERROR with words abaB and aaaaaa  (letters on different sides are getting switched!!

public class NewComputationTwo extends SurfaceWord
{
	
	// ERROR WITH CURVES ab AND bbbb ON SURFACE abAB!!!!, aba WITH a,aaa,aaaa,aaaaa,B,BBB,BBBB,etc
	// aba with AAA gives an infinite loop
	//bbbb with abaabb
int[][] PL;
int[][] SL1;
int[][] SL2;
int[][] SLComb;
int[] WordLengths;
int[][][] SL; //first coordinate is for curve 1 or 2, 2nd is for edge, 3rd is for index
int[] M;
int[] M1;
int[] M2;
ArrayList BL1;
ArrayList BL2;


String surfw;
String curvw1;
String curvw2;
int done;
int minInt;


	public NewComputationTwo()
	{
	super("");
	}

	public NewComputationTwo(String sw, String cw1, String cw2)
	{
		super(sw);
		surfw=new String(sw);
		curvw1=new String(cw1);
		curvw2=new String(cw2);
		minInt=0;
		WordLengths=new int[2];
		WordLengths[0]=cw1.length();
		WordLengths[1]=cw2.length();
		
		//construct point list according to rules inherent in sw and cw
		//SL1=new int[cw1.length()][2];
		SL=new int[2][][];	
		SL[0]=new int[cw1.length()][2];
		SL[1]=new int[cw2.length()][2];
		//SL2=new int[cw2.length()][2];
		PL=new int[2*(cw1.length()+cw2.length())][2];
		int[] C1 = this.toNumber(cw1);
		int[] C2=this.toNumber(cw2);
		int L=this.getSurfaceLength();
		M=new int[L];
		M1 =new int[L];
		M2=new int[L];
	//	System.out.println(2*(cw1.length()+cw2.length()));
	//	System.out.println(curvw1);
	//	System.out.println(curvw2);
		//counts how many of each pair there are
		for( int i = 0; i<L;i++)
		{
			for (int j = 0;j<cw1.length();j++)
			{
				if ( C1[j] == i)
				 {
					M1[i]++;
				 }
			}
		//	System.out.println(M1[i]);
			for (int j = 0;j<cw2.length();j++)
			{
				if ( C2[j] == i)
				 {
					M2[i]++;
				 }
			}
			M[i]=M1[i]+M2[i];
		//	System.out.println(M2[i]);
		}
	//	System.out.println(M);
		//this tells you which row of PL the first occurrence of point with letter i is located at;
		int[] start=new int[L];
		start[0]=0;
	//	System.out.println(L);
		for(int i=1;i<L;i++)
		{
			start[i]=start[i-1]+M1[i-1]+M1[this.barN.get(i-1)];
		//	System.out.println(start[i]);
		}
		//constructs the list of points
		int k=0;
		for (int i = 0;i<sw.length();i++)
		{
			boolean used;
			int con = this.barN.get(i);
			used=(con<i);
			if (!used)
			{
				for(int j=0;j<M[i]+M[con];j++)
				{	
					PL[k][0]=i;
					PL[k][1]=j;
					k++;
				}
			}
			else 
			{
				for(int j=M[i]+M[con]-1;j>=0;j--)
				{	
					PL[k][0]=i;
					PL[k][1]=j;
					k++;
				}
			}
		}
	//	System.out.println("********************************");
	//	this.printPoints();
	//	System.out.println("********************************");

	//	System.out.println("stop");
		
		//constructs the list of segments
		
		//first curve
		int LL=cw1.length();
		int[] avail=new int[sw.length()];
		int h = this.barN.get(C1[LL-1]);
			SL[0][0][0]=this.findPoint(h,Math.max(M1[C1[LL-1]]+M1[h]-1,0));
			SL[0][0][1]=findPoint(C1[0],avail[C1[0]]);	


			avail[C1[0]]++;
			avail[this.barN.get(C1[0])]++;
			for(int kk=1;kk<LL;kk++)
			{
				SL[0][kk][0]=this.findPoint(this.barN.get(PL[SL[0][kk-1][1]][0]),avail[PL[SL[0][kk-1][1]][0]]-1);
				SL[0][kk][1]=this.findPoint(C1[kk],avail[C1[kk]]);		
				avail[C1[kk]]++;
				avail[this.barN.get(C1[kk])]++;
			}
		//second curve
			int LL2=cw2.length();
			int h2 = this.barN.get(C2[LL2-1]);
				SL[1][0][0]=this.findPoint(h2,Math.max(M[C2[LL2-1]]+M[h2]-1,0));
				SL[1][0][1]=findPoint(C2[0],avail[C2[0]]);	
				//System.out.println(SL[1][0][1]);

				avail[C2[0]]++;
				avail[this.barN.get(C2[0])]++;
				for(int kk=1;kk<LL2;kk++)
				{
					//System.out.println(PL[SL[1][kk-1][1]][0]);
					//System.out.println(avail[PL[SL[1][kk-1][1]][0]]-1);

					SL[1][kk][0]=this.findPoint(this.barN.get(PL[SL[1][kk-1][1]][0]),avail[PL[SL[1][kk-1][1]][0]]-1);
					SL[1][kk][1]=this.findPoint(C2[kk],avail[C2[kk]]);		
					avail[C2[kk]]++;
					avail[this.barN.get(C2[kk])]++;
				}	
				SLComb=new int[cw1.length()+cw2.length()][2];
				for(int l=0;l<cw1.length()+cw2.length();l++)
				{
					if(l<cw1.length()){ 
						SLComb[l][0]=SL[0][l][0];
						SLComb[l][1]=SL[0][l][1];
					}
					if(l>=cw1.length()){ 
						SLComb[l][0]=SL[1][l-cw1.length()][0];
						SLComb[l][1]=SL[1][l-cw1.length()][1];
					}
				}
				
			//System.out.println("boo");
				System.out.println("********************************");
				this.printSegments();
				System.out.println("********************************");

		//System.out.println("This should only appear once");

	}
		
	public int[][] getPL()
	{
		return PL;
	}
	public int[][] getSL1()
	{
		return SL[0];
	}
	public int[][] getSL2()
	{
		return SL[1];
	}
	public int getMinInt()
	{
		return minInt;
	}
	public int[][] getCombSL()
	{return SLComb;}
	
	//returns row of PL containing letter a, index b;
	public int findPoint(int a, int b)
	{
	//	System.out.println(curvw11);
		for(int j=0;j<(2*(curvw1.length()+curvw2.length()));j++)
		{
			if (PL[j][0]==a && PL[j][1]==b) return j;
		}
	    return -1;
	}
	
	//returns true if segments i and j from curve c1 and c2 split in direction d, false if they don't
	public boolean split(int c1, int c2,int i, int j, int d)
	{
		System.out.println("curves "+c1 +" and " +c2+ "segments "+i+" and "+j+" , direction is "+d);
		if (d==1)
		{
		//	System.out.println(this.toLetter.get(PL[SL[c1][i][1]][0])+" "+this.toLetter.get(PL[SL[c2][j][1]][0]));
			//System.out.println(a.getSecondPoint().getLetter()+" boooo "+b.getFirstPoint().getLetter());
			return !(PL[SL[c1][i][1]][0]== PL[SL[c2][j][1]][0]);	
		}
		if (d==2)
		{
		//	System.out.println(this.toLetter.get(PL[SL[c1][i][1]][0])+" "+this.toLetter.get(PL[SL[c2][j][0]][0]));

		//	System.out.println(a.getSecondPoint().getLetter()+" boooo "+b.getFirstPoint().getLetter());
			return !(PL[SL[c1][i][1]][0]== PL[SL[c2][j][0]][0]);
					//a.getSecondPoint().getLetter()== b.getFirstPoint().getLetter());
		}
		if (d==3)
		{	//		System.out.println(this.toLetter.get(PL[SL[c1][i][0]][0])+" "+this.toLetter.get(PL[SL[c2][j][1]][0]));

		//	System.out.println("Checking segments "+i+" and "+ j+" in direction 3");
			return !(PL[SL[c1][i][0]][0]== PL[SL[c2][j][1]][0]);
		}
		if (d==4)
		{	//		System.out.println(this.toLetter.get(PL[SL[c1][i][0]][0])+" "+this.toLetter.get(PL[SL[c2][j][0]][0]));

			return !(PL[SL[c1][i][0]][0]== PL[SL[c2][j][0]][0]);
		}
		else return false;
	}
	
	//determines if two "segment words" SL[c1][a] and SL[c2][b] intersect
	public boolean intersect(int c1, int c2, int a,int b)
	{
	//	System.out.println(c2);
		//first figure out which is closer to the beginning of PL
		int size=2*(curvw1.length()+curvw2.length());
		int c;
		int[] rows=new int[4];	
		//rows[0]=SL[c1][a][0];
		rows[0]=0;
		rows[1]=(SL[c1][a][1]-SL[c1][a][0])%size;
		rows[2]=(SL[c2][b][0]-SL[c1][a][0])%size;
		rows[3]=(SL[c2][b][1]-SL[c1][a][0])%size;
		
		if(rows[1]<0){rows[1]=rows[1]+2*(curvw1.length()+curvw2.length());}
		if(rows[2]<0){rows[2]=rows[2]+2*(curvw1.length()+curvw2.length());}
		if(rows[3]<0){rows[3]=rows[3]+2*(curvw1.length()+curvw2.length());}

	/*	int l = 0;
		for(int i =0;i<4; i++)
		{	
			if(rows[l] > rows[i])
			{
				l = i;
			}
		}
		if (l==0 || l==1)c=a;
		else c=b;
		int d=a;
		if (c==a)d=b; 
		//System.out.println(PL[SL[0][c][0]][0]+" "+PL[SL[0][c][0]][1]+"  ,  "+PL[SL[0][c][1]][0]+" "+PL[SL[0][c][1]][1]);
	//	System.out.println(c2+"   "+d);
		int i1=SL[c1][c][0];
		int i2=SL[c1][c][1];
		int j1=SL[c2][d][0];
		int j2=SL[c2][d][1];
		
		
		if(i1<j1 && j1<i2 && i2<j2) return true;
		if(i1<j2 && j2<i2 && i2<j1) return true;
		if(i2<j1 && j1<i1 && i1<j2) return true;
		if(i2<j2 && j2<i1 && i1<j1) return true;
		*/
		if(rows[0]<rows[2] && rows[2]<rows[1] && rows[1]<rows[3]){
		System.out.println("Segments "+this.toLetter.get(PL[SL[c1][a][0]][0])+""+PL[SL[c1][a][0]][1]+","+this.toLetter.get(PL[SL[c1][a][1]][0])+""+PL[SL[c1][a][1]][1]+" and "+this.toLetter.get(PL[SL[c2][b][0]][0])+""+PL[SL[c2][b][0]][1]+","+this.toLetter.get(PL[SL[c2][b][1]][0])+""+PL[SL[c2][b][1]][1]+ " intersect.");
		return true;
		}
		if(rows[0]<rows[3] && rows[3]<rows[1] && rows[1]<rows[2]){
		System.out.println("Segments "+this.toLetter.get(PL[SL[c1][a][0]][0])+""+PL[SL[c1][a][0]][1]+","+this.toLetter.get(PL[SL[c1][a][1]][0])+""+PL[SL[c1][a][1]][1]+" and "+this.toLetter.get(PL[SL[c2][b][0]][0])+""+PL[SL[c2][b][0]][1]+","+this.toLetter.get(PL[SL[c2][b][1]][0])+""+PL[SL[c2][b][1]][1]+ " intersect.");

			return true;
		}
		else 		//System.out.println("Segments "+this.toLetter.get(PL[SL[c1][a][0]][0])+""+PL[SL[c1][a][0]][1]+","+this.toLetter.get(PL[SL[c1][a][1]][0])+""+PL[SL[c1][a][1]][1]+" and "+this.toLetter.get(PL[SL[c2][b][0]][0])+""+PL[SL[c2][b][0]][1]+","+this.toLetter.get(PL[SL[c2][b][1]][0])+""+PL[SL[c2][b][1]][1]+ " do NOT intersect.");
	//	System.out.println(rows[0]+" "+rows[1]+" "+rows[2]+" "+rows[3]);
		return false;

	}
	
	//searches through the curves c1 and c2 to find and remove excess intersections
	public void findIntersection()
	{
		int count=0;
//could do lcm instead of multiplying....but I'm lazy right now
	for(int c1=0;c1<WordLengths.length;c1++)
	{
	for(int c2=c1;c2<WordLengths.length;c2++)  //is this where the nested bigons idea needs to be fixed?
	{
		System.out.println("checking curve with itself");
	//	for (int i1=0;i1<WordLengths[c1]*WordLengths[c2];i1++)    //interchange these for testing
	//	{
		for (int i=0;i<WordLengths[c1];i++)
		{	

			//int i=i1 % WordLengths[c1];
			//System.out.println(C.getLength());
		if(c1==c2)
		{
		
			for(int j=i+1;j<WordLengths[c1];j++)
			{
				//System.out.println(i+"  "+j);
				if (this.intersect(c1,c2,i,j)) 
				{
				//	C.getSegment(i).printWord();
				//	C.getSegment(j).printWord();
				//	System.out.println("intersect");
				count++;	
				System.out.println("Checking segments "+c1+"-"+i+" and "+c2+"-"+j);
					if (this.potentialBigon(c1,c2,i,j)==1)
						{
						//	System.out.println("Checking segments "+i+" and "+j);
							return;
						}
				}
			}
		}
		if(c1!=c2)
		{
		//	for(int j1=0;j1<WordLengths[c2]*WordLengths[c1];j1++)
		//	{
			for(int j=0;j<WordLengths[c2];j++)
			{
			//	int j=j1 % WordLengths[c2];
				//System.out.println(i+"  "+j);
				if (this.intersect(c1,c2,i,j)) 
				{
				//	C.getSegment(i).printWord();
				//	C.getSegment(j).printWord();
				//	System.out.println("intersect");
				count++;	
	//			System.out.println("Checking segments "+i+" and "+j);
					if (this.potentialBigon(c1,c2,i,j)==1)
						{
						//	System.out.println("Checking segments "+i+" and "+j);
							return;
						}
				}
			}
		}
		}
	}
	}
	//	System.out.println("found no bigons or intersections");
		//this.getPL().printList();
	//	System.out.println(" ");
		//this.getSL[0]().printList();
	//	System.out.println("********************************************************");
	//	this.easierList();
		done=1;
		
	/*	System.out.println("found no more bigons to remove");
		printPoints();
		System.out.println("********************************************************");
		printSegments();
		System.out.println("********************************************************");
		System.out.println("There are "+count+" intersections.");
		System.out.println();
		//*/
		minInt=count;
	}
	
	//returns 1 if the potential bigon is a proper bigon, 0 otherwise;  
	public int potentialBigon(int c1, int c2,int i, int j)
	{
		//System.out.println((2)^3);
		//System.out.println("Checking for pot bigon");
		int direction=0;
		int founddir=0;
	//	int searchdone=0;
		int check=0;
	//	int oppdir=0;		//assign an initial direction and initialize two "legs" of the bigon.
		BL1=new ArrayList();
		BL2=new ArrayList();
		BL1.add(i);
		BL2.add(j);
		//check what directions are valid;	
		if(founddir==0)
		{
			int checker=0;
			if (!this.split(c1,c2,i,j,1)) {direction=1; founddir=1;}
		}
		if(founddir==0)
		{
			if (!this.split(c1,c2,i,j,2)) {direction=2; founddir=1;}
		}
		if(founddir==0)
		{
			if (!this.split(c1,c2,i,j,3)) {direction=3; founddir=1;}
		}
		if(founddir==0)
		{
			if (!this.split(c1,c2,i,j,4)){ direction=4; founddir=1;}
		}
		
	//	if(direction==0){System.out.println("No bigon possible");}
		else{System.out.println("Searching bigon in direction "+direction+" starting at segments "+this.toLetter.get(PL[SL[c1][i][0]][0])+""+PL[SL[c1][i][0]][1]+","+this.toLetter.get(PL[SL[c1][i][1]][0])+""+PL[SL[c1][i][1]][1]+" and "+this.toLetter.get(PL[SL[c2][j][0]][0])+""+PL[SL[c2][j][0]][1]+","+this.toLetter.get(PL[SL[c2][j][1]][0])+""+PL[SL[c2][j][1]][1]);}
		//build a bigon in the direction specified above
		if(direction==1)
		{
			int k=1;
	//		System.out.println("Direction is "+direction);
			for(int fk=1;fk<Math.max(WordLengths[c1],WordLengths[c2]);)
			{
			//	System.out.println(this.intersect(c1,c2,mod(c1,i+k), mod(c2,j+k)));
			//	System.out.println(this.split(c1,c2,mod(c1,i+k), mod(c2,j+k),direction));
				BL1.add(mod(c1,i+k));
				BL2.add(mod(c2,j+k));
				if(!this.split(c1,c2,mod(c1,i+k), mod(c2,j+k),direction) && !(this.intersect(c1,c2,mod(c1,i+k), mod(c2,j+k))))
				{
					//System.out.println("Keep adding");
					fk++;
					k++;
				}
				else fk=fk+Math.max(WordLengths[c1],WordLengths[c2]);   ///RIGHT HERE IS THE PROBLEM, replaced curvw1.length with max, need to probably do this elswhere as well
			}
			if (this.intersect(c1,c2,mod(c1,i+k), mod(c2,j+k)))
			{
				//System.out.println("these guys intersect");
				check=1;
			//	System.out.println(BL1.size()+" "+BL2.size());
				int permute = this.isProper(c1,c2,BL1,BL2,direction);
				if(permute==1) this.permutePoints(c1,c2,BL1,BL2,PL,direction);
				return permute;
			}		
			if (this.split(c1,c2,mod(c1,i+k),mod(c2,j+k),direction) && check==0)
			{
				BL1=new ArrayList();
				BL1.add(i);
				BL2=new ArrayList();
				BL2.add(j);
				if (this.split(c1,c2,i, j,4)==false){ direction=4;}
		//		oppdir=1;
			}
		}
		if(direction==4)
		{
			int k=1;
//			System.out.println("Direction is "+direction);
			for(int fk=1;fk<Math.max(WordLengths[c1],WordLengths[c2]);)
				{
					BL1.add(mod(c1,i-k));
					BL2.add(mod(c2,j-k));
					if(!this.split(c1,c2,mod(c1,i-k), mod(c2,j-k),direction) && !(this.intersect(c1,c2,mod(c1,i-k), mod(c2,j-k))))
					{
						fk++;
						k++;
					}
					else fk=fk+Math.max(WordLengths[c1],WordLengths[c2]);
				}			
			if (this.intersect(c1,c2,mod(c1,i-k), mod(c2,j-k)))
			{
				check=1;
				
				int permute = this.isProper(c1,c2,BL1,BL2,direction);
				if(permute==1) this.permutePoints(c1,c2,BL1,BL2, PL,direction);
				return permute;
					
			}
			if (this.split(c1,c2,mod(c1,i-k), mod(c2,j-k),direction)==true && check==0)
			{
				BL1=new ArrayList();
				BL1.add(i);
				BL2=new ArrayList();
				BL2.add(j);	
				return 0;
			}
		}
		if(direction==2)
		{
			int k=1;
		//	System.out.println("Direction is "+direction);
			for(int fk=1;fk<Math.max(WordLengths[c1],WordLengths[c2]);)
			{
				BL1.add(mod(c1,i+k));
				BL2.add(mod(c2,j-k));
				//System.out.println(c2+""+j+""+-k);
				if(!this.split(c1,c2,mod(c1,i+k), mod(c2,j-k),direction) && !(this.intersect(c1,c2,mod(c1,i+k),mod(c2,j-k))))
				{
					fk++;
					k++;
				}
				else fk=fk+Math.max(WordLengths[c1],WordLengths[c2]);
			}
			if (this.intersect(c1,c2,mod(c1,i+k),mod(c2,j-k)))
			{
				check=1;
				int permute = this.isProper(c1,c2,BL1,BL2,direction);
				if(permute==1) this.permutePoints(c1,c2,BL1,BL2, PL,direction);
				return permute;
			}	
			if (split(c1,c2,mod(c1,i+k),mod(c2,j-k),direction)==true && check==0)
			{
				BL1=new ArrayList();
				BL1.add(i);
				BL2=new ArrayList();
				BL2.add(j);	
				if (split(c1,c2,i,j,3)==false){ 
					direction=3;}
		//		oppdir=1;
			}
		
		}
		if(direction==3)
		{
			int k=1;			
			for(int fk=1;fk<Math.max(WordLengths[c1],WordLengths[c2]);)
			{
				BL1.add(mod(c1,i-k));
				BL2.add(mod(c2,j+k));
				if(!this.split(c1,c2,mod(c1,i-k),mod(c2,j+k),direction) && !(this.intersect(c1,c2,mod(c1,i-k),mod(c2,j+k))))
				{
					fk++;
					k++;
				}
				else fk=fk+Math.max(WordLengths[c1],WordLengths[c2]);
			}

			if (this.intersect(c1,c2,mod(c1,i-k),mod(c2,j+k)))
			{
				check=1;
	//			System.out.println("Intersect again");
				int permute = this.isProper(c1,c2,BL1,BL2,direction);
				if(permute==1) this.permutePoints(c1,c2,BL1,BL2, PL,direction);
				return permute;
			}
			if (!this.split(c1,c2,mod(c1,i-k),mod(c2,j+k),direction) && check==0)
			{
				BL1=new ArrayList();
				BL1.add(i);
				BL2=new ArrayList();
				BL2.add(j);		
				return 0;
			}
		}
		return 0;
	}
	
	public int isProper(int c1, int c2,ArrayList a, ArrayList b, int d)
	{
	//	System.out.println("Checking for proper");
	//	System.out.println(c1+" "+c2);
		System.out.println(a.size()+" "+b.size());
		if(c1!=c2) return 1;
	//	a.printList();
	//	System.out.println();
	//	b.printList();
	//	System.out.println();
		//inefficient
		int count=0;
		for (int i=0;i<a.size();i++)
		{
			for (int j=0; j<b.size();j++)
			{
				if (a.get(i)==b.get(j)) count++;
			}
		}
		//System.out.println("Count is" + count);
		if (count>=2) return 0;
		else if (count==1)
		{
			//this is to make the leg with the common segment automatically the first one considered
			ArrayList a1=a;
			ArrayList b1=b;
			if (b.get(0)==a.get(b.size()-1))
			{ 	b1=a;
				a1=b;
			}
				/*stores the locations of all 8 points to reduce clutter in this section
				(p1,p2) (p3,p4)
				  .		.
				  .		.
				  .		.
				(p5,p6) (p7,p8)		
				*/		
			
			//define an array of int
			  Integer[] ia1  = new Integer[a1.size()];
			  Integer[] ib1  = new Integer[b1.size()];
			   //convert a List to Array
			   a1.toArray(ia1);
			   b1.toArray(ib1);
			   
				int p1=SL[c1][ia1[0].intValue()][0];
				int p2=SL[c1][ia1[0].intValue()][1];
				
				int p3=SL[c1][ib1[0].intValue()][0];  //error with aBB and bbbb ends here
				int p4=SL[c1][ib1[0].intValue()][1];
				int p5=SL[c1][ia1[a1.size()-1].intValue()][0];
				int p6=SL[c1][ia1[a1.size()-1].intValue()][1];
			//	int p7=SL[0][ib1[b1.size()-1].intValue()][0];
			//	int p8=SL[0][ib1[b1.size()-1].intValue()][1];
		//		System.out.println("checking for properness");
			if (d==1)
			{		
					if((p2<p6 && p6<p4 && p1<p3 && p3<p5)||(p2>p6 && p6>p4 && p1>p3 && p3>p5))
					{
						return 0;	
					}
			}
			else return 1;
			if (d==4)
			{
				if (a1.get(0)==b1.get(b1.size()-1))
				{
					if((p2<p4 && p4<p6 && p1<p5 && p5<p3)||(p2>p4 && p4>p6 && p1>p5 && p5>p3))
					{
						return 0;	
					}	
				}
			}
			else return 1;			
		}
		return 1;
	}	
	
	//takes in the two legs of a proper bigon along with the associated direction, and permutes the points in PL accordingly
	public void permutePoints(int c1, int c2,ArrayList a,ArrayList b, int[][] p,int direction)
	{
	//	System.out.println("Checking to permute");
		//define an array of int
		  Integer[] ia  = new Integer[a.size()];
		  Integer[] ib  = new Integer[b.size()];
		   //convert a List to Array
		   a.toArray(ia);
		   b.toArray(ib);				
		for (int i=0;i<a.size()-1;i++)
		{
	//	System.out.println("permuting");
			
			
			//in this implementation, one needs to permute SL[c_i] as well, since the elements of SL[c_i] are really just pointers to rows of PL
			if(direction==1)
			{
				//System.out.println(a.getSegment(i).getSecondPoint().getLetter()+" "+a.getSegment(i).getSecondPoint().getIndex());
				//System.out.println(b.getSegment(i).getSecondPoint().getLetter()+" "+b.getSegment(i).getSecondPoint().getIndex());
				this.permute(SL[c1][ia[i].intValue()][1],SL[c2][ib[i].intValue()][1]);
				this.permute(SL[c1][ia[i+1].intValue()][0],SL[c2][ib[i+1].intValue()][0]);
				int h0=SL[0][ia[i].intValue()][1];
				SL[c1][ia[i].intValue()][1]=SL[c2][ib[i].intValue()][1];
				SL[c2][ib[i].intValue()][1]=h0;
				int h1=SL[c1][ia[i+1].intValue()][0];
				SL[c1][ia[i+1].intValue()][0]=SL[c2][ib[i+1].intValue()][0];
				SL[c2][ib[i+1].intValue()][0]=h1;	
			}
			if(direction==4)
			{
				this.permute(SL[c1][ia[i].intValue()][0],SL[c2][ib[i].intValue()][0]);
				this.permute(SL[c1][ia[i+1].intValue()][1],SL[c2][ib[i+1].intValue()][1]);
				int h0=SL[c1][ia[i].intValue()][0];
				SL[c1][ia[i].intValue()][0]=SL[c2][ib[i].intValue()][0];
				SL[c2][ib[i].intValue()][0]=h0;
				int h1=SL[c1][ia[i+1].intValue()][1];
				SL[c1][ia[i+1].intValue()][1]=SL[c2][ib[i+1].intValue()][1];
				SL[c2][ib[i+1].intValue()][1]=h1;
			}
			if(direction==2)
			{
				this.permute(SL[c1][ia[i].intValue()][1],SL[c2][ib[i].intValue()][0]);
				this.permute(SL[c1][ia[i+1].intValue()][0],SL[c2][ib[i+1].intValue()][1]);
				
				int h0=SL[c1][ia[i].intValue()][1];
				SL[c1][ia[i].intValue()][1]=SL[c2][ib[i].intValue()][0];
				SL[c2][ib[i].intValue()][0]=h0;
				
				int h1=SL[c1][ia[i+1].intValue()][0];
				SL[c1][ia[i+1].intValue()][0]=SL[c2][ib[i+1].intValue()][1];
				SL[c2][ib[i+1].intValue()][1]=h1;
			}
			if(direction==3)
			{
				this.permute(SL[c1][ia[i].intValue()][0],SL[c2][ib[i].intValue()][1]);
				this.permute(SL[c1][ia[i+1].intValue()][1],SL[c2][ib[i+1].intValue()][0]);
				
				int h0=SL[c1][ia[i].intValue()][0];
				SL[c1][ia[i].intValue()][0]=SL[c2][ib[i].intValue()][1];
				SL[c2][ib[i].intValue()][1]=h0;
				
				int h1=SL[c1][ia[i+1].intValue()][1];
				SL[c1][ia[i+1].intValue()][1]=SL[c2][ib[i+1].intValue()][0];
				SL[c2][ib[i+1].intValue()][0]=h1;
			}
		}
	/*	System.out.println();
		System.out.println("Updated point list");
		printPoints();
		System.out.println();
		printSegments();
		System.out.println();
*/
	}
	
	//switches the locations in PL of rows i and j (recall that a row of PL represents a point)
	public void permute(int i, int j)
	{
		System.out.println("Permuting " + this.toLetter.get(PL[i][0])+""+PL[i][1]+" with "+this.toLetter.get(PL[j][0])+""+PL[j][1] );
		
		int h0=PL[i][0];
		int h1=PL[i][1];
		int h2=PL[j][0];
		int h3=PL[j][1];
		PL[i][0]=h2;
		PL[i][1]=h3;
		PL[j][0]=h0;
		PL[j][1]=h1;
		System.out.println("****");
	//	this.printPoints();
	//	System.out.println();
	//	printSegments();
	//	System.out.println();
	}
	public int getDone()
	{
		return done;
		
	}
	public void printPoints()
	{
		for (int i=0;i<2*(curvw1.length()+curvw2.length());i++)
		{
			System.out.println(this.toLetter.get(PL[i][0])+""+PL[i][1]);
		}
		
	}
	public void printSegments()
	{
		System.out.println("Curve 1");

			for (int i=0;i<curvw1.length();i++)
			{
				System.out.println(this.toLetter.get(PL[SL[0][i][0]][0])+""+PL[SL[0][i][0]][1]+" , "+this.toLetter.get(PL[SL[0][i][1]][0])+""+PL[SL[0][i][1]][1]);
			}
		System.out.println("Curve 2");
		
		for (int i=0;i<curvw2.length();i++)
		{
			System.out.println(this.toLetter.get(PL[SL[1][i][0]][0])+""+PL[SL[1][i][0]][1]+" , "+this.toLetter.get(PL[SL[1][i][1]][0])+""+PL[SL[1][i][1]][1]);
		}
		
	}
	//gives the "combinatorial length" of the current representative given by PL and SL[0]
	public int combLength()
	{
		int length=0;
		for(int i=0;i<curvw1.length();i++)
		{
			int kmax=Math.max(SL[0][i][0], SL[0][i][1]);
			int kmin=Math.min(SL[0][i][0], SL[0][i][1]);
			length=length+Math.min(kmax-kmin,Math.abs(kmin+2*curvw1.length()-kmax));
		//	System.out.println(kmax+" , "+kmin+" , "+length);
		}
		return length;
	}
	//returns a mod (length of word c)
	public int mod(int c,int a1)
	{
		int a=a1 % WordLengths[c];
			if (a>WordLengths[c]-1) return a % WordLengths[c];
			else if (a<0) return a+WordLengths[c];
			else return a;
		
	}
	//returns the number of points located on a given edge indexed by 'a'
	public int getNumPoints(int a)
	{
		int[] C = this.toNumber(curvw1+curvw2);
		int L=this.getSurfaceLength();
		M1 =new int[L];
		
		//counts how many of each pair there are
		for( int i = 0; i<L;i++)
		{
			for (int j = 0;j<curvw1.length()+curvw2.length();j++)
			{
				if ( C[j] == i)
				 {
					M1[i]++;
				 }
			}
		//	System.out.println(M[i]);
		}
		return M1[a]+M1[this.barN.get(a)];
		
	}
	public void easierList()
	{
		int k=0;
		for (int i = 0;i<surfw.length();i++)
		{
			boolean used;
			int con = this.barN.get(i);
			used=(con<i);
			if (!used)
			{
				for(int j=0;j<M[i]+M[con];j++)
				{	
					PL[k][0]=i;
					PL[k][1]=j;
					k++;
				}
			}
			else 
			{
				for(int j=M[i]+M[con]-1;j>=0;j--)
				{	
					PL[k][0]=i;
					PL[k][1]=j;
					k++;
				}
			}
		
		
		}
	}
	public void updateSLComb()
	{
		for(int l=0;l<curvw1.length()+curvw2.length();l++)
		{
			if(l<curvw1.length()){ 
				SLComb[l][0]=SL[0][l][0];
				SLComb[l][1]=SL[0][l][1];
			}
			if(l>=curvw1.length()){ 
				SLComb[l][0]=SL[1][l-curvw1.length()][0];
				SLComb[l][1]=SL[1][l-curvw1.length()][1];
			}
		}
	}
	
	
}
