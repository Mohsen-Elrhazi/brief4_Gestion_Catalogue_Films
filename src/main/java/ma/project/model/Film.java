package ma.project.model;

import jakarta.persistence.*;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idFilm;


    private String title;
    private Integer releaseYear;
    private Integer duration;
    private String synopsis;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "director_id", nullable = true)
    private Director director;

    @ManyToOne
    @JoinColumn(name = "category_id",  nullable = true)
    private Category category;

    public Film(){}

    public Film(String title, Integer releaseYear, Integer duration, String synopsis, Double rating, Director director, Category category) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.synopsis = synopsis;
        this.rating = rating;
        this.director = director;
        this.category = category;
    }

    public Long getIdFilm() {
        return idFilm;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Double getRating() {
        return rating;
    }

    public Director getDirector() {
        return director;
    }

    public Category getCategory() {
        return category;
    }

    public void setIdFilm(Long idFilm) {
        this.idFilm = idFilm;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
