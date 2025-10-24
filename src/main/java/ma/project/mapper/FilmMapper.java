package ma.project.mapper;

import ma.project.dto.FilmDTO;
import ma.project.model.Category;
import ma.project.model.Director;
import ma.project.model.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {

    public FilmDTO toDTO(Film film) {
        FilmDTO dto = new FilmDTO();
        dto.setIdFilm(film.getIdFilm());
        dto.setTitle(film.getTitle());
        dto.setReleaseYear(film.getReleaseYear());
        dto.setDuration(film.getDuration());
        dto.setSynopsis(film.getSynopsis());
        dto.setRating(film.getRating());
        if(film.getDirector() != null) dto.setDirectorId(film.getDirector().getIdDirector());
        if(film.getCategory() != null) dto.setCategoryId(film.getCategory().getIdCategory());
        return dto;
    }

    public Film toEntity(FilmDTO dto, Director director, Category category) {
        Film film = new Film();
        film.setTitle(dto.getTitle());
        film.setReleaseYear(dto.getReleaseYear());
        film.setDuration(dto.getDuration());
        film.setSynopsis(dto.getSynopsis());
        film.setRating(dto.getRating());
        film.setDirector(director);
        film.setCategory(category);
        return film;
    }
}
