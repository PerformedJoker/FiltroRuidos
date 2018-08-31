package imageController;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import kripleyFunction.kRipleyFunction;

public class ImageController {

	
public static float[][] exibeImagem() {
	 
//	BufferedImage img = new BufferedImage(256, 256,
//		    BufferedImage.TYPE_INT_RGB);
//	
	
	
	try {
	    File img = new File("/home/alexsandro/Documents/rgb.png");
	    BufferedImage image = ImageIO.read(img ); 
	    int m = image.getWidth();
	    int n = image.getHeight();
	    int[] arrayPixel = new int[3];
	    WritableRaster raster = image.getRaster();
	    float[][] imagemMatriz = new float[m][n];
	    
	    BufferedImage imagemEscalaDeCinza = toGrayscale(image);
//	   System.out.println(toGrayscale(image)); 
	    
	    for(int i = 0; i<m;i++) {
	    	for(int j = 0; j<n;j++) {
	    		 System.out.print("R : " + raster.getPixel(i, j, arrayPixel)[0] + " | ");
                 System.out.print("G : " + raster.getPixel(i, j, arrayPixel)[1] + " | ");
                 System.out.println("B : " + raster.getPixel(i, j, arrayPixel)[2]);
//	    		System.out.println(arrayPixel[0]);
//                 image.setRGB(i, j, media(arrayPixel));
//	    		System.out.println(image.getRGB(i, j));
	    		imagemMatriz[i][j]= media(arrayPixel);
	    	}
	    }
	    
//	    kRipleyFunction.imprimeMatriz(imagemMatriz);
	    return imagemMatriz;
//	    kRipleyFunction.imprimeMatriz(imagemMatriz);
//	    Desktop.getDesktop().open(image);
	    
//	    Graphics2D g = image.createGraphics();
//	    
//		g.drawImage(image, 0, 0, null);
//		g.dispose();
		
//	    System.out.println(image);
	} catch (IOException e) { 
	    e.printStackTrace(); 
	}
	return null;
}


//---------------MÉTODOS PARA TRANSFORMAR EM TONS DE CINZA-----------------////
//Metodo da combinacao linear (deixa mais suavizado)
public static int combLinear( int r,  int g, int b)
{
 int novo;
 novo = (int) (0.3*r + 0.59*g + 0.11*b);
 return novo;
}

//Metodo da media (
public static int media( int[] canais){
 int novo;
 novo = (canais[0] + canais[1] + canais[2]) / 3;
 return novo;
}

//------------------------------------END---------------------////



private static BufferedImage toGrayscale(BufferedImage image)  throws IOException {  
    BufferedImage output = new BufferedImage(image.getWidth(),  
      image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);  
    
    Graphics2D g2d = output.createGraphics();  
    g2d.drawImage(image, 0, 0, null);  
    g2d.dispose();  
    return output;  
	}

}
