package pullsToCounts;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Stream;
import java.util.*;
import java.math.*;
import java.text.DecimalFormat;

public class NGramCalculators extends NGrams{

	protected HashMap<String, Double> precPercents;
	protected HashMap<String, Double> folPercents;
	public NGramCalculators(String text, int n) {
		super(text, n);
		precPercents = new HashMap<String, Double>();
		folPercents = new HashMap<String, Double>();
		// TODO Auto-generated constructor stub
	}
	protected HashMap<String, Double> fillFolPercents()
	{
		for(Map.Entry<String, Integer> entry : following.entrySet())
		{
			double i = (double)(entry.getValue());
    		double percentfrequency = getPercent(i);
    		String key = entry.getKey();
    		folPercents.put(key, percentfrequency);
    	    System.out.printf( "%-15s %-15s percent %n", entry.getKey(), percentfrequency);
    	}
		return folPercents;
	}
	protected HashMap<String, Double> fillPrecPercents()
	{
		for(Map.Entry<String, Integer> entry : preceding.entrySet())
		{
			double i = (double)(entry.getValue());
			//System.out.println("Frequency: " + i);
    		double percentfrequency = getPercent(i);
    		//System.out.println("Total ap or keyword: "+totalAppearancesOfKeyword);
    		//System.out.println("Percent frequency: "+percentfrequency);
    		String key = entry.getKey();
    		precPercents.put(key, percentfrequency);
    		//System.out.println("New dec format? "+new DecimalFormat("0.0000").format(percentfrequency));
    	    System.out.printf( "%-15s %-15s percent %n", entry.getKey(), percentfrequency);
    	}
		
		return precPercents;
	}
	 protected double getPercent(double counts)
	    {    	
		 return (counts/totalAppearancesOfKeyword)*100;    
		}
	
	public static void main(String[] args)
	{
		DayTweetPull dayTweetPull = new DayTweetPull();
    	String s = dayTweetPull.getSetSendWholeString();
    	NGramCalculators ngramsCalc = new NGramCalculators(s, 3);
    	ngramsCalc.generateNgrams();
    	ngramsCalc.getPreceding();
    	ngramsCalc.getFollowing();
    	System.out.println("\nPreceeding word counts/ total deppressed counts: ");
    	ngramsCalc.fillPrecPercents();
    	System.out.println("\nFollowing word counts/ total deppressed counts: ");
    	ngramsCalc.fillFolPercents();
    	
    	
	}

}
