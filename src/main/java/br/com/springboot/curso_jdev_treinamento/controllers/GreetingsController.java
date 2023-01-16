package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
   @ResponseBody
   @PostMapping(value = "/salvarUsuario")
   public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
	   
	   usuario = usuarioRepository.saveAndFlush(usuario);
	   
	   return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
   }
    
    @ResponseBody
    @GetMapping(value = "/listarUsuarios")
    public ResponseEntity<List<Usuario>> ListarUsuarios(){
    	
    	List<Usuario> usuariosDoBanco = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuariosDoBanco, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idUsuario){
    	
    	usuarioRepository.deleteById(idUsuario);
    	
    	return new ResponseEntity<String>("Usuario número: "+idUsuario+" excluído com sucesso",
    			HttpStatus.OK);
    	
    }
    
    @ResponseBody
    @GetMapping(value = "/buscarUsuarioPorId")
    public ResponseEntity<?> buscarUsuarioPorId(@RequestParam("id") Long id){
    	
    	Usuario usuario = usuarioRepository.findById(id).orElse(null);
    	
    	if(usuario == null) return new ResponseEntity<String>("Usuário não encontrado", HttpStatus.NOT_FOUND);;
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.FOUND);
    }
    
    @ResponseBody
    @PutMapping(value = "/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
 	   
       if(usuario.getId() == null) {
    	   return new ResponseEntity<String>("O campo id é obrigatório!",HttpStatus.BAD_REQUEST);
       }
    	
 	   usuario = usuarioRepository.saveAndFlush(usuario);
 	   
 	   return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
}
