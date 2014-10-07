/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package virtual.eyetracker;

import java.io.File;

/**
 *
 * @author rajin
 */
public class Util {
    public static final int INVALID =-1;
    public static final String HOST ="127.0.0.1";
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
