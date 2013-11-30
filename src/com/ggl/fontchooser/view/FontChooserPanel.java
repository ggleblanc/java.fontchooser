package com.ggl.fontchooser.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.ggl.fontchooser.view.extended.SamplePanel;

public class FontChooserPanel {
	
	private static final Insets COMBOBOX_INSETS = new Insets(0, 10, 0, 0);
	private static final Insets COMBOBOX_INSETS_LAST = new Insets(0, 10, 0, 10);
	private static final Insets LABEL_INSETS = new Insets(10, 10, 0, 0);

	private static final String SAMPLE_STRING = "AaBbIiMmYyZz";

	private int familySize;
	private int familyStyle;
	
	private DefaultListModel fontNameListModel;
	private DefaultListModel fontStyleListModel;
	private DefaultListModel fontSizeListModel;
	
	private Font font;
	
	private JPanel panel;
	
	private SamplePanel samplePanel;
	
	private String familyName;

	private TextFieldList fontNameList;
	private TextFieldList fontStyleList;
	private TextFieldList fontSizeList;
	
	public FontChooserPanel() {
		this(null);
	}
	
	public FontChooserPanel(Font font) {
		this.font = font;
		this.fontNameListModel = new DefaultListModel();
		this.fontStyleListModel = new DefaultListModel();
		this.fontSizeListModel = new DefaultListModel();
		getDefaultFontFields();
		getAllFontNames();
		getAllFontStyles();
		getAllFontSizes();
		createPartControl();
	}
	
	private void createPartControl() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		int gridy = 0;

		gridy = createFontLabelControl(gridy);
		gridy = createFontComboBoxControl(gridy);
		gridy = createSampleControl(gridy);
	}
	
	private void getDefaultFontFields() {
		if (font != null) {
			familyName = font.getFamily();
			familyStyle = font.getStyle();
			familySize = font.getSize();
		}
	}

	private void getAllFontNames() {
		GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		for (String name : e.getAvailableFontFamilyNames()) {
			fontNameListModel.addElement(name);
		}
	}

	private void getAllFontStyles() {
		fontStyleListModel.addElement("Regular");
		fontStyleListModel.addElement("Bold");
		fontStyleListModel.addElement("Italic");
		fontStyleListModel.addElement("Bold Italic");
	}

	private void getAllFontSizes() {
		for (int i = 8; i <= 11; i++) {
			fontSizeListModel.addElement((Integer) i);
		}
		for (int i = 12; i <= 28; i += 2) {
			fontSizeListModel.addElement((Integer) i);
		}
		fontSizeListModel.addElement((Integer) 36);
		fontSizeListModel.addElement((Integer) 48);
		fontSizeListModel.addElement((Integer) 72);
	}
	
	private int createFontLabelControl(int gridy) {
		JLabel fontNameLabel = new JLabel("Font");
		addComponent(panel, fontNameLabel, 0, gridy, 1, 1, LABEL_INSETS,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE);

		JLabel fontStyleLabel = new JLabel("Font style");
		addComponent(panel, fontStyleLabel, 1, gridy, 1, 1, LABEL_INSETS,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE);

		JLabel fontSizeLabel = new JLabel("Size");
		addComponent(panel, fontSizeLabel, 2, gridy++, 1, 1, LABEL_INSETS,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE);

		return gridy;
	}
	
	private int createFontComboBoxControl(int gridy) {
		DrawSelectionListener listener = new DrawSelectionListener();
		
		fontNameList = new TextFieldList(fontNameListModel);
		fontNameList.addListSelectionListener(listener);
		fontNameList.setVisibleRowCount(7);
		addComponent(panel, fontNameList, 0, gridy, 1, 1, COMBOBOX_INSETS,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

		fontStyleList = new TextFieldList(fontStyleListModel, 75);
		fontStyleList.addListSelectionListener(listener);
		fontStyleList.setVisibleRowCount(7);
		addComponent(panel, fontStyleList, 1, gridy, 1, 1, COMBOBOX_INSETS,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);

		fontSizeList = new TextFieldList(fontSizeListModel, 25);
		fontSizeList.addListSelectionListener(listener);
		fontSizeList.setVisibleRowCount(7);
		addComponent(panel, fontSizeList, 2, gridy++, 1, 1,
				COMBOBOX_INSETS_LAST, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		if (font != null) {
			// We must select by index first.
			fontStyleList.setSelectedIndex(familyStyle);
			fontNameList.setSelectedValue(familyName);
			fontSizeList.setSelectedValue((Integer) familySize);
		}

		return gridy;
	}

	private int createSampleControl(int gridy) {
		Border titledBorder = BorderFactory.createTitledBorder("Sample");
		Border bevelBorder = BorderFactory
				.createBevelBorder(BevelBorder.LOWERED);

		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(titledBorder);
		titlePanel.setLayout(new BorderLayout());

		samplePanel = new SamplePanel(SAMPLE_STRING);
		samplePanel.setBorder(bevelBorder);
		samplePanel.setPreferredSize(new Dimension(300, 100));

		titlePanel.add(samplePanel, BorderLayout.CENTER);

		addComponent(panel, titlePanel, 0, gridy++, 3, 1,
				COMBOBOX_INSETS_LAST, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		if (font != null) {
			drawSample();
		}

		return gridy;
	}
	
	private void addComponent(Container container, Component component,
			int gridx, int gridy, int gridwidth, int gridheight, Insets insets,
			int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
				gridwidth, gridheight, 1.0D, 1.0D, anchor, fill, insets, 0, 0);
		container.add(component, gbc);
	}
	
	void drawSample() {
		font = createSelectedFont();
		if (font != null) {
			if (samplePanel != null) {
				samplePanel.setSampleFont(font);
			}
		}
	}
	
	public Font createSelectedFont() {
		boolean allValues = true;

		if ((fontNameList == null) || (fontNameList.getSelectedValue() == null)) {
			allValues = false;
		} else {
			familyName = (String) fontNameList.getSelectedValue();
		}

		if (fontStyleList == null) {
			allValues = false;
		} else {
			familyStyle = fontStyleList.getSelectedIndex();
		}

		if ((fontSizeList == null) || (fontSizeList.getSelectedValue() == null)) {
			allValues = false;
		} else {
			familySize = (Integer) fontSizeList.getSelectedValue();
		}

		if (allValues) {
			return new Font(familyName, familyStyle, familySize);
		} else {
			return null;
		}
	}

	public JPanel getPanel() {
		return panel;
	}
	
	public class DrawSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			drawSample();
		}

	}

}
