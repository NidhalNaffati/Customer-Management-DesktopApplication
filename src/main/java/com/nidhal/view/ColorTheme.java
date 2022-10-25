package com.nidhal.view;

public enum ColorTheme {
    LIGHT,
    DARK;

    public static String getCssPath(ColorTheme colorTheme) {
        return switch (colorTheme) {
            case LIGHT -> "/css/themeLight.css";
            case DARK -> "/css/themeDark.css";
            default -> null;
        };
    }
}
