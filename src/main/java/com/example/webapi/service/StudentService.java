package com.example.webapi.service;


import com.example.webapi.model.Student;
import com.example.webapi.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional =
                studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken !!!");
        }
        studentRepository.save(student);
    }
    public void deleteStudent(Long id) {
        boolean exist = studentRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException(
                    "student with id "
                            + id +
                            "does not exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, Student studentUpdate) {
        Student student = studentRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(
                        "student with id "
                                + id +
                                "does not exist"));
        if (studentUpdate.getName() != null &&
                !Objects.equals(student.getName(), studentUpdate.getName())) {
            student.setName(studentUpdate.getName());
        }
        if (studentUpdate.getEmail() != null &&
                !Objects.equals(student.getEmail(), studentUpdate.getEmail())) {
            Optional<Student> studentOptional =
                    studentRepository.findStudentByEmail(studentUpdate.getEmail());
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken!!!");
            }
        }
        studentRepository.save(student);
    }
}
