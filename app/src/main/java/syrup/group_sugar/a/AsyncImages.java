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

class AsyncImages extends AsyncTask<String, Void, List<List<String>>> {

    HttpURLConnection con = null;
//    private List<ImageView> imgs;
    private TextView tv;
    private Button bt;
    private String uri;
//    private String title;
    private FragmentA fragment;

    private ProgressBar progerssBer;


    public AsyncImages(FragmentA fragment, TextView tv, Button bt, ProgressBar progerssBer, String uri){
        this.progerssBer = progerssBer;
        this.fragment = fragment;
//        this.imgs = imgs;
        this.tv = tv;
        this.bt = bt;
        this.uri = uri;
//        this.tv = tv;
//        this.title = title;
//        this.count = count;
    }

    @Override
    protected List<List<String>> doInBackground(String... params) {

        List<String> listImg = new ArrayList<>();
        List<String> listUrl = new ArrayList<>();
        List<String> listTitle = new ArrayList<>();
        List<List<String>> list = new ArrayList<>();
        final String uriStr = uri;
        int status = 0;
//        final String uriStr = "https://recipe.rakuten.co.jp/category/36-496";
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
                Elements elm = doc.select("figure.recipe_ranking__img img");
                Elements elm2 = doc.select("li.recipe_ranking__item a");
                Elements elm3 = doc.select("span.recipe_ranking__recipe_title");
                for (int i = 0; i < 10; i++) {
                    Element img = elm.get(i);
                    Element urls = elm2.get(i);
                    Element title = elm3.get(i);
                    listImg.add(img.absUrl("src"));
                    listUrl.add(urls.absUrl("href"));
                    listTitle.add(title.text());
                }
                list.add(listTitle);
                list.add(listImg);
                list.add(listUrl);

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
    protected void onPostExecute(List<List<String>> list) { //doInBackgroundが終わると呼び出される
        try {

            if(list != null) {
                fragment.Toast(list);
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