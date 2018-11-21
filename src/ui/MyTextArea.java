package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MyTextArea extends JTextArea {

    private Image img;
    URL url = new URL("https://cdn5.vectorstock.com/i/1000x1000/98/19/notebook-paper-background-template-with-vector-13609819.jpg");
    final ImageIcon imageIcon = new ImageIcon(url);

    public MyTextArea(int a, int b) throws MalformedURLException {
        super(a,b);
            img = imageIcon.getImage();

    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img,0,0,null);
        super.paintComponent(g);
    }
}