package br.com.cod3r.calculadora.visao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora extends JFrame{

	public Calculadora() {
		
		organizarLayout();
		
		setSize(355,550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	private void organizarLayout() {
		setLayout(new BorderLayout());
		Display display = new Display();
		display.setPreferredSize(new Dimension(355,65));
		add(display, BorderLayout.NORTH);

		Teclado teclado = new Teclado();		
		add(teclado, BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		new Calculadora();
	}
}
