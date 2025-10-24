package ma.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.project.dto.CategoryDTO;
import ma.project.mapper.CategoryMapper;
import ma.project.model.Category;
import ma.project.repository.CategoryRepository;
import org.springframework.stereotype.Service;

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

}
