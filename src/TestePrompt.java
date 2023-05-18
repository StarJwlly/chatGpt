public class TestePrompt {
    public static void main(String[] args) throws Exception{
        ChatGptClient client = new ChatGptClient();
        client.criarPergunta(null, "malabarismo", 
        "alternativa", "extremamente dificil", 
        "quem é o Deus louvado pela maioria dos malabaristas da America Central?");
    }
}
