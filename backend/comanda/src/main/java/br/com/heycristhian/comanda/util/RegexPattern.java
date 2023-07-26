package br.com.heycristhian.comanda.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RegexPattern {

    private RegexPattern() {
    }

    public static boolean containsNumber(String value) {
        final String regex = "\\d";
        return getMatcher(regex, value).find();
    }

    public static boolean containsUpperCaseLetter(String value) {
        final String regex = "[A-Z]";
        return getMatcher(regex, value).find();
    }

    public static boolean containsLowerCaseLetter(String value) {
        final String regex = "[a-z]";
        return getMatcher(regex, value).find();
    }

    public static boolean containsSpecialCharacter(String value) {
        final String regex = "[^a-zA-Z0-9\\s]";
        return getMatcher(regex, value).find();
    }

    public static Matcher getMatcher(String regex, String value) {
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        return pattern.matcher(value);
    }

}
