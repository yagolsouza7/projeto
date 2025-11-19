package application.view;

import java.util.Scanner;

public class mainController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("NOME: ");
		String nome = sc.nextLine();
		System.out.println("SALÁRIO BRUTO R$:");
		double salarioBruto = sc.nextDouble();
		System.out.println("IMPOSTO R$:");
		double imposto = sc.nextDouble();
		funcionarioController proc = new funcionarioController (nome, salarioBruto, imposto);
		double sal = proc.salarioLiquido();
		System.out.printf("\n FUNCIONARIO: %s, R$ %.2f\n", nome, sal);
		System.out.println("\nQual percentual para aumentar o salário ?");
		double porcentagem = sc.nextDouble();

		proc.salarioAumentado(porcentagem);
		
		sc.close();
	}
}
