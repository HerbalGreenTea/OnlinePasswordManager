package com.project.passmanager.main.algorithms.PasswordCheckerCommand;


import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PasswordCheckCommandExecutor {

    private final List<IPasswordCheckCommand> commands = Arrays.asList(
            new CheckMinimumLengthPasswordCommand(),
            new CheckMediumLengthCommand(),
            new CheckDigitsPasswordCommand(),
            new CheckLowerCaseAndUpperCasePasswordCommand(),
            new CheckSpecialCharactersPasswordCommand(),
            new CheckCommonPasswordCommand()
    );

    public Integer evaluatePasswordStrength(String password) throws IOException {

        var rating = new ArrayList<Integer>();

        for (IPasswordCheckCommand command : commands) {
            rating.add(command.execute(password));
        }

        return rating.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
