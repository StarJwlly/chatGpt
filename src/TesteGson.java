import com.google.gson.Gson;

public class TesteGson {
    public static void main(String[] args) {
        ChatGptCompletionRequest request = new ChatGptCompletionRequest(
            "text-davinci-003", "Por que o ceu é verde?", 150);
        
        Gson gson = new Gson();
        String json = gson.toJson(request);
        System.out.println(json);

        
    }
}
