package edu.upenn.cis350.hwk1;

import java.util.*;
import java.text.SimpleDateFormat;;

public class Tweet{
	private String text;
	private double x;
	private double y;
	private Date date;

	public Tweet(String msg, double xcoord, double ycoord, Date timestamp) {
		text = msg;
		x = xcoord;
		y = ycoord;
		date = timestamp;
	}
	
	public String getText() {
		return text;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Date getDate() {
		return date;
	}
	
}
