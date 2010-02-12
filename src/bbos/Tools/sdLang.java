/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbos.Tools;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author frederic
 */
public class sdLang {

    private Locale _locale=Locale.FRANCE;
    private ResourceBundle _resources;
    
    private static sdLang _singleton=null;
    
    private sdLang()
    {
        _resources=ResourceBundle.getBundle("resources.text.lang", _locale);
    }
    
    public static sdLang getSingleton()
    {
        if (_singleton==null)
        {
            _singleton=new sdLang();
        }
        return _singleton;
    }
    
    public void setLocale(Locale l)
    {
        _locale=l;
        _resources=ResourceBundle.getBundle("resources.text.lang", _locale);
    }
    
    public String getResource(String key)
    {
        try
        {
        key=key.replace(" ", "_");
        return _resources.getString(key);
        }
        catch (java.util.MissingResourceException e)
        {
            return key;
        }
    }
}
