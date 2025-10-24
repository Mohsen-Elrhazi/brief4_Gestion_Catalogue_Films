package ma.project.controller;

import lombok.RequiredArgsConstructor;
import ma.project.dto.FilmDTO;
import ma.project.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> save(@RequestBody FilmDTO dto) {
        FilmDTO saved = filmService.saveFilm(dto);
        Map<String, Object> response = new HashMap<>();
        response.put("statut", "succes");
        response.put("message", "Film créé avec succès");
        response.put("data", saved);
        return response;
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable Long id, @RequestBody FilmDTO dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            FilmDTO updated = filmService.updateFilm(id, dto);
            response.put("statut", "succes");
            response.put("message", "Film mis à jour avec succès");
            response.put("data", updated);
        } catch(RuntimeException e) {
            response.put("statut", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        boolean res = filmService.deleteFilm(id);
        if(res){
            response.put("statut", "succes");
            response.put("message", "Film supprimé avec succès");
        } else {
            response.put("statut", "error");
            response.put("message", "Film non trouvé");
        }
        return response;
    }

    @GetMapping
    public Map<String, Object> getAll() {
        Map<String, Object> response = new HashMap<>();
        List<FilmDTO> films = filmService.getAllFilms();
        response.put("statut", "succes");
        response.put("message", "Liste des films récupérée avec succès");
        response.put("data", films);
        return response;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            FilmDTO film = filmService.getFilmById(id);
            response.put("statut", "succes");
            response.put("message", "Film récupéré avec succès");
            response.put("data", film);
        } catch(RuntimeException e) {
            response.put("statut", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }
}
