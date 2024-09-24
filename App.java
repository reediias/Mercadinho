import java.util.Scanner;

class Marca{
    String nome;
    String descricao;

    public Marca(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String returnString() {
        return "Marca: " + nome + ", Descrição: " + descricao;
    }
}

class Produto {
    int codigo;
    String nome;
    double preco;
    Marca marca;


    public Produto(int codigo, String nome, double preco, Marca marca) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.marca = marca;
    }
    
    public String returnString() {
        return "Produto: " + nome + ", Código: " + codigo + ", Preço: R$ " + preco + "Marca: " + marca;
    }
}

class Item {
    Produto produto;
    int quantidade;

    public Item(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double calcularTotal() {
        return produto.preco * quantidade;
    }

    public String returnString() {
        return produto.returnString() + ", Quantidade: " + quantidade + ", Total: R$ " + calcularTotal();
    }
}

class Carrinho {
    Item[] items;
    int count;

    public Carrinho(int capacidade) {
        items = new Item[capacidade];
        count = 0;
    }

    public void adicionarItem(Item item) {
        if (count < items.length) {
            items[count] = item;
            count++;
        } else {
            System.out.println("Carrinho cheio!");
        }
    }

    public void exibirItens() {
        System.out.println("Itens no carrinho:");
        for (int i = 0; i < count; i++) {
            System.out.println(items[i].returnString());
        }
    }

    public double calcularTotalCarrinho() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += items[i].calcularTotal();
        }
        return total;
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Carrinho carrinho = new Carrinho(10); // capacidade máxima de 10 itens
        Marca marca1 = new Marca("Marca1", "Descrição da marca 1");
        // Adicionando produtos manualmente

        System.out.println("Adicionando produtos ao carrinho...");

        carrinho.adicionarItem(new Item(new Produto(1, "Açucar", 20, marca1), 2)); // 2 unidades de açucar

        // Exibindo itens do carrinho
        carrinho.exibirItens();

        // Calculando e exibindo o valor total do carrinho
        double totalCarrinho = carrinho.calcularTotalCarrinho();
        System.out.println("Total do carrinho: R$ " + totalCarrinho);

        scanner.close();
    }
}