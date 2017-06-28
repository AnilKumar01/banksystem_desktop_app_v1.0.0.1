/* *************************************************************************
 * Project: Bank System
 * Purpose: To make an account opening form.
 * Start date: Dec 10, 2014
 * Authors: Anil Kumar (DAC-11)
 * 			Shashi Bhushan Verma (DAC-76)
 * End Date:  
 * ***************************************************************************/

package com.bs.forms.internal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bs.actions.MasterAction;
import com.bs.operations.MasterOperations;

@SuppressWarnings("serial")
public class old_AddNewEmployeeForm extends JInternalFrame{
	/* ***************************************************************************/
	//PAGE ONE ELEMENTS
	/* ***************************************************************************/
	Map<Integer, String>deptList=new HashMap<Integer, String>();
	List<String> designList=new ArrayList<String>();
	MasterOperations ma=new MasterAction();
	
	JTabbedPane empFormTabs;
	JPanel pageOnePanel, empJobDetailPanel, empNamePanel, empDobGenECon, empAddressPanel;
	JLabel label;
	JTextField empId, empEConNo, empContact, empEmail, branchCode;
	JTextField empFirstName, empMidName, empLastName;
	JComboBox<String> empDept, empJob, empDobDD, empDobMM, empDobYYYY;
	JComboBox<String> empJoinDD, empJoinMM, empJoinYYYY;
	JPanel empDobPanel, empGenPanel, empConPanel;
	JRadioButton maleR, femaleR, transR;
	JRadioButton doctrate, postGraduate, graduate, others;
	ButtonGroup genGrp, eduGrp;
	JTextArea empPermanentAddress, empCurrentAddress;
	JPanel dummyPanel, dummyPanel2;
	JPanel departmentalInfoPanel;
	JScrollPane pane, pane1;
	JButton pageOneCancelBtn, pageOneNextBtn;
	
	/* ****************************************************************************/
	//FORM PAGE TWO ELEMENTS
	JPanel pageTwoPanel;
	JPanel empPhotoPanel, empSignPanel, empPhotoSignPanel, parentalInfoPanel, dependentDetailsPanel;
	JPanel empPhoto, empSign;
	JButton browsePhoto, browseSign;
	JButton pageTwoBackBtn, pageTwoNextBtn;
	JTextField dependent1, dependent2, dependent3, dependent4, dependent5;
	JComboBox<String> relation1, relation2, relation3, relation4, relation5;
	JTextField depAge1, depAge2, depAge3, depAge4, depAge5;
	String items[]={"Mother", "Father", "Daughter", "Son", "Wife", "Hasband", "Broder", "Sister"};
	JTextField empFatherName, empMotherName;
	JPanel tp1, tp2, tp3, tp4;
	/* ****************************************************************************/
	
	public old_AddNewEmployeeForm(){
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable=true;
		this.title="ADD EMPLOYEE FORM              [ Fill employee details ]";
		/* ***********************************************************************/		
		addEmployeePageOne();
		/* ***********************************************************************/
		addEmployeePageTwo();
		/* ***********************************************************************/
	//	this.setResizable(false);
		this.setVisible(true);
	}

	private void addEmployeePageTwo() {
		//ADD EMPLOYEE FORM PAGE TWO
		/* ************************************************************************/
		pageTwoPanel=new JPanel();
		pageTwoPanel.setBorder(BorderFactory.createBevelBorder(1));
		pageTwoPanel.setLayout(new GridLayout(3, 1));
		/* *************************************************************************/
		//EMPLOYEE PHOTOGRAPH AND SIGNATURE
		dummyPanel=new JPanel();
		dummyPanel.setLayout(new GridLayout(1, 2));
			empPhotoSignPanel=new JPanel();
			empPhotoSignPanel.setLayout(new GridLayout(1, 2, 10, 0));
			//EMPLOYEE PHOTOGRAPH
				empPhotoPanel=new JPanel();
				empPhotoPanel.setLayout(new BorderLayout());
					empPhoto=new JPanel();
					empPhoto.setBorder(BorderFactory.createBevelBorder(1));
					empPhoto.setBackground(Color.LIGHT_GRAY);					
				empPhotoPanel.add(empPhoto, BorderLayout.CENTER);
					browsePhoto=new JButton("Browse Phograph");
				empPhotoPanel.add(browsePhoto, BorderLayout.SOUTH);
					
			empPhotoSignPanel.add(empPhotoPanel);
			//EMPLOYEE SIGNATURE
				empSignPanel=new JPanel();
				empSignPanel.setLayout(new BorderLayout());
					empSign=new JPanel();
					empSign.setBorder(BorderFactory.createBevelBorder(1));
					empSign.setBackground(Color.LIGHT_GRAY);
				empSignPanel.add(empSign, BorderLayout.CENTER);
					browseSign=new JButton("Browse Signature");
				empSignPanel.add(browseSign, BorderLayout.SOUTH);
			empPhotoSignPanel.add(empSignPanel);
			empPhotoSignPanel.setBorder(BorderFactory.createTitledBorder("Upload photograph and signature"));
			dummyPanel.add(empPhotoSignPanel);
					
			dummyPanel2=new JPanel();
			dummyPanel.add(dummyPanel2);
		pageTwoPanel.add(dummyPanel);
		/* ************************************************************************/
		//PARENTAL INFORMATIONS
		dummyPanel =new JPanel();
		dummyPanel.setLayout(new GridLayout(1,2));
		
		parentalInfoPanel=new JPanel();
		parentalInfoPanel.setBorder(BorderFactory.createTitledBorder("Parental Informations"));
		parentalInfoPanel.setLayout(new GridLayout(2, 1));
			dummyPanel2=new JPanel();
			//FATHER NAME
			dummyPanel2.setBorder(BorderFactory.createTitledBorder("Father's Name"));
			empFatherName=new JTextField(20);
			empFatherName.setBorder(BorderFactory.createBevelBorder(1));
			dummyPanel2.add(empFatherName);
			parentalInfoPanel.add(dummyPanel2);
		
			//MOTHER NAME
			dummyPanel2=new JPanel();
			dummyPanel2.setBorder(BorderFactory.createTitledBorder("Mother's Name"));
			empMotherName=new JTextField(20);
			empMotherName.setBorder(BorderFactory.createBevelBorder(1));
			dummyPanel2.add(empMotherName);
			parentalInfoPanel.add(dummyPanel2);
			
		dummyPanel.add(parentalInfoPanel);
		pageTwoPanel.add(dummyPanel);
		/* ***********************************************************************/
		//DEPENDENT DETAILS
		dummyPanel2=new JPanel(); 
		dummyPanel2.setLayout(new GridLayout(1,0,5, 0));
		dummyPanel2.setBorder(BorderFactory.createTitledBorder("Dependent Details"));
		tp1=new JPanel();
		tp1.setBorder(BorderFactory.createTitledBorder("Dependent Name"));
		tp1.setLayout(new GridLayout(5, 1, 0, 7));
		dummyPanel2.add(tp1);
		
		tp2=new JPanel();
		tp2.setLayout(new GridLayout(1, 2));
		tp3=new JPanel();
		tp3.setLayout(new GridLayout(5, 1, 0, 5));
		tp3.setBorder(BorderFactory.createTitledBorder("Age"));
		tp2.add(tp3);
		tp4=new JPanel();
		tp4.setLayout(new GridLayout(5, 1, 0, 5));
		tp4.setBorder(BorderFactory.createTitledBorder("Relation"));
		tp2.add(tp4);
		dummyPanel2.add(tp2);
				
		dependent1=new JTextField(8);
		dependent1.setBorder(BorderFactory.createBevelBorder(1));
		tp1.add(dependent1);
		depAge1=new JTextField(2);
		depAge1.setBorder(BorderFactory.createBevelBorder(1));
		tp3.add(depAge1);
		relation1=new JComboBox<String>(items);
		tp4.add(relation1);
		
		dependent2=new JTextField(8);
		dependent2.setBorder(BorderFactory.createBevelBorder(1));
		tp1.add(dependent2);
		depAge2=new JTextField(2);
		depAge2.setBorder(BorderFactory.createBevelBorder(1));
		tp3.add(depAge2);
		relation2=new JComboBox<String>(items);
		tp4.add(relation2);
		
		dependent3=new JTextField(8);
		dependent3.setBorder(BorderFactory.createBevelBorder(1));
		tp1.add(dependent3);
		depAge3=new JTextField(2);
		depAge3.setBorder(BorderFactory.createBevelBorder(1));
		tp3.add(depAge3);
		relation3=new JComboBox<String>(items);
		tp4.add(relation3);
		
		dependent4=new JTextField(8);
		dependent4.setBorder(BorderFactory.createBevelBorder(1));
		tp1.add(dependent4);
		depAge4=new JTextField(2);
		depAge4.setBorder(BorderFactory.createBevelBorder(1));
		tp3.add(depAge4);
		relation4=new JComboBox<String>(items);
		tp4.add(relation4);
		
		dependent5=new JTextField(8);
		dependent5.setBorder(BorderFactory.createBevelBorder(1));
		tp1.add(dependent5);
		depAge5=new JTextField(2);
		depAge5.setBorder(BorderFactory.createBevelBorder(1));
		tp3.add(depAge5);
		relation5=new JComboBox<String>(items);
		tp4.add(relation5);
		
		dummyPanel.add(dummyPanel2);
		pageTwoPanel.add(dummyPanel);
		/* ************************************************************************/
		dummyPanel=new JPanel();
			pageTwoBackBtn=new JButton("Back");
			dummyPanel.add(pageTwoBackBtn);
			pageTwoNextBtn=new JButton("Next");
			dummyPanel.add(pageTwoNextBtn);
		pageTwoPanel.add(dummyPanel);
		
		/* ************************************************************************/
		empFormTabs=new JTabbedPane();
			empFormTabs.addTab("PAGE-1", pageOnePanel);
			empFormTabs.addTab("PAGE-2", pageTwoPanel);
		this.add(empFormTabs);
	}

	private void addEmployeePageOne() {
		pageOnePanel=new JPanel();
		pageOnePanel.setBorder(BorderFactory.createBevelBorder(1));
		pageOnePanel.setLayout(new GridLayout(6,1));
	/* *******************************************************************/
		//EMPLOYEE JOB DETAILS
		empJobDetailPanel=new JPanel();
		empJobDetailPanel.setBorder(BorderFactory.createTitledBorder("Departmental Information"));
			
		//DEPARTMENT OF EMPLOYEE
		empDept=new JComboBox<String>();
		empDept.addItem("Select Department");
		if(ma.departmentList(deptList)){
			for(Integer deptNo: deptList.keySet()){
				empDept.addItem(deptList.get(deptNo));
			}
		}

		empJobDetailPanel.add(empDept);
		//DESIGNATION OF EMPLOYEE
		empJob=new JComboBox<String>();
		empJob.addItem("Select Designation");
		if(ma.designationList(designList)){
			for(String design: designList){
				empJob.addItem(design);						
			}
		}
		empJobDetailPanel.add(empJob);
		//EMPLOYEE JOINING DATE
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Date of Joining"));
			//DATE
			empJoinDD=new JComboBox<String>();
			empJoinDD.addItem("DD");
			for(int i=1;i<=31;i++)
				empJoinDD.addItem(""+i);
			dummyPanel.add(empJoinDD);
			//MONTH
			empJoinMM=new JComboBox<String>();
			empJoinMM.addItem("MM");
			for(int i=1;i<=12;i++)
				empJoinMM.addItem(""+i);
			dummyPanel.add(empJoinMM);
			//YEAR
			empJoinYYYY=new JComboBox<String>();
			empJoinYYYY.addItem("YYYY");
			for(int i=1947;i<=2014;i++)
				empJoinYYYY.addItem(""+i);
			dummyPanel.add(empJoinYYYY);
		empJobDetailPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Branch Code"));
			branchCode=new JTextField(10);
			branchCode.setBorder(BorderFactory.createBevelBorder(1));
			dummyPanel.add(branchCode);
		empJobDetailPanel.add(dummyPanel);
		
				
		pageOnePanel.add(empJobDetailPanel);
			
		/* *********************************************************************/		
					
			
			//NAME OF THE EMPLOYEE
			empNamePanel=new JPanel();
				empNamePanel.setBorder(BorderFactory.createTitledBorder("Employee Name"));
				label=new JLabel("First Name");
				empNamePanel.add(label);
					empFirstName=new JTextField(15);
					empFirstName.setBorder(BorderFactory.createBevelBorder(1));
					empNamePanel.add(empFirstName);
					
				label=new JLabel("     Middle Name");
				empNamePanel.add(label);
					empMidName=new JTextField(15);
					empMidName.setBorder(BorderFactory.createBevelBorder(1));
					empNamePanel.add(empMidName);	
				
				label=new JLabel("     Last Name");
				empNamePanel.add(label);
					empLastName=new JTextField(15);
					empLastName.setBorder(BorderFactory.createBevelBorder(1));
					empNamePanel.add(empLastName);
					
			pageOnePanel.add(empNamePanel);
			/* *******************************************************************/
			
			//DATE OF BIRTH, GENDER, AND EMERGENCY CONTACT NUMBER
			empDobGenECon=new JPanel();
				empDobGenECon.setBorder(BorderFactory.createTitledBorder(""));
				//DATE OF BIRTH
				empDobPanel=new JPanel();
					empDobPanel.setBorder(BorderFactory.createTitledBorder("Date of Birth"));
				//DATE
				empDobDD=new JComboBox<String>();
					empDobDD.addItem("DD");
					for(int i=1;i<=31;i++)
						empDobDD.addItem(""+i);
					empDobPanel.add(empDobDD);
				//MONTH
				empDobMM=new JComboBox<String>();
				empDobMM.addItem("MM");
				for(int i=1;i<=12;i++)
					empDobMM.addItem(""+i);
				empDobPanel.add(empDobMM);
				//YEAR
				empDobYYYY=new JComboBox<String>();
				empDobYYYY.addItem("YYYY");
				for(int i=1947;i<=2014;i++)
					empDobYYYY.addItem(""+i);
				empDobPanel.add(empDobYYYY);
				
				empDobGenECon.add(empDobPanel);
				/* *************************************************************/
				//GENDER OF EMPLOYEE
				empGenPanel=new JPanel();
				empGenPanel.setBorder(BorderFactory.createTitledBorder("Gender"));
					maleR=new JRadioButton("Male");
					empGenPanel.add(maleR);
					femaleR=new JRadioButton("Female");
					empGenPanel.add(femaleR);
					transR=new JRadioButton("Transgender");
					empGenPanel.add(transR);
					genGrp=new ButtonGroup();
						genGrp.add(maleR);
						genGrp.add(femaleR);
						genGrp.add(transR);
				empDobGenECon.add(empGenPanel);
				/* **************************************************************/
				//EMERGENCY CONTACT NUMBER
				empConPanel=new JPanel();
				empConPanel.setBorder(BorderFactory.createTitledBorder("Em. Contact"));
					empEConNo=new JTextField(10);
					empEConNo.setBorder(BorderFactory.createBevelBorder(1));
					empConPanel.add(empEConNo);
				empDobGenECon.add(empConPanel);
				
				//CONTACT NUMBER
				empConPanel=new JPanel();
				empConPanel.setBorder(BorderFactory.createTitledBorder("Contact"));
					empContact=new JTextField(10);
					empContact.setBorder(BorderFactory.createBevelBorder(1));
					empConPanel.add(empContact);
				empDobGenECon.add(empConPanel);
			pageOnePanel.add(empDobGenECon);
		/* ***************************************************************/
			//EMPLOYEE ADDRESS INFORMATION
			empAddressPanel=new JPanel();
			dummyPanel=new JPanel();
			dummyPanel.setBorder(BorderFactory.createTitledBorder("Permanent Address"));
			empAddressPanel.add(dummyPanel);
				empPermanentAddress=new JTextArea(2,20);
				
				//empPermanentAddress.setBorder(BorderFactory.createBevelBorder(1));
				pane=new JScrollPane();
				pane.setBorder(BorderFactory.createBevelBorder(1));
				empPermanentAddress.setWrapStyleWord(true);
				empPermanentAddress.setLineWrap(true);
				pane.getViewport().add(empPermanentAddress);
				dummyPanel.add(pane);
			
			dummyPanel=new JPanel();
			dummyPanel.setBorder(BorderFactory.createTitledBorder("Current Address"));
				empCurrentAddress=new JTextArea(2,20);
				//empCurrentAddress.setBorder(BorderFactory.createBevelBorder(1));
				pane=new JScrollPane();
				pane.setBorder(BorderFactory.createBevelBorder(1));
				empCurrentAddress.setWrapStyleWord(true);
				empCurrentAddress.setLineWrap(true);
				pane.getViewport().add(empCurrentAddress);
				dummyPanel.add(pane);
			empAddressPanel.add(dummyPanel);
			
			dummyPanel=new JPanel();
			dummyPanel.setBorder(BorderFactory.createTitledBorder("E-mail"));
				empEmail=new JTextField(15);
				empEmail.setBorder(BorderFactory.createBevelBorder(1));
				dummyPanel.add(empEmail);
			empAddressPanel.add(dummyPanel);
			empAddressPanel.setBorder(BorderFactory.createTitledBorder(""));
		
		pageOnePanel.add(empAddressPanel);
		/* ************************************************************************/
		//EDUCATION QUALIFICATION
		dummyPanel2=new JPanel();
		dummyPanel2.setLayout(new GridLayout(1, 2));
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Education Qualifications"));
			doctrate=new JRadioButton("Doctrate");
			dummyPanel.add(doctrate);
			postGraduate=new JRadioButton("Post Graduate");
			dummyPanel.add(postGraduate);
			graduate=new JRadioButton("Graduate");
			dummyPanel.add(graduate);
			others=new JRadioButton("Others");
			dummyPanel.add(others);
			eduGrp=new ButtonGroup();
			eduGrp.add(doctrate);
			eduGrp.add(postGraduate);
			eduGrp.add(graduate);
			eduGrp.add(others);
		dummyPanel2.add(dummyPanel);
		dummyPanel=new JPanel();
		dummyPanel2.add(dummyPanel);
		
		pageOnePanel.add(dummyPanel2);
		/* ************************************************************************/
			dummyPanel=new JPanel();
			pageOneCancelBtn=new JButton("Cancel");
			dummyPanel.add(pageOneCancelBtn);
			pageOneNextBtn=new JButton("Next");
			dummyPanel.add(pageOneNextBtn);
		pageOnePanel.add(dummyPanel);
	}
}