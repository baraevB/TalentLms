package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;


    public Group create(Group group) {
        Set<Course> courses = new HashSet<>();
        Course course = courseRepository.findByCourseName(group.getCourseName());
        courses.add(course);
        group.setCourses(courses);
        Set<Group> groups = new HashSet<>();
        groups.add(group);
        course.setGroups(groups);
        group.setDateOfStart(LocalDate.now());
        return groupRepository.save(group);
    }

    public Group update(long id, Group group) {
        Group groupDto = groupRepository.findGroupById(id);
        groupDto.setGroupName(group.getGroupName());
        groupDto.setDateOfStart(group.getDateOfStart());
        groupDto.setDateOfFinish(group.getDateOfFinish());
        groupDto.setDateOfStart(LocalDate.now());
        return groupRepository.save(groupDto);

    }

    public void deleteById(long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isPresent()) {
            group.get().removeCourses();
            group.get().removeStudents();
            groupRepository.deleteById(group.get().getId());

        }
    }

    public Group getById(long id) {
        return groupRepository.findGroupById(id);
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }
    public List<Student>studentList(long id){
        return groupRepository.getStudentByGroup(id);
    }


}
