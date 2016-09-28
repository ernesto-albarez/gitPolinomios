package polinomios;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Polinomio {

	private double[] coeficientes;
	private int grado;

	public Polinomio(String ruta) {
		Locale.setDefault(new Locale("en", "us"));
		Scanner sc = null;
		try {
			sc = new Scanner(new File(ruta));
			this.grado = sc.nextInt();
			if (sc.hasNext()) {
				this.coeficientes = new double[this.grado + 1];
				this.coeficientes[this.grado] = sc.nextDouble();
			}
			while (sc.hasNext()) {
				this.coeficientes[sc.nextInt()] = sc.nextDouble();
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public double evaluarMSucesivas(double x) { // O(n^3)
		double acum = 0;
		for (int i = 0; i < this.coeficientes.length; i++) {
			if (this.coeficientes.length != 0) {
				double multSuc = 1;
				for (int j = 0; j < i; j++) {
					multSuc *= x;
				}
				acum += (multSuc * this.coeficientes[i]);
			}
		}
		return acum;
	}

	public double evaluarRecursiva(double x) { // O(^2)
		double acum = 0;
		for (int i = 0; i < coeficientes.length; i++) {
			acum += (this.coeficientes[i] * this.potencia(x, i));
		}
		return acum;
	}

	public double potencia(double x, int n) {
		double res = 0;
		if (n > 1) {
			res = x * potencia(x, n - 1);
		}
		if (n == 1)
			res = x;
		if (n == 0)
			res = 1;
		return res;
	}

	public double evaluarRecursivaPar(double x) { // O(n^2)
		double acum = 0;
		for (int i = 0; i < coeficientes.length; i++)
			acum += (this.coeficientes[i] * this.potenciaPar(x, i));
		return acum;
	}

	public double potenciaPar(double x, int n) {
		double res = 0;
		if (n % 2 == 0)
			if (n == 0)
				res = 1;
			else
				res = potenciaPar(x * x, n - 1);
		else
			res = x * potenciaPar(x, n - 1);
		return res;
	}

	public double evaluarProgDinamica(double x) { // O(n)
		double acum = 0;
		double[] vectorPotencia = new double[this.coeficientes.length];
		for (int i = 0; i < vectorPotencia.length; i++) {
			vectorPotencia[i] = 0;
		}
		for (int i = 0; i < coeficientes.length; i++) {
			if (vectorPotencia[i] == 0 && x != 0)
				vectorPotencia[i] = this.potencia(x, i);
			acum += this.coeficientes[i] * vectorPotencia[i];
		}
		return acum;
	}

	public double evaluarMejorada(double x) { // O(n)
		double acum = 0;
		double[] vectorPotencia = new double[this.coeficientes.length];
		for (int i = 0; i < vectorPotencia.length; i++) {
			vectorPotencia[i] = 0;
		}
		for (int i = 0; i < coeficientes.length; i++) {
			if (vectorPotencia[i] == 0 && x != 0)
				vectorPotencia[i] = this.potenciaPar(x, i);
			acum += this.coeficientes[i] * vectorPotencia[i];
		}
		return acum;
	}

	public double evaluarPow(double x) { // O(1)
		double acum = 0;
		for (int i = 0; i < coeficientes.length; i++) {
			acum += this.coeficientes[i] * Math.pow(x, i);
		}
		return acum;
	}

	public double evaluarHorner(double x) { // O(n)
		double acum = 0;
		for (int i = this.coeficientes.length - 1; i >= 0; i--) {
			acum = acum * x + this.coeficientes[i];
		}
		return acum;
	}

	public static void main(String[] args) {
		Polinomio pol1 = new Polinomio("polinomio1.in");
		
		System.out.println("evaluarMSucesivas: " + pol1.evaluarMSucesivas(1000));
		System.out.println("evaluarPow: " + pol1.evaluarPow(1000) + "\n");
		
		for (int i = 0; i < pol1.coeficientes.length; i++) {
			System.out.println("grado: " + i + " --Coeficiente: " + pol1.coeficientes[i]);
		}
		
		System.out.println("\npotencia--" + pol1.potencia(1000, 0));
		System.out.println("evaluarRecursiva: " + pol1.evaluarRecursiva(1000));
		System.out.println("evaluarRecursivaPar: " + pol1.evaluarRecursivaPar(1000));
		
		Calendar tIni = new GregorianCalendar();
		System.out.println("evaluarProgDinamica: " + pol1.evaluarProgDinamica(1000));
		
		Calendar tFin = new GregorianCalendar();
		System.out.println("\nTiempo de ejecucion de PorgDinamica: " + (tFin.getTimeInMillis() - tIni.getTimeInMillis()));
		
		System.out.println("evaluarHorner: " + pol1.evaluarHorner(1000));
		tIni = new GregorianCalendar();
		
		System.out.println("evaluarMejorada: " + pol1.evaluarMejorada(1000));
		tFin = new GregorianCalendar();
		
		System.out
				.println("Tiempo de ejecucion de evaluarMejorada: " + (tFin.getTimeInMillis() - tIni.getTimeInMillis()));
	}

}
