/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.seminarioprogramaciontp3;

import com.seminarioprogramaciontp3.dto.AseguradoraDTO;
import com.seminarioprogramaciontp3.dto.TurnoDTO;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.testfx.api.FxAssert.verifyThat;


@TestMethodOrder(OrderAnnotation.class)
public class TestFuncionales extends TestFxConfig {

   @Test
    @Order(1)
    public void listarTurnos() {
        sleep(1000);
        clickOn("#menup");
        sleep(1000);
        clickOn("#Turnos");
        TableView<TurnoDTO> tbViewTurnos = (TableView<TurnoDTO>) find("#tbViewTurnos");
        Assertions.assertTrue(tbViewTurnos.getItems().size() > 0);
       sleep(10000);

   }
    
    @Test
    @Order(2)
    public void asistenciaSI() {
        sleep(1000);
        clickOn("#menup");
        sleep(1000);
        clickOn("#Turnos");
        TableView<TurnoDTO> tbViewTurnos = (TableView<TurnoDTO>) find("#tbViewTurnos");
        tbViewTurnos.getSelectionModel().select(0);
        sleep(1000);
        clickOn("#brnAsistencia");
        sleep(1000);
        clickOn("Sí");
        sleep(5000);
        tbViewTurnos = (TableView<TurnoDTO>) find("#tbViewTurnos");
        TurnoDTO item = tbViewTurnos.getItems().get(0);
        Boolean nuevaAsistencia = item.getAsistencia();
        Assertions.assertEquals(true, nuevaAsistencia);
    }
    @Test
    @Order(3)
    public void asistenciaNO() {
        sleep(1000);
        clickOn("#menup");
        sleep(1000);
        clickOn("#Turnos");
        TableView<TurnoDTO> tbViewTurnos = (TableView<TurnoDTO>) find("#tbViewTurnos");
        tbViewTurnos.getSelectionModel().select(0);
        sleep(1000);
        clickOn("#brnAsistencia");
        sleep(1000);
        clickOn("No");
        sleep(5000);
        tbViewTurnos = (TableView<TurnoDTO>) find("#tbViewTurnos");
        TurnoDTO item = tbViewTurnos.getItems().get(0);
        Boolean nuevaAsistencia = item.getAsistencia();
        Assertions.assertEquals(false, nuevaAsistencia);
    }


    @Test
    @Order(4)
    public void eliminarTurno(){
        sleep(1000);
        clickOn("#menup");
        sleep(1000);
        clickOn("#Turnos");
           TableView<TurnoDTO> tbViewTurnos = (TableView<TurnoDTO>)find("#tbViewTurnos");
           int prevSize = tbViewTurnos.getItems().size();
           tbViewTurnos.getSelectionModel().selectFirst();
           clickOn("#btnEliminarTurno");
           sleep(1000);
           clickOn("Sí");
           sleep(1000);
           Assertions.assertEquals(prevSize-1,tbViewTurnos.getItems().size());
           sleep(1000);

    }



    @Test
    @Order(5)
    public void añadirTurno(){
        sleep(1000);
        clickOn("#menup");
        sleep(1000);
        clickOn("#Turnos");
        sleep(1000);
        clickOn("#btnAsignarTurno");
        sleep(1000);
        clickOn("#combobox_titulares").type(KeyCode.DOWN).type(KeyCode.ENTER);
        clickOn("#combobox_vehiculos").type(KeyCode.DOWN).type(KeyCode.ENTER);
        clickOn("#combobox_servicios").type(KeyCode.DOWN).type(KeyCode.ENTER);
        clickOn("#combobox_mecanicos").type(KeyCode.DOWN).type(KeyCode.ENTER);
        LocalDate fechaSeleccionada = LocalDate.of(2024, 6, 17);
        interact(() -> {
            lookup("#dpdia_atencion").queryAs(DatePicker.class).setValue(fechaSeleccionada);
        });
        clickOn("#txthora_atencion").write("10");
        clickOn("#txtmin_atencion").write("30");
        clickOn("#btnguardar");
        sleep(2000);
        clickOn("Aceptar");
        sleep(5000);
    }

    @Test
    @Order(6)
    public void añadirAseguradora(){
        sleep(1000);
        clickOn("#menup");
        sleep(1000);
        clickOn("#Gestion");
        sleep(1000);
        clickOn("#Aseguradoras");
        sleep(1000);
        clickOn("#btnNuevo");
        sleep(1000);
            clickOn("#txtNombre").write("Aseguradora");
            sleep(2000);
            clickOn("#txtEmail").write("contacto@a.com");
            sleep(2000);
            clickOn("#txtCuit").write("1234890");
            sleep(3000);
        clickOn("#btnguardar");
        sleep(2000);
        clickOn("Aceptar");
        sleep(10000);
    }

    @Test
    @Order(7)
    public void eliminarAseguradora(){
        sleep(1000);
        clickOn("#menup");
        sleep(1000);
        clickOn("#Gestion");
        sleep(1000);
        clickOn("#Aseguradoras");
        sleep(1000);
        TableView<AseguradoraDTO> tablaAseguradoras = (TableView<AseguradoraDTO>)find("#tablaAseguradoras");
        tablaAseguradoras.getSelectionModel().selectFirst();
        sleep(1000);
        clickOn("#btnEliminar");
        sleep(1000);
        clickOn("Sí");
        sleep(1000);
        clickOn("Aceptar");
        sleep(10000);
    }

    @Test
    @Order(8)
    public void imprimirFicha(){
        sleep(1000);
        clickOn("#menup");
        sleep(1000);
        clickOn("#Turnos");
        TableView<TurnoDTO> tbViewTurnos = (TableView<TurnoDTO>)find("#tbViewTurnos");
        int prevSize = tbViewTurnos.getItems().size();
        tbViewTurnos.getSelectionModel().selectFirst();
        clickOn("#btnImprimirComprobante");
        sleep(1000);
        clickOn("Aceptar");
        sleep(5000);
    }
}
