package SDET1611.testingframework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObjectFile {

	private static Properties properties = new Properties();

	/**
	 * Reads from a properties file into a properties object
	 * 
	 * @param filePath
	 *            The path to the properties file
	 * @return The properties object
	 * @throws IOException
	 *             If the file cannot be found
	 */
	public static Properties getObjectData(String filePath) throws IOException {
		InputStream stream = new FileInputStream(new File(filePath));
		properties.load(stream);

		return properties;
	}
}
