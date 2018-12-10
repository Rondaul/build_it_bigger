package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

class JokeAsyncTask extends AsyncTask<Void, Void, String> {
    private static JokeApi myJokeApi = null;
    private AsyncListener listener;

    JokeAsyncTask(AsyncListener listener) {
        this.listener = listener;
    }

    interface AsyncListener {
        void changeProgressBarStatus(boolean isChanged);
        void startJokeActivity(String result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.changeProgressBarStatus(true);
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(myJokeApi == null) {
            JokeApi.Builder builder = new JokeApi.Builder(
                    AndroidHttp.newCompatibleTransport()
                    , new AndroidJsonFactory(), null
            ).setRootUrl(Constant.API_URL)
                    .setGoogleClientRequestInitializer(
                            new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                    request.setDisableGZipContent(true);
                                }
                            }
                    );
            myJokeApi = builder.build();
        }
        try {
            return myJokeApi.getJokeFromBackend().execute().getData();
        } catch (IOException e) {
            Log.d("myTag", e.getMessage());
            return "";
        }
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.changeProgressBarStatus(false);
        listener.startJokeActivity(s);
    }
}
