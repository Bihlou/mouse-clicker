package com.perso.mouseclicker.models;

import java.io.File;
import java.util.ArrayList;

import com.perso.mouseclicker.beans.ActionBean;
import com.perso.mouseclicker.beans.FileBean;

public interface ManageFileModel {

	boolean saveFile(ArrayList<ActionBean> listToSave, int repeatCount, boolean repeatUntilStop, int delayAfter, String path);
	FileBean loadFile(File file);
		
}
