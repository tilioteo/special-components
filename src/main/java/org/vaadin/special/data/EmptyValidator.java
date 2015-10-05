/**
 * 
 */
package org.vaadin.special.data;

/**
 * @author kamil
 *
 */
@SuppressWarnings("serial")
public class EmptyValidator extends AbstractValidator {
	
	public EmptyValidator(String message) {
		super(message);
	}

	@Override
	public boolean isValid(Object value) {
		if (null == value || !(value instanceof String)) {
			return false;
		}
		
		return ((String)value).trim().length() > 0;
	}
	
}
