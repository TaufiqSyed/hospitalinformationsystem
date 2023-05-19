package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelUtil {
	public static JPanel createRoundRectPanel(int borderRadius, Color bgColor) {
		JPanel result = new JPanel(){
			private static final long serialVersionUID = -5269065918948721532L;
			@Override
		     protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Dimension arcs = new Dimension(borderRadius,borderRadius);
		        int width = getWidth();
		        int height = getHeight();
		        Graphics2D graphics = (Graphics2D) g;
		        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        
		        // Background
		        graphics.setColor(bgColor);
		        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
		        
		        // Border
		        graphics.setColor(new Color(180, 180, 180));
		        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
			}
		};
		result.setBounds(10,10,100,30);
		result.setOpaque(false);
		return result;
	}
	
	public static void addGridBagItem(JPanel panel, Component item, int width, int height, int x, int y, double weightx, double weighty) {
		// For short grid bag code
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = height;
		c.gridwidth = width;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = x;
		c.gridy = y;
		c.weightx = weightx;
		c.weighty = weighty;
		panel.add(item, c);
	}
	
	public static void pad(Component panel, int padTop, int padLeft, int padBottom, int padRight) {
		EmptyBorder padding = new EmptyBorder(padTop, padLeft, padBottom, padRight);
		((JComponent) panel).setBorder(padding);
	}
}
