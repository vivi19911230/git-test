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

//class A{//�~�����O
//	public void m1() {
//		System.out.println("mmm");
//}
//	class B{//�������O
//		public void m2() {
//			System.out.println("bbb");
//		
//	}
//}

public class MyWin1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;//�g�bMAIN�~�� �Ҧ��ܼƳ��ݪ���

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//�ΦW�������O
		//A()�l���O A�����O�W��
//		A v1 = new A() {};
//		v1.m1();
		
		
		
		
		//------�������O
		
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
		textField.setFont(new Font("�L�n������", Font.PLAIN, 18));
		contentPane.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u78BA\u5B9A");
		btnNewButton.addActionListener(
				new MyActionListener()
				
		/*		
		new ActionListener() {//�ΦW�������O new __(){}
			public void actionPerformed(ActionEvent e) {//���U ��@��H��k
				System.out.println("�w��"+textField.getText() +"���{..");
				JOptionPane.showMessageDialog(null, "�w��"+textField.getText() +"���{..");//�ƥ�B��
			}
		}*/
		);
		btnNewButton.setFont(new Font("�L�n������", Font.PLAIN, 28));
		contentPane.add(btnNewButton, BorderLayout.CENTER);
	}
	//�������O�i�H�����ϥΥ~�����O������(�ܼƻP��k)=>�i�ΰΦW���^
	class MyActionListener implements ActionListener{
		
	

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("�w��"+textField.getText() +"���{..");
			JOptionPane.showMessageDialog(null, "�w��"+textField.getText() +"���{..");//�ƥ�B��
	
			
		}
		
	}
}

