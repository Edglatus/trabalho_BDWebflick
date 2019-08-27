package apresentacao;

import javax.swing.*;

import modelo.Actor;

import java.awt.*;

public class TelaActor extends Tela<Actor>
{
	private static final long serialVersionUID = 1105290755443069525L;

	//Attributes
	private JTextField fSexo;
	
	public Actor getActor()
	{
		saveObject();
		return this.objRef;
	}
	
	//Constructor
	public TelaActor(boolean option)
	{
		createPanel("");
		if(option)
			displayButtons();
		
		this.repaint();
	}
	public TelaActor(boolean option, String defaultName)
	{
		createPanel(defaultName);
		if(option)
			displayButtons();
		
		this.repaint();
	}
	
	//Inherited Methods
	public static JFrame createWindow()
	{
		JFrame returnFrame = Tela.createWindow();		

		returnFrame.setTitle("Nova Atriz");
		
		Container c = returnFrame.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
						
		c.add(new TelaActor(true));
		
		returnFrame.pack();
		returnFrame.setSize(340, 200);
		returnFrame.setVisible(true);
		returnFrame.setLocationRelativeTo(null);
		
		return returnFrame;
	}
	protected void createPanel(String defaultName)
	{
		//Atributos da Janela		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
						
		guiPanel = new JPanel(new GridLayout(4, 0, 10, 10));
		guiPanel.setBorder(BorderFactory.createTitledBorder("DADOS DA ATRIZ:"));
		
		//Labels & Text fields & Buttons
		JLabel lNome = new JLabel("Nome:");
		JLabel lSexo = new JLabel("Sexo(F/M):");
		JLabel lAno = new JLabel("Ano de Nascimento:");
				
		fNome = new JTextField(defaultName, 40);
		fSexo = new JTextField(1);
		fAno = new JTextField("1900", 4);
		
		guiPanel.add(lNome);
		guiPanel.add(fNome);
		
		guiPanel.add(lSexo);
		guiPanel.add(fSexo);
		
		guiPanel.add(lAno);
		guiPanel.add(fAno);
		
		this.add(guiPanel);
	}
	protected void saveObject()
	{
		
		if(!fNome.getText().equals("") || !fAno.getText().contentEquals("") || 
				(fSexo.getText().length() < 1 && fSexo.getText().charAt(0) != 'm' && fSexo.getText().charAt(0) != 'f'))
		{
			String n = fNome.getText();
			char s = fSexo.getText().charAt(0);
			int ano = Integer.parseInt(fAno.getText());
			
			objRef = new Actor(n, s, ano);
			objRef = (Actor) new Actor(objRef.getNome()).consultar();
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
			objRef = (Actor) new Actor(fNome.getText()).consultar();
			if(objRef != null)
				objRef.excluir();
			else
				JOptionPane.showMessageDialog(null,"Não existe Atriz com este nome!");
		}
		else
		{
			JOptionPane.showMessageDialog(null,"O Código está vazio! O preenchimento é obrigatório!");
		}
	}
}
