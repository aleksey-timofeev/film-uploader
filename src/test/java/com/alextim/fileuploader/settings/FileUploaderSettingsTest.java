package com.alextim.fileuploader.settings;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * {@link FileUploaderSettings} test
 * 
 * @author Home
 *
 */
public class FileUploaderSettingsTest {

    private static final Object UPLOAD_DIRECTORY_VALUE = "G:/entertaiment/";

    private FileUploaderSettings testGetValues;
    private FileUploaderSettings testReadFileSettings;
    
    private Properties properties;
    
    @Before
    public void setUp() {
        this.properties = new Properties();
        this.properties.put(FileUploaderSettings.UPLOAD_DIRECTORY_KEY, UPLOAD_DIRECTORY_VALUE);
        
        this.testGetValues = new FileUploaderSettings() {
            protected Properties getProperties() {
                return properties;
            }
        };

        
        this.testReadFileSettings = new FileUploaderSettings() {
            protected InputStream getResourceStream() {
                String buf = String.format("%s=%s", FileUploaderSettings.UPLOAD_DIRECTORY_KEY, UPLOAD_DIRECTORY_VALUE);
                ByteArrayInputStream bais = new ByteArrayInputStream(buf.getBytes());
                
                return bais;
            }

        };
    }
    
    @Test
    public void testUploadDirectory() {
        String value = this.testGetValues.getUploadDirectory();
        Assert.assertEquals(UPLOAD_DIRECTORY_VALUE, value);
    }
    
    
    @Test
    public void testReadFile() throws IOException {
        String value = testReadFileSettings.getUploadDirectory();
        Assert.assertEquals(UPLOAD_DIRECTORY_VALUE, value);
    }
}
