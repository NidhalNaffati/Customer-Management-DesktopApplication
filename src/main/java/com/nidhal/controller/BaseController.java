package com.nidhal.controller;

import com.nidhal.view.ViewFactory;

public abstract class BaseController {
    protected ViewFactory viewFactory ;
    protected String FxmlFile ;

    public BaseController(ViewFactory viewFactory, String fxmlFile) {
        this.viewFactory = viewFactory;
        this.FxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return FxmlFile;
    }
}
