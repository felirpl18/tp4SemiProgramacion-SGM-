package com.seminarioprogramaciontp3.controlador;

import com.seminarioprogramaciontp3.sgmMain;
import com.seminarioprogramaciontp3.modelo.Mecanico;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;
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

public class controladorListarMecanicos implements Initializable {

    @FXML
    private TableView<MecanicoDTO> tablaMecanicos;
    @FXML
    private TableColumn<MecanicoDTO, String> colNombre;
    @FXML
    private TableColumn<MecanicoDTO, String> colApellido;
    @FXML
    private TableColumn<MecanicoDTO, String> colEspecialidad;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;

    private final Mecanico mecanicoModelo;

    public controladorListarMecanicos() {
        mecanicoModelo = new Mecanico();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("idEspecialidad"));

        listarMecanicos();
    }

    private void listarMecanicos() {
        List<MecanicoDTO> lista = mecanicoModelo.listar();
        tablaMecanicos.getItems().setAll(lista);
    }

    @FXML
    private void switchToMecanicos() throws IOException {
        sgmMain.newWindow("Mecanicos", ((Stage) btnNuevo.getScene().getWindow()), "Nuevo Mecánico");
        listarMecanicos();
    }


    @FXML
    private void ModificarMecanico(ActionEvent event) throws IOException {
        MecanicoDTO mecanicoSeleccionado = tablaMecanicos.getSelectionModel().getSelectedItem();
        if (mecanicoSeleccionado != null) {
            
        } else {
            mostrarAlerta("Seleccione un mecanico para modificar.");
        }
    }

    @FXML
    private void EliminarMecanico(ActionEvent event) {
        MecanicoDTO mecanicoSeleccionado = tablaMecanicos.getSelectionModel().getSelectedItem();
        if (mecanicoSeleccionado != null) {
            String mensaje = "¿Está seguro que quiere eliminar este Mecánico?";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean exito = mecanicoModelo.Borrar(mecanicoSeleccionado.getIdMecanico());
                    if (exito) {
                        mostrarAlerta("Mecánico eliminado exitosamente.");
                        listarMecanicos(); 
                    } else {
                        mostrarAlerta("Error al eliminar el mecánico.");
                    }
                }
            });
        } else {
            mostrarAlerta("Seleccione un mecánico para eliminar.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
