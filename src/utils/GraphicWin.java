package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.Edge;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

public class GraphicWin extends JFrame implements ActionListener
{
	Menu[] menu ;
	MenuBar menuBar;
	JFileChooser fileChooser; // for saving
	graph g= null;
	JFileChooser fileOpen; //for opening
	Color verC;
	HashMap<Integer,Point3D> locations;
	graph_algorithms gAlgo;
	FileName saveFrame;
	
	public GraphicWin(graph g)
	{
		this.g=g;
		verC =Color.blue;
		gAlgo = new Graph_Algo();
		gAlgo.init(g);
		locations = new HashMap<Integer,Point3D>();
		initGui();
	}

	private void initGui() {
		//init win
		this.setSize(750, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//init menu bar
		menuBar = new MenuBar();
		menu = new Menu[2];
		menu[0] = new Menu("File");
		menu[1] = new Menu("Algorithms");
		for (Menu menu2 : menu) {
			menuBar.add(menu2);
		}
		this.setMenuBar(menuBar);
		
		MenuItem item1 = new MenuItem("Save graph as...");
		MenuItem item2 = new MenuItem("Open graph from...");
		item1.addActionListener(this);
		item2.addActionListener(this);
		menu[0].add(item1);
		menu[0].add(item2);
		//init file chooser
		fileChooser =new JFileChooser("user.home/Desktop");
		fileChooser.addActionListener(this);
		fileOpen = new JFileChooser("user.home/Desktop");
		fileOpen.addActionListener(this);
		saveFrame = new FileName(false);
	}

	@Override
public void actionPerformed(ActionEvent e) { // listen to clicked in the menu
		
		String str = e.getActionCommand();
		
		if(str.equals("Save graph as..."))
		{
			saveFrame= new FileName(true);
			((FileName) saveFrame).setOk(this);
			
			
		}
		else if(str.equals("Open graph from..."))
		{
			fileOpen.showOpenDialog(this);
			
		}
		else if(e.getSource().equals(fileChooser))
		{
			String path = fileChooser.getSelectedFile().getPath();
			System.out.println(path);
			gAlgo.save(path+"//"+saveFrame.getFileName()+".graph");
		
		}
		else if(e.getSource().equals(saveFrame.getSource()))
		{
			saveFrame.dispose();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.showOpenDialog(this);
		}
		else if(e.getSource().equals(fileOpen))
		{
			System.out.println("Hey");
			String name = fileOpen.getSelectedFile().getName();
			if(name.endsWith(".graph"))
			{
				String path = fileOpen.getSelectedFile().getPath();
				gAlgo.init(path);
				this.g = gAlgo.copy();
				repaint();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Wrong type of file! please select .graph file", "Eror", JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		}
		
	}

	public void paint(Graphics g)
	{
		if(g!=null)
		{
			super.paint(g);
			drawG(g);
		}
		else
		{
			System.out.println("Cannot draw null graph");
		}
	
	}
	public void draw()
	{
		repaint();
	}
	private void drawG(Graphics graphics) {
		Graphics2D g2d = (Graphics2D) graphics.create();
		graphics.setFont(new Font("default", Font.BOLD, 14));
		List<node_data> v = new ArrayList<node_data>(g.getV());
		for (node_data ver : v) {
			//draw vertex
			Point3D p = new Point3D(ver.getLocation().x(),ver.getLocation().y());
			graphics.setColor(verC);
			graphics.fillOval(p.ix(), p.iy(), 7, 7);
			graphics.drawString(String.valueOf(ver.getKey()), p.ix(), p.iy()-5);
			//draw edges from this vertex
			Collection<edge_data> curEdge;
			 try
			 {
				 curEdge =  g.getE(ver.getKey());
			 }
			 catch(NullPointerException e)
			 {
				 continue;
			 }
			 Iterator<edge_data> it1 = curEdge.iterator();
				 while (it1.hasNext()) {
					 edge_data curE=it1.next();
					 graphics.setColor(Color.RED);
					 Point3D dest = new Point3D(v.get(curE.getDest()-1).getLocation());
					 graphics.drawLine(p.ix(), p.iy(),dest.ix(), dest.iy());	
					 graphics.drawString(String.valueOf(curE.getWeight()), (int)((p.x()+dest.x())/2),(int)((p.y()+dest.y())/2));
					 //draw the direction
					 Point3D dir = new Point3D((int)((p.x()+dest.x())/2),(int)((p.y()+dest.y())/2));
				
					 for(int i =0; i<3;i++)
					 {
						 dir = new Point3D((int)((dir.x()+p.x())/2),(int)((dir.y()+p.y())/2));
					 }
					 graphics.setColor(Color.ORANGE);
					 graphics.fillOval(dir.ix(), dir.iy(), 7, 7);
			}
				 	g2d.dispose();
			
			
		}
	}

}
