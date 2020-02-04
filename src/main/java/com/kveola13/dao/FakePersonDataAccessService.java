package com.kveola13.dao;

import com.kveola13.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> Database = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        Database.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return Database;
    }

}
