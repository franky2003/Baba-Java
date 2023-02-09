import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class MyTextField extends JPanel{
    JLabel label;
    JTextField field;
    public MyTextField(String Text){
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.BLACK);
        label = new JLabel(Text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.ORANGE);
        label.setPreferredSize(new Dimension(90, 20));
        field = new JTextField(10);
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setForeground(Color.LIGHT_GRAY);
        field.setBackground(Color.DARK_GRAY);
        field.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        field.setMaximumSize(new Dimension(200, field.getMinimumSize().height));
        add(label);
        add(field);
    }
    public String getFieldValue(){
        return field.getText();

    }
    }