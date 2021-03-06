package Fifo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Processo.Processo;
import RoudRobin.RoundRobin;

public class Teste {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		ArrayList<Processo> fila = new ArrayList<>();
		int quantum;
		int qtdProcessos;
	
		FileReader arch = null;
		try {
			arch = new FileReader("procs");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
		
		Fifo escalonador = new Fifo(qtdProcessos);
		escalonador.Executar(fila);
		
		System.out.println("Tempo de vazao: "+ escalonador.getVazao());
		System.out.println("Tempo de retorno medio : "+ escalonador.getTempRetornoMedia());
		System.out.println("Tempo de resposta medio : "+ escalonador.getTempRespostaMedia());
		
	
	
		
		
	}

}
