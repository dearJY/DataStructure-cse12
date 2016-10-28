import java.util.*;

public class Complexity {

		
	public static int method1(int num){

		int result = 0;
		for (int i=num; i>=0; i--)
		{
			for (int j=0; j<=i; j++) 
				result  = result + 1;
		}
		return result;		// O(n^2)
		
	}
	
    public static int method2(int num){
    	int result = 1;
    	int i, j=100;
    	for (i=0; i<=100; i++) 
    	{
    		while (j>i){               
    			result = result * num;
    			j--;
    	    }
    		j=100;
        }	
    	return result;		//O(1)
	}
    
    public static void method3(int num){
		int[] intArray = new int[num];  
		for (int i=0; i<num-1; i++)
			intArray[i] = i+1;
		
		
		for (int i=0; i<intArray.length; i++) {
			System.out.println(intArray[i]);
		}
		
		for (int i=intArray.length-1; i>0; i--)
			intArray[i]=intArray[i-1];
		
		intArray[0] = -1;	// O(n)
		
	}
	
	public static void main(String[] args) {

		List<String> res = new ArrayList<>();
		for (int i=5000;i<20000;i+=1000){
			res.add(testAllMethods(i));
		}
		System.out.print(res.toString());

	}

	public static String testAllMethods(int num){
		long T1 = 0;
		int T2 = 0;
		int T3 = 0;

		for(int i=0;i<1000;i++) {
			long prev1 = System.nanoTime();
			method1(num);
			long time1 = System.nanoTime() - prev1;
			T1 += time1;
		}

//		for(int i=0;i<1000;i++) {
//			long prev2 = System.nanoTime();
//			method2(num);
//			long time2 = System.nanoTime() - prev2;
//			T2 += time2;
//		}
//
//		for(int i=0;i<1000;i++) {
//			long prev3 = System.currentTimeMillis();
//			method3(num);
//			long time3 = System.currentTimeMillis() - prev3;
//			T3 += time3;
//		}

		String res = "time1: " + T1/1000 + '\n';
//		String res = "time1: " + T1/1000 + "; time2: " + T2/1000 + "; time3: " + T3/1000;
		return res;
	}


}
