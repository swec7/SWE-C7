package ui;

import javafx.scene.control.Label;

public class WrapLabel extends Label{

	public WrapLabel(){
		super();
		this.setWrapText(true);
	}
	
	public WrapLabel(String text){
		super(text);
		this.setWrapText(true);
	}
	
	
}
