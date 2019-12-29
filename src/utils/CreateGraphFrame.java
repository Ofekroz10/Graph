package utils;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataStructure.*;

import java.awt.Label;
import java.awt.TextField;
import java.util.LinkedList;

import javax.swing.JTextPane;
import java.awt.Panel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateGraphFrame extends JFrame {

	private JPanel contentPane;
	private JTextField verBox;
	private JLabel lblSrc;
	private LinkedList<JComponent> first;
	private LinkedList<JComponent> second;
	private JTextField textField;
	JComboBox srcCB;
	JComboBox destCB;
	graph g;
	int vertecies=0;
	GraphicWin main;

	/**
	 * Launch the application.
	 */
	
	public CreateGraphFrame(GraphicWin main)
	{
		this.main=main;
		create();
	}

	/**
	 * Create the frame.
	 */
	public void create() 
	{
		g=  new DGraph();
		first = new LinkedList<JComponent>();
		second=new LinkedList<JComponent>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		verBox = new JTextField();
		verBox.setText("number of vertices\r\n");
		verBox.setBounds(138, 12, 155, 22);
		contentPane.add(verBox);
		first.add(verBox);
		verBox.setColumns(10);
		
		JButton createV = new JButton("Create");
		createV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = verBox.getText();
				num = num.replace(" ","");
				try
				{
					Integer vNum= Integer.valueOf(num);
					makeVisible(false,first);
					makeVisible(true,second);
					vertecies = (int)vNum;
					for(int i =1;i<=vNum;i++)
					{//add vertexes to srcCB and destCB
						srcCB.addItem(i);
						destCB.addItem(i);
						g.addNode(new Vertex(i,randPoint(50,750)));
						
					}
				}
				catch(Exception e1)
				{
					String mess = "In correct number";
					 JOptionPane.showMessageDialog(null, mess, "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		createV.setBounds(304, 10, 97, 25);
		contentPane.add(createV);
		first.add(createV);
		
		lblSrc = new JLabel("src");
		lblSrc.setBounds(26, 74, 56, 16);
		contentPane.add(lblSrc);
		
		srcCB = new JComboBox();
		srcCB.setBounds(52, 74, 90, 22);
		contentPane.add(srcCB);
		second.add(srcCB);
		

		
		destCB = new JComboBox();
		destCB.setBounds(199, 71, 90, 22);
		contentPane.add(destCB);
		second.add(destCB);
		
		JButton connectB = new JButton("Connect");
		connectB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String num = textField.getText();
				num = num.replace(" ","");
				try
				{
					Double wNum= Double.valueOf(num);
					int v1 = (int) srcCB.getSelectedItem();
					int v2 = (int) destCB.getSelectedItem();
					g.connect(v1,v2,wNum);
					
				}
				catch(Exception e1)
				{
					String mess = "In correct number";
					 JOptionPane.showMessageDialog(null, mess, "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		connectB.setBounds(412, 70, 79, 25);
		contentPane.add(connectB);
		second.add(connectB);
		
		JButton finishB = new JButton("Finish");
		finishB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				main.setGraph(g);
				main.draw();
				
			}
		});
		finishB.setBounds(322, 215, 97, 25);
		contentPane.add(finishB);
		second.add(finishB);
		
		JButton btnNewButton = new JButton("Random graph");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graph newG = randomG(vertecies);
				dispose();
				main.setGraph(newG);
				main.draw();
			}
		});
		btnNewButton.setBounds(26, 215, 138, 25);
		contentPane.add(btnNewButton);
		second.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(356, 71, 46, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		second.add(textField);
		
		JLabel lblNewLabel = new JLabel("Weight");
		lblNewLabel.setBounds(301, 74, 40, 16);
		contentPane.add(lblNewLabel);
		second.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("numbers of vertex");
		lblNewLabel_1.setBounds(12, 15, 114, 16);
		contentPane.add(lblNewLabel_1);
		first.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("dest");
		lblNewLabel_2.setBounds(157, 74, 40, 16);
		contentPane.add(lblNewLabel_2);
		second.add(lblNewLabel_2);
		makeVisible(false,second);
		this.setSize(540,370);
		this.setVisible(true);
		
		
	}
	private void makeVisible(boolean v,LinkedList<JComponent> comp)
	{
		for (JComponent c : comp) {
			c.setEnabled(v);
		}		
	}
	public static Point3D randPoint(int low,int high)
	{
		int x = (int) (Math.random() * (high - low)) + low;
		int y = (int) (Math.random() * (high - low)) + low;
		return new Point3D(x,y,0);
	}
	private graph randomG(int num)
	{
graph g = new DGraph();
		
		int low = 50;
		int high = 750;
		int edges = num*2;
		
		for (int i = 1; i <= num; i++) {
			Point3D p = randPoint(50,750);
			g.addNode(new Vertex(i,new Point3D(p.ix(),p.iy())));
		}
		low=1;
		high=num;
		for (int i = 0; i < edges; i++) {
			System.out.println("connect");
			int x = (int) (Math.random() * (high - low)) + low;
			int y = (int) (Math.random() * (high - low)) + low;
			int  w= (int) (Math.random() * (100 - 1)) + 1;
			g.connect(x,y,w);
		}
		return g;
	}
}
