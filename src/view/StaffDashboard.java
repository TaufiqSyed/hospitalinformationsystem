package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import util.ImageUtil;
import util.PanelUtil;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.Box.Filler;
import java.awt.Insets;

public class StaffDashboard extends Thread {

	private JFrame frame;
	private int h, w;
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
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(203, 213, 224));
		frame.setTitle("VitalSys Information System");
		frame.setIconImage(ImageUtil.getIcon("images\\vitalsys.png", 64, 64).getImage());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		GridBagConstraints c = new GridBagConstraints();

		int padding = 10;
		EmptyBorder edgeInsets = new EmptyBorder(padding, padding, padding, padding);
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(203, 213, 224));
		frame.setTitle("VitalSys Information System");
		frame.setIconImage(ImageUtil.getIcon("images\\vitalsys.png", 64, 64).getImage());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(new Color(203, 213, 224));
//		panel.setBorder(outerMargin);
		frame.getContentPane().add(panel);
		
		JPanel pnlLeftDash = PanelUtil.createRoundRectPanel(25, Color.WHITE);
		pnlLeftDash.setBorder(edgeInsets);
		pnlLeftDash.setLayout(new BorderLayout(0, 0));
		

		JLabel lblAus = new JLabel("AUS University Hospital");
		lblAus.setFont(new Font("Tahoma", Font.BOLD, 13));
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

		pnlPatients.setBorder(edgeInsets);
		pnlPatients.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPatients = new JLabel("Patients");
		lblPatients.setFont(new Font("Dialog", Font.PLAIN, 12));
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
		pnlDoctors.setBorder(edgeInsets);
		
		JPanel pnlRightDash = new JPanel();
		pnlRightDash.setBackground(new Color(0,0,0,0));
		
		pnlRightDash.setLayout(new GridLayout(2, 0, 0, 9));
		pnlRightDash.add(pnlPatients);
		pnlRightDash.add(pnlDoctors);
		pnlDoctors.setLayout(new BorderLayout(0, 0));
		
		int dashW = 2, glueW = 1;
		int x = 0, y = 0;
		
		// panel, item, width, height, x, y, weight_x, weight_y
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueW, x, y, 0.1, 0.05); // margin
		PanelUtil.addGridBagItem(panel, pnlLeftDash, dashW, dashW, x+=glueW, y+=glueW, 0.25, 0.25);
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueW, x+=dashW, y, 0.02, 0.05); // margin
		PanelUtil.addGridBagItem(panel, pnlRightDash, dashW, dashW, x+=glueW, y, 0.33, 0.5);
		PanelUtil.addGridBagItem(panel, Box.createGlue(), glueW, glueW+2, x+=dashW, y+=glueW, 0.1, 0.05); //margin
		
		JLabel lblDoctors = new JLabel("Doctors");
		lblDoctors.setFont(new Font("Dialog", Font.PLAIN, 12));
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
	
	public void run() {
		
	}
	
	public void paintComponent(Graphics g) {
		Rectangle r = frame.getBounds();
		h = r.height;
		w = r.width;
		
	}
}
