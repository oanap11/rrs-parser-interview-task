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
		
		System.out.println(parseRSS("https://rss.nytimes.com/services/xml/rss/nyt/World.xml"));
		
		//third requirement of the application
		String versionNumber = "2.5.756";
		parseVersionNumber(versionNumber);
	
	}
	
	public static String parseRSS(String urlAddress) {
		try {
			URL rssUrl = new URL(urlAddress);
			BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream())); //reads the contents of the webpage
			
			StringBuilder parsedInfo = new StringBuilder();
			String line; //used for reading and storing lines
			
			while((line = in.readLine()) != null) {
				String title = extractTagContent(line, "<title>");
                String author = extractTagContent(line, "<dc:creator>");
                String pubDate = extractTagContent(line, "<pubDate>");
                String description = extractTagContent(line, "<description>");
                
                if (title != null) {
                    parsedInfo.append("\nTitle: ").append(title).append("\n");
                }
                if (author != null) {
                    parsedInfo.append("Author: ").append(author).append("\n");
                }
                if (pubDate != null) {
                    parsedInfo.append("Date: ").append(pubDate).append("\n\n");
                }
                if (description != null) {
                    parsedInfo.append("Description: ").append(description).append("\n");
                }
			}
			
			in.close();
			return parsedInfo.toString();
		}
		catch(MalformedURLException ue) {
			System.out.println("Malformed URL.");
		}
		catch(IOException ioe) {
			System.out.println("Input/Output exception.");
		}
		return null;
	}
	
	public static String extractTagContent(String line, String tagName) {
        if (line.contains(tagName)) {
            int firstPos = line.indexOf(tagName);
            String temp = line.substring(firstPos);
            temp = temp.replace(tagName, "");
            int lastPos = temp.indexOf("</" + tagName.substring(1));
            if (lastPos != -1) {
                temp = temp.substring(0, lastPos);
                return temp;
            }
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
