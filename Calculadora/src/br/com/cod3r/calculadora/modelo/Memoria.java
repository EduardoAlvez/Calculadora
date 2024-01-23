package br.com.cod3r.calculadora.modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private static final Memoria instancia = new Memoria();

	private enum TipoComandos {
		ZERAR, NUMERO, MULTIPLICACAO, DIVISAO, IGUAL, SOMA, SUBTRACAO, PORCENTAGEM, LIMPAR, VIRGULA
	};

	private final List<MemoriaObservador> observadores = new ArrayList<MemoriaObservador>();
	private TipoComandos ultimaOperacao = null;
	private boolean substituir = false;
	private String textoAtual = "";
	private String textoTemp = "";

	private Memoria() {
	}

//	MÉTODOS
	public void adicionarObservadores(MemoriaObservador obs) {
		observadores.add(obs);
	}

	public void processarComando(String texto) {
//		ENTENDA: AQUI VC PEGA O VALOR DO BOTAO E ADICIONA NA TELA
		TipoComandos tipo = detectarComando(texto);
		System.out.println(tipo);
		System.out.println(substituir);
		if (tipo == null) {
			return;
		} else if (tipo == TipoComandos.ZERAR) {
			this.textoAtual = "";
			this.textoTemp = "";
			this.substituir = false;
			this.ultimaOperacao = null;
		} else if (tipo == TipoComandos.NUMERO && tipo == TipoComandos.VIRGULA) {
			this.textoAtual = substituir ? texto : this.textoAtual + texto;
			substituir = false;
		} else {
			substituir = true;
			textoAtual = obterResultadoOperacao();
			textoTemp = textoAtual;
			ultimaOperacao = tipo;
		}
		observadores.forEach(o -> o.valorAlterado(getTexto()));
	}

	private String obterResultadoOperacao() {
		if(ultimaOperacao == null && ultimaOperacao == TipoComandos.IGUAL) {
			return textoAtual;
		}
		
		double numeroTemp = Double.parseDouble(textoTemp);
		double numeroAtual = Double.parseDouble(textoAtual);
		double resultado = 0;
		
		if(ultimaOperacao == TipoComandos.SUBTRACAO) {
			resultado = numeroTemp - numeroAtual;
		}else if(ultimaOperacao == TipoComandos.SOMA) {
			resultado = numeroTemp + numeroAtual;
		}
		
		String resultadoString = Double.toString(resultado);
		boolean inteiro = resultadoString.endsWith(",0");
		
		return inteiro ? resultadoString.replace(",0", ""): resultadoString;
	}

	private TipoComandos detectarComando(String texto) {
		if (this.textoAtual.isEmpty() && texto == "0") {
			return null;
		}
		try {
			Integer.parseInt(texto);
			return TipoComandos.NUMERO;
		} catch (NumberFormatException e) {
			// QUANDO NAO FOR NUMERO...
			if ("AC".equals(texto)) {
				return TipoComandos.ZERAR;
			} else if ("/".equals(texto)) {
				return TipoComandos.DIVISAO;
			} else if ("*".equals(texto)) {
				return TipoComandos.MULTIPLICACAO;
			} else if ("+".equals(texto)) {
				return TipoComandos.SOMA;
			} else if ("-".equals(texto)) {
				return TipoComandos.SUBTRACAO;
			} else if ("=".equals(texto)) {
				return TipoComandos.IGUAL;
			} else if (",".equals(texto) && !this.textoAtual.contains(",")) {
				return TipoComandos.VIRGULA;
			} else if ("%".equals(texto)) {
				return TipoComandos.PORCENTAGEM;
			}
		}
		return null;
	}

//	GETS
	public static Memoria getInstancia() {
		return instancia;
	}

	public String getTexto() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}

}
