package application.view;

	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import application.view.calculadoraController;

	public class calcularIMCController {

	    @FXML
	    private Button btnCalcularIMC;

	    @FXML
	    private Label lblResultado;

	    @FXML
	    private TextField txtAltura;

	    @FXML
	    private TextField txtNome;

	    @FXML
	    private TextField txtPeso;
	    
	    private void calcularIMC() {
	    	String nome = txtNome.getText();
	    	double peso = Double.valueOf(txtPeso.getText());
	    	double altura = calculadoraController.StrToDbl(txtAltura.getText());
	    	double imc = peso / (altura*altura);
	    	lblResultado.setText(String.format(txtNome.getText()+" O seu IMC é %.2f",imc)+" "+classificarIMC(imc));
	}
	    
	    private String classificarIMC(double imc) {
			if(imc < 18.5) {
				return "Abaixo do Peso";
			} else if (imc < 24.9) {
				return "Peso Normal";			
			} else if (imc < 34.9) {
				return "Obsidade Grau I";
			} else if (imc < 39.9) {
				return "Obsidade Grau II";
			} else {
				return "Obsidade Grau III";
			}
		}

	}
