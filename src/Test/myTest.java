package Test;
import algorithms.*;
import dataStructure.*;
import utils.*;

public class myTest 
{
	public static void main(String[] args)
	{	
		
	DGraph g = new DGraph();
	g.addNode(new Vertex(1,new Point3D(30,500)));
	g.addNode(new Vertex(2,new Point3D(270,80)));
	g.addNode(new Vertex(3,new Point3D(50,100)));
	g.addNode(new Vertex(4,new Point3D(250,250)));
	g.addNode(new Vertex(5,new Point3D(500,250)));
	g.addNode(new Vertex(6,new Point3D(450,550)));
	g.connect(1,3,14);
	g.connect(1,4,9);
	g.connect(1,6,7);
	g.connect(3,1,14);
	g.connect(3,2,9);
	g.connect(3,4,2);
	g.connect(4,5,11);
	g.connect(4,3,2);
	g.connect(4,6,5);
	g.connect(4,1,9);
	g.connect(6,4,10);
	g.connect(6,5,15);
	g.connect(3,2,9);
	//make well connected
	g.connect(5,2,6);
	g.connect(5,4,5);
	g.connect(5,6,5);
	g.connect(2,3,5);
	g.connect(2,5,5);

	
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

	}
	*/
	//g =(DGraph) createRandomGraph(20,40);
	//System.out.println(g);
	Graph_Algo graphA = new Graph_Algo();
	GraphicWin win = new GraphicWin(g);
	graphA.init(g);
	graphA.shortestPath(2,5);
	System.out.println(graphA.copy());
	
	System.out.println(graphA.shortestPath(1,1));
	System.out.println(graphA.isConnected());
	System.out.println(graphA.shortestPathDist(1, 2));
	graph_algorithms f = new Graph_Algo();
	System.out.println(((Graph_Algo) f).getGraph());
	/*graph g = createGraph(100);
	GraphicWin win = new GraphicWin(g);
	
	}
	static graph createGraph(int num)
	{
		graph g = new DGraph();
		
		int low = 0;
		int high = 750;
		
		for (int i = 1; i <= num; i++) {
			int x = (int) (Math.random() * (high - low)) + low;
			int y = (int) (Math.random() * (high - low)) + low;
			g.addNode(new Vertex(i,new Point3D(x,y)));
		}
		return g;
	}*/
	try {
		Thread.sleep(1000);
	}
	catch(Exception e)
	{
		
	}
	edge_data c= new Edge(2,5,4);

}
	static graph createRandomGraph(int num,int edges)
	{
		graph g = new DGraph();
		
		int low = 50;
		int high = 750;
		
		for (int i = 1; i <= num; i++) {
			int x = (int) (Math.random() * (high - low)) + low;
			int y = (int) (Math.random() * (high - low)) + low;
			g.addNode(new Vertex(i,new Point3D(x,y)));
		}
		low=1;
		high=num;
		for (int i = 0; i < edges; i++) {
			int x = (int) (Math.random() * (high - low)) + low;
			int y = (int) (Math.random() * (high - low)) + low;
			int  w= (int) (Math.random() * (100 - 1)) + 1;
			g.connect(x,y,w);
		}
		return g;
	}
	}
