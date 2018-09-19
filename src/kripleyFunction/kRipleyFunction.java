package kripleyFunction;

import featuresExtraction.Statistical;

public class kRipleyFunction {


public static float[][] remocaoDeRuidoBSD(float[][] matriz, int raio, float threshold,float taxaDeAnalise){
	
	float[][] MatrizAux = copiaMatriz(matriz);
	float[][] matrizK = geraMatrizKripley(MatrizAux, raio,taxaDeAnalise);
	System.out.println("\nMatriz de K  Abaixo");
//	imprimeMatriz(matrizK);
	float[][] matrizDePixels= copiaMatriz(matriz);
	//Verificar se o valor que está na matriz de K é inferior ao threshold e aplica a moda a essa vizinhança
	for(int i = 0; i<matrizK.length;i++) {
		for(int j = 0; j<matrizK[0].length;j++) {
			if(matrizK[i][j]<threshold){
//			System.err.println(count++);
//				System.out.println("valor de m[i][j]: "+matriz[i][j]+"\n limiar : "+threshold);
//				System.out.println("\nMatriz auxiliar na iteração ["+i+"]["+j+"]");
				matrizDePixels[i][j] = retornaModaDoElemento(matriz, raio, i, j);
//				System.out.println("Moda do elemento: "+matrizDePixels[i][j]);
			}
		}
	}
//	System.out.println("\nMatriz Final de pixels Abaixo");
//	imprimeMatriz(matrizDePixels);
	return matrizDePixels;
}



public static float retornaModaDoElemento(float[][] matriz, int raio, int x, int y) {
	int m = matriz.length;
	int n = matriz[0].length;
	int tamanhoMascara = 2*raio+1;
	float modaDaMascara ;
	float[][] matrizAuxiliar = matriz;
	float[][] mascara = iniciaMatriz(tamanhoMascara,tamanhoMascara);
	int iLinha = 0, jColuna =0;
	
	for(int i = x-raio; i<=x+raio;i++){
		jColuna = 0;
		for(int j = y-raio; j<=y+raio ;j++){
			if((i>=0 && i<m) && (j>=0 && j<n) ){
					mascara[iLinha][jColuna] = matrizAuxiliar[i][j];	
				}
			jColuna++;
			}
		iLinha++;
	}
	
	iLinha = 0;
	jColuna =0;
//	
//	System.out.println("\nMATRIZ RECEBIDA");
//	imprimeMatriz(matriz);
//	
//	System.out.println("\nMASCARA DO VALOR ["+x+"]["+y+"]");
//	imprimeMatriz(mascara);
//	
	modaDaMascara = Statistical.mode(mascara,true);//Ignorando os zeros
	return modaDaMascara;
}

public static float convolucaoMask(float[][] matriz, int raio, int x, int y,float taxaDeAnalise){
	int m = matriz.length;
	int n = matriz[0].length;
	int tamanhoMascara = 2*raio+1;
	float valorElementoatual =  matriz[x][y];
	int repeticoesTotalElemento,countElementoNaMascara ;
	float[][] matrizAuxiliar = matriz;
	float[][] mascara = iniciaMatriz(tamanhoMascara,tamanhoMascara);
	
	int areaMatrizDeAnalise = (int) (m*taxaDeAnalise);
	float[][] matrizPercentual = matrizPercentual(matriz,areaMatrizDeAnalise);
	
	int iLinha = 0, jColuna =0;
	int numeroElementosMatriz = (int)(m*taxaDeAnalise);
	for(int i = x-raio; i<=x+raio;i++){
		jColuna = 0;
		for(int j = y-raio; j<=y+raio ;j++){
			if((i>=0 && i<m) && (j>=0 && j<n) ){
					mascara[iLinha][jColuna] = matrizAuxiliar[i][j];	
				}
			jColuna++;
			}
		iLinha++;
	}
	
	
	iLinha = 0;
	jColuna =0;
	repeticoesTotalElemento = numeroRepeticaoElemento(matrizPercentual,valorElementoatual);
	countElementoNaMascara = numeroRepeticaoElemento(mascara,valorElementoatual);
	float resultado = (float)(countElementoNaMascara*repeticoesTotalElemento)/numeroElementosMatriz; 
		return resultado; 
}

public static float[][] geraMatrizKripley(float[][] matriz, int raio, float taxaDeAnalise){
	float[][] matrizK = new float[matriz.length][matriz[0].length];
	for(int i = 0; i<matriz.length;i++) {
//		System.out.println("i na funcao de gera matriz "+i);
		for(int j = 0; j<matriz[0].length; j++) {
//			System.out.println("j na funcao de gera matriz "+j);
			matrizK[i][j] = convolucaoMask(matriz, raio, i, j,taxaDeAnalise);
		}
	}
	
	return matrizK;
}


public static float[][] matrizPercentual(float[][] matrizDePixels, float taxaDeAnalise){
	int analise = (int) ( matrizDePixels.length * taxaDeAnalise);
	int iLinha = 0, jColuna =0;
	float[][] matrizPercentual = new float[analise][analise];
	for(int i = analise; i<=analise;i++){
		jColuna = 0;
		for(int j = analise; j<=analise ;j++){
			if((i>=0 && i<matrizDePixels.length) && (j>=0 && j<matrizDePixels[0].length) ){
				matrizPercentual[iLinha][jColuna] = matrizDePixels[i][j];	
				}
			jColuna++;
			}
		iLinha++;
	}
	
	
	return null;
}


public static float[][] copiaMatriz(float[][] matriz) {
	float [][] matrizCopia = new float[matriz.length][matriz[0].length];
	for (int i = 0; i<matriz.length;i++) {
		for(int j = 0; j<matriz[0].length;j++) {
			matrizCopia[i][j] = matriz[i][j]; 
		}
	}
	return matrizCopia;
}

private static float[][] iniciaMatriz(int total_X, int total_Y){
	float[][] matrizFinal = new float [total_X][total_Y];
	for(int i = 0; i<matrizFinal.length;i++){
		for (int j = 0;j<matrizFinal[0].length ;j++ ) {
			matrizFinal[i][j] = 0;
					}
	}
return matrizFinal;
}

private static float[][] iniciaMatrizValorNegativo(int total_X, int total_Y){
	float[][] matrizFinal = new float [total_X][total_Y];
	for(int i = 0; i<matrizFinal.length;i++){
		for (int j = 0;j<matrizFinal[0].length ;j++ ) {
			matrizFinal[i][j] = -1;
					}
	}
return matrizFinal;
}

private static int numeroRepeticaoElemento(float[][]matriz,float elemento){
	int count = 0;
	for(int i = 0;i<matriz.length;i++){
		for(int j = 0; j<matriz[0].length; j++){
			if(matriz[i][j] == elemento){
				count++;
			}
		} 
	}

	return count;
}


//private static int numeroRepeticaoElementoNaMascara(float[][]mascara,float elemento){
//	int count = 0;
//	for(int i = 0;i<mascara.length;i++){
//		for(int j = 0; j<mascara[0].length; j++){
//			if(mascara[i][j] == elemento){
//				count++;
//			}
//		} 
//	}
//
//	return count;
//}


public static boolean checkBoudingBox(int x, int y, int width, int heigth){
	
	if( x >= 0  && x < heigth  && y >= 0 && y<width ){
		return true;
	}else{
		return false;
	}

}

public static boolean gamaCheck(int i,int j, int raio){
	int linha = i;
	int coluna = j;
	if((linha-coluna)<=raio){
		return true;
	}else{
		return false;
	}
}

public static void imprimeMatriz(float[][] matrix) {
	for(int i = 0; i< matrix.length; i++) {
		
		for(int j = 0; j< matrix[0].length; j++) {
			if(matrix[i][j] == 0) {
				System.out.print(" | "+matrix[i][j]+" ");
			}else {
				System.out.print(" | "+matrix[i][j]);
			}
		} 
		
		System.out.println(" |");
	}
}

}