package ma.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.project.dto.DirectorDTO;
import ma.project.mapper.DirectorMapper;
import ma.project.model.Director;
import ma.project.repository.DirectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    // 🔹 Ajouter un nouveau réalisateur
    public DirectorDTO saveDirector(DirectorDTO directorDTO) {
        Director director = directorMapper.toEntity(directorDTO);
        return directorMapper.toDTO(directorRepository.save(director));
    }

    // 🔹 Supprimer un réalisateur par ID
    public boolean deleteDirector(Long id) {
        if(directorRepository.existsById(id)) {
            directorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 🔹 Mettre à jour un réalisateur
    public DirectorDTO updateDirector(Long id, DirectorDTO directorDTO) {
        Director existingDirector = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réalisateur non trouvé"));

        existingDirector.setFirstName(directorDTO.getFirstName());
        existingDirector.setLastName(directorDTO.getLastName());
        existingDirector.setNationality(directorDTO.getNationality());
        existingDirector.setBirthDate(directorDTO.getBirthDate());
        existingDirector.setBiography(directorDTO.getBiography());

        Director updatedDirector = directorRepository.save(existingDirector);
        return directorMapper.toDTO(updatedDirector);
    }

    // 🔹 Récupérer tous les réalisateurs
    public List<DirectorDTO> getAllDirectors() {
        return directorRepository.findAll()
                .stream()
                .map(directorMapper::toDTO)
                .collect(Collectors.toList());
    }

    // 🔹 Récupérer un réalisateur par ID
    public DirectorDTO getDirectorById(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réalisateur non trouvé"));
        return directorMapper.toDTO(director);
    }
}
