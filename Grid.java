package com.company;
import javax.swing.*;
import java.awt.*;



public class Grid {
    Card selectedCard;
    int selectedPlayer;
    int currentTurn;
    JButton displayButton;
    JTextField textBox;
    JFrame frame;
    GridBagConstraints gbc;
    Player[] players;
    final String[] types = new String[] {"BAT","RAT","TOAD","ROACH","FLY","SCORPION","SPIDER","STINKBUG"};

    public Grid(Player[] players) {
        this.currentTurn = 0;
        this.players = players;
        frame = new JFrame();
        gbc = new GridBagConstraints();
        frame.setSize(1480,1020);
        gbc.insets = new Insets(0, 0, 0, 0);
        frame.getContentPane().setLayout(new GridBagLayout());
        displayPlayers();
        addButtons();
    }

    void displayPlayers() {
        for (Player player: players) {

            displayDiscard(player);

        }
        displayHand(players[0]);
    }

    void displayDiscard(Player player) {
        for (int i = 0; i < 8; i++) {
            Card a = player.defaultHand[i];

            ImageIcon image = new ImageIcon(a.getImage());
            RotatedIcon ri = new RotatedIcon(image, player.degrees);
            a.button.setText("" + player.map.get(a.getType()));
            a.button.setHorizontalTextPosition(SwingConstants.CENTER);
            switch (player.number) {
                case 1-> a.button.setVerticalTextPosition(SwingConstants.TOP);
                case 2-> a.button.setHorizontalTextPosition(SwingConstants.LEFT);
                case 3-> a.button.setVerticalTextPosition(SwingConstants.BOTTOM);
                case 4-> a.button.setHorizontalTextPosition(SwingConstants.RIGHT);
            }

            a.button.setIcon(ri);
            a.button.setBorderPainted(false);
            a.button.setOpaque(false);
            a.button.setContentAreaFilled(false);
            a.button.setBorderPainted(false);
            a.button.setVisible(true);

            //if (player.number == 1) addCardClick(a.button);

            gbc.gridx = player.startx;
            gbc.gridy = player.starty + i;
            if (player.number % 2 == 1) {
                gbc.gridx = player.startx + i;
                gbc.gridy = player.starty;
            }
            frame.add(a.button, gbc);
        }
    }

    void displayHand(Player player) {
        //display p1 hand (bottom)
        //print default hand
        for (int i=0;i<player.hand.size();i++) {
            Card a = player.hand.get(i);
            ImageIcon image = new ImageIcon(a.getImage());
            RotatedIcon ri = new RotatedIcon(image, player.degrees);
            a.button.setIcon(ri);
            a.button.setBorderPainted(false);
            a.button.setOpaque(false);
            a.button.setContentAreaFilled(false);
            a.button.setBorderPainted(false);
            a.button.setVisible(true);
            gbc.gridx = player.startx+i;
            gbc.gridy = player.starty+1;
            if (i>7) {
                gbc.gridx -= 8;
                gbc.gridy++;
            }
            addCardClick(a);
            frame.add(a.button, gbc);

        }
    }


    void addCardClick(Card a) {
        a.button.addActionListener(e ->{
            displayButton.setIcon(a.button.getIcon());
            selectedCard = a;
        });
    }

    void addButtons() {
        addPlayerSelect();
        addInputBox();
        //addTorLie();
        addSubmit();
        addDisplay();
    }

    void addPlayerSelect() {
        gbc.gridx = 14;
        gbc.gridy = 2;

        JButton[] playerButtons = new JButton[3];
        for (int i=0;i< players.length-1;i++) {
            final int temp = players[i+1].number;
            JButton button = new JButton("Player " + temp);
            frame.add(button, gbc);
            gbc.gridx++;
            playerButtons[i] = button;
            button.addActionListener(e -> {
                selectedPlayer = temp-1;
                System.out.println(temp);
            });
            frame.setVisible(true);
        }
    }

    void addInputBox() {
        gbc.gridx = 15;
        gbc.gridy = 3;

        //gbc.gridwidth = 3;
        gbc.fill = 2;

        textBox = new JTextField();
        Font font = textBox.getFont().deriveFont(Font.PLAIN, 25f);
        textBox.setFont(font);
        frame.getContentPane().add(textBox, gbc);
        frame.setVisible(true);

        gbc.gridwidth = 1;
        gbc.fill = 0;

    }

    void addTorLie(boolean isTrue) {
        gbc.gridx = 14;
        gbc.gridy = 4;
        JButton TruthB = new JButton("Truth");
        JButton LieB = new JButton("Lie");
        frame.getContentPane().add(TruthB, gbc);
        gbc.gridx+=2;
        frame.getContentPane().add(LieB, gbc);
        TruthB.addActionListener(e -> {
            frame.remove(TruthB);
            frame.remove(LieB);
            endTurn(true, isTrue);
        });
        LieB.addActionListener(e -> {
            frame.remove(TruthB);
            frame.remove(LieB);
            endTurn(false, isTrue);
        });
        frame.setVisible(true);
    }

    void addSubmit() {
        gbc.gridx = 15;
        gbc.gridy = 5;
        JButton submitB = new JButton("Submit");
        submitB.addActionListener(e -> {
            if ((currentTurn != -1) && (selectedCard != null)) {
                finishTurn();
            }

        });
        frame.getContentPane().add(submitB, gbc);
        frame.setVisible(true);
    }

    boolean checkSameString(String a, String b) {
        if (a.length() != b.length()){
            return false;
        }
        for (int i=0;i<a.length();i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    boolean getTruth() {
        String cardName = textBox.getText();
        for (String cardType : types) {
            if (checkSameString(cardName, cardType)) {
                return (checkSameString(cardType, selectedCard.cardType));
            }
        }
        return false;
    }

    boolean isTrueforAny() {
        if (currentTurn == 0) {
            return getTruth();
        }
        else {
            int random2 = (int) (Math.random()*2);
            boolean isTrue = random2 == 1;
            String displayString = selectedCard.getType();
            if (!isTrue) {
                for (Card card: players[currentTurn].defaultHand) {
                    if (!checkSameString(displayString, card.getType())) {
                        displayString = card.getType();
                        break;
                    }
                }
            }
            textBox.setText(displayString);
            return isTrue;
        }
    }

    void endTurn(boolean guess, boolean isTrue) {
        players[currentTurn].hand.remove(selectedCard);
        boolean gameOver = false;

        //selected player is correct
        if (guess == isTrue) {
            gameOver = players[currentTurn].takeCard(selectedCard);
        } else // current player is correct
        {
            gameOver = players[selectedPlayer].takeCard(selectedCard);
            currentTurn = selectedPlayer;
        }

        System.out.println("Guess: " + (isTrue == guess));

        System.out.println(currentTurn);
        System.out.println(selectedPlayer);
        if (gameOver) {
            frame.dispose();
            gameDone();
            return;
        }

        selectedCard = null;
        //should be null
        selectedPlayer = -1;
        frame.dispose();

        frame = new JFrame();
        gbc = new GridBagConstraints();
        frame.setSize(1480,1020);
        gbc.insets = new Insets(0, 0, 0, 0);
        frame.getContentPane().setLayout(new GridBagLayout());

        displayPlayers();
        if (currentTurn == 0) {
            addButtons();
        } else {
            addDisplay();
            finishTurn();
        }
    }

    void finishTurn() {

        if (currentTurn == 0) {
            if ((selectedPlayer == -1) || (selectedCard == null)) {
                return;
            }
        }

        Player currentPlayer = players[currentTurn];
        //selected Card -instance
        if (currentPlayer.number != 1) {
            selectedCard = currentPlayer.hand.get(0);
        }

        //Which player giving to -instance
        if (currentPlayer.number != 1) {
            selectedPlayer = (int) (Math.random()*4);
            if (selectedPlayer == currentTurn) {
                selectedPlayer--;
            }
        }

        //is Current player lying -textBox + getTruth
        final boolean isTrue = isTrueforAny();

        //target player guess
        if (selectedPlayer == 0) {
            addTorLie(isTrue);
        }
        else {
            int random3 = (int) (Math.random()*2);
            boolean compTruthorLie = random3 == 1;
            endTurn(compTruthorLie, isTrue);
        }
    }

    void addDisplay() {
        gbc.gridx = 15;
        gbc.gridy = 7;
        Card a = new Card(-1, "");
        ImageIcon image = new ImageIcon(a.getImage());
        //RotatedIcon ri = new RotatedIcon(image, p3.degrees);
        a.button.setIcon(image);
        a.button.setBorderPainted(false);
        a.button.setOpaque(false);
        a.button.setContentAreaFilled(false);
        a.button.setBorderPainted(false);
        a.button.setVisible(true);
        frame.add(a.button, gbc);
        frame.setVisible(true);
        displayButton = a.button;

        selectedCard = a;
    }

    void gameDone() {
        frame = new JFrame();
        gbc = new GridBagConstraints();
        frame.setSize(750,500);
        gbc.insets = new Insets(0, 0, 0, 0);
        frame.getContentPane().setLayout(new GridBagLayout());
        JButton playerLoses = new JButton("Player " + selectedPlayer + " loses");
        frame.add(playerLoses, gbc);
        frame.setVisible(true);
    }
}
