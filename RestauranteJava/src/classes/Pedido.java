package classes;

import java.sql.Date; 
import java.time.LocalDateTime;

public class Pedido{
    private int idPedido;
    private Prato prato; // apenas um prato por pedido
    private String cliente;
    private int quantidade;
   private LocalDateTime dataHora;
    private String status;


    public Pedido() {
        // contrutor vazio
    }

    public Pedido(int idPedido, Prato prato, String cliente, int quantidade, LocalDateTime dataHora) {
        this.idPedido = idPedido;
        this.prato = prato;
        this.cliente = cliente;
        this.quantidade = quantidade;
        this.dataHora = LocalDateTime.now();
        this.status = "PENDENTE";
    }




    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

   public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
