package edu.upenn.cis350.hwk1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IOReader {
	public List<Tweet> tweetReader() {
		File file = new File(Main.tweetsFile);
		List<Tweet> tweetList = new ArrayList<Tweet>();
		String currLine = null;
		
		if (!file.exists()) {
			System.out.println("File does not exist.");
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " Error reading tweets file." );
		}
		
		if (!file.canRead()) {
			System.out.println("File cannot be read.");
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " Error reading tweets file." );
		}
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			while ((currLine = br.readLine()) != null) {
				int comma1 = currLine.indexOf(",");
				int closeBracket = currLine.indexOf("]");
				int dateIndex = currLine.indexOf("-", closeBracket) - 4;
				int txtIndex = currLine.indexOf("\t", dateIndex);
				
				double xcoord = Double.parseDouble(currLine.substring(1, comma1));
				double ycoord = Double.parseDouble(currLine.substring(comma1 + 1, closeBracket));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
				Date date = null;
				
				try {
					date = sdf.parse(currLine.substring(dateIndex, dateIndex + 19));
				}
				catch (ParseException e) {
					System.out.println("Error parsing date");
					System.out.println(currLine.substring(dateIndex, dateIndex + 19));
					System.exit(0);
				}
				String txt = currLine.substring(txtIndex + 1, currLine.length());
				
				tweetList.add(new Tweet(txt, xcoord, ycoord, date));
			}
			br.close();
		}
		catch (IOException e) {
			System.out.println("Error reading tweets .txt file");
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " Error reading tweets file." );
			System.exit(0);
		}
		
		return tweetList;
	}
	
	public List<State> stateReader() {
		File file = new File(Main.statesCSV);
		List<State> stateList = new ArrayList<State>();
		String currLine = null;
		
		if (!file.exists()) {
			System.out.println("File does not exist.");
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " Error reading states file." );
		}
		
		if (!file.canRead()) {
			System.out.println("File cannot be read.");
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " Error reading states file." );
		}
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			while ((currLine = br.readLine()) != null) {
				int comma1 = currLine.indexOf(",");
				int comma2 = currLine.indexOf(",", comma1 + 1);
				
				String stateName = currLine.substring(0, comma1);
				double xcoord = Double.parseDouble(currLine.substring(comma1 + 1, comma2));
				double ycoord = Double.parseDouble(currLine.substring(comma2 + 1, currLine.length()));
				
				stateList.add(new State(stateName, xcoord, ycoord));
			}
			br.close();
		} 
		catch (IOException e) {
			System.out.println("Error reading states .csv file");
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " Error reading states file." );
			System.exit(0);
		}
		
		return stateList;
	}
}
