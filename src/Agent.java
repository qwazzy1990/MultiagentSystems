

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

    public ArrayList<int[]> getAg1Strats()
    {
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
        return strats;
    }
    public ArrayList<int[]> getAg2Strats()
    {
        //Create an array list of all of Ag2s strats
        ArrayList<int[]> strats = new ArrayList<int[]>();
        //Copy ag2's values in the payoff matrix
        for(int i = 0; i < this.numRows; i++)
        {
            int[] temp = new int[this.numCols];
            for(int j = 0; j < this.numCols; j++)
            {
                temp[j] = this.matrix[i][j][1];
            }
            strats.add(temp);
        }//end for
        return strats;
    }


    //Gets the dominant strategy if there exists one
    int getDominant()
    {
        //the strat to return. If -1, then there is no dominant strat
        int stratNumber = -1;

        ArrayList<int[]> strats = getAg1Strats();        

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
    //The strategies are labelled s1 and s2 respectively. s1 is agent 1's strategy and s2 is agent 2's strategy
    int nashEquilibrium(int s1, int s2)
    {
        //Assumuing A1 plays s1, through all of agent 2's options. If ag2 can do better with another option, then this combination of strategies
        //is  not a nash equilibrium.

        //Step 1: go to row s1.
        ArrayList<int[]> agOneStrats = getAg1Strats();
        ArrayList<int[]> agTwoStrats = getAg2Strats();

        System.out.println(agOneStrats.get(0).length);
        for(int i = 0; i < agOneStrats.size(); i++){
            for(int j = 0; j < agOneStrats.get(0).length; j++){
                System.out.print(agOneStrats.get(i)[j]);
            }
            System.out.println();
        }

        for(int i = 0; i < agTwoStrats.size(); i++){
            for(int j = 0; j < agTwoStrats.get(0).length; j++){
                System.out.print(agTwoStrats.get(i)[j]);
            }
            System.out.println();
        }

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