class Solution {
public:
    vector<string> commonChars(vector<string>& words) {
        // Create a vector to store the common characters
        vector<string> result;
        
        // Iterate through each character from 'a' to 'z'
        for (char c = 'a'; c <= 'z'; c++) {
            // Initialize the minimum count for the current character to the maximum possible integer value
            int minCount = INT_MAX;
            
            // Iterate through each word
            for (auto word : words) {
                int count = 0;
                // Count the occurrences of the current character in the current word
                for (char ch : word) {
                    if (ch == c) {
                        count++;
                    }
                }
                // Update the minimum count for the current character
                minCount = min(minCount, count);
                // If the minimum count becomes 0, no need to check further
                if (minCount == 0) {
                    break;
                }
            }
            
            // Add the current character to the result vector the required number of times
            for (int i = 0; i < minCount; i++) {
                result.push_back(string(1, c));
            }
        }
        
        return result;
    }
};
