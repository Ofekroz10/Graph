package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VertexInputForm extends JFrame implements ActionListener
{
	JTextField tBox;
	JLabel label;
	JButton submit;
	GraphicWin mainWin;
	JPanel panel;
	public VertexInputForm(GraphicWin w)
	{
		tBox = new JTextField("                              ");
		panel = new JPanel();
		label = new JLabel("Enter vertexes, between each vertex write ',' for example 1,2,3");
		submit = new JButton("ok");
		mainWin= w;
		submit.addActionListener(this);
		panel.add(label);
		panel.add(tBox);
		panel.add(submit);
		add(panel);
		this.setSize(500, 200);
		setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//submit clicked
		String input = tBox.getText();
		input = input.replace(" ","");
		String[] split = input.split(",");
		ArrayList<Integer> vertexes = new ArrayList<Integer>(split.length);
		for (String s : split) {
			int v = Integer.valueOf(s);
			//check if v exist
			if(!mainWin.ifExist(v))
			{
				JOptionPane.showMessageDialog(null, "Wrong vertexes", "Eror", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			vertexes.add(v);
		}
		dispose();
		mainWin.tsp(vertexes);
		
	}

}
