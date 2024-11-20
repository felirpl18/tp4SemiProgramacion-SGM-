
package com.seminarioprogramaciontp3.controlador;
import com.seminarioprogramaciontp3.dto.VehiculoDTO;
import com.seminarioprogramaciontp3.sgmMain;
import com.seminarioprogramaciontp3.modelo.Vehiculo;
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

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class controladorListarAutos implements Initializable {

    @FXML
    private TableView<VehiculoDTO> lista;
    @FXML
    private TableColumn<VehiculoDTO, String> colTitular;
    @FXML
    private TableColumn<VehiculoDTO, String> colPoliza;
    @FXML
    private TableColumn<VehiculoDTO, String> colAseguradora;
    @FXML
    private TableColumn<VehiculoDTO, String> colPatente;
    @FXML
    private TableColumn<VehiculoDTO, String> colMarca;
    @FXML
    private TableColumn<VehiculoDTO, String> colModelo;
    @FXML
    private Button btningreso1;
    @FXML
    private Button btningreso;

    private Vehiculo vehiculo;

    public controladorListarAutos() {
        vehiculo = new Vehiculo();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colTitular.setCellValueFactory(new PropertyValueFactory<>("nombreTitular"));
        colPoliza.setCellValueFactory(new PropertyValueFactory<>("poliza"));
        colAseguradora.setCellValueFactory(new PropertyValueFactory<>("nombreAseguradora"));
        colPatente.setCellValueFactory(new PropertyValueFactory<>("patente"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        listarVehiculos();
    }

    private void listarVehiculos() {
        List<VehiculoDTO> listaVehiculos = vehiculo.listar();
        lista.getItems().setAll(listaVehiculos);
    }

    @FXML
    private void switchToVehiculos() throws IOException {
        sgmMain.newWindow("Vehiculos", ((Stage) btningreso1.getScene().getWindow()), "Nuevo Vehículo");
        listarVehiculos();
    }

    @FXML
    private void eliminar(ActionEvent event) {
        VehiculoDTO vehiculoSeleccionado = lista.getSelectionModel().getSelectedItem();
        if (vehiculoSeleccionado != null) {
            String mensaje = "¿Está seguro que quiere eliminar este Vehículo?";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean exito = vehiculo.borrar(vehiculoSeleccionado.getId_vehiculo());
                    if (exito) {
                        mostrarAlerta("Vehículo eliminado exitosamente.");
                        listarVehiculos();
                    } else {
                        mostrarAlerta("Error al eliminar el vehículo.");
                    }
                }
            });
        } else {
            mostrarAlerta("Seleccione un vehículo para eliminar.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
