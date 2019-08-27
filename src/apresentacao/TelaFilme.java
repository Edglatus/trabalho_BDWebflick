package apresentacao;

import javax.swing.*;

import modelo.*;

import java.awt.*;

public class TelaFilme extends Tela<Filme> 
{
	private static final long serialVersionUID = 1105290755443069525L;

	//Attributes
	protected JTextField fDir;
	protected JTextField fAct;
	
	public TelaFilme()
	{
		createPanel("");
		
		this.repaint();
	}
	
	public static JFrame createWindow()
	{
		JFrame returnFrame = Tela.createWindow();		
		
		returnFrame.setTitle("Novo Filme");
		
		Container c = returnFrame.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
						
		c.add(new TelaFilme());
		
		returnFrame.pack();
		returnFrame.setSize(650, 140);
		returnFrame.setVisible(true);
		returnFrame.setLocationRelativeTo(null);
		
		return returnFrame;
	}
	protected void createPanel(String defaultName)
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		guiPanel = new JPanel(new GridLayout(0, 4, 10, 10));
		guiPanel.setMaximumSize(new Dimension(630, 70));
		guiPanel.setBorder(BorderFactory.createTitledBorder("DADOS DO FILME:"));
		
		//Labels & Text fields & Buttons
		JLabel lNome = new JLabel("T�tulo:");
		JLabel lAno = new JLabel("Ano de Lan�amento:");
		JLabel lDir = new JLabel("Nome do(a) Diretor(a):");
		JLabel lAct = new JLabel("Nome da Atriz principal:");
		
		fNome = new JTextField(40);
		fAno = new JTextField("1900", 4);
		fDir = new JTextField(25);
		fAct = new JTextField(25);
				
		guiPanel.add(lNome);
		guiPanel.add(fNome);
		
		guiPanel.add(lAno);
		guiPanel.add(fAno);
		
		guiPanel.add(lDir);
		guiPanel.add(fDir);
		
		guiPanel.add(lAct);
		guiPanel.add(fAct);
		
		this.add(guiPanel);
		
		displayButtons();
	}
	protected void clearTextFields()
	{
		for(Component jC : guiPanel.getComponents())
		{
			if(jC.getClass().equals(new JTextField().getClass()))
			{
				JTextField jT = (JTextField) jC;
				jT.setText("");
			}
		}
	}
	protected void saveObject()
	{
		if(!fNome.getText().equals("") && !fAno.getText().contentEquals("") 
				&& !fAct.getText().contentEquals("") && !fDir.getText().contentEquals(""))
		{
			String n = fNome.getText();
			int ano = Integer.parseInt(fAno.getText());
			String actN = fAct.getText();
			String dirN = fDir.getText();
			
			Diretor dir = new Diretor(dirN);
			
			if(dir.getID() == -1)
			{
				JOptionPane.showMessageDialog(null, "Diretor(a) n�o econtrado(a)!", "Aviso", JOptionPane.WARNING_MESSAGE);
				int getDir = 0;
				while(dir.getID() == -1 && getDir != JOptionPane.CANCEL_OPTION)
				{
					TelaDiretor tD = new TelaDiretor(false, fDir.getText());
					getDir = JOptionPane.showConfirmDialog(null, tD, "Novo(a) Diretor(a)", JOptionPane.OK_CANCEL_OPTION);
					
					if(getDir == JOptionPane.OK_OPTION)
						dir = tD.getDiretor();
					else
						break;
				}
			}
			
			Actor act = new Actor(actN);
			if(act.getID() == -1)
			{
				int getAct = 0;
				JOptionPane.showMessageDialog(null, "Atriz/Ator n�o econtrada!", "Aviso", JOptionPane.WARNING_MESSAGE);
				while(act.getID() == -1 && getAct != JOptionPane.CANCEL_OPTION)
				{
					TelaActor tA = new TelaActor(false, fAct.getText());
					getAct = JOptionPane.showConfirmDialog(null, tA, "Nova Atriz/Ator", JOptionPane.OK_CANCEL_OPTION);
					
					if(getAct == JOptionPane.OK_OPTION)
						act = tA.getActor();
					else
						break;
				}
			}
			
			if(dir.getID() != -1 && act.getID() != -1)
				objRef = new Filme(n, ano, dir.getID(), act.getID());
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Um dos campos est� vazio! O preenchimento � obrigat�rio!");
		}
	}
	protected void deltObject()
	{
		if(!fNome.getText().equals(""))
		{
			objRef = new Filme();
			objRef.setTitulo(fNome.getText());
			objRef = (Filme) objRef.consultar();
			
			objRef.excluir();
		}
		else
		{
			JOptionPane.showMessageDialog(null,"O T�tulo est� vazio! O preenchimento � obrigat�rio!");
		}
	}
}
