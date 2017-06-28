/* *****************************************************************************
 * Project: Bank System
 * Purpose: Design an internal form for adding transaction type.
 * Author: Anil Kumar(dac11)
 * Filename: AddNewTransactionType.java
 * Version: 1.0
 * Start date: 27-Dec-2014
 * End date: 27-Dec-2014
 * *****************************************************************************/

package com.bs.forms.internal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bs.actions.AdminActions;
import com.bs.bankrelated.bean.TransactionType;
import com.bs.operations.AdminOperations;

@SuppressWarnings("serial")
public class AddNewTranactionType extends JInternalFrame implements ActionListener{
	/* ***************************************************************************/
	JPanel contentPanel, dummyPanel;
	JTextField tranactionType;
	JTextArea transDescription;
	JScrollPane pane;
	JButton save, cancel;
	/* ***************************************************************************/

	public AddNewTranactionType(){
		super("ADD NEW TRANSACTION TYPE                ");
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));		
		this.closable=true;
		this.resizable=true;
		this.maximizable=true;
		/* ***********************************************************************/
		addTransTypeForm();
		/* ***********************************************************************/
		this.setVisible(true);
	}

	private void addTransTypeForm() {
		contentPanel=new JPanel();
		contentPanel.setLayout(new GridLayout(3, 1));
		this.add(contentPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Transaction Type"));
		tranactionType=new JTextField(15);
		tranactionType.setBorder(BorderFactory.createBevelBorder(1));
		dummyPanel.add(tranactionType);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Transaction Descriptions"));
		transDescription=new JTextArea(2, 15);
		transDescription.setWrapStyleWord(true);
		transDescription.setLineWrap(true);
		pane=new JScrollPane();
		pane.getViewport().add(transDescription);
		pane.setBorder(BorderFactory.createBevelBorder(1));
		dummyPanel.add(pane);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		save=new JButton("Save");
		save.addActionListener(this);
		dummyPanel.add(save);
		cancel=new JButton("Cancel");
		cancel.addActionListener(this);
		dummyPanel.add(cancel);
		contentPanel.add(dummyPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource()==cancel){
			this.dispose();
		}
		if(e.getSource()==save){
			if(!(Pattern.matches("^[A-Za-z ]{1,50}", tranactionType.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nTransaction type must be alphabet characters.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				TransactionType tt=new TransactionType();
				tt.setTransactionType(tranactionType.getText());
				tt.setTransDescription(transDescription.getText());
				AdminOperations ao=new AdminActions();
				if(ao.addNewTransactionType(tt)){
					int opt=JOptionPane.showConfirmDialog(this, "Transaction Type is added successfuly.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
					if(opt==0){
						tranactionType.setText(null);
						transDescription.setText(null);
					}
					else {
						this.dispose();
					}
				}else{
					JOptionPane.showMessageDialog(this, "Transaction Type could not added. It is already exists.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
