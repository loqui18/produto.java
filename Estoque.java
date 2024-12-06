// Estoque.java
import java.util.*;

public class Estoque {
    static List<Produto> produtos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu: ");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Filtrar por categoria");
            System.out.println("4 - Ordenar por categoria");
            System.out.println("5 - Remover produto");
            System.out.println("6 - Atualizar preço");
            System.out.println("7 - Listagem com subtotal por categoria");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção:  ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1 -> cadastrarProduto(scanner);
                case 2 -> listarProdutos();
                case 3 -> filtrarPorCategoria(scanner);
                case 4 -> ordenarPorCategoria();
                case 5 -> removerProduto(scanner);
                case 6 -> atualizarPreco(scanner);
                case 7 -> listarComSubtotalPorCategoria();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    // Métodos do menu (cadastrarProduto, listarProdutos, etc.) permanecem iguais
}
