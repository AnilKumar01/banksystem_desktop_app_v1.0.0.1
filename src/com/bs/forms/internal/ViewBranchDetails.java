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
public class ViewBranchDetails extends JInternalFrame implements ActionListener{
	/* ***************************************************************************/
	JPanel contentPanel, dummyPanel, tempPanel;
	JTextField branchCode,accountType,branchLocation,ifscCode,telephoneNumber,faxNumber;
	JTextArea branchAddress;
	JButton getDetails,close;
	/* ***************************************************************************/
	public ViewBranchDetails(){
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
		ifscCode.setEditable(false);
		dummyPanel.add(ifscCode);
		
		dummyPanel.add(new JLabel("Branch Location"));
		branchLocation=new JTextField(15);
		branchLocation.setEditable(false);
		dummyPanel.add(branchLocation);
		
		dummyPanel.add(new JLabel("Branch Address"));
		branchAddress=new JTextArea();
		branchAddress.setEditable(false);
		dummyPanel.add(branchAddress);
		
		dummyPanel.add(new JLabel("Telephone Number"));
		telephoneNumber=new JTextField(15);
		telephoneNumber.setEditable(false);
		dummyPanel.add(telephoneNumber);
		
		dummyPanel.add(new JLabel("FAX Number"));
		faxNumber=new JTextField(15);
		faxNumber.setEditable(false);
		dummyPanel.add(faxNumber);
		
		
		contentPanel.add(dummyPanel, BorderLayout.CENTER);
		
		dummyPanel=new JPanel();
			
		close=new JButton("Close");
		
		dummyPanel.add(close);
		close.addActionListener(this);
		contentPanel.add(dummyPanel, BorderLayout.SOUTH);
		
		/* ***********************************************************************/
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==getDetails){
			AdminOperations ao=new AdminActions();
			if(!(Pattern.matches("\\d{5}", branchCode.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nBranch Code must be number of 5 digits.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				BranchList branchDet=ao.viewBranchDetails(branchCode.getText());
				if(branchDet.getBranchCode()!=null){
					ifscCode.setText(branchDet.getIfscCode());
					branchLocation.setText(branchDet.getLocation());
					branchAddress.setText(branchDet.getAddress());
					telephoneNumber.setText(branchDet.getTelephone());
					faxNumber.setText(branchDet.getFaxNumber());
				}
				else{
					JOptionPane.showMessageDialog(this, "Branch Code not found.", "Error Message", JOptionPane.ERROR_MESSAGE);	
				}
			}
		}
		if(e.getSource()==close){
			this.dispose();
		}
	}
}