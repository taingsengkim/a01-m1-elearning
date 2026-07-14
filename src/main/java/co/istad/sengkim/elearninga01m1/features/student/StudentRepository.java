package co.istad.sengkim.elearninga01m1.features.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentProfile,String> {
}
