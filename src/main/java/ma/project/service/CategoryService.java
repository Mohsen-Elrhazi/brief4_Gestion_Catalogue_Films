package ma.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.project.dto.CategoryDTO;
import ma.project.mapper.CategoryMapper;
import ma.project.model.Category;
import ma.project.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDTO saveCategory(CategoryDTO categorydto){
        Category category = categoryMapper.toEntity(categorydto);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    public boolean deleteCategory(Long id){
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        // Vérifie si la catégorie existe
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));

        // Met à jour les champs'
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());

        // Sauvegarde dans la BD
        Category updatedCategory = categoryRepository.save(existingCategory);

        // Retourne la catégorie mise à jour sous forme de DTO
        return categoryMapper.toDTO(updatedCategory);
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .toList();
    }

    // Service
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
        return categoryMapper.toDTO(category);
    }


}
