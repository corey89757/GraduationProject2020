package com.suda.scst.services;

import com.suda.scst.domain.Role;
import com.suda.scst.domain.Student;
import com.suda.scst.repositories.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final static Logger LOG = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private Map<String, Object> toD3Format(Collection<Student> movies) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        int i = 0;
        Iterator<Student> result = movies.iterator();
        while (result.hasNext()) {
            Student student = result.next();
            nodes.add(map("name", student.getName(), "label", "student"));
            int target = i;
            i++;
            for (Role role : student.getRoles()) {
                Map<String, Object> actor = map("name", role.getPerson().getName(), "label", "actor");
                int source = nodes.indexOf(actor);
                if (source == -1) {
                    nodes.add(actor);
                    source = i++;
                }
                rels.add(map("source", source, "target", target));
            }
        }
        return map("nodes", nodes, "links", rels);
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }

    //查询1
    @Transactional(readOnly = true)
    public Student findByName(String name) {
        Student result = studentRepository.findByName(name);
        return result;
    }

    //查询2
    @Transactional(readOnly = true)
    public Collection<Student> findByNameLike(String name) {
        Collection<Student> result = studentRepository.findByNameLike(name);
        return result;
    }

    //查询3
    @Transactional(readOnly = true)
    public Map<String, Object> graph(int limit) {
        Collection<Student> result = studentRepository.graph(limit);
        return toD3Format(result);
    }

    //删除
    @Transactional(readOnly = true)
    public void deleteByName(String name) {
        Student result = studentRepository.findByName(name);
        if (result != null) {
            studentRepository.delete(result);
        }
    }

    //更新或新增（1.如果入参的movie对象是先从库里搜索出来，在内存中改了部分字段，那么这边save就是更新，
    // 2.如果入参的movie对象是直接new出来的，那么就是新增，框架会帮你自动生成id）
    @Transactional(readOnly = true)
    public Student createMovie(Student student) {
        return studentRepository.save(student);
    }
}
