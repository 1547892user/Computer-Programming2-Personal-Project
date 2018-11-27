import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPOS extends JFrame
{
	public MyPOS()
	{
		setTitle("오늘도 대박나길");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1900, 1800);
		
		
		setVisible(true);
	}
	
	public static void main(String args[])
	{
		new MyPOS();
	}
}

class MainScreen extends JPanel
{
	
}