package com.suda.scst.repositories;

import com.suda.scst.domain.Student;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 * @author Michael J. Simons
 */
@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentRepository extends Neo4jRepository<Student, Long> {

	/*
	 StudentRepositiory 继承 Neo4jRepository类，实现增删查改
	 实现自己的接口：通过名字查询Student（可以是单个节点，也可以是一组节点List集合）
	 spring-data-neo4j 支持方法命名约定查询 findBy{Student的属性名}
	 findBy后面的属性名一定要在Student节点实体类里存在，否则会报错
	 */

	Student findByName(@Param("name") String name);

	Collection<Student> findByNameLike(@Param("name") String name);

//	void deleteByName(@Param("name") String name);
//
//	Student createMovie(Student student);

	@Query("MATCH (m:Class)<-[r:STUDY_IN]-(a:Student) RETURN m,r,a LIMIT {limit}")
	Collection<Student> graph(@Param("limit") int limit);
}