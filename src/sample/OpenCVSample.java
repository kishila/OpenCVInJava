package sample;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class OpenCVSample {

    public void processing() {
        System.out.println("Start.");

        // Read image.
        File inputFile = new File("src/img/lenna.jpg");
        System.out.println(
        String.format("Read %s.", inputFile.getName()));
        Mat image = Imgcodecs.imread(
                inputFile.getAbsolutePath());
        if (image == null) {
            throw new IllegalArgumentException("Illegal input file.");
        }
        File settingFile =
                new File("src/data/haarcascades/haarcascade_frontalface_default.xml");
        if (!settingFile.exists()) {
            throw new RuntimeException("No setting file.");
        }
        MatOfRect faces = new MatOfRect();
        CascadeClassifier faceDetector = new CascadeClassifier(
                settingFile.getAbsolutePath());
        faceDetector.detectMultiScale(image, faces);

        System.out.println(
            String.format("Detected %s faces.",
                    faces.toArray().length));

        // Draw a bounding box around each face.
        for (Rect rect : faces.toArray()) {
            Imgproc.rectangle(
                    image,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }

        // Save the result image of detection.
        String filename = "src/img/sample-face.jpg";
        System.out.println(String.format("Write %s", filename));
        Imgcodecs.imwrite(filename, image);
        System.out.println(String.format("done."));
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new OpenCVSample().processing();
    }
}