import javax.swing.table.AbstractTableModel;

public class ClientTableModel extends AbstractTableModel {
	
	@Override
	public Object getValueAt(int row, int col) {
		return Client.context.getValue(col);
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		System.out.println("In SetValueAt");
        Client.context.setValue(col, (String) value);
        fireTableCellUpdated(row, col);
    }
	
	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public int getRowCount() {
		return 1;
	}
	
	public boolean isCellEditable(int row, int col) {
		return true;
	}
}
