

package br.com.cadastroacademico.BEAN;

import br.com.cadastroacademico.DAO.AlunoDAO;
import br.com.cadastroacademico.VO.AlunoVO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="usu")
@SessionScoped

public class AlunoBEAN extends AlunoVO implements Serializable  {
    
    private List alunos;
    private AlunoDAO alunoDAO;
    
    public AlunoBEAN() throws ClassNotFoundException, SQLException{
        alunoDAO = new AlunoDAO();
        alunos = alunoDAO.consultar();
        limparTela();
    }
    
    public String addAluno(){
        String retorno = "erro";
        try{
           
            if(alunoDAO.insert(this)){
                alunos = alunoDAO.consultar();
                limparTela();
                retorno ="index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return retorno;
    }
    
    public String alterar(){
        String retorno = "erro";
        try {
            if(alunoDAO.update(this)){
                alunos = alunoDAO.consultar();// busca os dados no banco após excluir para 
                limparTela();
                retorno = "index";
                
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        return retorno;
    }
    
    public String editar(AlunoVO aluno){
        setMatricula(aluno.getMatricula());
        setNome(aluno.getNome());
        setDt_nascimento(aluno.getDt_nascimento());
        setEmail(aluno.getEmail());
        return "editar";
    }
    
    public String excluir(AlunoVO aluno){
        String retorno = "erro";
        try{
            if(alunoDAO.delete(aluno)){
                alunos = alunoDAO.consultar();
                limparTela();
                retorno = "index";
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return retorno;
        
    }

    private void limparTela() {
        setMatricula(null);
        setNome(null);
        setDt_nascimento(null);
        setEmail(null);
    }
    
    public List getAlunos(){
        return alunos;
    }
    public void setAlunos(List<AlunoVO>alunos){
        this.alunos = alunos;
    }
}


