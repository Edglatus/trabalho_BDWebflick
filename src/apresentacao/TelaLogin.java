package apresentacao;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JPanel
{
	private static final long serialVersionUID = -8988048148198683034L;

	private JPanel guiPanel;
	
	public JTextField fLogin, fSenha;

	public TelaLogin ()
	{		
		guiPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		guiPanel.setBorder(BorderFactory.createTitledBorder("Login to \"bd_webflick_oop\":"));
		
		//Labels & Text fields & Buttons
		JLabel lLogin = new JLabel("Login: ");
		JLabel lSenha = new JLabel("Senha: ");
		
		fLogin = new JTextField(40);
		fSenha = new JPasswordField(40);
				
		guiPanel.add(lLogin);
		guiPanel.add(fLogin);
		
		guiPanel.add(lSenha);
		guiPanel.add(fSenha);
				
		this.add(guiPanel);
	}
}
