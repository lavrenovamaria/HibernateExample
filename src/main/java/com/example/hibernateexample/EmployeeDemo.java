package com.example.hibernateexample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Импорт библиотеки для логирования


import java.util.List;

public class EmployeeDemo {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDemo.class);

    public static void main(String[] args) {
        Session session = null;
        try {
            logger.info("This is an info log message");
            // Создаем фабрику сессий Hibernate
            SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml") // указываем файл конфигурации Hibernate
                    .addAnnotatedClass(Employee.class) // добавляем класс Employee как сущность Hibernate
                    .buildSessionFactory();

            // Создаем сессию Hibernate
            session = factory.openSession();

            // Создаем объект Employee
            Employee employee = new Employee("John", "Doe", "john.doe@example.com");

            // Начинаем транзакцию
            session.beginTransaction();

            // Сохраняем сотрудника в базе данных
            session.save(employee);

            // Запрос всех сотрудников из базы данных
            List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();

            for (Employee employeew : employees) {
                System.out.println(employeew);
            } // Вывод информации о сотруднике


            // Завершаем транзакцию
            session.getTransaction().commit();

            System.out.println("Сотрудник успешно сохранен в базе данных.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Закрытие сессии Hibernate в блоке finally для освобождения ресурсов
            }
        }
    }
}
