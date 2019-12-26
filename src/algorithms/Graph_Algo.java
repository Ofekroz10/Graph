package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
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
		 Collection<node_data> vers =  g.getV();
		 Iterator<node_data> it=vers.iterator();
		 while (it.hasNext()) 
		 {
			 node_data data =it.next();
			verMap.put(data.getKey(),((Vertex)data));
		 }
		 //Deep copy of edges
		 for(int i=1;i<=verMap.size();i++)
		 {
			 Collection<edge_data> curEdge;
			 try
			 {
				 curEdge =  g.getE(i);
			 }
			 catch(NullPointerException e)
			 {
				 continue;
			 }
			 Iterator<edge_data> it1 = curEdge.iterator();
				 while (it1.hasNext()) {
					 edge_data curE=it1.next();
					 
					if(edges.get(i)==null)
					{
						LinkedList<edge_data> lst = new LinkedList<edge_data>();
						lst.add(((Edge)curE));//O(1)
						edges.put(i,lst); 
					}
					else
					{
						edges.get(i).add(((Edge)curE));
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
	            System.out.println("File is not exist");
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
	            System.out.println(ex);
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
		node_data start= null;
		//make cur node.distance to 0, node.father = null
		 Iterator it = verMap.entrySet().iterator();
		    while (it.hasNext()) { //O(V)
		        Map.Entry pair = (Map.Entry)it.next();
		       node_data v= (node_data)pair.getValue();
		       queue.add(v);
		       if(v.getKey()==src)
		       {
		    	   v.setWeight(0);
		    	   start = v;
		       }
		       else
		    	   v.setWeight(INFI);
		       v.setInfo("null");
		       v.setTag(0);
		    }
		   //end of initialization
		    node_data destV = null;
		    while(queue.size()!=0)
		    {
		    	queue.add(queue.poll());//Update the queue
		    	node_data v = queue.poll();
		    	v.setTag(1);
		    	if(v.getKey()!=dest)
		    		hakala(v,queue);
		    	else
		    		destV = v;
		    }
		    if(destV!=null)
		    {
			    path.addLast(destV);
			    node_data current = destV;
			    while(!current.getInfo().equals("null"))
			    {
			    	path.addFirst(verMap.get(Integer.valueOf(current.getInfo())));
			    	current = (node_data)verMap.get(Integer.valueOf(current.getInfo()));
			    	shortDest+=current.getWeight();
			    }
		    }
		    if(path.size()==1&&path.getFirst().getKey()==dest)
		    {
		    	System.out.println("blablabla");
		    	System.out.println(path);
		    	System.out.println(g.getNode(5).getWeight());
		    	path = new LinkedList<>();
		    }
		return path;
		
	}

	private void hakala(node_data v,PriorityQueue<node_data> q) {
		LinkedList<edge_data> nei =edges.get(v.getKey());
		if(nei!=null)
		{
		for (edge_data e : nei) {
			double x = v.getWeight()+e.getWeight();
			node_data curNeiVer = verMap.get(e.getDest());
			if(curNeiVer.getWeight()>x &&curNeiVer.getTag()==0)
			{
				curNeiVer.setInfo(String.valueOf(v.getKey()));
				curNeiVer.setWeight(x);
				//update the queue
				q.remove(curNeiVer);
				q.add(curNeiVer);
			}
		}
		}
		
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		HashMap<Integer,Double> preValues = new HashMap<Integer,Double>();
		List<node_data> result =new LinkedList<node_data>();
		int prev=-1;
		boolean firstCalc =true;
		for (Integer key : targets) {
			double preWeight=verMap.get(key).getWeight();
			verMap.get(key).setWeight(0);
			preValues.put(key,preWeight);
			if(prev==-1)
				prev=key;
			else
			{
				List<node_data> temp =shortestPath(prev,key);
				if(temp.size()==0) // the graph is not well connected
				{
					result =new LinkedList<>();
					break;
				}
				if(!firstCalc)
					temp.remove(0);//O(1)
				else
					firstCalc=false;
				result.addAll(temp);//O(1)
				prev=key;
			}
		}
		//set weight to prev weight
		Iterator it = preValues.entrySet().iterator();
		 while (it.hasNext()) { //O(V)
		        Map.Entry pair = (Map.Entry)it.next();
		       node_data v= verMap.get(((int)pair.getKey()));
		       v.setWeight(preValues.get(v.getKey()));
		 }
		 
		 return result;	
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
