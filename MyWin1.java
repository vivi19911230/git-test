package iii;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//class A{//外部類別
//	public void m1() {
//		System.out.println("mmm");
//}
//	class B{//內部類別
//		public void m2() {
//			System.out.println("bbb");
//		
//	}
//}

public class MyWin1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;//寫在MAIN外面 所有變數都看的到

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//匿名類部類別
		//A()子類別 A父類別名稱
//		A v1 = new A() {};
//		v1.m1();
		
		
		
		
		//------內部類別
		
//		new A().new B().m2();
//		
//		A a = new A();
//		A.B b = a.new B();
//		b.m2();
//		b.m2();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyWin1 frame = new MyWin1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyWin1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		contentPane.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u78BA\u5B9A");
		btnNewButton.addActionListener(
				new MyActionListener()
				
		/*		
		new ActionListener() {//匿名內部類別 new __(){}
			public void actionPerformed(ActionEvent e) {//註冊 實作抽象方法
				System.out.println("歡迎"+textField.getText() +"光臨..");
				JOptionPane.showMessageDialog(null, "歡迎"+textField.getText() +"光臨..");//事件處裡
			}
		}*/
		);
		btnNewButton.setFont(new Font("微軟正黑體", Font.PLAIN, 28));
		contentPane.add(btnNewButton, BorderLayout.CENTER);
	}
	//內部類別可以直接使用外部類別的成員(變數與方法)=>可用匿名戶替^
	class MyActionListener implements ActionListener{
		
	

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("歡迎"+textField.getText() +"光臨..");
			JOptionPane.showMessageDialog(null, "歡迎"+textField.getText() +"光臨..");//事件處裡
	
			
		}
		
	}
}

