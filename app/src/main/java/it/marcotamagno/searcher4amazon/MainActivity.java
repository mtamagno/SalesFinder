package it.marcotamagno.searcher4amazon;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    Button search;
    String categoria;
    EditText sconto,nome;
    StringGenerator generator;
    WebView Amazonview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.canScrollVertically(0);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Ehy! Prova anche tu il fantastico trova offerte per Amazon!";
                String shareSubj = "Trova offerte Amazon";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubj);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent,"Condividi l'applicazione"));
            }
        });
        setSearchPage();
    }

    private void setSearchPage(){
        generator = new StringGenerator();
        Amazonview = (WebView)findViewById(R.id.webview);
        Amazonview.getSettings().setJavaScriptEnabled(true);
        nome = (EditText)findViewById(R.id.nome);
        sconto = (EditText) findViewById(R.id.sconto);
        spinner = (Spinner)findViewById(R.id.categories);
        adapter = ArrayAdapter.createFromResource(this,R.array.categories,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(i) + " selected",Toast.LENGTH_LONG).show();
                categoria = (String)adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string;
                if(sconto.getText().toString().trim().length() > 0)
                    string = generator.string(Integer.parseInt(sconto.getText().toString()),categoria,nome.getText().toString());
                else
                    string = generator.string(0,categoria,nome.getText().toString());
                Amazonview.loadUrl(string);
            }
        });
        Amazonview.setWebViewClient(new WebViewClient() {
            @ Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("vnd.youtube")){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                }
                else {
                    return false;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (Amazonview.canGoBack()) {
                        Amazonview.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    public void onResume(){
        super.onResume();
    }
}
