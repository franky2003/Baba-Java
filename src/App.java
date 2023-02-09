import javax.swing.ImageIcon;
import javax.swing.JFrame;
public class App {
    JFrame frame;
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Baba Java");
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("images/z.png"));
        frame.setIconImage(logo.getImage());
        frame.setContentPane(new LoginForm(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400,500);
        frame.setVisible(true);
    }

    public void disposeFrame() {
        frame.dispose();
    }
}
