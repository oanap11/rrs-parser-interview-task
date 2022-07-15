import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JavaSolution {
	
	static StringTokenizer tokenizer;
	static ArrayList<String> info;

	public static void main(String[] args) {
		
		//second requirement of the application
		System.out.println(parseRSS("https://rss.nytimes.com/services/xml/rss/nyt/World.xml"));
		
		//third requierement of the application
		String versionNumber = "2.5.756";
		parseVersionNumber(versionNumber);
	
	}
	
	public static String parseRSS(String urlAddress) {
		try {
			URL rssUrl = new URL(urlAddress);
			BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream())); //reads the contents of the webpage
			
			String parsedInfo = "";
			String line; //used for reading and storing lines
			
			//when the contents of the webpage have been read, in.readLine() will return null
			//finds the required opening tag and retrieves the substring between it and the end tag
			while((line = in.readLine()) != null) {
				if (line.contains("<title>")) {
					int firstPosTitle = line.indexOf("<title>");
					String temp = line.substring(firstPosTitle);
					temp = temp.replace("<title>", "");
					int lastPosTitle = temp.indexOf("</title");
					temp = temp.substring(0, lastPosTitle);
					parsedInfo += "\nTitle: " + temp + "\n";
				}
				
				if (line.contains("<dc:creator>")) {
					int firstPosAuthor = line.indexOf("<dc:creator>");
					String temp = line.substring(firstPosAuthor);
					temp = temp.replace("<dc:creator>", "");
					int lastPosAuthor = temp.indexOf("</dc:creator");
					temp = temp.substring(0, lastPosAuthor);
					parsedInfo += "Author: " + temp + "\n";
				}
				
				if (line.contains("<pubDate>")) {
					int firstPosDate = line.indexOf("<pubDate>");
					String temp = line.substring(firstPosDate);
					temp = temp.replace("<pubDate>", "");
					int lastPosDate = temp.indexOf("</pubDate");
					temp = temp.substring(0, lastPosDate);
					parsedInfo += "Date: " + temp + "\n\n";
				}
				
	
				if (line.contains("<description>")) {
					int firstPosDate = line.indexOf("<description>");
					String temp = line.substring(firstPosDate);
					temp = temp.replace("<description>", "");
					int lastPosDate = temp.indexOf("</description");
					temp = temp.substring(0, lastPosDate);
					parsedInfo += "Description: " + temp + "\n";
				}
			}
			
			in.close();
			return parsedInfo;
		}
		catch(MalformedURLException ue) {
			System.out.println("Malformed URL.");
		}
		catch(IOException ioe) {
			System.out.println("Input/Output exception.");
		}
		return null;
	}
	
	public static void parseVersionNumber(String vNumber) {
		tokenizer = new StringTokenizer(vNumber, ".");
		info = new ArrayList<String>();
		
		while(tokenizer.hasMoreTokens()) {
			String number = tokenizer.nextToken().trim();
			info.add(number);
		} 
		
		System.out.print("Major=" + info.get(0) + "; " + "Minor=" + info.get(1) + "; " + "BugFix=" + info.get(2));
	}

}
