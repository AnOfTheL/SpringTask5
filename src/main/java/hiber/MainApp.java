package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      List<User> allUsers = new ArrayList<>();

      allUsers.add(new User("User1", "Lastname1", "user1@mail.ru"));
      allUsers.add(new User("User2", "Lastname2", "user2@mail.ru"));
      allUsers.add(new User("User3", "Lastname3", "user3@mail.ru"));
      allUsers.add(new User("User4", "Lastname4", "user4@mail.ru"));

      allUsers.get(0).setCar(new Car("model-1"));
      allUsers.get(1).setCar(new Car("model-2"));
      allUsers.get(2).setCar(new Car("model-3"));
      allUsers.get(3).setCar(new Car("model-4"));

      UserService userService = context.getBean(UserService.class);

      userService.add(allUsers.get(0));
      userService.add(allUsers.get(1));
      userService.add(allUsers.get(2));
      userService.add(allUsers.get(3));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar().toString());
         System.out.println();
      }

      User user = userService.userByCar(users.get(5).getCar());
      System.out.println("Id = " + user.getId());
      System.out.println("First Name = " + user.getFirstName());
      System.out.println("Last Name = " + user.getLastName());
      System.out.println("Email = " + user.getEmail());
      System.out.println("Car = " + user.getCar().toString());
      System.out.println();

      context.close();
   }
}
