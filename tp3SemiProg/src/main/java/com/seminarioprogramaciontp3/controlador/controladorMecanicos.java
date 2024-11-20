package com.seminarioprogramaciontp3.controlador;

import com.seminarioprogramaciontp3.modelo.Mecanico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controladorMecanicos {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField dniField;
    @FXML
    private TextField legajoField;
    @FXML
    private TextField horasPorDiaField;
    @FXML
    private ChoiceBox<String> especialidadChoiceBox;
    @FXML
    private Button btnguardar;
    @FXML
    private Button btncancelar;

    public void initialize() {
        
        especialidadChoiceBox.getItems().addAll("Frenos", "Electricidad", "Motor");
    }

    @FXML
    private void cancelar(ActionEvent event) {
        
        ((Stage) btncancelar.getScene().getWindow()).close();
    }

    @FXML
    private void guardar(ActionEvent event) {
        
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String dni = dniField.getText();
        String legajo = legajoField.getText();
        String horasPorDia = horasPorDiaField.getText();
        String especialidad = especialidadChoiceBox.getValue();

        
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || legajo.isEmpty() || horasPorDia.isEmpty() || especialidad == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor, complete todos los campos.");
            alert.showAndWait();
            return;
        }

        try {
            
            int dniNum = Integer.parseInt(dni);
            double horasPorDiaNum = Double.parseDouble(horasPorDia);
            int idEspecialidad = especialidadChoiceBox.getItems().indexOf(especialidad) + 1; 

            
            Mecanico mecanico = new Mecanico();

            
            boolean success = mecanico.Insertar(idEspecialidad,legajo,nombre,apellido,dni, Double.parseDouble(horasPorDia));

            Alert alert;
            if (success) {
                alert = new Alert(Alert.AlertType.INFORMATION, "Mecánico guardado con éxito.");
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Error al guardar el mecánico.");
            }
            alert.showAndWait();

            
            ((Stage) btnguardar.getScene().getWindow()).close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "El campo 'DNI' y 'Horas por Día' deben ser números.");
            alert.showAndWait();
        }
    }

}
