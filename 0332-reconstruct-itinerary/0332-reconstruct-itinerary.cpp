class Solution {
public:

    vector<string> findItinerary(vector<vector<string>>& tickets) {

        //using map+multiset+stack data structure 

        unordered_map<string,multiset<string>> adj;
        vector<string> ans; //create a vector of string to store the order of path
        int n=tickets.size();

        for(int i=0;i<n;i++)
            adj[tickets[i][0]].insert(tickets[i][1]);

            stack<string> mystack; //creating stack for storing the ans
            mystack.push("JFK");  //JFK is fixed staring point
            
            while(!mystack.empty())
            {
                string src = mystack.top();
                if(adj[src].size()==0)
                {
                    ans.push_back(src);
                    mystack.pop();
                }

                else
                {
                    auto dst = adj[src].begin();
                    mystack.push(*dst);
                    adj[src].erase(dst);
                }
            }
        reverse(ans.begin(),ans.end());
        return ans;
    }
};