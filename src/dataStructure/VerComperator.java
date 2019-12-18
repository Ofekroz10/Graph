package dataStructure;

import java.util.Comparator;

public class VerComperator implements Comparator<node_data>{
	
	public VerComperator() {;}
	@Override
	public int compare(node_data o1, node_data o2) {
			Vertex x = (Vertex)o1;
			Vertex y = (Vertex)o2;
			return (int)(x.getDisFromStart()-y.getDisFromStart());
	}

}
