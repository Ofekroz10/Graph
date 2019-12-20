package dataStructure;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Point3D;
import algorithms.*;
public class Vertex implements node_data,Serializable
{

	private int tag;
	private  int key ;
	private Point3D location;
	private double weight;
	private String father;
	
	
	public Vertex(int key,int tag,Point3D location,double weight)
	{
		this.tag= tag;
		this.key= key;
		this.location = location;
		this.weight = weight;
		this.weight = Graph_Algo.INFI;
		this.father = "null";

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
	public String getInfo() {
		return father;
	}

	@Override
	//get info in this form   (key,weight,tag,(location))
	public void setInfo(String s) {
		father =s;
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



}
