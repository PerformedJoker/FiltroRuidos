package kripleyFunction;

public class kRipleyFunction {




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



public static int convolucaoMask(int[][] matriz, int raio, int x, int y, boolean ripleyOuModa ){
	int m = matriz.length;
	int n = matriz[0].length;
	int min_X = x-raio;
	int max_X = raio+x;
	int min_Y = y-raio;
	int max_Y = raio+y;	
	int total_X = min_X + max_X;
	int total_Y = min_Y + max_Y;
	int elementoatual =  matriz[x][y];
	int repeticoesTotalElemento, modaDaMascara,countElementoNaMascara ;
	int[][] mascara = iniciaMatriz(total_X,total_Y);

	int numeroElementosMatriz = m*n;
	int[][] bufferModa = new int[m][n];

	for(int i = min_X; i<max_X;i++){
		for(int j = min_Y; j< max_Y ;j++){
			
			if(checkBoudingBox(i,j,m,n)){
				if(gamaCheck(i,j,raio)){
					mascara[i][j] = matriz[i][j];	
				}
			}
		}
	}
	repeticoesTotalElemento = numeroRepeticaoElemento(matriz,elementoatual);
	countElementoNaMascara = numeroRepeticaoElemento(mascara,elementoatual);
	modaDaMascara = moda();
	int resultado = (countElementoNaMascara* repeticoesTotalElemento)/numeroElementosMatriz; 

	if(ripleyOuModa){
		return resultado; 
	}else{
		return modaDaMascara;
	}

	
}



private static int[][] iniciaMatriz(int total_X, int total_Y){
	int[][] matriz = new int [total_X][total_Y];
	for(int i = 0; i<matriz.length;i++){
		for (int j = 0;j<matriz[0].length ;j++ ) {
			matriz[i][j] = -1;
			
		}
	}
return matriz;
}

private static int numeroRepeticaoElemento(int[][]matriz,int elemento){
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


private static int moda(){
	return 1;
}

}