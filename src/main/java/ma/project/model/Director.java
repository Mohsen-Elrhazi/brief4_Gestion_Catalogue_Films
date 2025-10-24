package ma.project.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDirector;

    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate birthDate;
    private String biography;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
    private List<Film> films;

    public Director(){}

    public Director( String firstName, String lastName, String nationality, LocalDate birthDate, String biography, List<Film> films) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.biography = biography;
        this.films = films;
    }

    public String getNationality() {
        return nationality;
    }

    public Long getIdDirector() {
        return idDirector;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBiography() {
        return biography;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setIdDirector(Long idDirector) {
        this.idDirector = idDirector;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
