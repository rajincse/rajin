package algorithm;

public class ZigZagConversion {
	public static void main(String []args)
	{
		ZigZagConversion obj = new ZigZagConversion();
		String input ="PAYPALISHIRING";		
		String output = obj.convert(input, 3);
		System.out.println(input+"=>"+output);
	}
	public String convert(String s, int numRows) {
		
		StringBuilder[] lines = new StringBuilder[numRows];
		for(int i=0;i<lines.length;i++)
		{
			lines[i] =new StringBuilder();
		}
		char[] charArray = s.toCharArray();
		int current=0;
		int upperBoundary=0;
		int lowerBoundary = numRows-1;
		int direction=1; // 1 = down, -1 = up
		for(int i=0;i<charArray.length;i++)
		{
			lines[current].append(charArray[i]);
			if(current == upperBoundary && current== lowerBoundary)
			{
				direction =0;
			}
			else if(current == upperBoundary)
			{
				direction=1;	//down			
			}
			else if(current == lowerBoundary)
			{
				direction= -1; // up
			}
			current+=direction;
			
		}
		StringBuilder zigzag = new StringBuilder();
		for(StringBuilder line: lines)
		{
			zigzag.append(line.toString());
		}
        return zigzag.toString();
    }
}
