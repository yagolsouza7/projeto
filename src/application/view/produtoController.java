package application.view;

public class produtoController {
	private String nome;
	private float preco;
	private int quantidade;

	public produtoController(String nome, float preco, int quantidade) {
	this.nome = nome;
	this.preco = preco;
	this.quantidade = quantidade;
	}
	public float valorTotalStock() {
	return quantidade * preco;
	}
	public void quantidadeProduto(int adicionar) {
	System.out.printf("Quantidade de %s: %d \n", nome, (quantidade + adicionar));
	quantidade = quantidade + adicionar;
	}
	public void removerProduto(int remover) {
	System.out.printf("Quantidade de %s: %d \n", nome, (quantidade - remover));
	quantidade = quantidade - remover;
	}
	
}
