package com.wizardden.demo.GameUser;

public class GameUserWrongPasswordException extends RuntimeException {
    GameUserWrongPasswordException() {
        super("Invalid Username/Password");
    }

    GameUserWrongPasswordException(String message) {
        super(message);
    }
}
