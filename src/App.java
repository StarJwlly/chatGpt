import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class App {
    private static String OPENAI_API_KEY;
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/App.Properties"));
        OPENAI_API_KEY = properties.getProperty("OPENAI_API_KEY");
        menu();
        
    }

    static void menu(){
        String menuStr = "1 - Traducao\n2 - emoji\n3 - explicacao\n0 - sair";
        while(true){
            UserInput<Integer> in = UserInterface.getIntInputInRange(menuStr, 0, 4);
            if(in.value == 0 || in.i == -1)
                break;

            if(in.value == 1){
                traducao();
            }else if(in.value == 2){
                emoji();
            }else if(in.value == 3){
                explicacao();
            }else if(in.value == 4){
                criarPergunta();
            }
        }
    }

    private static void emoji(){
        UserInput<String> emojiIn = 
        UserInterface.getStringInput("Insira o nome do filme que sera traduzido em 3 emojis: ");
        if(emojiIn.i == -1) return;

        ChatGptClient client = new ChatGptClient();
        try{
            String out = client.resumirEmEmoji(OPENAI_API_KEY, emojiIn.value);
            UserInterface.print(out);
        }catch(Exception e){
            e.printStackTrace();
        }
    } 

    private static void traducao(){
        UserInput<String> tradIn = 
        UserInterface.getStringInput("Insira o texto em ingles que sera traduzido para portugues: ");
        if(tradIn.i == -1) return;
        ChatGptClient client = new ChatGptClient();
        try{
            ArrayList<String> l = client.fazerTraducao(OPENAI_API_KEY, tradIn.value);
            String out = l.get(0) + "\n\n\nou\n" + l.get(1);
            UserInterface.print(out);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void explicacao(){
        UserInput<String> explIn = 
        UserInterface.getStringInput("Insira a pergunta que sera explicada de modo simples em ate 30 caracteres: ");
        if(explIn.i == -1) return;
        ChatGptClient client = new ChatGptClient();

        try{
            String out = client.explicarSimplesmente(OPENAI_API_KEY, explIn.value);
            UserInterface.print(out);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void criarPergunta(){
        ChatGptClient client = new ChatGptClient();
        UserInput<String> assunto = 
        UserInterface.getStringInput("insira o assunto da pergunta: ");
        if(assunto.i == -1) return;
        UserInput<String> dificuldade = 
        UserInterface.getStringInput("insira a dificuldade da pergunta: ");
        if(dificuldade.i == -1) return;
        UserInput<String> tipo = 
        UserInterface.getStringInput("insira o tipo da pergunta: ");
        if(tipo.i == -1) return;
        UserInput<String> perguntaExemplo = 
        UserInterface.getStringInput("insira umapargunta exemplo (opcional): ");
        if(perguntaExemplo.i == -1) return;


        try{
            String response = client.criarPergunta(OPENAI_API_KEY, 
            assunto.value, 
            tipo.value, 
            dificuldade.value, 
            perguntaExemplo.i == -2 ? null : perguntaExemplo.value);
            UserInterface.print(response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

