import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppFrame extends JFrame implements ActionListener {

    private JLabel label;
    private JButton button;
    private boolean counting = false;
    private Thread counterThread = null;

    public AppFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(2, 1));
        Dimension dim = new Dimension(200, 100);
        label = new JLabel();
        label.setMinimumSize(dim);
        label.setMaximumSize(dim);
        label.setText("0");
        add(label);

        button = new JButton("Toggle Count");
        button.addActionListener(this);
        add(button);

    }

    public void actionPerformed(ActionEvent e) {
        counting = !counting;

        if (counting) {
            Runnable countTask = new Runnable() {
                public void run() {
                    label.setText("0");
                    int i = 1;
                    while (true) {
                        try {
                            Thread.sleep(1000);
                            label.setText("" + i++);
                        } catch (InterruptedException e) {
                            System.out.println("interrupted at " + (i - 1));
                            return;
                        }
                    }
                }
            };
            counterThread = new Thread(countTask);
            counterThread.start();
        } else {
            counterThread.interrupt();
        }
    }
}
