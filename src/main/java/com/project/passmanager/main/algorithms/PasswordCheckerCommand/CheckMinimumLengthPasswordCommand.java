package com.project.passmanager.main.algorithms.PasswordCheckerCommand;

public class CheckMinimumLengthPasswordCommand implements IPasswordCheckCommand {
    @Override
    public int execute(String password) {
        if (password.length() >= 8) return 5;
        else return 0;
    }
}
