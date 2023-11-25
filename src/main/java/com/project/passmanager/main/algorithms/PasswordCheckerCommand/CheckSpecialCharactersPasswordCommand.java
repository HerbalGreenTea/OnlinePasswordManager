package com.project.passmanager.main.algorithms.PasswordCheckerCommand;

public class CheckSpecialCharactersPasswordCommand implements IPasswordCheckCommand {
    /**
     * Проверяет, содержит ли пароль специальные символы.
     *
     * @param password Пароль для проверки.
     * @return true, если пароль содержит специальные символы, иначе - false.
     */
    @Override
    public int execute(String password) {
        if (password.matches(".*[!@#\\$%\\^&\\*\\(\\)_\\+\\-=<>\\?].*")) return 10;
        else return 0;
    }
}
