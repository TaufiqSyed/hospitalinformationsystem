package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import util.ImageUtil;

import javax.swing.ImageIcon;

import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Welcome implements ActionListener {

	private JFrame frame;
	private JButton btnDoctor, btnStaff;
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Welcome() {
		initialize();
	}

	private void initialize() {
		ImageIcon vitalsysIcon = ImageUtil.getIcon("images\\vitalsys.png", 32, 32);
		ImageIcon doctorIcon = ImageUtil.getIcon("images\\doctoricon.png", 1707/16, 2133/16);
		ImageIcon staffIcon =  ImageUtil.getIcon("images\\stafficon.png", 2133/16, 2133/16);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblWelcome = new JLabel("VitalSys Hospital Information System", vitalsysIcon, JLabel.CENTER);
		lblWelcome.setForeground(new Color(0, 0, 0));
		lblWelcome.setBackground(new Color(255, 255, 255));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWelcome.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		frame.getContentPane().add(lblWelcome, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		btnDoctor = new JButton("I Am a Doctor", doctorIcon);
		btnDoctor.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDoctor.setVerticalTextPosition(AbstractButton.BOTTOM);
		btnDoctor.setHorizontalTextPosition(AbstractButton.CENTER);
		btnDoctor.addActionListener(this);
		panel.add(btnDoctor);
		
		btnStaff = new JButton("I Am a Staff Member", staffIcon);
		btnStaff.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnStaff.setVerticalTextPosition(AbstractButton.BOTTOM);
		btnStaff.setHorizontalTextPosition(AbstractButton.CENTER);
		btnStaff.addActionListener(this);
		panel.add(btnStaff);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == btnDoctor) {
			//1. Create the frame.
			JFrame frame2 = new JFrame("FrameDemo");

			//2. Optional: What happens when the frame closes?
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


			//3. Create components and put them in the frame.
			//...create emptyLabel...
			frame2.getContentPane().add(new JLabel("emptylabel"), BorderLayout.CENTER);

			//4. Size the frame.
			frame2.pack();

			//5. Show it.
			frame2.setVisible(true);
		}
	}

}
