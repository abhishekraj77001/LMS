package date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentDateTime {
	public String getCurrentDateTime() {
		String dt;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		dt = dtf.format(now);
		return dt;
	}

	public static void main(String[] args) {
		CurrentDateTime dt = new CurrentDateTime();
		System.out.println(dt.getCurrentDateTime());
	}
}
