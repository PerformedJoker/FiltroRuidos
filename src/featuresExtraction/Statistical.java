/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package featuresExtraction;

/**
 *
 * @author Alexsandro
 */
public class Statistical {

    /**
     * This function averaging of the matrix making the sum of values ​​of all
     * elements and dividing by the total number of elements.
     *
     * @param float[][] pixels
     * @return A float value with the mean of th matrix
     */
    public static float mean(float[][] pixels) {
        float bufferPlus = 0;
        float result;
        int rows = pixels.length;
        int columns = pixels[0].length;
        int numberOfElements = rows * columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                bufferPlus += pixels[i][j];
            }
        }
        result = bufferPlus / numberOfElements;
        return result;
    }

    /**
     * If this option is enable, the number zero isn't count in the mean
     */
    public static float mean(float[][] pixels, boolean noZeroCounter) {
        float bufferPlus = 0;
        float result;
        int rows = pixels.length;
        int columns = pixels[0].length;
        int numberOfElements = rows * columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (noZeroCounter && pixels[i][j] != 0) {
                    bufferPlus = bufferPlus + pixels[i][j];
                } else {
                    numberOfElements--;
                }
            }
        }
        result = bufferPlus / numberOfElements;
        return result;
    }

    /**
     * Função que utiliza a media, para descobrir uma medida de dispersão que
     * mostra o quão distante cada valor está do valor esperado.
     *
     * @param float[][] pixels
     * @return A float value with the level dispersion of the matrix.
     */
    public static float variance(float[][] pixels) {
        float result;
        float meanOfMatrix = Statistical.mean(pixels);
        float plusResult = 0;
        int rows = pixels.length;
        int columns = pixels[0].length;
        int numberOfElements = (rows * columns) - 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                plusResult += (float) Math.pow((pixels[i][j] - meanOfMatrix), 2);
            }
        }
        result = plusResult / numberOfElements;
        return result;
    }

    /**
     * If this option is enable, the number zero isn't count in the variance
     */
    public static float variance(float[][] pixels, boolean noZeroCounter) {
        float result;
        float meanOfMatrix = Statistical.mean(pixels, true);
        float plusResult = 0;
        int rows = pixels.length;
        int columns = pixels[0].length;
        int numberOfElements = (rows * columns) - 1;

        if (noZeroCounter) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (pixels[i][j] != 0) {
                        plusResult += (float) Math.pow((pixels[i][j] - meanOfMatrix), 2);
                    } else {
                        numberOfElements--;
                    }
                }
            }
        }
        result = plusResult / numberOfElements;
        return result;
    }

    /**
     * O desvio Padrão é utilizado para medir o grau de variabilidade em relação
     * a media.
     */
    public static float standardDeviation(float[][] pixels) {
        float result;
        float variance = Statistical.variance(pixels);
        result = (float) Math.sqrt(variance);
        return result;
    }

    /**
     * If this option is enable, the number zero isn't count in the
     * standardDeviation
     */
    public static float standardDeviation(float[][] pixels, boolean noZeroCounter) {
        float variance = Statistical.variance(pixels, true);
        float result = (float) Math.sqrt(variance);
        return result;
    }

    /**
     * Função que verifica qual o valor mais expressivo ou intenso da matriz.
     */
    public static float maximum(float[][] pixels) {
        float result, buffer = pixels[0][0];
        int rows = pixels.length;
        int columns = pixels[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (buffer < pixels[i][j]) {
                    buffer = pixels[i][j];
                }
            }
        }
        result = buffer;
        return result;
    }

    /**
     * If this option is enable, the number zero isn't count in the maximum
     */
    public static float maximum(float[][] pixels, boolean noZeroCounter) {
        float result, buffer = pixels[0][0];
        int rows = pixels.length;
        int columns = pixels[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((pixels[i][j] != 0 ||pixels[i][j] != -1) && buffer < pixels[i][j]) {
                    buffer = pixels[i][j];
                }
            }
        }
        result = buffer;
        return result;
    }

    /**
     * Função que verifica qual o valor menos expressivo ou intenso da matriz.
     */
    public static float minimum(float[][] pixels) {
        float result, buffer = pixels[0][0];
        int rows = pixels.length;
        int columns = pixels[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (buffer > pixels[i][j]) {
                    buffer = pixels[i][j];
                }
            }
        }
        result = buffer;
        return result;
    }

    /**
     * If this option is enable, the number zero isn't count in the minimum
     */
    public static float minimum(float[][] pixels, boolean noZeroCounter) {
        float result;
        float buffer = pixels[0][0];
        int rows = pixels.length;
        int columns = pixels[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((pixels[i][j] != 0 ||pixels[i][j] != -1)  && buffer > pixels[i][j]) {
                    buffer = pixels[i][j];
                }
            }
        }
        result = buffer;
        return result;
    }

    /**
     * Função que calcula a amplitude da matriz utilizando o maximo e o minimo.
     */
    public static float range(float[][] pixels) {
        float result = Statistical.maximum(pixels) - Statistical.minimum(pixels);
        return result;
    }

    /**
     * If this option is enable, the number zero isn't count in the range
     */
    public static float range(float[][] pixels, boolean noZeroCounter) {
        float result = Statistical.maximum(pixels, true) - Statistical.minimum(pixels, true);
        return result;
    }

    /**
     * Função que calcula o valor médio onde os elementos anteriores a ele são
     * menores e posteriores a ele são maiores
     *
     * @return A value what is the median of the matrix.
     */
    public static float median(float[][] pixels) {
        float result;
        int n = 0;
        float index, prev, next;
        int rows = pixels.length;
        int columns = pixels[0].length;
        int numberOfElements = columns * rows;
        float[] vector = new float[numberOfElements];

        /*Changing the matrix for vector*/
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                vector[n] = pixels[i][j];
                n++;
            }
        }

        /*Ordering the vector*/
        quickSort(vector, 0, vector.length - 1);

        if (numberOfElements % 2 == 1)// Caso o numero de elementos seja Impar
        {
            index = ((int) (numberOfElements / 2));
            result = vector[(int) index];
        } else {   //Caso o numero de elementos seja par é necessário fazer a média dos dois elementos centrais
            next = (vector[numberOfElements / 2]);
            prev = (vector[(numberOfElements / 2) - 1]);
            result = (next + prev) / 2;
        }
        return result;
    }

    /**
     * If this option is enable, the number zero isn't count in the median
     */
    public static float median(float[][] pixels, boolean noZeroCounter) {
        float result;
        int n = 0;
        float index, prev, next;
        int rows = pixels.length;
        int columns = pixels[0].length;
        int numberOfElements = columns * rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (pixels[i][j] == 0) {
                    numberOfElements--;
                }
            }
        }
        float[] vector = new float[numberOfElements];

        /*Changing the matrix for vector*/
        if (noZeroCounter) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (pixels[i][j] != 0) {
                        vector[n] = pixels[i][j];
                        n++;
                    }
                }
            }
        }

        /*Ordering the vector*/
        quickSort(vector, 0, vector.length - 1);
        if (numberOfElements % 2 == 1) // Caso o numero de elementos seja Impar
        {
            index = ((int) (numberOfElements / 2));
            result = vector[(int) index];
        } else {   //Caso o numero de elementos seja par é necessário fazer a média dos dois elementos centrais
            next = (vector[(int) numberOfElements / 2]);
            prev = (vector[(int) (numberOfElements / 2) - 1]);
            result = (next + prev) / 2;
        }
        return result;
    }

    /**
     * Função que calcula qual elemento mais se repete na uma matriz.
     */
    public static float mode(float[][] pixels) {
        int rows = pixels.length;
        int columns = pixels[0].length;
        int result;
        int k;
        int indexOfVector = 0;
        float big = 0;
        int pos = 0;
        float min = Statistical.minimum(pixels);
        float max = Statistical.maximum(pixels);
        int numOfElements = (int) (max - min);
        float[] vectorPixels = new float[rows * columns];
        float[] vector = new float[numOfElements + 1];
        /*This loop turn the matrix in vector*/
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                vectorPixels[indexOfVector] = pixels[i][j];
                indexOfVector++;
            }
        }

        for (int j = 0; j < vectorPixels.length; j++) {
            k = (int) (vectorPixels[j] - min);
            vector[k]++;
        }

        /* This loop make the comparison of index and the number of repetitions and returns the number more repeated */
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] > big) {
                big = vector[i];
                pos = i;
            }
        }
        result = pos;
        return result + min;
    }

    /**
     * If this option is enable, the number zero isn't count in the median
     */
    public static float mode(float[][] pixels, boolean noZeroCounter) {
        int rows = pixels.length;
        int columns = pixels[0].length;
        int result, k;
        int indexOfVector = 0;
        float big = 0;
        int pos = 0;
        float min = Statistical.minimum(pixels, noZeroCounter);
        float max = Statistical.maximum(pixels, noZeroCounter);
        int numOfElements = (int) max;
        float[] vectorPixels = new float[rows * columns];

        /*This loop turn the matrix in vector*/
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                vectorPixels[indexOfVector] = pixels[i][j];
                indexOfVector++;
            }
        }
        float[] vector = new float[numOfElements + 1];
        for (int j = 0; j < vectorPixels.length; j++) {
            k = (int) (vectorPixels[j]);
            vector[k]++;
        }

        /* This loop make the comparison of index and the number of repetitions and returns the number more repeated */
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] > big && i != 0) {
                big = vector[i];
                pos = i;
            }
        }
        result = pos;
        return result;
    }

    //--------Extra Functions for order Vector----------//
    @SuppressWarnings("empty-statement")
    public static int partition(float arr[], int left, int right) {
        int i = left, j = right;
        float tmp;
        float pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }

        return i;
    }

    public static void quickSort(float arr[], int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            quickSort(arr, left, (index - 1));
        }
        if (index < right) {
            quickSort(arr, index, right);
        }
    }
    //-------End extra Functions for order Vector--------//

}
