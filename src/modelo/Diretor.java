package modelo;

import javax.swing.JOptionPane;
import persistencia.DMDiretor;

public class Diretor
{
	//Attributes
	private String nome;
	private int data_nasc, idDiretor;

	private DMDiretor dmDiretor;
		
	//Get-Set
	public int getID() 
	{
		return this.idDiretor;
	}
	public void setID(int id) 
	{
		this.idDiretor = id;
	}
	
	public int getDataNascimento() 
	{
		return this.data_nasc;
	}
	public void setDataNascimento(int dN) 
	{
		this.data_nasc = dN;
	}

	public String getNome() 
	{
		return this.nome;
	}
	public void setNome(String n) 
	{
		this.nome = n;
	}
	
	//Constructors
	public Diretor()
	{	
		dmDiretor = new DMDiretor();
	}
	public Diretor(int id, String n, int a)
	{
		this.idDiretor = id;
		this.nome = n;
		this.data_nasc = a;
		dmDiretor = new DMDiretor();
	}
	public Diretor(String n, int a)
	{
		this.nome = n;
		this.data_nasc = a;
		
		dmDiretor = new DMDiretor();
	   	
	   	Diretor ref = (Diretor) consultar();
	    if(ref == null)
	    	incluir(this);
	    else
	    {
	    	this.idDiretor = ref.getID();
	    }
	}
	public Diretor(String n)
	{
		this.nome = n;
		
		dmDiretor = new DMDiretor();
	    
	   	Diretor ref = (Diretor) consultar();
	    if(ref != null)
	    {
	    	this.data_nasc = ref.getDataNascimento();
	    	this.idDiretor = ref.getID();
	    }
	    else
	    {
	    	this.idDiretor = -1;
	    }
	}
	
	//Methods
	public void incluir(Diretor objDir)
    {
		if (objDir.getNome().equals(""))
        {
    		JOptionPane.showMessageDialog(null, "O nome do Diretor é obrigatório!", "Mensagem de alerta",
    				JOptionPane.WARNING_MESSAGE);
            System.out.println("O nome do Diretor é obrigatório!");
        }
        else
        {   
        	if (dmDiretor.consultar(this)!= null)
	        {   
	    		JOptionPane.showMessageDialog(null, "Já existe um Diretor com este nome!", "Mensagem de Erro",
	    				JOptionPane.ERROR_MESSAGE);
	            System.out.println("Já existe um Diretor com este nome!"); 
	        }
	        else
	        {
	        	dmDiretor.incluir(this); 
	        }
        }
    }   
	public Object consultar()
    {
    	return dmDiretor.consultar(this);   
    }
	public void excluir()
    {
    	dmDiretor.excluir(this);
    }
    public void shutDown()
    {
    	dmDiretor.shutDown();   
    }
}