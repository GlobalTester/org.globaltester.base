package org.globaltester.base.ui.editors;

import org.eclipse.jface.text.rules.IWordDetector;

public class GtDefaultWordDetector implements IWordDetector {
	@Override
	public boolean isWordPart(char c) {
		return !Character.isWhitespace(c);
	}
	
	@Override
	public boolean isWordStart(char c) {
		return !Character.isWhitespace(c);
	}
}