package dataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class DGraph implements graph{
	HashMap<Integer,node_data> verMap;
	HashMap<Tuple,edge_data> edges;
	HashMap<Integer,LinkedList<edge_data>> edgesByVer;
	LinkedList<node_data> verAsLst;
	LinkedList<edge_data> edgeAsLst;
	
	public DGraph()
	{
		verMap = new HashMap<Integer,node_data>();
		 edges = new HashMap<Tuple,edge_data>();
		 verAsLst = new LinkedList<node_data>();
		 edgeAsLst = new LinkedList<edge_data>();
		 edgesByVer = new HashMap<Integer,LinkedList<edge_data>>();
	}

	@Override
	public node_data getNode(int key) {
		node_data curVer =verMap.get(key);
		//if(curVer==null)
			//throw new Exception("There is no vertex id:"+key+" in the grapgh");
		return curVer;
	}
	@Override
	public edge_data getEdge(int src, int dest) { 
		edge_data e =edges.get(new Tuple(src,dest));
		edgeAsLst.add(e);
		return e;
	}

	@Override
	public void addNode(node_data n) {
		verMap.put(n.getKey(),n);
		verAsLst.add(n);
	}

	@Override
	public void connect(int src, int dest, double w) {
		node_data source = verMap.get(src);
		node_data des = verMap.get(dest);
		if(des!=null&&source!=null)
		{
			Edge edge = new Edge(src,dest,w,0);
			edges.put(new Tuple(src,dest),edge);
			if(edgesByVer.get(src)!=null)
				edgesByVer.get(src).add(edge);//O(1)
			else
			{
				LinkedList<edge_data> lst = new LinkedList<edge_data>();
				lst.add(edge);//O(1)
				edgesByVer.put(src,lst); //O(1)
			}
			
		}
	}

	@Override
	public Collection<node_data> getV() {
		return verAsLst;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return edgesByVer.get(node_id);
	}

	@Override
	public node_data removeNode(int key) {
		//remove the edges
		 Iterator it = verMap.entrySet().iterator();
		    while (it.hasNext()) { //O(n)
		        Map.Entry pair = (Map.Entry)it.next();
		        Tuple cur= new Tuple(key,(int)pair.getKey());
		        Tuple cur1= new Tuple((int)pair.getKey(),key);
		        if(edges.get(cur)!=null)
		        	{   
		        	System.out.println("Removed "+key+" "+ pair.getKey());
		        		removeEdge(key,(int)pair.getKey());
		        	}
		        if(edges.get(cur1)!=null)
		        {
		        	System.out.println("Removed "+key+" "+ pair.getKey());
	        		removeEdge((int)pair.getKey(),key);
		        }
		    }
		    node_data remove =verMap.remove(key); //remove from ver map
			//remove from the list
			verAsLst.remove(remove);
		    return remove;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data removed = edges.remove(new Tuple(src,dest));//del from map<src,dest>
		edgeAsLst.remove(removed);//remove from list
		return removed;
	}

	@Override
	public int nodeSize() {
		return verMap.size();
	}

	@Override
	public int edgeSize() {
		return edges.size();
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString()
	{
		return "The vertexes are: "+verMap.values().toString() +"\n The edges are: "+edges.values().toString();
	}
}
