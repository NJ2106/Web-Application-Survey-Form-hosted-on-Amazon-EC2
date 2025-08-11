package com.restful.resources;

import java.io.Serializable;

public class StudentService implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
static WinningResult w1=new WinningResult();
public static WinningResult computeMeanSD(String raffle) {
	double sum=0.0;
	double Mean=0.0;
	double m=0.0;
	double n=0.0;
	double t=0.0;
	String[] str=raffle.split(",");
	int[]arr=new int[str.length];
	
	for(int i=0;i<str.length;i++) {
		arr[i]=Integer.parseInt(str[i]);
	}
	
	for(int i=0;i<arr.length;i++) {
		sum+=arr[i];
		Mean=sum/(arr.length);
	}
	 w1.setMean(Mean);
	

for(int i=0;i<arr.length;i++) {
	m=Math.pow((arr[i]-Mean), 2);
	n+=m;
	t=str.length-1;
}
double SD=Math.sqrt(n/t);
w1.setStdev(SD);
return w1;
}
}
