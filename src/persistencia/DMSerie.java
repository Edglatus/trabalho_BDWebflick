package persistencia;

import modelo.Serie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DMSerie extends DMGeral
{
	
	//Inherited Methods
    public void incluir(Object obj)
    {   
    	Serie objSerie = (Serie) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            
        	//Insertion String
        	String incluirSQL = "INSERT INTO serie (" +
                                "titulo, temporadas, id_DIRETOR_fk, id_ATOR_ATRIZ_fk" +
                                ") VALUES ('" +
                                objSerie.getTitulo() + "', '" +  
                                objSerie.getTemporadas() + "', '" +  
                                objSerie.getDiretor() + "', '" + 
                                objSerie.getAtor() +"')";
            System.out.println("Enviando c�digo SQL: " + getConnection().nativeSQL(incluirSQL) + "\n");
            int result = statement.executeUpdate(incluirSQL);
            if (result == 1)
            {
            	JOptionPane.showMessageDialog(null, "S�rie cadastrada corretamente!", "Mensagem de Informa��o",
            			JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {   
            	JOptionPane.showMessageDialog(null, "Erro ao cadastrar S�rie!", "Mensagem de Erro",
            			JOptionPane.ERROR_MESSAGE);
            	objSerie.setTitulo("");
            	objSerie.setID(0);    
            	objSerie.setAtor(0);
            	objSerie.setDiretor(0);
            }
            statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de inclus�o de S�rie!"); 
        }
    }
    public Object consultar(Object obj)
    {   
    	Serie objSerie = (Serie) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            
        	//Query String
        	String consultarSQL = "SELECT * FROM serie WHERE (titulo = '"+objSerie.getTitulo()+"')";
            System.out.println("Enviando c�digo SQL: " + getConnection().nativeSQL(consultarSQL));
            
            ResultSet result = statement.executeQuery(consultarSQL);
            if (result.next())
            {   
            	System.out.println("S�rie existente:" );
            	int id = result.getInt("id_SERIE");
            	String n = result.getString("titulo");
            	int ano = result.getInt("temporadas");
            	int atriz = result.getInt("id_ATOR_ATRIZ_fk");
            	int diretor = result.getInt("id_DIRETOR_fk");
            	result.close();
            	
            	objSerie = new Serie(id, n, ano, diretor, atriz);
                result.close();
            }
            else
            {   
            	System.out.println( "S�rie n�o encontrada!\n" );
                objSerie = null;
            }
            
            statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de consulta de S�rie!"); 
        }
        return objSerie;
    }
    public void excluir(Object obj)
    {   
    	Serie objSerie = (Serie) obj;
        try
        {   
        	Statement statement = getConnection().createStatement();
            String exclude = "DELETE FROM serie WHERE (id_SERIE = '" + objSerie.getID() + "')";
            System.out.println("Enviando c�digo SQL: " + getConnection().nativeSQL(exclude) + "\n");
            
            if (statement.executeUpdate(exclude) == 1)
            {   
            	JOptionPane.showMessageDialog(null,"S�rie exclu�da corretamente!","Mensagem de Informa��o",
            			JOptionPane.INFORMATION_MESSAGE); 
            }
            else
            {   
            	JOptionPane.showMessageDialog(null,"S�rie n�o encontrada!","Mensagem de Erro",
            			JOptionPane.ERROR_MESSAGE); 
            }
           statement.close();
        }
        catch (SQLException e)
        { 
        	System.out.println("Problemas com o SQL de exclus�o do S�rie!"); 
        }
    }
}