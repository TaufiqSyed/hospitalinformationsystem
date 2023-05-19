package utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageUtil {
	public static ImageIcon getIcon(String path, int height, int width) {
		ImageIcon imgIcon = new ImageIcon(path);
		Image img = imgIcon.getImage();
		Image newimg = img.getScaledInstance(height, width,  java.awt.Image.SCALE_SMOOTH);
		imgIcon = new ImageIcon(newimg);
		return imgIcon;
	}
}
