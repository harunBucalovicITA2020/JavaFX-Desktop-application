/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.gui.view;

import app.gui.view.AdminView;
import app.gui.view.UserView;
import app.model.Privilege;
import app.model.User;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Harun
 */
public enum ViewFactory {
    ADMIN(privilage-> "admin".equals(privilage.getName()), new AdminView()),
    USER(privilage->"user".equals(privilage.getName()), new UserView());

    private Predicate<Privilege> privilageTester;
    private BorderPane view;

    private ViewFactory(Predicate<Privilege> userPredicate, BorderPane borderPane) {
        this.privilageTester = userPredicate;
        this.view = borderPane;
    }

    public BorderPane getView() {
        return view;
    }
    

   public static BorderPane loadView(Privilege privilage){
       return Stream.of(ViewFactory.values())
               .filter(it->it.privilageTester.test(privilage))
               .map(ViewFactory::getView)
               .findFirst()
               .orElseThrow(()->new RuntimeException("No such privilage exist"));
   }
  
}
