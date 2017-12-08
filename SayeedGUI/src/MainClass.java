import gui.GUIBuilder;

/**
 * Created by rajin on 12/6/2017.
 */
public class MainClass {
    public static void main(String[] args)
    {
        GUIBuilder builder = new GUIBuilder("SayeedGUI", 500, 500, new MyDrawable());
        builder.setTimer(20);
        System.out.println("Hi");
    }
}
