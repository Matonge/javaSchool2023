package students;

class ClassroomDataGroups {
    // внутренние поля для хранения данных
    MyHashMap<Integer, Person> personMap = new MyHashMap<>();
    // алгоритм добавления студента в нужную группу согласно критерию
    public void addPerson(Person person) {
        //personMap.put(person.group, person.firstName + " " + person.secondName);
        personMap.newPut(person.group, person);
    }

    // возвращает студентов конкретной группы
    public Person[] getPersons(int groupNum) {
        //Вывод результатов группировки по группе
        //System.out.println("Фамилия и имя, группировка по группе " + groupNum +":");
        return personMap.getAllValues(groupNum);

    }
}
