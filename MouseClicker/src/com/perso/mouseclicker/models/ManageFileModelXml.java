package com.perso.mouseclicker.models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.perso.mouseclicker.beans.ActionBean;
import com.perso.mouseclicker.beans.FileBean;

public class ManageFileModelXml implements ManageFileModel{
	
	public ManageFileModelXml(){}
	
	public boolean saveFile(ArrayList<ActionBean> listToSave, int repeatCount, boolean repeatUntilStop, int delayAfter, String path){
		
	  try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(com.perso.mouseclicker.util.Xml.ACTIONS);
			doc.appendChild(rootElement);

			for (ActionBean actionBean : listToSave){
				
				Element action = doc.createElement(com.perso.mouseclicker.util.Xml.ACTION);
				rootElement.appendChild(action);

				Element id = doc.createElement(com.perso.mouseclicker.util.Xml.ID);
				id.appendChild(doc.createTextNode(String.valueOf(actionBean.getId())));
				action.appendChild(id);

				Element coordX = doc.createElement(com.perso.mouseclicker.util.Xml.COORD_X);
				coordX.appendChild(doc.createTextNode(String.valueOf(actionBean.getCoordX())));
				action.appendChild(coordX);
				
				Element coordY = doc.createElement(com.perso.mouseclicker.util.Xml.COORD_Y);
				coordY.appendChild(doc.createTextNode(String.valueOf(actionBean.getCoordY())));
				action.appendChild(coordY);

				Element actionDetail = doc.createElement(com.perso.mouseclicker.util.Xml.ACTION_DETAIL);
				actionDetail.appendChild(doc.createTextNode(actionBean.getAction().getLabel()));
				action.appendChild(actionDetail);
				
				Element minDelay = doc.createElement(com.perso.mouseclicker.util.Xml.MIN_DELAY);
				minDelay.appendChild(doc.createTextNode(String.valueOf(actionBean.getMinDelay())));
				action.appendChild(minDelay);

				Element maxDelay = doc.createElement(com.perso.mouseclicker.util.Xml.MAX_DELAY);
				maxDelay.appendChild(doc.createTextNode(String.valueOf(actionBean.getMaxDelay())));
				action.appendChild(maxDelay);

				Element repeat = doc.createElement(com.perso.mouseclicker.util.Xml.REPEAT);
				repeat.appendChild(doc.createTextNode(String.valueOf(actionBean.getRepeat())));
				action.appendChild(repeat);

				Element comment = doc.createElement(com.perso.mouseclicker.util.Xml.COMMENT);
				comment.appendChild(doc.createTextNode(String.valueOf(actionBean.getComment())));
				action.appendChild(comment);
				
			}
			
			
			Element repeatElement = doc.createElement(com.perso.mouseclicker.util.Xml.REPEAT_SCRIPT);
			repeatElement.setAttribute(com.perso.mouseclicker.util.Xml.REPEAT_COUNT, String.valueOf(repeatCount));
			repeatElement.setAttribute(com.perso.mouseclicker.util.Xml.REPEAT_UNITL_STOP, String.valueOf(repeatUntilStop));
			repeatElement.setAttribute(com.perso.mouseclicker.util.Xml.DELAY_AFTER, String.valueOf(delayAfter));
			
			rootElement.appendChild(repeatElement);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));

			transformer.transform(source, result);

			return true;

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
	  	  return false;
		}

	@Override
	public FileBean loadFile(File file) {
	    ArrayList<ActionBean> listActions = null;
		boolean repeatUntilStop = com.perso.mouseclicker.util.Config.REPEAT_SCRIPT_BOOLEAN;
		int repeat = com.perso.mouseclicker.util.Config.REPEAT_SCRIPT_VALUE;
		int delayAfter = com.perso.mouseclicker.util.Config.SCRIPT_DELAY_AFTER;
		
		try {
		    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = docFactory.newDocumentBuilder();
		    Document doc = db.parse(file);
			    
		    doc.getDocumentElement().normalize();
		    
		    //get list action
		    NodeList nList = doc.getElementsByTagName(com.perso.mouseclicker.util.Xml.ACTION);
		    listActions = new ArrayList<ActionBean>();
		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		    	
		    	Node nNode = nList.item(temp);
		    	
		    	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			    	ActionBean action = new ActionBean();
		    		
					Element eElement = (Element) nNode;
					
					action.setId(Integer.parseInt(eElement.getElementsByTagName(com.perso.mouseclicker.util.Xml.ID).item(0).getTextContent()));
					action.setCoordX(Integer.parseInt((eElement.getElementsByTagName(com.perso.mouseclicker.util.Xml.COORD_X).item(0).getTextContent())));
					action.setCoordY(Integer.parseInt((eElement.getElementsByTagName(com.perso.mouseclicker.util.Xml.COORD_Y).item(0).getTextContent())));
					action.setAction(com.perso.mouseclicker.util.ActionEnum.getActionByLabel(eElement.getElementsByTagName(com.perso.mouseclicker.util.Xml.ACTION_DETAIL).item(0).getTextContent()));
					action.setMinDelay(Integer.parseInt(eElement.getElementsByTagName(com.perso.mouseclicker.util.Xml.MIN_DELAY).item(0).getTextContent()));
					action.setMaxDelay(Integer.parseInt(eElement.getElementsByTagName(com.perso.mouseclicker.util.Xml.MAX_DELAY).item(0).getTextContent()));
					action.setRepeat(Integer.parseInt(eElement.getElementsByTagName(com.perso.mouseclicker.util.Xml.REPEAT).item(0).getTextContent()));
					action.setComment(eElement.getElementsByTagName(com.perso.mouseclicker.util.Xml.COMMENT).item(0).getTextContent());
					
					
					listActions.add(action);
				}
		    	
		    }
		    
		    //get repeat value and repeat until stop
		    NodeList nRepeatList = doc.getElementsByTagName(com.perso.mouseclicker.util.Xml.REPEAT_SCRIPT);
		    
		    
		    if (nRepeatList.getLength() == 1){
			    Node repeatNode = nRepeatList.item(0);
			    if (repeatNode.getNodeType() == Node.ELEMENT_NODE) {
			    	
			    	Element eElement = (Element) repeatNode;
			    	repeatUntilStop = Boolean.valueOf(eElement.getAttribute(com.perso.mouseclicker.util.Xml.REPEAT_UNITL_STOP));
			    	repeat = Integer.valueOf(eElement.getAttribute(com.perso.mouseclicker.util.Xml.REPEAT_COUNT));
			    	delayAfter = Integer.valueOf(eElement.getAttribute(com.perso.mouseclicker.util.Xml.DELAY_AFTER));
			    }
		    }
		    
		    
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
		return new FileBean(listActions, repeatUntilStop, repeat, delayAfter);
	}

}
