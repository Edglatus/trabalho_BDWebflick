package apresentacao;

import javax.swing.*;
import modelo.Diretor;
import java.awt.*;

public class TelaDiretor extends Tela<Diretor>
{
	private static final long serialVersionUID = 1105290755443069525L;

	//Attributes	
	public Diretor getDiretor()
	{
		saveObject();
		return this.objRef;
	}
	
	//Constructors
	public TelaDiretor(boolean option)
	{
		createPanel("");
		if(option)
			displayButtons();
		
		this.repaint();
	}
	public TelaDiretor(boolean option, String defaultName)
	{
		createPanel(defaultName);
		if(option)
			displayButtons();
		
		this.repaint();
	}
	
	//Methods
	public static JFrame createWindow()
	{
		JFrame returnFrame = Tela.createWindow();		

		returnFrame.setTitle("Novo(a) Diretor(a)");
		
		Container c = returnFrame.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
						
		c.add(new TelaDiretor(true));
		
		returnFrame.pack();
		returnFrame.setSize(340, 140);
		returnFrame.setVisible(true);
		returnFrame.setLocationRelativeTo(null);
		
		return returnFrame;
	}
	protected void createPanel(String defaultName)
	{
		//Atributos da Janela
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
						
		guiPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		guiPanel.setMaximumSize(new Dimension(320, 70));
		guiPanel.setBorder(BorderFactory.createTitledBorder("DADOS DO DIRETOR:"));
		
		//Labels & Text fields & Buttons
		JLabel lNome = new JLabel("Nome:");
		JLabel lAno = new JLabel("Ano de Nascimento:");
				
		fNome = new JTextField(defaultName, 40);
		fAno = new JTextField("1900", 4);
		
		guiPanel.add(lNome);
		guiPanel.add(fNome);
		
		guiPanel.add(lAno);
		guiPanel.add(fAno);
		
		this.add(guiPanel);
	}
	protected void saveObject()
	{
		if(!fNome.getText().equals("") || !fAno.getText().contentEquals(""))
		{
			String n = fNome.getText();
			int ano = Integer.parseInt(fAno.getText());
			
			objRef = new Diretor(n, ano);
			objRef = (Diretor) new Diretor(objRef.getNome()).consultar();
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Um dos campos está vazio! O preenchimento é obrigatório!");
		}
	}
	protected void deltObject()
	{
		if(!fAno.getText().equals(""))
		{
			objRef = (Diretor) new Diretor(fNome.getText()).consultar();
			if(objRef != null)
				objRef.excluir();
			else
				JOptionPane.showMessageDialog(null,"Não existe Diretor(a) com este nome!");						
		}
		else
		{
			JOptionPane.showMessageDialog(null,"O Código está vazio! O preenchimento é obrigatório!");
		}

	}
	
}
