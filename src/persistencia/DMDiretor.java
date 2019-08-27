package persistencia;

import modelo.Diretor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DMDiretor extends DMGeral
{
	
	//Inherited Methods
    public void incluir(Object obj)
    {   
    	Diretor objDiretor = (Diretor) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            
        	//Insertion String
        	String incluirSQL = "INSERT INTO diretor (" +
                                "nome, data_nascimento" +
                                ") VALUES ('" +
                                objDiretor.getNome() + "', '" +
                                objDiretor.getDataNascimento() +"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(incluirSQL) + "\n");
            int result = statement.executeUpdate(incluirSQL);
            if (result == 1)
            {
            	JOptionPane.showMessageDialog(null, "Diretor cadastrado corretamente!", "Mensagem de Informação",
            			JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {   
            	JOptionPane.showMessageDialog(null, "Erro ao cadastrar Diretor!", "Mensagem de Erro",
            			JOptionPane.ERROR_MESSAGE);
            	objDiretor.setNome("");
            	objDiretor.setID(-1);    
            	objDiretor.setDataNascimento(-1);
            }
            statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de inclusão de Diretor!"); 
        }
    }
    public Object consultar(Object obj)
    {   
    	Diretor objDiretor = (Diretor) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            
        	//Query String
        	String consultarSQL = "SELECT * FROM diretor WHERE (nome = '"+objDiretor.getNome()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            
            ResultSet result = statement.executeQuery(consultarSQL);
            if (result.next())
            {   
            	System.out.println("Diretor existente:" );
                int id = result.getInt("id_diretor");
                int dN = result.getInt("data_nascimento");
                result.close();
                
                objDiretor = new Diretor(id, objDiretor.getNome(), dN);
            }
            else
            {   
            	System.out.println( "Diretor não encontrado!\n" );
                objDiretor = null;
            }
            
            statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de consulta de Diretor!"); 
        }
        
        return objDiretor;
    }
    public void excluir(Object obj)
    {   
    	Diretor objDiretor = (Diretor) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            String exclude = "DELETE FROM diretor WHERE (id_DIRETOR = '" + objDiretor.getID() + "')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(exclude) + "\n");
            
            if (statement.executeUpdate(exclude) == 1)
            {   
            	JOptionPane.showMessageDialog(null,"Diretor excluído corretamente!","Mensagem de Informação",
            			JOptionPane.INFORMATION_MESSAGE); 
            }
            else
            {   
            	JOptionPane.showMessageDialog(null,"Diretor não encontrado!","Mensagem de Erro",
            			JOptionPane.ERROR_MESSAGE); 
            }
           statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de exclusão do Diretor!"); 
        }
    }
}