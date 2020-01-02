package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

class FileName extends JFrame implements ActionListener
{
	JButton okButton;
	JTextField textBox ;
	JLabel label;
	JPanel p;
	String fileN;
	
	public FileName(boolean show)
	{
		//init win size
				this.setSize(100, 100);
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				okButton = new JButton("Ok");
				p = new JPanel();
				textBox = new JTextField();
				textBox.setText("File_name");
				label = new JLabel("File name: ");
				p.add(label);
				p.add(textBox);
				p.add(okButton);
				this.add(p);
				this.setVisible(show);
				
	}


	public String getFileName()
	{
		return textBox.getText();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(okButton))
		{
			setVisible(false); //you can't see me!
			fileN = textBox.getText();
			dispose();
		}
		
	}
	public void setOk(ActionListener listener)
	{
		okButton.addActionListener(listener);
	}
	public JButton getSource()
	{
		return okButton;
	}

}