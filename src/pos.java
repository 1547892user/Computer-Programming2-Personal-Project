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

	firstScreen first = new firstScreen(); //firstScreen 객체 생성
	OrderScreen order = new OrderScreen(); //OrderScreen 객체 생성
	
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

class firstScreen extends JPanel //초기화면
{
	public firstScreen()
	{
		this.setLayout(null);
		tableScreen tS = new tableScreen();
		tS.setLocation(30, 30); //tableScreen 객체 생성
		
		OrderList OL = new OrderList();
		OL.setLocation(450, 30); //OrderList 객체 생성
		
		this.add(OL);
		this.add(tS);
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
	private int selected_table=0; //선택된 테이블
	
	public tableScreen()
	{
		this.setBorder(new TitledBorder(new LineBorder(Color.black, 2), "테이블")); //"테이블"이 제목인 경계선
		
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
	
	int getSelectedTable() //선택된 테이블 번호 넘겨주는 메소드
	{
		return selected_table;
	}
}

class OrderList extends Box //주문 목록 상자
{
	public int table_no;
	
	public OrderList()
	{
		JLabel Title = new JLabel("주문 목록");
		Title.setFont(new Font("돋움", Font.BOLD, 40));
		Title.setLocation(100, 20); 
		Title.setSize(400, 110); //"주문 목록"라벨 생성
		
		this.add(Title);
	}
}

class TableInfo //현재 테이블 주문 내역
{
	HashMap<String, Integer> ordered = new HashMap<String, Integer>();
	
	void newOrder(String menu, int number)
	{
		
	}
}

class MenuInfo //메뉴 가격 상수 해시맵
{
	static HashMap<String, Integer> Menu = new HashMap<String, Integer>();
	
	static void MakingMenuInfo() //메뉴 가격 설정
	{
		
	}
}

class OrderScreen extends JPanel //주문하는 화면
{
	public OrderScreen()
	{
		
	}
}