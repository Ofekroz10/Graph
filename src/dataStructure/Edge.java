package dataStructure;

public class Edge implements edge_data
{
	private final int src,dest;
	private int tag;
	private double weight;

	public Edge(int src,int dest,double weight,int tag)
	{
		this.src = src;
		this.dest = dest;
		this.tag = tag;
		this.weight = weight;
	}
	@Override
	public int getSrc() {
		return src;
	}

	@Override
	public int getDest() {
		return dest;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	//return string from the form (src,desr,weight,tag)
	public String getInfo() {
		return new String("("+src+","+dest+","+weight+","+tag+")");
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
		
	}
	@Override
	public String toString()
	{
		return "src: "+src+" dest: "+dest+" weight: "+weight;
	}
}
