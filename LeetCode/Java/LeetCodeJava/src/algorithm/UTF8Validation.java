package algorithm;

/**
 * Created by rajin on 11/24/2017.
 * 393. UTF-8 Validation
 */
public class UTF8Validation {
    public static void main(String[] args)
    {
        int[] data = new int[]{250,145,145,145,145};
        System.out.println(new UTF8Validation().validUtf8(data));
    }

    int index=0;
    public boolean validUtf8(int[] data)
    {
        if(data.length ==0)
        {
            return true;
        }

        boolean result = true;

        while (index < data.length && result)
        {
            result = result && isValid(data);
        }

        return result;
    }
    public boolean isValid(int[] data)
    {
        if(index >= data.length)
        {
            return true;
        }
        int leadingOnes = getLeadingOnes(data[index]);
        if(leadingOnes==0)
        {
            index++;
            return true;
        }
        else if(leadingOnes ==1)
        {
            index++;
            return false;
        }
        else if(leadingOnes > 4)
        {
            index++;
            return false;
        }
        else
        {
            int mask = 1<<(7-leadingOnes);
            if((data[index] & mask) !=0)
            {
                return false;
            }
            else
            {
                if(index+leadingOnes > data.length)
                {
                    return false;
                }
                int mask1 = 0x80;
                int mask2 = ~(0x40);
                for(int i=1;i<leadingOnes;i++)
                {
                    if((data[index+i] & mask1) ==0 )
                    {
                        return false;
                    }
                    if((data[index+i] | mask2) != mask2)
                    {
                        return false;
                    }
                }
                index = index+leadingOnes;
                return true;
            }
        }

    }

    public int getLeadingOnes(int data)
    {
        int count=0;
        int mask= 0x80;
        while((data & mask) >0)
        {
            count++;
            mask = mask >>> 1;
        }

        return count;
    }
}
