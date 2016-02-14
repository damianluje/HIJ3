/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import java.lang.annotation.Annotation;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.persistence.Converter;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

@FacesConverter("usuarioConverter")
public class UsuarioConverter implements javax.faces.convert.Converter {
 
	//private static final Logger LOG = LoggerFactory.getLogger(UsuarioConverter.class);
 
        @Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		//LOG.trace("String value: {}", value);
 
		return getObjectFromUIPickListComponent(component,value);
	}
 
        @Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		String string;
		//LOG.trace("Object value: {}", object);
		if(object == null){
			string="";
		}else{
			try{
				string = String.valueOf(((Usuario)object).getUsuCodigo());
			}catch(ClassCastException cce){
				throw new ConverterException();
			}
		}
		return string;
	}
 
	@SuppressWarnings("unchecked")
	private Usuario getObjectFromUIPickListComponent(UIComponent component, String value) {
		final DualListModel<Usuario> dualList;
		try{
			dualList = (DualListModel<Usuario>) ((PickList)component).getValue();
			Usuario team = getObjectFromList(dualList.getSource(),Integer.valueOf(value));
			if(team==null){
				team = getObjectFromList(dualList.getTarget(),Integer.valueOf(value));
			}
			
			return team;
		}catch(ClassCastException cce){
			throw new ConverterException();
		}catch(NumberFormatException nfe){
			throw new ConverterException();
		}
	}
 
	private Usuario getObjectFromList(final List<?> list, final Integer identifier) {
		for(final Object object:list){
			final Usuario team = (Usuario) object;
			if(team.getUsuCodigo().equals(identifier)){
				return team;
			}
		}
		return null;
	}

    
}
