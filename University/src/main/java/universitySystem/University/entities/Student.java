package universitySystem.University.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="students")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name="fullName")
    private String fullName;
    @Column(name="entryYear")
    private int entryYear;
    @Column(name="dateOfBirth")
    private Date dob;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="Student_Courses",
            joinColumns = {@JoinColumn(name="student_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "Lesson_Id",referencedColumnName = "id")}
    )
    private List<Lesson> lessons;
}
