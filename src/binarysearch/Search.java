package binarysearch;

import java.util.Random;

public abstract class Search{
	
	protected String name;
	public Search(String name_arg) {
		name = name_arg;
	}
	public abstract int SearchFor(int[] array, int searchfor);
	public void PrintSearch(int[] array, int searchfor) {
		int index = SearchFor(array, searchfor);
        if(index != -1)
            System.out.println(searchfor + " was found at index " + index);
        else
            System.out.println(searchfor + " was not found in the array");
	}
	public void SpeedTest(int trials){
		System.out.println("Please wait....");
		int arrlen = 10; 
		Metrics[] mar = new Metrics[7];
        for(int k = 0; k < 7; k++){
            int[] myarray = new int[arrlen];
            for(int i = 0; i < arrlen; i++)
                myarray[i] = i;
            Random r = new Random();
            
            long timeStarted = System.nanoTime();
           
            for(int i = 0; i < trials; i++){
                SearchFor(myarray, r.nextInt(arrlen));
            }
            timeStarted = (System.nanoTime() - timeStarted) / trials;
            
            mar[k] = new Metrics(arrlen, timeStarted/1000.0);
            
            //System.out.printf("For an array of size %d,\t%s finished in %.3f micro seconds\n", arrlen, this.name, timeStarted/1000.0);
            arrlen*=10;
        }
        System.out.printf("Out of %d trials, here are the average times of execution for %s\n", trials, name);
        System.out.println("Elements\tTime in micro seconds");
        
        for(int n = 0; n < 7; n++)
        	mar[n].Display();
	}
	
	protected class Metrics{
		private long elements;
		private double timelapse;
		public void Display() {
			String e = String.valueOf(elements);
			String t = String.format("%.3f", timelapse);
			while(e.length()+t.length() < 30)
				e+= ' ';
			e+=timelapse;
			System.out.println(e);
		}
		public Metrics(long el, double t) {
			elements = el;
			timelapse = t;
		}
	}
	
}




class LinearSearch extends Search{
	
	LinearSearch(){
		super("Linear Search");
	}
	
	public int SearchFor(int[] array, int searchfor){
        for(int i = 0; i < array.length; i++){
            if(array[i] == searchfor){
                return i;
            }
        }
        return -1;
    }
	
}


class BinarySearch extends Search{
   
    BinarySearch() {
    	super("Binary Search");
    }
    
    public int SearchFor(int[] array, int searchfor){
        int mid = (array.length / 2);
        int index = mid;
   
       
        for(int i = 0; true; i++){
            int adjust = mid;
            for(int j = 0; j < i; j++)
                adjust/=2;
            adjust = (adjust/2 + adjust%2);
            if(array[index] == searchfor){
                return index;
            }
            else if(adjust <= 0){
                return -1;
            }
             
            else if(array[index] < searchfor)
                index += adjust;
            else if(array[index] > searchfor)
                index -= adjust;
            if(index < 0 || index >= array.length){
                return -1;
            }
        }
       
    }
}