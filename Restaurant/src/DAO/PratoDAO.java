package DAO;

import classes.Prato;
import ConexaoBD.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PratoDAO extends ConexaoBD {

    public void adicionarPrato(Prato prato) {
        String sql = "INSERT INTO tbPrato (PRA_NOME, PRA_CATEGORIA, PRA_PRECO, PRA_DISPONIBILIDADE, PRA_DESCRICAO) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {
            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getCategoria());
            stmt.setDouble(3, prato.getPreco());
            stmt.setBoolean(4, prato.getDisp());
            stmt.setString(5, prato.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Prato> listarPrato() {
        List<Prato> list = new ArrayList<>();
        String sql = "SELECT * FROM tbPrato";

        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Prato prato = new Prato();
                prato.setCodigo(rs.getInt("PRA_CODIGO"));
                prato.setNome(rs.getString("PRA_NOME"));
                prato.setCategoria(rs.getString("PRA_CATEGORIA"));
                prato.setPreco(rs.getDouble("PRA_PRECO"));
                prato.setDisp(rs.getBoolean("PRA_DISPONIBILIDADE"));
                prato.setDescricao(rs.getString("PRA_DESCRICAO"));
                list.add(prato);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public boolean atualizarPrato(Prato prato) {
        String sql = "UPDATE tbPrato SET PRA_NOME = ?, PRA_CATEGORIA = ?, PRA_PRECO = ?, PRA_DISPONIBILIDADE = ?, PRA_DESCRICAO = ? WHERE PRA_CODIGO = ?";

        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {
            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getCategoria());
            stmt.setDouble(3, prato.getPreco());
            stmt.setBoolean(4, prato.getDisp());
            stmt.setString(5, prato.getDescricao());
            stmt.setInt(6, prato.getCodigo());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

  public boolean deletarPrato(int codigo) {
    String sql = "DELETE FROM tbPrato WHERE PRA_CODIGO = ?";

    try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {
        stmt.setInt(1, codigo);
        return stmt.executeUpdate() > 0;
    } catch (SQLException error) {
        throw new RuntimeException(error);
    }
}

    public Prato pesquisarPrato(int codigo) {
        String sql = "SELECT * FROM tbPrato WHERE PRA_CODIGO = ?";

        try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Prato prato = new Prato();
                prato.setCodigo(rs.getInt("PRA_CODIGO"));
                prato.setNome(rs.getString("PRA_NOME"));
                prato.setCategoria(rs.getString("PRA_CATEGORIA"));
                prato.setPreco(rs.getDouble("PRA_PRECO"));
                prato.setDisp(rs.getBoolean("PRA_DISPONIBILIDADE"));
                prato.setDescricao(rs.getString("PRA_DESCRICAO"));
                return prato;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}