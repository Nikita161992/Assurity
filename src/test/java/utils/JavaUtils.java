package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JavaUtils {

    public static String readProperties(String propName) throws IOException {
        FileInputStream fi = new FileInputStream("C:\\Users\\NIKITA\\IdeaProjects\\assurity\\src\\test\\resources\\application.properties");
        Properties prop = new Properties();
        prop.load(fi);
        return prop.getProperty(propName);
    }
}
