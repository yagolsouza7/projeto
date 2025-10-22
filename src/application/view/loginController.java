package application.view;

import application.dao.usuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private PasswordField senha;

    @FXML
    private TextField usuario;
    
    @FXML
    private Label lblNovoUsuario;

    @FXML
    private void sair() {
        System.exit(0);
    }

    @FXML
    public void entrar() {
    	try {
            String user = usuario.getText();
            String pass = senha.getText();
            usuarioDAO dao = new usuarioDAO();

            if (dao.autenticar(user, pass)) {
            	Alert aviso;
              aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Confirmação");
                aviso.setHeaderText(null);
                aviso.setContentText("Bem-vindo ao sistema " + user);
                aviso.showAndWait();
                
                usuario.getScene().getWindow().hide();
                
                Parent root = FXMLLoader.load(getClass().getResource("aplicativo.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } else {
                Alert aviso = new Alert(Alert.AlertType.ERROR);
                aviso.setTitle("Erro");
                aviso.setHeaderText(null);
                aviso.setContentText("Usuário ou senha incorretos");
                aviso.showAndWait();
            }
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
    }
    
   
    /* isso ou @override -> indica q o codigo
    será executado assim q carregar a página */
    @FXML
    private void initialize() {
    	/* QUANDO PRESSIONAR ENTER NO CAMPO USUARIO 
    	 FOCA A EDIÇÃO NO CAMPO DE SENHA */
    usuario.setOnAction(e->{senha.requestFocus();});
    /*QUANDO PRESSIONAR ENTER NO CAMPO SENHA
      ACIONA O METODO DE ENTRAR */
    
    senha.setOnAction(e->{entrar();});
    
    lblNovoUsuario.setOnMouseClicked(event->{
		try {
			//ABRE A TELA CADASTRO USUARIO
			Parent root = FXMLLoader.load(getClass().getResource("usuario.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}catch (Exception e) {
			e.printStackTrace();
		}
	});
}
}