package org.globaltester.core.ui.editors;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;

/**
 * MultiLineRule that matches XML Tags
 * @author amay
 *
 */
public class XMLTagRule extends MultiLineRule {
	

	public XMLTagRule(String contentType) {
		this(ContentTypeAppearanceManager.getContentTypeToken(contentType));
	}
	
	public XMLTagRule(IToken token) {
		super("<", ">", token);
	}

	protected boolean sequenceDetected(ICharacterScanner scanner,
			char[] sequence, boolean eofAllowed) {
		int c = scanner.read();
		if (sequence[0] == '<') {
			if (c == '?') {
				// processing instruction - abort
				scanner.unread();
				return false;
			}
			if (c == '!') {
				scanner.unread();
				// comment - abort
				return false;
			}
		} else if (sequence[0] == '>') {
			scanner.unread();
		}
		return super.sequenceDetected(scanner, sequence, eofAllowed);
	}
}
