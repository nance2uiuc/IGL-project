package computations;
import java.util.ArrayList;


public class NewComputation extends SurfaceWord
{
int[][] PL;
int[][] SL;
int[] M;
ArrayList BL1;
ArrayList BL2;


String surfw;
String curvw;
int done;
int minInt;


	public NewComputation()
	{
	super("");
	}

	public NewComputation(String sw, String cw)
	{
		super(sw);
		surfw=new String(sw);
		curvw=new String(cw);
		minInt=0;
		//construct point list according to rules inherent in sw and cw
		SL=new int[cw.length()][2];
		PL=new int[2*cw.length()][2];
		int[] C = this.toNumber(cw);
		int L=this.getSurfaceLength();
		M =new int[L];
		
		
		for(int i=0;i<=5;i++)
		{
			for(int j=0;j<=3;j++)
			{
				int k=i&j;
				System.out.println(i+" "+j+"  "+k);
			}
		}
		//counts how many of each pair there are
		for( int i = 0; i<L;i++)
		{
			for (int j = 0;j<cw.length();j++)
			{
				if ( C[j] == i)
				 {
					M[i]++;
				 }
			}
			//System.out.println(M[i]);
		}
		//System.out.println();
		//this tells you which row of PL the first occurrence of point with letter i is located at;
		int[] start=new int[L];
		start[0]=0;
	//	System.out.println(L);
		for(int i=1;i<L;i++)
		{
			start[i]=start[i-1]+M[i-1]+M[this.barN.get(i-1)];
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

		//constructs the list of segments
		int LL=cw.length();
		int[] avail=new int[sw.length()];
		int h = this.barN.get(C[LL-1]);
			SL[0][0]=this.findPoint(h,M[C[LL-1]]+M[h]-1);
			SL[0][1]=findPoint(C[0],avail[C[0]]);	
			avail[C[0]]++;
			avail[this.barN.get(C[0])]++;
			for(int kk=1;kk<LL;kk++)
			{
				SL[kk][0]=this.findPoint(this.barN.get(PL[SL[kk-1][1]][0]),avail[PL[SL[kk-1][1]][0]]-1);
				SL[kk][1]=this.findPoint(C[kk],avail[C[kk]]);		
				avail[C[kk]]++;
				avail[this.barN.get(C[kk])]++;
			}
		//System.out.println("This should only appear once");
	}
	public int[][] getPL()
	{
		return PL;
	}
	
	public int[][] getSL()
	{
		return SL;
	}
	
	//returns row of PL containing letter a, index b;
	public int findPoint(int a, int b)
	{
	//	System.out.println(curvw);
		for(int j=0;j<(2*curvw.length());j++)
		{
			if (PL[j][0]==a && PL[j][1]==b) return j;
		}
	    return -1;
	}
	
	//returns true if segments i and j split in direction d, false if they don't
	public boolean split(int i, int j, int d)
	{
		if (d==1)
		{
			//System.out.println(a.getSecondPoint().getLetter()+" boooo "+b.getFirstPoint().getLetter());
			return !(PL[SL[i][1]][0]== PL[SL[j][1]][0]);	
		}
		if (d==2)
		{
		//	System.out.println(a.getSecondPoint().getLetter()+" boooo "+b.getFirstPoint().getLetter());
			return !(PL[SL[i][1]][0]== PL[SL[j][0]][0]);
					//a.getSecondPoint().getLetter()== b.getFirstPoint().getLetter());
		}
		if (d==3)
		{
		//	System.out.println("Checking segments "+i+" and "+ j+" in direction 3");
			return !(PL[SL[i][0]][0]== PL[SL[j][1]][0]);
		}
		if (d==4)
		{
			return !(PL[SL[i][0]][0]== PL[SL[j][0]][0]);
		}
		else return false;
	}
	//determines if two "segment words" SL[a] and SL[b] intersect
	public boolean intersect(int a,int b)
	{
		//first figure out which is closer to the beginning of PL
		int c;
		int[] rows=new int[4];	
		rows[0]=SL[a][0];
		rows[1]=SL[a][1];
		rows[2]=SL[b][0];
		rows[3]=SL[b][1];
		int l = 0;
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
		//System.out.println(PL[SL[c][0]][0]+" "+PL[SL[c][0]][1]+"  ,  "+PL[SL[c][1]][0]+" "+PL[SL[c][1]][1]);
		int i1=SL[c][0];
		int i2=SL[c][1];
		int j1=SL[d][0];
		int j2=SL[d][1];
		if(i1<j1 && j1<i2 && i2<j2) return true;
		if(i1<j2 && j2<i2 && i2<j1) return true;
		if(i2<j1 && j1<i1 && i1<j2) return true;
		if(i2<j2 && j2<i1 && i1<j1) return true;
		else return false;

	}
	public void findIntersection()
	{
		int count=0;		
		for (int i=0;i<curvw.length();i++)
		{
			//System.out.println(C.getLength());
			for(int j=i+1;j<curvw.length();j++)
			{
				//System.out.println(i+"  "+j);
				if (this.intersect(i,j)) 
				{
				//	C.getSegment(i).printWord();
				//	C.getSegment(j).printWord();
				//	System.out.println("intersect");
				count++;	
	//			System.out.println("Checking segments "+i+" and "+j);
					if (this.potentialBigon(i,j)==1)
						{
						//	System.out.println("Checking segments "+i+" and "+j);
							return;
						}
				}
			}
		}
	//	System.out.println("found no bigons or intersections");
		//this.getPL().printList();
	//	System.out.println(" ");
		//this.getSL().printList();
	//	System.out.println("********************************************************");
		this.easierList();
		done=1;
		
	//	System.out.println("found no more bigons to remove");
	//	printPoints();
//		System.out.println("********************************************************");
//		printSegments();
//		System.out.println("********************************************************");
//		System.out.println("There are "+count+" intersections.");
//		System.out.println();
		//*/
		minInt=count;
	}
	public int getMinInt()
	{
		return minInt;
	}
	
	//returns 1 if the potential bigon is a proper bigon, 0 otherwise;  
	
	public int potentialBigon(int i, int j)
	{
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
			if (!this.split(i,j,1)) {direction=1; founddir=1;}
		}
		if(founddir==0)
		{
			if (!this.split(i,j,2)) {direction=2; founddir=1;}
		}
		if(founddir==0)
		{
			if (!this.split(i,j,3)) {direction=3; founddir=1;}
		}
		if(founddir==0)
		{
			if (!this.split(i,j,4)){ direction=4; founddir=1;}
		}
		//build a bigon in the direction specified above
		if(direction==1)
		{
			int k=1;
	//		System.out.println("Direction is "+direction);
			for(int fk=1;fk<curvw.length();)
			{
				BL1.add(mod(i+k));
				BL2.add(mod(j+k));
				if(!this.split(mod(i+k), mod(j+k),direction) && !(this.intersect(mod(i+k), mod(j+k))))
				{
					fk++;
					k++;
				}
				else fk=fk+curvw.length();
			}
			if (this.intersect(mod(i+k), mod(j+k)))
			{
				check=1;
				int permute = this.isProper(BL1,BL2,direction);
				if(permute==1) this.permutePoints(BL1,BL2,PL,direction);
				return permute;
			}		
			if (this.split(mod(i+k),mod(j+k),direction) && check==0)
			{
				BL1=new ArrayList();
				BL1.add(i);
				BL2=new ArrayList();
				BL2.add(j);
				if (this.split(i, j,4)==false){ direction=4;}
		//		oppdir=1;
			}
		}
		if(direction==4)
		{
			int k=1;
//			System.out.println("Direction is "+direction);
				for(int fk=1;fk<curvw.length();)
				{
					BL1.add(mod(i-k));
					BL2.add(mod(j-k));
					if(!this.split(mod(i-k), mod(j-k),direction) && !(this.intersect(mod(i-k), mod(j-k))))
					{
						fk++;
						k++;
					}
					else fk=fk+curvw.length();
				}			
			if (this.intersect(mod(i-k), mod(j-k)))
			{
				check=1;
				
				int permute = this.isProper(BL1,BL2,direction);
				if(permute==1) this.permutePoints(BL1,BL2, PL,direction);
				return permute;
					
			}
			if (this.split(mod(i-k), mod(j-k),direction)==true && check==0)
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
			for(int fk=1;fk<curvw.length();)
			{
				BL1.add(mod(i+k));
				BL2.add(mod(j-k));
				if(!this.split(mod(i+k), mod(j-k),direction) && !(this.intersect(mod(i+k),mod(j-k))))
				{
					fk++;
					k++;
				}
				else fk=fk+curvw.length();
			}
			if (this.intersect(mod(i+k),mod(j-k)))
			{
				check=1;
				int permute = this.isProper(BL1,BL2,direction);
				if(permute==1) this.permutePoints(BL1,BL2, PL,direction);
				return permute;
			}	
			if (split(mod(i+k),mod(j-k),direction)==true && check==0)
			{
				BL1=new ArrayList();
				BL1.add(i);
				BL2=new ArrayList();
				BL2.add(j);	
				if (split(i,j,3)==false){ 
					direction=3;}
		//		oppdir=1;
			}
		
		}
		if(direction==3)
		{
			int k=1;			
			for(int fk=1;fk<curvw.length();)
			{
				BL1.add(mod(i-k));
				BL2.add(mod(j+k));
				if(!this.split(mod(i-k),mod(j+k),direction) && !(this.intersect(mod(i-k),mod(j+k))))
				{
					fk++;
					k++;
				}
				else fk=fk+curvw.length();
			}

			if (this.intersect(mod(i-k),mod(j+k)))
			{
				check=1;
	//			System.out.println("Intersect again");
				int permute = this.isProper(BL1,BL2,direction);
				if(permute==1) this.permutePoints(BL1,BL2, PL,direction);
				return permute;
			}
			if (!this.split(mod(i-k),mod(j+k),direction) && check==0)
			{
				BL1=new ArrayList();
				BL1.add(i);
				BL2=new ArrayList();
				BL2.add(j);		
				return 0;
			}
		}
		//System.out.println("hmm didn't seem to find anything proper");
		return 0;
	}
	public int isProper(ArrayList a, ArrayList b, int d)
	{
		
	//	System.out.println("Checking for proper");
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
			   
				int p1=SL[ia1[0].intValue()][0];
				int p2=SL[ia1[0].intValue()][1];
				int p3=SL[ib1[0].intValue()][0];
				int p4=SL[ib1[0].intValue()][1];
				int p5=SL[ia1[a1.size()-1].intValue()][0];
				int p6=SL[ia1[a1.size()-1].intValue()][1];
			//	int p7=SL[ib1[b1.size()-1].intValue()][0];
			//	int p8=SL[ib1[b1.size()-1].intValue()][1];
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
	public void permutePoints(ArrayList a,ArrayList b, int[][] p,int direction)
	{

		//define an array of int
		  Integer[] ia  = new Integer[a.size()];
		  Integer[] ib  = new Integer[b.size()];
		   //convert a List to Array
		   a.toArray(ia);
		   b.toArray(ib);				
		for (int i=0;i<a.size()-1;i++)
		{
	//	System.out.println("permuting");
			
			
			//in this implementation, one needs to permute SL as well, since the elements of SL are really just pointers to rows of PL
			if(direction==1)
			{
				//System.out.println(a.getSegment(i).getSecondPoint().getLetter()+" "+a.getSegment(i).getSecondPoint().getIndex());
				//System.out.println(b.getSegment(i).getSecondPoint().getLetter()+" "+b.getSegment(i).getSecondPoint().getIndex());
				this.permute(SL[ia[i].intValue()][1],SL[ib[i].intValue()][1]);
				this.permute(SL[ia[i+1].intValue()][0],SL[ib[i+1].intValue()][0]);
				int h0=SL[ia[i].intValue()][1];
				SL[ia[i].intValue()][1]=SL[ib[i].intValue()][1];
				SL[ib[i].intValue()][1]=h0;
				int h1=SL[ia[i+1].intValue()][0];
				SL[ia[i+1].intValue()][0]=SL[ib[i+1].intValue()][0];
				SL[ib[i+1].intValue()][0]=h1;	
			}
			if(direction==4)
			{
				this.permute(SL[ia[i].intValue()][0],SL[ib[i].intValue()][0]);
				this.permute(SL[ia[i+1].intValue()][1],SL[ib[i+1].intValue()][1]);
				int h0=SL[ia[i].intValue()][0];
				SL[ia[i].intValue()][0]=SL[ib[i].intValue()][0];
				SL[ib[i].intValue()][0]=h0;
				int h1=SL[ia[i+1].intValue()][1];
				SL[ia[i+1].intValue()][1]=SL[ib[i+1].intValue()][1];
				SL[ib[i+1].intValue()][1]=h1;
			}
			if(direction==2)
			{
				this.permute(SL[ia[i].intValue()][1],SL[ib[i].intValue()][0]);
				this.permute(SL[ia[i+1].intValue()][0],SL[ib[i+1].intValue()][1]);
				
				int h0=SL[ia[i].intValue()][1];
				SL[ia[i].intValue()][1]=SL[ib[i].intValue()][0];
				SL[ib[i].intValue()][0]=h0;
				
				int h1=SL[ia[i+1].intValue()][0];
				SL[ia[i+1].intValue()][0]=SL[ib[i+1].intValue()][1];
				SL[ib[i+1].intValue()][1]=h1;
			}
			if(direction==3)
			{
				this.permute(SL[ia[i].intValue()][0],SL[ib[i].intValue()][1]);
				this.permute(SL[ia[i+1].intValue()][1],SL[ib[i+1].intValue()][0]);
				
				int h0=SL[ia[i].intValue()][0];
				SL[ia[i].intValue()][0]=SL[ib[i].intValue()][1];
				SL[ib[i].intValue()][1]=h0;
				
				int h1=SL[ia[i+1].intValue()][1];
				SL[ia[i+1].intValue()][1]=SL[ib[i+1].intValue()][0];
				SL[ib[i+1].intValue()][0]=h1;
			}
		}
	//	System.out.println();
	//	System.out.println("Updated point list");
	//	printPoints();
	//	System.out.println();
	//	printSegments();
	//	System.out.println();

	}
	//switches the locations in PL of rows i and j (recall that a row of PL represents a point)
	public void permute(int i, int j)
	{
		int h0=PL[i][0];
		int h1=PL[i][1];
		int h2=PL[j][0];
		int h3=PL[j][1];
		PL[i][0]=h2;
		PL[i][1]=h3;
		PL[j][0]=h0;
		PL[j][1]=h1;
		
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
		for (int i=0;i<2*curvw.length();i++)
		{
			System.out.println(this.toLetter.get(PL[i][0])+""+PL[i][1]);
		}
		
	}
	public void printSegments()
	{
		for (int i=0;i<curvw.length();i++)
		{
			System.out.println(this.toLetter.get(PL[SL[i][0]][0])+""+PL[SL[i][0]][1]+" , "+this.toLetter.get(PL[SL[i][1]][0])+""+PL[SL[i][1]][1]);
		}
		
	}
	//gives the "combinatorial length" of the current representative given by PL and SL
	public int combLength()
	{
		int length=0;
		for(int i=0;i<curvw.length();i++)
		{
			int kmax=Math.max(SL[i][0], SL[i][1]);
			int kmin=Math.min(SL[i][0], SL[i][1]);
			length=length+Math.min(kmax-kmin,Math.abs(kmin+2*curvw.length()-kmax));
		//	System.out.println(kmax+" , "+kmin+" , "+length);
		}
		return length;
	}
	public int mod(int a)
	{
			if (a>curvw.length()-1) return a % curvw.length();
			else if (a<0) return a+curvw.length();
			else return a;
		
	}
	//returns the number of points located on a given edge indexed by 'a'
	public int getNumPoints(int a)
	{
		int[] C = this.toNumber(curvw);
		int L=this.getSurfaceLength();
		M =new int[L];
		
		//counts how many of each pair there are
		for( int i = 0; i<L;i++)
		{
			for (int j = 0;j<curvw.length();j++)
			{
				if ( C[j] == i)
				 {
					M[i]++;
				 }
			}
		//	System.out.println(M[i]);
		}
		return M[a]+M[this.barN.get(a)];
		
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

	
	
}
