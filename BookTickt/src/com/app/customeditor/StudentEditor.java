package com.app.customeditor;

import java.beans.PropertyEditorSupport;

public class StudentEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(text.startsWith("mr.") || text.startsWith("ms."))
		{
			setValue(text);
		}
		else {
			text="ms."+text;
			setValue(text);
		}
	}

}
