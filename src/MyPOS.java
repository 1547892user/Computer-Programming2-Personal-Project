import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
	
public class MyPOS extends JFrame
{
	public MyPOS()
	{
		setTitle("Ex");
		setSize(1400, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		MyPOS one = new MyPOS();
		one.setContentPane(new MainScreen());
		one.repaint();
	}
}

class MainScreen extends JPanel
{
	public MainScreen()
	{
		this.setLayout(null);
		
		JPanel tablePane = new JPanel();
		tablePane.setSize(400, 500);
		tablePane.setLocation(30, 30);
		tablePane.setBorder(new TitledBorder(new LineBorder(Color.black, 2), "테이블"));
		tablePane.setLayout(null);
		
		for(int i=1; i<=3; i++)
			
		{
			JButton btn = new JButton(Integer.toString(i));
			btn.setSize(200, 100);
			btn.setLocation(80, -90+150*i);
			btn.setBackground(Color.lightGray);
			btn.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							MyPOS pos = (MyPOS) btn.getTopLevelAncestor();
							pos.setContentPane(new OrderPane());
							pos.repaint();
							pos.getContentPane().repaint();
						}
					});
			tablePane.add(btn);
		}
		
		this.add(tablePane);
	}
}

class OrderList extends JPanel
{
	public OrderList()
	{
		
	}
}

class OrderPane extends JPanel
{
	public OrderPane()
	{
		this.setLayout(null);
		
		JPanel order = new JPanel();
		order.setSize(400, 500);
		order.setLocation(30, 30);
		order.setBorder(new TitledBorder(new LineBorder(Color.black, 2), "주문 목록"));
		
		this.add(order);
	}
}