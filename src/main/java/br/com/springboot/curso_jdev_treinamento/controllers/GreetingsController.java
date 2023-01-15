package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}/{idade}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name, @PathVariable int idade) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(name);
    	usuario.setIdade(idade);
    	
    	usuario = usuarioRepository.save(usuario);
    	
    	System.out.println(usuario);
    	
        return "Iniciando estudos com SpringBoot: " + name + "!";
    }
    
    @ResponseBody
    @GetMapping(value = "/listarUsuarios")
    public ResponseEntity<List<Usuario>> ListarUsuarios(){
    	
    	List<Usuario> usuariosDoBanco = usuarioRepository.findAll();
    	
    	return new ResponseEntity<List<Usuario>>(usuariosDoBanco, HttpStatus.OK);
    }
}
