package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.RadioButton;

public class PainterController {
    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton penRadioButton;

    @FXML
    private RadioButton eraserRadioButton;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        boolean erasing = eraserRadioButton != null && eraserRadioButton.isSelected();
        Circle circle = new Circle(event.getX(), event.getY(), erasing ? 10 : 4);
        circle.setFill(erasing ? Color.WHITE : Color.BLACK);
        drawingAreaPane.getChildren().add(circle);
    }
}
