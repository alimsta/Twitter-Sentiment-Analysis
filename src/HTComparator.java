package edu.upenn.cis350.hwk1;

import java.util.Comparator;

public class HTComparator implements Comparator<HT> {
	public int compare(HT tag1, HT tag2) {
		if (tag1.getFreq() > tag2.getFreq()) {
			return 1;
		} 
		else if (tag1.getFreq() < tag2.getFreq()) {
			return -1;
		} 
		else {
			return 0;
		}
	}
	
	public boolean equals(HT tag1, HT tag2) {
		return tag1.getText().equals(tag2.getText());
	}
}
