package com.project.passmanager.main.algorithms.PasswordCheckerCommand;

import java.io.IOException;

public interface IPasswordCheckCommand {
    int execute(String password) throws IOException;
}
