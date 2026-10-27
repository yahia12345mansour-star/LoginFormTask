import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class User {

    private final String username;
    private final String password;
    private final String role;

    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    String getUsername() { return username; }

    String getPassword() { return password; }

    String getRole() { return role; }
}

class WelcomePage extends Frame {

    WelcomePage(User user) {
        super("Welcome");
        setLayout(new GridBagLayout());

        Panel content = new Panel(new GridLayout(2, 1, 0, 10));

        Label hello = new Label("Hello, " + user.getUsername(), Label.CENTER);
        hello.setFont(new Font("SansSerif", Font.BOLD, 22));

        Label role = new Label("Role: " + user.getRole(), Label.CENTER);
        role.setFont(new Font("SansSerif", Font.PLAIN, 16));

        content.add(hello);
        content.add(role);
        add(content);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { dispose(); }
        });
        setSize(420, 340);
        setLocationRelativeTo(null);
    }
}

public class LoginFormTask extends Frame {

    private static final int MAX_WRONG_PASSWORDS = 3;

    private final List<User> users = new ArrayList<>();
    private int wrongPasswords = 0;

    public LoginFormTask() {
        super("Login");
        loadUsers();

        setLayout(new GridBagLayout());
        Panel content = new Panel(new BorderLayout(10, 15));

        Label title = new Label("Welcome back", Label.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));

        Label message = new Label("", Label.CENTER);
        message.setForeground(Color.RED);
        message.setPreferredSize(new Dimension(380, 20));

        Panel header = new Panel(new GridLayout(2, 1));
        header.add(title);
        header.add(message);
        content.add(header, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(3, 2, 8, 8));
        form.add(new Label("Username:", Label.RIGHT));
        TextField username = new TextField(18);
        form.add(username);

        form.add(new Label("Password:", Label.RIGHT));
        TextField password = new TextField(18);
        password.setEchoChar('*');
        form.add(password);

        form.add(new Label("Role:", Label.RIGHT));
        Choice role = new Choice();
        role.add("Student");
        role.add("TA");
        role.add("Professor");
        form.add(role);

        content.add(form, BorderLayout.CENTER);

        Panel buttons = new Panel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        Button cancel = new Button("Cancel");
        Button login = new Button("Login");
        buttons.add(cancel);
        buttons.add(login);
        content.add(buttons, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 30, 25, 30);
        add(content, gbc);

        login.addActionListener(e -> {
            String name = username.getText();
            String pass = password.getText();

            if (name.isEmpty() && pass.isEmpty()) {
                message.setText("Please enter your username and password.");
                return;
            }
            if (name.isEmpty()) {
                message.setText("Please enter your username.");
                return;
            }
            if (pass.isEmpty()) {
                message.setText("Please enter your password.");
                return;
            }

            User user = findUser(name);
            if (user == null) {
                message.setText("User not found.");
                return;
            }

            if (!user.getPassword().equals(pass)) {
                wrongPasswords++;
                if (wrongPasswords >= MAX_WRONG_PASSWORDS) {
                    login.setEnabled(false);
                    message.setText("Attempt " + wrongPasswords + " of " + MAX_WRONG_PASSWORDS
                            + " failed. Login is disabled.");
                } else {
                    message.setText("Incorrect password. Attempt " + wrongPasswords
                            + " of " + MAX_WRONG_PASSWORDS + ".");
                }
                return;
            }

            new WelcomePage(user).setVisible(true);
            dispose();
        });

        cancel.addActionListener(e -> dispose());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { dispose(); }
        });
        pack();
        setLocationRelativeTo(null);
    }

    private void loadUsers() {
        users.add(new User("yahia",      "student123", "Student"));
        users.add(new User("akram",      "ta123",      "TA"));
        users.add(new User("dr.ibrahim", "prof123",    "Professor"));
    }

    private User findUser(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new LoginFormTask().setVisible(true);
    }
}
