package com.bs.forms.internal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bs.listGeneration.DepartmentList;

@SuppressWarnings("serial")
public class ListEmpDept extends JInternalFrame implements ActionListener {
	JPanel EmpListPanel;
	JTable table;
	DefaultTableModel model;
	JButton button;
	String dept = "";
	JComboBox<String> deptList;
	JPanel internalFrameHolder;

	public ListEmpDept() {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		this.closable = true;
		this.title = "[ EMPLOYEE Detail ]";
		this.setSize(getMaximumSize());
		EmpListPanel = new JPanel();
		EmpListPanel.setLayout(new FlowLayout());
		deptList = new JComboBox<String>();
		deptList.addItem("Select Department");
		List<String> deptlist = new ArrayList<String>();
		try {
			deptlist = DepartmentList.getDeptList();
		} catch (ClassNotFoundException | SQLException e) {
			deptList.addItem("Error IN Fetching List");
		}
		for (Iterator<String> i = deptlist.iterator(); i.hasNext();) {
			String item = i.next();
			deptList.addItem(item);
		}
		EmpListPanel.add(deptList);
		button = new JButton("Show List");
		button.addActionListener(this);
		EmpListPanel.add(button);
		this.add(EmpListPanel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			dept = (String) deptList.getSelectedItem();
			new ListEmpByDept(dept);
		}

	}
}
