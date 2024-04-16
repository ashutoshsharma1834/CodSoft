
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Guess extends JFrame {
    JTextField t1, t2, t3, t4;
    JLabel j4;
    ButtonListener bl1;
    ButtonListener2 bl2;
    ButtonListener3 bl3;

    int rand = (int) (Math.random() * 100);
    int count = 0;

    public Guess() {
        Container c = getContentPane();

        // Set absolute layout
        c.setLayout(null);

        // Set Background Color

        // Creating image
        // JLabel lblpic = new JLabel("");
        // lblpic.setIcon(new ImageIcon("images.jpg"));
        // lblpic.setBounds(0, 0, 500, 350);

        // Creating label Guess my number text
        JLabel j = new JLabel("Guess my number game . . . ");
        j.setForeground(Color.RED);
        j.setFont(new Font("tunga", Font.BOLD, 36));
        j.setSize(370, 50);
        j.setLocation(130, 80);

        // Creating label Enter a number.....
        JLabel j1 = new JLabel("Enter a number b/w 1-100");
        j1.setFont(new Font("tunga", Font.PLAIN, 25));
        j1.setSize(320, 30);
        j1.setLocation(40, 160);

        // Creating TextField for input guess
        t1 = new JTextField(10);
        t1.setSize(80, 30);
        t1.setLocation(400, 160);

        // Creating Label for Display message
        j4 = new JLabel("Try and guess my number");
        j4.setFont(new Font("tunga", Font.BOLD, 25));
        j4.setSize(300, 35);
        j4.setLocation(150, 230);

        // Creating Text field for best score
        t2 = new JTextField(10);
        t2.setSize(70, 30);
        t2.setLocation(10, 10);

        // Creating Label for best score
        JLabel j5 = new JLabel("Best Score");
        j5.setFont(new Font("tunga", Font.PLAIN, 27));
        j5.setForeground(Color.MAGENTA);
        j5.setSize(270, 30);
        j5.setLocation(90, 9);

        // Creating guess text field
        t3 = new JTextField(10);
        t3.setSize(50, 30);

        t3.setLocation(400, 10);

        // Creating guess Label
        JLabel j6 = new JLabel("Guesses");
        j6.setFont(new Font("tunga", Font.PLAIN, 27));
        j6.setForeground(Color.MAGENTA);
        j6.setSize(270, 30);
        j6.setLocation(460, 9);

        // creating 3 buttons
        JButton b1 = new JButton("Guess");
        b1.setSize(150, 40);
        b1.setLocation(200, 280);
        b1.setBackground(Color.GREEN);
        b1.setForeground(Color.BLACK);
        bl1 = new ButtonListener();
        b1.addActionListener(bl1);

        JButton b2 = new JButton("Give up!");
        b2.setSize(100, 35);
        b2.setLocation(150, 350);
        b2.setBackground(Color.RED);
        bl2 = new ButtonListener2();
        b2.addActionListener(bl2);

        JButton b3 = new JButton("Play Again");
        b3.setSize(120, 35);
        b3.setLocation(300, 350);
        b3.setBackground(Color.YELLOW);
        bl3 = new ButtonListener3();
        b3.addActionListener(bl3);

        // Place the components in the pane
        c.add(j5);
        c.add(j4);
        // c.add(lblpic);
        c.add(j);
        c.add(j1);
        c.add(t1);
        c.add(t2);
        c.add(t3);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        c.add(j6);

        // Changing TextFields to UnEditable
        t2.setEditable(false);
        t3.setEditable(false);

        // Set the title of the window
        setTitle("GUESS MY NUMBER");

        // Set the size of the window and display it
        setSize(600, 450);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private class ButtonListener implements ActionListener {
        int bestScore = 100;

        public void actionPerformed(ActionEvent e) {
            int a = Integer.parseInt(t1.getText());
            count = count + 1;
            if (a < rand) {
                j4.setText(a + " is too low, try again!!");
            } else if (a > rand) {
                j4.setText(a + " is too high, try again!!");
            } else {
                j4.setText("CORRECT, YOU WIN!!!!");
                if (count < bestScore) {
                    bestScore = count;
                    t2.setText(bestScore + "");
                } else
                    t2.setText("" + bestScore);
                t1.setEditable(false);
            }

            t1.requestFocus();
            t1.selectAll();

            t3.setText(count + "");
        }
    }

    private class ButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            t3.setText("");
            j4.setText(rand + " is the answer!");
            t1.setText("");
            t1.setEditable(false);
        }
    }

    private class ButtonListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            rand = (int) (Math.random() * 100);
            t1.setText("");
            t3.setText("");
            j4.setText("Try and guess my number");
            t3.setText("");
            count = 0;
            t1.setEditable(true);
            t1.requestFocus();
        }
    }
}
