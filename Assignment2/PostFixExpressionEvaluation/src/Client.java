import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class Client {
	
	public static ClientTableModel tableModel = new ClientTableModel();
	public static JTable table;
	public boolean isEquationView = false;
	public JFrame frame;
	public static Context context = new Context();
	
	public void setupTable() {
		JPanel tblpnl = new JPanel(new BorderLayout());
		JPanel btnpnl = new JPanel(new BorderLayout());
		frame = new JFrame("Postfix Evaluator");
		table = new JTable(tableModel);
		table.setRowHeight(25);
		JScrollPane sp = new JScrollPane(table);
		tblpnl.add(sp);
		JButton toggleView = new JButton("Toggle View");
		toggleView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isEquationView) {
					context.toggleEquationView();
					isEquationView = false;
				}
				else {
					context.toggleValueView();
					isEquationView = true;
				}
			}
		}); 
		
		btnpnl.add(toggleView);
		frame.add(tblpnl, BorderLayout.CENTER);
		frame.add(btnpnl, BorderLayout.SOUTH);
		frame.setSize(800, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}