package kripleyFunction;

public class kRipleyFunction {




public static boolean checkBoudingBox(int x, int y, int width, int heigth){
	
	if( x >= 0  && x < heigth  && y >= 0 && y<width ){
		return true;
	}else{
		return false;
	}

}

public static int gamaCheck(int i,int j, int raio){
	if((i-j)<=raio){
		return 1;
	}else{
		return 0;
	}
}

public static int[][] convolucaoMask(int[][] matriz, int raio, int x, int y){
	int m = matriz.length;
	int n = matriz[0].length;
	int min_X = x-raio;
	int max_X = raio+x;
	int min_Y = y-raio;
	int max_Y = raio+y;	
	int total_X = min_X + max_X;
	int total_Y = min_Y + max_Y;
	int valida = 0;
	int[][] auxBuffer = new int[total_X][total_Y];
	for(int i = min_X; i<max_X;i++){
		for(int j = min_Y; j< max_Y ;j++){
//			valida = checkBoudingBox(i,j,m, n);
			if(valida==1){
//				auxBuffer[i][j] = 
			}
		}
	}


	return auxBuffer;
}







}
