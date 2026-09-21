package reneiro.jean.proposta.dtos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import reneiro.jean.proposta.dtos.UsuarioCreateDto;
import reneiro.jean.proposta.dtos.UsuarioResponseDto;
import reneiro.jean.proposta.entities.Usuario;

public class UsuarioMapper {

	public static Usuario toUsuario(UsuarioCreateDto createDto) {
		Usuario usuario = new Usuario();
		usuario.setUsername(createDto.getUsername());
		usuario.setPassword(createDto.getPassword());
		return usuario;
	}

	public static Usuario mpaToUsuario(UsuarioCreateDto createDto) {
		return new ModelMapper().map(createDto, Usuario.class);
	}

	public static UsuarioCreateDto toUsuarioDto(Usuario usuer) {
		return new ModelMapper().map(usuer, UsuarioCreateDto.class);
	}

	public static UsuarioResponseDto toDto(Usuario usuer) {

		String role = usuer.getRole().name().substring("ROLE_".length());

		PropertyMap<Usuario, UsuarioResponseDto> propertyMap = new PropertyMap<Usuario, UsuarioResponseDto>() {
			@Override
			protected void configure() {
				map().setRole(role);
			}
		};

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(propertyMap);
		return new ModelMapper().map(usuer, UsuarioResponseDto.class);
	}
	
	public static List<UsuarioResponseDto> toDtoList(List<Usuario> usuarios) {
		return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
	}

}
