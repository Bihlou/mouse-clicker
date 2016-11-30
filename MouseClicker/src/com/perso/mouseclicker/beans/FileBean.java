package com.perso.mouseclicker.beans;

import java.util.ArrayList;

public class FileBean {

	private ArrayList<ActionBean> listAction;
	private boolean repeatUntilStop;
	private int repeat;
	private int delayAfter;
	
	public FileBean(ArrayList<ActionBean> listAction, boolean repeatUntilStop, int repeat, int delayAfter){
		this.listAction = listAction;
		this.repeatUntilStop = repeatUntilStop;
		this.repeat = repeat;
		this.delayAfter = delayAfter;
	}

	public ArrayList<ActionBean> getListAction() {
		return listAction;
	}

	public void setListAction(ArrayList<ActionBean> listAction) {
		this.listAction = listAction;
	}

	public boolean isRepeatUntilStop() {
		return repeatUntilStop;
	}

	public void setRepeatUntilStop(boolean repeatUntilStop) {
		this.repeatUntilStop = repeatUntilStop;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	public int getDelayAfter() {
		return delayAfter;
	}

	public void setDelayAfter(int delayAfter) {
		this.delayAfter = delayAfter;
	}
	
	

}
