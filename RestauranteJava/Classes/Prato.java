
public class Prato {
    private int codigo;
    private String nome;
    private String categoria;
    private double preco;
    private boolean disp;
    private String descricao;
            
    public Prato(String nome, String categoria, double preco, boolean disponibilidade, String descricao){
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.disp = disp;
        this.descricao = descricao;
    }
    
    public Prato(){
    }
    
    public int getCod(){
        return codigo;
    }
    
    public void setCod(int codigo){
        this.codigo = codigo;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(){
        this.nome = nome;
    }
    
    public String getCategoria(){
        return descricao;
    }
    
    public void setCategoria(){
        this.categoria = categoria;
    }
    
    public double getPreco(){
        return preco;
    }
    
    public void setPreco(){
        this.preco = preco;
    }
    
    public boolean getDisp(){
        return disp;
    }
    
    public void setDisp(){
        this.disp = disp;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public void setDescricao(){
        this.descricao = descricao;
    }
    
    @Override
    public String toString(){
        return "Código do Prato: " + codigo + "\n" +
                "Nome: " + nome + "\n" +
                "Categoria: " + categoria + "\n" +
                "Preço: " + preco + "\n" +
                "Disponibilidade: " + disp + "\n" +
                "Descrição: " + descricao + "\n";
    }
}
