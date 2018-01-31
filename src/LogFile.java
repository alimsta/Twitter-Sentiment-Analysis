package edu.upenn.cis350.hwk1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile {
	public static void write(String s) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(Main.logFile, true));
			bw.write(s+"\n");
			bw.close();
		}
		catch (IOException e) {
			System.out.println("Error writing to log file.");
		}
	}
}
