

package br.com.cadastroacademico.DAO;

import br.com.cadastroacademico.CONEXAO.Conexao;
import br.com.cadastroacademico.VO.AlunoVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AlunoDAO {
    
    private Connection conexao;
    
    public AlunoDAO() throws SQLException{
        conexao = Conexao.getConnection();
    }
    
    public boolean insert(AlunoVO vo) throws SQLException{
        String sql = "INSERT INTO aluno (matricula, nome, dt_nascimento, email)values(?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, vo.getMatricula());
        pstm.setString(2, vo.getNome());
        pstm.setString(3, vo.getDt_nascimento());
        pstm.setString(4, vo.getEmail());
        
        pstm.execute();
        pstm.close();
        return true;
        
    }
    
    public boolean update(AlunoVO vo)throws ClassNotFoundException, SQLException{
        String sql = "UPDATE aluno set nome = ?,dt_nascimento = ?,email = ? where matricula = ? ";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, vo.getNome());
        pstm.setString(2, vo.getDt_nascimento());
        pstm.setString(3, vo.getEmail());
        pstm.setString(4, vo.getMatricula());
        return pstm.executeUpdate() > 0;
    }
    
    public boolean delete(AlunoVO vo)throws ClassNotFoundException, SQLException {
        String sql = "DELETE from aluno where matricula = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, vo.getMatricula());
        return pstm.executeUpdate() > 0;
        
    }
    
    public List consultar() throws ClassNotFoundException, SQLException {
        
        String sql = "SELECT * FROM aluno";
        
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        AlunoVO novoAluno;
        List<AlunoVO> lista  = new ArrayList<AlunoVO>();
        while(rs.next()){
            novoAluno = new AlunoVO();
            novoAluno.setMatricula(rs.getString("matricula"));
            novoAluno.setNome(rs.getString("nome"));
            novoAluno.setDt_nascimento(rs.getString("dt_nascimento"));
            novoAluno.setEmail(rs.getString("email"));
            lista.add(novoAluno);
        }
        return lista;
    }
    
    
}
