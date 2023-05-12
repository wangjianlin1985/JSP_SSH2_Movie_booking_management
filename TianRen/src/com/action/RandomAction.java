// 
// 
// 

package com.action;

import javax.imageio.stream.ImageOutputStream;
import java.awt.Graphics;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import com.opensymphony.xwork2.ActionContext;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import com.opensymphony.xwork2.ActionSupport;

public class RandomAction extends ActionSupport
{
    private ByteArrayInputStream inputStream;
    
    public String execute() throws Exception {
        final int width = 60;
        final int height = 20;
        final BufferedImage image = new BufferedImage(width, height, 1);
        final Graphics g = image.getGraphics();
        final Random random = new Random();
        g.setColor(this.getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", 0, 18));
        g.setColor(this.getRandColor(160, 200));
        for (int i = 0; i < 155; ++i) {
            final int x = random.nextInt(width);
            final int y = random.nextInt(height);
            final int xl = random.nextInt(12);
            final int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String sRand = "";
        for (int j = 0; j < 4; ++j) {
            final String rand = String.valueOf(random.nextInt(10));
            sRand = String.valueOf(sRand) + rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * j + 6, 16);
        }
        ActionContext.getContext().getSession().put("rand", sRand);
        g.dispose();
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
        ImageIO.write(image, "JPEG", imageOut);
        imageOut.close();
        final ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        this.setInputStream(input);
        return "success";
    }
    
    private Color getRandColor(int fc, int bc) {
        final Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        final int r = fc + random.nextInt(bc - fc);
        final int g = fc + random.nextInt(bc - fc);
        final int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    public void setInputStream(final ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    public ByteArrayInputStream getInputStream() {
        return this.inputStream;
    }
}
