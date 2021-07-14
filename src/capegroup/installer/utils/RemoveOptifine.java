package capegroup.installer.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RemoveOptifine {
    public static FileWriter remove() throws IOException {
    	List<String> lines = Files.readAllLines(Paths.get(GetHosts.hosts()));
        ArrayList<String> finalLines = new ArrayList<String>();
        
        // Parse lines
        lines.forEach(line -> {
            if(!line.contains("s.optifine.net")) {
            	finalLines.add(line);
            }
        });
        
        // Write lines
        FileWriter writer = new FileWriter(GetHosts.hosts());
            
        finalLines.forEach(string -> {
        	try {
        		writer.write(string + "\n");
        	} catch (Exception e) {}
        });
        
        // Return writer for later usage (also closing)
        return writer;
    }
}
