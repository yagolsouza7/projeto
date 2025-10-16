package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class calculadoraController {

    @FXML
    private Label btnResultado;

    @FXML
    private Button dividir;

    @FXML
    private Button multiplicar;

    @FXML
    private Button resetar;

    @FXML
    private Button somar;

    @FXML
    private Button subtrair;

    @FXML
    private TextField txtNumero1;

    @FXML
    private TextField txtNumero2;

    @FXML
    private void initialize() {
        // iniciar programa com valores zerados
        txtNumero1.setText("0");
        txtNumero2.setText("0");

        // eventos dos botões
        subtrair.setOnAction(e -> Subtrair());
        multiplicar.setOnAction(e -> Multiplicar());
        dividir.setOnAction(e -> Dividir());
        resetar.setOnAction(e -> {
            txtNumero1.setText("0");
            txtNumero2.setText("0");
            btnResultado.setText("?");
        });

        // restringe a entrada para números e ponto
        txtNumero1.textProperty().addListener((observable, oldValue, newValue) -> {
            txtNumero1.setText(newValue.replaceAll("[^\\d.]", ""));
        });

        txtNumero2.textProperty().addListener((observable, oldValue, newValue) -> {
            txtNumero2.setText(newValue.replaceAll("[^\\d.]", ""));
        });
    }

    public void Somar() {
        double numero1;
        double numero2;
        try {
            numero1 = Double.valueOf(txtNumero1.getText());
        } catch (Exception e) {
            numero1 = 0;
            txtNumero1.setText("0");
        }

        try {
            numero2 = Double.valueOf(txtNumero2.getText());
        } catch (Exception e) {
            numero2 = 0;
            txtNumero2.setText("0");
        }

        double resultado = numero1 + numero2;

        String parOuImpar;
        if (resultado % 2 == 0) {
            parOuImpar = " É par.";
        } else {
            parOuImpar = " É ímpar.";
        }

        btnResultado.setText("Resultado: "+String.valueOf(resultado)+parOuImpar);
    }

    public void Subtrair() {
        double numero1 = StrToDbl(txtNumero1.getText());
        double numero2 = StrToDbl(txtNumero2.getText());

        txtNumero1.setText(String.valueOf(numero1));
        txtNumero2.setText(String.valueOf(numero2));

        double resultado = numero1 - numero2;
        btnResultado.setText(String.valueOf(resultado));
    }

    public void Multiplicar() {
        double numero1 = Double.valueOf(txtNumero1.getText());
        double numero2 = Double.valueOf(txtNumero2.getText());
        double resultado = numero1 * numero2;

        btnResultado.setText(String.valueOf(resultado));
    }

    public void Dividir() {
        double numero1 = Double.valueOf(txtNumero1.getText());
        double numero2 = Double.valueOf(txtNumero2.getText());
        double resultado = numero1 / numero2;

        btnResultado.setText(String.valueOf(resultado));
    }

    public static double StrToDbl(String numero) {
        try {
            return Double.valueOf(numero);
        } catch (Exception e) {
            return 0;
        }
    }
}