package U4.src.view;

import U4.src.control.UserStory;

import java.util.List;

public class UserStoryView {
    public static void dump(List<UserStory> liste){
        for(UserStory us : liste){
            System.out.println(us);
            System.out.println("------------");
        }
    }
}
