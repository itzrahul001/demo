package com.example.demo.controller;


import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class courseController {

 private final CourseService courseService;


    public courseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourse() {
        List<CourseDto> courses = courseService.getAllCourse();
        return ResponseEntity.ok(courses);
    }

    // ADD new course
    @PostMapping
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto courseDto) {
        CourseDto savedCourse = courseService.addCourse(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }



    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {

        courseService.deleteCourseById(courseId);

        return ResponseEntity.ok("Course deleted successfully with id: " + courseId);
    }


    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDto> updateCourse(
            @PathVariable Long courseId,
            @RequestBody CourseDto courseDto) {

        CourseDto updatedCourse = courseService.updateCourse(courseId, courseDto);

        return ResponseEntity.ok(updatedCourse);
    }

}
