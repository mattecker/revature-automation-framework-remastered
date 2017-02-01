package SDET1611.testingframework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObjectFile {

	private static Properties properties = new Properties();
	
	public static Properties getObjectData(String filePath) throws IOException {
		//Read file objects
		InputStream stream = new FileInputStream(new File(filePath));
		//Load the objects
		properties.load(stream);
		
		return properties;
	}
}
