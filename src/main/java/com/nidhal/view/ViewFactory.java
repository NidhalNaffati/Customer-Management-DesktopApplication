package com.nidhal.view;

import com.nidhal.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class ViewFactory {


    private final ArrayList<Stage> activeStages;

    public ViewFactory() {
        activeStages = new ArrayList<Stage>();
    }

    // show up the login window
    public void showLoginWindow() {
        BaseController controller = new LoginPageController(this, "/fxml/LoginPage.fxml");
        initializeStage(controller);
    }

    // show up the all the customers in the db.
    public void showAllDataWindow() {
        BaseController controller = new AllDataController(this, "/fxml/ShowData.fxml");
        initializeStage(controller);
    }

    // show up the delete customer page.
    public void showDeleteCustomerWindow() {
        BaseController controller = new DeleteCustomerController(this, "/fxml/DeleteCustomer.fxml");
        initializeStage(controller);
    }

    // show up add new customer window
    public void showAddCustomerWindow() {
        BaseController controller = new AddCustomerController(this, "/fxml/AddCustomer.fxml");
        initializeStage(controller);
    }


    // show up option window
    public void showOptionWindow() {
        BaseController controller = new OptionsController(this, "/fxml/Option.fxml");
        initializeStage(controller);
    }


    // this method responsible for showing a specific window (parameter)
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

    public void closeStage(Stage stageToClose) {
        stageToClose.close();
        activeStages.remove(stageToClose);
    }

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


    private ColorTheme colorTheme = ColorTheme.DARK;
    private FontSize fontSize = FontSize.BIG;

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }
}

