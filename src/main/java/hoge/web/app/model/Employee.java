package hoge.web.app.model;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Employee {

    private Integer id;
    private String firstName;
    private String lastName;

    public Employee() {

    }

    public List<Role> getRoles(String role) {
        System.out.println("DEBUG::" + role);
        return Arrays.asList(new Role());
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static class Query {

        public Query from(int offset) {
            return this;
        }

        public List<Employee>  fetch(int limit) {
            return null;
        }
    }

    public static Query findByRoles(final List<String> roles) {
        return new Query();
    }

    public static Optional<Employee> findBy(final Integer id) throws SQLException {
        Employee emp = new QueryRunner().query(
                Database.getConnection(),
                "aaa SELECT * FROM employees where id=?", new BeanHandler<Employee>(Employee.class), id);

        return Optional.ofNullable(emp);
    }

    public static List<Employee> findAll() throws SQLException {
        List<Employee> ret = new QueryRunner().query(
                Database.getConnection(),
                "SELECT * FROM employees ORDER by id",
                new BeanListHandler<Employee>(Employee.class));
        return ret;
    }

    public static List<Employee> findAll(int limit) throws SQLException {
        List<Employee> ret = findAll();
        return findAll().subList(0, Math.min(ret.size(), limit));
    }

    public static List<Employee> find(int limit, String where, Object... param) throws SQLException {
        List<Employee> ret = new QueryRunner().query(
                Database.getConnection(),
                "SELECT * FROM employees where " + where + " ORDER by id",
                new BeanListHandler<Employee>(Employee.class), param).subList(0, limit);
        return ret.subList(0, Math.min(ret.size(), limit));
    }

    public void save() throws SQLException {

        if (getId() == null) {
            new QueryRunner().update(Database.getConnection(),
                    "INSERT INTO employees (firstName, lastName) VALUES (?,?)",
                    getFirstName(), getLastName());

        } else {
            new QueryRunner().update(Database.getConnection(),
                    "UPDATE employees set firstName=? lastName=? where id=?",
                    getFirstName(), getLastName(), getId());
        }
    }
}
