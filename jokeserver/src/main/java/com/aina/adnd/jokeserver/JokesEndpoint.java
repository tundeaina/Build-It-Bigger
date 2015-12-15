/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.aina.adnd.jokeserver;

import com.aina.adnd.Joke;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "jokesApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "jokeserver.adnd.aina.com",
    ownerName = "jokeserver.adnd.aina.com",
    packagePath=""
  )
)
public class JokesEndpoint {

    private Joke joke = new Joke();

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public JokesBean sayHi(@Named("name") String name) {

        JokesBean response = new JokesBean();
        response.setData("Hi, " + name);
        return response;
    }

    /** Endpoint method that returns the next joke from the Jokester object */
    @ApiMethod(name = "nextJoke")
    public JokesBean nextJoke() {

        JokesBean response = new JokesBean();
        response.setData(joke.getNext());
        return response;
    }

}

