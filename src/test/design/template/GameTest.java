package test.design.template;

public class GameTest {

    public static void test() {
        Game game = new BasketBall();
        game.play();
        game = new FootBall();
        game.play();
    }
}
