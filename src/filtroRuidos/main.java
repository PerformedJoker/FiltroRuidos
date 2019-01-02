


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtroRuidos;

import java.io.File;

import imageController.ImageController;
import kripleyFunction.kRipleyFunction;

/**
 *
 * @author alexsandrosaraiva
 */
public class main {

    /**
     * @param args the command line arguments
     */
	
	
    public static void main(String[] args) {
        // TODO code application logic here
//        System.out.println("Primeiro branch nesse projeto");
//        System.out.println("tentando segundo commit");
        float[][] matrizTeste = new float[9][5];
        matrizTeste[0][0] =  10;
        matrizTeste[0][1] =  20;
        matrizTeste[0][2] =  30;
        matrizTeste[0][3] =  30;
        matrizTeste[0][4] =  30;
        matrizTeste[1][0] =  10;
        matrizTeste[1][1] =  0;
        matrizTeste[1][2] =  30;
        matrizTeste[1][3] =  10;
        matrizTeste[1][4] =  30;
        
        matrizTeste[2][0] =  10;
        matrizTeste[2][1] =  20;
        matrizTeste[2][2] =  30;
        matrizTeste[2][3] =  50;
        matrizTeste[2][4] =  30;
        
        matrizTeste[3][0] =  10;
        matrizTeste[3][1] =  20;
        matrizTeste[3][2] =  30;
        matrizTeste[3][3] =  10;
        matrizTeste[3][4] =  30;
        
        matrizTeste[4][0] =  10;
        matrizTeste[4][1] =  20;
        matrizTeste[4][2] =  50;
        matrizTeste[4][3] =  10;
        matrizTeste[4][4] =  30;
        
        matrizTeste[5][0] =  30;
        matrizTeste[5][1] =  30;
        matrizTeste[5][2] =  30;
        matrizTeste[5][3] =  10;
        matrizTeste[5][4] =  10;
        
        matrizTeste[6][0] =  40;
        matrizTeste[6][1] =  40;
        matrizTeste[6][2] =  30;
        matrizTeste[6][3] =  10;
        matrizTeste[6][4] =  30;
        
        matrizTeste[7][0] =  40;
        matrizTeste[7][1] =  40;
        matrizTeste[7][2] =  30;
        matrizTeste[7][3] =  50;
        matrizTeste[7][4] =  30;
        
        matrizTeste[8][0] =  30;
        matrizTeste[8][1] =  30;
        matrizTeste[8][2] =  30;
        matrizTeste[8][3] =  10;
        matrizTeste[8][4] =  30;
        
//        kRipleyFunction.imprimeMatriz(matrizTeste);
//        kRipleyFunction.imprimeMatriz(kRipleyFunction.geraMatrizKripley(matrizTeste,1));
        
//        System.out.println("matriz de Valores");
//        kRipleyFunction.imprimeMatriz(kRipleyFunction.matrizDeValoresInteiros(matrizTeste, 1, 0.5F));
//       kRipleyFunction.remocaoDeRuidoBSD(matrizTeste, 1, 0.6f,5);
//        System.err.println("\n final: ");
        
//        long tempoInicial = System.currentTimeMillis();
//        ImageController.baseImagens();
        ImageController.exibeImagem();
//        ImageController.selectedImage();
         
       // diretorio1 = ImageController.selectedDiretorio();
		//diretorio2 = ImageController.selectedDiretorio();
       
//        	ImageController.calculaErroQuadratico();
       
       
//      long tempoFinal = System.currentTimeMillis();
//      System.out.printf("\nTempo total de Execucao: %.2f ", ((tempoFinal - tempoInicial) / 1000d)/60);
        
        		}
    
}
