package dataStructure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Point3D;

public class Vertex implements node_data
{

	private int tag;
	private  int key ;
	private Point3D location;
	private double weight;
	private double disFromStart;
	private node_data father;
	
	
	public Vertex(int key,int tag,Point3D location,double weight)
	{
		this.tag= tag;
		this.key= key;
		this.location = location;
		this.weight = weight;
		this.disFromStart =Graph_Algo.INFI;
		this.father = null;

	}
	
	
	@Override
	public int getKey() {
		return key;
	}

	@Override
	public Point3D getLocation() {
		return location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location = new Point3D(p);
		
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public void setWeight(double w) {
		weight  =w;
	}

	@Override
	//return info in this form   (key,weight,tag,(location))
	public String getInfo() {
		return new String("("+key+","+weight+","+tag+"("+location.toString()+"))");
	}

	@Override
	//get info in this form   (key,weight,tag,(location))
	public void setInfo(String s) throws IOException {
		
		if(s==null)
			throw new IOException("Empty string");
		try {
		s = s.substring(1);
		String[] values = s.split(",");
		key =Integer.parseInt(values[0]);
		weight = Integer.parseInt(values[1]);
		tag = Integer.parseInt(values[2]);
		//init location after...
		
		}
		catch(Exception e)
		{
			throw new IOException("Incorrect string");
		}
		
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		tag = t;
		
	}
	@Override
	public String toString()
	{
		return "Vertex: "+key;
	}
	public Vertex copy()
	{
		if(location!=null)
			return new Vertex(key,tag,location.copy(),weight);
		else
			return new Vertex(key,tag,null,weight);
	}
	public double getDisFromStart()
	{
		return disFromStart;
	}
	public node_data getFather()
	{
		return father;
	}
	public void setDisFromStart(double val)
	{
		this.disFromStart=val;
	}
	public void setFather(node_data val)
	{
		this.father=val;
	}



}
