package com.perso.mouseclicker.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.perso.mouseclicker.controller.AppController;

public class ButtonClickListener implements ActionListener {
	
	private AppController controller;
	
	public ButtonClickListener(AppController controller) {
		this.controller = controller;
	}

	public void actionPerformed(ActionEvent e) {
		controller.treatActionButton(e);
    }		
}
