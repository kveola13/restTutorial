package com.kveola13.dao;

import com.kveola13.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> Database = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        Database.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return Database.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public List<Person> selectAllPeople() {
        return Database;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> findPossiblePerson = selectPersonById(id);
        if (findPossiblePerson.isEmpty()) {
            return 0;
        }
        Database.remove(findPossiblePerson.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person updatedPerson) {
        return selectPersonById(id).map(person1 -> {
            int indexOfPersonToUpdate = Database.indexOf(person1);
            if (indexOfPersonToUpdate >= 0) {
                Database.set(indexOfPersonToUpdate, new Person(id, updatedPerson.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

}
