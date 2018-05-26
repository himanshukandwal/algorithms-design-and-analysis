package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 721. Accounts Merge
 *
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name,
 * they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves
 * can be returned in any order.
 *
 * Example 1:
 *      Input: accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 *      Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
 *
 *      Explanation: The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 *                   The second John and Mary are different people as none of their email addresses are used by other accounts.
 *                   We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 *                   ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 *
 * Note:
 *   The length of accounts will be in the range [1, 1000].
 *   The length of accounts[i] will be in the range [1, 10].
 *   The length of accounts[i][j] will be in the range [1, 30].
 *
 * @author hxkandwal
 */
public class AccountsMerge extends AbstractCustomTestRunner {

    // union-find
    public List<List<String>> _accountsMergeUnionFind(List<List<String>> accounts) {
        int [] reps = new int [accounts.size()];
        for (int idx = 0; idx < accounts.size(); idx ++) reps [idx] = idx;

        Map<String, Integer> e2idx = new HashMap<>();

        for (int idx = 0; idx < accounts.size(); idx ++) {
            for (int j = 1; j < accounts.get(idx).size(); j ++) {
                String email = accounts.get(idx).get(j);
                if (!e2idx.containsKey(email)) e2idx.put (email, idx);
                else {
                    int prevIdx = e2idx.get(email);
                    int repp = find (reps, prevIdx);
                    int repc = find (reps, idx);

                    if (repp != repc) reps [repc] = repp;
                }
            }
        }

        for (int idx = 0; idx < reps.length; idx ++) find (reps, idx);
        List<List<String>> ans = new ArrayList<>();
        for (int idx = 0; idx < accounts.size(); idx ++) {
            if (reps [idx] != idx) continue;

            Set<String> uniq = new HashSet<>();
            for (int j = 0; j < reps.length; j ++) {
                if (reps [j] == idx)
                    for (int k = 1; k < accounts.get (j).size(); k ++) uniq.add (accounts.get(j).get(k));
            }
            List<String> list = new ArrayList<>(uniq);
            Collections.sort(list, (a, b) -> a.compareTo(b));
            list.add (0, accounts.get(idx).get(0));
            ans.add(list);
        }
        return ans;
    }

    private int find (int [] reps, int val) {
        return (val == reps [val]) ? val : (reps [val] = find (reps, reps [val]));
    }

    // DFS
    public List<List<String>> _accountsMergeDFS(List<List<String>> accounts) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> e2e = new HashMap<>();
        for (List<String> l : accounts) {
            for (int idx = 2; idx < l.size(); idx ++) {
                e2e.computeIfAbsent(l.get(idx), k -> new ArrayList<>()).add(l.get(1));
                e2e.computeIfAbsent(l.get(1), k -> new ArrayList<>()).add(l.get(idx));
            }
        }

        Set<String> seenEmails = new HashSet<>();
        for (List<String> l : accounts) {
            if (!seenEmails.contains(l.get(1))) {
                List<String> list = new ArrayList<>();
                dfs(e2e, seenEmails, list, l.get(1));
                Collections.sort (list, (a, b) -> a.compareTo(b));
                list.add(0, l.get(0));
                ans.add(list);
            }
        }
        return ans;
    }

    private void dfs (Map<String, List<String>> e2e, Set<String> seenEmails, List<String> collector, String email) {
        if (seenEmails.contains(email)) return;
        seenEmails.add(email);
        collector.add(email);
        if (e2e.get(email) != null) for (String e : e2e.get(email)) dfs (e2e, seenEmails, collector, e);
    }
}
