import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
public class LoginForm extends JPanel {
  private JLabel titleLabel;
  private MyTextField nameTextField;
  private MyTextField ageTextField;
  private MyTextField genderTextField;
  private JButton loginButton;
  private JLabel errorLabel;

  public LoginForm(JFrame parentFrame)  {
    setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    setBackground(new Color(0, 0, 0));

    titleLabel = new JLabel("Baba Java", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
    titleLabel.setForeground(Color.orange);
    titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    titleLabel.setMaximumSize(new Dimension(100,100));

    JLabel authorLabel = new JLabel("Authors:Ritin,Rhon and Franky", SwingConstants.CENTER);
    authorLabel.setFont(new Font("Arial", Font.BOLD, 13));
    authorLabel.setForeground(new Color(0, 0, 0));

    nameTextField = new MyTextField("Name    ");
    ageTextField = new MyTextField("Age       ");
    genderTextField = new MyTextField("Gender   ");
    errorLabel = new JLabel("");
    errorLabel.setFont(new Font("Arial", Font.PLAIN, 13));
    errorLabel.setForeground(Color.red);
    errorLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

    loginButton = new JButton("  Dive in  ");
    loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
    loginButton.setForeground(Color.BLUE);
    loginButton.setBackground(Color.YELLOW);
    loginButton.setFocusable(false);
    loginButton.setBorder(new LineBorder(Color.ORANGE, 2, true));
    loginButton.setPreferredSize(new Dimension(100, 50));
    loginButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (validateFields()) {
          String name = nameTextField.getFieldValue();
          String age = ageTextField.getFieldValue();
          String gender = genderTextField.getFieldValue();
          RandomQuote rq = new RandomQuote(name, age, gender);
          parentFrame.dispose();
          rq.frame.setVisible(true);

          System.out.println("Name  :" + name);
          System.out.println("Age   :" + age);
          System.out.println("Gender:" + gender);
        }
      }
    });

    JLabel imageLabel = new JLabel();
    imageLabel.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/z.png")));
    //imnameTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //imnameTextField.setPreferredSize(new Dimension(100,100));
    Box imagebox=Box.createHorizontalBox();
    imagebox.add(Box.createHorizontalGlue());
    imagebox.add(imageLabel);
    imagebox.add(Box.createHorizontalGlue());

    Box titlebox=Box.createHorizontalBox();
    titlebox.add(Box.createHorizontalGlue());
    titlebox.add(titleLabel);
    titlebox.add(Box.createHorizontalGlue());
    Box centerAlignFieldsBox = Box.createHorizontalBox();
    Box inputfieldsbox=Box.createVerticalBox();
    inputfieldsbox.add(nameTextField);
    inputfieldsbox.add(Box.createVerticalStrut(10));
    inputfieldsbox.add(ageTextField);
    inputfieldsbox.add(Box.createVerticalStrut(10));
    inputfieldsbox.add(genderTextField);
    inputfieldsbox.add(Box.createVerticalStrut(10));
    inputfieldsbox.add(errorLabel);
    inputfieldsbox.add(Box.createVerticalStrut(30));
    inputfieldsbox.add(loginButton);
    centerAlignFieldsBox.add(Box.createHorizontalGlue());
    centerAlignFieldsBox.add(inputfieldsbox);
    centerAlignFieldsBox.add(Box.createHorizontalGlue());
    add(imagebox);
    add(titlebox);
    add(authorLabel);
    add(centerAlignFieldsBox);
    add(errorLabel);
  }
    // add(sexField);
    //add(box,BorderLayout.CENTER);
    //add(imnameTextField, BorderLayout.CENTER);

    final boolean validateFields() {
        String name = nameTextField.getFieldValue();
        String age = ageTextField.getFieldValue();
        String gender = genderTextField.getFieldValue();
        boolean isValid = true;
        String errorMessage = "";
        try {
            int ageVal = Integer.parseInt(age);
            if (ageVal <= 18 || ageVal >= 100) {
                isValid = false;
                errorMessage = "Age should be between 18 and 100";
            }
            if (!gender.chars().allMatch(Character::isLetter)) {
              isValid = false;
              errorMessage = "Enter valid input";
            }
            if (!name.chars().allMatch(Character::isLetter)) {
                isValid = false;
                errorMessage = "Name should not contain any numbers";
            }
        } catch (NumberFormatException e) {
            isValid = false;
            errorMessage = "Age should be a number";
        }
        errorLabel.setText(errorMessage);
        return isValid;
    
    }
}




