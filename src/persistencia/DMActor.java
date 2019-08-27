package persistencia;

import modelo.Actor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DMActor extends DMGeral
{
	
	//Inherited Methods
    public void incluir(Object obj)
    {   
    	Actor objActor = (Actor) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            
        	//Insertion String
        	String incluirSQL = "INSERT INTO ator_atriz (" +
                                "nome, sexo, data_nascimento" +
                                ") VALUES ('" +
                                objActor.getNome() + "', '" +
                                objActor.getSexo() + "', '" +
                                objActor.getDataNascimento() +"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(incluirSQL) + "\n");
            int result = statement.executeUpdate(incluirSQL);
            if (result == 1)
            {
            	JOptionPane.showMessageDialog(null, "Atriz/Ator cadastrada corretamente!", "Mensagem de Informação",
            			JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {   
            	JOptionPane.showMessageDialog(null, "Erro ao cadastrar Atriz/Ator!", "Mensagem de Erro",
            			JOptionPane.ERROR_MESSAGE);
            	objActor.setNome("");
            	objActor.setSexo(' ');
            	objActor.setID(-1);    
            	objActor.setDataNascimento(-1);
            }
            statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de inclusão de Atriz/Ator!"); 
        }
    }
    public Object consultar(Object obj)
    {   
    	Actor objActor = (Actor) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            
        	//Query String
        	String consultarSQL = "SELECT * FROM ator_atriz WHERE (nome = '"+objActor.getNome()+"')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));
            
            ResultSet result = statement.executeQuery(consultarSQL);
            if (result.next())
            {   
            	System.out.println("Atriz/Ator existente:" );
                int id = result.getInt("id_ator_atriz");
                char sexo = result.getString("sexo").charAt(0);
                int dN = result.getInt("data_nascimento");
                result.close();
                
                objActor = new Actor(id, objActor.getNome(),sexo, dN);
            }
            else
            {   
            	System.out.println( "Atriz/Ator não encontrada!\n" );
                objActor = null;
            }
            
            statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de consulta de Atriz/Ator!"); 
        }
        return objActor;
    }
    public void excluir(Object obj)
    {   
    	Actor objActor = (Actor) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            String exclude = "DELETE FROM ator_atriz WHERE (id_ATOR_ATRIZ = '" + objActor.getID() + "')";
            System.out.println("Enviando código SQL: " + getConnection().nativeSQL(exclude) + "\n");
            
            if (statement.executeUpdate(exclude) == 1)
            {   
            	JOptionPane.showMessageDialog(null,"Atriz/Ator excluído corretamente!","Mensagem de Informação",
            			JOptionPane.INFORMATION_MESSAGE); 
            }
            else
            {   
            	JOptionPane.showMessageDialog(null,"Atriz/Ator não encontrado!","Mensagem de Erro",
            			JOptionPane.ERROR_MESSAGE); 
            }
           statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de exclusão do Atriz/Ator!"); 
        }
    }
}