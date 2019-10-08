

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
    public boolean nashEquilibrium(int s1, int s2, ArrayList<Integer> arr)
    {
        //Assumuing A1 plays s1, through all of agent 2's options. If ag2 can do better with another option, then this combination of strategies
        //is  not a nash equilibrium.

        //Step 1: go to row s1.
        ArrayList<int[]> agOneStrats = getAg1Strats();
        ArrayList<int[]> agTwoStrats = getAg2Strats();
      
    
        
        //go through all of ag1s strats, checking the payoff for ag2's assumed strat
        for(int i = 0; i < agOneStrats.size(); i++)
        {
            //get the value for ag1's ith strat assuming ag2 plays s2
            int val = agOneStrats.get(i)[s2];
            //if it is greater than s1 then return false b/c ag1 can do better than s1 assuming ag2 plays s2
            if(val  >agOneStrats.get(s1)[s2])return false;
        }//end for

        //Go through all of ag2's strats, checking the payoff for ag2 assuming ag1 plays s1
        for(int i = 0; i < agTwoStrats.get(s1).length; i++)
        {
           
            //if there is a valye greater than s1 then return false b/c ag2 can do better than s2 assuming ag1 plays s1
            int val = agTwoStrats.get(s1)[i];
            if(val > agTwoStrats.get(s1)[s2])return false;
        }
        arr.add(s1+1);
        arr.add(s2+1);
        return true;
    }

    public ArrayList<Integer> findNash()
    {

        ArrayList<Integer> strats = new ArrayList<Integer>();

        int a1Strats = this.numRows;
        int a2Strats = this.numCols;

        //go through all combinations of strats, s1/i, s2/j and try to find a NE
        for(int i = 0; i < a1Strats; i++)
            for(int j =0; j < a2Strats; j++)
                nashEquilibrium(i, j, strats);


        return strats;
    }

    public void runOptions()
    {
        int dominant = this.getDominant();
        if(dominant != -1)
        {
            System.out.println("Weakly dominant strategy of Ag1 is s"+Integer.toString(dominant));
            System.out.println("Ag1 strategy  = s"+Integer.toString(dominant));
            return;

        }
        ArrayList<Integer> nash = this.findNash();
        for(int i = 0; i < nash.size(); i+=2)
        {
            System.out.println("Nash option for Ag1 is s"+Integer.toString(nash.get(i))+" because Ag1 can do no better if Ag2 plays s"+Integer.toString(nash.get(i+1)));
            System.out.println("And Ag2 can do no better than s"+Integer.toString(nash.get(i+1))+" if Ag1 plays s"+Integer.toString(nash.get(i)));
            System.out.println("Ag1 strategy = s"+Integer.toString(nash.get(i)));
        }
        if(nash.size() > 0)return;

        int randStrat = (int)Math.random();

        randStrat = randStrat % this.numRows;
        randStrat++;
        System.out.println("Ag1 should play random strategy s"+Integer.toString(randStrat)+" because Ag1 can do no worse");
        
    }
    public String toString()
    {

        String s = new String();
        for(int i = 0; i < this.numCols; i++)
        {
            if(i == 0)
            {
                s+= "        ";
            }
            s+="Ag2: ";
            s+="t";
            s+= Integer.toString(i);
            s+= " ";
        }
        s+="\n";
        for(int i = 0; i < this.numRows; i++)
        {
            s+= "Ag1:";
            s+= " s";
            s+=Integer.toString(i+1);
            s+= " ";
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

        return s;
    }
}