package application.model;

public class carroModel {
    //atributos
	String marca;
	String nome;
	String motor;
	String cor;
	int ano;
	String modelo;
	boolean ligado;
	
	//Construtor
	public carroModel (String marca, String nome, String motor, String cor, int ano, String modelo, boolean ligado) 
	this.marca = marca;
	this.nome = nome;
	this.motor = motor;
	this.cor = cor;
	this.ano = ano;
	this.modelo = modelo;
	return false;
}
    //metodos
    public tring exibirInformacoes() {
    	return "Marca: " + marca +
    			"\nNme:s " + nome +
    			"\nMootor: " + motor +
    			"\nCor: " + cor +
    			"\nAno: " + ano +
    }