import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.Color;
import java.awt.BorderLayout;
import java.time.LocalDateTime;

import java.awt.Graphics;
import java.lang.Thread;


public class MyFrame extends JFrame {
    int width = 400;
    int height = 400;
    JLabel dateLabel = new JLabel();
    public static void main(String[] args) {
        MyFrame frame = new MyFrame("タイトル");
        frame.setVisible(true);
    }

    MyFrame(String title){
        setTitle(title);
        setSize(width, height);
        setLocationRelativeTo(null);//初期画面表示位置を中央に
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container CP = getContentPane();
        //CP.setLayout(null);

        MainPanel mainPanel = new MainPanel();
        CP.add(mainPanel);
        JPanel panel = new JPanel();
        JButton startBtn = new JButton("Hello");
        startBtn.addActionListener(e -> System.out.println("Hello"));
        JButton stopBtn = new JButton("World");
        stopBtn.addActionListener(e -> System.out.println("World"));
        JButton getTimeBtn = new JButton("What Time");
        getTimeBtn.addActionListener(e -> {
            dateLabel.setText(LocalDateTime.now().toString());
            CP.add(dateLabel, BorderLayout.SOUTH);
        });
        panel.add(startBtn);
        panel.add(stopBtn);
        panel.add(getTimeBtn);
        panel.setBackground(Color.ORANGE);
        CP.add(panel, BorderLayout.NORTH);
        // CP.add(dateLabel);
    }
}
class MainPanel extends JPanel implements Runnable {
    JLabel dateLabel = new JLabel();
    String dateString;
    MainPanel(){
        setBackground(Color.BLUE);
        // add(dateLabel);
        Thread t = new Thread(this);
        t.start();
    }
    public JLabel getDateLabel(){
        return this.dateLabel;
    }
    @Override
    public void paintComponent(Graphics g){
        System.out.println("Repaint!!!");
        // dateLabel.setText("Hello");
        this.add(dateLabel);
    }
    @Override
    public void run(){
        Integer count = 1;
        while (true) {
            System.out.println(count);
            dateLabel.setText(count.toString());
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
            count++;
        }
    }
}