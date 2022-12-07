package universitySystem.University.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import universitySystem.University.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
