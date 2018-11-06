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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import kripleyFunction.kRipleyFunction;
import filtroRuidos.main;

public class ImageController {
	
public static String[] baseImagens() {
	
	String[] nomeImagem = {"haiaBW_SaltAndPepper.jpg","amsBW_Noise.jpg","casaraoNoise.jpg","ceuNoise.jpg",
			"forteNoise.jpg","plantasNoise.jpg","praia1Noise.jpg","ufmaNoise.jpg","veinSaltAndPepper.png",
			"tulipaBW_SaltAndPepper.jpg","restBW_Noise.jpg","oasisSaltAndPepper_.jpg",
			 "lenaGraySaltAndPeper.jpg","bsbBW_SaltAndPepper.jpg","yachNoise.jpg"};
	System.err.println("Numero de imagens: "+nomeImagem.length);
	return nomeImagem;
}


public static String[] baseImagensProcessadas() {
	String[] caminhos = {"amsBW_Noise.jpg","casaraoNoise.jpg","ceuNoise.jpg",
			"forteNoise.jpg","plantasNoise.jpg","praia1Noise.jpg"};
	System.err.println("Numero de imagens: "+caminhos.length);
	return caminhos;
}
	

/*public static String[] todasImagensProcessadas() {
	String[] titulos = {
			"ceuNoise.jpg"
			"ceuSNR.jpg",
			"ceu_gaussiana.jpg",
			"ceu_media.jpg
			"ceu_mediana.jpg
			"forteNoise.jpg
			"forteSNR.jpg
			"forte_gaussiana.jpg
			"forte_media.jpg
			"forte_mediana.jpg
			"haiaBW_SaltAndPepper.jpg
			"haiaBW_gaussiana.jpg
			"haiaBW_media.jpg
			"haiaBW_mediana.jpg
			"haiaSNR.jpg
			"oasisBW_Media.jpg
			"oasisBW_Mediana.jpg
			"oasisGaussianNoise.jpg
			"oasisSNR.jpg
			"oasisSaltAndPepper_.jpg
			"plantasNoise.jpg
			"plantasSNR.jpg
			"plantas_gaussiana.jpg
			"plantas_media.jpg
			"plantas_mediana.jpg
			"praia1Noise.jpg
			"praia1SNR.jpg
			"praia1_gaussiana.jpg
			"praia1_media.jpg
			"praia1_mediana.jpg
			"rest.jpeg
			"restBW_Gaussiano.jpg
			"restBW_Noise.jpg
			"restSNR.jpg
			"tulipa.jpg
			"tulipaBW_Gaussiano.jpg
			"tulipaBW_SaltAndPepper.jpg
			"tulipaSNR.jpg
			"ufmaNoise.jpg
			"ufmaSNR.jpg
			"ufma_gaussiana.jpg
			"ufma_media.jpg
			"ufma_mediana.jpg
			"veinBW_Gaussiano.png
			"veinBW_SaltPepper.png
			"veinSNR.jpg
			"veinSaltAndPepper.png
			"yachGaussian.jpg
			"yachMedia.jpg
			"yachMediana.jpg
			"yachNoise.jpg
			"yachSNR.jpg
			
	}
	
	
}*/



public static void exibeImagem() {
	
	String[] nomeImagem = baseImagens();
	
	for (int i = 0;i<nomeImagem.length;i++) {
		 long tempoInicial = System.currentTimeMillis();
//		String nomeImagem = "amsBW_Noise.jpg";
		String urlProcessadaMacOS = "/Users/alexsandrosaraiva/Desktop/pastaDocumentos/"+nomeImagem[i];
//		String urlProcessada = "/home/alexsandro/Documents/"+caminhos[i];
		float[][] imagemMatriz = converteImageToMatriz(urlProcessadaMacOS);
		
	    int raio = 2;
	    float limiar =1.0f;
	    int taxaDeAnalise = 500;//tamanho da sub área de análise. 
	    	float[][] matrizProcessada =  kRipleyFunction.remocaoDeRuidoBSD(imagemMatriz, raio, limiar, taxaDeAnalise);
		    String descricao = nomeImagem[i]+"_raio_"+raio+"_FULL-SNR_"+i+"_limiar_"+limiar+"_taxa de analise_"+taxaDeAnalise+"x";
		    
			imagemSaida(matrizProcessada,descricao);	
			long tempoFinal = System.currentTimeMillis();
		      System.out.printf("A imagem acima demorou: %.2f ", ((tempoFinal - tempoInicial) / 1000d)/60);
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
		System.err.println("\nsalvou a imagem :"+ descricao);
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


public static void salvaArqTextResultadosTeste( String texto, String descricao) {
	FileWriter arquivo;
	try {
		arquivo = new FileWriter(new File(descricao+".txt"));
		arquivo.write(texto);
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
	
//	String[] caminhos = baseImagens();
	
	
	
//	for (int c = 0;c<caminhos.length;c++) {
		
		//diretório  Meus documetos  Linux SSP
//		String urlOriginais = "/home/alexsandro/Documents/"+caminhos[c];
	
	
	
//	while(true) {
	
		
		String urlProcessadaDocumentos = "/home/alexsandro/Documents/";
		String urlOriginais = selectedImage();
		float[][] imagemOriginal = converteImageToMatriz(urlOriginais);
		
		
		//Caminho do diretorio de imagens Linux
		String urlProcessadaFiltro = "/home/alexsandro/git/FiltroRuidos/imagensFinais";
		
		String results = "";
		
		//Caminho do diretório de imagens no Mac OS
//		String urlProcessadaMac = "/Users/alexsandrosaraiva/Documents/workspace-sts/FiltroRuidos";
		String urlProcessada = selectedImage();
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
		float erroQudraticoMedio = erroQuadratico/totalElementos;
		System.err.println("\nErro quadratico médio pelo numero de elementos:"+Math.sqrt(erroQuadratico));
		System.err.println("\nAcertos: "+acertos);
		
		results = "Caminho da imagem:"+urlProcessada+"\nErro quadrático: "+erroQuadratico+"\nErro quadrático médio:"+
		erroQudraticoMedio+"\nTotal de acertos: "+acertos;
		
		salvaArqTextResultadosTeste(results,urlProcessada);
//		if(acertos>0) {
//			System.err.println(acertos);
//			float acuracia = acertos/totalElementos;
//			System.err.println("\nAcurácia de:"+acuracia);
//		}else {
//			System.err.println("\nErro igual a zero");
//		}
//	}
	
}	



/*public static void calculaErroQuadraticoAutomatizado() {
	
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
		
		String urlProcessadaDocumentos = "/home/alexsandro/Documents/";
		String urlOriginais = selectedImage();
		float[][] imagemOriginal = converteImageToMatriz(urlOriginais);
		
		
		//Caminho do diretorio de imagens Linux
		String urlProcessadaFiltro = "/home/alexsandro/git/FiltroRuidos/imagensFinais";
		
		String results = "";
		
		//Caminho do diretório de imagens no Mac OS
//		String urlProcessadaMac = "/Users/alexsandrosaraiva/Documents/workspace-sts/FiltroRuidos";
		String urlProcessada = selectedImage();
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
		float erroQudraticoMedio = erroQuadratico/totalElementos;
		System.err.println("\nErro quadratico médio pelo numero de elementos:"+Math.sqrt(erroQuadratico));
		System.err.println("\nAcertos: "+acertos);
		
		results = "Caminho da imagem:"+urlProcessada+"\nErro quadrático: "+erroQuadratico+"\nErro quadrático médio:"+
		erroQudraticoMedio+"\nTotal de acertos: "+acertos;
		
		salvaArqTextResultadosTeste(results,urlProcessada);
	
}	*/

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





public static String selectedImage() {

    JFileChooser fileChooser = new JFileChooser();
    String caminho;
//    File diretorio  = selectedDiretorio(url);
//    File diretorio = new File( url);
//    if (directory != null) {
//        fileChooser.setCurrentDirectory(diretorio);
//    }
  
//    FileFilter filter = new FileNameExtensionFilter("Arquivos suportados", "jpg ");
//    fileChooser.setFileFilter(filter);
    
    
    
    
    fileChooser.setDialogTitle("Selecione uma imagem");

//    fileChooser.addChoosableFileFilter(filter);
    fileChooser.showOpenDialog(new JFrame(""));
    fileChooser.setVisible(true);

//    caminho = new File(fileChooser.getCurrentDirectory().getAbsolutePath());
    caminho = fileChooser.getSelectedFile().getAbsolutePath();
    
    System.err.println("\nCaminho da imagem: "+caminho);
    return caminho;
//    float[][] matrix = ReadTextFile(fileChooser.getSelectedFile().getAbsolutePath());
//  

}

public static File selectedDiretorio(String url) {
//
//	JFileChooser fc = new JFileChooser();
//    // restringe a amostra a diretorios apenas
//    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//    int res = fc.showOpenDialog(null);
//    if(res == JFileChooser.APPROVE_OPTION){
//        File diretorio = fc.getSelectedFile();
//        JOptionPane.showMessageDialog(null, "Voce escolheu o diretório: " + diretorio.getName());
//    }
//    else
//        JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio.");
//}
//	
	
//    JFileChooser fileChooser = new JFileChooser();
//    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    File diretorio;
    
    
// FileFilter filter = new FileNameExtensionFilter("Arquivos suportados", "jpg ");
//    fileChooser.setFileFilter(filter);
//  fileChooser.setDialogTitle("Selecione um Diretorio");
//
//  fileChooser.addChoosableFileFilter(filter);
//    fileChooser.showOpenDialog(new JFrame(""));
//    fileChooser.setVisible(true);
//
//    diretorio = new File( fileChooser.getCurrentDirectory().getAbsolutePath());
    System.err.println(url);
    diretorio = new File( url);
    System.out.println(diretorio.getName());
    return diretorio;
//    System.err.println("\nCaminho da imagem: "+caminho);
    //return diretorio;
//    float[][] matrix = ReadTextFile(fileChooser.getSelectedFile().getAbsolutePath());
//  

}

}


