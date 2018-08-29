package imageController;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import kripleyFunction.kRipleyFunction;

public class ImageController {

	
public static void exibeImagem() {
	 
//	BufferedImage img = new BufferedImage(256, 256,
//		    BufferedImage.TYPE_INT_RGB);
//	
	
	
	try {
	    File img = new File("/home/alexsandro/Documents/rgb.png");
	    BufferedImage image = ImageIO.read(img ); 
	    int m = image.getWidth();
	    int n = image.getHeight();
	    float[][] imagemMatriz = new float[m][n];
	    
	    for(int i = 0; i<m;i++) {
	    	for(int j = 0; j<n;j++) {
//	    		image.get
	    		imagemMatriz[i][j]= image.getRGB(i, j);
	    	}
	    }
	    
	    
	    kRipleyFunction.imprimeMatriz(imagemMatriz);
	    
	    
//	    Graphics2D g = image.createGraphics();
//		g.drawImage(img, 0, 0, null);
//		g.dispose();
		
	    System.out.println(image);
	} catch (IOException e) { 
	    e.printStackTrace(); 
	}
	
	
	
}
	
}
