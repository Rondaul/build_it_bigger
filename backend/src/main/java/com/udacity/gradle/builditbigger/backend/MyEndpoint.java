package com.udacity.gradle.builditbigger.backend;

import com.example.android.jokeJavaLibrary.Jokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "getJokeFromBackend")
    public MyBean getJokeFromBackend() {
        MyBean response = new MyBean();
        response.setData(Jokes.getRandomJoke());
        return response;
    }

}
