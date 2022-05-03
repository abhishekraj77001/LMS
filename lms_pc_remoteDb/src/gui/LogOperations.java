package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LogOperations {
	File f;
	FileReader fr;
	FileWriter fw;
	StringBuilder sb;
	PrintWriter pw;
	BufferedReader br;

	public ArrayList<String> readFromFile(String fileName) {
		ArrayList<String> str = new ArrayList<String>();
		try {
			f = new File(fileName);
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String s;
			while ((s = br.readLine()) != null) {
				str.add(s);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			return str;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return str;
	}

	public void writeToFile(String fileName, String... str) {
		try {
			f = new File(fileName);
			fw = new FileWriter(f);
			pw = new PrintWriter(fw);
			for (String s : str) {
				pw.println(s);
			}
			pw.close();
			fw.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
