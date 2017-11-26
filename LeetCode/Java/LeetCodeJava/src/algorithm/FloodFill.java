package algorithm;

/**
 * Created by rajin on 11/25/2017.
 * 733. Flood Fill
 */
public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(sr<0 || sr> image.length || sc<0 || sc> image[0].length)
        {
            return image;
        }
        int previousColor = image[sr][sc];
        if(previousColor==newColor)
        {
            return image;
        }
        image[sr][sc] = newColor;
        //up
        if(sr-1>=0 && image[sr-1][sc]==previousColor)
        {
            image=floodFill(image, sr-1, sc, newColor);
        }

        //down
        if(sr+1<image.length && image[sr+1][sc]==previousColor)
        {
            image=floodFill(image, sr+1, sc, newColor);
        }

        //left
        if(sc-1>=0 && image[sr][sc-1]==previousColor)
        {
            image=floodFill(image, sr, sc-1, newColor);
        }
        //up
        if(sc+1<image[0].length && image[sr][sc+1]==previousColor)
        {
            image=floodFill(image, sr, sc+1, newColor);
        }

        return image;

    }
}
