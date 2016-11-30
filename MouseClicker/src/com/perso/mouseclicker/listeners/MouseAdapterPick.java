package com.perso.mouseclicker.listeners;

import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.perso.mouseclicker.controller.AppController;

public class MouseAdapterPick extends MouseAdapter {

	AppController controller;
	
	public MouseAdapterPick(AppController controller) {
		super();
		this.controller = controller;
	}
	
	public void mouseClicked(MouseEvent e) {
        controller.updateCoord(MouseInfo.getPointerInfo().getLocation());
        controller.hidePickLayer();
        controller.showView();
    }

}
