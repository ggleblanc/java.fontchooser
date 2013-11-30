package com.ggl.fontchooser.view;

import java.awt.Dialog.ModalityType;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ggl.fontchooser.view.extended.EscapeDialog;

public class FontChooserDialog {

	private static final Insets PANEL_INSETS = new Insets(0, 0, 0, 0);
	private static final Insets BUTTON_INSETS = new Insets(10, 10, 10, 10);
	
	private boolean okPressed;

	private EscapeDialog dialog;

	private Font font;
	
	private FontChooserPanel fontChooserPanel;

	private JFrame frame;
	
	private JPanel mainPanel;

	public FontChooserDialog(JFrame frame) {
		this(frame, null);
	}

	public FontChooserDialog(JFrame frame, Font defaultFont) {
		this.frame = frame;
		this.font = defaultFont;
		createPartControl();
	}

	private void createPartControl() {
		dialog = new EscapeDialog(frame);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Font");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		int gridy = 0;
		
		gridy = createPanelControl(gridy);		
		gridy = createButtonControl(gridy);

		dialog.add(mainPanel);
		dialog.pack();
		dialog.setVisible(true);
	}

	private int createPanelControl(int gridy) {
		fontChooserPanel = new FontChooserPanel(font);

		addComponent(mainPanel, fontChooserPanel.getPanel(), 0, gridy++, 1, 1,
				PANEL_INSETS, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		return gridy;
	}

	private int createButtonControl(int gridy) {
		JPanel buttondrawingPanel = new JPanel();
		buttondrawingPanel.setLayout(new FlowLayout());

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Font testFont = fontChooserPanel.createSelectedFont();
				if (testFont == null) {
					return;
				} else {
					okPressed = true;
					font = testFont;
					dialog.setVisible(false);
				}
			}
		});
		dialog.setOkButton(okButton);
		buttondrawingPanel.add(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				okPressed = false;
				dialog.setVisible(false);
			}
		});
		buttondrawingPanel.add(cancelButton);

		setButtonSizes(okButton, cancelButton);

		addComponent(mainPanel, buttondrawingPanel, 0, gridy++, 1, 1,
				BUTTON_INSETS, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		return gridy;
	}

	private void setButtonSizes(JButton... buttons) {
		Dimension preferredSize = new Dimension();
		for (JButton button : buttons) {
			Dimension d = button.getPreferredSize();
			preferredSize = setLarger(preferredSize, d);
		}
		for (JButton button : buttons) {
			button.setPreferredSize(preferredSize);
		}
	}

	private Dimension setLarger(Dimension a, Dimension b) {
		Dimension d = new Dimension();
		d.height = Math.max(a.height, b.height);
		d.width = Math.max(a.width, b.width);
		return d;
	}
	
	private void addComponent(Container container, Component component,
			int gridx, int gridy, int gridwidth, int gridheight, Insets insets,
			int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0D, 1.0D, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}
	
	public Font getSelectedFont() {
		return font;
	}

	public boolean isOkPressed() {
		return okPressed;
	}

}
