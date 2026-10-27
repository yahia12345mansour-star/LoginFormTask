# Login Form Task

A simple login form built with Java AWT. The users are hard-coded and the Login button gets disabled after 3 wrong passwords.

## Project structure

```
src/
└── LoginFormTask.java    # User, WelcomePage and LoginFormTask classes
screenshots/              # Screenshots of each scenario
```

## Test users

| Username   | Password   | Role      |
|------------|------------|-----------|
| yahia      | student123 | Student   |
| akram      | ta123      | TA        |
| dr.ibrahim | prof123    | Professor |

## How it works

1. If the username or password is empty, a message tells which one is missing.
2. If the username does not exist, it shows `User not found.`
3. If the password is wrong, it shows `Incorrect password. Attempt N of 3.`
4. If everything is correct, the Welcome page opens with the username and role.

After 3 wrong passwords the Login button is disabled. Empty fields and unknown usernames do not count as attempts.

## Run

```bash
javac -d out src/LoginFormTask.java
java -cp out LoginFormTask
```

## Screenshots

| Scenario | Screenshot |
|----------|------------|
| Initial form | ![](screenshots/01_initial_form.png) |
| Both fields empty | ![](screenshots/02_empty_both.png) |
| Password empty | ![](screenshots/03_empty_password.png) |
| Username empty | ![](screenshots/04_empty_username.png) |
| User not found | ![](screenshots/05_user_not_found.png) |
| Wrong password (1st) | ![](screenshots/06_wrong_password_1.png) |
| Wrong password (2nd) | ![](screenshots/07_wrong_password_2.png) |
| 3rd wrong password, Login disabled | ![](screenshots/08_third_wrong_locked.png) |
| Correct password after lock, still disabled | ![](screenshots/09_still_disabled_after_lock.png) |
| Student login | ![](screenshots/10_success_student.png) |
| TA login | ![](screenshots/11_success_ta.png) |
| Professor login | ![](screenshots/12_success_professor.png) |
