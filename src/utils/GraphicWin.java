package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Vertex;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

public class GraphicWin extends JFrame implements ActionListener,MouseListener
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
	WinState state;
	LinkedList<edge_data> shortest;
	String console;
	boolean addV = false;
	int newVer =-1;
	int help=0;
	public  GraphicWin()
	{
		state = WinState.REGULAR;
		console="";
		verC =Color.blue;
		gAlgo = new Graph_Algo();
		g =new DGraph();
		help=2;
		locations = new HashMap<Integer,Point3D>();
		initGui();
	}

	public GraphicWin(graph g)
	{
		state = WinState.REGULAR;
		this.g=g;
		console="";
		verC =Color.blue;
		gAlgo = new Graph_Algo();
		gAlgo.init(g); 
		locations = new HashMap<Integer,Point3D>();
		initGui();

		Runnable myRunnable =
			    new Runnable(){
			int premc =g.getMC();
					@Override
			        public synchronized void run(){
			        	while(true)
					      {
			        		
					    	  if(premc<g.getMC())
					    	  {
					    		  System.out.println("Enter to draw");
					    		  System.out.println(g);
					    		  repaint();
					    		  premc = g.getMC();
					    	  }
					    	  try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
					      }
			        }
			    };
		 Thread thread = new Thread(myRunnable);

			  thread.start();
			

	}

	private void initGui() {
		//init win
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//init menu bar
		menuBar = new MenuBar();
		menu = new Menu[3];
		menu[0] = new Menu("File");
		menu[1] = new Menu("Algorithms");
		menu[2] = new Menu("Edit graph");
		for (Menu menu2 : menu) {
			menuBar.add(menu2);
		}
		this.setMenuBar(menuBar);
		
		MenuItem item1 = new MenuItem("Save graph as...");
		MenuItem item2 = new MenuItem("Open graph from...");
		MenuItem item3 = new MenuItem("Shortest path");
		MenuItem item4 = new MenuItem("Shortest path lenght");
		MenuItem item5 = new MenuItem("Is connected");
		MenuItem item7 = new MenuItem("TSP");
		MenuItem item6 = new MenuItem("Draw normal");
		MenuItem item8 = new MenuItem("Create new graph");
		MenuItem item9 = new MenuItem("Add vertex");
		MenuItem item10 = new MenuItem("Connect vertexes");
		MenuItem item11 = new MenuItem("Remove edge");
		MenuItem item12 = new MenuItem("Remove vertex");
		
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);
		item6.addActionListener(this);
		item7.addActionListener(this);
		item8.addActionListener(this);
		item9.addActionListener(this);
		item10.addActionListener(this);
		item11.addActionListener(this);
		item12.addActionListener(this);
		
		menu[0].add(item1);
		menu[0].add(item2);
		menu[0].add(item8);
		menu[1].add(item3);
		menu[1].add(item4);
		menu[1].add(item5);
		menu[1].add(item6);
		menu[1].add(item7);
		menu[2].add(item9);
		menu[2].add(item10);
		menu[2].add(item11);
		menu[2].add(item12);
		//init file chooser
		fileChooser =new JFileChooser("user.home/Desktop");
		fileChooser.addActionListener(this);
		fileOpen = new JFileChooser("user.home/Desktop");
		fileOpen.addActionListener(this);
		saveFrame = new FileName(false);
		this.addMouseListener(this);
	
	}

	@Override
public void actionPerformed(ActionEvent e) { // listen to clicked in the menu
		
		String str = e.getActionCommand();
		
		if(str.equals("Save graph as..."))
		{
			saveFrame= new FileName(true);
			((FileName) saveFrame).setOk(this);
			
		}
		else if(str.equals("Shortest path lenght"))
		{
			DistanceForm f = new DistanceForm(g.getV(),this);
			f.setV(true);
		}
		else if(str.equals("Is connected"))
		{
			 String mess = "Is the graph connected? "+gAlgo.isConnected();
			 JOptionPane.showMessageDialog(null, mess, "Info", JOptionPane.INFORMATION_MESSAGE);
			
		}
		else if(str.equals("TSP"))
		{
			VertexInputForm vif = new VertexInputForm(this);
			
		}
		else if(str.equals("Create new graph"))
		{
			CreateGraphFrame create = new CreateGraphFrame(this);
		}
		else if(str.equals("Draw normal"))
		{
			state = WinState.REGULAR;
			repaint();
		}
		else if(str.equals("Shortest path"))
		{
			Select2VerForm f = new Select2VerForm(this);
			f.setV(true);
		}
		else if(str.equals("Open graph from..."))
		{
			fileOpen.showOpenDialog(this);
			
		}
		else if(str.equals("Add vertex"))
		{
			AddVertex form = new AddVertex(g,this);
		}
		else if(str.equals("Remove vertex"))
		{
			RemoveVertex form = new RemoveVertex(this,g);
		}
		else if(str.equals("Remove edge"))
		{
			RemoveEdgeForm form = new RemoveEdgeForm(g,this);
		}

		else if(str.equals("Connect vertexes"))
		{
			AddConnection form = new AddConnection(this,g);
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
				this.g = ((Graph_Algo)gAlgo).getGraph();
				state = WinState.REGULAR;
				console = "";
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
			switch(state)
			{
				case REGULAR:
					console="";
					drawG(g);
					System.out.println("Draw regular");
					break;
				case SHORTPATH:
					drawG(g);
					System.out.println("Draw shortest");
					break;
			}
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
					 System.out.println(curE);
					 System.out.println(v);
					 int index = v.indexOf(new Vertex(curE.getDest(),null));
					 Point3D dest = new Point3D(v.get(index).getLocation());
					 graphics.drawLine(p.ix(), p.iy(),dest.ix(), dest.iy());	
					 graphics.drawString(String.valueOf(curE.getWeight()), (int)((p.x()+dest.x())/2),(int)((p.y()+dest.y())/2));
					 //draw the direction
					 Point3D dir = new Point3D((int)((p.x()+dest.x())/2),(int)((p.y()+dest.y())/2));
				
					 for(int i =0; i<3;i++)
					 {
						 dir = new Point3D((int)((dir.x()+p.x())/2),(int)((dir.y()+p.y())/2));
					 }
					 graphics.setColor(Color.ORANGE);
					 if(state==WinState.SHORTPATH)
					 {
						 if(shortest.contains(curE))
						 {
							 graphics.setColor(Color.GREEN);
							 shortest.remove(curE);
						 }
					 }
					 graphics.fillOval(dir.ix(), dir.iy(), 7, 7);
			}
				 	g2d.dispose();
			
		}
		graphics.setColor(Color.BLACK);
		graphics.drawString(console, 70,750);
	}
	public void showShortestPath(int src,int dest)
	{
		LinkedList<node_data> shortP = (LinkedList<node_data>) gAlgo.shortestPath(src, dest);
		console = "Shortest path between "+src+","+dest+": "+shortP.toString();
		shortest = new LinkedList<>();
		Iterator<node_data> it = shortP.iterator();
		if(shortP.size()==0)
		{
			console = "There is no path between "+src+","+dest;
			JOptionPane.showMessageDialog(null, console, "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			updateShortLst(it);
			state = WinState.SHORTPATH;
			repaint();
		}
	}
	public void applyDisAlgo(int src,int dest)
	{
		String s = "The distance of the sortest path between "+src+ " to "+dest+": "+gAlgo.shortestPathDist(src, dest);
		JOptionPane.showMessageDialog(null, s, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	public boolean ifExist(int v)
	{
		if(g.getNode(v)==null)
			return false;
		return true;
	}

	public void tsp(List<Integer> vertexes) {
		
		LinkedList<node_data> shortP = (LinkedList<node_data>) gAlgo.TSP(vertexes);
		shortest = new LinkedList<>();
		Iterator<node_data> it = shortP.iterator();
		if(shortP.size()==0)
		{
			console = "The graph is not well connected in this group "+vertexes.toString();
			JOptionPane.showMessageDialog(null, console, "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			updateShortLst(it);
			console = "Tsp"+vertexes.toString()+": "+shortP.toString();
			state = WinState.SHORTPATH;
			repaint();
		}
		
	}
	private void updateShortLst(Iterator<node_data> it)
	{
		node_data pre =null;
		while(it.hasNext())
		{
			if(pre ==null)
			{
			node_data v1 = it.next();
			node_data v2 = it.next();
			shortest.add(new Edge(v1.getKey(),v2.getKey(),0));
			pre = v2;
			}
			else
			{
				node_data v2 = it.next();
				shortest.add(new Edge(pre.getKey(),v2.getKey(),0));
				pre=v2;
			}
		}
	}
	public void setGraph(graph g)
	{
		this.g=g;
		gAlgo.init(g);
		if(help==2)
		{

			Runnable myRunnable =
				    new Runnable(){
				int premc =g.getMC();
						@Override
				        public synchronized void run(){
				        	while(true)
						      {
				        		
						    	  if(premc<g.getMC())
						    	  {
						    		  System.out.println("Enter to draw");
						    		  System.out.println(g);
						    		  repaint();
						    		  premc = g.getMC();
						    	  }
						    	  try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
						      }
				        }
				    };
			 Thread thread = new Thread(myRunnable);
				  thread.start();
				  repaint();
				  help=0;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Point p = arg0.getLocationOnScreen();
		System.out.println("click "+addV+" "+newVer);
		if(addV&&newVer!=-1)//need to add new ver
		{
			addV=false;
			g.addNode(new Vertex(newVer,new Point3D(p.getX(),p.getY())));
			newVer=-1;
			repaint();
			
		}
		
	}

	public void setNewVer(int a)
	{
		newVer = a;
		addV=true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
