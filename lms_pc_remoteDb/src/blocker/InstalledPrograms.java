package blocker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class InstalledPrograms {
	public ArrayList<String> getInstalledPrograms() {
		File f = null;
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder("");
		ArrayList<String> str_list = null;
		try {
			String c = "Get-ItemProperty -Path 'HKLM:\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths\\*'  "
					+ "|Where-Object {$_.'(default)' -ne $null} "
					+ "|Select-Object @{ expression={$_.PSChildName}; label='Programs'} " + "| Format-Table -Autosize";
			String command = "powershell -command \"" + c + "\"";
			Process p = Runtime.getRuntime().exec(command);
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			str_list = new ArrayList<String>();
			String line = "";
			while ((line = reader.readLine()) != null) {
				str_list.add(line);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return str_list;
	}

	public static void main(String[] args) {
		InstalledPrograms p = new InstalledPrograms();
		ArrayList<String> str_list = p.getInstalledPrograms();
		for (String s : str_list) {
			System.out.println(s);
		}
	}
}
