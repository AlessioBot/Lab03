package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SpellCheckerController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField txtWord;

    @FXML
    private Button btnCheck;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label labelErrors;

    @FXML
    private Button btnClear;

    @FXML
    private Label labelTime;

    @FXML
    void choose(ActionEvent event) {
    	String language = comboBox.getValue();
    	model.loadDictionary(language);
    	
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	double start = System.nanoTime();
    	int k=0;
    	String frase = txtWord.getText();
    	frase.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	String[] parole = frase.split(" ");
    	List<String> paroleLista = new LinkedList<String>();
    	for(int i=0; i<parole.length;i++) {
    		paroleLista.add(parole[i].toLowerCase());
    	}
    	List<RichWord> checkParole= model.SpellCheckText(paroleLista);
    	String s= "";
    	for(RichWord r:checkParole) {
    		if(r.isCorretta()==false) {
    			s += r.getParola()+"\n";
    			k++;
    		}
    	}
    	double stop = System.nanoTime();
    	double res = stop-start;
    	txtResult.appendText(s);
    	labelErrors.setText("The text contains "+k+" errors");
    	labelTime.setText("Spell Check completed in "+res+" seconds");
    	
    }

    @FXML
    void doClearText(ActionEvent event) {
    	txtWord.clear();
    	txtResult.clear();
    	labelErrors.setText("");
    	labelTime.setText("");

    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWord != null : "fx:id=\"txtWord\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert labelErrors != null : "fx:id=\"labelErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert labelTime != null : "fx:id=\"labelTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        comboBox.getItems().setAll("English","Italian");
    }

	public void setModel(Dictionary model) {
		this.model = model;
		
	}
}
