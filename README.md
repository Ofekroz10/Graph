**Ariel OOP - Ex2 The Maze of Waze**
***
<a href="https://ibb.co/dD0pq0F"><img src="https://i.ibb.co/sqv1Gvn/Untitled.png" alt="Untitled" border="0"></a><br /><br/>
*example of graph with 20 vertices.

**Introduction**
***
This project is part of assignment in Ariel University. Main purpose of the project is to create directed weighted graph and display it on graphic user interface , and run algorithms on the graph.

**How to use this project**
***
First of all, create an instance of graph
```java
graph g = new DGraph(20); // create directed weighted graph contains 20 vertices
```
Or you can create empty graph and add vertices your self
```java
graph g = new DGraph(); 
//Create 3 vertecies
DGraph g = new DGraph();
g.addNode(new Vertex(1,new Point3D(30,500)));
g.addNode(new Vertex(2,new Point3D(270,80)));
g.addNode(new Vertex(3,new Point3D(50,100)));
g.addNode(new Vertex(4,new Point3D(250,250)));
g.addNode(new Vertex(5,new Point3D(500,250)));
g.addNode(new Vertex(6,new Point3D(450,550)));
```
Connect vertices by edges:
```java
g.connect(1,3,14); //connect vertices 1,3 , the weight of the edge is 14.
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
g.connect(5,2,6);
g.connect(5,4,5);
g.connect(5,6,5);
g.connect(2,3,5);
g.connect(2,5,5);
```
And create an instance of GraphicWin 
```java
GraphicWin win = new GraphicWin(g); //Give to the constructor the graph
```
*You can see this file in the package examples
And then run the project, you will see the gui. 

**How to use gui**
***
The menu bar contains:
1. File: you can save this graph in .graph format, and load graphs from this format. You can create new graph by using the gui, just 
click on File->create new graph. First enter the number of vertices, and then you can connect the vertices by the combo box and connect 
button or create a random graph contains (2*vertice)s edges.
2.Algorithms: You can run algorithms and see the result in the screen.
3.Edit graph: You can add vertex or remove vertex or add edge or remove edge.

**Examples**
***
Graph Algo class
```java
Graph_Algo graphA = new Graph_Algo();
graphA.init(g);
graphA.shortestPath(2,5);
System.out.println(graphA.copy());
System.out.println(graphA.isConnected());
System.out.println(graphA.shortestPathDist(1, 2));
LinkedList<Integer> targets = new LinkedList<Integer>();
targets.add(1);
targets.add(4);
targets.add(3);
targets.add(2);
List<node_data> l2 = garphA.TSP(targets);
```
Edge class
```java
int src = 1;
int dest = 2;
double weight = 5.3;
Edge e = new Edge(src,dest,weight);
```

