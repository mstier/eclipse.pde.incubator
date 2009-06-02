/**
 * Copyright (c) 2009 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Anyware Technologies - initial API and implementation
 *
 * $Id: PDEFormToolkit.java,v 1.1 2009/02/12 22:20:32 bcabe Exp $
 */
package org.eclipse.pde.emfforms.editor;

import org.eclipse.swt.widgets.Display;

public class DefaultEmfFormEditorConfig implements IEmfFormEditorConfig {
	private PDEFormToolkit customizedToolkit = null;

	public VALIDATE_ON_SAVE getValidateOnSave() {
		return VALIDATE_ON_SAVE.NO_VALIDATION;
	}

	public boolean isSaveAsAllowed() {
		return true;
	}

	public boolean isUsingSharedClipboard() {
		return false;
	}

	public PDEFormToolkit createPDEFormToolkit(Display display) {
		if (this.customizedToolkit != null) {
			return this.customizedToolkit;
		}
		return new PDEFormToolkit(display);
	}

	public void setCustomizedToolkit(PDEFormToolkit customizedToolkit) {
		this.customizedToolkit = customizedToolkit;
	}

}