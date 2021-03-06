package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	List<String> dizionario = new LinkedList<String>();
	
	
	public void loadDictionary(String language) {
		if(language.compareTo("Italian")==0)
		{
			try {
				FileReader fr= new FileReader("rsc/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine()) != null) {
					dizionario.add(word);
				}
				br.close();
			}catch (IOException e){
				System.out.println("Errore nella lettura del file");
			}
		}
		else if(language.compareTo("English")==0) {
			try {
				FileReader fr= new FileReader("rsc/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while((word = br.readLine()) != null) {
					dizionario.add(word);
				}
				br.close();
			}catch (IOException e){
				System.out.println("Errore nella lettura del file");
			}
		}
		
	}
	
	
	public List<RichWord> SpellCheckText(List<String> inputTextList){
		List<RichWord> parole = new LinkedList<RichWord>();
		
		for(String ptemp:inputTextList) {
			if(dizionario.contains(ptemp)) {
				parole.add(new RichWord(ptemp,true));
			}
			else
				parole.add(new RichWord(ptemp,false));
		}
		return parole;
		
	}
}
