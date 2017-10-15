import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
public class ApplicationConfiguration {
    private final static ApplicationConfiguration INSTANCE = new ApplicationConfiguration();
 
    public static ApplicationConfiguration getInstance() {
        return INSTANCE;
    }
 
    private static Properties configuration = new Properties();
 
    private static Properties getConfiguration() {
        return configuration;
    }
 
    public void initilize(final String file) {
        InputStream in = null;
        try {
            in = new FileInputStream(new File(file));
            configuration.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public String getConfiguration(final String key) {
        return (String) getConfiguration().get(key);
    }
 
    public String getConfigurationWithDefaultValue(final String key,
            final String defaultValue) {
        return (String) getConfiguration().getProperty(key, defaultValue);
    }
}


