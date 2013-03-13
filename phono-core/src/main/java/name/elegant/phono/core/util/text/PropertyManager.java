package name.elegant.phono.core.util.text;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author hezeqing.pt
 */
public class PropertyManager {

    private static PropertyManager manager = null;
    private static Object managerLock = new Object();
    private Properties properties;
    private Object propertiesLock;
    private String resourceURI;

    public static String getProperty(String propsName, String name) {
        if (manager == null || !manager.resourceURI.equals(propsName)) {
            synchronized (managerLock) {
                if (manager == null || !manager.resourceURI.equals(propsName)) {
                    manager = new PropertyManager(propsName);
                }
            }
        }
        return manager.getProp(name);
    }

    public static String getProperty(String propsName, String name, String def) {
        String value = getProperty(propsName, name);
        return value == null ? def : value;
    }

    public static int getIntProperty(String propsName, String name) {
        String value = getProperty(propsName, name);
        return Integer.parseInt(value);
    }

    public static int getIntProperty(String propsName, String name, int def) {
        String value = getProperty(propsName, name);
        return value == null ? def : Integer.parseInt(value);
    }

    public static long getLongProperty(String propsName, String name) {
        String value = getProperty(propsName, name);
        return Long.parseLong(value);
    }

    public static long getLongProperty(String propsName, String name, long def) {
        String value = getProperty(propsName, name);
        return value == null ? def : Long.parseLong(value);
    }

    public static boolean getBooleanProperty(String propsName, String name) {
        String value = getProperty(propsName, name);
        return Boolean.parseBoolean(value);
    }

    public static boolean getBooleanProperty(String propsName, String name,
                                             boolean def) {
        String value = getProperty(propsName, name);
        return value == null ? def : Boolean.parseBoolean(value);
    }

    private PropertyManager(String resourceURI) {
        properties = null;
        propertiesLock = new Object();
        this.resourceURI = resourceURI;
    }

    protected String getProp(String name) {
        if (properties == null) {
            synchronized (propertiesLock) {
                if (properties == null) {
                    loadProps();
                }
            }
        }
        String property = properties.getProperty(name.trim());
        if (property == null) {
            return null;
        } else {
            return property.trim();
        }
    }

    private void loadProps() {
        properties = new Properties();
        InputStream in = null;
        try {
            in = getClass().getClassLoader().getResource(resourceURI)
                    .openStream();
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
