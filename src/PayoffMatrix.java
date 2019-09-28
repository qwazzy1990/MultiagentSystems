import java.util.*;

public class PayoffMatrix
{

    int[][][] matrix;
    int numRows;
    int numCols;

    PayoffMatrix(int m, int n)
    {
        this.matrix = new int[m][n][2];
        this.numRows = m;
        this.numCols = n;
    }

    public void parseMatrix(ArrayList<String> a)
    {
        int currRow = -1;
        int currCol = 0;
       
        for(int i = 2; i < a.size(); i++)
        {   
            if(i % 2 == 0)
            {
                currRow++;
            }
            String s = a.get(i);
            for(int j = 0; j < s.length(); j++)
            {
                //if i % 2 is 0 then we are adding the values for ag1
                if(i % 2 == 0)
                {
                    if(Character.isDigit(s.charAt(j)))
                    {
                        System.out.println(currRow);

                        System.out.println(currCol);
                        this.matrix[currRow][currCol][0]= Character.getNumericValue(s.charAt(j));
                        currCol++;
                    }
                }
            }
            currCol = 0;
            for(int j = 0; j < s.length(); j++)
            {
                //if i % 2 is 0 then we are adding the values for ag1
                if(i % 2 == 1)
                {
                    if(Character.isDigit(s.charAt(j)))
                    {
                        this.matrix[currRow][currCol][1] = Character.getNumericValue(s.charAt(j));
                        currCol++;
                    }
                }
            }
            currCol = 0;
        
        }
    }

    public String toString()
    {
        

        String s = new String();
        
        for(int i = 0; i < this.numRows; i++)
        {
            for(int j = 0; j < this.numCols; j++){
                s+="( ";
                s+= Integer.toString(this.matrix[i][j][0]);
                s+= ",";
                s+= Integer.toString(this.matrix[i][j][1]);
                s+= " )";
                s+= " ";
            }
            s+= "\n";
        }
        System.out.println(s);

        return s;
    }
}