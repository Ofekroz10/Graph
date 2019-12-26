package utils;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class RemoveEdgeForm extends JFrame {

	private JPanel contentPane;
	JComboBox comboBox_1;
	JComboBox comboBox;
	graph g;
	GraphicWin main;
	/**
	 * Create the frame.
	 */
	public RemoveEdgeForm(graph g,GraphicWin main) {
		this.g=g;
		this.main = main;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectEdgeTo = new JLabel("select edge to remove");
		lblSelectEdgeTo.setBounds(137, 13, 157, 16);
		contentPane.add(lblSelectEdgeTo);
		
		JLabel lblSrc = new JLabel("src");
		lblSrc.setBounds(32, 67, 56, 16);
		contentPane.add(lblSrc);
		
		JLabel lblDest = new JLabel("dest");
		lblDest.setBounds(167, 67, 56, 16);
		contentPane.add(lblDest);
		
		comboBox = new JComboBox();
		comboBox.setBounds(32, 109, 56, 22);
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(155, 109, 56, 22);
		contentPane.add(comboBox_1);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				int src,dest;
				src = (int) comboBox.getSelectedItem();
				dest= (int) comboBox_1.getSelectedItem();
				edge_data e = g.getEdge(src, dest);
				if(e==null)
				{
					String mess = "There is no edge between "+src+ " to "+dest;
					 JOptionPane.showMessageDialog(null, mess, "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					g.removeEdge(src,dest);
					dispose();
					main.draw();
				}
			}
		});
		btnRemove.setBounds(265, 83, 97, 25);
		contentPane.add(btnRemove);
		fillComboBox();
		setVisible(true);
	}
	private void fillComboBox()
	{
		LinkedList<node_data> v = new LinkedList<>( g.getV());
		for (node_data ver : v) {
			comboBox.addItem(ver.getKey());
			comboBox_1.addItem(ver.getKey());
		}
		
	}
}
