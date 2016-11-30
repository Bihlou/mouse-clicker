package com.perso.mouseclicker.run;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingWorker;

import com.perso.mouseclicker.beans.ActionBean;
import com.perso.mouseclicker.controller.AppController;
import com.perso.mouseclicker.util.ActionEnum;

public class Worker extends SwingWorker<Boolean, Void> {

	private AppController controller;
	private boolean run;
	private boolean repeat = false;
	private int repeatNumber = 0;
	private int delayAfter = 0;
	ArrayList<ActionBean> listAction;

	public Worker(ArrayList<ActionBean> listAction, AppController controller){
		super();
		this.listAction = listAction;
		this.controller = controller;
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {
		run = true;
		while (run){
				if (listAction != null && listAction.size() > 0){
					try {
						Robot robot = new Robot();
						
						for(ActionBean action : listAction){
		
				            int minDelay = action.getMinDelay();
				            int maxDelay = action.getMaxDelay();
				            int delay = maxDelay - minDelay;
				            
				            if (delay > 0){
					            Random random = new Random();
					            minDelay = minDelay + random.nextInt(Integer.MAX_VALUE) % delay;	
				            }
				            
							for (int repeat = 0; repeat < action.getRepeat();repeat ++){
								Thread.sleep(minDelay);
								robot.mouseMove(action.getCoordX(), action.getCoordY());
								
								if (action.getAction().equals(ActionEnum.LEFT_CLICK)){
									leftClick(robot);	
								} else if (action.getAction().equals(ActionEnum.RIGHT_CLICK)){
									rightClick(robot);
								}
							}
						}
						
						if (!repeat){
							repeatNumber --;
						}
						
					} catch (InterruptedException e) {
						//case when worker is stop during Thread.sleep()
						break;
					}catch (AWTException e1) {
						e1.printStackTrace();
					}
				} else {
					run = false;
				}
				
			
			if ( !repeat && repeatNumber <= 0 ){
				run = false;
			}
			
			try {
				if (delayAfter > 0){
					Thread.sleep(delayAfter);
				}
			} catch (InterruptedException e) {
				//case when worker is stop during Thread.sleep()
				break;
			}
			
		}
		
		controller.restoreView();
		
		return true;
	}
	
	private void leftClick(Robot robot){
		robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	private void rightClick(Robot robot){
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public int getRepeatNumber() {
		return repeatNumber;
	}

	public void setRepeatNumber(int repeatNumber) {
		this.repeatNumber = repeatNumber;
	}

	public int getDelayAfter() {
		return delayAfter;
	}

	public void setDelayAfter(int delayAfter) {
		this.delayAfter = delayAfter;
	}

}
