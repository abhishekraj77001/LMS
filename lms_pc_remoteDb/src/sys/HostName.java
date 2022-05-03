package sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HostName {
	ProcessBuilder pb;
	Process p;
	StringBuilder sb;
	BufferedReader reader;
	String new_line;

	public StringBuilder getHostName() {
		new_line = System.getProperty("line.separator");
		pb = new ProcessBuilder("hostname");
		try {
			p = pb.start();
			int op = p.waitFor();
//			System.out.println("return="+op);  //if 0 ,then successful
//			System.out.println("command executed with any errors? " + (op == 0 ? "No" : "Yes"));
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			sb = new StringBuilder();
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line + new_line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return sb;
	}

	public static void main(String[] args) {
		HostName hn = new HostName();
		System.out.println(hn.getHostName());
	}
}