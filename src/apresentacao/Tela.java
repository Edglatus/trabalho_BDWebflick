package apresentacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Tela<T> extends JPanel
{
	private static final long serialVersionUID = -7939528513161047072L;
	
	protected T objRef;
	protected JPanel guiPanel;
	protected JTextField fNome;
	protected JTextField fAno;
	
	public static JFrame createWindow()
	{
		JFrame returnFrame = new JFrame();		
				
		returnFrame.setSize(750, 150);
		returnFrame.setResizable(false);
		
		return returnFrame;
	}
	protected void displayButtons()
	{
		//Painel de Botões
		JPanel guiButtons = new JPanel();
		
		JButton bSave = new JButton("Salvar");
		JButton bDelete = new JButton("Excluir");
		JButton bClear = new JButton("Limpar");
		JButton bClose = new JButton("Fechar");
		
		guiButtons.add(bSave);
		guiButtons.add(bDelete);
		guiButtons.add(bClear);
		guiButtons.add(bClose);
		
		this.add(guiButtons);
		
		//Button Action Listeners
		bSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveObject();
			}
		});
		bDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				deltObject();
			}
		});
		bClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearTextFields();
			}
		});
		bClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.windowForComponent(guiPanel).dispose();
			}
		});
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
	
	protected abstract void createPanel(String defaultName);
	protected abstract void saveObject();
	protected abstract void deltObject();
}
