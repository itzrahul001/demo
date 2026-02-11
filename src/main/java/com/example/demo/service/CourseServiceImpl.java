package com.example.demo.service;

import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Course;
import com.example.demo.repo.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{


    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        Course course=new Course();
        course.setCourseId(courseDto.getCourseId());
        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());

        Course savedCourse=courseRepository.save(course);

        return new CourseDto(savedCourse.getCourseId(), savedCourse.getCourseName(), savedCourse.getDescription());
    }

    @Override
    public List<CourseDto> getAllCourse() {
        return courseRepository.findAll()
                .stream()
                .map(course -> new CourseDto(
                        course.getCourseId(),
                        course.getCourseName(),
                        course.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCourseById(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException("Course not found with id: " + courseId);
        }
        courseRepository.deleteById(courseId);
    }

    @Override
    public CourseDto updateCourse(Long courseId, CourseDto courseDto) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException(
                        "Course not found with id: " + courseId));

        // update fields

        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());

        Course updatedCourse = courseRepository.save(course);

        // convert Entity → DTO
        return mapToDto(updatedCourse);
    }

    private CourseDto mapToDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setCourseId(course.getCourseId());
        dto.setCourseName(course.getCourseName());
        dto.setDescription(course.getDescription());
        return dto;
    }
}
