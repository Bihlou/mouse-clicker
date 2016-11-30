package com.perso.mouseclicker.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.perso.mouseclicker.controller.AppController;

public class RepeatCheckBoxActionListener implements ActionListener {

	private AppController controller;
	
	public RepeatCheckBoxActionListener(AppController controller){
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.manageScriptRepeat();
	}

}
