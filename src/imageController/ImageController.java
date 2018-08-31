package imageController;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Random;

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
	    
	
//	   System.out.println(toGrayscale(image)); 
	    
	    for(int i = 0; i<m;i++) {
	    	for(int j = 0; j<n;j++) {
//	    		 System.out.print("R : " + raster.getPixel(i, j, arrayPixel)[0] + " | ");
//                 System.out.print("G : " + raster.getPixel(i, j, arrayPixel)[1] + " | ");
//                 System.out.println("B : " + raster.getPixel(i, j, arrayPixel)[2]);
	    		
	    		 raster.getPixel(i, j, arrayPixel) ;
	    		
//	    		System.out.println(arrayPixel[0]);
//                 image.setRGB(i, j, media(arrayPixel));
//	    		System.out.println(image.getRGB(i, j));
	    		imagemMatriz[i][j]= media(arrayPixel);
	    	}
	    }
	    
//	    kRipleyFunction.imprimeMatriz(imagemMatriz);
	    
//	    kRipleyFunction.imprimeMatriz(imagemMatriz);
//	    Desktop.getDesktop().open(image);
	    
//	    Graphics2D g = image.createGraphics();
//	    
//		g.drawImage(image, 0, 0, null);
//		g.dispose();
	    
	    kRipleyFunction.remocaoDeRuidoBSD(imagemMatriz, 1, 0.6f);
	    
	    
		imagemSaida(imagemMatriz);
		
		return imagemMatriz;
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

public static void imagemSaida(float[][] matriz) {
	BufferedImage image = new BufferedImage(matriz.length, matriz[0].length,BufferedImage.TYPE_INT_RGB);
	 try {
		 
    for(int i=0; i<matriz.length; i++) {
        for(int j=0; j<matriz[0].length; j++) {
            int a = (int) matriz[i][j];
            Color newColor = new Color(a,a,a);
            image.setRGB(i,j,newColor.getRGB());
        }
    }
    
    String nomeDoarquivo = geraNomeAleatorio()+".jpg";
//    File output = new File("GrayScale_teste.jpg");
    File output = new File(nomeDoarquivo);
		ImageIO.write(image, "jpg", output);
		System.err.println("salvou a imagem");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static String geraNomeAleatorio() {
	// Determia as letras que poderão estar presente nas chaves
	String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";
	 
	Random random = new Random();
	 
	String armazenaChaves = "";
	int index = -1;
	for( int i = 0; i < 9; i++ ) {
	   index = random.nextInt( letras.length() );
	   armazenaChaves += letras.substring( index, index + 1 );
	}
	System.err.println( armazenaChaves );
	
	return armazenaChaves;
}

}


