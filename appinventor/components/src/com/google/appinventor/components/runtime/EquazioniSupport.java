package com.google.appinventor.components.runtime;

import java.util.ArrayList;

public class EquazioniSupport extends EquazioneSupportAbstract
{

	/*
	 *	Lista di supporto per la gestione dei termini utili per il grafico
	 */
	private ArrayList<Double> equationsForGraph;
	

	
	@Override
	public ArrayList<String> equazioneDiPrimoGrado(String equazione)
	{
		equationsForGraph = new ArrayList<>();
		/*
		 * ArrayList in cui inserire il risultato
		 */
		ArrayList<String> result = new ArrayList<>();
		
		/*
		 * ArrayList in cui viene salavata l'equazione divisa in modo da avere ogni termine separato
		 */
		ArrayList<String> listEq = parserEquazione(equazione);
		
		/*
		 * Variabile in cui verranno sommate tutte i termini X
		 */
		double x = 0;
		
		/*
		 * Variabile in cui verranno sommati tutti i termini noti
		 */
		double noX = 0;
	
		result.add("Equazione: \n" + equazione);
	
		/*
		 * prendo l'equazione che c'e' dopo del simbolo uguale e la divido dalla prima equazione
		 */
		ArrayList<String> listEqDestra = new ArrayList<>();
		ArrayList<String> listEqSinistra = new ArrayList<>();
		
		int indexEqual = listEq.indexOf("=");
		listEqSinistra.addAll(listEq.subList(0, indexEqual));
		listEqDestra.addAll(listEq.subList(indexEqual+1, listEq.size()));		
		
		
		/*
		 * inverto i segni dell'equazione a destra del simbolo uguale e la unisco con l'equazione a sinistra del simbolo uguale
		 */
		for(String s : listEqDestra)
			listEqSinistra.add(invertiSegni(s));
		listEqSinistra.add("= 0");
		

		String tmp = listEqSinistra.toString().replace("[", "");
		tmp = tmp.replace("]", "");
		tmp = tmp.replace(",", "");
		result.add("Inverto i segni dell'equzione destra e la unisco con quella di sinistra: \n" + tmp);
		
		
		/*
		 * effettuo la somma di tutti i termini in x e tutti i termini noti
		 */
		
		listEqSinistra.remove("= 0");
		
		/*
		 * stringa in cui salvo tutti i termini in x
		 */
		String listaTerminiX = "";
		
		/*
		 * stringa in cui salvo tutti i termini noti
		 */
		String listaTerminiNoti = "";
		
		for(String s : listEqSinistra)
		{
			if (s.contains("x"))
			{
				listaTerminiX = listaTerminiX +" "+ s;
				s = s.replace("x", "");
				if (s.length() == 1)
					s += 1;
				x += Double.parseDouble(s);
			}
			else 
			{
				listaTerminiNoti = listaTerminiNoti +" "+ s;
				noX += Double.parseDouble(s);
			}
		}
		

		result.add("Termini: \n" 
					+ " -	X: " + x +" \n" 
					+ " -	TERMINE NOTO: " + noX);
		
		
		equationsForGraph.add(0.0);
		equationsForGraph.add(x);
		equationsForGraph.add(noX);

		
		String xString = "";
		String noXString = "";
		if((x+"").charAt(0) != ('+' | '-'))
			 xString = "+"+x;
		if((noX+"").charAt(0) != ('+' | '-'))
			noXString = "+"+noX;
			
		
		result.add("equazione di primo grado: \n" + xString+"x" +" = " + noXString);
		
		String res = "";
		if (x != +1)
		{
			double ris = noX / -x;
			res = "x = " + ris;
		} else
			res = "x = " + noX;
		
		result.add("risultato: \n" + res);
		
		return result;
	}
		
	
	@Override
	public ArrayList<String> equazioniDiSecondoGrado(String equazione)
	{
		equationsForGraph = new ArrayList<>();
		/*
		 * ArrayList in cui inserire il risultato
		 */
		ArrayList<String> result = new ArrayList<>();
		
		result.add("Equazione: \n" + equazione);
				
		/*
		 * ArrayList in cui viene salavata l'equazione divisa in modo da avere ogni termine separato
		 */
		ArrayList<String> listEq = parserEquazione(equazione);
		
		/*
		 * Variabile in cui verranno sommate tutte i termini x^2
		 */
		double a = 0;
		
		/*
		 * Variabile in cui verranno sommati tutti i termini in x
		 */
		double b = 0;

		/*
		 * Variabile in cui verranno sommati tutti i termini noti
		 */
		double c = 0;
		
		
		/*
		 * prendo l'equazione che c'e' dopo del simbolo uguale e la divido dalla prima equazione
		 */
		ArrayList<String> listEqDestra = new ArrayList<>();
		ArrayList<String> listEqSinistra = new ArrayList<>();
		
		int indexEqual = listEq.indexOf("=");
		listEqSinistra.addAll(listEq.subList(0, indexEqual));
		listEqDestra.addAll(listEq.subList(indexEqual+1, listEq.size()));		
		
		
		/*
		 * inverto i segni dell'equazione a destra del simbolo uguale e la unisco con l'equazione a sinistra del simbolo uguale
		 */
		for(String s : listEqDestra)
			listEqSinistra.add(invertiSegni(s));
		listEqSinistra.add("= 0");
		
		String tmp = listEqSinistra.toString().replace("[", "");
		tmp = tmp.replace("]", "");
		tmp = tmp.replace(",", "");
		result.add("Inverto i segni dell'equzione destra e la unisco con quella di sinistra: \n" + tmp);
		
		/*
		 * effettuo la somma di tutti i termini in x e tutti i termini noti
		 */
		
		listEqSinistra.remove("= 0");
		
		/*
		 * stringa in cui salvo tutti i termini in x^2
		 */
		String listaTerminiA = "";
		
		/*
		 * stringa in cui salvo tutti i termini x
		 */
		String listaTerminiB = "";
		
		/*
		 * stringa in cui salvo tutti i termini noti
		 */
		String listaTerminiC = "";
		
		/*
		 * trovo e sommo tutti i termini uguali 
		 */
		for(String s : listEqSinistra)
		{
			if(s.contains("x^2"))
			{
				listaTerminiA = listaTerminiA + s;
				s = s.replace("x^2", "");
				if (s.length() == 1)
					s += 1;
				a += Double.parseDouble(s);
			}
			else if (s.contains("x"))
			{
				listaTerminiB = listaTerminiB + s;
				s = s.replace("x", "");
				if (s.length() == 1)
					s += 1;
				b += Double.parseDouble(s);
			}
			else 
			{
				listaTerminiC = listaTerminiC + s;
//				s = s.replace("c", "");
				if (s.length() == 1)
					s += 1;
				c += Double.parseDouble(s);
			}
		}
		
		
		if((a+"").charAt(0) != ('+' | '-'))
			 listaTerminiA = "+"+a;
		if((b+"").charAt(0) != ('+' | '-'))
			listaTerminiB = "+"+b;
		if((c+"").charAt(0) != ('+' | '-'))
			listaTerminiC = "+"+c;
		
		result.add("Termini: \n" 
				+" -	X^2 : " + listaTerminiA +" \n" 
				+ " -	X : " + listaTerminiB +" \n" 
				+ " -	TERMINE NOTO : " + listaTerminiC);

		
		result.add("equazione di secondo grado: \n" + listaTerminiA + "x^2 " + listaTerminiB+"x " + listaTerminiC+" " + " = 0");
		
		
		/*
		 * Aggiugiamo alla lista di supporto i termini opportuni per poter rappresentare l'equazione sul grafico
		 */
		equationsForGraph.add(a);
		equationsForGraph.add(b);
		equationsForGraph.add(c);
		

	
		String res = "";
		
		/*
		 * Delta
		 */
		double d;
		double s1, s2;
		
		/*
		 * Calcolo del delta
		 */
		d = b * b - 4 * a * c;
		
		result.add("Delta= " + d);
		
		/*
		 * in base al delta riuscuamo a capire in che situazione siamo
		 */
		if (d == 0)
		{
			s1 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
			s2 = s1;
			res = "Soluzioni coincidenti: " + s1;
		}
		
		if (d > 0)
		{
			s1 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
			s2 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
			res = "Soluzioni reali: " + s1 + " " + s2;
		}
		
		if (d < 0)
		{
			res = "Soluzioni complesse";
		}
		
		result.add("risultato: \n" + res);
		
		return result;
					
	}
	

	@Override
	public ArrayList<Double> getEquationsForGraph()
	{
		return equationsForGraph;
	}
}
