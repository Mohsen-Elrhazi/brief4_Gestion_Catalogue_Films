package ma.project.dto;

import jakarta.validation.constraints.*;

public class CategoryDTO {

    private Long idCategory;

//    @NotBlank(message = "Le nom de la catégorie est obligatoire")
//    @Size(min = 2, max = 50, message = "Le nom doit être entre 2 et 50 caractères")
    private String name;

//    @Size(max = 2000, message = "La description ne peut pas dépasser 2000 caractères")
    private String description;

    public CategoryDTO() {}

    public CategoryDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
    // Getters & Setters
    public Long getIdCategory() { return idCategory; }
    public void setIdCategory(Long idCategory) { this.idCategory = idCategory; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}