package U3.src.view;

import U3.src.control.Member;

import java.util.List;

public class MemberView {

    public void dump(List<Member> liste){
        for(Member e : liste){
            System.out.println(e);
        }
    }
}
