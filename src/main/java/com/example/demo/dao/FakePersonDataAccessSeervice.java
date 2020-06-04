package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Person;

import org.springframework.stereotype.Repository;

@Repository("fakeDao")
public class FakePersonDataAccessSeervice implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = selectPersonById(id);
        if (person.isEmpty()) return 0;
        DB.remove(person.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        Optional<Person> p = selectPersonById(id);
        if (p.isEmpty()) return 0;
        int pIndex = DB.indexOf(p.get());
        if (pIndex<0) return 0;
        DB.set(pIndex, new Person(id, person.getName()));
        return 1;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(pers -> pers.getId().equals(id)).findFirst();
    }

}