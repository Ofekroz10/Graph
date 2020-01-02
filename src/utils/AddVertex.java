package utils;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataStructure.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddVertex extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;


	/**
	 * Create the frame.
	 */
	public AddVertex(graph g,GraphicWin main) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 354, 193);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLabel = new JLabel("Enter id of vertex");
		lblLabel.setBounds(12, 8, 326, 16);
		contentPane.add(lblLabel);
		
		txtId = new JTextField();
		txtId.setBounds(124, 7, 46, 22);
		txtId.setText("id");
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JButton btnNewButton = new JButton("ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String num = txtId.getText();
				num = num.replace(" ","");
				try
				{
					int vNum= Integer.valueOf(num);
					if(g.getNode(vNum)==null)
					{
						main.setNewVer(vNum);
						dispose();
					}
					else
					{
						String mess ="Vertex already exist";
						 JOptionPane.showMessageDialog(null, mess, "Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(Exception e1)
				{
					String mess = "In correct number";
					 JOptionPane.showMessageDialog(null, mess, "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(194, 8, 97, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblThenClickOn = new JLabel("Then, click on the screen");
		lblThenClickOn.setBounds(43, 61, 164, 16);
		contentPane.add(lblThenClickOn);
		this.setVisible(true);
		this.setSize(300,150);
	}
}
