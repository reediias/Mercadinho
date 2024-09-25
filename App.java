import java.util.ArrayList;
import java.util.Scanner;

//classe para informacoes da marca do produto
class Marca{
    String nome;
    String descricao;

    public Marca(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public String returnString(){
        return "Marca: " + nome + ", Descrição: " + descricao;
    }
}

//classe para informacoes do produto
class Produto{
    int codigo;
    String nome;
    double preco;
    Marca marca;

    public Produto(int codigo, String nome, double preco, Marca marca){
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.marca = marca;
    }

    public String returnString(){
        return "Produto: " + nome + ", Código: " + codigo + ", Preço: R$ " + preco + " " + marca.returnString();
    }
}

//classe para quantidade de itens no carrinho
class Item{
    Produto produto;
    int quantidade;

    public Item(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double calcularTotal(){
        return produto.preco * quantidade;
    }

    public String returnString(){
        return produto.returnString() + ", Quantidade: " + quantidade + ", Total: R$ " + calcularTotal();
    }
}

//classe para gerenciamento do carrinho
class Carrinho{
    ArrayList<Item> items = new ArrayList<>();

    public void adicionarItem(Item item){
        items.add(item);
    }

    public void exibirItens(){
        System.out.println("Itens no carrinho:");

        //se o carrinho esta vazio
        if(items.isEmpty()){
            System.out.println("O carrinho está vazio.");
            return;
        }
        //caso contrario percorre o carrinho e printa os itens
        for(Item item : items){
            System.out.println(item.returnString());
        }
    }

    public double calcularTotalCarrinho(){
        double total = 0;
        for(Item item : items){
            total += item.calcularTotal();
        }
        return total;
    }
}

//classe principal do programa e onde o código irá ser rodado
public class App{
    public static void main(String[] args){
        mostrarMenu();
    }

    //funcao menu
    public static void mostrarMenu(){
        Scanner scanner = new Scanner(System.in);
        Carrinho carrinho = new Carrinho();

        System.out.println("BEM-VINDO AO MERCADINHO SANTA LUZIA!");

        while(true){
            System.out.println("Digite 1 para adicionar produtos:\n 2 para ver o carrinho:\n 3 para finalizar a compra:\n 0 para sair:");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch(escolha){
                case 1:
                    //repete o loop de adicionar produtos ate o usuario finalizar o carrinho
                    Repeticao(scanner, carrinho);
                    break;

                case 2:
                    //exibe todo o carrinho
                    carrinho.exibirItens();
                    break;

                case 3:
                    //finaliza a compra
                    System.out.printf("O valor total da compra é: R$ %.2f%n", carrinho.calcularTotalCarrinho());
                    System.out.println("Compra finalizada com sucesso!");
                    return; 

                case 0:
                    System.out.println("Obrigado por visitar o Mercadinho, volte sempre!");
                    return; 

                default:
                    System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    //funcao repetir loop
    public static void Repeticao(Scanner scanner, Carrinho carrinho){

        while(true){
            System.out.println("Deseja adicionar produtos? (S/N)");
            String resposta = scanner.nextLine();

            if(resposta.equalsIgnoreCase("N")){
                break;
            } 
            else if (resposta.equalsIgnoreCase("S")){
                mostrarOpcoes(scanner, carrinho);
            } 
            else{
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void mostrarOpcoes(Scanner scanner, Carrinho carrinho) {
        System.out.println("Opções disponíveis de hoje:");
        System.out.println("1. Frutas\n2. Legumes\n3. Laticínios\n4. Açougue");

        System.out.print("Digite a sessão desejada: ");
        int escolha1 = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        switch (escolha1) {
            case 1:
                mostrarProdutos(scanner, "Frutas", new String[]{"Mamão", "Banana", "Goiaba", "Pêra", "Melancia", "Manga"}, new double[]{3.0, 1.0, 2.5, 4.0, 5.0, 3.5}, carrinho);
                break;
            case 2:
                mostrarProdutos(scanner, "Legumes", new String[]{"Couve", "Batata", "Cenoura", "Abobrinha", "Alface", "Tomate"}, new double[]{2.0, 1.5, 2.0, 3.0, 2.5, 4.0}, carrinho);
                break;
            case 3:
                mostrarProdutos(scanner, "Laticínios", new String[]{"Leite", "Queijo", "Danone", "Requeijão", "Manteiga", "Creme de Leite"}, new double[]{4.0, 25.0, 2.0, 6.0, 7.0, 3.0}, carrinho);
                break;
            case 4:
                mostrarProdutos(scanner, "Açougue", new String[]{"Carne de sol", "Carne de porco", "Frango", "Presunto", "Linguiça", "Carne de hambúrguer"}, new double[]{40.0, 30.0, 15.0, 25.0, 20.0, 25.0}, carrinho);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void mostrarProdutos(Scanner scanner, String categoria, String[] produtos, double[] precos, Carrinho carrinho) {
        System.out.println(categoria + ":");
        for (int i = 0; i < produtos.length; i++) {
            System.out.printf("%d. %s - R$ %.2f%n", (i + 1), produtos[i], precos[i]);
        }

        System.out.print("Digite o número do produto desejado: ");
        int escolhaProduto = scanner.nextInt() - 1;

        if (escolhaProduto >= 0 && escolhaProduto < produtos.length) {
            System.out.print("Digite o código do produto: ");
            int codigo = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            System.out.print("Digite a quantidade desejada: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            // Criando a marca do produto
            System.out.print("Digite o nome da marca: ");
            String nomeMarca = scanner.nextLine();
            System.out.print("Digite a descrição da marca: ");
            String descricaoMarca = scanner.nextLine();
            Marca marca = new Marca(nomeMarca, descricaoMarca);

            // Adicionando o produto ao carrinho
            carrinho.adicionarItem(new Item(new Produto(codigo, produtos[escolhaProduto], precos[escolhaProduto], marca), quantidade));
        } else {
            System.out.println("Produto inválido.");
        }
    }
}
