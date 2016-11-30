package com.perso.mouseclicker.views.generic;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadFileChooser extends JFileChooser {

	private static final long serialVersionUID = -8755274627925897373L;
	
	public LoadFileChooser(){
		super();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files", "xml");
		setFileFilter(filter);
	}
	
	public int show(JFrame frame){
		return this.showOpenDialog(frame);
	}
	
	public File getFile(){
		return this.getSelectedFile();
	}
}
