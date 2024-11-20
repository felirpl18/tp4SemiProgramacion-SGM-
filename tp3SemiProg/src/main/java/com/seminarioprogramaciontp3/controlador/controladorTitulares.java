
package com.seminarioprogramaciontp3.controlador;

import com.seminarioprogramaciontp3.modelo.Titular;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controladorTitulares {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField nroDocField;
    @FXML
    private TextField telefonoField;
    @FXML
    private Button btnguardar;
    @FXML
    private Button btncancelar;

    @FXML
    private void guardar(ActionEvent event) {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String nro_doc = nroDocField.getText();
        String telefono = telefonoField.getText();

        Titular titular = new Titular();
        boolean success = titular.insertar(nombre, apellido, nro_doc, telefono);

        Alert alert;
        if (success) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Titular guardado con éxito.");
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Error al guardar el titular.");
        }
        alert.showAndWait();

        
        ((Stage) btnguardar.getScene().getWindow()).close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        
        ((Stage) btncancelar.getScene().getWindow()).close();
    }
}


