import java.util.Scanner;

//classe sobre a marca dos produtos do mercadinho
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

//classe com as informacoes completas do produto
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
        return "Produto: " + nome + ", Código: " + codigo + ", Preço: R$ " + preco + "Marca: " + marca;
    }
}

//classe sobre a quantidade de produtos
class Item{
    Produto produto;
    int quantidade;

    public Item(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }

    //funcao para calcualr o valor do carrinho
    public double calcularTotal(){
        return produto.preco * quantidade;
    }

    public String returnString(){
        return produto.returnString() + ", Quantidade: " + quantidade + ", Total: R$ " + calcularTotal();
    }
}

//classe sobre o carrinho e a quantidade de produtos que ele comporta
class Carrinho{
    Item[] items;
    int count;

    public Carrinho(int capacidade){
        items = new Item[capacidade];
        count = 0;
    }

    public void adicionarItem(Item item){
        if(count < items.length){
            items[count] = item;
            count++;
        } 
        else{
            System.out.println("Carrinho cheio!");
        }
    }

    //funcao para exibir os itens do carrinho
    public void exibirItens(){
        System.out.println("Itens no carrinho:");

        for(int i = 0; i < count; i++){
            System.out.println(items[i].returnString());
        }
    }

    public double calcularTotalCarrinho(){
        double total = 0;

        for(int i = 0; i < count; i++){
            total += items[i].calcularTotal();
        }

        return total;
    }
}


//classe prinvipal do programa
public class App{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //carrinho com capacidade máxima de 10 itens
        Carrinho carrinho = new Carrinho(10); 

        //adicionando produtos manualmente
        Marca marca1 = new Marca("Marca1", "Descrição da marca 1");

        System.out.println("Adicionando produtos ao carrinho...");

        //2 unidades de açucar
        carrinho.adicionarItem(new Item(new Produto(1, "Açucar", 20, marca1), 2)); 

        //exibindo itens do carrinho
        carrinho.exibirItens();

        //calculando e exibindo o valor total do carrinho
        double totalCarrinho = carrinho.calcularTotalCarrinho();
        System.out.println("Total do carrinho: R$ " + totalCarrinho);

        scanner.close();
    }
}