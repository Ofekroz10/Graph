package utils;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dataStructure.node_data;

public class Select2VerForm extends JFrame implements ActionListener
{
	int v1,v2;
	JLabel v1L,v2L;
	JTextField t1,t2;
	ArrayList<node_data> lst;
	JButton submit ;
	boolean found = false;
	GraphicWin base;


	public Select2VerForm(Collection<node_data> ver,GraphicWin base) {
		v1L = new JLabel("Select source vertex");
		v2L = new JLabel("Select destination vertex");
		setLayout(new GridLayout(3,2));
		t1= new JTextField();
		t2 = new JTextField();
		submit = new JButton("Ok");
		lst = new ArrayList(ver);
		add(v1L);
		add(t1);
		add(v2L);
		add(t2);
		add(submit);
		this.setSize(100, 100);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submit.addActionListener(this);
		this.base = base;
		
	}
	
	public int getV1() {
		if(found)
			return v1;
		return -1;
	}

	public int getV2() {
		if(found)
			return v2;
		return -1;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(submit))
		{
			found = false;
			String v1s = t1.getText();
			String v2s = t2.getText();
			v1s= v1s.replace(" ","");
			v2s= v2s.replace(" ","");
			boolean foundv1 = false,foundv2 = false;
			for (node_data v : lst) {
				if(v.getKey()==Integer.valueOf(v1s))
					foundv1 = true;
				if(v.getKey()==Integer.valueOf(v2s))
					foundv2= true;
			}
			if(foundv1&&foundv2)
			{
				v1 = Integer.valueOf(v1s);
				v2 =Integer.valueOf(v2s);
				found = true;
				applyAlgo();
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Wrong vertexes", "Eror", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	public void applyAlgo()
	{
		base.showShortestPath(v1,v2);
	}
	public void setV(boolean v)
	{
		this.setVisible(v);
	}
	public JButton getB()
	{
		return this.submit;
	}
	
}
