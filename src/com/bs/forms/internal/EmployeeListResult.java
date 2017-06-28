package com.bs.forms.internal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.List;

import com.bs.listGeneration.EmployeeList;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bs.listGeneration.*;

@SuppressWarnings("serial")
public class EmployeeListResult extends JInternalFrame {
	JPanel EmpListPanel;
	JTable table;
	DefaultTableModel model;
	JScrollPane scroll;
	String columnNames[] = { "Employee Id", "Name", "Gender", "Contact No",
			"E Mail", "Designation", "Department Name", "Location" };

	public EmployeeListResult() {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "Full Employee List";
		EmpListPanel = new JPanel();
		table = new JTable();
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		List<EmpList> list = EmployeeList.getEmployeeList();
		int j = 0;
		for (Iterator<EmpList> i = list.iterator(); i.hasNext();) {
			EmpList item = i.next();
			model.addRow(new Object[] { item.getEmpID(), item.getName(),
					item.getGender(), item.getContact_No(), item.getE_Mail(),
					item.getDesig(), item.getDept_Name(), item.getLocation() });
			j++;
		}
		if (j < 1) {
			JOptionPane.showMessageDialog(null, "No Record Found", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {

		}
		EmpListPanel.add(scroll);
		this.add(EmpListPanel);
		this.setVisible(true);
	}
}
