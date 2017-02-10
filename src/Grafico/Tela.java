package Grafico;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import Processo.Processo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

/*
 * 
 * 
 * ESTA CLASSE CRIA A INTERFACE GRÁFICO DO APP
 * 
 * 
 * */




public class Tela extends JFrame {
	
	
	

	
	private JPanel contentPane;
	JPanel panelGrafico;
	ChartPanel grafico;
	private JTextField txtAbrirArq;
	JRadioButton radioRoundRobin;
	JRadioButton radioFifo;
	JRadioButton radioPrioridades;
	ButtonGroup radioGroup = new ButtonGroup();
	JLabel lblTempoDeResposta;
	JLabel lblTempoDeVazao;
	JLabel lblTempoDeRetorno;
	

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Criando frame
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 514);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Button Executar = new Button("Executar");
		Executar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Dados dados = new Dados();
				//Escolhe escalonador a ser executado
				int escalonador;
				if(radioRoundRobin.isSelected()){
					escalonador = 1;
				}else if(radioFifo.isSelected()){
					escalonador = 2;
				}else{
					escalonador= 3;
				}
				
				// Executa os processos com escalonador escolhido
				try {
					dados.dados(escalonador,txtAbrirArq.getText());
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
				atualizarLabel(dados.getTempVazao(), dados.getTempResposta(), dados.getTempRetorno());				
				ArrayList<Processo> Processos = dados.dadosProcsExecucao;

				//Conjunto de dados a plotar no grafico
				DefaultCategoryDataset f = new DefaultCategoryDataset();
				
				// Atualiza Dataset para o gráfico
				for (int i = 0; i < Processos.size(); i++) {
					f.addValue(Processos.get(i).getTempoRestante(),""+Processos.get(i).getElemento(), i+""+ Processos.get(i).getElemento());
				}
				
				// Criando grafico
				JFreeChart graph = ChartFactory.createBarChart("Escalonador", "Processos", "Tempo Processo", f);
				//Plotando gráfico
				CategoryPlot plot = graph.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				grafico = new ChartPanel(graph);
				panelGrafico.removeAll();
				panelGrafico.setLayout(new java.awt.BorderLayout());
				panelGrafico.add(grafico,BorderLayout.CENTER);
				grafico.setBounds(35, 24, 392, 176);
				grafico.validate();
				
								
			
				
				
			}
		});
		Executar.setBounds(60, 445, 86, 23);
		contentPane.add(Executar);
		
		JButton abrirArq = new JButton("Abrir");
		abrirArq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				String nomeArq = abrirArquivo();
				txtAbrirArq.setText(nomeArq);
				
			}

			
		});
		
		//Incializando variáveis
		
		abrirArq.setBounds(322, 320, 117, 25);
		contentPane.add(abrirArq);
		
		txtAbrirArq = new JTextField();
		txtAbrirArq.setBounds(49, 321, 238, 23);
		contentPane.add(txtAbrirArq);
		txtAbrirArq.setColumns(10);
		
		
		radioRoundRobin = new JRadioButton("Round Robin");
		radioRoundRobin.setBounds(49, 352, 149, 23);
		contentPane.add(radioRoundRobin);
		
		radioFifo = new JRadioButton("Fifo");
		radioFifo.setBounds(49, 376, 149, 23);
		contentPane.add(radioFifo);
		
		radioPrioridades = new JRadioButton("Prioridades");
		radioPrioridades.setBounds(49, 399, 149, 23);
		contentPane.add(radioPrioridades);
		
		lblTempoDeRetorno = new JLabel("Tempo de Retorno");
		lblTempoDeRetorno.setBounds(49, 275, 380, 23);
		contentPane.add(lblTempoDeRetorno);
		
		lblTempoDeVazao = new JLabel("Tempo de Vazao");
		lblTempoDeVazao.setBounds(49, 250, 380, 23);
		contentPane.add(lblTempoDeVazao);
		
		lblTempoDeResposta = new JLabel("Tempo de Resposta");
		lblTempoDeResposta.setBounds(49, 229, 380, 23);
		contentPane.add(lblTempoDeResposta);
		
		radioGroup.add(radioRoundRobin);
		radioGroup.add(radioFifo);
		radioGroup.add(radioPrioridades);
		
		panelGrafico = new JPanel();
		panelGrafico.setBounds(12, 35, 465, 182);
		contentPane.add(panelGrafico);
		
		
		
	}
	
	private String abrirArquivo() {
		JFileChooser openArq = new JFileChooser();
		openArq.showOpenDialog(getParent());
		File nomeArq = openArq.getSelectedFile();
		return nomeArq.getAbsolutePath();

	}


	void atualizarLabel(float vazao,float resposta, float retorno){
		lblTempoDeResposta.setText("Tempo de Resposta: "+Float.toString(resposta));
		lblTempoDeRetorno.setText("Tempo de Retorno: "+Float.toString(retorno));
		lblTempoDeVazao.setText("Tempo de Vazao: "+Float.toString(vazao));
		
	}
}