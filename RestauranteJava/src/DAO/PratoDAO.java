package DAO;

import classes.Prato;
import ConexaoBD.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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

        } catch (SQLException error) {
            error.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao adicionar prato: " + error.getMessage());
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
        } catch (SQLException error) {
            throw new RuntimeException("Erro ao listar pratos", error);
        }

        return list;
    }

    public void atualizarPrato(Prato prato) {
        String sql = "UPDATE tbPrato SET PRA_NOME = ?, PRA_CATEGORIA = ?, PRA_PRECO = ?, PRA_DISPONIBILIDADE = ?, PRA_DESCRICAO = ? WHERE PRA_CODIGO = ?";

      try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {

            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getCategoria());
            stmt.setDouble(3, prato.getPreco());
            stmt.setBoolean(4, prato.getDisp());
            stmt.setString(5, prato.getDescricao());
            stmt.setInt(6, prato.getCodigo());

            stmt.executeUpdate();
        } catch (SQLException error) {
            throw new RuntimeException("Erro ao atualizar prato", error);
        }
    }

    public void deletarPrato(int codigo) {
        String sql = "DELETE FROM tbPrato WHERE PRA_CODIGO = ?";

       try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao deletar prato: " + error.getMessage());
        }
    }

    public Prato pesquisarPrato(int codigo) {
        String sql = "SELECT * FROM tbPrato WHERE PRA_CODIGO = ?";
        Prato prato = null;

      try (PreparedStatement stmt = ConexaoBD.getConexao().prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                prato = new Prato();
                prato.setCodigo(rs.getInt("PRA_CODIGO"));
                prato.setNome(rs.getString("PRA_NOME"));
                prato.setCategoria(rs.getString("PRA_CATEGORIA"));
                prato.setPreco(rs.getDouble("PRA_PRECO"));
                prato.setDisp(rs.getBoolean("PRA_DISPONIBILIDADE"));
                prato.setDescricao(rs.getString("PRA_DESCRICAO"));
            }
        } catch (SQLException error) {
            throw new RuntimeException("Erro ao buscar prato", error);
        }

        return prato;
    }
}
