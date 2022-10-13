package ru.spbu.apcyb.svp.tasks;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Задание 1.
 */
public class Task1 {
	
	static int count =0;
	
	public static void Print(int arr[], int chast[]) {
		String number = "";
		for (int i=arr.length-1; i>=0;i--)
		{
			for (int j=0; j<chast[i]; j++)
			{
				number=number+arr[i]+" ";
			}
		}
		System.out.println(number);
	}
	
	public static void Inv1(int summ, int[] arr, int[] arr1, int[] chast, int j, int N)
	{
		
		int s=0;
		for (int i=chast[j]; i>=0; i--)
		{
			arr1[j]=i;
		
		if (j==0)
		{
			for (int k=0; k<N; k++)
			{
				s+=arr[k]*arr1[k];
			}
			if (s==summ)
			{
				Print(arr,arr1);
				count=count+1;
				//System.out.println("count i = " +count);
				
			}
			s=0;
		}
		else{
			Inv1(summ, arr, arr1, chast, j-1, N);
		}
		}
	}

  public static void main(String[] args) {
	  ArrayList<String> list = new ArrayList<String>();
	  // вводим сумму и купюры
	  //ArrayList<Integer> list = new ArrayList<Integer>();
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      //for (int i = 0; i < 10; i++) 
      {
    	  try {
          list.add(0, br.readLine()); // ввели числа
    	  } catch (IOException e) { // поймали ошибочку
              e.printStackTrace(); 
          }
      }
      String[] arr = new String[list.size()];
      String space = " ";
      String number = "";
      int k=0;
      int i=0;
      //System.out.println(list);
      // разделяем входные данные 
      arr[i] = list.get(i);
    	  int[] arr1 = new int[arr[i].length()];
    	  for (int j = 0; j < arr[i].length(); j++) {
    		  if (arr[i].charAt(j) != space.charAt(0)) {
    			  if ((arr[i].charAt(j)=='0') || (arr[i].charAt(j)=='1') || (arr[i].charAt(j)=='2')|| (arr[i].charAt(j)=='3')|| (arr[i].charAt(j)=='4')|| (arr[i].charAt(j)=='5')|| (arr[i].charAt(j)=='6')|| (arr[i].charAt(j)=='7')|| (arr[i].charAt(j)=='8')|| (arr[i].charAt(j)=='9'))
    				  number = number + arr[i].charAt(j);
    			  else {
    				  System.out.println("ошибка");
    				  System.exit(0);
    			  }
    		  }
    		  else {
    			  arr1[k]=Integer.parseInt(String.valueOf(number));
      			  //System.out.println(arr1[k]);
      			  k=k+1;
      			  number="";
      			  }
    		  }
    	  arr1[k]=Integer.parseInt(String.valueOf(number));
    	  if (arr1[0]==0)
    	  {
    		  System.out.println("ошибка");
			  System.exit(0);
    	  }
    	  //System.out.println("this "+arr1[0]);
    	  int[] arr2 = new int[k];
    	  for (int j=0; j<=k-1;j++) {
    		  arr2[j]=arr1[j+1];
    	  }
    	  for(int t=0; t<arr2.length-1; t++) {
    	         for (int j=t+1; j<arr2.length; j++) {
    	            if(arr2[t] == arr2[j]) {
    	               //System.out.println(j);
    	            	arr2[j]=0;
    	            }
    	         }
    	      }
    	  //for (int t=0; t<arr2.length; t++)
    	  //{
    	//	  if (arr2[t]==0)
    		//  {
    		//	  k=k-1;
    		//	  if (t < arr2.length-1)
    		//	  {
    		//		  for (int j=t; j<arr2.length-1;j++)
    		//		  {
    		//			  arr2[j]=arr2[j+1];
    		//		  
    		//		  }
    		//	  }
    		//  }
    	  //}
    	  
      Integer summ = arr2[i];
      Integer summ1;
      
      //System.out.println("15 = "+arr2[0]);
      
      Arrays.sort(arr2);
      
      for (int t=0; t<arr2.length; t++)
	  {
		  if (arr2[t]==0)
		  {
			  k=k-1;
			  if (t < k-1)
			  {
				  for (int j=t; j< arr2.length-1;j++)
				  {
					  arr2[j]=arr2[j+1];
				  
				  }
			  }
		  }
	  }
      if (k==0)
	  {
		  System.out.println("ошибка");
		  System.exit(0);
	  }
      
      //System.out.println(arr2[k-1]);
      System.out.println(arr2[0]);
      System.out.println("k = "+ k);
      
      int[] K = new int[k];
      int[] chast = new int[k];
      for (int j = 0; j < k; j++)
      {
    	  chast[j]=arr1[0]/arr2[j];
      }
      Inv1(arr1[0], arr2, K, chast, k-1, k);
      System.out.println("count = "+count);
      
      
    }
      
}
