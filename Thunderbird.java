/******************************************************************************
 * Copyright (C) 2019 Eric Pogue.
 * 
 * This file and the ThunderbirdLite application is licensed under the 
 * BSD-3-Clause.
 * 
 * You may use any part of the file as long as you give credit in your 
 * source code.
 * 
 * This application utilizes the HttpRequest.java library developed by 
 * Eric Pogue
 * 
 * Version: 1.3
 *****************************************************************************/
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Container; 
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;

class ContactTile extends JPanel {
    private int red, green, blue;
    private ThunderbirdContact contactInSeat = null;

    private Boolean isAnAisle = false;
    public void setAisle() { isAnAisle = true; }

    ContactTile() {
        super();

        // Todo: Remove everything to do with random colors.
        // Todo: Implement visually appealing colors for aisles and seats.
        // HRP: IMPLEMENTED
        
    }

    ContactTile(ThunderbirdContact contactInSeatIn) {
        super();
        
        contactInSeat = contactInSeatIn;
    } 

     public void paintComponent(Graphics g) {
        super.paintComponent(g); 

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (isAnAisle) {
            g.setColor(new Color(192,192,192));
        } else {
            g.setColor(new Color(255,255,255));
        }
        
        g.fillRect (10, 10, panelWidth-20, panelHeight-20);

        g.setColor(new Color(0,0,0));

        final int fontSize=16;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        int stringX = (panelWidth/2)-80;
        int stringY = (panelHeight/2);
        if (contactInSeat != null) {

            // ToDo: Dispay preferred name instead of first and last name. 
            // HRP: IMPLEMENTED, but the online programming assignment says to print full name AND preferred name
            String firstLastPref = contactInSeat.getFirstName() + " " + contactInSeat.getLastName() + ", \n" + contactInSeat.getPreferredName();
            g.drawString(firstLastPref,stringX,stringY);
        }
    }

}

class ThunderBirdFrame extends JFrame implements ActionListener {
    private ArrayList<ContactTile> tileList;

    int[] aisleSeats = {1,2,3,4,5,6,7,8,9,10,12,15,18,19,21,22,23,24,27,28,30,33,36,37,39,40,41,42,43,44,45,46,48,51,54,55,57,58,59,60,63,64,66,69,72,73,74,75,76,77,78,79,80,81,82,83,84,87,88,89,90,91,92,93,96,97,98,99};

    public ThunderBirdFrame() {
        setBounds(100,100,1600,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton reverseView = new JButton("Reverse View");
        buttonPanel.add(reverseView);
        reverseView.addActionListener(this);

        JPanel contactGridPanel = new JPanel();
        contentPane.add(contactGridPanel, BorderLayout.CENTER);

        contactGridPanel.setLayout(new GridLayout(11,9));

        ThunderbirdModel tbM = new ThunderbirdModel();
        tbM.LoadIndex();
        tbM.LoadContactsThreaded();

        // Todo: Review ThunderbirdModel in detail and implement a multithreaded version of loading contacts. 
        // Hint: Review LoadContact() and LoadContactsThreaded() in detail.

        // HRP: IMPLEMENTED

        System.out.println("Printing Model:");
        System.out.println(tbM);
        tbM.ValidateContacts();   


        tileList = new ArrayList<ContactTile>();
        
        for(int i=0; i<99; i++) {
            ThunderbirdContact contactInSeat = tbM.findContactInSeat(i);
            if (contactInSeat != null) {
                System.out.println(contactInSeat);
            }

            ContactTile tile = new ContactTile(contactInSeat);

            // Todo: Place all the aisle seats in a array or an ArrayList instead of hard coding them. 
            // HRP: IMPLEMENTED

            for (int s: aisleSeats) {
                if (i+1 == s) {
                    tile.setAisle();
                }
            }

            tileList.add(tile);
            contactGridPanel.add(tile);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for(ContactTile tile : tileList) {
            // Todo: Remove randomization functionality and implement a visually appealing view of seats and aisles.
            // HRP: DONE

            // Todo: Implement reverse view where it looks like you are looking at the room from the back instead of the front of the room
        }

        repaint();
    }
}

// Todo: Rename the following class to Thunderbird.
// Hint: This will also require you to rename the Java file.
// HRP: IMPLEMENTED


public class Thunderbird {
    public static void main(String[] args) {

        // Todo: Update the following line so that it reflects the name change to Thunderbird.
        // HRP: DONE
        
        System.out.println("Thunderbird Starting...");

        ThunderBirdFrame myThunderBirdFrame = new ThunderBirdFrame();
        myThunderBirdFrame.setVisible(true);
    }
}