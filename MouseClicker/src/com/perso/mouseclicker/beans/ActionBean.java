package com.perso.mouseclicker.beans;

import java.io.Serializable;

import com.perso.mouseclicker.util.ActionEnum;

public class ActionBean implements Comparable<ActionBean>, Serializable {

	private static final long serialVersionUID = 3387751976238646483L;
	
	private int id;
	private int coordX;
	private int coordY;
	//@TODO udpate to enum list
	private ActionEnum action;
	private int minDelay;
	private int maxDelay;
	private int repeat;
	private String comment;
	
	public ActionBean(){
		coordX = -1;
		coordY = -1;
		action = ActionEnum.LEFT_CLICK;
		minDelay = com.perso.mouseclicker.util.Config.MIN_DELAY_VALUE;
		maxDelay = com.perso.mouseclicker.util.Config.MAX_DELAY_VALUE;
		repeat = com.perso.mouseclicker.util.Config.REPEAT_ACTION_VALUE;
		comment = "";
	};
	
	public ActionBean(int id, int coordX2, int coordY2, ActionEnum action, int minDelay, int maxDelay, int repeat, String comment) {
		this.id = id;
		this.coordX = coordX2;
		this.coordY = coordY2;
		this.action = action;
		this.minDelay = minDelay;
		this.maxDelay = maxDelay;
		this.repeat = repeat;
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		if (coordX < 0.0){
			throw new IllegalArgumentException("Coord X outside screen");
		}
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		if (coordY < 0.0){
			throw new IllegalArgumentException("Coord Y outside screen");
		}
		this.coordY = coordY;
	}
	public ActionEnum getAction() {
		return action;
	}
	public void setAction(ActionEnum action) {
		this.action = action;
	}
	public int getMinDelay() {
		return minDelay;
	}
	public void setMinDelay(int minDelay) {
		this.minDelay = minDelay;
	}
	public void setMaxDelay(int maxDelay) {
		this.maxDelay = maxDelay;
	}
	public int getMaxDelay() {
		return maxDelay;
	}
	public int getRepeat() {
		return repeat;
	}
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int compareTo(ActionBean o) {
		return Integer.compare(this.getId(), o.getId());
	}
	
	public Object Clone(Object a){
		return a;
		
	}

	public static ActionBean cloneActionBean(ActionBean a) {
		return new ActionBean(a.getId(), a.getCoordX(), a.getCoordY(), a.getAction(), a.getMinDelay(),a.getMaxDelay(),a.getRepeat(),a.getComment());
	}
}
