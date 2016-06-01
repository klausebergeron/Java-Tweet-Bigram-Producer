package pullsToCounts;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Object;
import pullsToCounts.Extractor;

public class Tweet {

	public final String KEY_WORD = "depression";

	protected Extractor extractor = new Extractor();
	protected String originalTweet;
	protected String cleanTweet;
	protected String getCleanTweet(){return cleanTweet;}
	protected String[] words;
	protected ArrayList<String> tweetWords;
	Iterator<String> tweetWordIt;//creates next&has next
	
	
	
public Tweet(){ }
	
	public Tweet(String oString)
	{ 
		originalTweet = oString;
		tweetWords = new ArrayList<String>();
		tweetWordIt = tweetWords.iterator();
		cleanTweet = cleanTweet(oString);
		
		/**initializing mentions/urls/hash lists
		*mentions = new ArrayList<String>();
		urls = new ArrayList<String>();
		hashIt = hashWords.iterator();
		 * //hashWords = new ArrayList<String>();**/
	}
	
	protected String getWord(int index)
		{ return tweetWords.get(index);	}
	
	
	public void displayTweet()
	{
		System.out.println("Original Tweet: "+originalTweet);
		System.out.println("Clean Tweet: "+ cleanTweet);
	}
	
	
	protected List<String> extractWords()
	{
		String regex = " ";
		List<String> tWords = new ArrayList<String>(Arrays.asList(cleanTweet.split(regex)));
		tweetWords.addAll(tWords);
		return tWords;
	}
	
	
	protected String removeUrl(String str)
    {
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        int i = 0;
        while (m.find()) {
            str = str.replaceAll(m.group(i),"").trim();
            i++;
        }
        return str;
    }
	
	protected String cleanTweet(String ot)
	{
		String charOT = removeUrl(ot);
		charOT = charOT.replaceAll("[^A-Za-z ]", "");
		return charOT.toLowerCase();
		//cleanTweet = clearStopWords(this.originalTweet);
	}
	
	
	protected List<String> getTweetWords()
	{ return tweetWords; } 

	
public static void main(String[] args)
{
	Tweet sampleTweet = new Tweet("I work this weekend so I'll be missing out on the FQF, low key depressed about it lol \ud83d\ude29 You #aintNevah can never tell how i'm gonna feel in one day...I can be happy.. or depressed... #Bot");
	sampleTweet.displayTweet();
	
}

/**temporarily unused variables/methods
 * protected ArrayList<String> hashWords;
protected ArrayList<String> mentions;
protected ArrayList<String> urls;
Iterator hashIt;

*protected String getHash(int index)
	{ return hashWords.get(index); }
	public void displayHash()
{
	System.out.print("Hashtagged words: \n");
	for(String word : hashWords)
	{
		System.out.println(word);
	}
}
protected boolean isHash(String word)
{
	if(word.indexOf('#') == -1 || word.indexOf('＃') == -1 )
		return false;
	else
		return true;
}
public void displayMentions()
{
	System.out.print("User mentions: \n");
	for(String word : mentions)
	{
		System.out.println(word);
	}
}
public void displayURLs()
{
	System.out.print("URLs: \n");
	for(String word : urls)
	{
		System.out.println(word);
	}
}

  /**
   * Extract @username references from Tweet text. A mention is an occurance of @username anywhere in a Tweet.
   *
   * @return List of usernames referenced (without the leading @ sign)
   
protected List<String> extractMentionedScreennames()
{
	List<String> mentionedWords = extractor.extractMentionedScreennames(this.originalTweet);
	mentions.addAll(mentionedWords);
	return mentionedWords;
}
  /**
   * Extract URL references from Tweet text.
   *
   * @param text of the tweet from which to extract URLs
   * @return List of URLs referenced.
  
protected List<String> extractURLs()
{
	List<String> urls = extractor.extractURLs(this.originalTweet);
	hashWords.addAll(urls);
	return urls;
}

  /**
   * Extract #hashtag references from Tweet text.
   *
   * @return List of hashtags referenced (without the leading # sign)
  
protected List<String> extractHashtags()
{
	List<String> hWords = extractor.extractHashtags(this.originalTweet);
	hashWords.addAll(hWords);
	return hWords;
}
protected List<String> getMentionsList()
{ return mentions;}
protected List<String> getHashList()
{ return hashWords;}
protected List<String> getURLList()
{ return urls;}
protected String clearStopWords(String w)
{
	Stopwords stopwordClear = new Stopwords();
	String cTweet = w.toLowerCase();
	cTweet = stopwordClear.removeStopWords(cTweet);
	return cTweet;
}
	**/
}
