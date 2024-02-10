package br.com.cod3r.calculadora.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.cod3r.calculadora.modelo.Memoria;
import br.com.cod3r.calculadora.modelo.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador {
	
	private final JLabel label;
	
	public Display() {
//		ENTENDA: AQUI VC PEDE PRA SER NOTIFICADO QUANDO OUVER ALGUMA ALTERAÇÃO
		Memoria.getInstancia().adicionarObservadores(this);
		
//		setBackground(new Color(46,49,50));
		label = new JLabel(Memoria.getInstancia().getTextoAtual()); // AQUI VC USA A INSTACIA MEMORIA PRA VER OS DADOS
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Courier", Font.PLAIN,25));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10,20));
		add(label);
		
	}

	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
		
	}

}
