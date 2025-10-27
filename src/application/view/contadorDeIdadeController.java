package application.view;

import java.time.LocalDate;
import java.time.Period;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class contadorDeIdadeController {

	    @FXML
	    private Button btnIdade;

	    @FXML
	    private DatePicker dateNascimento;

	    @FXML
	    private Label lblResultado;

	    @FXML
	    private TextField txtNome;

	    @FXML
	    private void calcularIdade() {
	        String nome = txtNome.getText();
	        LocalDate dataNascimento = dateNascimento.getValue();
	        
	        if (dataNascimento != null && nome != null && !nome.isEmpty()) {
	            LocalDate hoje = LocalDate.now();
	            Period idade = Period.between(dataNascimento, hoje);

	            long diasVividos = java.time.temporal.ChronoUnit.DAYS.between(dataNascimento, hoje);

	            String diaSemanaNascimento = dataNascimento.getDayOfWeek().getDisplayName(
	                java.time.format.TextStyle.FULL, 
	                java.util.Locale.getDefault()
	            );

	            lblResultado.setText(
	                nome + ", você tem " + idade.getYears() + " anos.\n" +
	                "Você viveu " + diasVividos + " dias.\n" +
	                "Você nasceu em uma " + diaSemanaNascimento + "."
	            );
	            
	        } else {
	            lblResultado.setText("Por favor, preencha todos os campos.");
	        }
	    }
}