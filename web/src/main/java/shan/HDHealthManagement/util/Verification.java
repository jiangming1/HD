package shan.HDHealthManagement.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * @author zhaozhangshan 验证码生成类
 * 
 */
public class Verification {
	
	/**
	 * 生成随机验证码
	 * @return
	 */
	public static String verificationCode(){
		Random random = new Random();
		String sRand = "";
		String code = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghigklnmpoqrstuvwxyz123456789";
		for (int i = 0; i < 4; i++) {
			sRand += code.charAt(random.nextInt(59));
		}
		return sRand;
	}
	
	/**
	 * 生成随机验证码图片
	 * @param sRand
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage imageCode(String sRand){
		int width = 65, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random(); 
		g.setColor(getRandColor(230, 255));
		g.fillRect(0, 0, 100, 25); 
		g.setFont(new Font("Arial", Font.CENTER_BASELINE | Font.ITALIC, 18)); 
		g.drawLine(0, 0, 0, 0); 
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(sRand.charAt(i));
			g.setColor(getRandColor(100, 150));
			g.drawString(rand, 15 * i + 6, 16);
		}
		for (int i = 0; i < (random.nextInt(5) + 5); i++) {
			g.setColor(new Color(random.nextInt(255) + 1,
					random.nextInt(255) + 1, random.nextInt(255) + 1));
			g.drawLine(random.nextInt(100), random.nextInt(30),
					random.nextInt(100), random.nextInt(30));
		}
		g.dispose();
		return image;
	}
	
	/**
	 * 生成随机颜色
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
