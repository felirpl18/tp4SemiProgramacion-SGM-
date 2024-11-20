package com.seminarioprogramaciontp3.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.seminarioprogramaciontp3.dao.FactoryDAO;
import com.seminarioprogramaciontp3.dto.TitularDTO;
import com.seminarioprogramaciontp3.dto.AseguradoraDTO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class controladorAutos implements Initializable {

    @FXML
    private Button btnguardar;
    @FXML
    private Button btncancelar;
    @FXML
    private ChoiceBox<String> cbTitular;
    @FXML
    private ChoiceBox<String> cbAseguradora;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtPoliza;
    @FXML
    private TextField txtPatente;

    private FactoryDAO factoryDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factoryDAO = FactoryDAO.getFactory("Factory");
        cargarTitulares();
        cargarAseguradoras();
    }

    private void cargarTitulares() {
        List<TitularDTO> titulares = factoryDAO.getTitularDao().listar();
        for (TitularDTO titular : titulares) {
            cbTitular.getItems().add(titular.getNombre() + " " + titular.getApellido()); 
        }
    }

    private void cargarAseguradoras() {
        List<AseguradoraDTO> aseguradoras = factoryDAO.getAseguradoraDao().listar();
        for (AseguradoraDTO aseguradora : aseguradoras) {
            cbAseguradora.getItems().add(aseguradora.getNombre());
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        ((Stage) btncancelar.getScene().getWindow()).close();
    }


    @FXML
    private void guardarVehiculo(ActionEvent event) {
        String nombreTitular = cbTitular.getValue(); 
        String nombreAseguradora = cbAseguradora.getValue(); 
        String marca = txtMarca.getText(); 
        String modelo = txtModelo.getText(); 
        String nroPoliza = txtPoliza.getText(); 
        String patente = txtPatente.getText(); 

        
        int idTitular = obtenerIdTitular(nombreTitular);
        if (idTitular == -1) {
            mostrarMensaje("Error", "El titular seleccionado no es válido.");
            return; 
        }

        
        int idAseguradora = obtenerIdAseguradora(nombreAseguradora);
        if (idAseguradora == -1) {
            mostrarMensaje("Error", "La aseguradora seleccionada no es válida.");
            return; 
        }

        
        boolean exito = factoryDAO.getVehiculoDao().insertar(idTitular, idAseguradora, patente, marca, modelo, nroPoliza);

        if (exito) {
            mostrarMensaje("Éxito", "Vehículo guardado exitosamente.");
            ((Stage) btnguardar.getScene().getWindow()).close(); 
        } else {
            mostrarMensaje("Error", "Error al guardar el vehículo.");
        }
    }


    private int obtenerIdTitular(String nombreCompleto) {
        List<TitularDTO> titulares = factoryDAO.getTitularDao().listar();
        for (TitularDTO titular : titulares) {
            String nombreApellido = titular.getNombre() + " " + titular.getApellido();
            if (nombreApellido.equals(nombreCompleto)) {
                return titular.getId_titular();
            }
        }
        
        mostrarMensaje("Error", "El titular seleccionado no es válido");
        return -1; 
    }


    private int obtenerIdAseguradora(String nombreAseguradora) {
        List<AseguradoraDTO> aseguradoras = factoryDAO.getAseguradoraDao().listar();
        for (AseguradoraDTO aseguradora : aseguradoras) {
            if (aseguradora.getNombre().equals(nombreAseguradora)) {
                return aseguradora.getId_aseguradora();
            }
        }
        return -1; 
    }



    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
