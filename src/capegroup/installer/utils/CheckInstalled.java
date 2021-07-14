package capegroup.installer.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CheckInstalled {
    public static boolean installed() {
    	
		try {
			List<String> lines = Files.readAllLines(Paths.get(GetHosts.hosts()));
	    	boolean result = false;
	    	
	    	for(String line: lines) {
	    		if(line.contains("54.37.139.51 s.optifine.net")) {
	    			result = true;
	    		}
	    	};
	    	
	    	return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

    }
}
