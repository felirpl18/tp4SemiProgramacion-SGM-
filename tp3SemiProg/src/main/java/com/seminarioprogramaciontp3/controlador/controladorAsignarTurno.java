
package com.seminarioprogramaciontp3.controlador;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;
import com.seminarioprogramaciontp3.dto.TitularDTO;
import com.seminarioprogramaciontp3.dto.ServicioDTO;
import com.seminarioprogramaciontp3.dto.VehiculoDTO;
import com.seminarioprogramaciontp3.modelo.Mecanico;
import com.seminarioprogramaciontp3.modelo.Titular;
import com.seminarioprogramaciontp3.modelo.Servicio;
import com.seminarioprogramaciontp3.modelo.Turno;
import com.seminarioprogramaciontp3.modelo.Vehiculo;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class controladorAsignarTurno implements Initializable {

    @FXML
    private ComboBox combobox_titulares;

    @FXML
    private ComboBox combobox_vehiculos;

    @FXML
    private ComboBox combobox_servicios;

    @FXML
    private ComboBox combobox_mecanicos;

    @FXML
    private Button btnguardar;

    @FXML
    private Button btncancelar;
    @FXML
    private DatePicker dpdia_atencion;
    @FXML
    private TextField txthora_atencion;
    @FXML
    private TextField txtmin_atencion;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        Titular titular = new Titular(); 
        List<TitularDTO> titulares = titular.listar(); 
        combobox_titulares.getItems().addAll(titulares); 

        
        Servicio servicio = new Servicio(); 
        List<ServicioDTO> servicios = servicio.listar(); 
        combobox_servicios.getItems().addAll(servicios); 

    }

    @FXML
    private void cancelar() throws IOException {
        
        ((Stage) btncancelar.getScene().getWindow()).close();
    }

    @FXML
    private void guardar() throws IOException {

        Turno turno = new Turno();
        String tit = "Error";
        String msj = "";
        Alert.AlertType alt = Alert.AlertType.ERROR;
        boolean error = false;

        
        if (combobox_titulares.getValue() == null) {
            msj += "Seleccione un Titular\n";
        }
        if (combobox_servicios.getValue() == null) {
            msj += "Seleccione un Servicio\n";
        }
        if (combobox_vehiculos.getValue() == null) {
            msj += "Seleccione un Vehículo\n";
        }
        if (combobox_mecanicos.getValue() == null) {
            msj += "Seleccione un Mecánico\n";
        }
        if (dpdia_atencion.getValue() == null) {
            msj += "Debe seleccionar la fecha y hora para el turno.\n";
        }

        
        if (msj.equals("")) {

            VehiculoDTO vehiculo = (VehiculoDTO) combobox_vehiculos.getValue();
            ServicioDTO servicio = (ServicioDTO) combobox_servicios.getValue();
            TitularDTO titular   = (TitularDTO)  combobox_titulares.getValue();

            error = !turno.insertar(
                    dpdia_atencion.getValue(),
                    LocalTime.of(
                            Integer.parseInt(txthora_atencion.getText()),
                            Integer.parseInt(txtmin_atencion.getText())
                    ),
                    false,
                    vehiculo.getId_vehiculo(),
                    servicio.getId_servicio(),
                    titular.getId_titular()
            );

            if (error) {
                tit = "Error al Asignar Turno";
                msj = "Ocurrio un error al asignar el turno";
                alt = Alert.AlertType.ERROR;
            } else {
                tit = "Asignar Turno";
                msj = "Se asigno el turno correctamente.";
                alt = Alert.AlertType.INFORMATION;
            }
        } else {
            error = true;
        }

        Alert a = new Alert(alt, msj, ButtonType.OK);
        a.setTitle(tit);
        a.setHeaderText(tit);
        a.showAndWait();

        if (!error) {
            ((Stage) btnguardar.getScene().getWindow()).close();
        }
    }


    @FXML
    private void combobox_titulares_change() throws IOException {
        TitularDTO titular = (TitularDTO) combobox_titulares.getSelectionModel().getSelectedItem();
        combobox_vehiculos.getItems().clear();

        
        Vehiculo vehiculo = new Vehiculo();
        List<VehiculoDTO> vehiculos = vehiculo.listar(titular);
        combobox_vehiculos.getItems().addAll(vehiculos);
    }

    @FXML
    private void combobox_servicios_change() throws IOException {
        ServicioDTO servicio = (ServicioDTO) combobox_servicios.getSelectionModel().getSelectedItem();
        combobox_mecanicos.getItems().clear();


        Mecanico mecanico = new Mecanico();
        List<MecanicoDTO> mecanicos = mecanico.listar(servicio.getEspecialidad());
        combobox_mecanicos.getItems().addAll(mecanicos);
    }
}
