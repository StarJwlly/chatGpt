import java.util.ArrayList;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatGptClient {
    public String criarPergunta(
        String OPENAI_API_KEY, 
        String assunto, 
        String tipo, 
        String dificuldade,
        String perguntaExemplo) throws Exception{
            String prompt = """
                        Elabore uma questao sobre %s.
                        Do tipo %s%s.
                        Nivel de dificuldade %s.
                        %s%s          
                    """.formatted(assunto, tipo, 
                    tipo.equalsIgnoreCase("alternativa") ? " com 4 alternativas" : "",
                    dificuldade, 
                    perguntaExemplo == null ? "" : "Use a seguinte pergunta como exemplo: ",
                    perguntaExemplo == null ? "" : perguntaExemplo);
            System.out.println(prompt);

            ChatGptCompletionRequest request = new ChatGptCompletionRequest(
                "text-davinci-003", prompt, 100, 1);
            
            ChatGptCompletionResponse chatGptCompletionResponse = makeRequest(request, OPENAI_API_KEY);

            return chatGptCompletionResponse.getChoices().get(0).getText();
    }

    public ArrayList<String> fazerTraducao(String OPENAI_API_KEY, String input) throws Exception{
        String prompt = String.format("Traduza o seguinte texto do ingles para o portugues: %s", input);
        System.out.println(prompt);

        ChatGptCompletionRequest request = new ChatGptCompletionRequest(
            "text-davinci-003", prompt, 100, 2);
        
        ChatGptCompletionResponse chatGptCompletionResponse = makeRequest(request, OPENAI_API_KEY);

        ArrayList<String> result = new ArrayList<String>();
        result.add(chatGptCompletionResponse.getChoices().get(0).getText());
        result.add(chatGptCompletionResponse.getChoices().get(1).getText());
        return result;
        
    }
    public String resumirEmEmoji(String OPENAI_API_KEY, String input) throws Exception{
        String prompt = String.format("Resuma o filme '%s' em 3 emojis", input);
        System.out.println(prompt);

        ChatGptCompletionRequest request = new ChatGptCompletionRequest(
            "text-davinci-003", prompt, 100, 1);
        
        ChatGptCompletionResponse chatGptCompletionResponse = makeRequest(request, OPENAI_API_KEY);
        return chatGptCompletionResponse.getChoices().get(0).getText();
    }

    public String explicarSimplesmente(String OPENAI_API_KEY, String input) throws Exception {
        String prompt = String.format("Responda a pergunta: '%s' em ate 30 palavras de forma em que criancas possam entender", input);
        System.out.println(prompt);

        ChatGptCompletionRequest request = new ChatGptCompletionRequest(
            "text-davinci-003", prompt, 50, 1);
        
        ChatGptCompletionResponse chatGptCompletionResponse = makeRequest(request, OPENAI_API_KEY);
        return chatGptCompletionResponse.getChoices().get(0).getText();
    }

    ChatGptCompletionResponse makeRequest(ChatGptCompletionRequest request, String OPENAI_API_KEY) throws Exception{
        Gson gson = new Gson();
        String requestJson = gson.toJson(request);

        RequestBody rBody = RequestBody.create(requestJson, MediaType.parse("application/json"));
        OkHttpClient httpClient = new OkHttpClient();

        Request r = new Request.Builder()
            .url("https://api.openai.com/v1/completions")
            .addHeader("Media-Type", "application/json")
            .addHeader("Authorization", "Bearer " + OPENAI_API_KEY)
            .post(rBody)
            .build();
        
        Response response = httpClient.newCall(r).execute();
        return gson.fromJson(response.body().string(), ChatGptCompletionResponse.class);
    }
}

