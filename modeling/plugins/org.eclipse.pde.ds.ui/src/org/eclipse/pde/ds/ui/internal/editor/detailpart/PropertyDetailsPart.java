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
 * $Id: PropertyDetailsPart.java,v 1.5 2009/06/02 10:48:02 bcabe Exp $
 */
package org.eclipse.pde.ds.ui.internal.editor.detailpart;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.pde.ds.scr.JavaType;
import org.eclipse.pde.ds.scr.ScrPackage;
import org.eclipse.pde.ds.ui.internal.editor.composites.PropertyComposite;
import org.eclipse.pde.emfforms.databinding.EMFValidatingUpdateValueStrategy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.*;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class PropertyDetailsPart implements IDetailsPage {

	private PropertyComposite propertyComposite;
	private IManagedForm managedForm;
	private EditingDomain editingDomain;
	private DataBindingContext dataBindingContext;
	private IObservableValue currentProperty;

	public PropertyDetailsPart(IManagedForm managedForm, EditingDomain editingDomain, DataBindingContext dataBindingContext) {
		this.managedForm = managedForm;
		this.editingDomain = editingDomain;
		this.dataBindingContext = dataBindingContext;

		currentProperty = new WritableValue();
	}

	public void createContents(Composite parent) {
		GridLayoutFactory.fillDefaults().margins(0, 0).applyTo(parent);

		FormToolkit toolkit = managedForm.getToolkit();
		Section section = toolkit.createSection(parent, Section.TITLE_BAR);
		section.setText("Details");
		section.marginWidth = 10;
		section.marginHeight = 5;

		propertyComposite = new PropertyComposite(section, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).grab(true, true).applyTo(propertyComposite);

		managedForm.getToolkit().adapt(propertyComposite);

		toolkit.adapt(propertyComposite);
		propertyComposite.setParent(section);

		section.setClient(propertyComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(section);

		propertyComposite.getComboType().setInput(JavaType.values());
		bind(dataBindingContext);

	}

	protected void bind(DataBindingContext bindingContext) {
		// Name
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(propertyComposite.getTextName()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getProperty_Name()).observeDetail(currentProperty), null, null);

		// Type
		bindingContext.bindValue(ViewersObservables.observeSingleSelection(propertyComposite.getComboType()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getProperty_Type()).observeDetail(currentProperty), null, null);

		//Value
		bindingContext.bindValue(WidgetProperties.text(SWT.FocusOut).observe(propertyComposite.getTextValue()), EMFEditProperties.value(editingDomain, ScrPackage.eINSTANCE.getProperty_Value()).observeDetail(currentProperty), new EMFValidatingUpdateValueStrategy(), null);

	}

	public void commit(boolean onSave) {
	}

	public void dispose() {
	}

	public void initialize(IManagedForm form) {

	}

	public boolean isDirty() {
		return false;
	}

	public boolean isStale() {
		return false;
	}

	public void refresh() {
	}

	public void setFocus() {
		propertyComposite.getTextName().setFocus();
		propertyComposite.getTextName().selectAll();
	}

	public boolean setFormInput(Object input) {
		return false;
	}

	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection sel = (IStructuredSelection) selection;
		if (sel.getFirstElement() instanceof WrapperItemProvider) {
			WrapperItemProvider wip = (WrapperItemProvider) sel.getFirstElement();
			Object o = wip.getEditableValue(sel.getFirstElement());
			currentProperty.setValue(o);
		}
	}

}
