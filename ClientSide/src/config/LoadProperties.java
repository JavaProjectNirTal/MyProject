package config;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoadProperties {				//reads the Client's Properties from an XML file
	private static final String FILE_NAME = "resources/Properties.xml";
	
	public static ClientProperties readProperties() {
		XMLDecoder decoder = null;
		File f = new File(FILE_NAME);
		try {
			if (f.isFile()) {
				decoder = new XMLDecoder(new FileInputStream(FILE_NAME));
				ClientProperties properties = (ClientProperties)decoder.readObject();
				return properties;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (f.isFile())
				decoder.close();
		}
		System.out.println("file doesn't exist");
		return null;
	}
}
