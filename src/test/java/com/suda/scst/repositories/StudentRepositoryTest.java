package com.suda.scst.repositories;

import com.suda.scst.domain.Person;
import com.suda.scst.domain.Role;
import com.suda.scst.domain.Student;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pdtyreus
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private PersonRepository personRepository;

	@Before
	public void setUp() {
		Student matrix = new Student("The Matrix", 1627405146, "male",1999, 40);

		studentRepository.save(matrix);

		Person keanu = new Person("Keanu Reeves", 1964);

		personRepository.save(keanu);

		Role neo = new Role(matrix, keanu);
		neo.addRoleName("Neo");

		matrix.addRole(neo);

		studentRepository.save(matrix);
	}

	/**
	 * Test of findByTitle method, of class MovieRepository.
	 */
	@Test
	public void testFindByTitle() {

		String title = "The Matrix";
		Student result = studentRepository.findByName(title);
		assertNotNull(result);
		assertEquals(1999, result.getBirth());
	}

	/**
	 * Test of findByTitleContaining method, of class MovieRepository.
	 */
	@Test
	public void testFindByTitleContaining() {
		String title = "*Matrix*";
		Collection<Student> result = studentRepository.findByNameLike(title);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	/**
	 * Test of graph method, of class MovieRepository.
	 */
	@Test
	public void testGraph() {
		Collection<Student> graph = studentRepository.graph(5);

		assertEquals(1, graph.size());

		Student student = graph.iterator().next();

		assertEquals(1, student.getRoles().size());

		assertEquals("The Matrix", student.getName());
		assertEquals("Keanu Reeves", student.getRoles().iterator().next().getPerson().getName());
	}
}