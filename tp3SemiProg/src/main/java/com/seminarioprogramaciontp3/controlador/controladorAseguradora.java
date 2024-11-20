package com.seminarioprogramaciontp3.controlador;

import com.seminarioprogramaciontp3.dao.AseguradoraSQL;
import com.seminarioprogramaciontp3.modelo.Aseguradora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controladorAseguradora {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCuit;
    @FXML
    private Button btnguardar;
    @FXML
    private Button btncancelar;

    private AseguradoraSQL aseguradoraDAO;

    public controladorAseguradora() {
        aseguradoraDAO = new AseguradoraSQL();
    }

    @FXML
    private void guardar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String cuit = txtCuit.getText();

        Aseguradora Aseguradora = new Aseguradora();
        boolean success = Aseguradora.insertar(nombre,email,cuit);

        Alert alert;
        if (success) {
            alert = new Alert(Alert.AlertType.INFORMATION, "Aseguradora guardada con éxito.");
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Error al guardar la aseguradora.");
        }
        alert.showAndWait();

        cerrarVentana();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnguardar.getScene().getWindow();
        stage.close();
    }
}
