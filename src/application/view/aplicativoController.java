package application.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class aplicativoController {

    @FXML
    private AnchorPane conteudoPane;

    private void carregarTela(String fxmlFile) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource(fxmlFile));
            conteudoPane.getChildren().clear();
            conteudoPane.getChildren().add(fxml);

            conteudoPane.setTopAnchor(fxml, 0.0);
            conteudoPane.setBottomAnchor(fxml, 0.0);
            conteudoPane.setLeftAnchor(fxml, 0.0);
            conteudoPane.setRightAnchor(fxml, 0.0);

            Scene cena = conteudoPane.getScene();
            if (cena != null) {
                Stage stage = (Stage) cena.getWindow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirPaginaInicial() {
        carregarTela("aplicativo.fxml");
    }

    @FXML
    private void abrirCalculadora() {
        carregarTela("calculadora.fxml");
    }

    @FXML
    private void initialize() {
    	subtrair.setOnAction(e->{Subtrair();});
		multiplicar.setOnAction(e->{Multiplicar();});
		divisao.setOnAction(e->{Divisao();});
		btnReset.setOnAction(e->{
			txtNumero1.setText("0");
			txtNumero2.setText("0");
			btnResultado.setText("?");
		});
		}
    }
		txtNumero1.textProperty().addListener()
		(observable, oldValue, newValue) ->{
		txtNumero1.setText(newValue,replaceAll("[^\\d]",""));
		));
		
		txtNumero2.textProperty().addListener()
		(observable, oldValue, newValue) ->{
		txtNumero2.setText(newValue,replaceAll("[^\\d]",""));
		));
    }
}
		
}