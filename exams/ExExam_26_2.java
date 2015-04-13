package exams;

import stack.ArrayStack;
import stack.Stack;


/*
 * La funzione prende in input una stringa s e restituisce true se e solo se 
 * s è ben formata. Una stringa è ben formata se è costituita da sequenze di 0 
 * o più caratteri (diversi dalle parentesi angolari ‘<’ e ‘>’) racchiusi tra coppie 
 * di parentesi angolari. Le sequenze possono essere annidate all’interno di altre 
 * sequenze e i caratteri di una stessa sequenza devono essere tutti uguali.  
 * Ad esempio la stringa “<aaa<bbb>a<ddd> a><ee>” è ben formata in quanto 
 * contiene le quattro sequenze <aaaaa>, <bbb>, <ddd>,<ee>. La stringa 
 * “<aaa<bbbb>c<dd>a>” non è ben formata perché contiene le sequenze <aaaca>, <bbbb>
 * e <dd>, la prima delle quali non è formata da caratteri identici. Anche la stringa
 * “<aaa>><ff>a<bbb>a>” non è ben formata perché c’è una parentesi angolare ‘>’ a cui
 * non corrisponde alcuna parentesi angolare ‘<’. La stringa vuota e la stringa “< >” sono ben formate.
 */
public class ExExam_26_2 {
	public static boolean checkString(String s){
		char[] string;
		Stack<Character> stack = new ArrayStack<Character>();
		if(s.isEmpty())
			return true;
		string = s.toCharArray();
		for(int i = 0; i<string.length; i++){
			if(string[i] == '<' || string[i] != '>')
				stack.push(string[i]);
			else {
				if(stack.isEmpty()) 
					return false;
				char first = stack.pop();
				if(first != '<'){
					try{
						char current = stack.pop();
						while(current != '<'){
							if(current !=first) 
								return false;
							current = stack.pop();
						}
					}catch(Exception e){ return false;}
				}
			}
		}
		if(!stack.isEmpty())
			return false;
		
		return true;
	}
	
	
	public static void main(String[] args){
		String s = "<aaa<bbb>a<ddd>a><ee>";
		System.out.println(checkString(s));
		s = "<aaa<bbbb>c<dd>a>";
		System.out.println(checkString(s));
		s = "aaa<bbbb>c<dd>a>";
		System.out.println(checkString(s));
		s = "<aaa<bbbb>c<dd>a";
		System.out.println(checkString(s));

	}
}
