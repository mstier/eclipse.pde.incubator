/*******************************************************************************
 * Copyright (c) 2006, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.browser.view.ui.listeners;

import org.eclipse.browser.view.ui.sections.SearchSection;
import org.eclipse.browser.view.util.GoogleSearchLinkManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * SearchButtonSelectionListener
 *
 */
public class SearchButtonSelectionListener extends SelectionAdapter {

	private SearchSection fSection;	
	
	/**
	 * @param view
	 */
	public SearchButtonSelectionListener(SearchSection section) {
		fSection = section;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
		handleButtonSelectedSearch();
	}	
	
	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionAdapter#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
		handleButtonSelectedSearch();
	}

	/**
	 * 
	 */
	private void handleButtonSelectedSearch() {
		fSection.getView().getOpenLinkAction().update(
				GoogleSearchLinkManager.getGoogleSearchLink(
						fSection.getView().getModel(), 
						fSection.getSearchTextField().getText()));
		fSection.getView().getOpenLinkAction().run();				
	}	
	
}
