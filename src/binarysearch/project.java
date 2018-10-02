package binarysearch;

public class project {

	public static void main(String[] args) {
		LinearSearch ls = new LinearSearch();
		BinarySearch bs = new BinarySearch();
    	ls.SpeedTest(1000); 
    	System.out.println("\n\n");
    	bs.SpeedTest(1000); 
    	// call SpeedTest method to keep main clean
	}

}
