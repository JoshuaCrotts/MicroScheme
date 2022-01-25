//package com.joshuacrotts.minischeme.turtle;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class TurtlePanel extends JPanel {
//
//    /**
//     *
//     */
//    private final Timer TIMER;
//
//    /**
//     *
//     */
//    private final int MS;
//
//    /**
//     *
//     */
//    private final Turtle TURTLE;
//
//    public TurtlePanel(final Turtle turtle, final int width, final int height) {
//        this.setMinimumSize(new Dimension(width, height));
//        this.setMaximumSize(new Dimension(width, height));
//        this.setPreferredSize(new Dimension(width, height));
//        this.MS = 16;
//        this.TIMER = new Timer(this.MS, e -> repaint());
//        this.TIMER.start();
//        this.TURTLE = turtle;
//    }
//
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(Color.WHITE);
//        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
//        this.TURTLE.drawTurtle(g2);
//    }
//
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(this.WIDTH, this.HEIGHT);
//    }
//}
