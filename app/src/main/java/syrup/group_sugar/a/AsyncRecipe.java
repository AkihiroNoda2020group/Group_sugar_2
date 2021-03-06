package syrup.group_sugar.a;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class AsyncRecipe extends AsyncTask<String, Void, List<Recipe>> {

    HttpURLConnection con = null;
    private TextView tv;
    private Button bt;
    private String uri;
    private FragmentAA fragment;

    private ProgressBar progerssBer;


    public AsyncRecipe(FragmentAA fragment, TextView tv, Button bt, ProgressBar progerssBer, String uri){
        this.progerssBer = progerssBer;
        this.fragment = fragment;
        this.tv = tv;
        this.bt = bt;
        this.uri = uri;
    }

    @Override
    protected List<Recipe> doInBackground(String... params) {

        List<String> listItem_name = new ArrayList<>();
        List<String> listItem_serving = new ArrayList<>();
        List<String> listHowto_text = new ArrayList<>();
        List<Recipe> list = new ArrayList<>();
        final String uriStr = uri;
        int status = 0;

        Log.d("uri", uriStr);

//        for(int j =0;j< 3;j++) {
            try {
                URL url = new URL(uriStr);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setDoInput(true);
                con.connect(); //HTTP接続
                status = con.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    // Jsoupで対象URLの情報を取得する。

                    Document doc = Jsoup.connect(uriStr).get();
                    Elements elm = doc.select("div.recipe_info_img img");
                    Elements elm2 = doc.select("h1.page_title__text");
                    Elements elm3 = doc.select("span.recipe_material__item_name");
                    Elements elm4 = doc.select("span.recipe_material__item_serving");
                    Elements elm5 = doc.select("span.recipe_howto__text");
                    Element img = elm.get(0);
                    Element title = elm2.get(0);
                    for (int i = 0; i < elm3.size(); i++) {
                        Element item_name = elm3.get(i);
                        Element item_serving = elm4.get(i);
                        listItem_name.add(item_name.text());
                        listItem_serving.add(item_serving.text());
                    }

                    for (int i = 0; i < elm5.size(); i++) {
                        Element Howto_text = elm5.get(i);
                        listHowto_text.add(Howto_text.text());
                    }

                    list.add(new Recipe(img.absUrl("src"), title.text(), listItem_name, listItem_serving, listHowto_text));

                    con.disconnect();
                    return list; //onPostExecuteへreturn
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        return null;
                    }
                }
            } catch (Exception e) { //エラー
                Log.e("button", e.getMessage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    return null;
                }
                return null;
            }
//        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progerssBer.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(List<Recipe> list) { //doInBackgroundが終わると呼び出される
        try {
            if(list != null) {
                fragment.Toast1(list);
            }else{
                tv.setVisibility(View.VISIBLE);
                bt.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        progerssBer.setVisibility(View.GONE);
    }
}