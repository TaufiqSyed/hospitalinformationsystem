package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utils.ImageUtil;
import utils.PanelUtil;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.GridBagLayout;

public class StaffDashboard {

	private JFrame frame;
	private JPanel panel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffDashboard window = new StaffDashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StaffDashboard() {
//		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(203, 213, 224));
		frame.setTitle("VitalSys Information System");
		frame.setIconImage(ImageUtil.getIcon("images\\vitalsys.png", 64, 64).getImage());
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		int leftPadW = 20, leftPadH = 15;
		int rightPadW = 15, rightPadH = 12;
		
		EmptyBorder leftPad = new EmptyBorder(leftPadH, leftPadW, leftPadH, leftPadW);
		EmptyBorder rightPad = new EmptyBorder(rightPadH, rightPadW, rightPadH, rightPadW);
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(new Color(203, 213, 224));
		frame.getContentPane().add(panel);
		
		JPanel pnlLeftDash = PanelUtil.createRoundRectPanel(25, Color.WHITE);
		pnlLeftDash.setBorder(leftPad);
		pnlLeftDash.setLayout(new BorderLayout(0, 0));
		

		JLabel lblAus = new JLabel("AUS University Hospital");
		lblAus.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblAus.setHorizontalAlignment(SwingConstants.LEFT);
		lblAus.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlLeftDash.add(lblAus, BorderLayout.NORTH);
		
		JPanel pnlFloors = new JPanel();
		pnlFloors.add(new JLabel("Hello there what's up lol"));
		pnlFloors.add(new JLabel("Hello there what's up lol"));
		pnlFloors.add(new JLabel("Hello there what's up lol"));
		pnlFloors.add(new JLabel("Hello there what's up lol"));
		pnlFloors.add(new JLabel("Hello there what's up lol"));
		pnlFloors.add(new JLabel("Hello there what's up lol"));
		pnlFloors.add(new JLabel("Hello there what's up lol"));
		pnlLeftDash.add(pnlFloors, BorderLayout.CENTER);
		pnlFloors.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pnlPatients = PanelUtil.createRoundRectPanel(25, Color.WHITE);
		pnlPatients.setBorder(rightPad);
		pnlPatients.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPatients = new JLabel("Patients");
		lblPatients.setFont(new Font("Dialog", Font.BOLD, 15));
		pnlPatients.add(lblPatients, BorderLayout.NORTH);
		
		JScrollPane scrPatients = new JScrollPane();
		pnlPatients.add(scrPatients, BorderLayout.CENTER);
		
		
		JPanel panel_1 = new JPanel();
		scrPatients.setViewportView(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("Hello there what's up lol");
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Hello there what's up lol");
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Hello there what's up lol");
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Hello there what's up lol");
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("Hello there what's up lol");
		panel_1.add(label_4);
		
		
		JPanel pnlDoctors = PanelUtil.createRoundRectPanel(25, Color.WHITE);
		pnlDoctors.setBorder(rightPad);
		
		JPanel pnlRightDash = new JPanel();
		pnlRightDash.setBackground(new Color(0,0,0,0));
		
		pnlRightDash.setLayout(new GridLayout(2, 0, 0, 9));
		pnlRightDash.add(pnlPatients);
		pnlRightDash.add(pnlDoctors);
		pnlDoctors.setLayout(new BorderLayout(0, 0));
		
		
		int marginW = 2, marginH = 2, glueW = 1, glueH = 1, x = 0, y = 0;
		
		// panel, item, width, height, x, y, weight_x, weight_y
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueH, x, y, 0.1, 0.05); // margin
		PanelUtil.addGridBagItem(panel, pnlLeftDash, marginW, marginH, x+=glueW, y+=glueH, 0.25, 0.25);
		
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueH, x+=marginW, y, 0.02, 0.05); // margin
		PanelUtil.addGridBagItem(panel, pnlRightDash, marginW, marginH, x+=glueW, y, 0.33, 0.5);
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueH+2, x+=marginW, y+=glueH, 0.1, 0.05); //margin
		
		
		JLabel lblDoctors = new JLabel("Doctors");
		lblDoctors.setFont(new Font("Dialog", Font.BOLD, 15));
		pnlDoctors.add(lblDoctors, BorderLayout.NORTH);
		
		JScrollPane scrDoctors = new JScrollPane();
		pnlDoctors.add(scrDoctors, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		scrDoctors.setViewportView(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel label_5 = new JLabel("Hello there what's up lol");
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("Hello there what's up lol");
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("Hello there what's up lol");
		panel_2.add(label_7);
		
		JLabel label_8 = new JLabel("Hello there what's up lol");
		panel_2.add(label_8);
		
		JLabel label_9 = new JLabel("Hello there what's up lol");
		panel_2.add(label_9);
		
		JLabel label_10 = new JLabel("Hello there what's up lol");
		panel_2.add(label_10);
		
		JLabel label_11 = new JLabel("Hello there what's up lol");
		panel_2.add(label_11);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(236, 236, 236));
		frame.setJMenuBar(menuBar);
		
		JMenu mnFloors = new JMenu("Floors");
		menuBar.add(mnFloors);
		
		JMenu mnPatients = new JMenu("Patients");
		menuBar.add(mnPatients);
		
		JMenu mnDoctors = new JMenu("Doctors");
		menuBar.add(mnDoctors);
		
		JMenu mnStatistics = new JMenu("Statistics");
		menuBar.add(mnStatistics);
	}
}
