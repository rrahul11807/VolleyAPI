package com.example.volleyapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.text);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url="https://anapioficeandfire.com/api/characters/583";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                StringBuilder localities = new StringBuilder();
                try
                {
                    JSONArray data = response.getJSONArray("aliases");
                    localities.append("Aliases"+"\n\n");
                    for (int index = 0; index < data.length(); index++)
                    {
                        localities.append(data.get(index) + "\n");
                    }
                    System.err.println(data);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                textView.setText(localities.toString());
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        textView.setText("That did not work!");
                    }
                });
        queue.add(jsObjRequest);


            }
}
