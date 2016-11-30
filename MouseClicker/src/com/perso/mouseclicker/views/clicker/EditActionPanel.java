package com.perso.mouseclicker.views.clicker;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.perso.mouseclicker.listeners.ButtonClickListener;
import com.perso.mouseclicker.util.ActionEnum;
import com.perso.mouseclicker.views.generic.TitlePanel;

public class EditActionPanel extends TitlePanel {
	
	private static final long serialVersionUID = -6102321401827672389L;
	
	private JPanel xPanel;
	private JPanel yPanel;
	private JPanel actionPanel;
	private JPanel repeatPanel;
	private JPanel delayPanel;
	private JPanel commentPanel;

	private JTextField xTextField;
	private JTextField yTextField;
	private JTextField repeatTextField;
	private JTextField minDelayTextField;
	private JTextField maxDelayTextField;
	//@TODO replace by list action
	private JComboBox<ActionEnum> actionComboxBox;
	private JTextField commentTextField;
	
	private JLabel xLabel;
	private JLabel yLabel;
	private JLabel repeatLabel;
	private JLabel minDelayLabel;
	private JLabel maxDelayLabel;
	private JLabel actionLabel;
	private JLabel commentLabel;

	private JPanel buttonPanel;
	private JButton pickButton;
	private JButton addButton;
	private JButton updateButton;
	
	private ButtonClickListener buttonClickListener;
	
	public EditActionPanel(ButtonClickListener buttonClickListener) {
		super(com.perso.mouseclicker.util.Text.TITLE_ACTION);
		this.buttonClickListener = buttonClickListener;
		init();
	}

	private void init() {
		this.setPreferredSize(new Dimension(900,290));
		this.setMinimumSize(new Dimension(900,290));
		xLabel = new JLabel(com.perso.mouseclicker.util.Text.X);
		xTextField = new JTextField(5);
		xTextField.setText(com.perso.mouseclicker.util.Config.COORD_X_DEFAULT_STRING);
		pickButton = new JButton(com.perso.mouseclicker.util.Text.PICK);
	    pickButton.setActionCommand(com.perso.mouseclicker.util.Text.PICK);
	    pickButton.addActionListener(buttonClickListener);
	    
		yLabel = new JLabel(com.perso.mouseclicker.util.Text.Y);
		yTextField = new JTextField(5);
		yTextField.setText(com.perso.mouseclicker.util.Config.COORD_Y_DEFAULT_STRING);
		
		actionLabel = new JLabel(com.perso.mouseclicker.util.Text.ACTION);		
		actionComboxBox = new JComboBox<ActionEnum>(ActionEnum.values());

		repeatLabel = new JLabel(com.perso.mouseclicker.util.Text.REPEAT);
		repeatTextField = new JTextField(5);
		repeatTextField.setText(String.valueOf(com.perso.mouseclicker.util.Config.REPEAT_ACTION_VALUE));
		
		minDelayLabel = new JLabel(com.perso.mouseclicker.util.Text.MIN_DELAY);
		minDelayTextField = new JTextField(5);
		minDelayTextField.setText(String.valueOf(com.perso.mouseclicker.util.Config.MIN_DELAY_VALUE));
		maxDelayLabel = new JLabel(com.perso.mouseclicker.util.Text.MAX_DELAY);
		maxDelayTextField = new JTextField(5);
		maxDelayTextField.setText(String.valueOf(com.perso.mouseclicker.util.Config.MAX_DELAY_VALUE));

		commentLabel = new JLabel(com.perso.mouseclicker.util.Text.COMMENT);
		commentTextField = new JTextField(20);
		commentTextField.setText(com.perso.mouseclicker.util.Config.COMMENT_DEFAULT_STRING);
		
		addButton = new JButton(com.perso.mouseclicker.util.Text.ADD);
		addButton.setActionCommand(com.perso.mouseclicker.util.Text.ADD);
		addButton.addActionListener(buttonClickListener);
		updateButton = new JButton(com.perso.mouseclicker.util.Text.UPDATE);
		updateButton.setActionCommand(com.perso.mouseclicker.util.Text.UPDATE);
		updateButton.addActionListener(buttonClickListener);

		build();
	}
	
	private void build(){
		xPanel = new JPanel();
		xPanel.add(xLabel);
		xPanel.add(xTextField);
		xPanel.add(pickButton);
		this.add(xPanel, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,1 ,0, 0, 0 ));
		
		yPanel = new JPanel();
		yPanel.add(yLabel);
		yPanel.add(yTextField);
		this.add(yPanel, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,1 ,0, 0, 1 ));
		
		actionPanel = new JPanel();
		actionPanel.add(actionLabel);
		actionPanel.add(actionComboxBox);
		this.add(actionPanel, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,1 ,0, 0, 2 ));
		
		repeatPanel = new JPanel();
		repeatPanel.add(repeatLabel);
		repeatPanel.add(repeatTextField);	
		this.add(repeatPanel, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,1 ,0, 0, 3 ));

		delayPanel = new JPanel();
		delayPanel.add(minDelayLabel);
		delayPanel.add(minDelayTextField);
		delayPanel.add(maxDelayLabel);
		delayPanel.add(maxDelayTextField);
		this.add(delayPanel, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,1 ,0, 0, 4 ));

		commentPanel = new JPanel();
		commentPanel.add(commentLabel);
		commentPanel.add(commentTextField);
		this.add(commentPanel, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHWEST,1 ,0, 0, 5 ));

		buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(updateButton);
		this.add(buttonPanel, com.perso.mouseclicker.util.Method.createGridConstraints(GridBagConstraints.NORTHEAST,1 , 1, 0, 6 ));
		
	}

	public JPanel getxPanel() {
		return xPanel;
	}

	public void setxPanel(JPanel xPanel) {
		this.xPanel = xPanel;
	}

	public JPanel getyPanel() {
		return yPanel;
	}

	public void setyPanel(JPanel yPanel) {
		this.yPanel = yPanel;
	}

	public JPanel getActionPanel() {
		return actionPanel;
	}

	public void setActionPanel(JPanel actionPanel) {
		this.actionPanel = actionPanel;
	}

	public JPanel getRepeatPanel() {
		return repeatPanel;
	}

	public void setRepeatPanel(JPanel repeatPanel) {
		this.repeatPanel = repeatPanel;
	}

	public JPanel getDelayPanel() {
		return delayPanel;
	}

	public void setDelayPanel(JPanel delayPanel) {
		this.delayPanel = delayPanel;
	}

	public JPanel getCommentPanel() {
		return commentPanel;
	}

	public void setCommentPanel(JPanel commentPanel) {
		this.commentPanel = commentPanel;
	}

	public JTextField getxTextField() {
		return xTextField;
	}

	public void setxTextField(JTextField xTextField) {
		this.xTextField = xTextField;
	}

	public JTextField getyTextField() {
		return yTextField;
	}

	public void setyTextField(JTextField yTextField) {
		this.yTextField = yTextField;
	}

	public JTextField getRepeatTextField() {
		return repeatTextField;
	}

	public void setRepeatTextField(JTextField repeatTextField) {
		this.repeatTextField = repeatTextField;
	}

	public JTextField getMinDelayTextField() {
		return minDelayTextField;
	}

	public void setMinDelayTextField(JTextField minDelayTextField) {
		this.minDelayTextField = minDelayTextField;
	}

	public JTextField getMaxDelayTextField() {
		return maxDelayTextField;
	}

	public void setMaxDelayTextField(JTextField maxDelayTextField) {
		this.maxDelayTextField = maxDelayTextField;
	}

	public JTextField getCommentTextField() {
		return commentTextField;
	}

	public void setCommentTextField(JTextField commentTextField) {
		this.commentTextField = commentTextField;
	}

	public JLabel getxLabel() {
		return xLabel;
	}

	public void setxLabel(JLabel xLabel) {
		this.xLabel = xLabel;
	}

	public JLabel getyLabel() {
		return yLabel;
	}

	public void setyLabel(JLabel yLabel) {
		this.yLabel = yLabel;
	}

	public JLabel getRepeatLabel() {
		return repeatLabel;
	}

	public void setRepeatLabel(JLabel repeatLabel) {
		this.repeatLabel = repeatLabel;
	}

	public JLabel getMinDelayLabel() {
		return minDelayLabel;
	}

	public void setMinDelayLabel(JLabel minDelayLabel) {
		this.minDelayLabel = minDelayLabel;
	}

	public JLabel getMaxDelayLabel() {
		return maxDelayLabel;
	}

	public void setMaxDelayLabel(JLabel maxDelayLabel) {
		this.maxDelayLabel = maxDelayLabel;
	}

	public JLabel getActionLabel() {
		return actionLabel;
	}

	public void setActionLabel(JLabel actionLabel) {
		this.actionLabel = actionLabel;
	}

	public JComboBox<ActionEnum> getActionComboxBox() {
		return actionComboxBox;
	}

	public void setActionComboxBox(JComboBox<ActionEnum> actionComboxBox) {
		this.actionComboxBox = actionComboxBox;
	}

	public JLabel getCommentLabel() {
		return commentLabel;
	}

	public void setCommentLabel(JLabel commentLabel) {
		this.commentLabel = commentLabel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JButton getPickButton() {
		return pickButton;
	}

	public void setPickButton(JButton pickButton) {
		this.pickButton = pickButton;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(JButton updateButton) {
		this.updateButton = updateButton;
	}

	public ButtonClickListener getButtonClickListener() {
		return buttonClickListener;
	}

	public void setButtonClickListener(ButtonClickListener buttonClickListener) {
		this.buttonClickListener = buttonClickListener;
	}
	
}
