import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatGptCompletionRequest {
    private String model;
    private String prompt;
    private int max_tokens;
    private int n;
}
