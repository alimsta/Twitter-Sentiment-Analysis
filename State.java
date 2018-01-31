package edu.upenn.cis350.hwk1;

import java.util.*;

public class State implements Comparable<State>{
	private String name;
	private double x;
	private double y;
	private int tweetCount;
	
	public State(String txt, double xcoord, double ycoord) {
		name = txt;
		x = xcoord;
		y = ycoord;
		tweetCount= 0;
	}
	
	public String getName() {
		return name;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getTweetCount() {
		return tweetCount;
	}
	
	public void setTweetCount(int n) {
		tweetCount = n;
	}
	
	public int compareTo(State s) {
		return s.getTweetCount() - this.getTweetCount();
	}
	
	public boolean equals(State s) {
		return this.getName().equals(s.getName());
	}
}
