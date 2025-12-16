package DAO;

import ConexaoBD.ConexaoBD;
import classes.Pedido;
import classes.Prato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {
    public void create(Pedido pedido) {
        String sql = "INSERT INTO tbPedido (PED_CLIENTE, PED_QTDE, PED_DATA, PED_STATUS, PRA_CODIGO) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {
            stmt.setString(1, pedido.getCliente());
            stmt.setInt(2, pedido.getQuantidade());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(pedido.getDataHora()));
            stmt.setString(4, pedido.getStatus());
            stmt.setInt(5, pedido.getPrato().getCodigo());
            stmt.executeUpdate();
        } catch(SQLException error) {
            throw new RuntimeException("Erro ao criar o Pedido.", error);
        }
    }

    public List<Pedido> read() {
        String sql = "SELECT * FROM tbPedido";
        List<Pedido> pedidos = new ArrayList<>();
        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql);
             ResultSet result = stmt.executeQuery()) {
            while(result.next()) {
                Pedido pedido = new Pedido();
                Prato prato = new Prato();
                pedido.setIdPedido(result.getInt("PED_CODIGO"));
                prato.setCodigo(result.getInt("PRA_CODIGO")); 
                pedido.setPrato(prato);
                pedido.setCliente(result.getString("PED_CLIENTE"));
                pedido.setQuantidade(result.getInt("PED_QTDE"));
                java.sql.Timestamp ts = result.getTimestamp("PED_DATA");
                if (ts != null) {
                    pedido.setDataHora(ts.toLocalDateTime());
                }
                pedido.setStatus(result.getString("PED_STATUS"));
                pedidos.add(pedido);
            }
            return pedidos;
        } catch(SQLException error) {
            throw new RuntimeException("Erro ao listar os Pedidos.", error);
        }
    }

    public void update(Pedido pedido) {
        String sql = "UPDATE tbPedido SET PED_CLIENTE = ?, PED_QTDE = ?, PED_DATA = ?, PED_STATUS = ?, PRA_CODIGO = ? WHERE PED_CODIGO = ?";
        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {
            stmt.setString(1, pedido.getCliente());
            stmt.setInt(2, pedido.getQuantidade());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(pedido.getDataHora()));
            stmt.setString(4, pedido.getStatus());
            stmt.setInt(5, pedido.getPrato().getCodigo()); 
            stmt.setInt(6, pedido.getIdPedido());
            stmt.executeUpdate();
        } catch(SQLException error) {
            throw new RuntimeException("Erro ao atualizar o Pedido.", error);
        }
    }

    public void delete(int idPedido) {
        String sql = "DELETE FROM tbPedido WHERE PED_CODIGO = ?";
        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        } catch(SQLException error) {
            throw new RuntimeException("Erro ao deletar o Pedido.", error);
        }
    }

    public pedido search(int idPedido){
        String sql = "SELECT * FROM tbPedido WHERE PED_CODIGO = ?";
        try(PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)){
            stmt.setInt(1, idPedido);
            ResultSet result = stmt.executeQuery();

                if(result.next()){
                    Pedido pedido = new Pedido();
                    Prato prato = new Prato();
                    pedido.setIdPedido(result.getInt("PED_CODIGO"));
                    prato.setCodigo(result.getInt("PRA_CODIGO")); 
                    pedido.setPrato(prato);
                    pedido.setCliente(result.getString("PED_CLIENTE"));
                    pedido.setQuantidade(result.getInt("PED_QTDE"));
                    java.sql.Timestamp ts = result.getTimestamp("PED_DATA");
                    if (ts != null) {
                        pedido.setDataHora(ts.toLocalDateTime());
                    }
                    pedido.setStatus(result.getString("PED_STATUS"));
                    return pedido;
                } else {
                    return null;
                }
        } catch(SQLException error){
            throw new RuntimeException("Erro ao pesquisar o Pedido.", error);
        }
    }
}
