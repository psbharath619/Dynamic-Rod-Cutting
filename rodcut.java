import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;


class rodcut
{

	static public int computeprice(int[] p,int n)
	{
		//System.out.println("fun1"+n);
		if(n<=0)
			{return 0;}

		int max = -1;		
		for(int i=1;i<=n;i++)
			max = Math.max(max, p[i] + computeprice(p,n-i));
		return max;
	}


	static public int computeprice_dyn(int[] p,int n)
	{			

		//System.out.println("fun2");		
		int[] solution = new int[n + 1];
		solution[0] = 0;

		for (int i = 1; i <= n; i++) 
		{
			int max = -1;
			for (int j = 1; j <=i; j++) 
			{
				max = Math.max(max, p[j] + solution[i - j]);
				solution[i] = max;
			}
		}
		return solution[n];

	}

	public static void main(String args[])
	{
		Random ran=new Random();
		String path="1.txt";
		FileInputStream in=null;
		long endTime, startTime;
		int[] p = new int[51]; 
		//int[] p={0,1,5,8,9,10,17,17,20,24,30};
		p[0]=0;
		for(int i=1;i<=50;i++)
		{
			p[i]=ran.nextInt(20);		
		}

		try
		{
		BufferedReader br = new BufferedReader(new FileReader(path));
		in = new FileInputStream(path);

		String c;
		int count=1;
		System.out.println();
		System.out.println("#SNo\t\tBF\t\tDYN");
		System.out.println();
		while((c=br.readLine())!=".")
		{	
			int x=Integer.parseInt(c);

			//System.out.println(x	+"\t");
			
			System.out.print(count+"\t");	
			startTime =System.currentTimeMillis();
			computeprice(p,x);
			//System.out.print(computeprice(p,x)+"\t");
			endTime = System.currentTimeMillis();
			//System.out.print("\t-\t");
			System.out.print((endTime-startTime)/ 1000.0 +"\t");

			startTime =System.currentTimeMillis();
			computeprice_dyn(p,x);
			//System.out.println(computeprice_dyn(p,x));

			endTime = System.currentTimeMillis();
			
			System.out.println((endTime-startTime)/ 1000.0);
			count++;
		}
		System.out.println();
		in.close();
		}
			
		catch(Exception e)
		{		
			System.out.println(e);
			//System.out.println();
			//System.out.println("Program Ended Successfully.");
		}


	}
}