package com.project.passmanager.main.algorithms;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PasswordSettings {
    private int passwordLength;
    private boolean useLower;
    private boolean useUpper;
    private boolean useDigits;
    private boolean useSpecialCharacters;

    public int getPasswordLength() {
        return passwordLength;
    }

    public boolean isUseLower() {
        return useLower;
    }

    public boolean isUseUpper() {
        return useUpper;
    }

    public boolean isUseDigits() {
        return useDigits;
    }

    public boolean isUseSpecialCharacters() {
        return useSpecialCharacters;
    }
}
