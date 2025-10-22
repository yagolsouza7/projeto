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
    
    private void carregarTela(String fxmlfile) {
    	try {
    		Parent fxml = FXMLLoader.load(getClass().getResource(fxmlfile));
    		conteudoPane.getChildren().clear();
    		conteudoPane.getChildren().add(fxml);
    		
    		conteudoPane.setTopAnchor(fxml,0.0);
    		conteudoPane.setBottomAnchor(fxml,0.0);
    		conteudoPane.setLeftAnchor(fxml,0.0);
    		conteudoPane.setRightAnchor(fxml,0.0);
    		Scene cena =  conteudoPane.getScene();
    		
    		if (cena!=null) {
    			Stage stage = (Stage) cena.getWindow();
    			}
    		
    	} catch(Exception e) {
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
    private void abrirCalculadoraIMC() {
    	carregarTela("calcularIMC.fxml");
    }
	
    @FXML
    private void abrirGame() {
    	carregarTela("jogo.fxml");
    }
    
    @FXML
    private void abrirMedia() {
    	carregarTela("mediadenotas.fxml");
    }
    
    @FXML
    private void abrircontadorDeIdade() {
    	carregarTela("contadorDeIdade.fxml");
    }
    
	@FXML
	private void initialize() {
		//abrirPaginaInicial();
	}
}
	
