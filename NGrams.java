package pullsToCounts;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class NGrams {
	private String words[]; // List of words in Supplied String
    protected HashMap<String, List<String>> nGramsMap; // Mapping of the Ngrams
    protected HashMap<String, Integer> preceding;
   //creates next&has next
    protected HashMap<String, Integer> following;
    private int value; // Value for NGram
    public String keyword = "depressed";
    protected int totalAppearancesOfKeyword = 0;
    
    public NGrams(String text, int n) {
		// TODO Auto-generated constructor stub
    	if (text == null) throw new NullPointerException("Check the input parameters: String should not be null");
        if(n<1 || text.length()==0)  throw new IllegalArgumentException("Check the input parameters : String should not  empty and Ngram value should be > 1");

        words = text.split(" ");
        nGramsMap = new HashMap<String, List<String>>();
        preceding = new HashMap<String, Integer>(0);
        following = new HashMap<String, Integer>(0);
        this.value = n;
	}

    private void addToPreceding(String s)
    {
    	if (!preceding.containsKey(s))
    	{
    		preceding.put(s, 1);
    	}
    	else
    		preceding.put(s, preceding.get(s) + 1); //increments frequency
    }
    private void addToFollowing(String s)
    {
    	if (!following.containsKey(s))
    	{
    		following.put(s, 1);
    	}
    	else
    		following.put(s, following.get(s) + 1); //increments frequency
    }
	public Map<String, List<String>> generateNgrams()
    {
        // Filling the HashMap based on N value and using WOrds Array
        /*
         * Look at each set of N adjacent words in a document. Use the first N-1 words of the set as a key, and remember the fact that the nth word
         * followed that key. Once you’ve finished, you know the list of individual words that can follow each N-1 word sequence in the document.
         */
        for (int i = 0; i < words.length; i++)
        {
        	if((words[i]).equals(keyword))
        	{
        		totalAppearancesOfKeyword++;
	            StringBuilder sb = new StringBuilder();
	            
	            	if(i>0)
	            		{ sb.append((words[i-1]).trim());}
	            	sb.append(" ");
	            	sb.append(words[i].trim());
	            	sb.append(" ");
	            	if(i==words.length-1)
	            		{sb.append(" ");}
	            	else
	            		sb.append(words[i + 1].trim());
	        
	
	            String key = sb.toString();
	
	            if (!nGramsMap.containsKey(key))
	            {
	                ArrayList<String> list = new ArrayList<>();
	                list.add(words[i-1]);
	                addToPreceding(words[i-1]);
	                if(i!= words.length-1)
	                {
	                	list.add(words[i + 1]);
	                	addToFollowing(words[i+1]);
	                }
	                nGramsMap.put(key, list);
	            }

	           preceding = sortHashMapByValues(preceding);
	           following = sortHashMapByValues(following);
	            //System.out.printf( "%-25s %-25s %n", key, words[i-1] + "\t" + words[i+1]);
	            //System.out.println("=>   "+words[i-1]+ ", " + words[i+1] );
        	}
	        
        }return nGramsMap;
    }
    protected Map<String, Integer> getPreceding()
    {
    	System.out.println("\nWords before depression:");
    	for(Map.Entry<String, Integer> entry : preceding.entrySet()){
    	    System.out.printf( "%-15s %-15s %n", entry.getKey(), entry.getValue());
    	}
    	return preceding;
    }
    protected Map<String, Integer> getFollowing()
    {
    	System.out.println("\nWords following depression:");
    	for(Map.Entry<String, Integer> entry : following.entrySet()){
    	    System.out.printf("%-15s %-15s %n", entry.getKey(), entry.getValue());
    	}
    	return following;
    }
    public LinkedHashMap<String, Integer> sortHashMapByValues(HashMap passedMap) {
    	   List mapKeys = new ArrayList(passedMap.keySet());
    	   List mapValues = new ArrayList(passedMap.values());
    	   Collections.sort(mapValues);
    	   Collections.sort(mapKeys);

    	   LinkedHashMap sortedMap = new LinkedHashMap();

    	   Iterator valueIt = mapValues.iterator();
    	   while (valueIt.hasNext()) {
    	       Object val = valueIt.next();
    	       Iterator keyIt = mapKeys.iterator();

    	       while (keyIt.hasNext()) {
    	           Object key = keyIt.next();
    	           String comp1 = passedMap.get(key).toString();
    	           String comp2 = val.toString();

    	           if (comp1.equals(comp2)){
    	               passedMap.remove(key);
    	               mapKeys.remove(key);
    	               sortedMap.put((String)key, (Integer)val);
    	               break;
    	           }

    	       }

    	   }
    	   return sortedMap;
    	}
    

    public static void main(String[] args)
    {
    	DayTweetPull dayTweetPull = new DayTweetPull();
    	String s = dayTweetPull.getSetSendWholeString();
    	NGrams ngrams = new NGrams(s, 3);
    	ngrams.generateNgrams();
    	ngrams.getPreceding();
    	ngrams.getFollowing();
    	
    }
}
