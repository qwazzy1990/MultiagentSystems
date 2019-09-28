import java.util.*;

public class PayoffTuple
{
    //array representing the payoff for two agents.
    //idex 0 will be agent 1's payoff and index 1 will be agent two's payoff
    public int[] payoff;
    public PayoffTuple(int a, int b)
    {
        this.payoff[0] = a;
        this.payoff[1] = b;
    }
}