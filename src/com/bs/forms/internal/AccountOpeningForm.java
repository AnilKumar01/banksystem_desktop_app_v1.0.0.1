/* *************************************************************************
 * Project: Bank System
 * Purpose: To make an account opening form.
 * Authors: Anil Kumar (DAC-11) and  Shashi Bhushan Verma (DAC-76)
 * Filename: AccountOpeningForm.java
 * Version: 1.0
 * Start date: Dec 1, 2014
 * End Date:  
 * ***************************************************************************/
package com.bs.forms.internal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.bs.actions.ClerkActions;
import com.bs.actions.MasterAction;
import com.bs.bankrelated.bean.Customer;
import com.bs.customerrelated.bean.AccountSummary;
import com.bs.customerrelated.bean.Nominee;
import com.bs.operations.ClerkOperations;



@SuppressWarnings("serial")
public class AccountOpeningForm extends JInternalFrame implements ActionListener {
	Customer customer=new Customer();
	Nominee nominee=new Nominee();
	String ACCOUNT_TYPE=null;
	String BRANCH_CODE=null;
	AccountSummary accountSummary;
	/* ******************************************************/
	//FIRST PAGE COMPONENTS
	/* *******************************************************/
	private Map<String, String>branchLocation=new HashMap<String, String>();
	private JRadioButton saving, current;
	private JRadioButton unmarried, married;
	private JRadioButton others, service, business, student, defence;
	private JRadioButton male, female, trans;
	private JRadioButton lowIncome, midIncome, highIncome;
	private JRadioButton phd, masters, bachlors, other;
	private JTextField fname, mname, lname;
	private JTextField email, mobNo, panNo;
	private JTextArea currentAddress, permanentAddress;
	private JComboBox<String> branch_list, date, month, year;
	
	private JButton cancel, next;

	private JPanel contentP, pageOneButtonP, emailP, mobileP, contact_quali_P;
	private JPanel accountTypeP, accountNoP,nameP, genderP, dobP, maritalP, addressP_1, addressP_2, qualificationsP, occupationP, incomeP, contactP, panP;
	private JPanel pan_gender_dob_marry_P, occu_income_P, accountP, current_permanet_address_P;
	private Border border, borderText, borderLine;
	private JScrollPane addrPane;
	private JLabel lnameL, fnameL, mnameL, emaill, mobl;
	private JTabbedPane tab;
	/* ***********************************************************************/
	
	/* ***********************************************************************/
	// SECOND PAGE COMPONENTS
	/* ***********************************************************************/
	private JPanel nominee_P, n_name_P, n_dob_P, n_rel_P, n_mob_P, n_addr_P, pageTwoButton_P;
	private JLabel n_fname,n_mname,n_lname;
	private JTextField n_fname_T,n_mname_T,n_lname_T, n_mob_T;
	private JTextArea n_address;
	private JComboBox<String>n_relationC, n_dd,n_mm,n_yyyy;
	private JPanel pageTwoP;
	private JPanel photo_P, photo_show_P, sign_P, sign_show_P;
	private JPanel n_dob_rel_mob_addr_P, photo_sign_P;
	private JButton browse_photo_B, browse_sign_B, cancel_P2, save_B;
	/* ***********************************************************************/
	
	/* ************************************************************************/
	// SUMMERY PAGE ONE COMPONENTS
	/* ************************************************************************/
	private JPanel summaryPage, dummyPanel;
	private JTextField accountNumber, accountType, branchCode, IFSCCode, branchLoc;
	private JTextArea custAddress, branchAddress;
	private JTextField branchTel, branchFAX, accountHolder, customerMobile;
	private JTextField accountOpenDate;
	private JButton summaryCloseBtn;
	/* ************************************************************************/
	
	public AccountOpeningForm() {
		
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable=true;
		this.title="ACCOUNT OPENING FORM              [ Fill applicant details ]";
		//FIRST PAGE
		formPageOne();
		//SECOND PAGE
		formPageTwo();
		//THIRD PAGE
		summeryPageOne();
		/* *************************/
		
		tab=new JTabbedPane();
	
		tab.addTab("PAGE-1", contentP);
		
		tab.addTab("PAGE-2", pageTwoP);
		tab.setEnabledAt(1, false);
		tab.addTab("PAGE-3", summaryPage);
		tab.setEnabledAt(2, false);
		
		this.add(tab);
		this.setVisible(true);
	}

	private void summeryPageOne(){
		summaryPage=new JPanel();
		summaryPage.setBorder(BorderFactory.createBevelBorder(1));
		summaryPage.setLayout(new GridLayout(7, 2, 20, 0));
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Account No"));
		accountNumber=new JTextField(15);
		accountNumber.setEditable(false);
		dummyPanel.add(accountNumber);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Account Type"));
		accountType=new JTextField(15);
		accountType.setEditable(false);
		dummyPanel.add(accountType);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Branch Code"));
		branchCode=new JTextField(15);
		branchCode.setEditable(false);
		dummyPanel.add(branchCode);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("IFSC Code"));
		IFSCCode=new JTextField(15);
		IFSCCode.setEditable(false);
		dummyPanel.add(IFSCCode);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Branch Location"));
		branchLoc=new JTextField(15);
		branchLoc.setEditable(false);
		dummyPanel.add(branchLoc);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Branch Address"));
		branchAddress=new JTextArea(3,15);
		branchAddress.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		branchAddress.setEditable(false);
		dummyPanel.add(branchAddress);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Telephone No"));
		branchTel=new JTextField(15);
		branchTel.setEditable(false);
		dummyPanel.add(branchTel);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("FAX No"));
		branchFAX=new JTextField(15);
		branchFAX.setEditable(false);
		dummyPanel.add(branchFAX);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Account Holder"));
		accountHolder=new JTextField(15);
		accountHolder.setEditable(false);
		dummyPanel.add(accountHolder);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Address"));
		custAddress=new JTextArea(3, 15);
		custAddress.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		custAddress.setEditable(false);
		dummyPanel.add(custAddress);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Mobile No"));
		customerMobile=new JTextField(15);
		customerMobile.setEditable(false);
		dummyPanel.add(customerMobile);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Account Open Date"));
		accountOpenDate=new JTextField(15);
		accountOpenDate.setEditable(false);
		dummyPanel.add(accountOpenDate);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		
		dummyPanel=new JPanel();
		summaryPage.add(dummyPanel);
		summaryCloseBtn=new JButton("Close");
		summaryCloseBtn.addActionListener(this);
		dummyPanel.add(summaryCloseBtn);
	}
	
	private void formPageTwo() {
		pageTwoP=new JPanel();
		pageTwoP.setLayout(new GridLayout(3,0));
		pageTwoP.setBorder(BorderFactory.createBevelBorder(1));
		borderText=BorderFactory.createBevelBorder(1);
		/* **************************Photograph and Signature**********************/
		photo_sign_P=new JPanel();
		photo_sign_P.setLayout(new GridLayout(0,4, 10 ,0));
		border=BorderFactory.createTitledBorder("Upload Photograph and Signature");
		photo_sign_P.setBorder(border);
		/* **********************************************************************/
		/* **********************Photo upload section****************************/
		photo_P=new JPanel();
		photo_P.setLayout(new BorderLayout());
			photo_show_P=new JPanel();
			border=BorderFactory.createBevelBorder(1);
			photo_show_P.setBorder(border);
			photo_show_P.setBackground(Color.LIGHT_GRAY);
			photo_P.add(photo_show_P, BorderLayout.CENTER);
				browse_photo_B=new JButton("Browse Photograph");
				browse_photo_B.addActionListener(this);
				photo_P.add(browse_photo_B, BorderLayout.SOUTH);
		
		photo_sign_P.add(photo_P);
		
		/* **********************Signature upload section**************************/
		sign_P=new JPanel();
		sign_P.setLayout(new BorderLayout());
		sign_P.setBackground(Color.WHITE);
			
			sign_show_P=new JPanel();
			sign_show_P.setBorder(border);
			sign_show_P.setBackground(Color.LIGHT_GRAY);
			sign_P.add(sign_show_P, BorderLayout.CENTER);
			browse_sign_B=new JButton("Browse Signature");
			browse_sign_B.addActionListener(this);
				sign_P.add(browse_sign_B, BorderLayout.SOUTH);
			
		photo_sign_P.add(sign_P);
		
		pageTwoP.add(photo_sign_P);
		/*****************************************************************************/
		/* ******************************Nominee details**************************/
		nominee_P=new JPanel();
		nominee_P.setLayout(new GridLayout(2,1));
		border=BorderFactory.createTitledBorder("Nominee Details");
		nominee_P.setBorder(border);
		/* ******************************Nominee Name*****************************/
		n_name_P=new JPanel();
			border=BorderFactory.createTitledBorder("Name");
			n_name_P.setBorder(border);
				n_fname=new JLabel("First Name");
					n_name_P.add(n_fname);
				n_fname_T=new JTextField(15);
				n_fname_T.setBorder(borderText);
					n_name_P.add(n_fname_T);
				
				n_mname=new JLabel("     Middle Name");
					n_name_P.add(n_mname);
				n_mname_T=new JTextField(15);
				n_mname_T.setBorder(borderText);
					n_name_P.add(n_mname_T);
				
				n_lname=new JLabel("     Last Name");
					n_name_P.add(n_lname);
				n_lname_T=new JTextField(15);
				n_lname_T.setBorder(borderText);
					n_name_P.add(n_lname_T);
			nominee_P.add(n_name_P);
			/* **************************************************************/
			n_dob_rel_mob_addr_P=new JPanel();
			
			
			/* ****************************************************************/
			/* *********************Nominee's DOB******************************/
			
			n_dob_P=new JPanel();
			border=BorderFactory.createTitledBorder("Date of Birth");
				n_dob_P.setBorder(border);
				n_dd=new JComboBox<String>();
				n_dd.addItem("DD");
				for(int i=1;i<=31;i++)
				{
					n_dd.addItem(""+i);
				}
				n_dob_P.add(n_dd);
				
				n_mm=new JComboBox<String>();
				n_mm.addItem("MM");
				for(int i=1;i<=12;i++)
				{
					n_mm.addItem(""+i);
				}
				n_dob_P.add(n_mm);
				
				n_yyyy=new JComboBox<String>();
				n_yyyy.addItem("YYYY");
				for(int i=1947;i<=2014;i++)
				{
					n_yyyy.addItem(""+i);
				}
				n_dob_P.add(n_yyyy);	
			
				n_dob_rel_mob_addr_P.add(n_dob_P);
			/* ************************Relation With Customer******************************/
			
			n_rel_P=new JPanel();
			border=BorderFactory.createTitledBorder("Relation");
			n_rel_P.setBorder(border);
				
				n_relationC=new JComboBox<String>();
					n_relationC.addItem("Mother");
					n_relationC.addItem("Father");
					n_relationC.addItem("Wife");
					n_relationC.addItem("Husband");
					n_relationC.addItem("Broder");
					n_relationC.addItem("Sistor");
					n_relationC.addItem("Daughter");
					n_relationC.addItem("Son");
					n_relationC.addItem("Other");
					n_rel_P.add(n_relationC);
			n_dob_rel_mob_addr_P.add(n_rel_P);
		/* *********************Mobile No************************************/
			n_mob_P=new JPanel();
			border=BorderFactory.createTitledBorder("Mobile No.");
				n_mob_P.setBorder(border);
				n_mob_T=new JTextField(10);
				n_mob_T.setBorder(borderText);
					n_mob_P.add(n_mob_T);
			n_dob_rel_mob_addr_P.add(n_mob_P);
		/* *****************************Nominee Address***************************/
			n_addr_P=new JPanel();
			border=BorderFactory.createTitledBorder("Address");
				n_addr_P.setBorder(border);
				n_address=new JTextArea(2,20);
				n_address.setLineWrap(true);
				n_address.setWrapStyleWord(true);
									
				addrPane=new JScrollPane(n_address);
				addrPane.setBorder(borderText);
				n_addr_P.add(addrPane);
			n_dob_rel_mob_addr_P.add(n_addr_P);
			
			nominee_P.add(n_dob_rel_mob_addr_P);
		pageTwoP.add(nominee_P);
				
		/* ****************************Action Button******************************/
		pageTwoButton_P=new JPanel();
		
			cancel_P2=new JButton("Cancel");
			cancel_P2.addActionListener(this);
			pageTwoButton_P.add(cancel_P2);
			
			save_B=new JButton("Save");
			save_B.addActionListener(this);
			pageTwoButton_P.add(save_B);
		pageTwoP.add(pageTwoButton_P);
	}

	private void formPageOne() {
		
		contentP=new JPanel();
		contentP.setBorder(BorderFactory.createBevelBorder(1));
		contentP.setLayout(new GridLayout(7,1));
		
		borderText=BorderFactory.createBevelBorder(1);
		
		/* ****************************************************************/
		accountP=new JPanel();
		accountP.setBorder(borderLine);
		/* *************************Account type***************************/
		accountTypeP=new JPanel();
		border=BorderFactory.createTitledBorder("Account Type");
		accountTypeP.setBorder(border);
			saving=new JRadioButton("Saving Account");
				accountTypeP.add(saving);
			current=new JRadioButton("Current Account");
				accountTypeP.add(current);
		ButtonGroup bg_5=new ButtonGroup();
			bg_5.add(saving);
			bg_5.add(current);
		accountP.add(accountTypeP);
		
		/* ***************Account number**********************************/
		accountNoP=new JPanel();
		border=BorderFactory.createTitledBorder("Select Branch");
		accountNoP.setBorder(border);
			branch_list=new JComboBox<String>();
				branch_list.addItem("Select Branch Location");
				MasterAction ma=new MasterAction();
				if(ma.branchList(branchLocation)){
					for(String loc: branchLocation.keySet()){
						branch_list.addItem(branchLocation.get(loc));
					}
				}
			accountNoP.add(branch_list);
		accountP.add(accountNoP);
		contentP.add(accountP);
		
		/* ***********************  Applicant name*************************/
		
		nameP=new JPanel();
		border=BorderFactory.createTitledBorder("Applicant Name");
		nameP.setBorder(border);
			fnameL = new JLabel("    First Name ");
				nameP.add(fnameL);
			fname = new JTextField(15);
			fname.setBorder(borderText);
				nameP.add(fname);
			mnameL = new JLabel("     Middle Name ");
				nameP.add(mnameL);
			mname = new JTextField(15);
			mname.setBorder(borderText);
				nameP.add(mname);
			lnameL = new JLabel("     Last Name ");
				nameP.add(lnameL);
			lname = new JTextField(15);
			lname.setBorder(borderText);
				nameP.add(lname);
		contentP.add(nameP);
		
		pan_gender_dob_marry_P=new JPanel();
		/* **************************PAN Details*************************/
		
		panP=new JPanel();
		border=BorderFactory.createTitledBorder("PAN");
		panP.setBorder(border);
			panNo = new JTextField(15);
			panNo.setBorder(borderText);
				panP.add(panNo);
		pan_gender_dob_marry_P.add(panP);
		
		/* ******************* Gender of applicant**********************/
		
		border=BorderFactory.createTitledBorder("Gender");
		genderP=new JPanel();
		genderP.setBorder(border);
				
			male = new JRadioButton("Male");
				genderP.add(male);
			female = new JRadioButton("Female");
				genderP.add(female);
			trans = new JRadioButton("Transgender");
				genderP.add(trans);
		pan_gender_dob_marry_P.add(genderP);
		ButtonGroup bg=new ButtonGroup();
			bg.add(male);
			bg.add(female);
			bg.add(trans);
		
		/* *********************** Date of birth of applicant****************/
			
		border=BorderFactory.createTitledBorder("Date of Birth");
		dobP=new JPanel();
		dobP.setBorder(border);;
			date = new JComboBox<String>();
				date.addItem("DD");
				for (int i = 1; i <= 31; i++) {
					date.addItem(""+i);
				}
				dobP.add(date);
			month = new JComboBox<String>();
				month.addItem("MM");
				for (int i = 1; i <= 12; i++) {
					month.addItem(""+i);
				}
				dobP.add(month);
			year = new JComboBox<String>();
				year.addItem("YYYY");
				for (int i = 1900; i <= 2014; i++) {
					year.addItem(""+i);
				}
				dobP.add(year);
		
		pan_gender_dob_marry_P.add(dobP);
		
		/* ***************************Martial status*************************/
		maritalP=new JPanel();
		border=BorderFactory.createTitledBorder("Martial Status");
		maritalP.setBorder(border);
			married=new JRadioButton("Married");
				maritalP.add(married);
			unmarried=new JRadioButton("Unmarried");
				maritalP.add(unmarried);
		pan_gender_dob_marry_P.add(maritalP);
		ButtonGroup bg_4=new ButtonGroup();
			bg_4.add(married);
			bg_4.add(unmarried);
		
		contentP.add(pan_gender_dob_marry_P);
		/* *********************************************************************/
		current_permanet_address_P=new JPanel();
		/*  ******************************* Current Address ****************************/
		addressP_1=new JPanel();
		border=BorderFactory.createTitledBorder("Current Address");
		addressP_1.setBorder(border);
			border=BorderFactory.createBevelBorder(1);
			currentAddress = new JTextArea(2, 20);
			currentAddress.setLineWrap(true);
			currentAddress.setWrapStyleWord(true);
				addressP_1.add(currentAddress);
		addrPane=new JScrollPane();
		addrPane.setBorder(borderText);
		addrPane.getViewport().add(currentAddress);
		addressP_1.add(addrPane);
		//contentP.add(addressP);
		current_permanet_address_P.add(addressP_1);
		
		/* *************************Permanent Address**********************************/
		addressP_2=new JPanel();
		border=BorderFactory.createTitledBorder("Parmanent Address");
		addressP_2.setBorder(border);
			permanentAddress=new JTextArea();
			//border=BorderFactory.createBevelBorder(1);
			permanentAddress = new JTextArea(2, 20);
			permanentAddress.setLineWrap(true);
			permanentAddress.setWrapStyleWord(true);
				addressP_2.add(permanentAddress);
		addrPane=new JScrollPane();
		addrPane.setBorder(borderText);
		addrPane.getViewport().add(permanentAddress);
		addressP_2.add(addrPane);
		current_permanet_address_P.add(addressP_2);
		
		contentP.add(current_permanet_address_P);
		
		/* **************************************************************/
		contact_quali_P=new JPanel();
		/* *****************************Contact details*******************/
		contactP=new JPanel();
		contactP.setLayout(new GridLayout(1,2));
		
		border=BorderFactory.createTitledBorder("Contact Details");
		contactP.setBorder(border);
			emailP=new JPanel();
				emaill=new JLabel("E-mail ");
					emailP.add(emaill);
				email = new JTextField(15);
				email.setBorder(borderText);
					emailP.add(email);
			contactP.add(emailP);
			mobileP=new JPanel();
				mobl = new JLabel("Mobile ");
					mobileP.add(mobl);
				mobNo = new JTextField(10);
				mobNo.setBorder(borderText);
					mobileP.add(mobNo);
			contactP.add(mobileP);
		
		contact_quali_P.add(contactP);
		
		//contentP.add(address_contact_P);
		/* *****************************Qualification*************************/
		qualificationsP=new JPanel();
		border=BorderFactory.createTitledBorder("Educational Qualifications");
		qualificationsP.setBorder(border);
			phd = new JRadioButton("Doctorate");
				qualificationsP.add(phd);
			masters = new JRadioButton("Post Graduate");
				qualificationsP.add(masters);
			bachlors = new JRadioButton("Graduate");
				qualificationsP.add(bachlors);
			other = new JRadioButton("Others");
				qualificationsP.add(other);
		//contentP.add(qualificationsP);
		//address_quali_P.add(qualificationsP);
		contact_quali_P.add(qualificationsP);
		ButtonGroup bg_1=new ButtonGroup();
			bg_1.add(phd);
			bg_1.add(masters);
			bg_1.add(bachlors);
			bg_1.add(other);
		
		contentP.add(contact_quali_P);
	
		/* *******************************************************************/	
		occu_income_P=new JPanel();
		/* *************************Occupation*********************************/
		occupationP = new JPanel();
		border=BorderFactory.createTitledBorder("Occupation");
		occupationP.setBorder(border);
		 	business = new JRadioButton("Business");
		 		occupationP.add(business);
		 	defence=new JRadioButton("Defence");
		 		occupationP.add(defence);
			service = new JRadioButton("Service");
				occupationP.add(service);
			student=new JRadioButton("Student");
				occupationP.add(student);
			
			others = new JRadioButton("Others");
				occupationP.add(others);
	
		occu_income_P.add(occupationP);
		ButtonGroup bg_2=new ButtonGroup();
			bg_2.add(business);
			bg_2.add(defence);
			bg_2.add(service);
			bg_2.add(student);
			bg_2.add(others);
		contentP.add(occu_income_P);
		
		/* *****************************Annual income****************************/
		incomeP = new JPanel();
		border=BorderFactory.createTitledBorder("Annual Income");
		incomeP.setBorder(border);
			lowIncome = new JRadioButton("Below Rs. 100,000");
				incomeP.add(lowIncome);
			midIncome = new JRadioButton("100,000 to 500,000");
				incomeP.add(midIncome);
			highIncome = new JRadioButton("Above Rs. 500,000");
				incomeP.add(highIncome);
		
		occu_income_P.add(incomeP);
		ButtonGroup bg_3=new ButtonGroup();
			bg_3.add(lowIncome);
			bg_3.add(midIncome);
			bg_3.add(highIncome);
			
		/* *************************Button Panel*******************************/
		pageOneButtonP=new JPanel();
			cancel = new JButton("Cancel");
			cancel.addActionListener(this);
			pageOneButtonP.add(cancel);
			next = new JButton("Next");
			next.addActionListener(this);
			pageOneButtonP.add(next);
		contentP.add(pageOneButtonP);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==cancel){
			this.dispose();
		}
		if (e.getSource() == next) {
			boolean status=false;
			if(!(saving.isSelected()||current.isSelected())){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease select account type.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(branch_list.getSelectedItem().equals("Select Branch Location")){
				JOptionPane.showMessageDialog(this, "Please select valid branch location.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(!(Pattern.matches("^[A-Za-z]{1,20}", fname.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nFirst name must be alphabets of max\n20 characters.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(!(Pattern.matches("^[A-Za-z]{0,20}", mname.getText()))){
				JOptionPane.showMessageDialog(this, "Middle name must be alphabets of max\n20 characters.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(!(Pattern.matches("^[A-Za-z]{0,20}", lname.getText()))){
				JOptionPane.showMessageDialog(this, "Last name must be alphabets of max\n20 characters.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(panNo.getText().length()>0){
				if(!(Pattern.matches("^[A-Z0-9]{10}", panNo.getText()))){
					JOptionPane.showMessageDialog(this, "PAN number must be alphanumeric of 10 characters.\nEx. DHVPK1724G", "Error Message	", JOptionPane.ERROR_MESSAGE);
				}
			}else if(!(male.isSelected()||female.isSelected()||trans.isSelected())){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease select gender.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(date.getSelectedItem().equals("DD")||month.getSelectedItem().equals("MM")||year.getSelectedItem().equals("YYYY")){
				JOptionPane.showMessageDialog(this, "Please select a valid date of birth.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(!(married.isSelected()||unmarried.isSelected())){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease select martial status.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(currentAddress.getText().length()==0){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease enter current address.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(permanentAddress.getText().length()==0){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease enter permanent address.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(email.getText().length()>0){
				if(!(Pattern.matches("^[A-Za-z0-9]@[A-Za-z.].[A-Za-z]", email.getText()))){
					JOptionPane.showMessageDialog(this, "Please enter a valid e-mail.", "Error Message	", JOptionPane.ERROR_MESSAGE);
				}
			}else if(!(Pattern.matches("\\d{10}", mobNo.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease enter 10 digits mobile number.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(!(phd.isSelected()||masters.isSelected()||bachlors.isSelected()||other.isSelected())){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease select education qualification.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(!(business.isSelected()||defence.isSelected()||service.isSelected()||student.isSelected()||others.isSelected())){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease select occupation.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(!(highIncome.isSelected()||midIncome.isSelected()||lowIncome.isSelected())){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease select annual income.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else{
				status=true;
			}
			
			if(status){
				tab.setEnabledAt(0, false);
				tab.setEnabledAt(1, true);
				tab.setSelectedIndex(1);
				//getting page one data.
				getPageOneData();
			}			
		}
		if(e.getSource()==cancel_P2){
			this.dispose();			
		}
		if(e.getSource()==save_B){
			boolean status=false;
			if(customer.getPhotoGraph()==null){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease upload photograph.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(customer.getSignature()==null){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease upload signature.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(!(Pattern.matches("^[A-Za-z]{1,20}", n_fname_T.getText()))){
				JOptionPane.showMessageDialog(this, "Required!!\nFirst name must be alphabets of max\n20 characters.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(!(Pattern.matches("^[A-Za-z]{0,20}", n_mname_T.getText()))){
				JOptionPane.showMessageDialog(this, "Middle name must be alphabets of max\n20 characters.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(!(Pattern.matches("^[A-Za-z]{0,20}", n_lname_T.getText()))){
				JOptionPane.showMessageDialog(this, "Last name must be alphabets of max\n20 characters.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(n_dd.getSelectedItem().equals("DD")||n_mm.getSelectedItem().equals("MM")||n_yyyy.getSelectedItem().equals("YYYY")){
				JOptionPane.showMessageDialog(this, "Please select a valid date of birth.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(n_mob_T.getText().length()>0){
				if(!(Pattern.matches("\\d{10}", n_mob_T.getText())))
					JOptionPane.showMessageDialog(this, "Required!!\nPlease enter 10 digits mobile number.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else if(n_address.getText().length()==0){
				JOptionPane.showMessageDialog(this, "Required!!\nPlease enter address.", "Error Message	", JOptionPane.ERROR_MESSAGE);
			}else{
				status=true;
			}
			
			if(status){
				//getting  page two data.
				getPageTwoData();
			
				ClerkOperations co=new ClerkActions();
				accountSummary=co.openNewAccount(customer, nominee, ACCOUNT_TYPE, BRANCH_CODE);
				if(accountSummary!=null){
					int opt=JOptionPane.showConfirmDialog(this, "Account opened successfuly.\n\nWould you like to view details?\n", "Message", JOptionPane.YES_NO_OPTION);
					if(opt==0){
						tab.setEnabledAt(1, false);
						tab.setEnabledAt(2, true);
						tab.setSelectedIndex(2);
						//SETTING SUMMARY PAGE DETAILS
						setSummaryPage();
						
					}else{
						this.dispose();
					}
				}else{
					JOptionPane.showMessageDialog(this, "Account could not opened.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(e.getSource()==browse_photo_B){
			//System.out.println("BROWING PHOTOGRAPH");
			JFileChooser fc=new JFileChooser();
			int status=fc.showOpenDialog(this);
			if(status==0){
				File photograph=fc.getSelectedFile();
				ImageIcon img=new ImageIcon(photograph.toString());
				JLabel label=new JLabel(img);
				photo_show_P.add(label);
				this.repaint();	
				{
					customer.setPhotoGraph(photograph.toString());
				}
			}
		
		}
		if(e.getSource()==browse_sign_B){
			//System.out.println("BROWING SIGNATURE");
			JFileChooser fc=new JFileChooser();
			int status=fc.showOpenDialog(this);
			if(status==0){
				File signature=fc.getSelectedFile();
				ImageIcon img=new ImageIcon(signature.toString());
				JLabel label=new JLabel(img);
				sign_show_P.add(label);
				this.repaint();
				{
					customer.setSignature(signature.toString());
				}
			}
			
		}
		if(e.getSource()==summaryCloseBtn){
			this.dispose();
		}
	}

	private void setSummaryPage() {
		accountNumber.setText(accountSummary.getAccountNumber());
		accountType.setText(accountSummary.getAccountType());
		branchCode.setText(accountSummary.getBranchCode());
		IFSCCode.setText(accountSummary.getIFSCCode());
		branchLoc.setText(accountSummary.getBranchLocation());
		branchAddress.setText(accountSummary.getBranchAddress());
		branchTel.setText(accountSummary.getBranchTel());
		branchFAX.setText(accountSummary.getBranchFAX());
		accountHolder.setText(accountSummary.getAccountHolder());
		custAddress.setText(accountSummary.getAddress());
		customerMobile.setText(accountSummary.getCustomerMobile());
		accountOpenDate.setText(accountSummary.getOpenAccountDate());
	}

	private void getPageTwoData() {
		nominee.setFirstName(n_fname_T.getText());
		nominee.setMiddleName(n_mname_T.getText());
		nominee.setLastName(n_lname_T.getText());
		
		//DATE OF BIRTH
		String dob=n_yyyy.getSelectedItem()+"/"+n_mm.getSelectedItem()+"/"+n_dd.getSelectedItem();
		nominee.setDateOfBirth(dob);
		
		//RELATION
		nominee.setRelation((String) n_relationC.getSelectedItem());
		
		//MOBILE NUMBER
		nominee.setMobileNo(n_mob_T.getText());
		
		//ADDRESS
		nominee.setAddress(n_address.getText());
	}

	private void getPageOneData() {
		//ACCOUNT TYPE
		if(saving.isSelected()){
			ACCOUNT_TYPE="SAVING";
		}else{
			ACCOUNT_TYPE="CURRENT";
		}
		//BRANCH LOCATION
		String selectItem=(String) branch_list.getSelectedItem();
		for(String key: branchLocation.keySet()){
			
			if(branchLocation.get(key).equals(selectItem)){
				BRANCH_CODE=key;
				break;
			}
		}
		
		//CUSTOMER INFORMATIONS
		customer.setFirstName(fname.getText());
		customer.setMiddleName(mname.getText());
		customer.setLastName(lname.getText());
		customer.setEmail(email.getText());
		customer.setMobile(mobNo.getText());
		customer.setCurrentAddress(currentAddress.getText());
		customer.setPermananetAddress(permanentAddress.getText());
		customer.setPancard(panNo.getText());
		//GENDER
		if(male.isSelected()){
			customer.setGender("Male");
		}else if(female.isSelected()){
			customer.setGender("Female");
		}else{
			customer.setGender("Transgender");
		}
		//MARTIAL STATUS
		if(married.isSelected()){
			customer.setMartialStatus("Married");
		}else{
			customer.setMartialStatus("Unmarried");
		}
		//DATE OF BIRTH
		String dob=year.getSelectedItem()+"/"+month.getSelectedItem()+"/"+date.getSelectedItem();
		customer.setDateOfBirth(dob);
		
		//ANNUAL INCOME CATEGORY
		if(lowIncome.isSelected()){
			customer.setAnnulIncome("LOW");
		}else if(highIncome.isSelected()){
			customer.setAnnulIncome("HIGH");
		}else{
			customer.setAnnulIncome("MEDIUM");
		}				
		
		//EDUCATION QUALOFICATIONS
		if(phd.isSelected()){
			customer.setEduQualification("Doctorate");
		}else if(masters.isSelected()){
			customer.setEduQualification("Post Graduate");
		}else if(bachlors.isSelected()){
			customer.setEduQualification("Graduate");
		}else{
			customer.setEduQualification("Others");
		}
		//OCCUPATION
		if(business.isSelected()){
			customer.setOccupation("Business");
		}else if(defence.isSelected()){
			customer.setOccupation("Defence");
		}else if(service.isSelected()){
			customer.setOccupation("Service");
		}else if(student.isSelected()){
			customer.setOccupation("Student");
		}else{
			customer.setOccupation("Other");
		}
	}
}
