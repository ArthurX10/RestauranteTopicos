package classes;

public class Prato {
    private int codigo;
    private String nome;
    private String categoria;
    private double preco;
    private boolean disp;
    private String descricao;

    public Prato(String nome, String categoria, double preco, boolean disp, String descricao) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.disp = true;
        this.descricao = descricao;
    }

    public Prato() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean getDisp() {
        return disp;
    }

    public void setDisp(boolean disp) {
        this.disp = disp;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Código do Prato: " + codigo + "\n" +
               "Nome: " + nome + "\n" +
               "Categoria: " + categoria + "\n" +
               "Preço: " + preco + "\n" +
               "Disponibilidade: " + disp + "\n" +
               "Descrição: " + descricao + "\n";
    }
}

