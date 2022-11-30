package password_generator.app;

import password_generator.gui.UserInterface;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface(new StandardPasswordGenerator(), new PasswordStrengthParser());
    }
}
