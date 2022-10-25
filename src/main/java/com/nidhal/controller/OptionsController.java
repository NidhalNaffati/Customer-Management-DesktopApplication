package com.nidhal.controller;


import com.nidhal.view.ColorTheme;
import com.nidhal.view.FontSize;
import com.nidhal.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController extends BaseController implements Initializable {
    public OptionsController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    private Button btnApply;

    @FXML
    private Button btnCancel;

    @FXML
    private ChoiceBox<ColorTheme> themePicker;

    @FXML
    private Slider fontSizePicker;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpThemePicker();
        setUpSizePicker();
    }

    private void setUpSizePicker() {
        fontSizePicker.setMin(0);
        fontSizePicker.setMax(FontSize.values().length - 1);
        fontSizePicker.setValue(viewFactory.getFontSize().ordinal());
        fontSizePicker.setMajorTickUnit(1);
        fontSizePicker.setMinorTickCount(0);
        fontSizePicker.setBlockIncrement(1);
        fontSizePicker.setSnapToTicks(true);
        fontSizePicker.setShowTickMarks(true);
        fontSizePicker.setShowTickLabels(true);
        fontSizePicker.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                int i = object.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        });
        fontSizePicker.valueProperty().addListener((obs, oldVal, newVal) -> {
            fontSizePicker.setValue(newVal.intValue());
        });

    }


    private void setUpThemePicker() {
        themePicker.setItems(FXCollections.observableArrayList(ColorTheme.values()));
        themePicker.setValue(viewFactory.getColorTheme());
    }


    // to apply the changes.
    @FXML
    void ApplyBtnAction() {
        viewFactory.setColorTheme(themePicker.getValue());
        viewFactory.setFontSize(FontSize.values()[(int) (fontSizePicker.getValue())]);
        viewFactory.updateStyles();
    }

    // to cancel the changes.
    @FXML
    void ApplyBtnCancel() {
        Stage stage = (Stage) fontSizePicker.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

}
