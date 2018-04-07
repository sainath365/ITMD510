package views;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LoanView {
	public void runView(ResultSet rs) {
		// instantiate vector objects to hold column/row data for JTable
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<String> column = new Vector<String>();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int columns = metaData.getColumnCount();

			// get column names from table!
			String cols = "";

			for (int i = 1; i <= columns; i++) {
				cols = metaData.getColumnName(i);
				column.add(cols);
			}
			// get row data from table!

			while (rs.next()) {
				Vector<Object> row = new Vector<Object>(columns);

				for (int i = 1; i <= columns; i++) 
					row.addElement(rs.getObject(i));
				data.addElement(row);
			}

			DefaultTableModel model = new DefaultTableModel(data, column);

			JTable table = new JTable(model);
			JFrame frame = new JFrame("Loan Details");
			frame.setSize(700, 200);
			frame.add(new JScrollPane(table));
			frame.setDefaultCloseOperation(0);
			frame.pack();
			frame.setVisible(true);

			rs.close(); //close ResultSet instance 
		} catch (SQLException e) { e.printStackTrace(); }
	}

}
