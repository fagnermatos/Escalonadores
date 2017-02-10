package RoudRobin;

import java.util.ArrayList;

import Processo.Processo;

public class RoundRobin {
	int quantum;
	int tempoTotal;
	int qtdProcessos;
	ArrayList<Processo> filaFinalizados = new ArrayList<>();
	ArrayList<Processo> dadosProcsExecucao = new ArrayList<>();
	
	public RoundRobin(int quantum,int qtdProcessos){
		this.quantum = quantum;
		this.qtdProcessos = qtdProcessos;
	}
	
	public void Executar(ArrayList<Processo> fila){
		
		
		int ciclo=0;
		
		while(!fila.isEmpty()){
			
			Processo aux = new Processo();
			Processo executando = new Processo();
			
			
			
			executando = escalonar(fila);
			
			// Armazena na variavel dadosProcsExecucao para plota no grafico
			dadosProcsExecucao.add(executando.clone());
			
			atualizaTempoResposta(executando,ciclo);
			atualizaTempoRestante(executando);
			
			
			
			
			
			if (finalizado(executando)) {
				atualizaTempoRetorno(executando,tempoTotal+quantum);	
				filaFinalizados.add(executando);
				fila.remove(0);
			}else {
				aux = executando;
				fila.remove(0);
				fila.add(aux);
			}
			ciclo++;
			tempoTotal+= quantum;
		}	
	}
	
	
	private void atualizaTempoRestante(Processo executando) {
		
		int tempRestante = executando.getTempoRestante();
		executando.setTempoRestante(tempRestante-quantum);
		
	}

	private void atualizaTempoRetorno(Processo executando, int tempo) {
		
		executando.setTempoRetorno(tempo);
	}


	private void atualizaTempoResposta(Processo executando, int ciclo) {
		
		if(ciclo < qtdProcessos ){
			executando.setTempoResposta(tempoTotal);
		}
	}


	private Processo escalonar(ArrayList<Processo> fila) {
		
		return fila.get(0);
	}


	public float getVazao(){
		
		if (filaFinalizados.isEmpty()) {
			return 0;
		}
		return (float)filaFinalizados.size()/tempoTotal;	
	}
	
	public float getTempRetornoMedia(){
		if (filaFinalizados.isEmpty()) {
			return 0;
		}
		
		int somaTempTotal = 0;
		for (int i = 0; i < filaFinalizados.size(); i++) {
			somaTempTotal += filaFinalizados.get(i).getTempoRetorno();
		}
		
		return (float)somaTempTotal/filaFinalizados.size();
		
	}
	
	public float getTempRespostaMedia(){
		if (filaFinalizados.isEmpty()) {
			return 0;
		}
		
		int somaTempTotal = 0;
		for (int i = 0; i < filaFinalizados.size(); i++) {
			somaTempTotal += filaFinalizados.get(i).getTempoResposta();
		}
		
		return (float)somaTempTotal/filaFinalizados.size();
		
	}
	
	public boolean finalizado(Processo processo){
		if(processo.getTempoRestante() == 0 ){
			return true;
		}
		return false;
	}

	public ArrayList<Processo> getFinalizados(){
		return filaFinalizados;
	}
	
	public ArrayList<Processo> getDadosProcsExecucao(){
		return dadosProcsExecucao;
	}
}
