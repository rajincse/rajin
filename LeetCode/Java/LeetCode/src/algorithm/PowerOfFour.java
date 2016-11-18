package algorithm;

public class PowerOfFour {
	public boolean isPowerOfFour(int num) {
		double exponent = Math.log(num)/ Math.log(4);
		
		double diff = exponent - (int) exponent;
        return diff== 0;
    }
	
	public static void main(String[] args)
	{
		PowerOfFour pf = new PowerOfFour();
		int input =1;
		System.out.println(input+" powerOfFour? :"+pf.isPowerOfFour(input));
	}
}
