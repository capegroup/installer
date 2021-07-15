/*
    capegroup - a cape service, without the controversy
    Copyright (C) 2021 capegroup

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package capegroup.installer;

import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.IntelliJTheme;

import capegroup.installer.utils.CheckAdmin;
import capegroup.installer.utils.CheckInstalled;
import capegroup.installer.utils.Listeners;

public class Main extends JPanel {
	private static final long serialVersionUID = -4740404846581449373L;
	
	public JButton install = new JButton("install");
	public JLabel title = new JLabel("capegroup");
	public JButton uninstall = new JButton("uninstall");
	public JLabel status = new JLabel(CheckInstalled.installed() ? "CG is installed!" : "CG isn't installed!");
	public JButton twitter = new JButton("twitter");
	public JButton discord = new JButton("discord");
	public JLabel support_text = new JLabel ("support here >>");

	public Main() {
		this.setPreferredSize(new Dimension (237, 162));
		this.setLayout(null);

     	this.add(install);
     	this.add(title);
     	this.add(uninstall);
     	this.add(status);
     	this.add(twitter);
     	this.add(discord);
      	this.add(support_text);

      	install.setBounds (5, 60, 100, 25);
      	title.setBounds (85, 5, 100, 25);
      	uninstall.setBounds (5, 30, 100, 25);
      	status.setBounds (115, 45, 100, 25);
      	twitter.setBounds (120, 120, 100, 25);
      	discord.setBounds (5, 120, 100, 25);
      	support_text.setBounds (70, 90, 100, 25);
  }


  public static void main(String[] args) {
	  try {
		  UIManager.setLookAndFeel(IntelliJTheme.createLaf( Main.class.getResourceAsStream("/capegroup/installer/theme.json")));
	  } catch(Exception ex) {
		  System.err.println("Failed to initialize LaF");
	  }
	  	
	  if(CheckAdmin.admin()) {
	      JFrame frame = new JFrame("CG");
	      Main main = new Main();
	      
	      try {
			frame.setIconImage(ImageIO.read(Main.class.getResourceAsStream("/capegroup/installer/icon.png")));
	      } catch (IOException e) {
			e.printStackTrace();
		  }
	      
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().add(main);
	      frame.pack();
	      
	      Listeners.setupActions(main);
	      Listeners.setupSocial(main);
	      frame.setVisible (true);
	  } else {
		  JOptionPane.showMessageDialog(null, "Start this application in administrator/sudo mode.");
          System.exit(1);
	  }
  }
}
