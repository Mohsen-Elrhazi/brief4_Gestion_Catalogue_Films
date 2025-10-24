package ma.project.controller;

import lombok.RequiredArgsConstructor;
import ma.project.dto.DirectorDTO;
import ma.project.service.DirectorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> save(@RequestBody DirectorDTO directorDTO) {
        DirectorDTO savedDirector = directorService.saveDirector(directorDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("statut", "succes");
        response.put("message", "Réalisateur créé avec succès");
        response.put("data", savedDirector);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, Object> delete(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        boolean res = directorService.deleteDirector(id);
        if(res){
            response.put("statut", "succes");
            response.put("message", "Réalisateur supprimé avec succès");
        } else {
            response.put("statut", "error");
            response.put("message", "Réalisateur non trouvé");
        }
        return response;
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable("id") Long id, @RequestBody DirectorDTO directorDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            DirectorDTO updated = directorService.updateDirector(id, directorDTO);
            response.put("statut", "succes");
            response.put("message", "Réalisateur mis à jour avec succès");
            response.put("data", updated);
        } catch (RuntimeException e) {
            response.put("statut", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping
    public Map<String, Object> getAllDirectors() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<DirectorDTO> directors = directorService.getAllDirectors();
            response.put("statut", "succes");
            response.put("message", "Liste des réalisateurs récupérée avec succès");
            response.put("data", directors);
        } catch (RuntimeException e) {
            response.put("statut", "error");
            response.put("message", "Impossible de récupérer les réalisateurs");
        }
        return response;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getDirectorById(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            DirectorDTO director = directorService.getDirectorById(id);
            response.put("statut", "succes");
            response.put("message", "Réalisateur récupéré avec succès");
            response.put("data", director);
        } catch (RuntimeException e) {
            response.put("statut", "error");
            response.put("message", e.getMessage());
        }
        return response;
    }
}
