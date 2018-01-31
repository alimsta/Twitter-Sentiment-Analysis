package edu.upenn.cis350.hwk1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
import java.text.SimpleDateFormat;

public class Calculations {
	private List<Tweet> tweetList;
	private List<State> stateList;
	
	public void readAll() {
		IOReader reader = new IOReader();
		stateList = reader.stateReader();
		tweetList = reader.tweetReader();
	}
	
	public void numTweetsPerState() {
		for (int i = 0; i < tweetList.size(); i++) {
			Tweet currTweet = tweetList.get(i);
			State currState = stateList.get(0);
			double dx = currTweet.getX() - currState.getX();
			double dy = currTweet.getY() - currState.getY();
			double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
			
			for (int j = 0; j < stateList.size(); j++) {
				double currDX = currTweet.getX() - stateList.get(j).getX();
				double currDY = currTweet.getY() - stateList.get(j).getY();
				double currDist = Math.sqrt(Math.pow(currDX, 2) + Math.pow(currDY, 2));
				
				if (distance >= currDist) {
					currState = stateList.get(j);
					distance = currDist;
				}
			}
			currState.setTweetCount(currState.getTweetCount() + 1);
		}
		Collections.sort(stateList);
		for (int k = 0; k < stateList.size(); k++) {
			if (stateList.get(k).getTweetCount() > 0) {
				System.out.println(stateList.get(k).getName() + " " + stateList.get(k).getTweetCount());
			}
		}
		System.out.println("\n");
	}
	
	public void hashtagsByState() {
		List<Tweet> tweets = new ArrayList<Tweet>();
		List<HT> HTList = new ArrayList<HT>();
		System.out.println("Enter a state: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		
		State state = new State("Failure", 0, 0);
		State state2 = new State("Failure", 0, 0);
		for (int i = 0; i < stateList.size(); i++) {
			if (stateList.get(i).getName().toLowerCase().equals(name.toLowerCase()))
	    		state = stateList.get(i);
	    }
		
		if (state.equals(state2)) {
			System.out.println("Invalid State Name. Try again.");
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " Most Popular Hashtags for: INVALID");
			hashtagsByState();
		}
		
		String time = String.valueOf(System.currentTimeMillis());
		LogFile.write(time + " Most Popular Hashtags for: " + state.getName());
		
		for (int i = 0; i < tweetList.size(); i++) {
			Tweet currTweet = tweetList.get(i);
			State currState = stateList.get(0);
			double dx = currTweet.getX() - currState.getX();
			double dy = currTweet.getY() - currState.getY();
			double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
			
			for (int j = 0; j < stateList.size(); j++) {
				double currDX = currTweet.getX() - stateList.get(j).getX();
				double currDY = currTweet.getY() - stateList.get(j).getY();
				double currDist = Math.sqrt(Math.pow(currDX, 2) + Math.pow(currDY, 2));
				
				if (distance >= currDist) {
					currState = stateList.get(j);
					distance = currDist;
				}
			}
			if (currState != null && state != null && currState.getName() == state.getName()) {
				tweets.add(currTweet);
			}
		}
		for (int j = 0; j < tweets.size(); j++) {
			String txt = tweets.get(j).getText().toLowerCase();
			for (int k = 0; k < txt.length(); k++) {
				if (txt.charAt(k) == '#') {
					int p = k;
					String tag = "";
					while (p < txt.length() && !Character.isWhitespace(txt.charAt(p))) {
						tag += txt.charAt(p);
						p++;
					}
					
					if (HT.containsHT(HTList, new HT(tag), new HTComparator())) {
						for (HT h : HTList) {
							if (h.getText().equals(tag)) {
								h.setFreq(h.getFreq() + 1);
							}
						}
					} 
					else {
						HTList.add(new HT(tag));
					}
					k = p;
				}
			}
		}
		Collections.sort(HTList);
		for (int k = 0; k < 10 && k < HTList.size(); k++) {
			System.out.println(HTList.get(k).getText());
		}
		System.out.println("\n");
	}
	
	public void tweetsPerHour() {
		List<Tweet> tweetMatches = new ArrayList<Tweet>();
		List<Date> dateList = new ArrayList<Date>();
		List<Date> lst = new ArrayList<Date>();
		Map<Date, Integer> map = new TreeMap<Date, Integer>();
		
		System.out.println("Enter text to search: ");
		Scanner sc = new Scanner(System.in);
		String txt = sc.nextLine();
		
		if (txt == null) {
			System.out.println("Did not read properly. Try again.");
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " Occurrence for: INVALID");
			tweetsPerHour();
		}
		
		String time = String.valueOf(System.currentTimeMillis());
		LogFile.write(time + " Occurrence for: " + txt);
		
		for (int i = 0; i < tweetList.size(); i++) {
			String msg = tweetList.get(i).getText();
			if (msg.contains(txt)) {
				tweetMatches.add(tweetList.get(i));
			}
		}
		
		for (int j = 0; j < tweetMatches.size(); j++) {
			Date date = tweetMatches.get(j).getDate();
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			date = cal.getTime();
			dateList.add(date);
			}
		
		for (int k = 0; k < dateList.size(); k++) {
			Date date1 = dateList.get(k);
			
			if (map.containsKey(date1)) {
				map.put(date1,  map.get(date1) + 1);
			} 
			else if (!map.containsKey(date1)){
				map.put(date1, 1);
			}
		}
		Set<Date> set = map.keySet();
		
		for (Date date2 : set) {
			lst.add(date2);
		}
		
		Collections.sort(lst, new Comparator<Date>() {
		    public int compare(Date date1, Date date2) {
		        if (date1.getTime() < date2.getTime())
		            return -1;
		        else if (date1.getTime() == date2.getTime())
		            return 0;
		        else
		            return 1;
		    }
		});
		for  (Date date3 : lst) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");
			System.out.println(sdf.format(date3) + " " + map.get(date3) + " times");
		}
		System.out.println("\n");
	}
}
