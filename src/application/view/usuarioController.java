package application.view;

import application.dao.usuarioDAO;
import application.model.usuarioModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class usuarioController {

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField txtSenha;
    
    public void Salvar() {
    	try {
    		usuarioDAO dao = new usuarioDAO();
    		
    		String nome=txtNome.getText();
    		String login=txtLogin.getText();
    		String senha=txtSenha.getText();
    		usuarioModel usuarioNovo= new usuarioModel(0,nome,login,senha);
    	boolean cadastrado = dao.inserirUsuario(usuarioNovo);
    	Alert mensagem;
    	mensagem=new Alert(Alert.AlertType.INFORMATION);
    	if (cadastrado) {
    	// mensagem de cadastro realizado
    		mensagem.setTitle("Confirmação");
    		mensagem.setHeaderText(null);
    		mensagem.setContentText("Cadastro realizado com sucesso.");
    		mensagem.showAndWait();
    		//limpar campos apos confirmação do cadastro
    		txtNome.setText("");
    		txtLogin.setText("");
    		txtSenha.setText("");
    	}else{
    	// mensagem de erro ao cadastrar
    		mensagem.setTitle("Erro");
    		mensagem.setHeaderText(null);
    		mensagem.setContentText("Erro ao realizar cadastro.");
    		mensagem.showAndWait();
    	}
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}