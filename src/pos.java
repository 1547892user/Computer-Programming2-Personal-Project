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
	OrderScreen order = new OrderScreen(); //OrderScreen 객체 생성
	firstScreen first = new firstScreen(); //firstScreen 객체 생성
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

class firstScreen extends JPanel //초기화면
{	
	tableScreen tS = new tableScreen(); //tableScreen 객체 생성
	OrderList OL = new OrderList(); //OrderList 객체 생성
	
	public firstScreen()
	{
		this.setLayout(null);
		
		tS.setLocation(30, 30);
		OL.setLocation(450, 30); 
		
		JButton order = new JButton("주문");
		order.setSize(100, 50);
		order.setBackground(Color.lightGray);
		order.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JButton innerButton = (JButton)e.getSource();
						program innerFrame = (program)innerButton.getTopLevelAncestor();
						innerFrame.toOrderScreen();
					}
				}); 
		order.setLocation(800, 900);//주문버튼 생성
		
		this.add(OL);
		this.add(tS);
		this.add(order);
	}
}

class Box extends JPanel //상자
{
	public Box()
	{	
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.black, 2));
		this.setSize(400, 800);
	}
}

class tableScreen extends Box //테이블 상자
{
	static int selected_table=0; //선택된 테이블
	
	public tableScreen()
	{
		JButton table1 = new JButton("1");
		table1.setSize(200, 100);
		table1.setLocation(80, 50);
		table1.setBackground(Color.lightGray);
		table1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JButton innerButton = (JButton)e.getSource();
						selected_table = Integer.parseInt(innerButton.getText()); //선택된 테이블 넘버 설정
					}
				}); //테이블 버튼 생성
		
		this.add(table1); //tableScreen에 table 붙이기

	}
}

class OrderList extends Box //주문 목록 상자
{	
	JPanel List = new JPanel();
	
	public OrderList()
	{
		JLabel Title = new JLabel("주문 목록");
		Title.setFont(new Font("돋움", Font.BOLD, 40));
		Title.setLocation(100, 20); 
		Title.setSize(400, 110); //"주문 목록"라벨 생성
		

		List.setSize(380 ,500);
		List.setLocation(10, 150);
		List.setLayout(null);
		List.setBorder(new LineBorder(Color.black, 2));
		
		
		this.setFont(new Font("Arial", Font.PLAIN, 13));
		 
		this.add(Title);
		this.add(List);
	}
	
	void PrintOrder()
	{
		this.List.removeAll();
		int y_coord = 100;
		program tmp = (program)this.getTopLevelAncestor();
		HashMap<String, Integer> temporary = tmp.t_info[tableScreen.selected_table-1].ordered;
		Set<String> keys = temporary.keySet();
		Iterator <String> it = keys.iterator();
		while(it.hasNext())
		{
			String key = it.next();
			JLabel menu = new JLabel(key);
			menu.setSize(100, 20);
			menu.setLocation(20, y_coord);
			
			String num = Integer.toString(temporary.get(key));
			
			JLabel number = new JLabel(num);
			number.setSize(50, 20);
			number.setLocation(60, y_coord);
			
			String mon = Integer.toString(MenuInfo.Menu.get(key)*(temporary.get(key)));
			
			JLabel money = new JLabel(mon);
			money.setSize(50, 20);
			money.setLocation(70, y_coord);
			
			this.List.add(menu);
			this.List.add(number);
			this.List.add(money);
			
			y_coord += 20;
		}
		this.List.repaint();
		this.List.revalidate();
	}
}

class TableInfo //현재 테이블 주문 내역
{
	HashMap<String, Integer> ordered = new HashMap<String, Integer>();
	
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
}

class MenuInfo //메뉴 가격 상수 해시맵
{
	static HashMap<String, Integer> Menu = new HashMap<String, Integer>();
	
	static void MakingMenuInfo() //메뉴 가격 설정
	{
		Menu.put("Juice", 2000);
		Menu.put("Cake", 5000);
		Menu.put("Latte", 3000);
		Menu.put("Tea", 4000);
	}
}

class OrderScreen extends JPanel //주문하는 화면
{		
	OrderList list = new OrderList(); 
	
	public OrderScreen()
	{
		this.setLayout(null);
		

		list.setLocation(30, 30); //OrderList 객체 생성
		
		MenuPan menupan = new MenuPan();
		menupan.setLocation(450, 30); //MenuPan 객체 생성
		
		JButton order = new JButton("처음 화면으로");
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
		order.setLocation(800, 900); //돌아가기 버튼 생성
		
		this.add(order);
		this.add(list);
		this.add(menupan);
	}
}

class MenuPan extends Box
{
	MenuOrder[] menuorder = new MenuOrder[4]; //Menu를 표시할 객체
	int previousMenuNumber = 0; //이전 MenuNumber의 숫자
	
	public MenuPan()
	{
		this.setLayout(null);
		
		for(int i=1;i<=4;i++) //MenuOrder 객체 4개 생성
		{
			menuorder[i-1] = new MenuOrder(i);
		};
		
		JPanel MenuNumber = new JPanel();
		MenuNumber.setLayout(new FlowLayout());
		MenuNumber.setSize(370, 180);
		MenuNumber.setLocation(20,20); //MenuNumber 객체 생성 및 위치 설정
		
		for(int i=1;i<=4;i++) //MenuNumber 생성
		{
			JButton button = new JButton(Integer.toString(i));
			button.setFont(new Font("Arial", Font.BOLD, 15));
			button.setPreferredSize(new Dimension(150, 50));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) //Button에 따라 MenuOrder를 바꾸는 리스너
				{
					JButton innerbutton = (JButton) e.getSource();
					int i = Integer.parseInt(innerbutton.getText());
					MenuPan innerPan = (MenuPan) innerbutton.getParent().getParent();
					
					if(previousMenuNumber != 0)
						innerPan.remove(menuorder[previousMenuNumber-1]);
					innerPan.add(menuorder[i-1]);
					
					previousMenuNumber = i;
					
					innerPan.repaint();
					innerPan.revalidate();
				}
			});
			MenuNumber.add(button);
		} //MenuNumber에 버튼 부착
		
		this.add(MenuNumber);
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
			JButton Menu1 = new JButton("Juice");
			Menu1.setFont(new Font("Arial", Font.BOLD, 13));
			Menu1.setPreferredSize(new Dimension(100, 50));
			Menu1.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							JButton innerbutton = (JButton)e.getSource();
							program innerFrame = (program) innerbutton.getTopLevelAncestor();
							innerFrame.t_info[tableScreen.selected_table-1].newOrder(innerbutton.getText());
							innerFrame.order.list.PrintOrder();
						}
					});
			
			this.add(Menu1);
		}
		else if(i==2)
		{
			this.setLayout(new FlowLayout());
			this.setSize(370, 400);
			this.setLocation(20, 200);
			JButton Menu1 = new JButton("Latte");
			Menu1.setFont(new Font("Arial", Font.BOLD, 13));
			Menu1.setPreferredSize(new Dimension(100, 50));
			Menu1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JButton innerbutton = (JButton)e.getSource();
					program innerFrame = (program) innerbutton.getTopLevelAncestor();
					innerFrame.t_info[tableScreen.selected_table-1].newOrder(innerbutton.getText());
					innerFrame.order.list.PrintOrder();
				}
			});
			this.add(Menu1);
		}
		else if(i==3)
		{
			this.setLayout(new FlowLayout());
			this.setSize(370, 400);
			this.setLocation(20, 200);
			JButton Menu1 = new JButton("Tea");
			Menu1.setFont(new Font("Arial", Font.BOLD, 13));
			Menu1.setPreferredSize(new Dimension(100, 50));
			Menu1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JButton innerbutton = (JButton)e.getSource();
					program innerFrame = (program) innerbutton.getTopLevelAncestor();
					innerFrame.t_info[tableScreen.selected_table-1].newOrder(innerbutton.getText());
					innerFrame.order.list.PrintOrder();
				}
			});
			this.add(Menu1);
		}
		else
		{
			this.setLayout(new FlowLayout());
			this.setSize(370, 400);
			this.setLocation(20, 200);
			JButton Menu1 = new JButton("Cake");
			Menu1.setFont(new Font("Arial", Font.BOLD, 13));
			Menu1.setPreferredSize(new Dimension(100, 50));
			Menu1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JButton innerbutton = (JButton)e.getSource();
					program innerFrame = (program) innerbutton.getTopLevelAncestor();
					innerFrame.t_info[tableScreen.selected_table-1].newOrder(innerbutton.getText());
					innerFrame.order.list.PrintOrder();
				}
			});
			this.add(Menu1);
		}
	}
}