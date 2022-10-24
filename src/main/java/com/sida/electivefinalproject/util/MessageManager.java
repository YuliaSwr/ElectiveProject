package com.sida.electivefinalproject.util;

import java.util.ResourceBundle;

public class MessageManager {
    private final static String FILE_NAME = "messages";
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(FILE_NAME);
    private MessageManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
