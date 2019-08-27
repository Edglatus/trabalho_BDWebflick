package persistencia;

import modelo.Filme;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DMFilme extends DMGeral
{
	
	//Inherited Methods
    public void incluir(Object obj)
    {   
    	Filme objFilm = (Filme) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            
        	//Insertion String
        	String incluirSQL = "INSERT INTO filme (" +
                                "titulo, ano_lancamento, id_DIRETOR_fk, id_ATOR_ATRIZ_fk" +
                                ") VALUES ('" +
                                objFilm.getTitulo() + "', '" +
                                objFilm.getAno() + "', '" +
                                objFilm.getDiretor() + "', '" + 
                                objFilm.getAtor() +"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(incluirSQL) + "\n");
            int result = statement.executeUpdate(incluirSQL);
            if (result == 1)
            {
            	JOptionPane.showMessageDialog(null, "Filme cadastrado corretamente!", "Mensagem de Informação",
            			JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {   
            	JOptionPane.showMessageDialog(null, "Erro ao cadastrar Filme!", "Mensagem de Erro",
            			JOptionPane.ERROR_MESSAGE);
            	objFilm.setTitulo("");
            	objFilm.setID(0);    
            	objFilm.setAtor(0);
            	objFilm.setDiretor(0);
            }
            statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de inclusão de Filme!"); 
        }
    }
    public Object consultar(Object obj)
    {   
    	Filme objFilm = (Filme) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            
        	//Query String
        	String consultarSQL = "SELECT * FROM filme WHERE (titulo = '"+objFilm.getTitulo()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            
            ResultSet result = statement.executeQuery(consultarSQL);
            if (result.next())
            {   
            	System.out.println("Filme existente:" );
            	int id = result.getInt("id_FILME");
            	String n = result.getString("titulo");
            	int ano = result.getInt("ano_lancamento");
            	int atriz = result.getInt("id_ATOR_ATRIZ_fk");
            	int diretor = result.getInt("id_DIRETOR_fk");
            	result.close();
            	
            	objFilm = new Filme(id, n, ano, diretor, atriz);
            }
            else
            {   
            	System.out.println( "Filme não encontrado!\n" );
                objFilm = null;
            }
            
            statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de consulta de Filme!"); 
        }
        
        return objFilm;
    }
    public void excluir(Object obj)
    {   
    	Filme objFilme = (Filme) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            String exclude = "DELETE FROM filme WHERE (id_FILME = '" + objFilme.getID() + "')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(exclude) + "\n");
            
            if (statement.executeUpdate(exclude) == 1)
            {   
            	JOptionPane.showMessageDialog(null,"Filme excluído corretamente !","Mensagem de Informação",
            			JOptionPane.INFORMATION_MESSAGE); 
            }
            else
            {   
            	JOptionPane.showMessageDialog(null,"Filme não encontrado!","Mensagem de Erro",
            			JOptionPane.ERROR_MESSAGE); 
            }
           statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de exclusão do Filme!"); 
        }
    }
}