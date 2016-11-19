import java.io.File;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.FileWriter;
	public class Data
	{		
		ArrayList <String> user = new ArrayList<String>();
				
		public static void save(String a, String b, String c)
		{
			try{
				FileWriter fw=new FileWriter("data.txt",true);
				Formatter f=new Formatter(fw);
				
				f.format("Account: %s  Username: %s  Password: %s\r\n",a,b,c);
				f.close();
				JOptionPane.showMessageDialog(null,"Data has been saved","Data saved",JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"No such a file or directory.","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			
		}
		
		public static void load(String x)
		{
			try
			{
				File file=new File("data.txt");
				Scanner sc = new Scanner(file);
				
				JOptionPane.showMessageDialog(null, sc.findWithinHorizon("Account: "+x,0)+sc.nextLine(),"Your data",JOptionPane.INFORMATION_MESSAGE);
				
				sc.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"No such a file or directory.","ERROR",JOptionPane.ERROR_MESSAGE);
			}
					
		}
		
}