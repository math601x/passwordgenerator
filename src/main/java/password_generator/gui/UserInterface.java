package password_generator.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import password_generator.app.PasswordStrengthParser;
import password_generator.framework.PasswordGenerator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInterface {
  PasswordGenerator passGen;
  PasswordStrengthParser strengthParser;

  JFrame frame;
  JPanel centerPanel, bottomPanel;
  JLabel inputLabel;
  JButton generatePasswordButton, switchApplicationModeButton;
  JTextPane usageInfoTextArea, passwordTextArea, strengthDisplayTextArea;
  BoxLayout boxlayout;

  public UserInterface(PasswordGenerator passwordGeneratorType, PasswordStrengthParser passwordStrengthParser) {
    passGen = passwordGeneratorType;
    strengthParser = passwordStrengthParser;

    // Set up Frame
    frame = new JFrame("Password generator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(350, 400);

    // Set up panels
    bottomPanel = new JPanel();
    centerPanel = new JPanel();
    BoxLayout boxlayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
    centerPanel.setLayout(boxlayout);

    // set up textareas
    SimpleAttributeSet attribs = new SimpleAttributeSet();
    StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);

    // Usage info
    usageInfoTextArea = new JTextPane();
    usageInfoTextArea.setParagraphAttributes(attribs, true);
    usageInfoTextArea.setEditable(false);
    usageInfoTextArea
        .setText("Enter your own password manually to see strength, or press \"Generate password\" to make one");

    // Password Text area
    passwordTextArea = new JTextPane();
    passwordTextArea.setParagraphAttributes(attribs, true);
    Border border = BorderFactory.createLineBorder(Color.BLACK);
    passwordTextArea
        .setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

    passwordTextArea.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
        String password = passwordTextArea.getText();
        strengthDisplayTextArea.setText("Security level: " + strengthParser.getPasswordStrengthFeedback(password)
            + strengthParser.getTimeToCrack(password));

        if (strengthParser.getPasswordStrengtNumericalValue(password) == 0)
          strengthDisplayTextArea.setBackground(Color.red);

        else if (strengthParser.getPasswordStrengtNumericalValue(password) == 1)
          strengthDisplayTextArea.setBackground(Color.orange);

        else if (strengthParser.getPasswordStrengtNumericalValue(password) == 2)
          strengthDisplayTextArea.setBackground(Color.yellow);

        else if (strengthParser.getPasswordStrengtNumericalValue(password) == 3)
          strengthDisplayTextArea.setBackground(Color.green);

        else if (strengthParser.getPasswordStrengtNumericalValue(password) == 4)
          strengthDisplayTextArea.setBackground(Color.CYAN);
      }
    });

    // Strength display
    strengthDisplayTextArea = new JTextPane();
    strengthDisplayTextArea.setParagraphAttributes(attribs, true);
    strengthDisplayTextArea.setEditable(false);
    strengthDisplayTextArea.setText("Security level: ");

    // set up buttons
    generatePasswordButton = new JButton("Generate new Password");
    generatePasswordButton.addActionListener((ActionListener) new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String password = passGen.generatePassword();
        passwordTextArea.setText(password);
        strengthDisplayTextArea.setText("Security level: " + strengthParser.getPasswordStrengthFeedback(password)
            + strengthParser.getTimeToCrack(password));
        strengthDisplayTextArea.setBackground(Color.green);
      }
    });

    // Add components to frame
    centerPanel.add(usageInfoTextArea, BorderLayout.CENTER);
    centerPanel.add(passwordTextArea, BorderLayout.CENTER);
    centerPanel.add(strengthDisplayTextArea, BorderLayout.CENTER);
    bottomPanel.add(generatePasswordButton);

    frame.getContentPane().add(centerPanel);
    frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
    frame.setVisible(true);
  }
}