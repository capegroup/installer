package capegroup.installer;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.IntelliJTheme;

public class Main {
    static String hosts = "/etc/hosts";

    public static boolean isAdmin() {
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

    public static void main(String[] args) throws IOException {
        if (System.getProperty("os.name").startsWith("Windows")) {
            hosts = "C:\\Windows\\System32\\drivers\\etc\\hosts";
        }

        if (isAdmin()) {

        	try {
        	    UIManager.setLookAndFeel(IntelliJTheme.createLaf( Main.class.getResourceAsStream("/capegroup/installer/theme.json")));
        	} catch( Exception ex ) {
        	    System.err.println( "Failed to initialize LaF" );
        	}

            JFrame frame = new JFrame("CG");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 150);

            // Create label
            JLabel label = new JLabel("Welcome to CapeGroup!", JLabel.CENTER);
            
            // Create status
            JLabel status = new JLabel(checkInstalled() ? "CapeGroup is installed!" : "CapeGroup isn't installed!", JLabel.CENTER);

            // Create buttons
            JPanel panel = new JPanel();

            JButton install = new JButton("Install");
            JButton uninstall = new JButton("Uninstall");
            
            // Add listeners
            uninstall.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {

                    try {
                    	FileWriter writer = removeOptifine();
                        writer.close();
                        
                        status.setText("CapeGroup isn't installed!");
                    } catch (Exception e) {e.printStackTrace();}
                }
            });

            install.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {

                    try {
                    	FileWriter writer = removeOptifine();
                    	writer.write("54.37.139.51 s.optifine.net # hello this is capegroup we installed this\n");
                    	writer.close();
                    	
                    	status.setText("CapeGroup is installed!");
                    } catch (Exception e) {e.printStackTrace();}
                }
            });
            
            // Add buttons to panel
            panel.add(install);
            panel.add(uninstall);
            

            
            // Setup buttons
            frame.add(panel);

            frame.getContentPane().add(BorderLayout.SOUTH, panel);
            
            // Setup label
            frame.add(label);

            frame.getContentPane().add(BorderLayout.NORTH, label);
            
            // Setup status
            frame.add(status);

            frame.getContentPane().add(BorderLayout.CENTER, status);
            

            
            // check if is installed
            
            // Set app in the middle of the screen
            frame.setLocationRelativeTo(null);

            // Turn on
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Start this application in Administrator/Sudo mode.");
            System.exit(1);
        }
    }
    
    public static boolean checkInstalled() throws IOException {
    	List<String> lines = Files.readAllLines(Paths.get(hosts));
    	boolean result = false;
    	
    	for(String line: lines) {
    		if(line.contains("54.37.139.51 s.optifine.net")) {
    			result = true;
    		}
    	};
    	
    	return result;
    }
    public static FileWriter removeOptifine() throws IOException {
    	List<String> lines = Files.readAllLines(Paths.get(hosts));
        ArrayList<String> finalLines = new ArrayList<String>();
        
        // Parse lines
        lines.forEach(line -> {
            if(!line.contains("s.optifine.net")) {
            	finalLines.add(line);
            }
        });
        
        // Write lines
        FileWriter writer = new FileWriter(hosts);
            
        finalLines.forEach(string -> {
        	try {
        		writer.write(string + "\n");
        	} catch (Exception e) {}
        });
        
        // Return writer for later usage (also closing)
        return writer;
    }
    public static void openURL(String url) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}