package com.adc.io;

import com.adc.model.Employee;
import com.adc.model.Manager;

import java.io.*;

public class ObjectStreamTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var harry = new Employee("Haryy", 800);
        var carl = new Manager("Carl", 1000);
        var tony = new Manager("Tony", 1000);
        carl.setSecretary(harry);
        tony.setSecretary(harry);

        var staff = new Employee[3];
        staff[0] = harry;
        staff[1] = carl;
        staff[2] = tony;

        try (var out = new ObjectOutputStream(new FileOutputStream("emp.dat"))) {
            out.writeObject(staff);
        }

        try (var in = new ObjectInputStream(new FileInputStream("emp.dat"))) {
            var newStaff = (Employee[])in.readObject();
            newStaff[0].raiseSalary(100);

            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }
}
