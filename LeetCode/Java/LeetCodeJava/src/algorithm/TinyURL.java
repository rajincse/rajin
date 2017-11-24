package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by rajin on 11/22/2017.
 * 535. Encode and Decode TinyURL
 */

class Codec {


    Random random = new Random();
    HashMap<Integer, String> database = new HashMap<Integer, String>();
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {

        int id = Math.abs(random.nextInt());
        database.put(id, longUrl);
        String url = idToSixCharURL(id);
        return url;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int id = sixCharURLtoID(shortUrl);

        return database.getOrDefault(id,"Error");

    }

    private String idToSixCharURL(int id){
        if(id ==0)
        {
            return "0";
        }
        String map = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int radix = map.length();
        int num = id;

        StringBuilder sb = new StringBuilder();
        while(num !=0)
        {
            int val = num%radix;
            sb.append(map.charAt(val));
            num = num / radix;
        }
        sb = sb.reverse();
        return sb.toString();
    }

    private int sixCharURLtoID(String url){
        int id =0;
        int radix = 62;
        char []charArr = url.toCharArray();
        for(char ch: charArr){
            int val =0;
            if(ch >='0' && ch <='9')
            {
                val = ch-'0';
            }
            else if(ch >='a' && ch <='z')
            {
                val = ch-'a'+10;
            }
            else
            {
                val = ch -'A'+36;
            }
            id *= radix;
            id+= val;
        }
        return id;
    }
}
public class TinyURL {
    public static void main(String[] args)
    {
        String host ="http://cis.fiu.edu/";
        String []urls = new String[]{
                "~salam011",
                "~nferd001",
                "~gshaw"
        };
        String[] shortUrls = new String[urls.length];
        Codec c= new Codec();
        for(int i=0;i<shortUrls.length;i++)
        {
            shortUrls[i] = c.encode(urls[i]);
        }

        for(int i=0;i<shortUrls.length;i++)
        {
            System.out.println(host+urls[i]+"=>"+host+shortUrls[i]+"=>"+host+c.decode(shortUrls[i]));
        }
    }
}
