package com.ggl.fontchooser.view.extended;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class SamplePanel extends JPanel {

	private static final long serialVersionUID = 7405261908065757006L;

	private Font font;

	private String sampleString;

	public SamplePanel(String sampleString) {
		super();
		this.sampleString = sampleString;
	}

	public void setSampleFont(Font font) {
		this.font = font;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (font == null) {
			return;
		}

		Graphics2D g2d = (Graphics2D) g;
		FontRenderContext frc = g2d.getFontRenderContext();
		TextLayout layout = new TextLayout(sampleString, font, frc);
		Rectangle2D bounds = layout.getBounds();

		int width = (int) Math.round(bounds.getWidth());
		int height = (int) Math.round(bounds.getHeight());
		int x = (getWidth() - width) / 2;
		int y = height + (getHeight() - height) / 2;

		layout.draw(g2d, (float) x, (float) y);
	}

}
