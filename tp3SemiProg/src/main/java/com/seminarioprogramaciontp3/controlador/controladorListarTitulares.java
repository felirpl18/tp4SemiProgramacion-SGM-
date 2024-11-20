
package com.seminarioprogramaciontp3.controlador;

import com.seminarioprogramaciontp3.dto.TitularDTO;
import com.seminarioprogramaciontp3.sgmMain;
import com.seminarioprogramaciontp3.modelo.Titular;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class controladorListarTitulares implements Initializable {

    @FXML
    private Button btningreso1;
    @FXML
    private Button btningreso;
    @FXML
    private TableView<TitularDTO> lista;
    @FXML
    private TableColumn<TitularDTO, String> colNombre;
    @FXML
    private TableColumn<TitularDTO, String> colApellido;
    @FXML
    private TableColumn<TitularDTO, String> colNroDoc;
    @FXML
    private TableColumn<TitularDTO, String> colTelefono;

    private Titular titularModelo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titularModelo = new Titular();

        
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colNroDoc.setCellValueFactory(new PropertyValueFactory<>("nro_doc"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        
        cargarDatos();
    }

    private void cargarDatos() {
        List<TitularDTO> titulares = titularModelo.listar();
        lista.getItems().clear();
        lista.getItems().addAll(titulares);
    }

    @FXML
    private void switchToTitulares() throws IOException {
        sgmMain.newWindow("Titulares",((Stage) lista.getScene().getWindow()),"Nuevo Titular");
        cargarDatos();
    }

    @FXML
    private void eliminar(ActionEvent event) {
        TitularDTO titularSeleccionado = lista.getSelectionModel().getSelectedItem();
        if (titularSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("¿Está seguro de que desea eliminar este titular?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Titular titular = new Titular();
                boolean eliminado = titular.borrar(titularSeleccionado.getId_titular());
                if (eliminado) {
                    cargarDatos(); 
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setContentText("Titular eliminado con éxito.");
                    successAlert.showAndWait();
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setContentText("Error al eliminar el titular.");
                    errorAlert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Seleccione un titular para eliminar.");
            alert.showAndWait();
        }
    }
}
