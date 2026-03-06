package com.parcial.parcial1corte.services;

import com.parcial.parcial1corte.dto.request.StudentRequestDTO;
import com.parcial.parcial1corte.dto.response.StudentResponseDTO;
import com.parcial.parcial1corte.entities.StudentEntity;
import com.parcial.parcial1corte.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    //Create
    public StudentResponseDTO saveStudent(StudentRequestDTO requestDTO) {
        StudentEntity studentToSave = StudentEntity.builder()
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .program(requestDTO.getProgram())
                .build();
        StudentEntity studentSaved = studentRepository.save(studentToSave);
        return toDTO(studentSaved);
    }

    // GET ALL
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // GET BY ID
    public StudentResponseDTO getStudentById(Long id) {
        StudentEntity product = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return toDTO(product);
    }

    // UPDATE
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO requestDTO) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        student.setName(requestDTO.getName());
        student.setEmail(requestDTO.getEmail());
        student.setProgram(requestDTO.getProgram());
        StudentEntity studentUpdated = studentRepository.save(student);
        return toDTO(studentUpdated);
    }

    // DELETE
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    // Entity to DTO
    private StudentResponseDTO toDTO(StudentEntity entity) {
        return StudentResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .program(entity.getProgram())
                .build();
    }

}
