package pl.sabuk.mateusz.assignment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookAPIClass {
    private final String ISBNNum;
    private final Context context;
    private JSONObject bookItem = null;



    public BookAPIClass(String ISBNNum, Context context, Activity activity) {
        this.ISBNNum = ISBNNum;
        this.context = context;


        Log.d("API lookup", "looking for the book");
        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + ISBNNum;
        Log.d("API lookup", "URL: " + url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.get("totalItems").toString().equals("0")) {
                        Log.d("ISBN Lookup", "book not found");
                        Toast.makeText(context,"Book is not found. \n" +
                                        "Check the ISBN number, but it might not be in our library",
                                Toast.LENGTH_LONG).show();
                    } else {
                        // code when one or more books is found

                        bookItem = (JSONObject) ((JSONArray) response.get("items")).get(0);
                        Log.d("Book info ", getTitle());
                        Log.d("Book Info", getAuthor());

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Do you want to add: \n" + getTitle() + "\nby: " + getAuthor())
                                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Log.d("alert Button", "pressed yes");

                                        Intent intent = new Intent(activity, AddBookActivity.class);
                                        intent.putExtra("ISBN", ISBNNum);
                                        intent.putExtra("Title", getTitle());
                                        intent.putExtra("Author", getAuthor());
                                        intent.putExtra("Description", getDescription());
                                        context.startActivity(intent);

                                    }
                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Log.d("alert Button", "pressed no");

                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        });

        queue.add(jsonObjectRequest);
        queue.start();


    }

    public String getISBNNum() {
        return ISBNNum;
    }

    public String getAuthor() {
        JSONArray authorList;
        StringBuilder output = new StringBuilder();
        try {
            authorList = bookItem.getJSONObject("volumeInfo").getJSONArray("authors");
            for (int i = 0; i < authorList.length(); i++) {
                if (i == (authorList.length() - 2)) output.append(authorList.get(i)).append(" & ");
                else if (i == (authorList.length()-1)) output.append(authorList.get(i));
                else output.append(authorList.get(i)).append(", ");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public boolean isBookFound() {
        return bookItem != null;
    }

    public String getTitle() {
        try {
            return bookItem.getJSONObject("volumeInfo").get("title").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";

    }

    public String getDescription() {
        try {
            return bookItem.getJSONObject("volumeInfo").get("description").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";

    }


}

