package utils;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataStructure.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class AddConnection extends JFrame {

	private JPanel contentPane;
	graph g ;
	JComboBox comboBox;
	JComboBox comboBox_1;
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public AddConnection(GraphicWin main,graph g) {
		this.g=g;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectSrcAnd = new JLabel("Select src and dest");
		lblSelectSrcAnd.setBounds(107, 13, 252, 16);
		contentPane.add(lblSelectSrcAnd);
		
		JLabel lblSrc = new JLabel("src");
		lblSrc.setBounds(26, 77, 56, 16);
		contentPane.add(lblSrc);
		
		comboBox = new JComboBox();
		comboBox.setBounds(12, 106, 57, 22);
		contentPane.add(comboBox);
		
		
		JLabel lblDest = new JLabel("dest");
		lblDest.setBounds(107, 77, 56, 16);
		contentPane.add(lblDest);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(107, 106, 56, 22);
		contentPane.add(comboBox_1);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String num = textField.getText();
				num = num.replace(" ","");
				try
				{
					Double wNum= Double.valueOf(num);
					g.connect((int)comboBox.getSelectedItem(),(int)comboBox_1.getSelectedItem(),wNum);
					dispose();
					main.draw();
					
				}
				catch(Exception e1)
				{
					String mess = "In correct number";
					 JOptionPane.showMessageDialog(null, mess, "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		btnConnect.setBounds(211, 154, 97, 25);
		contentPane.add(btnConnect);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(214, 77, 56, 16);
		contentPane.add(lblWeight);
		
		textField = new JTextField();
		textField.setBounds(188, 106, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
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
