package utils;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataStructure.*;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoveVertex extends JFrame {

	private JPanel contentPane;
	
	graph g;
	JComboBox comboBox;
	GraphicWin main;
	/**
	 * Create the frame.
	 */
	public RemoveVertex(GraphicWin win,graph g) {
		this.g= g;
		this.main=main;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 178);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectVertexTo = new JLabel("Select vertex to remove");
		lblSelectVertexTo.setBounds(12, 13, 283, 16);
		contentPane.add(lblSelectVertexTo);
		 comboBox = new JComboBox();
		comboBox.setBounds(34, 42, 99, 22);
		contentPane.add(comboBox);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selected = (int) comboBox.getSelectedItem();
				g.removeNode(selected);
				//main.draw();
				dispose();
			}
		});
		btnRemove.setBounds(198, 41, 97, 25);
		contentPane.add(btnRemove);
		fillComboBox();
		setVisible(true);
	}
	private void fillComboBox()
	{
		LinkedList<node_data> v = new LinkedList<>(g.getV());
		for (node_data ver : v) {
			comboBox.addItem(ver.getKey());
		}
		
	}
}
