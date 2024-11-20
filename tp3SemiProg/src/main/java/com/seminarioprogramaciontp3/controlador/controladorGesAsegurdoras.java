package com.seminarioprogramaciontp3.controlador;
import com.seminarioprogramaciontp3.sgmMain;
import com.seminarioprogramaciontp3.modelo.Aseguradora;
import com.seminarioprogramaciontp3.dto.AseguradoraDTO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class controladorGesAsegurdoras implements Initializable {

    @FXML
    private TableView<AseguradoraDTO> tablaAseguradoras;
    @FXML
    private TableColumn<AseguradoraDTO, String> colNombre;
    @FXML
    private TableColumn<AseguradoraDTO, String> colEmail;
    @FXML
    private TableColumn<AseguradoraDTO, String> colCuit;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;

    private final Aseguradora aseguradoraModelo;

    public controladorGesAsegurdoras() {
        aseguradoraModelo = new Aseguradora();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCuit.setCellValueFactory(new PropertyValueFactory<>("cuit"));

        listarAseguradoras();
    }

    private void listarAseguradoras() {
        List<AseguradoraDTO> lista = aseguradoraModelo.listar();
        tablaAseguradoras.getItems().setAll(lista);
    }

    @FXML
    private void NuevaAseguradora(ActionEvent event) throws IOException {
        sgmMain.newWindow("Aseguradora", ((Stage) btnNuevo.getScene().getWindow()), "Nueva Aseguradora");
        listarAseguradoras();
    }

    @FXML
    private void ModificarAseguradora(ActionEvent event) throws IOException {
        AseguradoraDTO aseguradoraSeleccionada = tablaAseguradoras.getSelectionModel().getSelectedItem();
        if (aseguradoraSeleccionada != null) {
            
        } else {
            mostrarAlerta("Seleccione una aseguradora para modificar.");
        }
    }

    @FXML
    private void EliminarAseguradora(ActionEvent event) {
        AseguradoraDTO aseguradoraSeleccionada = tablaAseguradoras.getSelectionModel().getSelectedItem();
        if (aseguradoraSeleccionada != null) {
            String mensaje = "¿Está seguro que quiere eliminar esta Aseguradora?";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean exito = aseguradoraModelo.borrar(aseguradoraSeleccionada.getId_aseguradora());
                    if (exito) {
                        mostrarAlerta("Aseguradora eliminada exitosamente.");
                        listarAseguradoras(); 
                    } else {
                        mostrarAlerta("Error al eliminar la aseguradora.");
                    }
                }
            });
        } else {
            mostrarAlerta("Seleccione una aseguradora para eliminar.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
