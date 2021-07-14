package capegroup.installer.utils;

import java.io.PrintStream;
import java.util.prefs.Preferences;

public class CheckAdmin {
    public static boolean admin() {
        Preferences prefs = Preferences.systemRoot();
        PrintStream systemErr = System.err;
        synchronized(systemErr) {
            System.setErr(null);
            try {
                prefs.put("foo", "bar");
                prefs.remove("foo");
                prefs.flush();
                return true;
            } catch (Exception e) {
                return false;
            } finally {
                System.setErr(systemErr);
            }
        }
    }
}
