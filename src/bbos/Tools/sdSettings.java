/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Tools;

import bbos.MainBBoS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author root
 */
public class sdSettings {

    private static sdSettings _singleton = null;
    private Properties _data;

    private sdSettings() {
        _data = new Properties();
        try {
            String userDir = System.getProperty("user.dir");
            _data.load(new FileInputStream(userDir + "/bbos.settings.properties"));
            return;
        } 
        catch (Exception e) {
            
        }
        try {
            _data.load(getClass().getResourceAsStream("/resources/text/settings.properties"));
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static sdSettings getSingleton() {
        if (_singleton == null) {
            _singleton = new sdSettings();
        }
        return _singleton;
    }

    public void setProperty(String key, String value) {
        _data.setProperty(key, value);
    }

    public String getProperty(String key) {
        return _data.getProperty(key);
    }

    public boolean saveProperties() {
        // Create temp file.
        try {
            String userDir = System.getProperty("user.dir");
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            _data.store(new FileOutputStream(userDir + "/bbos.settings.properties"), sdf.format(cal.getTime()));

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
