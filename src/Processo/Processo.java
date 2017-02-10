package Processo;

public class Processo<E> {

	private E Elemento;
	int tempoRestante;
	private int tempoRetorno;
	private int tempoResposta;
	private int prioridade;
	
	public Processo(){}
	
	public Processo(E elemento, int tempoRestante, int prioridade) {
		
		Elemento = elemento;
		this.tempoRestante = tempoRestante;
		this.prioridade = prioridade;
	}
	
	
	public E getElemento() {
		return Elemento;
	}
	public void setElemento(E elemento) {
		Elemento = elemento;
	}
	public int getTempoRestante() {
		return tempoRestante;
	}
	public void setTempoRestante(int tempoRestante) {
		this.tempoRestante = tempoRestante;
	}
	public int getTempoRetorno() {
		return tempoRetorno;
	}
	public void setTempoRetorno(int tempoRetorno) {
		this.tempoRetorno = tempoRetorno;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public int getTempoResposta() {
		return tempoResposta;
	}
	public void setTempoResposta(int tempoResposta) {
		this.tempoResposta = tempoResposta;
	}
	
	public Processo<E> clone(){
		Processo<E> copia = new Processo<>();
		copia.Elemento = Elemento;
		copia.prioridade = prioridade;
		copia.prioridade = prioridade;
		copia.tempoResposta = tempoResposta;
		copia.tempoRestante = tempoRestante;
		copia.tempoRetorno = tempoRetorno;
		
		return copia;
	}
	
	public String toString(){
		
		return Elemento +" " ;
	}

}
