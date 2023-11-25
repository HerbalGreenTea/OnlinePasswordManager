package com.project.passmanager.main.algorithms;

public interface IPasswordHandler {

    String encrypt(String password);

    Boolean isMatch(String candidate, String hashed);
}
