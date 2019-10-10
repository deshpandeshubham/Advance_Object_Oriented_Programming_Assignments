import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

public class Client {
	
	static ClientTableModel tableModel = new ClientTableModel();
	static JTable table;
	static boolean isEquationView = false;
	JFrame frame;
	static Context context = new Context();
	HashMap<Integer, String> previousValues = new HashMap<Integer, String>();
	boolean isCellValueChanged = false;
	static boolean isUndoClicked = false;
	int[] dependentCellsIndex = new int[9];
	EvaluateExpression eval = new EvaluateExpression();
	static boolean isToggleClicked = false;
	static boolean isObserver = false;
	int[][] arr = instantiateDependencyMatrix();
	
	public void setupTable() {
		JPanel tblpnl = new JPanel(new BorderLayout());
		JPanel btnpnl = new JPanel(new FlowLayout());
		frame = new JFrame();
		table = new JTable(tableModel);
		frame.setTitle("Equation View");
		table.setRowHeight(25);
		JScrollPane sp = new JScrollPane(table);
		tblpnl.add(sp);
		JButton toggleView = new JButton("Toggle View");
		toggleView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isToggleClicked = true;
				if(isEquationView) {
					frame.setTitle("Equation View");
					previousValues.putAll(context.expressionString);
					toggleEquationView();
					isEquationView = false;
				}
				else {						
					int i=0;
					for(int key : previousValues.keySet()) {
						if(!(context.values.get(key).equals(previousValues.get(key)))) {
							System.out.println("Value changed at : " + key);
							isCellValueChanged = true;
							dependentCellsIndex[i] = key;
							i++;
						}
					}
					frame.setTitle("Value View");
					instantiateDependencyMatrix();
					if(isCellValueChanged) {
						notifyObserver();
						isCellValueChanged = false;
					}
					toggleValueView();
					isEquationView = true;
				}
				isToggleClicked = false;
			}
		}); 
		
		JButton undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isUndoClicked = true;
				Client.context.restorePreviousState();
				isUndoClicked = false;
			}
		});
		
		btnpnl.add(toggleView);
		btnpnl.add(undo);
		frame.add(tblpnl, BorderLayout.CENTER);
		frame.add(btnpnl, BorderLayout.SOUTH);
		frame.setSize(800, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void toggleValueView() {		
		for(Integer key : context.values.keySet()) {
			String value = context.getValue(key);
			System.out.println("Cell Value -----> "+value+" at index :"+key);
			context.expressionString.put(key, value);
			if(value.contains(" ")) {
				context.expressionString.put(key, value);
				System.out.println("Value at "+key+" in expression map : "+ context.expressionString.get(key));
				value = getValueExpression(value);
				System.out.println("Value Expression After Conversion----->"+value);
				Client.context.dependentCellsSet = new HashSet<String>();
			}
			Double res = eval.getResult(value.trim());
			context.values.put(key, res.toString());
			tableModel.setValueAt(res.toString(), 0, key);
		}
		context.saveCellState();
	}

	public void toggleEquationView() {
		for(Integer key : context.expressionString.keySet()) {
			String value = context.expressionString.get(key);
			System.out.println("Cell Value----->"+value+"at index : "+key);
			tableModel.setValueAt(value, 0, key);
		}
	}

	public static String getValueExpression(String expression) {
		String[] alphabets = expression.split(" ");
		for(int i=0;i<alphabets.length;i++) {
			if(alphabets[i].matches("[A-I]")) {
				isObserver = true;
				alphabets[i] = getValueExpression(context.getValue(((int)(alphabets[i].charAt(0) - 65))));
			}
			else
				continue;
		}
		return Arrays.toString(alphabets).replace("[", "").replace("]", "").replace(",", "");
	}
	
	public int[][] instantiateDependencyMatrix() {
		int[][] dependencyMatrix = new int[9][9];
		for(int i=0;i<context.values.size();i++) {
			String[] literals = context.values.get(i).split(" ");
			for(int j=0;j<literals.length;j++) {
				if(literals[j].equals("A"))
					dependencyMatrix[i][0] = 1;
				else if(literals[j].equals("B"))
					dependencyMatrix[i][1] = 1;
				else if(literals[j].equals("C"))
					dependencyMatrix[i][2] = 1;
				else if(literals[j].equals("D"))
					dependencyMatrix[i][3] = 1;
				else if(literals[j].equals("E"))
					dependencyMatrix[i][4] = 1;
				else if(literals[j].equals("F"))
					dependencyMatrix[i][5] = 1;
				else if(literals[j].equals("G"))
					dependencyMatrix[i][6] = 1;
				else if(literals[j].equals("H"))
					dependencyMatrix[i][7] = 1;
				else if(literals[j].equals("I"))
					dependencyMatrix[i][8] = 1;
			}
		}
		return dependencyMatrix;
	}
	
	public void notifyObserver() {
		for(int colIndex : dependentCellsIndex)
			update(dependentCellsIndex[colIndex]);
	}
	
	public void update(int colIndex) {
		for(int row=0; row<9; row++) {
			if(arr[row][colIndex] == 1) {
				String expr = context.values.get(row);
				Double result = eval.getResult(expr);
				tableModel.setValueAt(result.toString(), 0, row);
			}
		}
	}
}