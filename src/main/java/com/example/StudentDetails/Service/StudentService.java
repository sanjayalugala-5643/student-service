package com.example.StudentDetails.Service;

import com.example.StudentDetails.Models.Student;
import com.example.StudentDetails.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private  StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public String addStudent(Student student){
        studentRepository.save(student);
        return "add successfully";
    }
    public Student findById(long id ){
        return studentRepository.findById(id).orElse(null);

    }
    public List<Student> getAll(){
        return studentRepository.findAll();
    }
    public String delete(Long id){
       if(studentRepository.existsById(id)){
           studentRepository.deleteById(id);
           return " delete Successfully";
       }
       return "not found ";

    }

    public String update(Long id,Student updatestudent){
        List<Student> students = studentRepository.findAll();

        for(Student student : students){
            if(student.getId()==id){
                student.setName(updatestudent.getName());
                student.setStudent_class(updatestudent.getStudent_class());
                student.setAge(updatestudent.getAge());
                student.setPhone_number(updatestudent.getPhone_number());

                studentRepository.save(student);
                return "update successful";

            }

        }
        return "not found ";

    }
    public String updated(long id, Student updates) {
        Student student = studentRepository.findById(id).orElse(null);

        if (student == null) {
            return "Student not found";
        }

        if (updates.getName() != null) {
            student.setName(updates.getName());
        }
        if (updates.getStudent_class() != null) {
            student.setStudent_class(updates.getStudent_class());
        }
        if (updates.getAge() != null) {
            student.setAge(updates.getAge());
        }
        if (updates.getPhone_number() != null) {
            student.setPhone_number(updates.getPhone_number());
        }

        studentRepository.save(student);
        return "Update successfully";
    }


   /* @Transactional
    public void updateAgeAndPhoneNumber(Long id, String name, Integer student_class, Integer age, String phone_number) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        student.setName(name);
        student.setStudent_class(student_class);
        student.setAge(age);
        student.setPhone_number(phone_number);
    } */

    
    public Page<Student> getStudent(int page , int size){
        return  studentRepository.findAll(PageRequest.of(page , size));
    }

}

