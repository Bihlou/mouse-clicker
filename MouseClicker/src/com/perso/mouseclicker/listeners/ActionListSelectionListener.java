package com.perso.mouseclicker.listeners;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.perso.mouseclicker.controller.AppController;
import com.perso.mouseclicker.models.ActionListModel;
import com.perso.mouseclicker.views.clicker.ListActionPanel;

public class ActionListSelectionListener implements ListSelectionListener {
	
	private AppController controller;
	private ListActionPanel listActionPanel;

	public ActionListSelectionListener(AppController controller, ListActionPanel listActionPanel){
		this.controller = controller;
		this.listActionPanel = listActionPanel;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
	 	ActionListModel listModel = (ActionListModel) listActionPanel.getListAction().getModel();
	 	controller.updateCurrentAction(listModel.getSelectedActionBean(listActionPanel.getListAction().getSelectedRow()));
	 	
	}

}
