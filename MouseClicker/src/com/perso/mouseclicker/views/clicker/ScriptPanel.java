package com.perso.mouseclicker.views.clicker;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.perso.mouseclicker.controller.AppController;
import com.perso.mouseclicker.listeners.ButtonClickListener;
import com.perso.mouseclicker.listeners.RepeatCheckBoxActionListener;
import com.perso.mouseclicker.views.generic.TitlePanel;

public class ScriptPanel extends TitlePanel {

	private static final long serialVersionUID = 980200185624617233L;

	private JLabel repeatLabel;
	private JTextField repeatTextField;
	private JLabel repeatUntilStop;
	private JCheckBox repeatCheckBox;
	private JLabel delayAfterLabel;
	private JTextField delayAfterTextField;
	private JButton start;
	private JButton stop;
	private ButtonClickListener buttonClickListener;
	private AppController appController;
	
	private SpringLayout springLayout;
	
	public ScriptPanel(AppController appController, ButtonClickListener buttonClickListener) {
		super(com.perso.mouseclicker.util.Text.SCRIPT);
		this.appController = appController;
		this.buttonClickListener = buttonClickListener;
		init();
	}
	
	private void init(){
		this.setPreferredSize(new Dimension(900,50));
		this.setMinimumSize(new Dimension(900,50));
		springLayout = new SpringLayout();
		this.setLayout(springLayout);
		
		repeatLabel = new JLabel(com.perso.mouseclicker.util.Text.REPEAT);
		repeatTextField = new JTextField(5);
		repeatTextField.setText(String.valueOf(com.perso.mouseclicker.util.Config.REPEAT_SCRIPT_VALUE));
		repeatUntilStop = new JLabel(com.perso.mouseclicker.util.Text.REPEAT_STOP);
		repeatCheckBox = new JCheckBox();
		delayAfterLabel = new JLabel(com.perso.mouseclicker.util.Text.SCRIPT_DELAY_AFTER);
		delayAfterTextField = new JTextField(5);
		delayAfterTextField.setText(String.valueOf(com.perso.mouseclicker.util.Config.SCRIPT_DELAY_AFTER));
		
		repeatCheckBox.addActionListener(new RepeatCheckBoxActionListener(appController));
		
		start = new JButton(com.perso.mouseclicker.util.Text.START);
		start.addActionListener(buttonClickListener);
		stop = new JButton(com.perso.mouseclicker.util.Text.STOP);
		stop.setEnabled(false);
		stop.addActionListener(buttonClickListener);

		springLayout.putConstraint(SpringLayout.WEST, repeatLabel, 5, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.NORTH, repeatLabel, 5, SpringLayout.NORTH, this);

		springLayout.putConstraint(SpringLayout.WEST, repeatTextField, 5, SpringLayout.EAST, repeatLabel);
		springLayout.putConstraint(SpringLayout.NORTH, repeatTextField, 2, SpringLayout.NORTH, this);
		
		springLayout.putConstraint(SpringLayout.WEST, repeatUntilStop, 5, SpringLayout.EAST, repeatTextField);
		springLayout.putConstraint(SpringLayout.NORTH, repeatUntilStop, 5, SpringLayout.NORTH, this);
		
		springLayout.putConstraint(SpringLayout.WEST, repeatCheckBox, 5, SpringLayout.EAST, repeatUntilStop);
		springLayout.putConstraint(SpringLayout.NORTH, repeatCheckBox, 2, SpringLayout.NORTH, this);

		springLayout.putConstraint(SpringLayout.WEST, delayAfterLabel, 5, SpringLayout.EAST, repeatCheckBox);
		springLayout.putConstraint(SpringLayout.NORTH, delayAfterLabel, 5, SpringLayout.NORTH, this);

		springLayout.putConstraint(SpringLayout.WEST, delayAfterTextField, 5, SpringLayout.EAST, delayAfterLabel);
		springLayout.putConstraint(SpringLayout.NORTH, delayAfterTextField, 2, SpringLayout.NORTH, this);

		springLayout.putConstraint(SpringLayout.EAST, start, 5, SpringLayout.WEST, stop);		
		springLayout.putConstraint(SpringLayout.EAST, stop, 5, SpringLayout.EAST, this);
		
		this.add(repeatLabel);
		this.add(repeatTextField);
		this.add(repeatUntilStop);
		this.add(repeatCheckBox);
		this.add(delayAfterLabel);
		this.add(delayAfterTextField);
		
		this.add(start);
		this.add(stop);
		
	}
	
	@Override
	protected void setLayout(){
		this.setLayout(new GridBagLayout());
	}

	public JTextField getRepeatTextField() {
		return repeatTextField;
	}
	
	public void setRepeatTextField(JTextField repeatTextField) {
		this.repeatTextField = repeatTextField;
	}
	
	public JTextField getDelayAfterTextField() {
		return delayAfterTextField;
	}

	public void setDelayAfterTextField(JTextField delayAfterTextField) {
		this.delayAfterTextField = delayAfterTextField;
	}

	public JCheckBox getRepeatCheckBox() {
		return repeatCheckBox;
	}

	public void setRepeatCheckBox(JCheckBox repeatCheckBox) {
		this.repeatCheckBox = repeatCheckBox;
	}

	public JButton getStart() {
		return start;
	}

	public void setStart(JButton start) {
		this.start = start;
	}

	public JButton getStop() {
		return stop;
	}

	public void setStop(JButton stop) {
		this.stop = stop;
	}
	
	
	
}
