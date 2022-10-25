package com.nidhal.view;

public enum FontSize {
    SMALL,
    BIG;


    public static String getCssPath(FontSize fontSize) {
        switch (fontSize) {
            case BIG:
                return "/css/fontBig.css";
            case SMALL:
                return "/css/fontSmall.css";
            default:
                return "/css/fontSmall.css";
        }
    }

}
