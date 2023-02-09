import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class RandomQuote {
    JFrame frame;
    private JLabel quoteLabel;
    private JLabel imageLabel;
    private JLabel qLabel;

    public RandomQuote(String name, String age, String gender) {
            class StyledLabel extends JLabel {
                StyledLabel(String text) {
                        setText(text);
                        setFont(new Font("Arial", Font.PLAIN, 16));
                        setForeground(Color.ORANGE);
                }
            }
        frame = new JFrame("Prediction");
        String q="Your Spirit Card";
        ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("images/z.png"));
        frame.setIconImage(logo.getImage());
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout());
        try {
            // Connect to the SQLite database
            String QuoteRes, PredRes;
            Connection connection = DriverManager.getConnection("jdbc:sqlite:babajava.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // Fetch a random quote from the database
            ResultSet Resquote = statement.executeQuery("SELECT * FROM quotes ORDER BY RANDOM() LIMIT 1");
            Resquote.next();
            QuoteRes=Resquote.getString("quote");
            ResultSet Respred = statement.executeQuery("SELECT * FROM prediction ORDER BY RANDOM() LIMIT 1");
            Respred.next();
            PredRes=Respred.getString("pred");
            // result.getString("quote");
            String quote = "<html><center>Dear " + name + ",<br><br>Baba Java predicts that "
                    + PredRes + ".As Baba is aware of the fact that you are a " + age + " year old "
                    + gender + ", he advices you to " +QuoteRes + "</center></html>";
            final int maxImageCount = 10;
            int imageUrlId = new Random().nextInt(maxImageCount) + 1;
            ImageIcon imageIcon = new ImageIcon("src/images/" + imageUrlId + ".png");

            // Create and add the quote label to the panel
            quoteLabel = new StyledLabel(quote);
            // Create and add the image label to the panel
            imageLabel = new JLabel(imageIcon);
            // imageLabel.setPreferredSize(new Dimension(200, 200));
            imageLabel.setIcon(new ImageIcon(new ImageIcon("src/images/" + imageUrlId + ".png").getImage()
                    .getScaledInstance(200, 250, Image.SCALE_SMOOTH)));
            Box vbox = Box.createVerticalBox();
            vbox.add(Box.createVerticalGlue());
            vbox.add(HCenter(qLabel));
            Box centerImage = Box.createHorizontalBox();
            centerImage.add(Box.createHorizontalGlue());
            centerImage.add(imageLabel);
            centerImage.add(Box.createHorizontalGlue());
            vbox.add(centerImage);

            Box centerQuote = Box.createHorizontalBox();
            centerQuote.add(Box.createHorizontalGlue());
            centerQuote.add(Box.createHorizontalStrut(40));
            centerQuote.add(quoteLabel);
            centerQuote.add(Box.createHorizontalStrut(40));
            centerQuote.add(Box.createHorizontalGlue());
            vbox.add(Box.createVerticalStrut(20));
            vbox.add(centerQuote);

            Box ButtonContainer = Box.createVerticalBox();
            Box Buttons = Box.createHorizontalBox();
            Buttons.add(Box.createHorizontalGlue());
            JLabel exitLabel = new StyledLabel("");

            class ExitButton extends JButton {
                ExitButton(String text) {
                        setText(text);
                        setFont(new Font("Arial", Font.PLAIN, 16));
                        setForeground(Color.BLUE);
                        setBackground(Color.YELLOW);
                        setFocusable(false);
                        setBorder(new LineBorder(Color.LIGHT_GRAY, 3, true));
                        setPreferredSize(new Dimension(100, 50));
                }
            }

            JButton thankButt = new ExitButton(" Thank you ");
            thankButt.setFont(new Font("Arial", Font.PLAIN, 16));
            thankButt.setForeground(Color.BLUE);
            thankButt.addActionListener((new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            }));

            JButton againButt = new ExitButton(" Baba, again ");
            againButt.addActionListener((new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    exitLabel.setText("Only a single prediction will be done in a day, have some faith in Baba");
                }
            }));
            Buttons.add(thankButt);
            Buttons.add(Box.createHorizontalStrut(20));
            Buttons.add(againButt);
            Buttons.add(Box.createHorizontalGlue());

            ButtonContainer.add(Buttons);
            ButtonContainer.add(Box.createVerticalStrut(40));
            ButtonContainer.add(HCenter(exitLabel));

            vbox.add(Box.createVerticalStrut(40));
            vbox.add(ButtonContainer);
            vbox.add(Box.createVerticalGlue());
            panel.add(vbox, BorderLayout.CENTER);


            // Add the panel to the frame and set the default close operation
            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
            // Make the frame visible
            frame.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
private Box HCenter(Component comp) {
    Box hBox = Box.createHorizontalBox();
    hBox.add(Box.createHorizontalGlue());
    hBox.add(comp);
    hBox.add(Box.createHorizontalGlue());   
    return hBox;
}
}
