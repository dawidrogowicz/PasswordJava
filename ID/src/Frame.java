import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
public class Frame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JLabel text;
	private JTextField field1;
	private JPasswordField field2;
	private JPasswordField passfield;
	private JButton set;
	private JButton get;
	private JRadioButton check;
	private JRadioButton passb;
	private JRadioButton uncheck;
	private ButtonGroup group;
	private JComboBox<String> box ;
	private ArrayList<String> user = new ArrayList<String>();
	private String[] array;
	private String account;
	File file = new File("data.txt");
	
		public Frame()
		{
								
		super("ID notepad");
		setLayout(new FlowLayout());
		
		find();
		
		text = new JLabel("Type your informations to save them");
		add(text);
		
		field1 =new JTextField("Account",30);
		field1.setToolTipText("Type what account are those informations for");
		add(field1);
		
		field2 =new JPasswordField("Username",30);
		field2.setToolTipText("Enter your user name/ID");
		field2.setEchoChar((char) 0);
		add(field2);
						
		passfield =new JPasswordField("Password",30);
		passfield.setToolTipText("Enter your password");	
		passfield.setEchoChar('*');
		add(passfield);
				
		passb = new JRadioButton("Hide password",true);
		add(passb);
		
		check = new JRadioButton("Hide everything", false);
		add(check);
		
		uncheck = new JRadioButton ("Show everything",false);
		add(uncheck);
		
		set = new JButton("SAVE");
		set.setToolTipText("Save data that you've just entered above");
		add(set);
		
		if(file.exists())
		{
		box = new JComboBox<String>(array);
		box.setToolTipText("Your freshly saved data will be displayed here after restarting program");
		add(box);
		box.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent event)
			{
				if(event.getStateChange()==ItemEvent.SELECTED)
					account = (String) box.getSelectedItem();
			}
		});
		}
			
		get = new JButton("LOAD");
		get.setToolTipText("Choose which account you want to load data form in the box on the left");
		add(get);
							
		group = new ButtonGroup();
		group.add(check);
		group.add(uncheck);
		group.add(passb);
		
		thehandler handler =new thehandler();
		
		set.addActionListener(handler);
		get.addActionListener(handler);
		check.addActionListener(handler);
		uncheck.addActionListener(handler);
		passb.addActionListener(handler);
				
		}
				//HANDLER CLASS
				private class thehandler implements ActionListener
				{
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent event)
					{
					String acc="";
					String id="";
					String pass="";
					
						if(event.getSource()==set)
						{
					   	    acc=field1.getText();
							id=field2.getText();
							pass=passfield.getText();
														
							Data.save(acc, id, pass);
							find();
							
						}	
					    else if(event.getSource()==get)
					    {
						Data.load(account);		
					    }
						
					    else if(event.getSource()==check)
					    {
					    	if(check.isSelected()==true)
							{
								passfield.setEchoChar('*');
								field2.setEchoChar('*');
							}
					    }
						
					    else if(event.getSource()==passb)
					    {
					    	if(passb.isSelected()==true)
							{
								passfield.setEchoChar('*');
								field2.setEchoChar((char) 0);
							}
					    }
						
					    else if(event.getSource()==uncheck)
					    {
					    	if(uncheck.isSelected()==true)
							{
								passfield.setEchoChar((char) 0);
								field2.setEchoChar((char) 0);
							}
					    }
												
					}
			
				}
				
				public void find()
				{
					try
					{
											
						if(file.exists())
						{
								Scanner sc = new Scanner(file);
								
								while(sc.hasNextLine())
								{
									sc.findInLine("Account: ");
									user.add(sc.next());
									array = user.toArray(new String[user.size()]);		
									sc.nextLine();
								}	
								sc.close();
						}
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,"No such a file or directory.","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
		
}