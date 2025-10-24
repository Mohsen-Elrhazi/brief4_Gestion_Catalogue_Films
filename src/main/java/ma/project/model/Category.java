package ma.project.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idCategory;

    private String name;
    private String Description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Film> films;

    public Category(){}

    public Category(String name, String description, List<Film> films) {
        this.name = name;
        Description = description;
        this.films = films;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
