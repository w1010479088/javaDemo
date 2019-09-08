package job.switchtest;

public class Test {

    private void a() {
        A a = A.AE;
        switch (a) {
            case AE:
                break;
            case BE:
                break;
        }
    }

    private void b(){
//        long a = 10L;
//        switch (a){
//            case 20L:
//                break;
//        }
    }
}

enum A {
    AE, BE
}

class B {

}
