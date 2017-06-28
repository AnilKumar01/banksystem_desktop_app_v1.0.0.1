package com.bs.forms.internal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bs.actions.AdminActions;
import com.bs.bankrelated.bean.BranchList;
import com.bs.operations.AdminOperations;

@SuppressWarnings("serial")
public class Update_Branch_Details extends JInternalFrame implements ActionListener{
	/* ***************************************************************************/
	JPanel contentPanel, dummyPanel, tempPanel;
	JTextField branchCode,accountType,branchLocation,ifscCode,telephoneNumber,faxNumber;
	JTextArea branchAddress;
	JButton getDetails,cancel,updateDetails;
	/* ***************************************************************************/
	public Update_Branch_Details(){
		super("View Branch Details                            ");
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));		
		this.closable=true;
		this.resizable=true;
		this.maximizable=true;
		/* ***********************************************************************/
		contentPanel=new JPanel();
		contentPanel.setLayout(new BorderLayout());
		this.add(contentPanel);
		
		dummyPanel=new JPanel();
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Branch Code"));
		dummyPanel.add(tempPanel);
		
		branchCode=new JTextField(15);
		branchCode.setBorder(BorderFactory.createBevelBorder(1));
		tempPanel.add(branchCode);
		getDetails=new JButton("Get Details");
		getDetails.addActionListener(this);
		tempPanel.add(getDetails);
		
		contentPanel.add(dummyPanel, BorderLayout.NORTH);
		
		dummyPanel=new JPanel();
		dummyPanel.setLayout(new GridLayout(8,2, 0,5));
		
		dummyPanel.add(new JLabel("IFSC Code"));
		ifscCode=new JTextField(15);
		dummyPanel.add(ifscCode);
		
		dummyPanel.add(new JLabel("Branch Location"));
		branchLocation=new JTextField(15);
		dummyPanel.add(branchLocation);
		
		dummyPanel.add(new JLabel("Branch Address"));
		branchAddress=new JTextArea();
		dummyPanel.add(branchAddress);
		
		dummyPanel.add(new JLabel("Telephone Number"));
		telephoneNumber=new JTextField(15);
		dummyPanel.add(telephoneNumber);
		
		dummyPanel.add(new JLabel("FAX Number"));
		faxNumber=new JTextField(15);
		dummyPanel.add(faxNumber);
		
		
		contentPanel.add(dummyPanel, BorderLayout.CENTER);
		
		dummyPanel=new JPanel();
		updateDetails=new JButton("Update Branch Details");
		updateDetails.setEnabled(false);
		updateDetails.addActionListener(this);
		dummyPanel.add(updateDetails);
		
		cancel=new JButton("Cancel");
		
		cancel.addActionListener(this);
		dummyPanel.add(cancel);	
		contentPanel.add(dummyPanel, BorderLayout.SOUTH);
		
		/* ***********************************************************************/
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==getDetails){
			if(!(Pattern.matches("\\d{5}", branchCode.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nBranch Code must be number of 5 digits.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				AdminOperations ao=new AdminActions();
				BranchList branchDet=ao.viewBranchDetails(branchCode.getText());
				if(branchDet.getBranchCode()!=null){
					ifscCode.setText(branchDet.getIfscCode());
					branchLocation.setText(branchDet.getLocation());
					branchAddress.setText(branchDet.getAddress());
					telephoneNumber.setText(branchDet.getTelephone());
					faxNumber.setText(branchDet.getFaxNumber());
					updateDetails.setEnabled(true);
				}
				else{
					JOptionPane.showMessageDialog(this, "Branch Code not found.", "Error Message", JOptionPane.ERROR_MESSAGE);	
				}
			}
		}
		if(e.getSource()==updateDetails){
			boolean status = false;
			BranchList bl=new BranchList();
			if(!(branchCode.getText().length()==5 && Pattern.matches("\\d*", branchCode.getText())))
			{
				JOptionPane.showMessageDialog(this, "Required!!\nBranch Code must be number of 5 digits.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(!(ifscCode.getText().length()==11 && Pattern.matches("^[A-Z]{3}\\d{8}", ifscCode.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nIFSC Code must be alphanumeric with 11 characters.\nEx.ABC12345678 ", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(!(Pattern.matches("^[A-Za-z ]{1,50}", branchLocation.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nBranch location must be alphabets.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(branchAddress.getText().length()==0){
					JOptionPane.showMessageDialog(this, "Required!!\nAddress field reqired.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(!Pattern.matches("\\d{8,12}", telephoneNumber.getText())){
				JOptionPane.showMessageDialog(this, "Required!!\nTelephone number must be between 8 to 12 digits.", "Error Message", JOptionPane.ERROR_MESSAGE);	
			}else if(!(Pattern.matches("\\d{8,12}", faxNumber.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nFAX number must be between 8 to 12 digits.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				status=true;
			}
			if(status){
				updateBranchDetails(bl);
			}
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}
	private void updateBranchDetails(BranchList bl) {
		AdminOperations ao=new AdminActions();
		bl.setBranchCode(branchCode.getText());
		bl.setIfscCode(ifscCode.getText());
		bl.setLocation(branchLocation.getText());
		bl.setAddress(branchAddress.getText());
		bl.setTelephone(telephoneNumber.getText());
		bl.setFaxNumber(faxNumber.getText());
		if(ao.updateBranchDetails(bl)){
			int opt =JOptionPane.showConfirmDialog(this, "User Updated successfully.\n\nWould you like to Continue ?", "Message", JOptionPane.YES_NO_OPTION);
			if(opt==1)
				this.dispose();
			else{
				branchCode.setText(null);
				ifscCode.setText(null);
				branchLocation.setText(null);
				branchAddress.setText(null);
				telephoneNumber.setText(null);
				faxNumber.setText(null);
				updateDetails.setEnabled(false);
			}
		}
	}
}