/**
 * @(#)Debut.java
 * La classe chargé d'afficher le debut de l'application en mode graphique. 
 * @author SMIMITE Badr-Eddine
 * @version 1.00 
 */ 
package view.ihm;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;

public class frameExemple extends JFrame{
	
	/**
	int type;
	String S;
	Principal P;
	
	JLabel jLabel1 = new JLabel("choix du déroulement de la partie :");
	JRadioButton jRadioButton1 = new JRadioButton(" pas à pas.");
	JRadioButton jRadioButton2 = new JRadioButton(" par coup.");
	JRadioButton jRadioButton3 = new JRadioButton(" par partie.");
	ButtonGroup  buttonGroup1  = new ButtonGroup();
	JButton      btok          = new JButton("ok");
    JButton      btnQuit       = new JButton("annuler");
    
	
    public frameExemple() {
    	this.type = 1;
    	
    	//global
    	this.setSize(250,140);
    	this.move(400,200);
    	this.setTitle("::jeu de carte - bataille::");
    	this.getContentPane().setLayout(null);
    	
    	//les composants
    	// label 1
    	jLabel1.setBounds(new Rectangle(3, 4, 400, 20));
    	//les zones de choix
    	//choix 1
    	jRadioButton1.setSelected(true);
        jRadioButton1.setBounds(new Rectangle(4, 26, 150, 15));
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                radioButtons_itemStateChanged(e);
            }
        });
    	//choix 2
        jRadioButton2.setBounds(new Rectangle(4, 42, 150, 15));
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                radioButtons_itemStateChanged(e);
            }
        });
        //choix 3
    	jRadioButton3.setBounds(new Rectangle(4, 58, 150, 15));
        jRadioButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                radioButtons_itemStateChanged(e);
            }
        });
        //les bouttons
        //ok
        btok.setBounds(new Rectangle(1, 75, 98, 23));
        btok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Button_on_click(e);
            }
        });
        //annuler
        btnQuit.setBounds(new Rectangle(97, 75, 98, 23));
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Button_on_click(e);
            }
        });
        
    	//ajout des composants
    	this.getContentPane().add(jLabel1, null);
    	this.getContentPane().add(jRadioButton1,null);
    	this.getContentPane().add(jRadioButton2,null);
    	this.getContentPane().add(jRadioButton3,null);
    	this.buttonGroup1.add(this.jRadioButton1);
        this.buttonGroup1.add(this.jRadioButton2);
        this.buttonGroup1.add(this.jRadioButton3);
        this.getContentPane().add(this.btnQuit);
        this.getContentPane().add(this.btok);
        
    	
    	
    	
    	//gestion des événements sur la fenétre
    	this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                dispose();
            }
            public void windowClosed(WindowEvent evt) {
                
            }
        });
    }
    
   
   
   /**renvoie le choix de deroulement de la partie
    public int getType(){
    	return this.type;
    }
    
   
    
    
    /**gére les évenement sur les composants de la fenétre.
    void radioButtons_itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        if (source == jRadioButton1) this.type = 1;
        if (source == jRadioButton2) this.type = 2;
        if (source == jRadioButton3) this.type = 3;
    }
    
    //gestion des evenement sur les boutons
    void Button_on_click(ActionEvent e){
    	Object source = e.getSource();
    	if(source == btok){
    		
    		this.setVisible(false);   
    		this.dispose();
    		int R = JOptionPane.showConfirmDialog(null,"voulez vous charger une partie?");
    		if(R == 0){
    			S = JOptionPane.showInputDialog(null,"Entrez votre partie sous forme 11112222....(au min 20 chiffre 1-5)");
    			
    		}
    		this.P = new Principal(this.type, this.S);
    		this.P.setVisible(true);
    	}
    	if(source == btnQuit){
    		this.setVisible(false);   
    		this.dispose();
    	}
    }*/
    
    
    
    
}