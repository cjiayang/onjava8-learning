package enums;

// enums/NotClasses.java
// {javap -c LikeClasses}
enum LikeClasses {
    WINKEN {
        @Override
        void behavior() {
            System.out.println("Behavior1");
        }
    },
    BLINKEN {
        @Override
        void behavior() {
            System.out.println("Behavior2");
        }
    },
    NOD {
        @Override
        void behavior() {
            System.out.println("Behavior3");
        }
    };
    abstract void behavior();
}
public class NotClasses {
    // void f1(LikeClasses.WINKEN instance) {} // Nope
}