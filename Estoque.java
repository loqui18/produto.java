import java.util.*;

class Produto {
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
                case 1:
                    cadastrarProduto(scanner);
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    filtrarPorCategoria(scanner);
                    break;
                case 4:
                    ordenarPorCategoria();
                    break;
                case 5:
                    removerProduto(scanner);
                    break;
                case 6:
                    atualizarPreco(scanner);
                    break;
                case 7:
                    listarComSubtotalPorCategoria();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
        scanner.close();
    }

    public static void cadastrarProduto(Scanner scanner) {
        System.out.print("Nome do produto:  ");
        String nome = scanner.nextLine();
        System.out.print("Quantidade em estoque:  ");
        int quantidade = scanner.nextInt();
        System.out.print("Preço unitário:  ");
        double precoUnitario = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer
        System.out.print("Categoria:  ");
        String categoria = scanner.nextLine();
        System.out.print("Quantidade mínima:  ");
        int qtdMinima = scanner.nextInt();

        produtos.add(new Produto(nome, quantidade, precoUnitario, categoria, qtdMinima));
        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\nLista de Produtos: ");
        produtos.forEach(System.out::println);
    }

    public static void filtrarPorCategoria(Scanner scanner) {
        System.out.print("Digite a categoria para filtrar:  ");
        String categoria = scanner.nextLine();
        produtos.stream()
                .filter(p -> p.categoria.equalsIgnoreCase(categoria))
                .forEach(System.out::println);
    }

    public static void ordenarPorCategoria() {
        produtos.sort(Comparator.comparing(p -> p.categoria));
        System.out.println("Produtos ordenados por categoria.");
    }

    public static void removerProduto(Scanner scanner) {
        System.out.print("Digite o nome do produto para remover:  ");
        String nome = scanner.nextLine();
        produtos.removeIf(p -> p.nome.equalsIgnoreCase(nome));
        System.out.println("Produto removido com sucesso (se existia).");
    }

    public static void atualizarPreco(Scanner scanner) {
        System.out.print("Digite o nome do produto para atualizar o preço:  ");
        String nome = scanner.nextLine();
        produtos.stream()
                .filter(p -> p.nome.equalsIgnoreCase(nome))
                .findFirst()
                .ifPresentOrElse(p -> {
                    System.out.print("Novo preço:  ");
                    p.precoUnitario = scanner.nextDouble();
                    System.out.println("Preço atualizado com sucesso!");
                }, () -> System.out.println("Produto não encontrado."));
    }

    public static void listarComSubtotalPorCategoria() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        Map<String, List<Produto>> produtosPorCategoria = new TreeMap<>();

        for (Produto produto : produtos) {
            produtosPorCategoria.computeIfAbsent(produto.categoria, k -> new ArrayList<>()).add(produto);
        }

        double totalGeral = 0.0;

        System.out.println("\nListagem com Subtotal por Categoria: ");
        for (Map.Entry<String, List<Produto>> entry : produtosPorCategoria.entrySet()) {
            System.out.println("Categoria:  " + entry.getKey());
            double subtotal = entry.getValue().stream().mapToDouble(Produto::calcularSubtotal).sum();
            System.out.println("Subtotal:  R$ " + subtotal);
            totalGeral += subtotal;
        }

        System.out.println("Total Geral:  R$ " + totalGeral);
    }
