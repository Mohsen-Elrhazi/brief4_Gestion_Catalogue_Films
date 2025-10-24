package ma.project.mapper;

import ma.project.dto.DirectorDTO;
import ma.project.model.Director;
import org.springframework.stereotype.Component;

@Component
public class DirectorMapper {

    public DirectorDTO toDTO(Director director) {
        DirectorDTO dto = new DirectorDTO();
        dto.setIdDirector(director.getIdDirector());
        dto.setFirstName(director.getFirstName());
        dto.setLastName(director.getLastName());
        dto.setNationality(director.getNationality());
        dto.setBirthDate(director.getBirthDate());
        dto.setBiography(director.getBiography());
        return dto;
    }

    public Director toEntity(DirectorDTO dto) {
        Director director = new Director();
        director.setFirstName(dto.getFirstName());
        director.setLastName(dto.getLastName());
        director.setNationality(dto.getNationality());
        director.setBirthDate(dto.getBirthDate());
        director.setBiography(dto.getBiography());
        return director;
    }
}
