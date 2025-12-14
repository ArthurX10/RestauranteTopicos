package DAO;

import Classes.Prato;
import Conexao.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PratoDAO extends ConexaoBD {

    public static final String url = ""; //add aq

    public void adicionarPrato(Prato prato) {
        String sql = "INSERT INTO tbPrato (PRA_NOME, PRA_CATEGORIA, PRA_PRECO, PRA_DISPONIBILIDADE, PRA_DESCRICAO) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, prato.getNome());
            stmt.setString(2, prato.getCategoria());
            stmt.setDouble(3, prato.getPreco());
            stmt.setBoolean(4, prato.getDisp());
            stmt.setString(5, prato.getDescricao());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                prato.setCodigo(rs.getInt(1));
            }
        } catch (SQLException error) {
            throw new RuntimeException("Erro ao inserir prato", error);
        }
    }

    public List<Prato> listarPrato() {
        List<Prato> list = new ArrayList<>();
        String sql = "SELECT * FROM tbPrato";

        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(sql);
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

        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(sql)) {

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

        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();

        } catch (SQLException error) {
            throw new RuntimeException("Erro ao deletar prato", error);
        }
    }

    public Prato pesquisar(int codigo) {
        String sql = "SELECT * FROM tbPrato WHERE PRA_CODIGO = ?";
        Prato prato = null;

        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(sql)) {

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
