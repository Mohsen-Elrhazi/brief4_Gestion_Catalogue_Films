package ma.project.controller;


import lombok.RequiredArgsConstructor;
import ma.project.dto.CategoryDTO;
import ma.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> save(@RequestBody CategoryDTO categorydto){
        CategoryDTO saveCategory= categoryService.saveCategory(categorydto);

        Map<String, Object> response= new HashMap<>();
        response.put("statut", "succes");
        response.put("message", "category crée avec succes");
        response.put("data", saveCategory);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, String> delete(@PathVariable("id")  Long id){
        boolean res= categoryService.deleteCategory(id);
        categoryService.deleteCategory(id);
        Map<String, String> response= new HashMap<>();
        response.put("statut", "succes");
            response.put("message", "category supprimer avec succes");
        if(res){
            response.put("statut", "succes");
            response.put("message", "category supprimer avec succes");
        }else{
            response.put("statut", "error");
            response.put("message", "category non trouvé");
        }
        return response;
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            CategoryDTO updated = categoryService.updateCategory(id, categoryDTO);
            response.put("statut", "succes");
            response.put("message", "Catégorie mise à jour avec succès");
            response.put("data", updated);
        } catch (RuntimeException e) {
            response.put("statut", "error");
            response.put("message", e.getMessage());
        }

        return response;
    }

    @GetMapping
    public Map<String, Object> getAllCategories() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<CategoryDTO> categories = categoryService.getAllCategories();
            response.put("statut", "succes");
            response.put("message", "Liste des catégories récupérée avec succès");
            response.put("data", categories);
        } catch (RuntimeException  e) {
            response.put("statut", "error");
            response.put("message", "Impossible de récupérer les catégories");
        }
        return response;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCategoryById(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try{
        CategoryDTO category = categoryService.getCategoryById(id);
        response.put("statut", "succes");
        response.put("message", "Catégorie récupérée avec succès");
        response.put("data", category);
        }catch (RuntimeException  e) {
        response.put("statut", "error");
        response.put("message", e.getMessage());
    }
        return response;
    }




}
