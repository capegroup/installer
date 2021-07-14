package capegroup.installer.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import capegroup.installer.Main;

public class Listeners {
	public static void setupActions(Main main) {
        main.uninstall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                try {
                	FileWriter writer = RemoveOptifine.remove();
                    writer.close();
                    
                    main.status.setText("CG isn't installed!");
                } catch (Exception e) {e.printStackTrace();}
            }
        });

        main.install.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                try {
                	FileWriter writer = RemoveOptifine.remove();
                	writer.write("54.37.139.51 s.optifine.net # hello this is capegroup we installed this\n");
                	writer.close();
                	
                	main.status.setText("CG is installed!");
                } catch (Exception e) {e.printStackTrace();}
            }
        });
	}
	
	public static void setupSocial(Main main) {
		main.discord.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {OpenUrl.url("https://discord.gg/y9BUsHSREg");}
		});
		
		main.twitter.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {OpenUrl.url("https://twitter.com/cap3gr0up");}
		});
	}
}
