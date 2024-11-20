package com.seminarioprogramaciontp3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.stage.Modality;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class sgmMain extends Application {

    private static Scene scene;
    private static Stage stage;

   
    @Override
    public void start(Stage stage) throws IOException {
        Logger eclipselinkLogger = Logger.getLogger("org.eclipse.persistence");
        eclipselinkLogger.setLevel(Level.OFF);
       //EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        scene = new Scene(loadFXML("MainMenu"));
        this.stage = stage;
        stage.setTitle("Sistema de Gestion Mecanica");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(sgmMain.class.getResource("/com/seminarioprogramaciontp3/vistas/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static FXMLLoader getFXMLLoader(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(sgmMain.class.getResource("/com/seminarioprogramaciontp3/vistas/" + fxml + ".fxml"));
        return fxmlLoader;
    }


    public static Stage newWindow(String vista, Stage parentw, String titulo) throws IOException {
        Scene sn = new Scene(loadFXML(vista));
        Stage st = new Stage();
        st.setScene(sn);
        st.setTitle(titulo);
        st.initOwner(parentw);
        
        st.initModality(Modality.APPLICATION_MODAL);
        st.showAndWait();
        return st;
    }

    public static Stage newWindow(FXMLLoader vista, Stage parentw, String titulo) throws IOException {
        Scene sn = new Scene(vista.load());
        Stage st = new Stage();
        st.setScene(sn);
        st.setTitle(titulo);
        st.initOwner(parentw);
        st.initModality(Modality.APPLICATION_MODAL);
        st.showAndWait();
        return st;
    }

    public static Stage newWindow(FXMLLoader vista, Stage parentw, String titulo, boolean mostrar) throws IOException {
        Scene sn = new Scene(vista.load());
        Stage st = new Stage();
        st.setScene(sn);
        st.setTitle(titulo);
        st.initOwner(parentw);
        st.initModality(Modality.APPLICATION_MODAL);
        if(mostrar){
        st.showAndWait();
        }
        return st;
    }

    public static Stage newWindow(String vista, String titulo) throws IOException {
        Scene sn = new Scene(loadFXML(vista));
        Stage st = new Stage();
        st.setScene(sn);
        st.setTitle(titulo);
        st.show();

        return st;
    }

    public static Stage newWindow(FXMLLoader vista, String titulo) throws IOException {
        Scene sn = new Scene(vista.load());
        Stage st = new Stage();
        st.setScene(sn);
        st.setTitle(titulo);
        st.show();

        return st;
    }

    public static void main(String[] args) {
        launch(args);
    }
}