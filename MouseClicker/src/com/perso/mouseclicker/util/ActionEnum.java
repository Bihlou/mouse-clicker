package com.perso.mouseclicker.util;

public enum ActionEnum {
	LEFT_CLICK(com.perso.mouseclicker.util.Text.ACTION_LEFT_CLICK), RIGHT_CLICK(com.perso.mouseclicker.util.Text.ACTION_RIGHT_CLICK);
	
	private final String label;
	
	ActionEnum(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public static ActionEnum getActionByLabel(String label){
		if(com.perso.mouseclicker.util.Text.ACTION_LEFT_CLICK.equals(label)){
			return ActionEnum.LEFT_CLICK;
		} else if(com.perso.mouseclicker.util.Text.ACTION_LEFT_CLICK.equals(label)){
			return ActionEnum.RIGHT_CLICK;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
       return this.label;
    }
	
	
}
