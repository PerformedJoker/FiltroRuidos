


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtroRuidos;

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
        System.out.println("Primeiro branch nesse projeto");
        System.out.println("tentando segundo commit");
        float[][] matrizTeste = new float[4][4];
        matrizTeste[0][0] = 10 ;
        matrizTeste[0][1] =  20;
        matrizTeste[0][2] =  30;
        matrizTeste[0][3] =  30;
        matrizTeste[1][0] =  10;
        matrizTeste[1][1] =  0;
        matrizTeste[1][2] =  30;
        matrizTeste[1][3] =  10;
        matrizTeste[2][0] =  10;
        matrizTeste[2][1] =  20;
        matrizTeste[2][2] =  30;
        matrizTeste[2][3] =  50;
        matrizTeste[3][0] =  10;
        matrizTeste[3][1] =  20;
        matrizTeste[3][2] =  30;
        matrizTeste[3][3] =  10;
        
//        kRipleyFunction.imprimeMatriz(matrizTeste);
//        kRipleyFunction.imprimeMatriz(kRipleyFunction.geraMatrizKripley(matrizTeste,1));
        
//        System.out.println("matriz de Valores");
//        kRipleyFunction.imprimeMatriz(kRipleyFunction.matrizDeValoresInteiros(matrizTeste, 1, 0.5F));
        kRipleyFunction.remocaoDeRuidoBSD(matrizTeste, 1, 0.6f);
        System.err.println("\n final");
        		}
    
}
