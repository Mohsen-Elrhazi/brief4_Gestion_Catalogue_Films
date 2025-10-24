package ma.project.repository;

import ma.project.model.Category;
import ma.project.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
