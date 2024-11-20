package com.seminarioprogramaciontp3.controlador;
import com.seminarioprogramaciontp3.sgmMain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class controladorMenu implements Initializable {

    @FXML
    private MenuBar menup;
    @FXML
    private Menu mturnos;
    @FXML
    private MenuItem smgestionturnos;
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    private void CloseApp() throws IOException {
        Platform.exit();
    }

    @FXML
    private void switchToMecanicosListado() throws IOException {
    
        sgmMain.newWindow("MecanicosListado", (Stage) menup.getScene().getWindow(), "Gestionar Mecánicos");
    }

    @FXML
    private void switchToTrabajos() throws IOException {
        
        sgmMain.newWindow("Trabajos", ((Stage) menup.getScene().getWindow()), "Trabajos del Día");
    }

    @FXML
    private void switchToTurnos() throws IOException {
        sgmMain.newWindow("Turnos", ((Stage) menup.getScene().getWindow()), "Turnos");
    }

    @FXML
    private void switchToGestionEspecialidad() throws IOException {

        sgmMain.newWindow("GestionEspecialidad", ((Stage) menup.getScene().getWindow()), "Gestionar Especialidades");
    }

    @FXML
    private void switchToGestionAseguradora() throws IOException {

        sgmMain.newWindow("GestionAseguradora", ((Stage) menup.getScene().getWindow()), "Gestionar Aseguradoras");
    }

    @FXML
    private void switchToGestionServicio() throws IOException {

        sgmMain.newWindow("GestionServicio", ((Stage) menup.getScene().getWindow()), "Gestionar Servicios");
    }

    @FXML
    private void switchToPrestacionesListado() throws IOException {

        sgmMain.newWindow("PrestacionesListado", ((Stage) menup.getScene().getWindow()), "Listado de Prestaciones");
    }

    @FXML
    private void switchToTitularesListado() throws IOException {

        sgmMain.newWindow("TitularesListado", ((Stage) menup.getScene().getWindow()), "Gestionar Titulares");
    }
 
    @FXML
    private void switchToVehiculosListado() throws IOException {

        sgmMain.newWindow("VehiculosListado", ((Stage) menup.getScene().getWindow()), "Gestionar Vehiculos");
    }   
    
    @FXML
    private void switchToReporteMensual() throws IOException {

        sgmMain.newWindow("ReporteMensual", ((Stage) menup.getScene().getWindow()), "Generar Informe Mensual");
    }
}
