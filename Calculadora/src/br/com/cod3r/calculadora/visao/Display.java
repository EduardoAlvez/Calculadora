package br.com.cod3r.calculadora.visao;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JPanel;

import br.com.cod3r.calculadora.modelo.Memoria;
import br.com.cod3r.calculadora.modelo.MemoriaObservador;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoriaObservador {
	
	private final Label label;
	
	public Display() {
//		ENTENDA: AQUI VC PEDE PRA SER NOTIFICADO QUANDO OUVER ALGUMA ALTERAÇÃO
		Memoria.getInstancia().adicionarObservadores(this);
		
		label = new Label(Memoria.getInstancia().getTexto()); // AQUI VC USA A INSTACIA MEMORIA PRA VER OS DADOS
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Courier", Font.PLAIN,25));
		
		setLayout(new FlowLayout(FlowLayout.LEFT, 10,20));
		add(label);
		
	}

	@Override
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
		
	}

}
