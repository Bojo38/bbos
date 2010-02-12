/*
 * dMeteo.java
 *
 * Created on November 26, 2007, 8:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model;

import bbos.Tools.bbTool;
import bbos.*;
import java.awt.*;
import java.io.Serializable;

/**
 *
 * @author moi
 */
public class dMeteo implements Serializable {

    public final static int METEO_NICE = 0;
    public final static int METEO_HEAT = 1;
    public final static int METEO_SUNNY = 2;
    public final static int METEO_RAIN = 3;
    public final static int METEO_BLIZZARD = 4;
    int _meteo;

    public int getMeteo() {
        return _meteo;
    }

    /** Creates a new instance of dMeteo */
    protected dMeteo() {
        _meteo = METEO_NICE;
    }

    /* public Image getMeteoPicture()
    {
    return _meteoPicture;
    }*/
    public void setMeteo(int meteo_roll) {
        /*
         * Meteo table
         */
        if (meteo_roll == 2) {
            _meteo = METEO_HEAT;
        } else {
            if (meteo_roll == 3) {
                _meteo = METEO_SUNNY;
            } else {
                if (meteo_roll == 11) {
                    _meteo = METEO_RAIN;
                } else {
                    if (meteo_roll == 12) {
                        _meteo = METEO_BLIZZARD;
                    } else {
                        _meteo = METEO_NICE;
                    }
                }
            }
        }

    }
    //transient Image _meteoPicture;
}
