package org.hepcrush.carnet.swing;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class ZModel extends AbstractTableModel {
    private Object[][] data;
    private String[] title;

    public ZModel(Object[][] data, String[] title){
        this.data = data;
        this.title = title;
    }

    public String getColumnName(int col){
        return this.title[col];
    }

    @Override
    public int getColumnCount() {
        return this.title.length;
    }

    public int getRowCount(){
        return this.data.length;
    }

    public Object getValueAt(int row, int col){
        return this.data[row][col];
    }

    public Class getColumnClass(int col){
        return this.data[0][col].getClass();
    }

    public boolean isCellEditable(int row, int col){
        if (getValueAt(0,col) instanceof JButton){
            return true;
        }
        return true;
    }

    public void addRow(Object[] data){
        int i=0;
        int nbRow= this.getRowCount();
        int nbCol = this.getColumnCount();

        Object temp[][] = this.data;
        this.data = new Object[nbRow+1][nbCol];

        for (Object[] value: temp){
            this.data[i++] = value;
        }

        this.data[i] = data;
        temp=null;
        this.fireTableDataChanged();
    }
}

