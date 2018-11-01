package imageController;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import kripleyFunction.kRipleyFunction;

public class ImageController {


	
public static String[] baseImagens() {
	String[] caminhos = {"yachNoise.jpg","amsBW_Noise.jpg","bsbNoise.jpg","casaraoNoise.jpg","ceuNoise.jpg",
			"forteNoise.jpg","plantasNoise.jpg","praia1Noise.jpg"};
	System.err.println(caminhos[0]);
	System.err.println(caminhos.length);
	return caminhos;
	
}
	
public static void exibeImagem() {
	
	String[] caminhos = baseImagens();
	
	for (int i = 0;i<caminhos.length;i++) {
//		String nomeImagem = "amsBW_Noise.jpg";
		String urlProcessada = "/home/alexsandro/Documents/"+caminhos[i];
		float[][] imagemMatriz = converteImageToMatriz(urlProcessada);
		
	    int raio = 3;
	    float limiar =5.5f;
	    int taxaDeAnalise = 500;//tamanho da sub área de análise. 
	    	float[][] matrizProcessada =  kRipleyFunction.remocaoDeRuidoBSD(imagemMatriz, raio, limiar, taxaDeAnalise);
		    String descricao = caminhos[i]+"_raio_"+raio+"_MODAMASK_"+i+"_limiar_"+limiar+"_taxa de analise_"+taxaDeAnalise+"x";
			imagemSaida(matrizProcessada,descricao);	
	}
		    
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
//  novo = (int)(canais[0] + canais[1] + canais[2]) / 3;
 if(canais[1]>0   && canais[2]>0) {
//	 System.err.println("troca de cores para gray");
	 novo = (canais[0] + canais[1] + canais[2]) / 3;	 
 }else {
	 novo = canais[0];
 }
 
 return novo;
}

//------------------------------------END---------------------////

public static void imagemSaida(float[][] matriz, String descricao) {
	BufferedImage image = new BufferedImage(matriz.length, matriz[0].length,BufferedImage.TYPE_INT_RGB);
	 try {
		 
    for(int i=0; i<matriz.length; i++) {
        for(int j=0; j<matriz[0].length; j++) {
            int a = (int) matriz[i][j];
            Color newColor = new Color(a,a,a);
           
            image.setRGB(i,j,newColor.getRGB());
        }
    }
    
    String nomeDoarquivo = descricao+"_"+".jpg";
//    String nomeDoarquivo = geraNomeAleatorio()+"_"+descricao+".jpg";
//    File output = new File("GrayScale_teste.jpg");
    File output = new File(nomeDoarquivo);
		ImageIO.write(image, "jpg", output);
		System.err.println("salvou a imagem");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public static void salvaArqText(float[][] matriz, String descricao) {
	String textoQueSeraEscrito = transformaMatrizToTxt(matriz);
	FileWriter arquivo;
	try {
		arquivo = new FileWriter(new File(descricao+".txt"));
		arquivo.write(textoQueSeraEscrito);
		arquivo.close();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
}


public static String geraNomeAleatorio() {
	// Determia as letras que poderão estar presente nas chaves
	String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";
	 
	Random random = new Random();
	 
	String armazenaChaves = "";
	int index = -1;
	for( int i = 0; i < 3; i++ ) {
	   index = random.nextInt( letras.length() );
	   armazenaChaves += letras.substring( index, index + 1 );
	}
	System.err.println( armazenaChaves );
	
	return armazenaChaves;
}

public static String transformaMatrizToTxt(float[][] matrix) {
	String arquivo="";
	for(int i = 0; i< matrix.length; i++) {
		
		for(int j = 0; j< matrix[0].length; j++) {
			if(matrix[i][j] == 0) {
				arquivo+=" | "+matrix[i][j]+" ";
			}else {
				arquivo+=" | "+matrix[i][j];
			}
		} 
		
		arquivo+=" |";
	}
	
	
	return arquivo;
}


public static void calculaErroQuadratico() {
	
//	String nomeImagemOriginal = "";
	String urlOriginais = "/home/alexsandro/Documents/oasisBW.jpg";
	float[][] imagemOriginal = converteImageToMatriz(urlOriginais);
	
	String urlProcessada = "/home/alexsandro/git/FiltroRuidos/imagensFinais/oasis_Final.jpg";
//	String urlProcessada = "/home/alexsandro/Documents/oasisBW_Media.jpg";
	float[][] imagemProcessada = converteImageToMatriz(urlProcessada);
	int acertos=0;
	int totalElementos = imagemOriginal.length*imagemOriginal[0].length;
	float erroQuadratico=0.00f;
	System.err.println("\nTotal de elementos:"+totalElementos);
	for(int i = 0; i<imagemOriginal.length;i++) {
    	for(int j = 0; j<imagemOriginal[0].length;j++) {
    		if(imagemOriginal[i][j] == imagemProcessada[i][j]) {
    			acertos++;
//    			System.out.println("valor na original: "+imagemOriginal[i][j]+" Valor no process: "+imagemProcessada[i][j]);
    		}else {
//    			System.out.println("valor na original: "+imagemOriginal[i][j]+" Valor no process: "+imagemProcessada[i][j]);
    		}
    		
    		erroQuadratico += Math.abs((imagemOriginal[i][j]-imagemProcessada[i][j]));
    		
    	}
    		
    }
	
	System.err.println("\nErro quadratico:"+erroQuadratico);
	
	System.err.println("\nErro quadratico pelo numero de elementos:"+Math.sqrt(erroQuadratico/totalElementos));
	System.err.println("\nAcertos: "+acertos);
	if(acertos>0) {
		System.err.println(acertos);
		float acuracia = acertos/totalElementos;
		System.err.println("\nAcurácia de:"+acuracia);
	}else {
		System.err.println("\nErro igual a zero");
	}
	
	
}	


public static float[][] converteImageToMatriz(String url){
	
	try {
	
//	String nomeImagem = nomeArquivo+"_";
//    File img = new File("/home/alexsandro/Documents/"+nomeImagem+".jpg");
		System.err.println("Caminho do arquivo:"+url);
		File img = new File(url);
    BufferedImage image = ImageIO.read(img ); 
    int m = image.getWidth();
    int n = image.getHeight();
    int[] arrayPixel = new int[3];
    WritableRaster raster = image.getRaster();
    float[][] imagemMatriz = new float[m][n];
    for(int i = 0; i<m;i++) {
    	for(int j = 0; j<n;j++) {
//    		 System.out.print("R : " + raster.getPixel(i, j, arrayPixel)[0] + " | ");
//             System.out.print("G : " + raster.getPixel(i, j, arrayPixel)[1] + " | ");
//             System.out.println("B : " + raster.getPixel(i, j, arrayPixel)[2]);
    		arrayPixel[0] = raster.getPixel(i, j, arrayPixel)[0];
    		arrayPixel[1] = raster.getPixel(i, j, arrayPixel)[1];
    		arrayPixel[2] = raster.getPixel(i, j, arrayPixel)[2];
    		imagemMatriz[i][j]= media(arrayPixel);
    	}
    }
//    kRipleyFunction.imprimeMatriz(imagemMatriz);
	    return imagemMatriz;
		
		} catch (IOException e) { 
		    e.printStackTrace(); 
		    return null;
		}
		
}


}


