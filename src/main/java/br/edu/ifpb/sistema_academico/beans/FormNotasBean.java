package br.edu.ifpb.sistema_academico.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sistema_academico.models.Estudante;
import br.edu.ifpb.sistema_academico.services.EstudanteService;

@Named
@ViewScoped
public class FormNotasBean implements Serializable {
	
	@Inject
	private EstudanteService service;

	public String atualizarBoletim(Estudante estudante) {
		this.getFlash().setKeepMessages(true);
		service.alterar(estudante);
		
		FacesMessage mensagem = new FacesMessage(
		          FacesMessage.SEVERITY_INFO,
		          "Boletim atualizado com sucesso!",
		          null);
		      getContext().addMessage(null, mensagem);
		
		return "notas?faces-redirect=true";
	}
	
	public boolean travaCampoFinal(Estudante estudante) {
		if(estudante.getNota1() != null && estudante.getNota2() != null && estudante.getNota3() != null) {
			
			if(estudante.getFaltas() != null) {
				if(estudante.getFaltas() > 25) {
					return true;
				}
			}
			
			return !((
					estudante.getNota1().doubleValue() + 
					estudante.getNota2().doubleValue() + 
					estudante.getNota3().doubleValue())/3 >= 4);
		}
		return true;
	}
	
	private Flash getFlash() {
	    return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	private FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
}
