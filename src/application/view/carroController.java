package application.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class carroController {

    public static class Carro {
        private String marca;
        private String nome;
        private String motor;
        private String cor;
        private int ano;
        private String modelo;
        private boolean ligado;
        private String imagem; // Caminho absoluto

        public Carro(String marca, String nome, String motor, String cor, int ano, String modelo, String imagem) {
            this.marca = marca;
            this.nome = nome;
            this.motor = motor;
            this.cor = cor;
            this.ano = ano;
            this.modelo = modelo;
            this.imagem = imagem;
            ligado = false;
        }

        public void ligarCarro() { ligado = true; }
        public void desligarCarro() { ligado = false; }
        public String getEstado() { return ligado ? "Ligado" : "Desligado"; }

        public String getMarca() { return marca; }
        public String getNome() { return nome; }
        public String getMotor() { return motor; }
        public String getCor() { return cor; }
        public int getAno() { return ano; }
        public String getModelo() { return modelo; }
        public String getImagem() { return imagem; }

        @Override
        public String toString() {
            return marca + " " + nome;
        }
    }

    @FXML private ListView<Carro> listCarros;

    @FXML private Label lblMarca;
    @FXML private Label lblNome;
    @FXML private Label lblMotor;
    @FXML private Label lblCor;
    @FXML private Label lblAno;
    @FXML private Label lblModelo;
    @FXML private Label lblEstado;

    @FXML private Button btnLigar;
    @FXML private Button btnDesligar;

    @FXML private ImageView imgCarro;

    private ObservableList<Carro> carros;

    @FXML
    public void initialize() {

        carros = FXCollections.observableArrayList(
            new Carro("Honda", "Civic", "2.0", "Preto", 2020, "Sedan",
                "file:/C:/Users/sistemavesp/Downloads/honda-civic-2020-versao-sport-1.jpg"),

            new Carro("Fiat", "Uno", "1.0", "Branco", 2015, "Hatch",
                "file:/C:/Users/sistemavesp/Downloads/uninho.jpg"),

            new Carro("Toyota", "Corolla", "2.0", "Prata", 2022, "Sedan",
                "file:/C:/Users/sistemavesp/Downloads/Toyota-Corolla-XEi_4.jpg"),

            new Carro("Ford", "Mustang", "5.0 V8", "Vermelho", 2021, "Esportivo",
                "file:/C:/Users/sistemavesp/Downloads/mustangao.jpeg"),

            new Carro("Chevrolet", "Onix", "1.4", "Azul", 2019, "Hatch",
                "file:/C:/Users/sistemavesp/Downloads/onix.jpg")
        );

        listCarros.setItems(carros);

        listCarros.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldVal, newVal) -> exibirInformacoes(newVal)
        );
    }

    private void exibirInformacoes(Carro c) {
        if (c == null) return;

        lblMarca.setText("Marca: " + c.getMarca());
        lblNome.setText("Nome: " + c.getNome());
        lblMotor.setText("Motor: " + c.getMotor());
        lblCor.setText("Cor: " + c.getCor());
        lblAno.setText("Ano: " + c.getAno());
        lblModelo.setText("Modelo: " + c.getModelo());
        lblEstado.setText("Estado: " + c.getEstado());

        try {
            imgCarro.setImage(new Image(c.getImagem()));
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + c.getImagem());
        }
    }

    @FXML
    private void ligarCarro() {
        Carro c = listCarros.getSelectionModel().getSelectedItem();
        if (c != null) {
            c.ligarCarro();
            exibirInformacoes(c);
        }
    }

    @FXML
    private void desligarCarro() {
        Carro c = listCarros.getSelectionModel().getSelectedItem();
        if (c != null) {
            c.desligarCarro();
            exibirInformacoes(c);
        }
    }
}

