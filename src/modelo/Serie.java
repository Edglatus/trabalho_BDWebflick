package modelo;

import javax.swing.JOptionPane;
import persistencia.DMSerie;

public class Serie
{
	//Attributes
	private String titulo;
	private int temporadas, idSerie, cod_diretor, cod_ator;

	private DMSerie dmSerie;
		
	//Get-Set
	public int getID() 
	{
		return this.idSerie;
	}
	public void setID(int c) 
	{
		this.idSerie = c;
	}
	
	public int getTemporadas() 
	{
		return this.temporadas;
	}
	public void getTemporadas(int t) 
	{
		this.temporadas = t;
	}

	public String getTitulo() 
	{
		return this.titulo;
	}
	public void setTitulo(String n) 
	{
		this.titulo = n;
	}

	public int getDiretor() 
	{
		return this.cod_diretor;
	}
	public void setDiretor(int dir)
	{
		this.cod_diretor = dir;
	}

	public int getAtor() 
	{
		return this.cod_ator;
	}
	public void setAtor(int cA) 
	{
		this.cod_ator = cA;
	}
	
	//Constructors
	public Serie()
	{
		dmSerie = new DMSerie();
	}
	public Serie(String t, int num, int dir, int act)
	{
		this.titulo = t;
		this.temporadas = num;
		this.cod_diretor = dir;	
		this.cod_ator = act;
		
		dmSerie = new DMSerie();
	    incluir(this);
	}
	public Serie(int id, String t, int num, int dir, int act)
	{
		this.idSerie = id;
		this.titulo = t;
		this.temporadas = num;
		this.cod_diretor = dir;	
		this.cod_ator = act;
		
		dmSerie = new DMSerie();
	}
	
	//Methods
	public void incluir(Serie objS)
    {
		if (objS.getTitulo().equals(""))
        {
    		JOptionPane.showMessageDialog(null, "O título da Série é obrigatório!", "Mensagem de alerta",
    				JOptionPane.WARNING_MESSAGE);
            System.out.println("O título da Série é obrigatório!");
        }
        else
        {   
        	if (dmSerie.consultar(this)!= null)
	        {   
	    		JOptionPane.showMessageDialog(null, "Já existe uma Série com este título!", "Mensagem de Erro",
	    				JOptionPane.ERROR_MESSAGE);
	            System.out.println("Já existe uma Série com este título!"); 
	        }
	        else
	        {
	        	dmSerie.incluir(this); 
	        }
        }
    }   
	public Object consultar()
    {
    	return dmSerie.consultar(this);   
    }
	public void excluir()
    {
    	dmSerie.excluir(this);
    }
    public void shutDown()
    {
    	dmSerie.shutDown();   
    }
}