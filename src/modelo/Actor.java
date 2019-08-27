package modelo;

import javax.swing.JOptionPane;
import persistencia.DMActor;

public class Actor
{
	//Attributes
	private String nome;
	private char sexo;
	private int data_nasc, idActor;

	private DMActor dmActor;
		
	//Get-Set
	public int getID() 
	{
		return this.idActor;
	}
	public void setID(int id) 
	{
		this.idActor = id;
	}
	
	public char getSexo() 
	{
		return this.sexo;
	}
	public void setSexo(char s) 
	{
		this.sexo = s;
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
	public Actor()
	{
		dmActor = new DMActor();
	}
	public Actor(String n, char s, int a)
	{
		this.data_nasc = a;
		this.sexo = s;
		this.nome = n;
		
		dmActor = new DMActor();
	    incluir(this);
	}
	public Actor(int id, String n, char s, int a)
	{
		this.idActor = id;
		this.nome = n;
		this.sexo = s;
		this.data_nasc = a;
		dmActor = new DMActor();
	}
	public Actor(String n)
	{
		this.nome = n;
		
		dmActor = new DMActor();
	    
		Actor ref = (Actor) consultar();
	    if(ref != null)
	    {
	    	this.data_nasc = ref.getDataNascimento();
	    	this.idActor = ref.getID();
	    }
	    else
	    {
	    	this.idActor = -1;
	    }
	}
	
	//Methods
	public void incluir(Actor objAct)
    {
		if (objAct.getNome().equals(""))
        {
    		JOptionPane.showMessageDialog(null, "O título da Série é obrigatório!", "Mensagem de alerta",
    				JOptionPane.WARNING_MESSAGE);
            System.out.println("O título da Série é obrigatório!");
        }
        else
        {   
        	if (dmActor.consultar(this)!= null)
	        {   
	    		JOptionPane.showMessageDialog(null, "Já existe uma Série com este título!", "Mensagem de Erro",
	    				JOptionPane.ERROR_MESSAGE);
	            System.out.println("Já existe uma Série com este título!"); 
	        }
	        else
	        {
	        	dmActor.incluir(this); 
	        }
        }
    }   
	public Object consultar()
    {
    	return dmActor.consultar(this);   
    }
	public void excluir()
    {
    	dmActor.excluir(this);
    }
    public void shutDown()
    {
    	dmActor.shutDown();   
    }
}