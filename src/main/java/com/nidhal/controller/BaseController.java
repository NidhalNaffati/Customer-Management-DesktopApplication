package com.nidhal.controller;

import com.nidhal.view.ViewFactory;

/**
 * BaseController class is an abstract class that is used as a base class for all the controllers in the application.
 * It provides a basic implementation to access the common fields such as `viewFactory` and `FxmlFile`.
 * The class should be extended by all the controllers that need to use the `viewFactory` and `FxmlFile` fields.
 */
public abstract class BaseController {

    /**
     * `viewFactory` is an instance of the ViewFactory that is used to load FXML views.
     */
    protected ViewFactory viewFactory;

    /**
     * `FxmlFile` is a string that contains the name of the FXML file associated with this controller.
     */
    protected String FxmlFile;

    /**
     * The constructor of the BaseController class takes two parameters `viewFactory` and `fxmlFile`.
     *
     * @param viewFactory instance of the ViewFactory class
     * @param fxmlFile name of the FXML file
     */
    public BaseController(ViewFactory viewFactory, String fxmlFile) {
        this.viewFactory = viewFactory;
        this.FxmlFile = fxmlFile;
    }

    /**
     * The `getFxmlFile` method returns the name of the FXML file associated with this controller.
     *
     * @return name of the FXML file
     */
    public String getFxmlFile() {
        return FxmlFile;
    }
}

