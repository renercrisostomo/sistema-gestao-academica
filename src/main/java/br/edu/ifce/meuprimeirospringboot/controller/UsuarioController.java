package br.edu.ifce.meuprimeirospringboot.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifce.meuprimeirospringboot.beans.Usuario;
import br.edu.ifce.meuprimeirospringboot.dto.CpfDTO;
import br.edu.ifce.meuprimeirospringboot.enums.Raca;
import br.edu.ifce.meuprimeirospringboot.serviceImpl.UsuarioServiceImpl;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@GetMapping
	String listar(ModelMap model) {
		model.addAttribute("usuarios", usuarioService.listarTodos());
		return "usuario/lista";
	}
	
	// @RequestParam --> GET /usuario?cpf=12345678900
	// @RequestBody --> GET /usuario/12345678900 @GetMapping("/usuario/{cpf}")
	// @PathVariable --> Lê o corpo da requisição (normalmente JSON) e mapeia para um objeto Java.

	@PostMapping("/buscar-por-cpf")
    public ResponseEntity<Usuario> getUsuarioPorCPF(@RequestBody CpfDTO dto) {
		 Usuario usuario = usuarioService.buscarPorCPF(dto.getCpf());
	        return ResponseEntity.ok(usuario); 
	    }
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
	    Usuario novoUsuario = usuarioService.salvar(usuario);
	    return ResponseEntity.ok(novoUsuario);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuarioAtualizado) {
	    Usuario usuarioEditado = usuarioService.editar(usuarioAtualizado);
	    return ResponseEntity.ok(usuarioEditado);
	}


	@GetMapping("/cadastrar")
	String CadastrarUsuarios(ModelMap model){
		model.addAttribute("usuario",new Usuario());
		model.addAttribute("racas", Raca.values());
		return "/usuario/cadastro";
	}
	
	@PostMapping("/salvar")
	String Salvar(Usuario usuario) {
		try {
				usuarioService.salvar(usuario); 
				return "redirect:/usuarios";
		} catch (RuntimeException e) {
				return "redirect:/usuarios/cadastrar";
		}
	}
	
	@GetMapping("/excluir/{id}")
	String excluir(@PathVariable("id") Long id) { // Added RedirectAttributes
		usuarioService.excluirPorId(id);
		return "redirect:/usuarios";	
	}
	
	
	@GetMapping("/editar/{id}")
	String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("usuario",usuarioService.buscarPorId(id));
		model.addAttribute("racas", Raca.values());
		return "/usuario/cadastro";
	}
	
	@PostMapping("/editar")
	String editar(Usuario usuario) {
		try {
			usuarioService.editar(usuario); 
			return "redirect:/usuarios";
		} catch (RuntimeException e) {
			return "redirect:/usuarios/editar/" + usuario.getId();
		}
	}
		
}
