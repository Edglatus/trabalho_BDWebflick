package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DMGeral
{
	//Attributes
	protected static Connection connection;

	//Get-Set
	public static Connection getConnection()
	{	
		return connection;
	}
	
	//Methods			
	public static void conectaDataBase(String dataBase, String userName, String password)
	{	
		//Connection String
		String url = "jdbc:mysql://localhost/"+dataBase+"?serverTimezone=UTC";
		
		try
		{	
			//MySQL Class Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Connect to Database
		   	connection = DriverManager.getConnection(url,userName,password);
			System.out.println("Conexao ao banco de dados feita com sucesso!");
		} 
		catch (ClassNotFoundException cnfex)
		{	
			//Driver not Found
			System.err.println("Falha ao abrir o driver JDBC/ODBC");
		}
		catch (SQLException sqlex)
		{	
			System.err.println("Impossível conectar");
		}
	}
	public void shutDown()
	{	
		try
		{	
			connection.close();	
		}
		catch (SQLException sqlex)
		{
			System.err.println("Impossível desconectar");
		  	sqlex.printStackTrace();
		}
	}

	//Abstract Methods
	public abstract void incluir(Object obj); 
	public abstract Object consultar(Object obj);
	public abstract void excluir(Object obj);
}