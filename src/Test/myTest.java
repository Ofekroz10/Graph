package Test;
import java.util.Random;
import algorithms.*;
import dataStructure.*;
import utils.*;

public class myTest 
{
	public static void main(String[] args)
	{	DGraph g = new DGraph();
	for(int i=1; i<=6;i++) // add 1,2..7 vertex
	{
		Vertex x = new Vertex(i,0,null,0);
		g.addNode(x);
	}
	g.connect(1,3,14);
	g.connect(1,4,9);
	g.connect(1,6,7);
	g.connect(3,1,14);
	g.connect(3,2,9);
	g.connect(3,4,2);
	g.connect(4,5,11);
	g.connect(4,3,2);
	g.connect(4,6,10);
	g.connect(4,1,9);
	g.connect(6,4,10);
	g.connect(6,5,15);
	g.connect(3,2,9);
	//make well connected
	g.connect(5,2,6);
	g.connect(2,5,6);
	g.connect(2,3,6);
	g.connect(5,6,6);
	
	/*for(int i=1;i<=7;i++)
	{
		for(int j=0;j<3;j++)
		{
			Random r = new Random();
			int dest = r.nextInt((7 - 1) + 1) + 1;
			g.connect(i, dest, 0);
		}
	}*/
	/*
	for(int i=1; i<=3;i++)
	{
		for(int j=0;j<2;j++)
		{
			g.connect(i,i+1+j,0);
		}

	}*/
	System.out.println(g);
	Graph_Algo graphA = new Graph_Algo();
	graphA.init(g);
	System.out.println(graphA.copy());
	System.out.println(graphA.shortestPath(1,2));
	System.out.println(graphA.isConnected());

	}
	
}
