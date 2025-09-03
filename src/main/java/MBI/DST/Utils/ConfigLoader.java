package MBI.DST.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
	
	private static Properties props = new Properties();

	static {
		try {
			FileInputStream fis = new FileInputStream("src/main/java/MBI/DST/Resources/config.properties");
			props.load(fis);
			fis.close();
			System.out.println("Config loaded successfully.");
		} catch (IOException e) {
			System.err.println("Could not load config.properties. Using defaults where possible.");
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}

	public static String getOrDefault(String key, String defaultValue) {
		String value = props.getProperty(key);
		return (value != null && !value.trim().isEmpty()) ? value : defaultValue;
	}

	public static boolean has(String key) {
		String value = props.getProperty(key);
		return value != null && !value.trim().isEmpty();
	}


}
