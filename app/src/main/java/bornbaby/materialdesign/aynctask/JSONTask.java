package bornbaby.materialdesign.aynctask;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.compat.BuildConfig;
import android.util.ArrayMap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import bornbaby.materialdesign.Utils.ApiConfiguration;
import bornbaby.materialdesign.Utils.Utility;
import bornbaby.materialdesign.activity.BaseActivity;

/**
 * Created by madhu on 15-Nov-17.
 */

public class JSONTask extends AsyncTask<Object, Integer, Object> {
    private WeakReference<JSONResult> WEAK_JSON_RESULT;
    private ArrayMap<String, String> HEADER_MAP;

    private HashMap<?, ?> INPUT_PARAMS;
    private int CODE = -1;
    private int CONNECTION_TIMEOUT = 8000;
    private String METHOD_TYPE;

    private String ERROR_MESSAGE = "We could not process your request at this time. Please try again later.";
    private String NO_INTERNET = "No internet.";
    private String SERVER_URL;
    private String RESULT_MESSAGE;

    private BaseActivity parent;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public JSONTask(JSONResult result, BaseActivity parent) {

        this.WEAK_JSON_RESULT = new WeakReference<JSONResult>(result);
        this.HEADER_MAP = new ArrayMap<>();
        this.parent = parent;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    public void execute() {
        super.executeOnExecutor(THREAD_POOL_EXECUTOR);
        CustomDialog.showProgressDialog(parent, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Object doInBackground(Object... objects) {
        if (!Utility.isNetworkAvailable(parent)) {
            RESULT_MESSAGE = NO_INTERNET;
            return null;
        }

        URL url;
        HttpURLConnection httpURLConnection;

        try {
            url = new URL(SERVER_URL);
            httpURLConnection = (HttpURLConnection) url.openConnection();

        } catch (Exception e) {
            e.printStackTrace();
            RESULT_MESSAGE = ApiConfiguration.SERVER_NOT_RESPONDING;
            return null;
        }
        try {
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpURLConnection.setRequestMethod(METHOD_TYPE);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            for (int i = 0; i < HEADER_MAP.size(); i++) {
                httpURLConnection.setRequestProperty(HEADER_MAP.keyAt(i), HEADER_MAP.valueAt(i));
            }
            httpURLConnection.setUseCaches(false);
        } catch (Exception e) {
            e.printStackTrace();
            RESULT_MESSAGE = ApiConfiguration.SERVER_NOT_RESPONDING;
            return null;
        }


        switch (METHOD_TYPE) {
            /*ADD INPUT PARAMS FOR THE POST METHOD*/
            case "POST":
                if (INPUT_PARAMS != null) {
                    try {
                        String param1 = urlEncodeUTF8(INPUT_PARAMS);
                        OutputStream os = httpURLConnection.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        writer.write(param1);
                        writer.flush();
                        writer.close();
                        os.close();
                        httpURLConnection.connect();
                    } catch (Exception e) {
                        e.printStackTrace();
                        RESULT_MESSAGE = ApiConfiguration.SERVER_NOT_RESPONDING;
                        return null;
                    }
                }
                break;
            case "PUT":
                if (INPUT_PARAMS != null) {
                    try {
                        String param1 = urlEncodeUTF8(INPUT_PARAMS);
                        OutputStream os = httpURLConnection.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        writer.write(param1);
                        writer.flush();
                        writer.close();
                        os.close();
                        httpURLConnection.connect();
                    } catch (Exception e) {
                        e.printStackTrace();
                        RESULT_MESSAGE = ApiConfiguration.SERVER_NOT_RESPONDING;
                        return null;
                    }
                }
                break;
        }

        String result;
        JSONObject resultJSON;
        int responseCode;
        try {
            responseCode = httpURLConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
            RESULT_MESSAGE = ApiConfiguration.SERVER_NOT_RESPONDING;
            return null;
        }

         /*
        *  ERROR RESPONSE CODE ie NOT the 200
        * */

        if (responseCode != 201 && responseCode != 200) {
            if (BuildConfig.DEBUG)
                Log.v("API CALL :", "WRONG RESPONSE CODE: " + responseCode);
            RESULT_MESSAGE = ERROR_MESSAGE;
            return null;
        }

         /*
        *  VALID RESPONSE CODE
        * */
        else {
            InputStream reader;
            try {
                reader = httpURLConnection.getInputStream();
            } catch (IOException e1) {
                e1.printStackTrace();
                RESULT_MESSAGE = ApiConfiguration.SERVER_NOT_RESPONDING;
                return null;
            }

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[16384];
            try {
                while ((nRead = reader.read(data, 0, data.length)) != -1)
                    buffer.write(data, 0, nRead);
                buffer.flush();
            } catch (IOException e) {
                RESULT_MESSAGE = ApiConfiguration.SERVER_NOT_RESPONDING;
                e.printStackTrace();
                return null;
            }
            byte[] downloadedData = buffer.toByteArray();

            try {
                result = new String(downloadedData, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                RESULT_MESSAGE = ApiConfiguration.SERVER_NOT_RESPONDING;
                e.printStackTrace();
                return null;
            }

            try {
                resultJSON = new JSONObject(result);
                if (BuildConfig.DEBUG) {
                    Log.v("API CALL :", "RESULT: " + resultJSON);
                    Utility.showLog("RESPONSE<><>", result);
                }
            } catch (JSONException e) {
                RESULT_MESSAGE = ApiConfiguration.SERVER_NOT_RESPONDING;
                e.printStackTrace();
                return null;
            }
        }
        httpURLConnection.disconnect();
        return resultJSON;
    }


    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        CustomDialog.hideProgressBar(parent);

         /*
         *CALLS  "RUN_JSON_RESULT" IF ORIGINAL ACTIVITY IS STILL FOCUSED
         **/
        JSONResult activity = WEAK_JSON_RESULT.get();
        if (result == null && RESULT_MESSAGE.equals(NO_INTERNET)) {
            //NetworkUtils.showNetworkConnectDialog(parent, true);
        } else if (activity != null && result != null) {
            activity.successJSONResult(CODE, result);
        } else{
            CustomDialog.hideProgressBar(parent);
            activity.failedJSONResult(CODE);
        }

    }

    private String urlEncodeUTF8(Map<?, ?> map) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        boolean first = true;

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey().toString(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
        }
        return result.toString();
    }

    /*
    * SET THE HEADER AS A MAP VALUES
    * */
    public void setHeaderMap(ArrayMap<String, String> headerMap) {

        this.HEADER_MAP = headerMap;
    }

    /*
    * SET THE SINGLE HEADER  VALUE
    * */
    public void setHeader(String key, String value) {
        HEADER_MAP.put(key, value);
    }

    /*
    * SET THE SERVER URL
    * */
    public String setServerUrl(String str) {
        return SERVER_URL = str;
    }

    /*
    * SET THE CONNECTION TIME OUT
    * */
    public void setConnectTimeout(int code) {
        this.CONNECTION_TIMEOUT = code;
    }

    /*
    * SET THE UNIQUE CODE
    * */
    public void setCode(int code) {
        this.CODE = code;
    }

    /*
    *  RETURNS THE ERROR MESSAGE
    * */
    public String getResultMessage() {
        return RESULT_MESSAGE;
    }

    public String setErrorMessage(String str) {
        return ERROR_MESSAGE = str;
    }

    /*
   *  RETURNS THE TASK METHOD
   * */
    public enum METHOD {
        GET, PUT, FILE_UPLOAD, POST, DELETE;
    }

    public String setMethod(METHOD method) {
        return METHOD_TYPE = method.toString();
    }

    /*
      * SET THE PARAMS
      * */
    public HashMap<?, ?> setParams(HashMap<?, ?> params) {
        return INPUT_PARAMS = params;
    }


}
