package kripleyFunction;

import featuresExtraction.Statistical;

public class kRipleyFunction {


public static float[][] remocaoDeRuidoBSD(float[][] matriz, int raio, int threshold ){
	
	float[][] matrizK = geraMatrizKripley(matriz, raio);
	
	for(int i = 0; i<matrizK.length;i++) {
		for(int j = 0; j<matrizK[0].length;j++) {
			if(matrizK[i][j]<threshold){
				matrizK[i][j] = convolucaoMask(matriz, raio, i, j, false);
			}
		}
	}
	
	return matrizK;
}




public static float convolucaoMask(float[][] matriz, int raio, int x, int y, boolean ripleyOuModa ){
	int m = matriz.length;
	int n = matriz[0].length;
	int tamanhoMascara = 2*raio+1;
	
	
	int min_x = x-raio;
	int min_y =y-raio;
	
	System.out.println("O valor min em x é: "+ min_x);
	System.out.println("O valor min em y é: "+ min_y);
	
	float valorElementoatual =  matriz[x][y];
	float modaDaMascara;
	int repeticoesTotalElemento,countElementoNaMascara ;
	float[][] mascara = iniciaMatriz(tamanhoMascara,tamanhoMascara);
	
	int numeroElementosMatriz = m*n;
//	float[][] bufferModa = new float[m][n];

	for(int i = min_x; i<tamanhoMascara;i++){
		System.out.println("valor de i"+i );
		for(int j = min_y; j< tamanhoMascara ;j++){
			System.out.println("valor de j"+j );
			if(checkBoudingBox(i,j,m,n)){
//				System.out.println("entrou checa fronteira quando o i e j eram: "+i+j);
				if(gamaCheck(i,j,raio)){
					System.out.println("entrou quando o i e j eram: "+i+j);
					mascara[i][j] = matriz[i][j];	
				}
			}
		}
	}
	imprimeMatriz(mascara);
	
	repeticoesTotalElemento = numeroRepeticaoElemento(matriz,valorElementoatual);
	countElementoNaMascara = numeroRepeticaoElemento(mascara,valorElementoatual);
	modaDaMascara = Statistical.mode(mascara);
	float resultado = (countElementoNaMascara*repeticoesTotalElemento)/numeroElementosMatriz; 

	if(ripleyOuModa){
		return resultado; 
	}else{
		return modaDaMascara;
	}
	
}

public static float[][] geraMatrizKripley(float[][] matriz, int raio){
	float[][] matrizK = new float[matriz.length][matriz[0].length];
	for(int i = 0; i<matriz.length;i++) {
		for(int j = 0; j<matriz[0].length; j++) {
			matrizK[i][j] = convolucaoMask(matriz, raio, i, j, true);
		}
	}
	
	return matrizK;
}

private static float[][] iniciaMatriz(int total_X, int total_Y){
	float[][] matriz = new float [total_X][total_Y];
	for(int i = 0; i<matriz.length;i++){
		for (int j = 0;j<matriz[0].length ;j++ ) {
			matriz[i][j] = -1;
			
		}
	}
return matriz;
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

public static boolean checkBoudingBox(int x, int y, int width, int heigth){
	
	if( x >= 0  && x < heigth  && y >= 0 && y<width ){
		return true;
	}else{
		return false;
	}

}

public static boolean gamaCheck(int i,int j, int raio){
	if((i-j)<=raio){
		return true;
	}else{
		return false;
	}
}

public static void imprimeMatriz(float[][] matriz) {
	for(int i = 0; i< matriz.length; i++) {
		
		for(int j = 0; j< matriz[0].length; j++) {
			if(matriz[i][j] == 0) {
				System.out.print(" | "+matriz[i][j]+" ");
			}else {
				System.out.print(" | "+matriz[i][j]);
			}
		} 
		
		System.out.println(" |");
	}
}

}