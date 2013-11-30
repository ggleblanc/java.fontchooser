package com.ggl.fontchooser.controller;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

public class SelectionListener implements ListSelectionListener {

	private FieldListener fieldListener;

	private JList list;

	private JTextField textField;

	public void setFieldListener(FieldListener fieldListener) {
		this.fieldListener = fieldListener;
	}

	public void setList(JList list) {
		this.list = list;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		setFieldValue();
	}

	private void setFieldValue() {
		Document document = textField.getDocument();
		document.removeDocumentListener(fieldListener);
		Object object = list.getSelectedValue();
		if (object != null) {
			textField.setText(object.toString());
		}
		document.addDocumentListener(fieldListener);
	}

}