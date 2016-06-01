package pullsToCounts;

import java.util.*;

import javax.swing.JOptionPane;

import java.io.*;

public class DayTweetPull {
//C:\\Users\\panasonic\\workspace\\TwitterAPI\\src\\pullsToCounts\\rawTweets.txt
	
	static File sourceFile;
	static Scanner sourceFileScanner;
	static protected  ArrayList<String> tweetWords;
	protected static String allTheTweetsString = "";
	public String getAllTheTweetsString(){return allTheTweetsString;}
	static String sourceFileName = "C:\\Users\\panasonic\\workspace\\TwitterAPI\\src\\pullsToCounts\\rawTweets.txt";//JOptionPane.showInputDialog("Enter path for source file");
	
	public DayTweetPull() 
	{
		try{setupSourceFile();} catch(IOException e){System.out.println("Error opening "+ sourceFile);}
		tweetWords = new ArrayList<String>();
		
		/**try{setupOutFile();} catch(IOException e){System.out.println("Error opening "+ outputFile);}
		hashWords = new ArrayList<String>();
		mentions = new ArrayList<String>();
		urls = new ArrayList<String>();**/
	}
	
	
	public static void setupSourceFile() throws IOException
	{
		sourceFile = new File(sourceFileName);
		sourceFileScanner = new Scanner(sourceFile);
	}
	
	private static void addTweetWords(Tweet tweet)
	{
		String tweetString = tweet.getCleanTweet();
		String temp = allTheTweetsString;
		allTheTweetsString = temp.concat(tweetString); //adds to total string
		tweetWords.add(tweetString); //adds whole clean tweet as one string into list
		//System.out.println("Clean tweet: "+ tweetString);
		//System.out.println("Total string: "+ allTheTweetsString);
	}
	
	
	private void populateAllFields(Tweet t)
	{
		addTweetWords(t);
		//addHashWords(t);
		//addMentions(t);
		//addLinks(t);
	}
	
	public String getSetSendWholeString()
	{
		DayTweetPull todaysPull = new DayTweetPull();
		int count = 0;
		while(sourceFileScanner.hasNextLine())
		{
			String t = sourceFileScanner.nextLine();
			Tweet tweetFromFile = new Tweet(t);
			addTweetWords(tweetFromFile);
			count++;
		}
		sourceFileScanner.close();
		return todaysPull.getAllTheTweetsString();
	}
	
	
	
	
	public static void main(String [] args)
	{ 
		DayTweetPull todaysPull = new DayTweetPull();
		todaysPull.getSetSendWholeString();
		System.out.println(" clean tweets: ");
		System.out.println(todaysPull.getAllTheTweetsString());
		//sendToOutfile(tweetWords);
		//sendToOutfile(hashWords);
		//sendToOutfile(mentions);
		//sendToOutfile(urls);
	
	//outPrintWriter.close();
	sourceFileScanner.close();
	}
}

/**
 * 
 * C:\\Users\\panasonic\\workspace\\TwitterAPI\\src\\pullsToCounts\\processedTweets.txt
 * iding a filepath to as a string literal you need 
	to use two backslashes for every 
}
static String outFileName = "C:\\Users\\panasonic\\workspace\\TwitterAPI\\src\\pullsToCounts\\processedTweets.txt";//= JOptionPane.showInputDialog("Enter path for output file");
static FileWriter outputFile;
static PrintWriter outPrintWriter;
static protected ArrayList<String> hashWords;
static protected ArrayList<String> mentions;
static protected ArrayList<String> urls;
public static void setupOutFile() throws IOException
//{throw new IOException("Error with the output file.");}
{
	try
	{
		outputFile = new FileWriter("outFileName", true);
		outPrintWriter = new PrintWriter(outputFile);
	} catch(IOException e){}
}
private static void addHashWords(Tweet tweet)
{
	List<String> hashString = new ArrayList<String>();
	hashString = tweet.getHashList();
	hashWords.addAll(hashString);
}
private static void addMentions(Tweet tweet)
{
	List<String> mentionString = new ArrayList<String>();
	mentionString = tweet.getMentionsList();
	mentions.addAll(mentionString);
}
private static void addLinks(Tweet tweet)
{
	List<String> linkString = new ArrayList<String>();
	linkString = tweet.getURLList();
	urls.addAll(linkString);
}
private static void sendToOutfile(List<String> s)
{
	outPrintWriter.println(s);
}
**/
