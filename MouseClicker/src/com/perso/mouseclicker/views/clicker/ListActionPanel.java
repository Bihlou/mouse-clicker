package com.perso.mouseclicker.views.clicker;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.perso.mouseclicker.controller.AppController;
import com.perso.mouseclicker.listeners.ActionListSelectionListener;
import com.perso.mouseclicker.listeners.ButtonClickListener;
import com.perso.mouseclicker.models.ActionListModel;
import com.perso.mouseclicker.views.generic.TitlePanel;

public class ListActionPanel extends TitlePanel {

	private static final long serialVersionUID = -6762289768431264357L;

	private JTable listAction;
	private JButton deleteButton;
	private JButton moveUp;
	private JButton moveDown;
	private JPanel buttonPanel;

	private ActionListModel actionListModel;
	
	private AppController controller;
	private ButtonClickListener buttonClickListener;
	
	public ListActionPanel(AppController controller, ButtonClickListener buttonClickListener){
		super(com.perso.mouseclicker.util.Text.TITLE_ACTION_LIST);
		this.controller = controller;
		this.buttonClickListener = buttonClickListener;
		init();
	}
	
	private void init() {

		this.setPreferredSize(new Dimension(900,290));
		this.setMinimumSize(new Dimension(900,290));
		
		String[] header = {"Action","X","Y","Min","Max","Repeat","Comment"};
		
		actionListModel = new ActionListModel(header);
		listAction = new JTable(actionListModel);
		listAction.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listAction.getSelectionModel().addListSelectionListener(new ActionListSelectionListener(getController(), this));

		JScrollPane scrollPane = new JScrollPane(listAction);
		listAction.setFillsViewportHeight(true);
		scrollPane.setPreferredSize(new Dimension(700, 200));
		
		deleteButton = new JButton(com.perso.mouseclicker.util.Text.REMOVE_ROW);
		deleteButton.addActionListener(buttonClickListener);
		
		moveUp = new JButton(com.perso.mouseclicker.util.Text.MOVE_UP);
		moveUp.addActionListener(buttonClickListener);
		
		moveDown = new JButton(com.perso.mouseclicker.util.Text.MOVE_DOWN);
		moveDown.addActionListener(buttonClickListener);
		
		this.add(scrollPane, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,9 ,5, 0, 0 ));
		
		buttonPanel = new JPanel();

		buttonPanel.setLayout(new GridBagLayout());
		
		buttonPanel.add(deleteButton, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,1 ,0, 0, 0 ));
		buttonPanel.add(moveUp, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,1 ,1, 0, 1 ));
		buttonPanel.add(moveDown, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,1 ,1, 0, 2 ));

		this.add(buttonPanel, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,1 ,0, 10, 0 ));
		
		
	}
	
	public void selectRow(int row){
		listAction.setRowSelectionInterval(row,row);
	}

	public JTable getListAction() {
		return listAction;
	}

	public void setListAction(JTable listAction) {
		this.listAction = listAction;
	}

	public ActionListModel getActionListModel() {
		return actionListModel;
	}

	public void setActionListModel(ActionListModel actionListModel) {
		this.actionListModel = actionListModel;
	}

	public AppController getController() {
		return controller;
	}

	public void setController(AppController controller) {
		this.controller = controller;
	}
	
	
	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	
}
