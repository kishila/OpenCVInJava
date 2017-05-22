import org.opencv.core.Core;

import sample.Convert;


public class Main {
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


	public static void main(String[] args) {
		Convert.convertGray();
	}

}
