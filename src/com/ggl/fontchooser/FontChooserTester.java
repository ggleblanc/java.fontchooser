package com.ggl.fontchooser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.ggl.fontchooser.view.FontChooserDialog;

public class FontChooserTester implements Runnable {

	private JFrame frame;

	@Override
	public void run() {
		frame = new JFrame("Font Chooser Tester");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				exitProcedure();
			}
		});

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		final JLabel fontLabel = new JLabel("");
		fontLabel.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(fontLabel, BorderLayout.NORTH);

		JButton fontButton = new JButton("Font Chooser");
		fontButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				FontChooserDialog fontChooser = new FontChooserDialog(frame, frame
						.getFont());
				if (fontChooser.isOkPressed()) {
					Font font = fontChooser.getSelectedFont();
					frame.setFont(font);
					fontLabel.setFont(font);
					String s = buildFontString(font);
					fontLabel.setText(s);
					fontLabel.repaint();
				}
			}

			private String buildFontString(Font font) {
				String[] styleTypes = { "plain", "bold", "italic",
						"bold italic" };
				StringBuilder builder = new StringBuilder();
				builder.append(font.getFamily());
				builder.append(" font, ");
				builder.append(styleTypes[font.getStyle()]);
				builder.append(" style, ");
				builder.append(font.getSize());
				builder.append(" point.");
				return builder.toString();
			}
		});
		mainPanel.add(fontButton, BorderLayout.SOUTH);

		frame.add(mainPanel);
		frame.setSize(new Dimension(700, 500));
		frame.setVisible(true);

	}

	public void exitProcedure() {
		frame.dispose();
		System.exit(0);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new FontChooserTester());
	}

}
