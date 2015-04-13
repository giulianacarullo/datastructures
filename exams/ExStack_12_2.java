package exams;

import stack.ArrayStack;
import stack.Stack;

/*
 * La funzione prende in input un carattere x e una stringa s formata da lettere 
 * dell’alfabeto racchiuse tra coppie di parentesi tonde. Si assuma che le lettere
 * che compaiono in s siano a due a due distinte. Se l’espressione contenuta in s 
 * è ben parentesizzata e se il carattere x compare in s allora la funzione 
 * restituisce in output il livello di annidamento di x. Se x non compare in s 
 * o se s non è ben parentesizzata allora la funzione restituisce -1. Ad esempio, 
 * se s=”d(f(b)((r)))a(w)” e x=’a’ allora la funzione resituisce 0; 
 * se invece x= ‘r’ allora la funzione restituisce 3.
 * La funzione deve usare come unica struttura dati ausiliaria uno Stack.
 */

public class ExStack_12_2 {

	public static int level(char x, String s){
		int level = 0;
		int founded = -1;
		char[] array = s.toCharArray();
		Stack<Character> stack = new ArrayStack<Character>();
		for(int i = 0; i<array.length; i++){
			if(array[i]== '('){
				stack.push(array[i]);
				level++;
			}
			else if(array[i] == ')'){
				if(stack.isEmpty())
					return -1;
				stack.pop();
				level--;
			}
			else{
				if(array[i] == x && founded == -1) //in caso di più lettere uguali restituisco la prima
					founded = level;
			}
		}
		if(!stack.isEmpty() || founded == -1)
			return -1; 
		return founded;
	}
	
	public static void main(String[] args){
		String s1="d(f(b)((r)))a(w)";
		System.out.println(level('a', s1));
		System.out.println(level('r', s1));
		System.out.println(level('y', s1));
		s1 = s1+'(';
		System.out.println(level('r', s1));
		

	}
	
	
}
