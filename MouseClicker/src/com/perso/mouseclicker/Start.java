package com.perso.mouseclicker;

import com.perso.mouseclicker.controller.AppController;
import com.perso.mouseclicker.models.ManageFileModel;
import com.perso.mouseclicker.models.ManageFileModelXml;
import com.perso.mouseclicker.views.clicker.MouseClickerWindow;

public class Start {

	public static void main(String[] args) {
		StartApp();
	}
	
	private static void StartApp(){
		//test commit
		AppController controller = new AppController();
		
		MouseClickerWindow view = new MouseClickerWindow(controller);
		
		ManageFileModel model = new ManageFileModelXml();
		
		controller.addView(view);
		controller.addModel(model);
		controller.startApp();
	}

}
