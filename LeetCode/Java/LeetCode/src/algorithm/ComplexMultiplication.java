package algorithm;

class ComplexNumber{
	int real;
	int imaginary;
	public ComplexNumber(int real, int imaginary) {
		super();
		this.real = real;
		this.imaginary = imaginary;
		
	}
	@Override
	public String toString() {
		return real+"+"+imaginary+"i";
	}
	public ComplexNumber multiply(ComplexNumber other){
		int real = (this.real * other.real) - (this.imaginary * other.imaginary);
		
		int imaginary = (this.real * other.imaginary)+(other.real * this.imaginary);
		
		return new ComplexNumber(real, imaginary);
	}
	
	public static ComplexNumber parse(String a){
		int plusIndex = a.indexOf('+');
		
		
		int real = Integer.parseInt(a.substring(0,plusIndex));
		int imaginary = Integer.parseInt(a.substring(plusIndex+1, a.length()-1));
		
		ComplexNumber complex = new ComplexNumber(real, imaginary);
		return complex;
	}
}
public class ComplexMultiplication {

	public static void main(String[] args){
		
		String a  ="1+0i";
		String b ="1+0i";
		ComplexMultiplication m = new ComplexMultiplication();
		String result = m.complexNumberMultiply(a,b);
		
		System.out.println(" "+a+" X "+b+"=>"+result);
	}
	public String complexNumberMultiply(String a, String b) {
        
		ComplexNumber com1 = ComplexNumber.parse(a);
		ComplexNumber com2 = ComplexNumber.parse(b);
		
		
		return com1.multiply(com2).toString();
    }
}
