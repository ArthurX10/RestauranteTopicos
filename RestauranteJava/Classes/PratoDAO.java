import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PratoDAO {

    public static final String url = ""; //add aq
    
    public void adicionarPrato(Prato prato){
        String sql = "INSERT INTO tbPrato (PRA_NOME, PRA_CATEGORIA, PRA_PRECO, PRA_DISPONIBILIDADE, PRA_DESCRICAO) Values (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url);
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS){
            
                stmt.setString(1, prato.getNome());
                stmt.setString(2, prato.getCategoria());
                stmt.setDouble(3, prato.getPreco());
                stmt.setBoolean(4, prato.getDisp());
                stmt.setString(5, prato.getDescricao());
                
                stmt.executeUpdate();
                
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()){
                    prato.setCod(rs.getCod(1));
                }
        }catch (SQLException error){
            throw new RuntimeException("Erro ao inserir prato", error);
        }
      }
    
    public List<Prato> listarPrato(){
        Lista<Prato> list = new ArrayList<>();
        String sql = "Select *From tbPrato";
        
        try(Connection con = DriverManager.getConnection(url);
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()){
                Prato prato = new Prato();
                prato.setCod(rs.getInt("PRA_CODIGO"));
                prato.setNome(rs.getString("PRA_NOME"));
                prato.setCategoria(rs.getString("PRA_CATEGORIA"));
                prato.setDisp(rs.getBoolean("PRA_DISPONIBILIDADE"));
                prato.setDescricao(rs.getString("PRA_DESCRICAO"));
                list.add(prato);
            }
        }catch(SQLException error){
            throw new RuntimeException("Erro ao listar pratos", error);
        }
        
        return list;
    }
    
    public void atualizarPrato(Prato prato){
        String sql = "UPDATE tbPrato SET PRA_NOME = ?, PRA_CATEGORIA = ?, PRA_PRECO = ?, PRA_DISPONIBILIDADE = ?, PRA_DESCRICAO = ?";
        
        try (Connection con = DriverManager.getConnection(url));
             PreparedStatement stmt = con.prepareStatement(sql){
             
            stmt.setInt(1, prato.getCod());
            stmt.setString(2, prato.getNome());
            stmt.setString(3, prato.getCategoria());
            stmt.setDouble(4, prato.getPreco());
            stmt.setBoolean(5, prato.getDisp());
            stmt.setString(6, prato.getDescricao());
            
            stmt.executeUpdate();
        }catch (SQLException error){
            throw new RuntimeException("Erro ao atualizar prato", error);    
        }
    }
        
        public void deletarPrato(int codigo){
            String sql = "DELETE FROM tbPrato Where PRA_CODIGO = ?";
            
             try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();

             }catch (SQLException error) {
                 throw new RuntimeException("Erro ao deletar cliente", error);
             }
        }
}
