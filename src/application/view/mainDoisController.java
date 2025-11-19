package application.view;

import java.util.Scanner;

public class mainDoisController {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nome do Produto:");
		String nome = sc.nextLine();
		System.out.println("Valor R$:");
		float valor = sc.nextFloat();
		System.out.println("Quantidade: ");
		int quantidade = sc.nextInt();

		produtoController proc = new produtoController(nome, valor, quantidade);

		System.out.printf("Nome: %s \nPreço R$ $.2f\nQuantidade: %d \nValor Total RS: %.2f\n\n", nome, valor, quantidade, proc.valorTotalStock());
		System.out.println("Quantidade que deseja adicionar: ");

		int adicionar = sc.nextInt();

		proc.quantidadeProduto(adicionar);

		System.out.printf("Preco R$ %.2f, \nvalor Total R$ %.2f\n\n", valor, proc.valorTotalStock());

		System.out.println("Quantidade que deseja Remover: ");

		int remover = sc.nextInt();


		proc.removerProduto(remover);

		System.out.printf("Preço R$ %.2f, \nValor Total R$ %.2f\n\n", valor, proc. valorTotalStock());

		sc.close();

		}
}
