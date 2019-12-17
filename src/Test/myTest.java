package Test;
import java.util.Random;

import dataStructure.*;
import utils.*;

public class myTest 
{
	public static void main(String[] args)
	{
		DGraph g = new DGraph();
		for(int i=1; i<=7;i++) // add 1,2..7 vertex
		{
			Vertex x = new Vertex(i,0,null,0);
			g.addNode(x);
		}
		/*for(int i=1;i<=7;i++)
		{
			for(int j=0;j<3;j++)
			{
				Random r = new Random();
				int dest = r.nextInt((7 - 1) + 1) + 1;
				g.connect(i, dest, 0);
			}
		}*/
		for(int i=1; i<=3;i++)
		{
			for(int j=0;j<2;j++)
			{
				g.connect(i,i+1+j,0);
			}

		}
		System.out.println(g);
		//Delete ver 3
		System.out.println("Vertex 3 deleted...");
		g.removeNode(3);
		System.out.println(g);
	}
	
}
