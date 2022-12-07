package universitySystem.University.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import universitySystem.University.entities.Lesson;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
}
