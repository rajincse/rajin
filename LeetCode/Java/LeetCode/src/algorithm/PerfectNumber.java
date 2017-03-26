package algorithm;

public class PerfectNumber {
	
	public static void main(String[] args){
	
		int num =4;
		PerfectNumber n = new PerfectNumber();
		boolean result = n.checkPerfectNumber(num);
		
		System.out.println(" "+num+"=>"+result);
	}
	boolean checkPerfectNumber(int num) {
        
		if(num > 1){
			int sum =1;
			int limit = (num/2)+1;
			String divisors ="1,";
			for(int i=2;i<limit;i++){
				if(num%i ==0){
					sum+=i;
					divisors+=""+i+",";
					int dividend = num/i;
					if(dividend > i ){
						sum+=dividend;
						divisors+=""+dividend+",";
					}
					limit = dividend;
				}
			}
			System.out.println(divisors+" =>"+sum);
			return (sum == num);
		}
		else{
			return false;
		}
		
    }
}
