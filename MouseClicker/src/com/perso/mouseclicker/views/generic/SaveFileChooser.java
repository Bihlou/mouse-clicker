package com.perso.mouseclicker.views.generic;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveFileChooser extends JFileChooser {

	private static final long serialVersionUID = -6028253358094052288L;

	public SaveFileChooser(){
		super();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files", "xml");
		setFileFilter(filter);
	}
	
	public int show(JFrame frame){
		return this.showSaveDialog(frame);
	}
	
	public File getFile(){
		return this.getSelectedFile();
	}
}
