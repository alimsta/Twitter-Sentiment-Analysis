package edu.upenn.cis350.hwk1;

import java.util.List;

public class HT implements Comparable<HT>{
	private String text;
	private int frequency;
	
	public HT(String txt){
		text = txt;
		frequency = 1;
	}
	
	public String getText() {
		return text;
	}
	public int getFreq() { 
		return frequency;
	}
	
	public void setFreq(int n) {
		frequency = n;
	}
	
	public int compareTo(HT ht2) {
		return ht2.getFreq() - this.getFreq();
	}
	
	public static boolean containsHT(List<HT> lst, HT tag, HTComparator c) {
		for (HT h : lst) {
			if (c.equals(h, tag)) {
				return true;
			}
		}
		return false;
	}
}
