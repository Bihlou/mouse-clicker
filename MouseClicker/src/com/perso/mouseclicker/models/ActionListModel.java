package com.perso.mouseclicker.models;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.AbstractTableModel;

import com.perso.mouseclicker.beans.ActionBean;

public class ActionListModel extends AbstractTableModel{

	private static final long serialVersionUID = 59507077822861153L;
	
	private String[] header;
    private ArrayList<ActionBean> data;

	public ActionListModel(String[] header) {
        this.header = header;
        this.data = new ArrayList<ActionBean>();
    }
    
    public void addRow(ActionBean row) {
    	data.add(row);
    	this.fireTableDataChanged();
    }
    
    public void removeRow(int row) {
    	data.remove(row);
    	refresh();
    }
    
	@Override
	public int getRowCount() {
		if (data != null){
			return data.size();
		} else {
			return 0;
		}
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}
	
	@Override
	public String getColumnName(int columns) {
		return header[columns];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0){
			return data.get(rowIndex).getAction();
		} else if(columnIndex == 1){
			return data.get(rowIndex).getCoordX();
		} else if(columnIndex == 2){
			return data.get(rowIndex).getCoordY();
		} else if(columnIndex == 3){
			return data.get(rowIndex).getMinDelay();
		} else if(columnIndex == 4){
			return data.get(rowIndex).getMaxDelay();
		} else if(columnIndex == 5){
			return data.get(rowIndex).getRepeat();
		} else if(columnIndex == 6){
			return data.get(rowIndex).getComment();
		}
		return null;
	}
	
	@Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if(columnIndex == 0){
			data.get(rowIndex).setAction(com.perso.mouseclicker.util.ActionEnum.getActionByLabel((String) value));
		} else if(columnIndex == 1){
			data.get(rowIndex).setCoordX((int) value);
		} else if(columnIndex == 2){
			data.get(rowIndex).setCoordY((int) value);
		} else if(columnIndex == 3){
			data.get(rowIndex).setMinDelay((int) value);
		} else if(columnIndex == 4){
			data.get(rowIndex).setMaxDelay((int) value);
		} else if(columnIndex == 5){
			data.get(rowIndex).setRepeat((int) value);
		} else if(columnIndex == 6){
			data.get(rowIndex).setComment((String) value);
		}
    }

    public ArrayList<ActionBean> getData() {
    	Collections.sort(data);
		return data;
	}

	public void setData(ArrayList<ActionBean> data) {
		Collections.sort(data);
		this.data = data;
    	this.fireTableDataChanged();
	}

	public ActionBean getSelectedActionBean(int row) {
		if (row >= 0 && getData().size()  >= row ){
			return getData().get(row);
		} else {
			return new ActionBean();
		}
	}
	
	public int getNewId() {
		ArrayList<ActionBean> listAction = getData();
		if (listAction != null && listAction.size() > 0){
			return listAction.get(listAction.size() -1).getId() + 1;
		}
		return 0;
	}
	
	public void refresh(){
		this.fireTableDataChanged();
	}

	public void sort() {
		Collections.sort(data);
	}

}
