package com.bs.forms.internal;

import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.bs.listGeneration.EmpList;
import com.bs.listGeneration.EmpListByDept;

@SuppressWarnings("serial")
public class ListEmpByDept extends JFrame {
	JTable table;
	JPanel panel;
	DefaultTableModel model;
	JScrollPane scroll;
	String columnNames[] = { "Employee Id   ", "Name   ", "Gender", "Contact No",
			"E Mail", "Designation", "Department Name  ", "Location  " };

	public ListEmpByDept(String dept) {
		super("[ EMPLOYEE Details ] ");
		this.setSize(600, 500);
		table = new JTable();
		panel = new JPanel();
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.setFillsViewportHeight(true);
		scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		List<EmpList> list = EmpListByDept.getEmployeeList(dept);
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
			panel.add(scroll);
			this.add(panel);
			this.setVisible(false);
		} else {
			panel.add(scroll);
			this.add(panel);
			this.setVisible(true);
		}

	}
}
