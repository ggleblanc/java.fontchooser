package com.ggl.fontchooser.controller;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class BorderListCellRenderer implements ListCellRenderer {

	private Border insetBorder;

	private DefaultListCellRenderer defaultRenderer;

	public BorderListCellRenderer(int rightMargin) {
		this.insetBorder = new EmptyBorder(0, 2, 0, rightMargin);
		this.defaultRenderer = new DefaultListCellRenderer();
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultRenderer
				.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
		renderer.setBorder(insetBorder);
		return renderer;
	}

}
