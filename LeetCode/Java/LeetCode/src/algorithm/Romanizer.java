package algorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
/**
 * Created by vinod on 18/7/15.
 */
public class Romanizer {
 
    Integer [] number = new Integer[]{1,4,5,9,10,40,50,90,100,400,500,900,1000};
    String[] roman = new String[]{"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
 
    public int binarySearch(int key) {
 
        int start = 0;
        int end = number.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (key < number[mid]){
                if (mid-1>-1){
                    if ( key>number[mid-1])
                        return mid-1;
                    else{
                        end=mid-1;
                    }
                }
                else{
                    return mid;
                }
            }
            else{
                if (mid+1<=end){
                    if ( key<number[mid+1])
                        return mid;
                    else{
                        start=mid+1;
                    }
                }
                else{
                    return mid-1;
                }
            }
 
        }
        return -1;
    }
 
    public static void main(String[] args) {
        System.out.println(new Romanizer().getRomanValue(999));
    }
 
    public String getRomanValue(int num){
        String romanValue="";
        ArrayList <Integer> decimal = new ArrayList<Integer>(Arrays.asList(number));
        int index;
        while ((index=decimal.indexOf(num))==-1){
            index = binarySearch(num);
            if (index==-1){
                return null;
            }
 
            romanValue=romanValue+roman[index];
            num=num-number[index];
        }
        romanValue=romanValue+roman[index];
        return romanValue;
    }
 
 
}