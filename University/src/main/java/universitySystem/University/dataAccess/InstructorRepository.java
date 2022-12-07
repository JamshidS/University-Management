package universitySystem.University.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import universitySystem.University.entities.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    boolean existsByName(String name);
}
