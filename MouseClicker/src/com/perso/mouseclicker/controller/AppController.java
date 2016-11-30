package com.perso.mouseclicker.controller;
	
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.perso.mouseclicker.beans.ActionBean;
import com.perso.mouseclicker.beans.FileBean;
import com.perso.mouseclicker.exceptions.ListIllegalArgumentException;
import com.perso.mouseclicker.models.ActionListModel;
import com.perso.mouseclicker.models.ManageFileModel;
import com.perso.mouseclicker.run.Worker;
import com.perso.mouseclicker.util.ActionEnum;
import com.perso.mouseclicker.views.clicker.MouseClickerWindow;
import com.perso.mouseclicker.views.clicker.PickLayer;
import com.perso.mouseclicker.views.generic.LoadFileChooser;
import com.perso.mouseclicker.views.generic.SaveFileChooser;

public class AppController {

	private MouseClickerWindow mainView;
	private ManageFileModel appModel;
	private PickLayer pickLayer;
	private LoadFileChooser loadFileChooser;
	private SaveFileChooser saveFileChooser;
	private Worker worker;
	
	public AppController(){}
	
	public void startApp(){
		getMainView().show();
	}
	
	public void updateCoord(Point point){
		getMainView().getEditActionPanel().getxTextField().setText(Integer.toString((int)point.getX()));
		getMainView().getEditActionPanel().getyTextField().setText(Integer.toString((int)point.getY()));
	}
	
	public void addView(MouseClickerWindow view){
		setMainView(view);
	}

	public void addModel(ManageFileModel model) {
		setAppModel(model);
	}
	
	public void treatActionButton(ActionEvent e){
		String command = e.getActionCommand();  
        if( command.equals( com.perso.mouseclicker.util.Text.PICK ))  {
        	pickCoordXY();
        } else if ( command.equals( com.perso.mouseclicker.util.Text.ADD ))  {
        	addActionRow();
        } else if ( command.equals( com.perso.mouseclicker.util.Text.SAVE ))  {
        	saveActionList();
        } else if ( command.equals( com.perso.mouseclicker.util.Text.LOAD ))  {
        	loadData();
        } else if ( command.equals( com.perso.mouseclicker.util.Text.UPDATE )){
        	updateSelectedRow();
        } else if ( command.equals( com.perso.mouseclicker.util.Text.REMOVE_ROW )){
        	removeSelectedRow();
        } else if ( command.equals( com.perso.mouseclicker.util.Text.MOVE_UP )){
        	moveUp();
        } else if ( command.equals( com.perso.mouseclicker.util.Text.MOVE_DOWN )){
        	moveDown();
        } else if ( command.equals( com.perso.mouseclicker.util.Text.START )){
        	startScript();
        } else if ( command.equals( com.perso.mouseclicker.util.Text.STOP )){
        	stopScript();
        }
       
	}
	
	private void startScript(){
		ArrayList<String> errors = new ArrayList<String>();
		boolean error = false;
		String delayAfter = getMainView().getScriptPanel().getDelayAfterTextField().getText(); 
		String repeatCount = getMainView().getScriptPanel().getRepeatTextField().getText(); 
		
		if (! com.perso.mouseclicker.util.Method.isPositiveInteger(delayAfter)){
			errors.add(com.perso.mouseclicker.util.Text.ERROR_SCRIPT_DELAY);
		}

		if (! com.perso.mouseclicker.util.Method.isPositiveInteger(repeatCount)){
			errors.add(com.perso.mouseclicker.util.Text.ERROR_SCRIPT_REPEAT);
		}
		
		if (error){
			showErrorsBox(errors);
		} else {
			getWorker(true);
			getWorker(false).setDelayAfter(Integer.valueOf(delayAfter));
			if ( getMainView().getScriptPanel().getRepeatCheckBox().isSelected() ){
				getWorker(false).setRepeat(true);
			} else {
				getWorker(false).setRepeatNumber(Integer.valueOf(repeatCount));	
			}
			beforeScriptLaunched();
			getWorker(false).execute();
		}
	}
	
	private void showErrorsBox(ArrayList<String> errors){
		String errorMessage = "";
		for (String error : errors){
			errorMessage += error + "\n";
		}
		
		JOptionPane.showMessageDialog(getMainView().getMainFrame(),
			    errorMessage,
			    com.perso.mouseclicker.util.Text.ERROR_BOX_TITLE,
			    JOptionPane.ERROR_MESSAGE);
	}
	
	private void showMessageBox(String message){
		JOptionPane.showMessageDialog(getMainView().getMainFrame(),
				message,
			    com.perso.mouseclicker.util.Text.MESSAGE_BOX_TITLE,
			    JOptionPane. INFORMATION_MESSAGE);
	}
	
	private void stopScript(){
		//getThread().interrupt();
		getWorker(false).cancel(true);
		restoreView();
	}

	private void moveUp() {
		int row = getMainView().getListActionPanel().getListAction().getSelectedRow();
		int rowCount = getMainView().getListActionPanel().getListAction().getModel().getRowCount();
		if (row > 0 && rowCount > 1){
			ActionListModel actionListModel = getActionListModel();
			ActionBean actualAction = actionListModel.getData().get(row);
			ActionBean moveAction = actionListModel.getData().get(row - 1);
			int tmpId = actualAction.getId();
			actualAction.setId(moveAction.getId());
			moveAction.setId(tmpId);
			actionListModel.sort();
			actionListModel.fireTableRowsUpdated(row -1, row);
			getMainView().getListActionPanel().selectRow(row - 1);
		}
	}
	
	private void moveDown() {
		int row = getMainView().getListActionPanel().getListAction().getSelectedRow();
		int rowCount = getMainView().getListActionPanel().getListAction().getModel().getRowCount();
		if (row > -1 && rowCount > 1  && row + 1 < rowCount){
			
			ActionListModel actionListModel = getActionListModel();
			ActionBean actualAction = actionListModel.getData().get(row);
			ActionBean moveAction = actionListModel.getData().get(row + 1);
			int tmpId = actualAction.getId();
			actualAction.setId(moveAction.getId());
			moveAction.setId(tmpId);
			actionListModel.sort();
			actionListModel.fireTableRowsUpdated(row, row + 1);
			getMainView().getListActionPanel().selectRow(row + 1);
		}
	}
	
	public ActionListModel getActionListModel(){
		return (ActionListModel) getMainView().getListActionPanel().getListAction().getModel();
	}

	private void removeSelectedRow() {
		int row = getMainView().getListActionPanel().getListAction().getSelectedRow();
		if (row > -1){
			getMainView().getListActionPanel().getActionListModel().removeRow(row);
		}
	}

	private void pickCoordXY(){
		getMainView().hide();
    	getPickLayer().show();
	}
	
	private void addActionRow(){
		try{
			getMainView().getListActionPanel().getActionListModel().addRow(getCurrentAction());
		} catch(ListIllegalArgumentException e){
			showErrorsBox(e.getListErrors());
		}
	}
	
	private void saveActionList(){
		if (getSaveFileChooser().show(getMainView().getMainFrame()) == JFileChooser.APPROVE_OPTION){

    		String path = getSaveFileChooser().getFile().getPath();
    		if (! path.toLowerCase().endsWith(".xml")){
    			path += ".xml";
    		}
    		
    		ActionListModel listModel = getActionListModel();
    		boolean repeatUntilStop =  getMainView().getScriptPanel().getRepeatCheckBox().isSelected();
    		String repeatCount = getMainView().getScriptPanel().getRepeatTextField().getText();
    		String delayAfter = getMainView().getScriptPanel().getDelayAfterTextField().getText();
    		boolean error = false;
    		ArrayList<String> errorsMessage = new ArrayList<String>();
    		
    		if (! com.perso.mouseclicker.util.Method.isPositiveInteger(repeatCount) ){
    			error = true;
    			errorsMessage.add(com.perso.mouseclicker.util.Text.ERROR_SCRIPT_REPEAT);
    		}
    		
    		if (! com.perso.mouseclicker.util.Method.isPositiveInteger(delayAfter) ){
    			error = true;
    			errorsMessage.add(com.perso.mouseclicker.util.Text.ERROR_SCRIPT_DELAY);
    		}
    		
    		if (error){
    			showErrorsBox(errorsMessage);
    		} else {
    			if (getAppModel().saveFile(listModel.getData(), Integer.valueOf(repeatCount), repeatUntilStop, Integer.valueOf(delayAfter), path)){
    				showMessageBox(com.perso.mouseclicker.util.Text.MESSAGE_BOX_FILE_SAVED.replace("?", path));
    			}
    		}
    		
    	}
	}
	
	private void updateSelectedRow(){
	 	ActionListModel listModel = getActionListModel();
	 	
	 	int selectedRow = getMainView().getListActionPanel().getListAction().getSelectedRow();
	 	// get selected action to update
	 	ActionBean selectedAction = listModel.getSelectedActionBean(selectedRow);
	 	
	 	try{
	 		// get new action
	 		ActionBean newAction = getCurrentAction();
			// set new params
		 	selectedAction.setCoordX(newAction.getCoordX());
		 	selectedAction.setCoordY(newAction.getCoordY());
		 	selectedAction.setAction(newAction.getAction());
		 	selectedAction.setRepeat(newAction.getRepeat());
		 	selectedAction.setMinDelay(newAction.getMinDelay());
		 	selectedAction.setMaxDelay(newAction.getMaxDelay());
		 	selectedAction.setComment(newAction.getComment());
		 	// refresh updated action
		 	listModel.fireTableRowsUpdated(selectedRow, selectedRow);
		} catch(ListIllegalArgumentException e){
			showErrorsBox(e.getListErrors());
		}
	 	
	}
	
	private void loadData(){
		if (getLoadFileChooser().show(getMainView().getMainFrame()) == JFileChooser.APPROVE_OPTION){
    		ActionListModel listModel = getActionListModel();

    		FileBean fileBean = getAppModel().loadFile(getLoadFileChooser().getFile());
    		
    		listModel.setData(fileBean.getListAction());
    		
    		getMainView().getScriptPanel().getRepeatTextField().setText(String.valueOf(fileBean.getRepeat()));
    		getMainView().getScriptPanel().getRepeatCheckBox().setSelected(fileBean.isRepeatUntilStop());
    	}
	}
	
	private ActionBean getCurrentAction() throws ListIllegalArgumentException{
		
		boolean error = false;
		ArrayList<String> listErrors = new ArrayList<String>();
		
		int id = getMainView().getListActionPanel().getActionListModel().getNewId();

		String coordXString = getMainView().getEditActionPanel().getxTextField().getText();
		String coordYString = getMainView().getEditActionPanel().getyTextField().getText();
		
		int coordX = 0;
		if (com.perso.mouseclicker.util.Method.isPositiveInteger(coordXString)){
			coordX = Integer.valueOf(coordXString) ;
		} else {
			error = true;
			listErrors.add(" Coord x must be a positive integer ");
		}
		
		int coordY = 0;
		if (com.perso.mouseclicker.util.Method.isPositiveInteger(coordYString)){
			coordY = Integer.valueOf(coordYString) ;
		} else {
			error = true;
			listErrors.add(" Coord y must be a positive integer ");
		}
		
		ActionEnum action = (ActionEnum) getMainView().getEditActionPanel().getActionComboxBox().getSelectedItem();
	
    	int minDelay = com.perso.mouseclicker.util.Config.MIN_DELAY_VALUE;
    	String minDelayString = getMainView().getEditActionPanel().getMinDelayTextField().getText();
    	if (com.perso.mouseclicker.util.Method.isPositiveInteger(minDelayString)){
    		minDelay = Integer.valueOf(minDelayString) ;
    	} else {
    		error = true;
			listErrors.add("Min delay must be a positive integer");
    	}
    	
    	int maxDelay = com.perso.mouseclicker.util.Config.MAX_DELAY_VALUE;
    	String maxDelayString = getMainView().getEditActionPanel().getMaxDelayTextField().getText();
    	if (com.perso.mouseclicker.util.Method.isPositiveInteger(maxDelayString)){
    		maxDelay = Integer.valueOf(maxDelayString) ;
    	} else {
    		error = true;
			listErrors.add("Max delay must be a positive integer");
    	}
    	
    	int repeat = com.perso.mouseclicker.util.Config.REPEAT_ACTION_VALUE;
    	String repeatString = getMainView().getEditActionPanel().getRepeatTextField().getText();
    	if (com.perso.mouseclicker.util.Method.isPositiveInteger(repeatString)){
    		repeat = Integer.valueOf(getMainView().getEditActionPanel().getRepeatTextField().getText());
    	} else {
    		error = true;
			listErrors.add("Repeat must be a positive integer");
    	}
    	
    	String comment = getMainView().getEditActionPanel().getCommentTextField().getText();
    	
    	if ( error ){
    		throw new ListIllegalArgumentException("Some arguments are invalid",listErrors);
    	}
    	
		return new ActionBean(id, coordX, coordY, action, minDelay, maxDelay, repeat, comment);
	}
	
	/**
	 * Update action box with actionBean in param
	 * @param selectedActionBean
	 */
	public void updateCurrentAction(ActionBean actionBean) {
		
		if ( actionBean.getCoordX() == com.perso.mouseclicker.util.Config.COORD_X_NULL ){
			getMainView().getEditActionPanel().getxTextField().setText(com.perso.mouseclicker.util.Config.COORD_X_DEFAULT_STRING);
		} else {
			getMainView().getEditActionPanel().getxTextField().setText(Integer.toString(actionBean.getCoordX()));
		}
		
		if ( actionBean.getCoordY() == com.perso.mouseclicker.util.Config.COORD_Y_NULL ){
			getMainView().getEditActionPanel().getyTextField().setText(com.perso.mouseclicker.util.Config.COORD_Y_DEFAULT_STRING);
		} else {
			getMainView().getEditActionPanel().getyTextField().setText(Integer.toString(actionBean.getCoordY()));
		}
		
		getMainView().getEditActionPanel().getActionComboxBox().setSelectedItem(actionBean.getAction());
		getMainView().getEditActionPanel().getRepeatTextField().setText(Integer.toString(actionBean.getRepeat()));
		getMainView().getEditActionPanel().getMinDelayTextField().setText(Integer.toString(actionBean.getMinDelay()));
		getMainView().getEditActionPanel().getMaxDelayTextField().setText(Integer.toString(actionBean.getMaxDelay()));
		getMainView().getEditActionPanel().getCommentTextField().setText(actionBean.getComment());
	}
	
	public MouseClickerWindow getMainView() {
		return mainView;
	}

	public void setMainView(MouseClickerWindow mainView) {
		this.mainView = mainView;
	}

	public PickLayer getPickLayer() {
		if( pickLayer == null ){
			setPickLayer( new PickLayer(this));
		}
		return pickLayer;
	}

	public void setPickLayer(PickLayer pickLayer) {
		this.pickLayer = pickLayer;
	}
	
	public void hidePickLayer(){
		getPickLayer().hide();
	}
	
	public void showView() {
		getMainView().show();
	}

	public LoadFileChooser getLoadFileChooser() {
		if (loadFileChooser == null){
			setLoadFileChooser(new LoadFileChooser());
		}
		return loadFileChooser;
	}

	public void setLoadFileChooser(LoadFileChooser loadFileChooser) {
		this.loadFileChooser = loadFileChooser;
	}
	

	public SaveFileChooser getSaveFileChooser() {
		if (saveFileChooser == null){
			setSaveFileChooser(new SaveFileChooser());
		}
		return saveFileChooser;
	}

	public void setSaveFileChooser(SaveFileChooser saveFileChooser) {
		this.saveFileChooser = saveFileChooser;
	}
	
	public ManageFileModel getAppModel() {
		return appModel;
	}

	public void setAppModel(ManageFileModel appModel) {
		this.appModel = appModel;
	}
	
	private Worker getWorker(boolean forceNew){
		if ( worker == null || forceNew){
			ArrayList<ActionBean> clone = new ArrayList<ActionBean>();
			for(ActionBean actionBean:getActionListModel().getData()){
				clone.add(ActionBean.cloneActionBean(actionBean));
			}
			worker = new Worker(clone, this);
		}
		return worker;
	}

	public void restoreView() {
		getMainView().getMainFrame().setExtendedState(JFrame.NORMAL);
		getMainView().getScriptPanel().getStart().setEnabled(true);
		getMainView().getScriptPanel().getStop().setEnabled(false);
	}

	public void beforeScriptLaunched() {
		getMainView().getScriptPanel().getStart().setEnabled(false);
		getMainView().getScriptPanel().getStop().setEnabled(true);
		getMainView().getMainFrame().setExtendedState(JFrame.ICONIFIED);
	}

	public void manageScriptRepeat() {
		if ( getMainView().getScriptPanel().getRepeatCheckBox().isSelected()){
			getMainView().getScriptPanel().getRepeatTextField().setEnabled(false);
		} else {
			getMainView().getScriptPanel().getRepeatTextField().setEnabled(true);
		}
	}

}