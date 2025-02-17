package com.taoxiuxia.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCode {
	
	
	public void generationCheckCode (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma", "no-cache");
		
		int height = 30;
		int width = 120;
		int xpyl = 5;
		int ypyl = 22;
		int bang = 20;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics2D g = (Graphics2D) img.getGraphics();

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width - 1, height - 1);

		for (int i = 0; i < 5; i++) {
			g.setColor(Color.red);
			g.drawLine(randNum(0, width), randNum(0, height), randNum(0, width), randNum(0, height));
		}

		String base = "123456789";

		for (int i = 0; i < 4; i++) {
			g.setColor(new Color(randNum(0, 255), randNum(0, 255), randNum(0, 255)));
			g.setFont(new Font("黑体", Font.BOLD, bang));
			int r=randNum(-45,45);
			g.rotate(1.0 * r / 180 * Math.PI, xpyl + (i * 30), ypyl);
			g.drawString(base.charAt(randNum(0, base.length() - 1)) + "", xpyl + (i * 30), ypyl);
			g.rotate(1.0 * -r / 180 * Math.PI, xpyl + (i * 30), ypyl);
		}
		ImageIO.write(img, "jpg", response.getOutputStream());
	}

	private Random rand = new Random();

	private int randNum(int begin, int end) {
		return rand.nextInt(end - begin) + begin;
	}
}
