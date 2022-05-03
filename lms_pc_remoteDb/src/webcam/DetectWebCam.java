package webcam;

import com.github.sarxos.webcam.Webcam;

public class DetectWebCam {
	public boolean webCamExists() {
		Webcam webcam = Webcam.getDefault();
		if (webcam != null) {
//			System.out.println("Webcam: " + webcam.getName());
			return true;
		} else {
//			System.out.println("No webcam detected");
			return false;
		}
	}

	public static void main(String[] args) {
		DetectWebCam dwc = new DetectWebCam();
		System.out.println(dwc.webCamExists());
	}
}