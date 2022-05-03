package webcam;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.github.sarxos.webcam.Webcam;

public class TakePicture {
	public TakePicture(File f) {
		// get default webcam and open it
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		// get image
		BufferedImage image = webcam.getImage();
		// save image to PNG file
		try {
			ImageIO.write(image, "PNG", f);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String dt;
		dt = new date.CurrentDateTime().getCurrentDateTime();
		TakePicture tp = new TakePicture(new File("C:\\Users\\Public\\lms_pc\\Logs\\" + dt + ".png"));
	}
}