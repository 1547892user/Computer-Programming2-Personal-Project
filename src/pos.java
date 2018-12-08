import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class pos 
{
	static int today_income=0;
	
	public static void main(String args[])
	{
		program pg = new program();
	}
}

class program extends JFrame
{
	OrderScreen order = new OrderScreen(); //OrderScreen ��ü ����
	firstScreen first = new firstScreen(); //firstScreen ��ü ����
	TableInfo[] t_info = new TableInfo[4];
	
	public program()
	{
		setTitle("Order up");
		setSize(1400, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for(int i=0; i<4; i++)
		{
			t_info[i] = new TableInfo();
		}
		
		MenuInfo.MakingMenuInfo();
		
		setContentPane(first);
		
		setVisible(true);
	}
	
	void toFirstScreen() 
	{
		this.setContentPane(first);
		this.revalidate();
	}
	
	void toOrderScreen()
	{
		this.setContentPane(order);
		this.revalidate();
	}
}

class firstScreen extends JPanel //�ʱ�ȭ��
{	
	tableScreen tS = new tableScreen(); //tableScreen ��ü ����
	OrderList OL = new OrderList(); //OrderList ��ü ����
	JLabel tableNo = new JLabel("Selected table : " + Integer.toString(tableScreen.selected_table));
	
	public firstScreen()
	{
		this.setLayout(null);
		
		tS.setLocation(30, 30);
		OL.setLocation(450, 30); 
		
		JButton order = new JButton("�ֹ�");
		order.setSize(100, 50);
		order.setBackground(Color.lightGray);
		order.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JButton innerButton = (JButton)e.getSource();
						program innerFrame = (program)innerButton.getTopLevelAncestor();
						innerFrame.toOrderScreen();
						innerFrame.order.tableNo.setText("Selected table : " + Integer.toString(tableScreen.selected_table));
						innerFrame.order.list.PrintOrder();
						innerFrame.order.revalidate();
					}
				}); 
		order.setLocation(800, 880);//�ֹ���ư ����
		
		JButton pay = new JButton("����");
		pay.setSize(100, 50);
		pay.setBackground(Color.lightGray);
		pay.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JButton innerButton = (JButton)e.getSource();
						program innerFrame = (program)innerButton.getTopLevelAncestor();
						pos.today_income += innerFrame.t_info[tableScreen.selected_table-1].getSum();
						innerFrame.t_info[tableScreen.selected_table-1].ordered.clear();
						innerFrame.first.OL.PrintOrder();
						innerFrame.first.revalidate();
						innerFrame.setTitle("������ ���� : " + Integer.toString(pos.today_income));
					}
				}); 
		pay.setLocation(950, 880);//������ư ����
		
		tableNo.setFont(new Font("Arial", Font.BOLD, 20));
		tableNo.setSize(200,50);
		tableNo.setLocation(1000, 100);
		
		this.add(tableNo);
		this.add(OL);
		this.add(tS);
		this.add(order);
		this.add(pay);
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
	static int selected_table=1; //���õ� ���̺�
	
	public tableScreen()
	{
		for(int i=1;i<=4;i++)
		{
			JButton table = new JButton(Integer.toString(i));
			table.setSize(200, 100);
			table.setLocation(90, -60+120*i);
			table.setBackground(Color.lightGray);
			table.setFont(new Font("Arial", Font.BOLD, 25));
			table.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							JButton innerButton = (JButton)e.getSource();
							selected_table = Integer.parseInt(innerButton.getText()); //���õ� ���̺� �ѹ� ����
							program innerFrame = (program)innerButton.getTopLevelAncestor();
							innerFrame.first.OL.PrintOrder();
							innerFrame.first.tableNo.setText("Selected table : " + Integer.toString(tableScreen.selected_table));
							innerFrame.first.revalidate();
						}
					}); 
			this.add(table);
		} //���̺� ��ư ����

	}
}

class OrderList extends Box //�ֹ� ��� ����
{	
	JPanel List = new JPanel();
	JLabel sum = new JLabel("�հ� : 0");
	
	public OrderList()
	{
		JLabel Title = new JLabel("�ֹ� ���");
		Title.setFont(new Font("����", Font.BOLD, 40));
		Title.setLocation(100, 20); 
		Title.setSize(400, 110); //"�ֹ� ���"�� ����
		

		List.setSize(380 ,500);
		List.setLocation(10, 150);
		List.setLayout(null);
		List.setBorder(new LineBorder(Color.black, 2));
		
		
		 
		this.add(Title);
		this.add(List);
		
		sum.setFont(new Font("����", Font.BOLD, 20));
		sum.setSize(150, 40);
		sum.setLocation(30, 450);
		
		this.List.add(sum);
	}
	
	void PrintOrder()
	{
		this.List.removeAll();
		int y_coord = 10;
		program tmp = (program)this.getTopLevelAncestor();
		HashMap<String, Integer> temporary = tmp.t_info[tableScreen.selected_table-1].ordered;
		Set<String> keys = temporary.keySet();
		Iterator <String> it = keys.iterator();
		while(it.hasNext())
		{
			String key = it.next();
			JLabel menu = new JLabel(key);
			menu.setSize(200, 20);
			menu.setLocation(20, y_coord);
			menu.setFont(new Font("Arial", Font.BOLD, 14));
			
			String num = Integer.toString(temporary.get(key))+" ��";
			
			JLabel number = new JLabel(num);
			number.setSize(100, 20);
			number.setLocation(250, y_coord);

			number.setFont(new Font("����", Font.BOLD, 20));
			
			String mon = Integer.toString(MenuInfo.Menu.get(key)*(temporary.get(key)));
			
			JLabel money = new JLabel(mon);
			money.setSize(200, 20);
			money.setLocation(300, y_coord);
			money.setFont(new Font("Arial", Font.BOLD, 20));
			
			this.List.add(menu);
			this.List.add(number);
			this.List.add(money);
			
			y_coord += 20;
		}
		this.sum.setText("�հ� : " + Integer.toString(tmp.t_info[tableScreen.selected_table-1].getSum()));
		this.List.add(sum);
		this.List.repaint();
		this.List.revalidate();
	}
}

class TableInfo //���� ���̺� �ֹ� ����
{
	HashMap<String, Integer> ordered = new HashMap<String, Integer>();
	int sum=0;
	
	void newOrder(String menu)
	{
		boolean already_ordered = ordered.containsKey(menu);
		if(already_ordered)
		{
			int previousNum = ordered.get(menu);
			ordered.remove(menu);
			ordered.put(menu, previousNum+1);
		}
		else
		{
			ordered.put(menu, 1);
		}
	}
	
	void cancleOrder(String menu)
	{
		boolean already_ordered = ordered.containsKey(menu);
		if(already_ordered)
		{
			int previousNum = ordered.get(menu);
			ordered.put(menu, previousNum-1);
			if(ordered.get(menu)==0)
			{
				ordered.remove(menu);
			}
		}
	}
	
	int getSum()
	{
		sum=0;
		HashMap<String, Integer> temporary = this.ordered;
		Set<String> keys = temporary.keySet();
		Iterator <String> it = keys.iterator();
		while(it.hasNext())
		{
			String key = it.next();
			int num = temporary.get(key);
			int mon = MenuInfo.Menu.get(key)*temporary.get(key);
			sum+=mon;
		}
		return sum;
	}
}

class MenuInfo //�޴� ���� ��� �ؽø�
{
	static HashMap<String, Integer> Menu = new HashMap<String, Integer>();
	
	static void MakingMenuInfo() //�޴� ���� ����
	{
		Menu.put("HamCheese Sandwich", 3500);
		Menu.put("Vegetable Sandwich", 3000);
		Menu.put("HamEgg Sandwich", 3500);
		Menu.put("Crab Sandwich", 4000);
		Menu.put("Chicken Sandwich", 4000);
		Menu.put("Chocolate Cake", 4500);
		Menu.put("Cheese Cake", 5000);
		Menu.put("Strawberry Cake", 4800);
		Menu.put("Cream Cake", 4200);
		Menu.put("Carrot Cake", 4500);
		Menu.put("Strawberry Juice", 3000);
		Menu.put("Mango Juice", 3000);
		Menu.put("Orange Juice", 2500);
		Menu.put("Tomato Juice", 2500);
		Menu.put("Banana Juice", 2500);
		Menu.put("Espresso", 2800);
		Menu.put("Americano", 3400);
		Menu.put("CaffeLatte", 4500);
		Menu.put("Cappuccino", 5000);
		Menu.put("CafeMocha", 5000);
	}
}

class OrderScreen extends JPanel //�ֹ��ϴ� ȭ��
{		
	OrderList list = new OrderList(); 
	JLabel tableNo = new JLabel("Selected table : " + Integer.toString(tableScreen.selected_table));
	
	public OrderScreen()
	{
		this.setLayout(null);
		

		list.setLocation(30, 30); //OrderList ��ü ����
		
		MenuPan menupan = new MenuPan();
		menupan.setLocation(450, 30); //MenuPan ��ü ����
		
		JButton order = new JButton("ó�� ȭ������");
		order.setSize(100, 50);
		order.setBackground(Color.lightGray);
		order.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JButton innerButton = (JButton)e.getSource();
						program innerFrame = (program)innerButton.getTopLevelAncestor();
						innerFrame.toFirstScreen();
						innerFrame.first.OL.PrintOrder();
					}
				}); 
		order.setLocation(800, 900); //���ư��� ��ư ����
		
		tableNo.setFont(new Font("Arial", Font.BOLD, 20));
		tableNo.setSize(200,50);
		tableNo.setLocation(1000, 100);
		
		this.add(tableNo);
		this.add(order);
		this.add(list);
		this.add(menupan);
	}
}

class MenuPan extends Box
{
	MenuOrder[] menuorder = new MenuOrder[4]; //Menu�� ǥ���� ��ü
	int previousMenuNumber = 0; //���� MenuNumber�� ����
	JButton[] menuType = new JButton[4]; //MenuNumber�� �� ��ư
	
	public MenuPan()
	{
		this.setLayout(null);
		
		for(int i=1;i<=4;i++) //MenuOrder ��ü 4�� ����
		{
			menuorder[i-1] = new MenuOrder(i);
		};
		
		JPanel MenuNumber = new JPanel();
		MenuNumber.setLayout(new FlowLayout());
		MenuNumber.setSize(370, 180);
		MenuNumber.setLocation(20,20); //MenuNumber ��ü ���� �� ��ġ ����
		this.add(MenuNumber);
		
		menuType[0] = new JButton("Coffee");
		menuType[1] = new JButton("Juice");
		menuType[2] = new JButton("Cake");
		menuType[3] = new JButton("Sandwich");
		
		for(int i=0; i<4; i++)
		{
			menuType[i].setFont(new Font("Arial", Font.BOLD, 15));
			menuType[i].setPreferredSize(new Dimension(150, 50));
			menuType[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) //Button�� ���� MenuOrder�� �ٲٴ� ������
				{
					int j=0;
					JButton innerbutton = (JButton) e.getSource();
					String str = innerbutton.getText();
					switch(str)
					{
					case "Coffee" : j=0;break;
					case "Juice" : j=1;break;
					case "Cake" : j=2;break;
					case "Sandwich" : j=3;break;
					}
					
					MenuPan innerPan = (MenuPan) innerbutton.getParent().getParent();
					
					if(previousMenuNumber != 0)
						innerPan.remove(menuorder[previousMenuNumber-1]);
					innerPan.add(menuorder[j]);
					
					previousMenuNumber = j+1;
					
					innerPan.repaint();
					innerPan.revalidate();
				}
			});
		
			MenuNumber.add(menuType[i]);
		}
	}
}

class MenuOrder extends JPanel
{
	public MenuOrder(int i)
	{
		if(i==1)
		{
			this.setLayout(new FlowLayout());
			this.setSize(370, 400);
			this.setLocation(20, 200);
			
			JButton[] Menu = new JButton[5];
			Menu[0] = new JButton("Espresso");
			Menu[1] = new JButton("Americano");
			Menu[2] = new JButton("CaffeLatte");
			Menu[3] = new JButton("Cappuccino");
			Menu[4] = new JButton("CafeMocha");
			
			for(int k=0;k<5;k++)
			{
				Menu[k].setFont(new Font("Arial", Font.BOLD, 13));
				Menu[k].setPreferredSize(new Dimension(200, 50));
				Menu[k].addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						JButton innerbutton = (JButton)e.getSource();
						program innerFrame = (program) innerbutton.getTopLevelAncestor();
						if(e.getButton()==MouseEvent.BUTTON1)
						{
							innerFrame.t_info[tableScreen.selected_table-1].newOrder(innerbutton.getText());
						}
						else if(e.getButton()==MouseEvent.BUTTON3)
						{
							innerFrame.t_info[tableScreen.selected_table-1].cancleOrder(innerbutton.getText());
						}
						innerFrame.order.list.PrintOrder();
					}
				});
				this.add(Menu[k]);
			}
		}
		else if(i==2)
		{
			this.setLayout(new FlowLayout());
			this.setSize(370, 400);
			this.setLocation(20, 200);
			
			JButton[] Menu = new JButton[5];
			Menu[0] = new JButton("Strawberry Juice");
			Menu[1] = new JButton("Mango Juice");
			Menu[2] = new JButton("Orange Juice");
			Menu[3] = new JButton("Tomato Juice");
			Menu[4] = new JButton("Banana Juice");
			
			for(int k=0;k<5;k++)
			{
				Menu[k].setFont(new Font("Arial", Font.BOLD, 13));
				Menu[k].setPreferredSize(new Dimension(200, 50));
				Menu[k].addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						JButton innerbutton = (JButton)e.getSource();
						program innerFrame = (program) innerbutton.getTopLevelAncestor();
						if(e.getButton()==MouseEvent.BUTTON1)
						{
							innerFrame.t_info[tableScreen.selected_table-1].newOrder(innerbutton.getText());
						}
						else if(e.getButton()==MouseEvent.BUTTON3)
						{
							innerFrame.t_info[tableScreen.selected_table-1].cancleOrder(innerbutton.getText());
						}
						innerFrame.order.list.PrintOrder();
					}
				});
				this.add(Menu[k]);
			}
		}
		else if(i==3)
		{
			this.setLayout(new FlowLayout());
			this.setSize(370, 400);
			this.setLocation(20, 200);
			JButton[] Menu = new JButton[5];
			Menu[0] = new JButton("Carrot Cake");
			Menu[1] = new JButton("Chocolate Cake");
			Menu[2] = new JButton("Cheese Cake");
			Menu[3] = new JButton("Strawberry Cake");
			Menu[4] = new JButton("Cream Cake");
			
			for(int k=0;k<5;k++)
			{
				Menu[k].setFont(new Font("Arial", Font.BOLD, 13));
				Menu[k].setPreferredSize(new Dimension(200, 50));
				Menu[k].addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						JButton innerbutton = (JButton)e.getSource();
						program innerFrame = (program) innerbutton.getTopLevelAncestor();
						if(e.getButton()==MouseEvent.BUTTON1)
						{
							innerFrame.t_info[tableScreen.selected_table-1].newOrder(innerbutton.getText());
						}
						else if(e.getButton()==MouseEvent.BUTTON3)
						{
							innerFrame.t_info[tableScreen.selected_table-1].cancleOrder(innerbutton.getText());
						}
						innerFrame.order.list.PrintOrder();
					}
				});
				this.add(Menu[k]);
			}
		}
		else
		{
			this.setLayout(new FlowLayout());
			this.setSize(370, 400);
			this.setLocation(20, 200);
			JButton[] Menu = new JButton[5];
			Menu[0] = new JButton("HamCheese Sandwich");
			Menu[1] = new JButton("Vegetable Sandwich");
			Menu[2] = new JButton("HamEgg Sandwich");
			Menu[3] = new JButton("Crab Sandwich");
			Menu[4] = new JButton("Chicken Sandwich");
			
			for(int k=0;k<5;k++)
			{
				Menu[k].setFont(new Font("Arial", Font.BOLD, 13));
				Menu[k].setPreferredSize(new Dimension(200, 50));
				Menu[k].addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						JButton innerbutton = (JButton)e.getSource();
						program innerFrame = (program) innerbutton.getTopLevelAncestor();
						if(e.getButton()==MouseEvent.BUTTON1)
						{
							innerFrame.t_info[tableScreen.selected_table-1].newOrder(innerbutton.getText());
						}
						else if(e.getButton()==MouseEvent.BUTTON3)
						{
							innerFrame.t_info[tableScreen.selected_table-1].cancleOrder(innerbutton.getText());
						}
						innerFrame.order.list.PrintOrder();
					}
				});
				this.add(Menu[k]);
			}
		}
	}
}