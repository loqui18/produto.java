// Produto.java
public class Produto {
    String nome;
    int quantidade;
    double precoUnitario;
    String categoria;
    int qtdMinima;

    public Produto(String nome, int quantidade, double precoUnitario, String categoria, int qtdMinima) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.categoria = categoria;
        this.qtdMinima = qtdMinima;
    }

    public double calcularSubtotal() {
        return quantidade * precoUnitario;
    }

    @Override
    public String toString() {
        return nome + " - " + quantidade + " - " + precoUnitario;
    }
}
