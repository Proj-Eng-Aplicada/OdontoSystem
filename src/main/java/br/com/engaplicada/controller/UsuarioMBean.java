package br.com.engaplicada.controller;

/**
 * @author Paulo Neto
 * **/

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.engaplicada.entity.Usuario;
import br.com.engaplicada.service.UsuarioService;

import br.com.engaplicada.util.RNException;
import br.com.engaplicada.util.RepositoryException;

@ManagedBean(name="usuarioMBean")
@RequestScoped
public class UsuarioMBean {
	private Usuario usuario;
	private String confirmaSenha;
	private UsuarioService service;
	private String destino;
	
	
	public UsuarioMBean(){	
		reset();
		this.service = new UsuarioService();
		destino = "";
	}
	
	public void reset(){
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String cadastrar()throws RNException, RepositoryException{
		FacesContext obj = FacesContext.getCurrentInstance();
		if(this.confirmaSenha.equals(usuario.getSenha())){
			if(service.isCadastrar(usuario)){
				FacesMessage mensagem = new FacesMessage("Usu�rio Cadastrado com Sucesso !");
				obj.addMessage(null, mensagem);
				reset();
				return null;
			}else{
				FacesMessage mensagem = new FacesMessage("ERRO : Falha ao salvar o Usuario ");
				obj.addMessage(null, mensagem);
				reset();
				return "erro";
			}
		}else{
			FacesMessage mensagem = new FacesMessage("ERRO : Falha na confirma��o da senha ! ");
			obj.addMessage(null, mensagem);
			reset();
			return null;
		}

	}
	
	public String atualizarUsuario()throws RNException{
		FacesContext obj = FacesContext.getCurrentInstance();
		if(service.isAtualizar(usuario)){
			FacesMessage mensagem = new FacesMessage("Usu�rio Atualizado com Sucesso !");
			obj.addMessage(null, mensagem);
			reset();
			return null;
		}else{
			FacesMessage mensagem = new FacesMessage(" ERRO : Falha ao atualizar o Usuario !");
			obj.addMessage(null, mensagem);
			reset();
		return null;
		}
	}
	
	public List<Usuario> getLista()throws RepositoryException{
		return service.getAllUsuarios();
	}
	
	public String remover()throws RNException, RepositoryException{
		FacesContext obj = FacesContext.getCurrentInstance();
		if(service.isRemover(usuario)){
			FacesMessage mensagem = new FacesMessage("Usuario Removido !!");
			obj.addMessage(null, mensagem);
			reset();
			return null;
		}else{
			FacesMessage mensagem = new FacesMessage("ERRO : Falha ao Remover Usuario");
			obj.addMessage(null, mensagem);
		}
		reset();
		return null;
	}
	
	public String atualizar(){
		return destino;
	}
	
	
	public String forward(){
		return "inicio";
	}
	
	public String entrarCadastroUsuario(){
		return "cadastro";
	}
	
}
