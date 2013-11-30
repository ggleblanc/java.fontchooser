package com.ggl.fontchooser.controller;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.ggl.fontchooser.model.ComparisonResult;

public class FieldListener implements DocumentListener {

	private JList list;

	private JTextField textField;

	private ListModel model;

	private SelectionListener selectionListener;

	public void setList(JList list) {
		this.list = list;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public void setModel(ListModel model) {
		this.model = model;
	}

	public void setSelectionListener(SelectionListener selectionListener) {
		this.selectionListener = selectionListener;
	}

	@Override
	public void changedUpdate(DocumentEvent event) {
	}

	@Override
	public void insertUpdate(DocumentEvent event) {
		setListSelection(event);
	}

	@Override
	public void removeUpdate(DocumentEvent event) {
		setListSelection(event);
	}

	private void setListSelection(DocumentEvent event) {
		Document document = textField.getDocument();
		try {
			String s = document.getText(0, document.getLength());
			if (s.equals("")) {
				list.clearSelection();
				list.ensureIndexIsVisible(0);
			} else {
				ComparisonResult result = searchModel(s);
				list.removeListSelectionListener(selectionListener);
				list.ensureIndexIsVisible(model.getSize() - 1);
				if (result.isExactMatch()) {
					list.setSelectedIndex(result.getListIndex());
				} else {
					list.clearSelection();
					list.ensureIndexIsVisible(result.getListIndex());
				}
				list.addListSelectionListener(selectionListener);
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private ComparisonResult searchModel(String searchTerm) {
		for (int i = 0; i < model.getSize(); i++) {
			Object object = model.getElementAt(i);
			String s = object.toString().toLowerCase();
			String t = searchTerm.toLowerCase();
			if (s.equals(t)) {
				return new ComparisonResult(i, true);
			} else if (s.startsWith(t)) {
				return new ComparisonResult(i, false);
			}
		}
		return new ComparisonResult(0, false);
	}

}
