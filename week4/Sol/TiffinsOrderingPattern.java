/*
 * 
Analysis of Tiffins Ordering Pattern

A popular online food delivery chain wanted to analyze the patterns of the tiffin orders placed
by its users at different time intervals. The information of the users, time intervals and tiffin-
items ordered are stored in three different arrays namely username [], timestamp [] and tiffin
items []. We can assume that a user, username[i] has ordered the item, tiffinitem[i] at time
timestamp[i].

A 3-sequence is a list of 3 tiffin item ordered in the increasing order of timestamp. (3-sequence
are not necessarily distinct.) The items are sorted in ascending order by the time of ordering.
Note the following

Both username[i] and tiffinitems[i] contain only lowercase characters.

It is guaranteed that there is at least one user who ordered at least 3 tiffin items.

No user orders two tiffin items at the same time.

Find the 3-sequence ordered by the largest number of users. If there is more than one solution,
return the lexicographically smallest such 3-sequence.


For example:

Sample input :
tim tim tim bob bob bob bob ann ann ann
1 2 3 4 5 6 7 8 9 10
idly mdosa upma idly mdosa pattu pdosa idly mdosa upma

Sample output:
idly
mdosa
upma

Explanation:
First line indicates list of users (Space seperated)
Second line indicates timestamps of each user (Space seperated)
Third line indicates tiffin items ordered by each user (Space seperated)
The tuples in this example are:
["tim", 1, "idly"], ["tim", 2, "mdosa"], ["tim", 3, "upma"], ["bob", 4, "idly"], ["bob", 5,
"mdosa"], ["bob", 6, "pattu"], ["bob", 7, "pdosa"], ["ann", 8, "idly"], ["ann", 9, "mdosa"],["ann", 10, "upma"]

The 3-sequence
o ("idly", "mdosa", "upma") was ordered at least once by 2 users.
o ("idly", "mdosa", "pdosa") was ordered at least once by 1 user.
o ("idly", "pattu", "pdosa") was ordered at least once by 1 user.
o ("idly", "mdosa", "pattu") was ordered at least once by 1 user.
o ("mdosa", "pattu", "pdosa") was ordered at least once by 1 user.


==========================================================================



input=tim tim tim bob bob bob bob ann ann ann
1 2 3 4 5 6 7 8 9 10
idly mdosa dosa idly mdosa pattu dosa idly mdosa dosa
output=idly
mdosa
dosa



input=tim tim tim bob bob bob bob bob ann ann ann ram ram ram
1 2 3 4 5 6 7 8 9 10 11 12 13 14
idly mdosa upma idly mdosa pattu pdosa idly pdosa mdosa upma mdosa pdosa idly
output=mdosa
pdosa
idly


input=tim tim tim tim tim bob bob bob bob bob ann ann ann ram ram ram
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
idly pdosa mdosa upma pattu pdosa mdosa pattu pdosa idly pdosa mdosa pattu mdosa pdosa idly
output=pdosa
mdosa
pattu



input=tim tim tim tim tim bob bob bob bob bob ann ann ann ann ann ram ram ram ram ram
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
idly dosa pdosa upma pattu pdosa dosa pattu pdosa idly pdosa dosa pdosa idly pdosa mdosa pattu dosa pdosa idly
output=dosa
pdosa
idly


input=tim tim tim tim tim bob bob bob bob bob ann ann ann ram ram ram syam syam syam
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
idly pdosa mdosa upma idly pdosa mdosa pattu pdosa idly pdosa mdosa pattu idly pdosa mdosa idly pdosa mdosa
output=idly
pdosa
mdosa



input=tim tim tim tim tim bob bob bob bob bob ann ann ann ann ann ram ram ram ram ram sai sai sai
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
wada dosa pdosa upma pattu pdosa dosa pattu pdosa wada pdosa dosa pdosa wada dosa pdosa wada pdosa mdosa pattu dosa pdosa wada
output=dosa
pdosa
wada


input=tim tim tim tim tim bob bob bob bob bob ann ann ann ann ann ram ram ram ram ram sai sai sai tim bob ann
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 25 26 27
wada dosa pdosa upma pattu pdosa dosa pattu pdosa wada pdosa dosa pdosa wada dosa pdosa wada pdosa mdosa pattu dosa pdosa wada upma upma upma
output=dosa
pdosa
upma



 */

package Elite2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.*;

public class TiffinsOrderingPattern {
	
	public static  List<String> tiffinsOrderingPattern(String[] username, int[] timestamp, String[] tiffins) {
        int n = timestamp.length;
        //1. Sort sessions list by time, can not use map ,because web will be duplicated
        List<List<String>> sessions =  new ArrayList<>();
        for (int i = 0; i < n; i++){
            sessions.add(new ArrayList<>());
            sessions.get(i).add(username[i]);
            sessions.get(i).add(""+timestamp[i]);
            sessions.get(i).add(tiffins[i]);
        }
        sessions.sort((a, b)-> Integer.parseInt(a.get(1)) - Integer.parseInt(b.get(1)));
        
        //2. add each person visited list;
        Map<String, List<String>> visited = new HashMap<>();//(name, list<web>)
        for (int i = 0; i < n; i++){
            visited.putIfAbsent(sessions.get(i).get(0), new ArrayList<>());
            visited.get(sessions.get(i).get(0)).add(sessions.get(i).get(2));
        }
        
        //3. find each user list and build all 3-subsequences and count by map, and get maxCount;
        Map<String, Integer> sequence = new HashMap<>();//(sequence, count)
        int maxCount = 0;
        String maxseq = "";
        for (String name : visited.keySet()){
            List<String> list = visited.get(name);
            if(list.size() < 3) continue;
            //build users' all 3-sequences, use set in case duplicated 3-sequences
            Set<String> subseqences = subseqence(list);
            for (String seq : subseqences){
                sequence.put(seq, sequence.getOrDefault(seq, 0)+1);
                if(sequence.get(seq) > maxCount){
                    maxCount = sequence.get(seq);
                    maxseq = seq;
                }
                else if (sequence.get(seq) == maxCount && seq.compareTo(maxseq) < 0) maxseq = seq;
            }
        }
        String[] strs = maxseq.split(",");
        List<String> res = new ArrayList<>();
        for (String s : strs) res.add(s);
        return res;
    }
    
    public static Set<String> subseqence(List<String> list){
        int n = list.size();
        Set<String> res = new HashSet<>();
        for (int i = 0; i < n-2; i++){
            for (int j = i+1; j < n-1; j++){
                for (int k = j+1; k < n; k++){
                    res.add(list.get(i)+","+list.get(j)+","+list.get(k));
                }
            }
        }
        return res;
    }
    
    
	    public static void main(String args[] ) throws IOException {

		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str = br.readLine();
			String[] usernames = str.split(" ");
			str = br.readLine();

			String[] timestampline = str.split(" ");
			int[] timestamps = Arrays.asList(timestampline).stream().mapToInt(Integer::parseInt).toArray();
			str = br.readLine();
			String[] tiffins = str.split(" ");
			List<String> res=tiffinsOrderingPattern(usernames,timestamps,tiffins);
				for(String site:res){
		    	    System.out.println(site);
		    	} 
				
			}
}
