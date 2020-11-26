package com.example.bismillah;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Pengumuman extends AppCompatActivity {
    String url = "https://pajuts.000webhostapp.com/pengumuman/readpengumuman.php";
    ListView listView;
    TextView jmlInfo;
    private ProgressDialog progressDialog;
    ArrayList<HashMap<String, String>> JsonList;
    String mTitle[] = {"INFORMASI", "PERINGATAN", "KUESIONER", "DOWNLOAD"};
    String mDescription[] = {"JUDUL INFORMASI", "JUDUL PERINGATAN", "JUDUL KUESIONER", "JUDUL DOWNLOAD"};
    int images[] = {R.drawable.ic_baseline_message_24, R.drawable.ic_baseline_announcement_24, R.drawable.ic_baseline_rate_review_24, R.drawable.ic_baseline_photo_size_select_actual_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengumuman);

        listView = findViewById(R.id.listView);
        JsonList = new ArrayList<>();
        jmlInfo = findViewById(R.id.jmlInfo);
        new Getnpm().execute();
        // now create an adapter class
    }


    @SuppressLint("StaticFieldLeak")
    private class Getnpm extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Pengumuman.this);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();
            String jsonString = httpHandler.makeServiceCall(url);
            if (jsonString != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray result = jsonObject.getJSONArray("result");

                    for (int i = 0; i < result.length(); i++) {
                        JSONObject c = result.getJSONObject(i);

                        String judulPengumuman = c.getString("judulPengumuman");

                        HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("judulPengumuman", judulPengumuman);

                        JsonList.add(resultx);
                    }
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Could not get json from server.",
                                Toast.LENGTH_LONG).show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (progressDialog.isShowing()) progressDialog.dismiss();
            ListAdapter adapter = new SimpleAdapter(
                    Pengumuman.this, JsonList, R.layout.listview_pengumuman,
                    new String[]{"judulPengumuman"},
                    new int[]{R.id.jdlPengumuman});

            listView.setAdapter(adapter);
        }

    }

    /*class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter(Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.listview_daftarulang, R.id.kategori, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listview_daftarulang = layoutInflater.inflate(R.layout.listview_daftarulang, parent, false);
            ImageView images = listview_daftarulang.findViewById(R.id.image);
            TextView myTitle = listview_daftarulang.findViewById(R.id.kategori);
            TextView myDescription = listview_daftarulang.findViewById(R.id.judulPengumuman);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);

            HttpHandler httpHandler = new HttpHandler();
            String jsonString = httpHandler.makeServiceCall(url);
            if (jsonString != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray result = jsonObject.getJSONArray("result");

                    for (int i = 0; i < result.length(); i++) {
                        JSONObject c = result.getJSONObject(i);
                        String judulPengumuman = c.getString("judulPengumuman");

                        HashMap<String, String> resultx = new HashMap<>();

                        resultx.put("judulPengumuman", judulPengumuman);

                        JsonList.add(resultx);
                    }
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Could not get json from server.",
                                Toast.LENGTH_LONG).show();
                    }
                });

            }



            return listview_daftarulang;
        }
    }*/
}