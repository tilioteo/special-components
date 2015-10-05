/**
 * 
 */
package org.vaadin.special.data;

import java.util.Locale;

import com.vaadin.data.util.converter.AbstractStringToNumberConverter;

/**
 * @author kamil
 * 
 */
@SuppressWarnings("serial")
public class StringToDoubleConverter extends
		AbstractStringToNumberConverter<Double> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.data.util.converter.Converter#convertToModel(java.lang.Object,
	 * java.lang.Class, java.util.Locale)
	 */
	@Override
	public Double convertToModel(String value,
			Class<? extends Double> targetType, Locale locale)
			throws ConversionException {

		if (null == value || value.trim().length() == 0) {
			return null;
		}

		Double result = null;
		try {
			result = Double.parseDouble(value.replace(",", "."));
		} catch (NumberFormatException e) {
			throw new ConversionException(String.format("Could not convert {0} to {1}.", value, getModelType().getName()));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.util.converter.Converter#getModelType()
	 */
	@Override
	public Class<Double> getModelType() {
		return Double.class;
	}

}
