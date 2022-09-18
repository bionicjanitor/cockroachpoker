package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

public class Card {
    JButton button;
    private CardType type;
    private Image img;
    String cardType;

    public String getType() {
        return cardType;
    }

    public void setType(CardType type) {
        this.type = type;
        addImage();
    }

    public Card(CardType type)
    {
        this.type = type;
        addImage();
    }

    public Card(int a, String cardType)
    {
        this.cardType = cardType;
        this.button = new JButton();
        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(type);
            }
        });
        if (a == 0) {this.setType(CardType.BAT);}
        if (a == 1) {this.setType(CardType.RAT);}
        if (a == 2) {this.setType(CardType.TOAD);}
        if (a == 3) {this.setType(CardType.ROACH);}
        if (a == 4) {this.setType(CardType.FLY);}
        if (a == 5) {this.setType(CardType.SCORPION);}
        if (a == 6) {this.setType(CardType.SPIDER);}
        if (a == 7) {this.setType(CardType.STINKBUG);}
        if (a == 8) {this.setType(CardType.gBAT);}
        if (a == 9) {this.setType(CardType.gRAT);}
        if (a == 10) {this.setType(CardType.gTOAD);}
        if (a == 11) {this.setType(CardType.gROACH);}
        if (a == 12) {this.setType(CardType.gFLY);}
        if (a == 13) {this.setType(CardType.gSCORPION);}
        if (a == 14) {this.setType(CardType.gSPIDER);}
        if (a == 15) {this.setType(CardType.gSTINKBUG);}
        if (a == -1) {this.setType(CardType.BACK);}
        addImage();
    }

    public void addImage() {
        try{
            this.img = ImageIO.read(getClass().getResource(CardType.getFilePath(type)));
            ImageIcon image = new ImageIcon(this.img);
            button.setIcon(image);
            button.setBorderPainted(false);
        }
        catch(IOException e)
        {

        }
    }
    public Image getImage() {
        return this.img;
    }
}
