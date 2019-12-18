package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import dataStructure.graph;
import dataStructure.node_data;
import java.util.Map;
import dataStructure.*;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */

	
public class Graph_Algo implements graph_algorithms, Serializable{
	
	public static final int INFI = Integer.MAX_VALUE;
	private HashMap<Integer,node_data> verMap;
	private HashMap<Integer,LinkedList<edge_data>> edges;
	public static final Comparator<node_data> _Comp = new VerComperator();
	public static Comparator<node_data> getComp() {return _Comp;}
	public int shortDest=0;
	private graph g;
	
	@Override
	public void init(graph g) {
		this.g=g;
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
		 try
	        {    
	            // Reading the object from a file 
	            FileInputStream file = new FileInputStream(file_name); 
	            ObjectInputStream in = new ObjectInputStream(file); 
	              
	            // Method for deserialization of object 
	            Graph_Algo object1 = (Graph_Algo)in.readObject(); 
	              
	            in.close(); 
	            file.close();
	            //init
	            init(object1.getGraph());
	            
	        } 
	          
	        catch(IOException ex) 
	        { 
	            System.out.println("IOException is caught"); 
	        } 
	          
	        catch(ClassNotFoundException ex) 
	        { 
	            System.out.println(file_name+ " do not exist"); 
	        } 
	  
	    } 
	

	@Override
	public void save(String file_name) {
		
		   try
	        {    
	            //Saving of object in a file 
	            FileOutputStream file = new FileOutputStream(file_name); 
	            ObjectOutputStream out = new ObjectOutputStream(file); 
	              
	            // Method for serialization of object 
	            out.writeObject(this); 
	              
	            out.close(); 
	            file.close(); 
	            
	  
	        } 
	          
	        catch(IOException ex) 
	        { 
	            System.out.println("Eror with saving a file"); 
	            System.out.println(ex.getMessage());
	        } 
	  
	}

	@Override
	public boolean isConnected() {
		Iterator it = verMap.entrySet().iterator();
	    while (it.hasNext()) { //O(V)
	        Map.Entry pair = (Map.Entry)it.next();
	        int cur = (int) pair.getKey();
	        boolean connect = isConnectedFrom(cur);
	        if(!connect)
	        	return false;
	    }
	    return true;
	        
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		shortestPath(src,dest);
		return shortDest;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		LinkedList<node_data> path = new LinkedList<node_data>();
		PriorityQueue<node_data> queue = new PriorityQueue<node_data>(new VerComperator());
		shortDest=0;
		if(verMap.get(src)==null)
			throw new ArithmeticException("src is not exist!");
		if(verMap.get(dest)==null)
			throw new ArithmeticException("dest is not exist!");
		if(src==dest)
		{
			path.add(verMap.get(src));
			shortDest=0;
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
		   //end of initialization
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
		if(path!=null)
		{
			shortDest = path.size()-1;
		}
		return path;
		
	}

	private void hakala(Vertex v) {
		LinkedList<edge_data> nei =edges.get(v.getKey());
		if(nei!=null)
		{
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
		
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
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
	private boolean isConnectedFrom(int src)
	{
		LinkedList<node_data> vers = new LinkedList<node_data>();
		int grayNum = 0;
		//Init vertexColor to white
		Iterator it = verMap.entrySet().iterator();
	    while (it.hasNext()) { //O(V)
	        Map.Entry pair = (Map.Entry)it.next();
	       node_data v= (node_data)pair.getValue();
	       v.setTag(0);//0 represent white
	       if(v.getKey()== src)
	       {
	    	   vers.add(v);
	    	   v.setTag(1);
	       		grayNum++;
	       }
	    }
	    while (vers.size()!=0) { 
	       node_data v = (node_data) vers.remove();
	       LinkedList<edge_data> nei = edges.get(v.getKey());
	       if(nei!=null)
	       {
	       for (edge_data e : nei) {
	    	   node_data curNei = (node_data)(verMap.get(e.getDest()));
				if(curNei.getTag()==0)
				{
					vers.add(curNei);
					curNei.setTag(1);//1 represent gray
					grayNum++;
				}
			}
	       }
	    }
	       
	    return(grayNum==verMap.size());
	}
	
	public graph getGraph()
	{
		return g;
	}

}
