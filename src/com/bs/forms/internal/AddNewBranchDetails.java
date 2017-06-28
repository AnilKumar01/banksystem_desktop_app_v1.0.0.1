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
import com.bs.bankrelated.bean.BranchList;
import com.bs.operations.AdminOperations;

@SuppressWarnings("serial")
public class AddNewBranchDetails extends JInternalFrame implements ActionListener{
	/* ************************************************************************/
	JPanel contentPanel;
	JPanel dummyPanel, tempPanel;
	JTextField branchCode, ifscCode, location, telNo, faxNo;
	JTextArea address;
	JScrollPane pane;
	JButton addBranch, cancel;
	boolean flag=true, status=false;
	/* ************************************************************************/
	
	public AddNewBranchDetails(){
		super("ADD NEW BRANCH DETAILS                  ");
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable=true;
		this.resizable=true;
		this.maximizable=true;
		/* ********************************************************************/
		addBranchForm();
		/* ********************************************************************/
		this.setVisible(true);
	}

	private void addBranchForm() {
		contentPanel=new JPanel();
		contentPanel.setLayout(new GridLayout(4, 1));
		//BRANCH CODE AND IFSC CODE
		//BRANCH CODE
		dummyPanel=new JPanel();
		
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Branch Code"));
		branchCode=new JTextField(15);
		branchCode.setBorder(BorderFactory.createBevelBorder(1));
		tempPanel.add(branchCode);
		dummyPanel.add(tempPanel);
		
		//IFSC CODE
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("IFSC CODE"));
		ifscCode=new JTextField(15);
		ifscCode.setBorder(BorderFactory.createBevelBorder(1));
		tempPanel.add(ifscCode);
		dummyPanel.add(tempPanel);
		
		contentPanel.add(dummyPanel);
		//LOCATION AND ADDRESS
		//LOCATION
		dummyPanel=new JPanel();
		
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Branch Location"));
		location=new JTextField(15);
		location.setBorder(BorderFactory.createBevelBorder(1));
		tempPanel.add(location);
		dummyPanel.add(tempPanel);
		
		//ADDRESS
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Branch Address"));
		address=new JTextArea(2,15);
		address.setWrapStyleWord(true);
		address.setLineWrap(true);
		//address.setBorder(BorderFactory.createBevelBorder(1));
		pane=new JScrollPane();
		pane.setBorder(BorderFactory.createBevelBorder(1));
		pane.getViewport().add(address);
		tempPanel.add(pane);
		dummyPanel.add(tempPanel);
		
		contentPanel.add(dummyPanel);
		
		//TELEPHONE NUMBER AND FAX NUMBER
		//TELEPHONE NUMBER
		dummyPanel=new JPanel();
		
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Telephone Number"));
		telNo=new JTextField(15);
		telNo.setBorder(BorderFactory.createBevelBorder(1));
		tempPanel.add(telNo);
		dummyPanel.add(tempPanel);
		
		//FAX NUMBER
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("FAX Number"));
		faxNo=new JTextField(15);
		faxNo.setBorder(BorderFactory.createBevelBorder(1));
		tempPanel.add(faxNo);
		dummyPanel.add(tempPanel);
		contentPanel.add(dummyPanel);
		
		//BUTTONS
		dummyPanel=new JPanel();
		addBranch=new JButton("Save");
		
		addBranch.addActionListener(this);
		dummyPanel.add(addBranch);
		cancel=new JButton("Cancel");
		cancel.addActionListener(this);
		dummyPanel.add(cancel);
		contentPanel.add(dummyPanel);
		
		
		this.add(contentPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addBranch){
			status=false;
			BranchList bl=new BranchList();
			if(!(branchCode.getText().length()==5 && Pattern.matches("\\d*", branchCode.getText())))
			{
				JOptionPane.showMessageDialog(this, "Required!!\nBranch Code must be number of 5 digits.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(!(ifscCode.getText().length()==11 && Pattern.matches("^[A-Z]{3}\\d{8}", ifscCode.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nIFSC Code must be alphanumeric with 11 characters.\nEx.ABC12345678 ", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(!(Pattern.matches("^[A-Za-z ]{1,50}", location.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nBranch location must be alphabets.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(address.getText().length()==0){
					JOptionPane.showMessageDialog(this, "Required!!\nAddress field reqired.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(!Pattern.matches("\\d{8,12}", telNo.getText())){
				JOptionPane.showMessageDialog(this, "Required!!\nTelephone number must be between 8 to 12 digits.", "Error Message", JOptionPane.ERROR_MESSAGE);	
			}else if(!(Pattern.matches("\\d{8,12}", faxNo.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nFAX number must be between 8 to 12 digits.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				status=true;
			}
			
			if(status){
				addBranchDetails(bl);
			}
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}

	private void addBranchDetails(BranchList bl) {
		bl.setBranchCode(branchCode.getText());
		bl.setIfscCode(ifscCode.getText());
		bl.setLocation(location.getText());
		bl.setAddress(address.getText());
		bl.setTelephone(telNo.getText());
		bl.setFaxNumber(faxNo.getText());
		AdminOperations ao=new AdminActions();
		if(ao.addNewBranchDetails(bl)){
			int opt=JOptionPane.showConfirmDialog(this, "Branch record added successfuly.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
			if(opt==1){
				this.dispose();
			}else{
				branchCode.setText(null);
				ifscCode.setText(null);
				location.setText(null);
				address.setText(null);
				telNo.setText(null);
				faxNo.setText(null);
			}
		}else{
			JOptionPane.showMessageDialog(this, "Branch could not saved. Duplicate branch code.\n\n", "Error Message", JOptionPane.ERROR_MESSAGE);			
		}
	}

	
}
