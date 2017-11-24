package algorithm;

/**
 * Created by rajin on 11/24/2017.
 * 418. Sentence Screen Fitting
 */
public class SentenceScreenFitting {
    public static void main(String[] args){
        String[] sentence = new String[]{"a","c","d"};
        int rows = 8;
        int cols = 7;
        System.out.println(new SentenceScreenFitting().wordsTyping(sentence,rows,cols));
    }
    public int wordsTyping(String[] sentence, int rows, int cols) {
        if(rows==0 || cols==0)
        {
            return 0;
        }
        if(sentence ==null || sentence.length ==0)
        {
            return 0;
        }

        int[] lengths= new int[sentence.length];
        int total =0;
        for(int i=0;i<sentence.length;i++)
        {
            if(sentence[i].length()> cols){
                return 0;
            }
            else
            {
                lengths[i] = sentence[i].length();
                total+=lengths[i];
            }
        }

        total+=lengths.length-1; // with dashes
        int r=0;
        int count =0;
        int remaining = cols;
        int index=0;
        int runningSum =0;
        while(r< rows)
        {
            if(index ==0 && remaining>= total){ // case -1 enough space to fit in the current row
                if(remaining/ total == 1) //enough place for fitting once
                {
                    count++;
                    remaining= remaining% (total);
                    remaining--;
                }
                else // enough place for fitting more than 1
                {
                    int times = remaining/ (total+1);
                    count+= times;
                    remaining= remaining% (total+1);
                }



                continue;
            }
            else if(total-runningSum<= remaining) // rest of the partial fitting possible
            {
                remaining= remaining - (total-runningSum)-1;
                index = lengths.length-1;
            }
            else if(lengths[index] <= remaining){ // partial fitting
                remaining= remaining - lengths[index]-1;
                runningSum+= lengths[index];
                runningSum++;
            }
            else //fitting not possible, next row
            {
                remaining = cols;
                r++;
                continue;
            }
            index++;
            if(index == lengths.length){ // fitting done once
                count++;
                index=0;
                runningSum=0;
            }
        }
        if(index == lengths.length) { // check whether last one was a fit
            count++;
        }
        return count;
    }
}
