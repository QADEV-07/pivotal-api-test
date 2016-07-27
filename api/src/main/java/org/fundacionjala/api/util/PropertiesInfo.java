package org.fundacionjala.api.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This class is for get the properties defined into properties file
 *
 * @author RosarioGarcia
 */
public class PropertiesInfo {
    private static final Logger LOGGER = Logger.getLogger(PropertiesInfo.class.getSimpleName());

    private static PropertiesInfo instance;

    private Properties properties;

    private PropertiesInfo() {
        loadProperties();
    }

    public static PropertiesInfo getInstance() {
        if (instance == null) {
            instance = new PropertiesInfo();
        }
        return instance;
    }

    /**
     * This method is used to load the .properties file
     * which contains the configurations to project
     */
    private void loadProperties() {
        try {
            FileInputStream fileInputStream = new FileInputStream(Constants.CONFIG_PROPERTIES);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            LOGGER.warn("The properties file couldn't be found", e.getCause());
        } catch (IOException e) {
            LOGGER.warn("A problem of type", e.getCause());
        }
    }

    public String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    public String getEmail() {
        return getProperty(Constants.EMAIL);
    }

    public String getPassword() {
        return getProperty(Constants.PASSWORD);
    }

    public String getUrl() {
        return getProperty(Constants.URL);
    }

    public String getUrlApi() {
        return getProperty(Constants.URL_API);
    }

    public String getApiToken() {
        return getProperty(Constants.API_TOKEN);
    }

    public String getSauceUser() {
        return getProperty(Constants.REMOTE_USER);
    }

    public String getSauceAccessKey() {
        return getProperty(Constants.REMOTE_ACCESS_KEY);
    }

    public String getBrowser() {
        return getProperty(Constants.LOCAL_BROWSER);
    }

    public String getRemoteBrowser() {
        return getProperty(Constants.REMOTE_BROWSER);
    }

    public String getBrowserVersion() {
        return getProperty(Constants.REMOTE_BROWSER_VERSION);
    }

    public String getPlatform() {
        return getProperty(Constants.REMOTE_PLATFORM);
    }

    public String getProxy() {
        return String.format("http://%s:%s", getProxyHost(), getProxyPort());
    }

    public String getProxyHost() {
        return getProperty(Constants.PROXY_HOST);
    }

    public String getProxyPort() {
        return getProperty(Constants.PROXY_PORT);
    }

    public String getRemoteTestName() {
        return getProperty(Constants.REMOTE_TEST_NAME);
    }
}
