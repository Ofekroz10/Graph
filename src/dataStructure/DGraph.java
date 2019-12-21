package dataStructure;
 
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
 
public class DGraph implements graph ,Serializable{
    HashMap<Integer,node_data> verMap;
    HashMap<Tuple,edge_data> edges;
    HashMap<Integer,HashMap<Integer,edge_data>> edgesByVer;
    int mc;
   
    public DGraph()
    {
        verMap = new HashMap<Integer,node_data>();
         edges = new HashMap<Tuple,edge_data>();
         edgesByVer = new HashMap<Integer,HashMap<Integer,edge_data>>();
         mc = 0;
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
        return e;
    }
 
    @Override
    public void addNode(node_data n) 
    {
    	if(verMap.get(n)!=null)
			System.out.println("Vertex "+n.getKey()+" already exist!");
        verMap.put(n.getKey(),n);
        mc=1;
    }
 
    @Override
    public void connect(int src, int dest, double w) {
        node_data source = verMap.get(src);
        node_data des = verMap.get(dest);
        if(des!=null&&source!=null&&verMap.get(src)!=null&&verMap.get(dest)!=null)
        {
            Edge edge = new Edge(src,dest,w,0);
            edges.put(new Tuple(src,dest),edge);
            edgesByVer.put(src, edgesByVer.get(src));
            if(edgesByVer.get(src)==null)
            {
                HashMap<Integer,edge_data> temp=new HashMap<Integer,edge_data>();
                temp.put(dest, edge);
                edgesByVer.put(src,temp);
            }
            else
            {
                edgesByVer.get(src).put(dest, edge);
            }
        }
        mc=1;
    }
 
    @Override
    public Collection<node_data> getV() {
        return verMap.values();
    }
 
    @Override
    public Collection<edge_data> getE(int node_id) {
        return edgesByVer.get(node_id).values();
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
            mc=1;
            return remove;
    }
 
    @Override
    public edge_data removeEdge(int src, int dest) {
        edge_data removed = edges.remove(new Tuple(src,dest));//del from map<src,dest>
        edgesByVer.get(src).remove(dest);
        mc=1;
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
        return mc;
    }
    @Override
    public String toString()
    {
        return "The vertexes are: "+verMap.values().toString() +"\n The edges are: "+edges.values().toString();
    }
}