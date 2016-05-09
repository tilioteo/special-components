/**
 * 
 */
package org.vaadin.special.ui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Kamil Morong - Hypothesis
 * 
 */
@SuppressWarnings("serial")
public class MultipleComponentPanel<C extends AbstractComponent> extends Panel {

	public enum Orientation {
		Vertical, Horizontal
	};

	protected List<C> childList = new LinkedList<C>();
	private Orientation orientation = Orientation.Horizontal;
	private String childWidth = null;
	private String childHeight = null;
	private String childStyle = null;
	private String oldChildStyle = null;

	protected MultipleComponentPanel() {
		// nop
	}
	
	protected void addChild(C child) {
		childList.add(child);
	}
	
	protected void removeChild(C child) {
		childList.remove(child);
	}
	
	protected void removeAllChildren() {
		childList.clear();
	}
	
	public int getChildIndex(C child) {
		int index = 0;
		for (C child2 : childList) {
			if (child2.equals(child))
				return index;

			++index;
		}
		return -1;
	}

	protected Iterator<C> getChildIterator() {
		return childList.iterator();
	}

	public String getChildrenHeight() {
		return childHeight;
	}

	public String getChildrenWidth() {
		return childWidth;
	}

	public String getChildrenStyle() {
		return childStyle;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	private void setChildHeight(C child) {
		child.setHeight(childHeight);
	}

	public void setChildrenHeight(String height) {
		this.childHeight = height;
		markAsDirty();
	}

	public void setChildrenWidth(String width) {
		this.childWidth = width;
		markAsDirty();
	}

	private void setChildWidth(C child) {
		child.setWidth(childWidth);
	}
	
	public void setChildrenStyle(String style) {
		this.oldChildStyle = childStyle;
		this.childStyle = style;
		markAsDirty();
	}
	
	private void setChildStyle(C child) {
		if (oldChildStyle != null) {
			child.removeStyleName(oldChildStyle);
		}
		
		child.addStyleName(childStyle);
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
		updateContent();
	}

	public void updateContent() {
		AbstractOrderedLayout layout = (AbstractOrderedLayout) getContent();
		AbstractOrderedLayout newLayout = null;

		if (orientation.equals(Orientation.Horizontal) &&
				(null == layout || layout instanceof VerticalLayout)) { 
			newLayout = new HorizontalLayout();
		} else if (orientation.equals(Orientation.Vertical) &&
				(null == layout || layout instanceof HorizontalLayout)) {
			newLayout = new VerticalLayout();
		}

		if (newLayout != null) {
			if (layout != null) {
				layout.removeAllComponents();
			}
			setContent(newLayout);
			newLayout.setSizeFull();
			
			layout = newLayout;
		} else if (layout != null) {
			layout.removeAllComponents();
		}

		for (C child : childList) {
			setChildWidth(child);
			setChildHeight(child);
			setChildStyle(child);

			if (layout != null) {
				layout.addComponent(child);
				layout.setComponentAlignment(child, Alignment.MIDDLE_CENTER);
			}
		}
		markAsDirty();
	}

}
