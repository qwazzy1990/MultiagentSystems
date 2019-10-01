

import java.util.*;




public class Agent
{


    int numRows;
    int numCols;
    int [][][] matrix;

    //Constructor, sets the same values as the matrix
    public Agent(int [][][] m)
    {
        this.matrix = new int[m.length][m[0].length][2];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++){
                for(int k = 0; k < 2; k++){
                    this.matrix[i][j][k] = m[i][j][k];
                }
            }
        this.numRows = m.length;
        this.numCols = m[0].length;
    }


    //Gets the dominant strategy if there exists one
    int getDominant()
    {
        //the strat to return. If -1, then there is no dominant strat
        int stratNumber = -1;

        //Create an array list of all of Ag1s strats
        ArrayList<int[]> strats = new ArrayList<int[]>();
        //Copy ag1's values in the payoff matrix
        for(int i = 0; i < this.numRows; i++)
        {
            int[] temp = new int[this.numCols];
            for(int j = 0; j < this.numCols; j++)
            {
                temp[j] = this.matrix[i][j][0];
            }
            strats.add(temp);
        }//end for

        //Go through each row which represents a strategy of Ag1
        int winCount = 1;
        int countTwo = 0;
        for(int i = 0; i < strats.size(); i++)
        {
            //represents the current strat of Ag1
            int[] currStrat = strats.get(i);

            //Go through each column and compare the current strat with all other strats at that column
            for(int j = 0; j < this.numRows; j++)
            {
                if(j == i)continue;

                int[] otherStrat = strats.get(j);

                //keep track of the number of times the current strat beats other strats at that column
                //go through every other strat
                for(int k = 0; k < this.numCols; k++)
                {
                    //tempTwo is the other strategy Ag1 could play
                    //if the current strat at the current column >= other strat at the current column then 
                    //count++
                    if(currStrat[k] >= otherStrat[k])
                    {
                        countTwo++;
                    }//end if
                }//end for

                //if the current strat beat the other strat at every other column then the current strat has
                //a wn
                if(countTwo >= this.numCols)
                {
                    winCount++;
                }//end if
                countTwo = 0;
               
            }//end for
            //if the winCount for the current strat >= all the other strats, then return currentStrat
            if(winCount >= this.numRows)
            {
                return i+1;
            }
        }//end for
        return stratNumber;
    }//end func


    //Fix me
    int nashEquilibrium()
    {
        return 0;
    }

    public String toString()
    {

        String s = new String();

        for(int i = 0; i < this.matrix.length; i++)
        {
            for(int j = 0; j < this.matrix[0].length; j++){
                s+="( ";
                s+= Integer.toString(this.matrix[i][j][0]);
                s+= ",";
                s+= Integer.toString(this.matrix[i][j][1]);
                s+= " )";
                s+= " ";
            }
            s+= "\n";
        }

        return s;
    }
}