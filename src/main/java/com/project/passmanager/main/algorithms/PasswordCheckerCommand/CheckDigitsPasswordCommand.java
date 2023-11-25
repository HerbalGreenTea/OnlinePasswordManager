package com.project.passmanager.main.algorithms.PasswordCheckerCommand;

public class CheckDigitsPasswordCommand implements IPasswordCheckCommand {

    /**
     * Проверяет, содержит ли пароль цифры.
     *
     * @param password Пароль для проверки.
     * @return 10, если пароль содержит цифры, иначе - 0.
     */
    @Override
    public int execute(String password) {
        if (password.matches(".*\\d.*")) return 10;
        else return 0;
    }
}
