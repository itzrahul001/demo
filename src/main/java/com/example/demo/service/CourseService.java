package com.example.demo.service;


import com.example.demo.dto.CourseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {

CourseDto addCourse(CourseDto courseDto);
List<CourseDto> getAllCourse();

    void deleteCourseById(Long courseId);

    CourseDto updateCourse(Long courseId, CourseDto courseDto);
}
