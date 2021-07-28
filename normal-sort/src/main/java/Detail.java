import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhangyuxiao
 * @date 2021-07-22 18:40
 * @description
 */
public class Detail {
    String type;
    Long time;

    public Detail(String type, long time) {
        this.type = type;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "{type='" + type + '\'' +
                ", time=" + time +
                "ms}";
    }
}
