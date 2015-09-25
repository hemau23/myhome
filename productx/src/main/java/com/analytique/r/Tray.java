package com.analytique.r;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tray {
   PopupMenu popup=null;
   TrayIcon trayIcon=null;
    public static void main(String[] args) throws IOException {
        Tray tray = new Tray();
        File imageFile = new File(Tray.class.getResource("/image.png").getPath());
        Image image = ImageIO.read(imageFile);
        tray.run("hemant",image,new JFrame());
        tray.trayIcon.displayMessage("sadlklkd","sadsa", TrayIcon.MessageType.ERROR);


    }
    public void run(String name, Image image, final JFrame frame) {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        MouseAdapter mouseListener = new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == 1) {
                    frame.setVisible(true);
                    frame.setExtendedState(JFrame.NORMAL);
                }
            }
        };

        final SystemTray tray = SystemTray.getSystemTray();
        PopupMenu popup = menu(frame);
        trayIcon = new TrayIcon(image, name, popup);
        trayIcon.addMouseListener(mouseListener);
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }

    public PopupMenu menu(final JFrame frame) {
        ActionListener exitListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        ActionListener openListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                frame.setExtendedState(JFrame.NORMAL);
            }
        };
        popup = new PopupMenu();
        MenuItem aboutItem = new MenuItem("hemant");
        MenuItem openItem = new MenuItem("jaiswal");
        MenuItem exitItem = new MenuItem("sdf");
        openItem.addActionListener(openListener);
        exitItem.addActionListener(exitListener);
        popup.add(aboutItem);
        popup.add(openItem);
        popup.addSeparator();
        popup.add(exitItem);
        return popup;
    }
}