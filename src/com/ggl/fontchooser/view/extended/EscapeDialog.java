package com.ggl.fontchooser.view.extended;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public class EscapeDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	protected JButton okButton;

	public EscapeDialog() {
		super();
	}

	public EscapeDialog(Dialog owner, boolean modal) {
		super(owner, modal);
	}

	public EscapeDialog(Dialog owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
	}

	public EscapeDialog(Dialog owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	public EscapeDialog(Dialog owner, String title) {
		super(owner, title);
	}

	public EscapeDialog(Dialog owner) {
		super(owner);
	}

	public EscapeDialog(Frame owner, boolean modal) {
		super(owner, modal);
	}

	public EscapeDialog(Frame owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
	}

	public EscapeDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	public EscapeDialog(Frame owner, String title) {
		super(owner, title);
	}

	public EscapeDialog(Frame owner) {
		super(owner);
	}

	public EscapeDialog(Window owner, ModalityType modalityType) {
		super(owner, modalityType);
	}

	public EscapeDialog(Window owner, String title, ModalityType modalityType,
			GraphicsConfiguration gc) {
		super(owner, title, modalityType, gc);
	}

	public EscapeDialog(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
	}

	public EscapeDialog(Window owner, String title) {
		super(owner, title);
	}

	public EscapeDialog(Window owner) {
		super(owner);
	}

	@Override
	protected JRootPane createRootPane() {
		JRootPane rootPane = new JRootPane();
		KeyStroke escapeStroke = KeyStroke.getKeyStroke("ESCAPE");

		Action escapeActionListener = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
			}
		};

		KeyStroke enterStroke = KeyStroke.getKeyStroke("ENTER");

		Action enterActionListener = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent event) {
				okButton.doClick();
			}
		};

		InputMap inputMap = rootPane
				.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(escapeStroke, "ESCAPE");
		inputMap.put(enterStroke, "ENTER");

		rootPane.getActionMap().put("ESCAPE", escapeActionListener);
		rootPane.getActionMap().put("ENTER", enterActionListener);

		return rootPane;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

}
