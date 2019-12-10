import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChronoDisplay extends JFrame implements ActionListener, ChronometerListener {

    private JLabel label;
    private JButton button;
    private Chronometer chronometer;

    ChronoDisplay() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(2, 1));
        Dimension dim = new Dimension(200, 100);
        label = new JLabel();
        label.setMinimumSize(dim);
        label.setMaximumSize(dim);
        label.setText("0");
        add(label);

        button = new JButton("Toggle");
        button.addActionListener(this);
        add(button);

        chronometer = new Chronometer();
        chronometer.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chronometer.toggle();
    }

    @Override
    public void currentChronometer(String current) {
        label.setText(current);
    }
}
