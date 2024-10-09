package pl.minewars.example.util;

import org.jetbrains.annotations.NotNull;

import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class TranslationUtility {

    protected static final ResourceBundle MESSAGES_BUNDLE = getBundle("messages");

    public static @NotNull String translationOf(
            @NotNull String key
    ) {
        return MESSAGES_BUNDLE.getString(key);
    }

}
