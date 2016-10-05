package polinomios;

import java.io.*;
import java.util.Random;

public class GenPolinRand {

	public GenPolinRand() {
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter("./Preparacion de Prueba/Lote de Prueba/Entrada/pruebaTiempoTodoEnUno.in");
			pw = new PrintWriter(fw);
				
			Random rand = new Random();
				
			for (int i = 8500; i >= 0; i--) {
				pw.println(i + " " + rand.nextInt((100 - 0) + 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
				pw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	 public static void main(String[] args) { 
		 GenPolinRand ran = new GenPolinRand();
		 // Imprimo 'ran' para not tener warning
		 System.out.println(ran);
	 }
}
