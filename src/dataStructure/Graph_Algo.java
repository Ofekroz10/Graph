package dataStructure;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import dataStructure.graph;
import dataStructure.node_data;
import java.util.Map;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */

	
public class Graph_Algo implements graph_algorithms{
	
	public static final int INFI = Integer.MAX_VALUE;
	private HashMap<Integer,node_data> verMap;
	private HashMap<Integer,LinkedList<edge_data>> edges;
	public static final Comparator<node_data> _Comp = new VerComperator();
	public static Comparator<node_data> getComp() {return _Comp;}
	
	@Override
	public void init(graph g) {
		verMap = new HashMap<Integer,node_data>();
		 edges = new HashMap<Integer,LinkedList<edge_data>>();
		 //Deep copy of verMap
		 LinkedList<node_data> vers = (LinkedList<node_data>) g.getV();
		 for (node_data data : vers) 
			verMap.put(data.getKey(),((Vertex)data).copy());
		 //Deep copy of edges
		 for(int i=1;i<=verMap.size();i++)
		 {
			 LinkedList<edge_data> curEdge = (LinkedList<edge_data>) g.getE(i);
			 if(curEdge!=null)
			 {
				 for (edge_data e : curEdge) {
					if(edges.get(i)==null)
					{
						LinkedList<edge_data> lst = new LinkedList<edge_data>();
						lst.add(((Edge)e).copy());//O(1)
						edges.put(i,lst); 
					}
					else
					{
						edges.get(i).add(((Edge)e).copy());
					}
				}
			 }
		 }
		
	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		LinkedList<node_data> path = new LinkedList<node_data>();
		PriorityQueue<node_data> queue = new PriorityQueue<node_data>(new VerComperator());
		if(verMap.get(src)==null)
			throw new ArithmeticException("src is not exist!");
		if(verMap.get(dest)==null)
			throw new ArithmeticException("dest is not exist!");
		if(src==dest)
		{
			path.add(verMap.get(src));
			return path;
		}
		Vertex start= null;
		//make cur node.distance to 0, node.father = null
		 Iterator it = verMap.entrySet().iterator();
		    while (it.hasNext()) { //O(V)
		        Map.Entry pair = (Map.Entry)it.next();
		       Vertex v= (Vertex)pair.getValue();
		       queue.add(v);
		       if(v.getKey()==src)
		       {
		    	   v.setDisFromStart(0);
		    	   start = v;
		       }
		       else
		    	   v.setDisFromStart(INFI);
		       v.setFather(null);
		       v.setTag(0);
		    }
		   
		    Vertex destV = null;
		    while(queue.size()!=0)
		    {
		    	queue.add(queue.remove());//Update the queue
		    	Vertex v = (Vertex) queue.poll();
		    	v.setTag(1);
		    	if(v.getKey()!=dest)
		    		hakala(v);
		    	else
		    		destV = v;
		    }
		    if(destV!=null)
		    {
			    path.addLast(destV);
			    Vertex current = destV;
			    while(current.getFather()!=null)
			    {
			    	path.addLast(current.getFather());
			    	current = (Vertex)current.getFather();
			    }
		    }
		return path;
		
	}

	private void hakala(Vertex v) {
		LinkedList<edge_data> nei =edges.get(v.getKey());
		for (edge_data e : nei) {
			Vertex curNei  = (Vertex) verMap.get(e.getDest());
			double x = v.getDisFromStart()+e.getWeight();
			Vertex curNeiVer = (Vertex) verMap.get(e.getDest());
			if(curNeiVer.getDisFromStart()>x &&curNeiVer.getTag()==0)
			{
				curNeiVer.setFather(v);
				curNeiVer.setDisFromStart(x);
			}
		}
		
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		DGraph g = new DGraph();
		Iterator it = verMap.entrySet().iterator();
		 while (it.hasNext()) { //O(V)
		        Map.Entry pair = (Map.Entry)it.next();
		       Vertex v= (Vertex)pair.getValue();
		       g.addNode(v.copy());
		 }
		 it = edges.entrySet().iterator();
		 while (it.hasNext()) { //O(E)
		        Map.Entry pair = (Map.Entry)it.next();
		       LinkedList<edge_data> curEdges = edges.get(pair.getKey());
		       int curKey = (int) pair.getKey();
		       for (edge_data e : curEdges) {
				g.connect(curKey,e.getDest(),e.getWeight());
			}
		 }
		return g;
		       
	}

}
