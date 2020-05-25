package info.pablogiraldo.imgup.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.pablogiraldo.imgup.dao.IUsuarioDAO;
import info.pablogiraldo.imgup.entity.Usuario;
import info.pablogiraldo.imgup.utils.RenderizadorPaginas;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

//	@Value("${upload.path}")
//	private String ruta;

//	@Autowired
//	ServletContext context;

	@Autowired
	private IUsuarioDAO usuarioDao;

	@Autowired
	ServletContext context;

	@GetMapping("/formusuario")
	public String formUsuario(Model model) {

		model.addAttribute("usuario", new Usuario());
		return "formUsuario";
	}

	@PostMapping("/formusuario")
	public String addUsuario(@RequestParam(name = "file", required = false) MultipartFile foto, Usuario usuario,
			RedirectAttributes flash) {

		if (!foto.isEmpty()) {
//			String ruta = "C://pruebas//img";

//			String ruta = "src/main/resources/static/img/";

			String relativeWebPath = "/img/";
			String ruta = context.getRealPath(relativeWebPath);

//			String ruta = "\\META-INF.resources\\img\\";

			String nombreUnico = UUID.randomUUID().toString() + "-" + foto.getOriginalFilename();

			try {
				byte[] bytes = foto.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//" + nombreUnico);
				Files.write(rutaAbsoluta, bytes);
				usuario.setFoto(nombreUnico);
			} catch (Exception e) {
				// TODO: handle exception
			}

			usuarioDao.save(usuario);
			flash.addFlashAttribute("success", "Foto subida.");

		}

		return "redirect:/usuarios/formusuario";
	}

	@GetMapping("/listusuarios")
	public String listUsuarios(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable userPageable = PageRequest.of(page, 2);
		Page<Usuario> usuarios = usuarioDao.findAll(userPageable);
		RenderizadorPaginas<Usuario> renderizadorPaginas = new RenderizadorPaginas<Usuario>("/usuarios/listusuarios",
				usuarios);

		model.addAttribute("renpag", renderizadorPaginas);
		model.addAttribute("usuarios", usuarios);

		model.addAttribute("titulo", "Listado usuarios");

		return "listUsuarios";
	}

}
