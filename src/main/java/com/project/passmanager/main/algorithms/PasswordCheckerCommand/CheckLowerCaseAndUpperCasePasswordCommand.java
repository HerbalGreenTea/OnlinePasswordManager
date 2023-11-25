package com.project.passmanager.main.algorithms.PasswordCheckerCommand;

public class CheckLowerCaseAndUpperCasePasswordCommand implements IPasswordCheckCommand {

    /**
     * Проверяет, содержит ли пароль буквы в верхнем и нижнем регистре.
     *
     * @param password Пароль для проверки.
     * @return Баллы, рассчитанные на основе количества заглавных и прописных букв.
     */
    @Override
    public int execute(String password) {
        var uppercaseCount = 0;
        var lowercaseCount = 0;

        for (var character : password.toCharArray()) {
            if (Character.isUpperCase(character)) {
                uppercaseCount++;
            } else if (Character.isLowerCase(character)) {
                lowercaseCount++;
            }
        }

        // Присвоение баллов в зависимости от количества заглавных и прописных букв
        return Math.min(uppercaseCount, lowercaseCount) * 2;
    }
}
