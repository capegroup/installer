package capegroup.installer.utils;

public class GetHosts {
    static String hosts = "/etc/hosts";
    
    public static String hosts() {
        if (System.getProperty("os.name").startsWith("Windows")) {
            hosts = "C:\\Windows\\System32\\drivers\\etc\\hosts";
        }
        
		return hosts;
    }
}
