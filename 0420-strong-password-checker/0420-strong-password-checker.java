class Solution {
    
    public int strongPasswordChecker(String password) {
        // Convert the password string to a character array for easy iteration.
        char[] c = password.toCharArray();
        int n = c.length;

        // Initialize requirements based on password length.
        int addreq = 0; // Required additions to meet minimum length.
        int delreq = 0; // Required deletions to meet maximum length.
        int fillme = 0; // Requirements for missing types of characters (digits, uppercase, lowercase).
        int nut = 0; // Tracks number of modifications made to reduce repeated sequences.

        // Set requirements based on the current length of the password.
        if (n < 6) addreq = 6 - n;
        if (n > 20) delreq = n - 20;
        
        // Temporary character to store the last processed character.
        char temp = '@';

        // Flags to check presence of digit, uppercase and lowercase characters.
        boolean digitp = false;
        boolean upperp = false;
        boolean lowerp = false;

        int np = 0; // Current sequence length.
        int repchel = 0; // Unused variable, can be removed.

        // Priority queue to store lengths of sequences where characters repeat.
        // It sorts primarily by the sequence length modulo 3, to optimize the number of deletions required.
        Queue<Integer> delqueue = new PriorityQueue<>((a, b) -> a % 3 - b % 3);

        // Iterate through the password to detect character sequences and character types.
        for (char ch : c) {
            if (ch == temp) {
                np++;
            } else {
                if (np >= 3) {
                    delqueue.offer(np);
                }
                np = 1;
                temp = ch;
            }

            // Check and update the presence of digit, uppercase, and lowercase.
            if (!digitp && Character.isDigit(ch)) digitp = true;
            if (!upperp && Character.isUpperCase(ch)) upperp = true;
            if (!lowerp && Character.isLowerCase(ch)) lowerp = true;
        }

        // Offer the last sequence to the queue if it's a repeating sequence.
        if (np >= 3) {
            delqueue.offer(np);
        }
        
        // Increment `fillme` for each missing character type.
        if (!digitp) fillme++;
        if (!upperp) fillme++;
        if (!lowerp) fillme++;

        // Process deletions from sequences to try to meet the max length requirement.
        while (delreq > 0 && !delqueue.isEmpty()) {
            int l = delqueue.peek();
            int tobedeleted = l % 3 + 1;
            if (tobedeleted > delreq) break;
            else {
                delreq -= tobedeleted;
                l -= tobedeleted;
                delqueue.poll();
                nut += tobedeleted;
                if (l >= 3) delqueue.offer(l);
            }
        }

        // Calculate the total replacement required for leftover sequences.
        int repreq = 0;
        while (!delqueue.isEmpty()) {
            repreq += delqueue.poll() / 3;
        }

        // The total number of operations needed is the sum of deletions, max of additions/replacements, and filling missing character types.
        return nut + delreq + Math.max(fillme, Math.max(addreq, repreq));
    }
}