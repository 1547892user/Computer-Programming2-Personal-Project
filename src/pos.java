import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class pos 
{
	public static void main(String args[])
	{
		program pg = new program();
	}
}

class program extends JFrame
{

	firstScreen first = new firstScreen(); //firstScreen ��ü ����
	OrderScreen order = new OrderScreen(); //OrderScreen ��ü ����
	
	public program()
	{
		setTitle("Order up");
		setSize(1400, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setContentPane(first);
		
		setVisible(true);
	}
	
	void toFirstScreen() 
	{
		this.setContentPane(first);
	}
}

class firstScreen extends JPanel //�ʱ�ȭ��
{
	public firstScreen()
	{
		this.setLayout(null);
		tableScreen tS = new tableScreen();
		tS.setLocation(30, 30); //tableScreen ��ü ����
		
		OrderList OL = new OrderList();
		OL.setLocation(450, 30); //OrderList ��ü ����
		
		this.add(OL);
		this.add(tS);
	}
}

class Box extends JPanel //����
{
	public Box()
	{	
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black, 2));
		this.setSize(400, 800);
	}
}

class tableScreen extends Box //���̺� ����
{
	private int selected_table=0; //���õ� ���̺�
	
	public tableScreen()
	{
		this.setBorder(new TitledBorder(new LineBorder(Color.black, 2), "���̺�")); //"���̺�"�� ������ ��輱
		
		JButton table1 = new JButton("1");
		table1.setSize(200, 100);
		table1.setLocation(80, 50);
		table1.setBackground(Color.lightGray);
		table1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JButton innerButton = (JButton)e.getSource();
						selected_table = Integer.parseInt(innerButton.getText()); //���õ� ���̺� �ѹ� ����
					}
				}); //���̺� ��ư ����
		
		this.add(table1); //tableScreen�� table ���̱�
	}
	
	int getSelectedTable() //���õ� ���̺� ��ȣ �Ѱ��ִ� �޼ҵ�
	{
		return selected_table;
	}
}

class OrderList extends Box //�ֹ� ��� ����
{
	public int table_no;
	
	public OrderList()
	{
		JLabel Title = new JLabel("�ֹ� ���");
		Title.setFont(new Font("����", Font.BOLD, 40));
		Title.setLocation(100, 20); 
		Title.setSize(400, 110); //"�ֹ� ���"�� ����
		
		this.add(Title);
	}
}

class TableInfo //���� ���̺� �ֹ� ����
{
	HashMap<String, Integer> ordered = new HashMap<String, Integer>();
	
	void newOrder(String menu, int number)
	{
		
	}
}

class MenuInfo //�޴� ���� ��� �ؽø�
{
	static HashMap<String, Integer> Menu = new HashMap<String, Integer>();
	
	static void MakingMenuInfo() //�޴� ���� ����
	{
		
	}
}

class OrderScreen extends JPanel //�ֹ��ϴ� ȭ��
{
	public OrderScreen()
	{
		
	}
}