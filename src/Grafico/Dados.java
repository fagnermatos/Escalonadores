package Grafico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Fifo.Fifo;
import Prioridades.Prioridades;
import Processo.Processo;
import RoudRobin.RoundRobin;


/*
 * 
 * 
 * ESTA CLASSE LER O ARQUIVO E EXECUTA OS PROCESSOS
 * 
 * 
 * 
 * */

public class Dados {
	
	ArrayList<Processo> fila = new ArrayList<>();
	ArrayList<Processo> filaFinalizados = new ArrayList<>();
	ArrayList<Processo> dadosProcsExecucao = new ArrayList<>();
	int quantum;
	int qtdProcessos;
	float vazao;
	float resposta;
	float retorno;

	public void dados(int op, String arquivo) throws IOException{
	
	FileReader arch = null;
	try {
		arch = new FileReader(arquivo);
		BufferedReader read = new BufferedReader(arch);
		
		String quanProc[] = read.readLine().split(" ");  
		quantum = Integer.parseInt(quanProc[0]);
		qtdProcessos = Integer.parseInt(quanProc[1]);
		
		String proc = read.readLine();
		while(proc!=null){
			
			String temp[] = proc.split(" ");
			
			String elemento = temp[0];
			int tempoRestante = Integer.parseInt(temp[1]);
			int prioridade = Integer.parseInt(temp[2]);
			
			Processo novoProc = new Processo(elemento,tempoRestante,prioridade);
			fila.add(novoProc);
			proc = read.readLine();
			
		}
		
		
		switch (op) {
		case 1:
			RoundRobin escalonador = new RoundRobin(quantum,qtdProcessos);
			escalonador.Executar(fila);
			atualizaDados(escalonador);
			break;
		case 2:
			Fifo escalonador1 = new Fifo(qtdProcessos);
			escalonador1.Executar(fila);
			atualizaDados(escalonador1);
			break;
		case 3:
			Prioridades escalonador2 = new Prioridades(qtdProcessos);
			escalonador2.Executar(fila);
			atualizaDados(escalonador2);
		default:
			break;
		}
		
	
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	System.out.println("Tempo de vazao: "+ vazao);
	System.out.println("Tempo de retorno medio : "+ retorno);
	System.out.println("Tempo de resposta medio : "+ resposta);
	
	
	
	
	}

	private void atualizaDados(RoundRobin escalonador) {
		// TODO Auto-generated method stub
		filaFinalizados = escalonador.getFinalizados();
		dadosProcsExecucao = escalonador.getDadosProcsExecucao();
		vazao = escalonador.getVazao();
		resposta = escalonador.getTempRespostaMedia();
		retorno = escalonador.getTempRetornoMedia();
	
		
	}
	private void atualizaDados(Prioridades escalonador) {
		// TODO Auto-generated method stub
		filaFinalizados = escalonador.getFinalizados();
		dadosProcsExecucao = escalonador.getDadosProcsExecucao();
		vazao = escalonador.getVazao();
		resposta = escalonador.getTempRespostaMedia();
		retorno = escalonador.getTempRetornoMedia();
	
		
	}
	private void atualizaDados(Fifo escalonador) {
		// TODO Auto-generated method stub
		filaFinalizados = escalonador.getFinalizados();
		dadosProcsExecucao = escalonador.getDadosProcsExecucao();
		vazao = escalonador.getVazao();
		resposta = escalonador.getTempRespostaMedia();
		retorno = escalonador.getTempRetornoMedia();
	
		
	}
	
	float getTempResposta(){
		return resposta;
	}
	float getTempRetorno(){
		return retorno;
	}
	float getTempVazao(){
		return vazao;
	}

}
