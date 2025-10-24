package ma.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.project.dto.FilmDTO;
import ma.project.mapper.FilmMapper;
import ma.project.model.Category;
import ma.project.model.Director;
import ma.project.model.Film;
import ma.project.repository.CategoryRepository;
import ma.project.repository.DirectorRepository;
import ma.project.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;
    private final CategoryRepository categoryRepository;
    private final FilmMapper filmMapper;

    public FilmDTO saveFilm(FilmDTO dto) {
        Director director = directorRepository.findById(dto.getDirectorId())
                .orElseThrow(() -> new RuntimeException("Réalisateur non trouvé"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        Film film = filmMapper.toEntity(dto, director, category);
        return filmMapper.toDTO(filmRepository.save(film));
    }

    public FilmDTO updateFilm(Long id, FilmDTO dto) {
        Film existingFilm = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film non trouvé"));

        existingFilm.setTitle(dto.getTitle());
        existingFilm.setReleaseYear(dto.getReleaseYear());
        existingFilm.setDuration(dto.getDuration());
        existingFilm.setSynopsis(dto.getSynopsis());
        existingFilm.setRating(dto.getRating());

        if(dto.getDirectorId() != null) {
            Director director = directorRepository.findById(dto.getDirectorId())
                    .orElseThrow(() -> new RuntimeException("Réalisateur non trouvé"));
            existingFilm.setDirector(director);
        }

        if(dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
            existingFilm.setCategory(category);
        }

        return filmMapper.toDTO(filmRepository.save(existingFilm));
    }

    public boolean deleteFilm(Long id) {
        if(filmRepository.existsById(id)) {
            filmRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<FilmDTO> getAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(filmMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FilmDTO getFilmById(Long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film non trouvé"));
        return filmMapper.toDTO(film);
    }
}
