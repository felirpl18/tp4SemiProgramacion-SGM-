package com.seminarioprogramaciontp3.controlador;
import com.seminarioprogramaciontp3.dto.EspecialidadDTO;
import com.seminarioprogramaciontp3.dto.MecanicoDTO;
import com.seminarioprogramaciontp3.dto.ServicioDTO;
import com.seminarioprogramaciontp3.dto.TitularDTO;
import com.seminarioprogramaciontp3.dto.TurnoDTO;
import com.seminarioprogramaciontp3.dto.VehiculoDTO;
import com.seminarioprogramaciontp3.sgmMain;
import com.seminarioprogramaciontp3.modelo.Especialidad;
import com.seminarioprogramaciontp3.modelo.Mecanico;
import com.seminarioprogramaciontp3.modelo.Turno;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class controladorTurnos implements Initializable {

    @FXML
    private Button btnAsignarTurno;
    @FXML
    private Button btnEliminarTurno;
    @FXML
    private Button brnAsistencia;
    @FXML
    private Button btnFichaMecanica;
    @FXML
    private Button btnImprimirComprobante;
    @FXML
    private Button bttnFiltrar;
    @FXML
    private ComboBox combobox_especialidades;
    @FXML
    private ComboBox combobox_mecanicos;
    @FXML
    private TableView<TurnoDTO> tbViewTurnos;

    @FXML
    private TableColumn<TurnoDTO, String> colAsistencia;

    @FXML
    private TableColumn<TurnoDTO, Date> colFecha;

    @FXML
    private TableColumn<TurnoDTO, LocalTime> colHorario;

    @FXML
    private TableColumn<MecanicoDTO, String> colMecanico;

    @FXML
    private TableColumn<ServicioDTO, String> colServicio;

    @FXML
    private TableColumn<TitularDTO, String> colTitular;

    @FXML
    private TableColumn<VehiculoDTO, String> colVehiculo;

    @FXML
    private DatePicker dtpFecha;
    @FXML
    private Button btnlimpiar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            Especialidad especialidad = new Especialidad();
            List<EspecialidadDTO> especialidades = especialidad.listar();
            combobox_especialidades.getItems().addAll(especialidades);

           
            Mecanico mecanico = new Mecanico();
            List<MecanicoDTO> mecanicos = mecanico.listar();
            combobox_mecanicos.getItems().addAll(mecanicos);

            
            bttnFiltrar_OnClick();


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btnlimpiar_OnClick() throws IOException {
        dtpFecha.setValue(null);
        combobox_mecanicos.setValue(null);
        bttnFiltrar_OnClick();

    }

    @FXML
    private void bttnFiltrar_OnClick() throws IOException {
        tbViewTurnos.getItems().clear();
        MecanicoDTO mecanico = (MecanicoDTO) combobox_mecanicos.getSelectionModel().getSelectedItem();
        LocalDate fechaDtp = dtpFecha.getValue();

        Turno turno = new Turno(); 
        List<TurnoDTO> turnos;
        if (mecanico != null) {
            if (fechaDtp != null) {
                turnos = turno.listar(mecanico, fechaDtp);
            } else {
                turnos = turno.listar(mecanico);
            }
        } else {
            if (fechaDtp != null) {
                turnos = turno.listar(fechaDtp);
            } else {
                turnos = turno.listar();
            }
        }

        colFecha.setCellValueFactory(new PropertyValueFactory<>("dia_atencion"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("hora_atencion"));
        colAsistencia.setCellValueFactory(t -> {
            boolean vf = t.getValue().getAsistencia();
            String valor = vf ? "SI" : "NO";
            return new ReadOnlyStringWrapper(valor);
        });
        colMecanico.setCellValueFactory(new PropertyValueFactory<>("Mecanico"));
        colServicio.setCellValueFactory(new PropertyValueFactory<>("Servicio"));
        colTitular.setCellValueFactory(new PropertyValueFactory<>("Titular"));
        colVehiculo.setCellValueFactory(new PropertyValueFactory<>("Vehiculo"));
        ObservableList<TurnoDTO> listObsevable = FXCollections.observableList(turnos);
        tbViewTurnos.setItems(listObsevable);
    }

    @FXML
    private void combobox_especialidad_change() throws IOException {
        EspecialidadDTO especilidad = (EspecialidadDTO) combobox_especialidades.getSelectionModel().getSelectedItem();

        combobox_mecanicos.getItems().clear();
        Mecanico mecanico = new Mecanico();
        List<MecanicoDTO> mecanicos = mecanico.listar(especilidad);
        combobox_mecanicos.getItems().addAll(mecanicos); 
    }

    @FXML
    private void switchToAsignarTurno() throws IOException {
        Stage wd = (Stage) btnlimpiar.getScene().getWindow();

        sgmMain.newWindow("AsignarTurno", ((Stage) btnAsignarTurno.getScene().getWindow()), "Asignar Turno");

        wd.close();
        wd.show();
        btnlimpiar_OnClick();
    }

    @FXML
    private void eliminarTurno(ActionEvent event) {

        String mensaje = "¿Está seguro que quiere eliminar este Turno?";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {

                if (tbViewTurnos.getSelectionModel().getSelectedCells().isEmpty()) 
                {
                    Alert alertTurnoNoEncontrado = new Alert(AlertType.ERROR);
                    alertTurnoNoEncontrado.setTitle("Error");
                    alertTurnoNoEncontrado.setHeaderText("Error");
                    alertTurnoNoEncontrado.setContentText("No se seleccionó ningún turno.");
                    alertTurnoNoEncontrado.showAndWait();

                    return; 
                }

                Turno turno = new Turno();
                TablePosition pos = tbViewTurnos.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();

               
                TurnoDTO item = tbViewTurnos.getItems().get(row);

                if (item != null) {
                    if (!turno.borrar(item.getId_turno())) {

                        Alert alert2 = new Alert(AlertType.ERROR);
                        alert2.setTitle("Error");
                        alert2.setHeaderText("Error al eliminar");
                        alert2.setContentText("Ocurrio un error al eliminar.");
                        alert2.showAndWait();

                    } else {
                        try {
                            bttnFiltrar_OnClick();
                        } catch (IOException ex) {
                            Logger.getLogger(controladorTurnos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
    }

    @FXML
    private void confirmarAsistencia(ActionEvent event) {
        String mensaje = "¿El titular asistió al turno seleccionado?";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, mensaje, ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait().ifPresent(response -> {
            if (response != ButtonType.CANCEL) {

                if (tbViewTurnos.getSelectionModel().getSelectedCells().isEmpty()) 
                {
                    Alert alertTurnoNoEncontrado = new Alert(AlertType.ERROR);
                    alertTurnoNoEncontrado.setTitle("Error");
                    alertTurnoNoEncontrado.setHeaderText("Error");
                    alertTurnoNoEncontrado.setContentText("No se seleccionó ningún turno.");
                    alertTurnoNoEncontrado.showAndWait();

                    return;
                }

                boolean asistencia = (response == ButtonType.YES);

                Turno turno = new Turno();

                TablePosition pos = tbViewTurnos.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();

             
                TurnoDTO item = tbViewTurnos.getItems().get(row);

                if (item != null) {
                    if (!turno.modificarAsistencia(item.getId_turno(), asistencia)) {

                        Alert alert2 = new Alert(AlertType.ERROR);
                        alert2.setTitle("Error");
                        alert2.setHeaderText("Error al actualizar");
                        alert2.setContentText("Ocurrio un error al actualizar.");
                        alert2.showAndWait();

                    } else {
                        try {
                            bttnFiltrar_OnClick();
                        } catch (IOException ex) {
                            Logger.getLogger(controladorTurnos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        }
        );
    }

    @FXML
    private void imprimirComprobante(ActionEvent event) throws IOException {
        String mensaje = "¿Desea crear la Ficha Mecanica?";
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensaje, ButtonType.OK, ButtonType.CANCEL);

        alert.showAndWait();
        if (alert.getResult() != ButtonType.OK) { 
            return;
        }

       
        if (tbViewTurnos.getSelectionModel().getSelectedCells().isEmpty()) 
        {
            Alert alertTurnoNoEncontrado = new Alert(AlertType.ERROR);
            alertTurnoNoEncontrado.setTitle("Error");
            alertTurnoNoEncontrado.setHeaderText("Error");
            alertTurnoNoEncontrado.setContentText("No se seleccionó ningún turno.");
            alertTurnoNoEncontrado.showAndWait();

            return; 
        }

        TablePosition pos = tbViewTurnos.getSelectionModel().getSelectedCells().get(0); 
        int row = pos.getRow();

        
        TurnoDTO item = tbViewTurnos.getItems().get(row);

        
        
        String misDocumentos = System.getProperty("java.io.tmpdir"); 
        String rutaArchivo = misDocumentos + "ComprobanteTurno_" + item.getId_turno() + ".pdf"; 

        
        try ( PDDocument doc = new PDDocument()) {

            PDPage page = new PDPage();
            doc.addPage(page);

            float fontSize = 20;
            float leading = 1.5f * fontSize;

            float margin = 72; 
            float startX = page.getMediaBox().getLowerLeftX() + margin;
            float startY = page.getMediaBox().getUpperRightY() - margin;

            try ( PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                contents.beginText();
                contents.setFont(PDType1Font.TIMES_ROMAN, fontSize); 
                contents.newLineAtOffset(startX, startY);

                
                contents.showText("Fecha: " + item.getDia_atencion());
                contents.newLineAtOffset(0, -leading);

                contents.showText("Hora: " + item.getHora_atencion());
                contents.newLineAtOffset(0, -leading);

                if (item.getAsistencia() == false) {
                    contents.showText("Asistencia: " + "No");
                } else {
                    contents.showText("Asistencia: " + "Si");
                }
                contents.newLineAtOffset(0, -leading);

                contents.showText("Dueño del Vehiculo: " + item.getTitular());
                contents.newLineAtOffset(0, -leading);

                contents.showText("Automovil: " + item.getVehiculo());
                contents.newLineAtOffset(0, -leading);

                contents.showText("Reparacion: " + item.getServicio());
                contents.newLineAtOffset(0, -leading);

                contents.showText("Mecánico: " + item.getMecanico());
                contents.newLineAtOffset(0, -leading);

                contents.endText();
                contents.close();
            }

            doc.save(rutaArchivo); 

            try {
                
                File file = new File(rutaArchivo);
                if (!Desktop.isDesktopSupported())
                {
                    System.out.println("not supported");
                    return;
                }
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) 
                {
                    desktop.open(file);
                }
            } catch (IOException e) {
                Alert alertTurnoNoEncontrado = new Alert(AlertType.ERROR);
                alertTurnoNoEncontrado.setTitle("Error");
                alertTurnoNoEncontrado.setHeaderText("Error");
                alertTurnoNoEncontrado.setContentText("Error al acceder al archivo PDF.");
                alertTurnoNoEncontrado.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            Logger.getLogger(controladorTurnos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
