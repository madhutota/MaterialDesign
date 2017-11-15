package bornbaby.materialdesign.aynctask;

/**
 * Created by madhu on 15-Nov-17.
 */

public interface JSONResult {

    void successJSONResult(int code, Object result);

    void failedJSONResult(int code);
}
