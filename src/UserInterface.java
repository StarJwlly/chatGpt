import javax.swing.JOptionPane;

public class UserInterface {

    public static UserInput<String> getStringInput(String show){
        return getStringInput(show, "");
    }

    public static UserInput<String> getStringInput(String show, String initial){
        while(true){
            String in = JOptionPane.showInputDialog(null, show, initial);
            if(in == null)
                return new UserInput<String>(-1, "");
            if(in.isEmpty())
                return new UserInput<String>(-2, "");
            return new UserInput<String>(0, in);
        }
    }
    public static UserInput<Integer> getIntInput(String show){
        while(true){
            try{
                String in = JOptionPane.showInputDialog(null, show);
                if(in == null)
                    return new UserInput<Integer>(-1, 0);
                if(in.isEmpty())
                    return new UserInput<Integer>(-2, 0);
                return new UserInput<Integer>(0, Integer.parseInt(in));
            }catch(NumberFormatException e){
                
            }
        }
    }

    public static UserInput<Integer> getIntInputInRange(String show, int min, int max){
        if(min > max)
            return new UserInput<Integer>(-1, 0);
        while(true){
            UserInput<Integer> i = getIntInput(show);
            if(i.i == -1)
                return i;
            if(i.i < min || i.i > max)
                continue;
            return i;
        }
    }

    
    public static void print(String show){
        JOptionPane.showMessageDialog(null, show);
    }
}
