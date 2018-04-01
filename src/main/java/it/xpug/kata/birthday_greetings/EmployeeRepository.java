package it.xpug.kata.birthday_greetings;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public List<Employee> getAllEmployees(String fileName) throws IOException, ParseException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str = "";
        str = in.readLine(); // skip header
        List<Employee> employees = new ArrayList<>();
        while ((str = in.readLine()) != null) {
            String[] employeeData = str.split(", ");
            Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);

            employees.add(employee);
        }
        return employees;
    }

}
