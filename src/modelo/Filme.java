package modelo;

import javax.swing.JOptionPane;
import persistencia.DMFilme;

public class Filme
{
	//Attributes
	private String titulo;
	private int ano_lancamento, idFilme, cod_diretor, cod_ator;

	private DMFilme dmFilme;
		
	//Get-Set
	public int getID() 
	{
		return this.idFilme;
	}
	public void setID(int c) 
	{
		this.idFilme = c;
	}
	
	public int getAno() 
	{
		return this.ano_lancamento;
	}
	public void setAno(int aL) 
	{
		this.ano_lancamento = aL;
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
	public Filme()
	{
		dmFilme = new DMFilme();
	}
	public Filme(String t, int dL, int dir, int act)
	{
		this.titulo = t;
		this.ano_lancamento = dL;
		this.cod_diretor = dir;	
		this.cod_ator = act;
		
		dmFilme = new DMFilme();
	    incluir(this);
	}
	public Filme(int id, String t, int dL, int dir, int act)
	{
		this.idFilme = id;
		this.titulo = t;
		this.ano_lancamento = dL;
		this.cod_diretor = dir;	
		this.cod_ator = act;
		
		dmFilme = new DMFilme();
	}
	
	//Methods
	public void incluir(Filme objF)
    {
		if (objF.getTitulo().equals(""))
        {
    		JOptionPane.showMessageDialog(null, "O título do Filme é obrigatório!", "Mensagem de alerta",
    				JOptionPane.WARNING_MESSAGE);
            System.out.println("O título do Filme é obrigatório!");
        }
        else
        {   
        	if (dmFilme.consultar(this)!= null)
	        {   
	    		JOptionPane.showMessageDialog(null, "Já existe um Filme com este título!", "Mensagem de Erro",
	    				JOptionPane.ERROR_MESSAGE);
	            System.out.println("Já existe um Filme com este título!"); 
	        }
	        else
	        {
	        	dmFilme.incluir(this); 
	        }
        }
    }   
	public Object consultar()
    {
    	return dmFilme.consultar(this);   
    }
	public void excluir()
    {
    	dmFilme.excluir(this);
    }
    public void shutDown()
    {
    	dmFilme.shutDown();   
    }
}