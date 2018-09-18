/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import modelo.Fuzzy;
import principal.Principal;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class ControladorPrincipal implements Initializable {

    @FXML
    private JFXTextField tamanho;

    @FXML
    private JFXTextField parametro;

    Fuzzy f = new Fuzzy();

    float[] resultado;

    static ControladorPrincipal controlador;
   
    @FXML
    private StackPane stack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlador = this;
    }

    @FXML
    private void verificar(ActionEvent event) {
        float t = Float.parseFloat(tamanho.getText());
        float p = Float.parseFloat(parametro.getText());
        f.modelagem();
        resultado = f.tratamento(t, p);
        mudarTela();
        //se quiser colocar resultado em uma caixa de dialogo
        //String conteudo = "Excelente = " + String.valueOf(resultado[0]) + "\nMuito Bom = " + String.valueOf(resultado[1]) + "\nBom = " + String.valueOf(resultado[2]) + "\nRegular = " + String.valueOf(resultado[3]) + "\nPreocupante = " + String.valueOf(resultado[4]);
        //gerarDialogo("Avaliação da Qualidade de um Método OO", conteudo, stack);
    }

    private void mudarTela() {
        controlador = this; //pegando o contexto do outro controlador.
        Parent tela2;
        try {
            tela2 = FXMLLoader.load(getClass().getResource("/visao/Resultado.fxml"));
        } catch (NullPointerException | IOException ex) {
            return;
        }
        Scene scene = new Scene(tela2);
        Principal.getStagePrincipal().setScene(scene);

    }

    public static void gerarDialogo(String title, String content, StackPane stack) {
        Text titulo = new Text();
        titulo.setText(title);
        titulo.setFont(Font.font("System", FontWeight.BOLD, 16));

        JFXDialogLayout dialogContent = new JFXDialogLayout();
        dialogContent.setHeading(titulo);
        dialogContent.setBody(new Text(content));

        JFXButton close = new JFXButton("Fechar");
        close.setButtonType(JFXButton.ButtonType.FLAT);
        close.setStyle("-fx-background-color:  #4059a9; -fx-text-fill: white;");

        dialogContent.setActions(close);
        JFXDialog dialog = new JFXDialog(stack, dialogContent, JFXDialog.DialogTransition.CENTER);
        close.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent __) {
                dialog.close();
            }
        });
        dialog.show();
    }

}
