package com.evolvingstuff;

public class util 
{
	public static int argmax(double[] vec) {
		int result = -1;
		double max = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < vec.length; i++) {
			if (vec[i] > max){
				max = vec[i];
				result = i;
			}
		}
		return result;
	}
	
	public static int argmin(double[] vec) {
		int result = -1;
		double min = Double.POSITIVE_INFINITY;
		for (int i = 0; i < vec.length; i++) {
			if (vec[i] < min){
				min = vec[i];
				result = i;
			}
		}
		return result;
	}
	
	public static double getSquaredError(double[] vector1, double[] vector2) {
        double squaredError = 0;
        for (int i = 0; i < vector1.length; i++) {
            squaredError += (vector1[i] - vector2[i]) * (vector1[i] - vector2[i]);
        }
        return squaredError;
    }
	
	public static double[] sin(double... v){
		double[] r = new double[v.length];
		for (int i=0; i<v.length; i++)
			r[i] = Math.sin(v[i]);
		return r;
	}
	
	public static double[] seq(double ini, int n, double inc){
		double[] r = new double[n];
		for (int i=0; i<n; i++)
			r[i] = ini+=inc;
		return r;
	}
	
	public static String join(double[] a){
	    String r=a[0]+"";
	    for (int i=1; i<a.length; i++)
	        r+="\t"+a[i];
	    return r;
	}
}
