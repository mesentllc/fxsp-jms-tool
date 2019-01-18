package com.fedex.services.jmstool;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class EvenOddRenderer implements TableCellRenderer {

	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		((JLabel) renderer).setOpaque(true);
		renderer.setForeground(Color.BLACK);
		renderer.setFont(new Font("Dialog", Font.BOLD, 12));
		if (row % 2 == 0) {
			renderer.setBackground(Color.WHITE);
		}
		else {
			renderer.setBackground(Color.LIGHT_GRAY);
		}
		return renderer;
	}
}
