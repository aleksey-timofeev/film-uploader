package com.alextim.fileuploader.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * Utility class that works with a settings.property file
 *
 */
public class FileUploaderSettings {
    private static final String SETTINGS_FILE_NAME = "settings.properties";
    
    protected static final String UPLOAD_DIRECTORY_KEY = "uploadDirectory";

    private final Logger log = Logger.getLogger(FileUploaderSettings.class);
    
    private Properties properties;
    

    /**
     * 
     * Reads a settings.property file to the properties {@link Properties} list 
     * 
     */
    private void readFile() {
        if (getProperties() == null) {
            this.properties = new Properties();
            
            InputStream settingsStream = null;
            try {
                settingsStream = getResourceStream();
                this.properties.load(settingsStream);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } finally {
                if (settingsStream != null) { try {settingsStream.close();} catch (IOException e) {/*nop*/}}
            }
        }
    }

    /**
     * 
     * Reads upload directory key from the properties {@link Properties} list 
     *  
     * @return the key's value 
     * 
     */
    public String getUploadDirectory() {
        return readValue(UPLOAD_DIRECTORY_KEY);
    }


    /**
     * 
     * Reads value by a key from the properties {@link Properties} list
     * 
     * @return  the key's value 
     * 
     */
    private String readValue(String name) {
        if (getProperties() == null) {
            readFile();
        }
        return  getProperties().getProperty(name);
    }
    
    /**
     * 
     * The method for the unit tests only
     * 
     * @return the properties {@link Properties} list
     */
    protected Properties getProperties() {
        return properties;
    }

    
    /**
     * 
     * The method for the unit test only
     * 
     * @return a settings file resource {@link InputStream}
     */
    protected InputStream getResourceStream() {
        return getClass().getResourceAsStream(SETTINGS_FILE_NAME);
    }

}
