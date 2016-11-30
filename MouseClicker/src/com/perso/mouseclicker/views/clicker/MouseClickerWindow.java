package com.perso.mouseclicker.views.clicker;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.perso.mouseclicker.controller.AppController;
import com.perso.mouseclicker.listeners.ButtonClickListener;

public class MouseClickerWindow {

	private JFrame mainFrame;
	private EditActionPanel editActionPanel;
	private ListActionPanel listActionPanel;
	private ScriptPanel scriptPanel;

	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemSave, menuItemLoad;
	
	private AppController controller;

	private ButtonClickListener buttonClickListener;
	
	public MouseClickerWindow(AppController controller){
		addController(controller);
		init();
	}
	
	private void init() {
		
		mainFrame = new JFrame(com.perso.mouseclicker.util.Text.WINDOW_TITLE);
		mainFrame.setSize(900, 700);
		mainFrame.setLocationRelativeTo(null);
		
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createMenu();
		mainFrame.setJMenuBar(menuBar);
		
		setEditActionPanel(new EditActionPanel(getButtonClickListener()));
		mainFrame.add(getEditActionPanel(),com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,5 ,0, 0, 0 ));
	    
	    setListActionPanel(new ListActionPanel(getController(), getButtonClickListener()));
	    mainFrame.add(getListActionPanel(),com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,5 ,0, 0, 1 ));
	    
	    setScriptPanel(new ScriptPanel(getController(), getButtonClickListener()));
	    mainFrame.add(getScriptPanel(),com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,1 ,1, 0, 2 ));
	}
	
	public void addController(AppController controller){
		this.controller =  controller;
	}
	
	private void createMenu(){
		menuBar = new JMenuBar();
		menuFile = new JMenu(com.perso.mouseclicker.util.Text.FILE);
		menuBar.add(menuFile);
		menuItemSave = new JMenuItem(com.perso.mouseclicker.util.Text.SAVE);
		menuItemSave.setActionCommand(com.perso.mouseclicker.util.Text.SAVE);
		menuItemSave.addActionListener(getButtonClickListener());
		menuItemLoad = new JMenuItem(com.perso.mouseclicker.util.Text.LOAD);
		menuItemLoad.setActionCommand(com.perso.mouseclicker.util.Text.LOAD);
		menuItemLoad.addActionListener(getButtonClickListener());
		menuFile.add(menuItemSave);
		menuFile.add(menuItemLoad);
	}

	public void show() {
		mainFrame.setVisible(true);
	}
	
	public void hide() {
		mainFrame.setVisible(false);
	}
	
	
	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public ListActionPanel getListActionPanel() {
		return listActionPanel;
	}

	public AppController getController() {
		return controller;
	}

	public void setController(AppController controller) {
		this.controller = controller;
	}

	public ButtonClickListener getButtonClickListener() {
		if ( buttonClickListener == null ) {
			buttonClickListener = new ButtonClickListener(getController());
		}
		return buttonClickListener;
	}

	public void setButtonClickListener(ButtonClickListener buttonClickListener) {
		this.buttonClickListener = buttonClickListener;
	}

	public EditActionPanel getEditActionPanel() {
		return editActionPanel;
	}

	public void setEditActionPanel(EditActionPanel editActionPanel) {
		this.editActionPanel = editActionPanel;
	}

	public void setListActionPanel(ListActionPanel listActionPanel) {
		this.listActionPanel = listActionPanel;
	}

	public ScriptPanel getScriptPanel() {
		return scriptPanel;
	}

	public void setScriptPanel(ScriptPanel scriptPanel) {
		this.scriptPanel = scriptPanel;
	}

}
