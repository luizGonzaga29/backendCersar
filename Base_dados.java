package dados;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Base_dados {
	
	/*
	 sala1:
	 3 ar condicionados / potência 2100w
	 20 lâmpadas / potência 40w
	 15 pcs / 180w
	 */
	static final double[] potenciaSala1 = {2100.00, 40.00, 180.00};
	/*
	 * posição 0,1 qtd min max de ar
	 * posição 2,3 qtd min max de lâmpadas
	 * posição 4,5 qtd min max de pcs
	 * */
	
	static final int[] qtdSala1 = {2, 4, 12 , 21, 10, 16};
	static final int[] qtdFimDeSemana1 = {1, 3, 5, 11, 4, 9};
	
	/*
	 sala2:
	 2 ar condicionados / potência 2000w
	 15 lâmpadas / potência 40w
	 10 pcs / 180w
	 */
	static final double[] potenciaSala2 = {2000.00, 40.00, 180.00};
	/*
	 * posição 0,1 qtd min max de ar
	 * posição 2,3 qtd min max de lâmpadas
	 * posição 4,5 qtd min max de pcs
	 * */
	static final int[] qtdSala2 = {1, 3, 8, 16, 6, 11};
	static final int[] qtdFimDeSemana2 = {1, 2, 5, 11, 3, 7};
	
	static final int dias = 90;
	static final int[] salas = {1, 2};
	
	/*posição 0,1 : horas fim de semana
	 * posição 2,3 : horas dia de semana
	 * posição 4,5 : hora extra
	 * 	*/
	static final int[] horas = {2,7,8,10,10,17};
	
	public static void main(String[] args) {
		
		
		
		Calendar cal = Calendar.getInstance();
		String path = "C:\\Users\\cabra\\OneDrive\\Área de Trabalho\\Empresa_A.txt";
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
			
			calcularConsumo(cal,potenciaSala1, qtdSala1, salas[0],  bw, horas, qtdFimDeSemana1);
			cal = Calendar.getInstance();
			calcularConsumo(cal,potenciaSala2, qtdSala2, salas[1], bw, horas, qtdFimDeSemana2);
			
		}catch (IOException e) {
			e.getMessage();
		}
		

	}
	
	static void calcularConsumo(Calendar cal,double[] potencia, int[] qtd, int sala, BufferedWriter bw, int[] horas, int[] qtdFimSem) throws IOException {
		
		DecimalFormat df = new DecimalFormat("0.0000");
		df.setRoundingMode(RoundingMode.DOWN);
		String line = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for(int i = 0;i < dias;i++) {
			
			if(cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7) {
								
				line = "S:" + sala + "#d:" + sdf.format(cal.getTime()) + "#A:" + 
						df.format(potencia[0] * (gerarNumero(horas[0], horas[1]) + gerarMinutos()) / 1000.0 * gerarNumero(qtdFimSem[0], qtdFimSem[1])) +
						"#L:" + df.format(potencia[1] * (gerarNumero(horas[0], horas[1]) + gerarMinutos()) / 1000.0 * gerarNumero(qtdFimSem[2], qtdFimSem[3])) + "#E:" +
						df.format(potencia[2] * (gerarNumero(horas[0], horas[1]) + gerarMinutos()) / 1000.0 * gerarNumero(qtdFimSem[4], qtdFimSem[5])) + "#";
				
			}else if(cal.get(Calendar.DAY_OF_WEEK) == 4) {
				
				line = "S:" + sala + "#d:" + sdf.format(cal.getTime()) + "#A:" + 
						df.format(potencia[0] * (gerarNumero(horas[4], horas[5]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[0], qtd[1])) +
						"#L:" + df.format(potencia[1] * (gerarNumero(horas[4], horas[5]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[2], qtd[3])) + "#E:" +
						df.format(potencia[2] * (gerarNumero(horas[4], horas[5]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[4], qtd[5])) + "#";
				
			}else {
			
				line = "S:" + sala + "#d:" + sdf.format(cal.getTime()) + "#A:" + 
						df.format(potencia[0] * (gerarNumero(horas[2], horas[3]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[0], qtd[1])) +
						"#L:" + df.format(potencia[1] * (gerarNumero(horas[2], horas[3]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[2], qtd[3])) + "#E:" +
						df.format(potencia[2] * (gerarNumero(horas[2], horas[3]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[4], qtd[5])) + "#";
			}
			bw.write(line);
			bw.newLine();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			
		}
		
	}
	
	static int gerarNumero(int min, int max) {
		int x = 0;
		while(x < min) {
			x = new Random().nextInt(max);
		}
		return x;
		
	}
	
	static double gerarMinutos() {
		double x = 0.0;
		while(x < 1.0) {
			x = (double) new Random().nextInt(60);
		}
		return x / 60;
	}
	

}
