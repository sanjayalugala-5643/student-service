package com.example.StudentDetails.Controller;

import com.example.StudentDetails.Models.Student;
import com.example.StudentDetails.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @GetMapping("/{id}")
    public Student findById(@PathVariable Long id){
        return studentService.findById(id);
    }
    @GetMapping("/all")
    public List<Student> getAllStudent(){
        return studentService.getAll();
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        return studentService.delete(id);
    }

   @PutMapping("/{id}")
    public String update(@PathVariable long id ,@RequestBody Student updatestudent){
        return studentService.update(id,updatestudent);
   }
   @PatchMapping("/{id}")
    public  String updated(@PathVariable long id ,@RequestBody Student updates){
        return studentService.updated(id,updates);
   }
    /*@PutMapping("/update/{id}")
    public String updateStudent(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam Integer student_class,
            @RequestParam Integer age,
            @RequestParam String phone_number) {
        studentService.updateAgeAndPhoneNumber(id, name ,student_class ,age, phone_number);
        return "Student updated successfully!";
    } */

    @GetMapping("/page")
    public Page<Student> getStudent(
               @RequestParam(defaultValue = "1") int page,
               @RequestParam(defaultValue = "5") int size){
        return studentService.getStudent(page , size);
    }



}
