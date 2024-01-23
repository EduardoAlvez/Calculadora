package br.com.cod3r.calculadora.visao;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Botao extends JButton{
	
	public Botao(String texto, Color cor) {
	setText(texto);
	setBackground(cor);
	setForeground(Color.WHITE);
	setFont(new Font("coutier", Font.PLAIN, 25));
	setOpaque(true);
	
	setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

}
