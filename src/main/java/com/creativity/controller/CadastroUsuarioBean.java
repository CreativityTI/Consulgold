/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.creativity.controller;

import com.creativity.model.Empresa;
import com.creativity.model.GrupoUsuario;
import com.creativity.model.Usuario;
import com.creativity.repository.Empresas;
import com.creativity.repository.GruposUsuarios;
import com.creativity.repository.Usuarios;
import com.creativity.service.CadastroUsuarioService;
import com.creativity.service.NegocioException;
import com.creativity.util.FacesUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author rafael.lima
 */
@Named
@javax.faces.view.ViewScoped
public class CadastroUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroUsuarioService usuarioService;

    private Usuario usuario;
    private Usuario usuarioSelecionado;

    @Inject
    private Usuarios repositorioGestores;
    private List<Usuario> gestores;
    private List<Usuario> gestorLogado;

    private List<Usuario> usuarioLogado;

    @Inject
    private GruposUsuarios repositorioGruposUsuario;
    private GrupoUsuario grupoUsuarioSelecionado;

    private List<GrupoUsuario> gruposUsuarios;
    private List<GrupoUsuario> grupoUsuarioConsultor;

    @Inject
    private Empresas repositorioEmpresa;
    private Empresa empresaSelecionada;
    private List<Empresa> empresas;

    private String cep = null;

    public void prepararCadastro() {
        this.gruposUsuarios = this.repositorioGruposUsuario.todosGruposUsuarios();
        this.grupoUsuarioConsultor = this.repositorioGruposUsuario.gruposUsuariosConsultores();
        this.empresas = this.repositorioEmpresa.todasEmpresas();
        this.gestores = this.repositorioGestores.todosGestores();
        this.gestorLogado = this.repositorioGestores.gestorLogado();

        if (this.usuario == null) {
            this.usuario = new Usuario();

            this.grupoUsuarioSelecionado = new GrupoUsuario();
            this.empresaSelecionada = new Empresa();

        }
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.usuarioService.salvar(this.usuario);
            context.addMessage(null, new FacesMessage(" Usuário salvo com sucesso!"));
            this.usuario = new Usuario();
            this.grupoUsuarioSelecionado = new GrupoUsuario();

        } catch (NegocioException e) {

            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void salvarConsultor() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
          
            this.usuarioService.salvar(this.usuario);
            context.addMessage(null, new FacesMessage(" Consultor salvo com sucesso!"));
            this.usuario = new Usuario();
            this.grupoUsuarioSelecionado = new GrupoUsuario();

        } catch (NegocioException e) {

            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public void carregarGestores() {
        this.gestores = this.repositorioGestores.todosGestores();
        
    }

    public String retornaFoto(Usuario usu) {
        if (usu != null) {
            if (usu.getFoto() != null) {
                return "/resources/fotos/" + usu.getFoto();
            } else {
                return "/resources/fotos/foto.gif";
            }
        }
        return "/resources/fotos/foto.gif";
    }

    public String retornaFotoUsuario() {

        if (this.usuario != null) {
            if (this.usuario.getFoto() != null) {
                return "/resources/fotos/" + this.usuario.getFoto();
            } else {
                return "/resources/fotos/foto.gif";
            }
        }
        return "/resources/fotos/foto.gif";
    }

    public String retornaFoto() {
        if (this.usuario != null) {
            if (this.usuario.getFoto() != null) {
                return "/resources/fotos/" + this.usuario.getFoto();
            } else {
                return "/resources/fotos/foto.gif";
            }
        }
        return "/resources/fotos/foto.gif";
    }


    /* public void handleFileUpload(FileUploadEvent event) {

        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);*/
    public void processarFoto(FileUploadEvent event) {
        ServletContext servletContext = (ServletContext) FacesContext.
                getCurrentInstance().getExternalContext().getContext();
        String absoluteDiskPath = servletContext.getRealPath("/resources/fotos/");
        File targetFolder = new File(absoluteDiskPath);
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();

        }

        try (InputStream inputStream = event.getFile().getInputstream()) {
            OutputStream out;
            out = new FileOutputStream(new File(targetFolder, event.getFile().getFileName()));
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            this.usuario.setFoto(event.getFile().getFileName());
            FacesUtil.addInfoMessage("Foto processada!");

            /*Util.criarAviso("Foto processada!");
            Util.executarJavaScript("PF('dlgfoto').hide();");
            Util.atualizarForm("criar:pic");*/
        } catch (IOException ex) {
            FacesUtil.addErrorMessage("ERRO:!" + ex);

        }

    }

    public void encontraCEPS() {
        CepWebService cepWebService = new CepWebService(getCep());

        if (cepWebService.getResultado() == 1) {

            usuario.setCep(cep);
            usuario.setTipoLogradouro(cepWebService.getTipoLogradouro());
            usuario.setLogradouro(cepWebService.getLogradouro());
            usuario.setEstado(cepWebService.getEstado());
            usuario.setCidade(cepWebService.getCidade());
            usuario.setBairro(cepWebService.getBairro());

        } else {

            usuario.setCep(cep);
            usuario.setTipoLogradouro(cepWebService.getTipoLogradouro());
            usuario.setLogradouro(cepWebService.getLogradouro());
            usuario.setEstado(cepWebService.getEstado());
            usuario.setCidade(cepWebService.getCidade());
            usuario.setBairro(cepWebService.getBairro());

        }

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Servidor não está respondendo",
                        "Servidor não está respondendo"));

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public List<GrupoUsuario> getGruposUsuarios() {
        return gruposUsuarios;
    }

    public GrupoUsuario getGrupoUsuarioSelecionado() {
        return grupoUsuarioSelecionado;
    }

    public void setGrupoUsuarioSelecionado(GrupoUsuario grupoUsuarioSelecionado) {
        this.grupoUsuarioSelecionado = grupoUsuarioSelecionado;

    }

    public Empresa getEmpresaSelecionada() {
        return empresaSelecionada;
    }

    public void setEmpresaSelecionada(Empresa empresaSelecionada) {
        this.empresaSelecionada = empresaSelecionada;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Usuario> getGestores() {
        return gestores;
    }

    public void setGestores(List<Usuario> gestores) {
        this.gestores = gestores;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<Usuario> getGestorLogado() {
        return gestorLogado;
    }

    public void setGestorLogado(List<Usuario> gestorLogado) {
        this.gestorLogado = gestorLogado;
    }

    public List<GrupoUsuario> getGrupoUsuarioConsultor() {
        return grupoUsuarioConsultor;
    }

    public void setGrupoUsuarioConsultor(List<GrupoUsuario> grupoUsuarioConsultor) {
        this.grupoUsuarioConsultor = grupoUsuarioConsultor;
    }
    
    

    public boolean isEditando() {
        return this.usuario.getId() != null;
    }

    public boolean isEditandoCep() {
        return this.usuario.getId() == null;
    }
    
    

}
