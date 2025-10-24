package ma.project.controller;


import lombok.RequiredArgsConstructor;
import ma.project.dto.CategoryDTO;
import ma.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/api/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> save(@RequestBody CategoryDTO categorydto){
        CategoryDTO saveCategory= categoryService.saveCategory(categorydto);

        Map<String, Object> response= new HashMap<>();
        response.put("statut", "succes");
        response.put("message", "category crée avec succes");
        response.put("data", saveCategory);

        return response;
    }

    @DeleteMapping("{/id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, Object> delete(@PathVariable  Long id){
        boolean res= categoryService.deleteCategory(id);
        Map<String, Object> response= new HashMap<>();
        if(categoryService.deleteCategory(id)){
            response.put("statut", "succes");
            response.put("message", "category supprimer avec succes");
        }else{
            response.put("statut", "error");
            response.put("message", "category non trouvé");
        }
        return response;
    }
}
