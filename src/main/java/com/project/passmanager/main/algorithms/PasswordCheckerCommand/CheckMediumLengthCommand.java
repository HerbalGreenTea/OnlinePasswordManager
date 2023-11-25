package com.project.passmanager.main.algorithms.PasswordCheckerCommand;

public class CheckMediumLengthCommand implements IPasswordCheckCommand {
    @Override
    public int execute(String password) {
        if (password.length() >= 12) return 8;
        else return 0;
    }
}
