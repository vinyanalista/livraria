package br.ufs.livraria.jsf.validator;

import java.util.regex.Matcher;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

	private static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
	private static final String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)*";
	private static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

	private java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
			"^" + ATOM + "+(\\." + ATOM + "+)*@"
					+ DOMAIN
					+ "|"
					+ IP_DOMAIN
					+ ")$",
			java.util.regex.Pattern.CASE_INSENSITIVE
	);
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) {
		if (value != null && !value.toString().isEmpty()) {
			Matcher matcher = pattern.matcher(value.toString());
			if (!matcher.matches()) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email inválido", null));
			}
		}
	}
}