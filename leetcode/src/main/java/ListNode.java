import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor()
public class ListNode<T> {
    final T value;
    ListNode next;
}
