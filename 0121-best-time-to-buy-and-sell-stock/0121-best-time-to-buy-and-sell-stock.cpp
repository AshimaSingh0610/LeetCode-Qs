class Solution {
public:
    int maxProfit(vector<int>& prices) 
    {

        int lsf = INT_MAX;
        int overall_profit = 0;
        

        for(int i=0;i<prices.size();i++)
        {
            if(prices[i]<lsf)
            {
                lsf=prices[i];

            }
            int  profit_as_of_today = prices[i]-lsf;

            if(profit_as_of_today > overall_profit)
            {
                overall_profit = profit_as_of_today ;
            }
        }

        return overall_profit;



        
    }
};