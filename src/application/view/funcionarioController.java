package application.view;

public class funcionarioController {
	private String nome;

	private double salarioBruto;

	private double imposto;

	public funcionarioController (String nome1, double salarioBruto1, double imposto1) {

	this.nome=nome1;

	this.salarioBruto=salarioBruto1;

	this.imposto=imposto1;
	}

	public double salarioLiquido() {

	double salarioAtual = salarioBruto - imposto;
	return salarioAtual;

	}

	public void salarioAumentado(double porcentagem) {

	double salarioAtual = salarioBruto - imposto;

	double aumento = salarioBruto * (porcentagem / 100);

	double novoSalario = salarioAtual + aumento;

	System.out.printf("Dados Atualizados: %s, R$ %.2f", nome, novoSalario);

	}
}
