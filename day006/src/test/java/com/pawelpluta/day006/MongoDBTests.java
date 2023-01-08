package com.pawelpluta.day006;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static com.pawelpluta.day006.PersonFixture.personWithLastName;
import static com.pawelpluta.day006.PersonFixture.randomLastName;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
class MongoDBTests {

    @Autowired
    PersonRepository personRepository;

    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    @BeforeEach
    void setUp() {
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    static void mongoProps(DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
    void cleanup() {
        mongoDBContainer.stop();

    }
    @Test
    void personCouldBeFoundByItsLastName() {
        // given - person to find
        Person knownPerson = personWithLastName(randomLastName());
        saved(knownPerson);
        // given - additional irrelevant data
        saved(personWithLastName(randomLastName()));
        saved(personWithLastName(randomLastName()));

        // when
        List<Person> foundPersons = personRepository.findByLastName(knownPerson.getLastName());

        // then
        assertEquals(1, foundPersons.size());
        assertEquals(knownPerson.getFirstName(), foundPersons.get(0).getFirstName());
        assertEquals(knownPerson.getLastName(), foundPersons.get(0).getLastName());
    }

    private void saved(Person personData) {
        personRepository.save(personData);
    }

}