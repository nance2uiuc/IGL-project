package boundary;

import java.util.*;
public class Main{
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Scanner a = new Scanner(in.nextLine());
		ArrayList<Integer> ra = new ArrayList<Integer>();
		ArrayList<Integer> wa = new ArrayList<Integer>();
		int n =Integer.parseInt(a.nextLine());
		int[][] r = new int[n][];
		for(int i=0;i<n;i++){
			a = new Scanner(in.nextLine());
			while(a.hasNext()){
				ra.add(a.nextInt());
			}
			int[] z = new int[ra.size()];
			for(int j=0;j<ra.size();j++){
				z[j] = ra.get(j);
			}
			r[i] = z;
		}

		a = new Scanner(in.nextLine());
		while(a.hasNext()){
			wa.add(a.nextInt());
		}

		int[] w = new int[wa.size()];
		for(int i=0;i<wa.size();i++){
			w[i] = wa.get(i);
		}

		p(reduce(r,w));
	}
	public static int[] reduce(int[][] r, int[] w){
		for(int i=0;i<r.length;i++){
		//cyclic shift
		for(int z=0;z<r[i].length;z++){
			r[i] = shift(r[i]);
			for(int n=r[i].length/2+1;n<=r[i].length;n++){
				//create slice
				int[] t = Arrays.copyOfRange(r[i],0,n);
				int a = exist(t,w);
				if(a!=-1){
					int[] l = {9,9};
					w = replace(w,a,a+n,inverse(Arrays.copyOfRange(r[i],n,r[i].length)));
					return reduce(r,w);
				}
			}
		}
		}
		return w;
	}
	public static int[] inverse(int[] a){
		int[] b = new int[a.length];
		for(int i=0;i<a.length;i++){
			b[a.length-1-i] = -a[i];
		}
		return b;
	}
	public static int[] shift(int[] a){
		int[] b = new int[a.length];
		for(int i=0;i<a.length-1;i++){
			b[i] = a[i+1];
		}
		b[a.length-1] = a[0];
		return b;
	}
	public static void p(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}
	public static int exist(int[] t, int[] w){
		for(int i=0;i<=w.length-t.length;i++){
			boolean x = true;
			for(int j=0;j<t.length;j++){
				if(t[j] != w[i+j]){
					x = false;
					break;
				}
			}
			if(x){
				return i; 
			}
		}
		return -1;
	}
	public static int[] free_reduce(ArrayList<Integer> t){
		for(int i=0;i<t.size()-1;i++){
			while(t.get(i) == -t.get(i+1)){
				t.remove(i);
				t.remove(i);
				if(i+1 >= t.size()){
					break;
				}
			}
		}
		while(t.get(0) == -t.get(t.size()-1)){
			t.remove(0);
			t.remove(t.size()-1);
		}

		int[] r = new int[t.size()];
		for(int i=0;i<t.size();i++){
			r[i] = t.get(i);
		}
		return r;
	}
	public static int[] replace(int[] w, int a, int b, int[] n){
		ArrayList<Integer> t = new ArrayList<Integer>();
		for(int i=0;i<a;i++){
			t.add(w[i]);
		}
		for(int i=0;i<n.length;i++){
			t.add(n[i]);
		}
		for(int i=b;i<w.length;i++){
			t.add(w[i]);
		}
		return free_reduce(t);
	}
}
