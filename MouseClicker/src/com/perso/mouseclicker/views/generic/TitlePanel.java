package com.perso.mouseclicker.views.generic;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class TitlePanel extends JPanel{

	private static final long serialVersionUID = -2691376494223198528L;
	
	private TitledBorder titleBorder;
	
	public TitlePanel(String title){
		init(title);
	}
	
	private void init(String title){
		titleBorder = new TitledBorder(title);
		setLayout();
		this.setBorder(titleBorder);
	}
	
	protected void setLayout(){
		this.setLayout(new GridBagLayout());
	}

}
