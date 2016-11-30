package com.perso.mouseclicker.views.clicker;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.perso.mouseclicker.controller.AppController;
import com.perso.mouseclicker.listeners.MouseAdapterPick;

public class PickLayer {

	private JFrame layerFrame;
	
	private AppController controller;
	
	public PickLayer(AppController controller){
		this.controller = controller;
		createLayer();
	}
	
	private void createLayer(){

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		layerFrame = new JFrame();
		layerFrame.setSize(screenSize);
		layerFrame.setUndecorated(true);
		layerFrame.setOpacity(0.5f);
		
		layerFrame.addMouseListener(new MouseAdapterPick(controller));
		
		layerFrame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		
	}

	public void show() {
		layerFrame.setVisible(true);
	}
	
	public void hide() {
		layerFrame.setVisible(false);
	}
}
