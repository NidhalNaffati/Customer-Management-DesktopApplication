package com.nidhal.view;
import com.nidhal.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

/**
 * ViewFactory is responsible for creating and showing different windows for different tasks such as login,
 * showing all customer's data, adding a new customer, deleting a customer and displaying options.
 * Each window is represented by a controller class (e.g. LoginPageController, AllDataController)
 * that extends the BaseController class and is responsible for the logic of the corresponding window.
 * The ViewFactory class also keeps track of all the currently open windows (stages) in an ArrayList and
 * allows for closing and updating the styles of these windows.
 */
public class ViewFactory {

    /**
     * ArrayList to keep track of all the currently open windows(stages)
     */
    private final ArrayList<Stage> activeStages;

    /**
     * constructor to initialize activeStages list
     */
    public ViewFactory() {
        activeStages = new ArrayList<Stage>();
    }

    /**
     * Show the login window
     */
    public void showLoginWindow() {
        BaseController controller = new LoginPageController(this, "/fxml/LoginPage.fxml");
        initializeStage(controller);
    }

    /**
     * Show all the customers in the db.
     */
    public void showAllDataWindow() {
        BaseController controller = new AllDataController(this, "/fxml/ShowData.fxml");
        initializeStage(controller);
    }

    /**
     * Show the delete customer page.
     */
    public void showDeleteCustomerWindow() {
        BaseController controller = new DeleteCustomerController(this, "/fxml/DeleteCustomer.fxml");
        initializeStage(controller);
    }

    /**
     * Show add new customer window
     */
    public void showAddCustomerWindow() {
        BaseController controller = new AddCustomerController(this, "/fxml/AddCustomer.fxml");
        initializeStage(controller);
    }

    /**
     * Show option window
     */
    public void showOptionWindow() {
        BaseController controller = new OptionsController(this, "/fxml/Option.fxml");
        initializeStage(controller);
    }

    /**
     * This method is responsible for showing a specific window (parameter)
     * @param baseController the controller class that represents the window
     */
    private void initializeStage(BaseController baseController) {
        System.out.print("Opening : ");
        System.out.println(getClass().getResource(baseController.getFxmlFile()));

        URL fxmlFileUrl = getClass().getResource(baseController.getFxmlFile());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(baseController);
        Parent parent;

        try {
            fxmlLoader.setLocation(fxmlFileUrl);
            parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            scene.getStylesheets().add(getClass().getResource("/css/themeDark.css").toExternalForm());
            Image iconImage = new Image(getClass().getResourceAsStream("/images/DoctorImage.png"));
            stage.getIcons().add(iconImage);
            stage.setScene(scene);
            stage.setTitle("CUSTOMER MANAGEMENT");
            stage.show();
            activeStages.add(stage);
        } catch (IllegalArgumentException e) {
            e.getCause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close a specific stage
     * @param stageToClose the stage to be closed
     */
    public void closeStage(Stage stageToClose) {
        stageToClose.close();
        activeStages.remove(stageToClose);
    }

    /**
     * Method to update the styles of all the currently open stages
     */
    public void updateStyles() {
        try {
            for (Stage stage : activeStages) {
                Scene scene = stage.getScene();
                scene.getStylesheets().clear();
                scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
                scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());
            }
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * current color theme
     */
    private ColorTheme colorTheme = ColorTheme.DARK;
    /**
     * current font size
     */
    private FontSize fontSize = FontSize.BIG;

    /**
     * Get the current color theme
     * @return the current color theme
     */
    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    /**
     * Set the color theme
     * @param colorTheme the color theme to set
     */
    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    /**
     * Get the current font size
     * @return the current font size
     */
    public FontSize getFontSize() {
        return fontSize;
    }

    /**
     * Set the font size
     * @param fontSize the font size to set
     */
    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

}

