package tabpackage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import model.CategoriesPojo;
import com.example.faisal.quizassignment.MainActivitya;
import com.example.faisal.quizassignment.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.AppController;

/**
 * Created by root on 19/2/16.
 */
public class TabFrag1 extends android.support.v4.app.Fragment {

    ListView listView;
    String url ="https://connectdevhiappt.hixapi.com/quiz-services/rest/fetch_categories?type=json";
    String tag_json_obj = "json_obj_req";
    ProgressDialog pDialog;
    List<String> titles = new ArrayList<>();
    List<Integer> Ids = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("hello", response.toString());
                        Gson gson= new Gson();
                        CategoriesPojo categoriesPojos = gson.fromJson(response.toString(),CategoriesPojo.class);
                        Log.d("hello",categoriesPojos.getCategories().get(0).getCategoryName());
                        for(int i=0;i<categoriesPojos.getCategories().size();i++)
                        {
                            titles.add(categoriesPojos.getCategories().get(i).getCategoryName());
                            Ids.add(categoriesPojos.getCategories().get(i).getCategoryId());
                            Log.d("hello", categoriesPojos.getCategories().get(i).getCategoryName());
                            Log.d("hello",titles.get(i));
                        }
                        //titles.toArray();
                        pDialog.hide();
                        if(getActivity()!=null&&!getActivity().isFinishing())
                        MakeList(titles);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("hello", error.toString());
                pDialog.hide();
            }
        }); /*{
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("apiKey", "xxxxxxxxxxxxxxx");
                return null;
            }*/

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(getActivity(), "Attached", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rowview = inflater.inflate(R.layout.tabfrag1, container, false);
        listView = (ListView) rowview.findViewById(R.id.list);
        MakeList(titles);
        return rowview;
    }
void MakeList(List<String> titles)
{
    ArrayAdapter<String> files = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, titles);
    listView.setAdapter(files);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(getActivity(),MainActivitya.class);
            int cat_id = Ids.get(position);
            intent.putExtra("category_id",cat_id);
            startActivity(intent);
        }
    });
}
    @Override
    public void onStart() {
        super.onStart();
    }
}
