## OKHttpSample
OkHttp is an open source project designed to be an efficient HTTP and HTTP/2 client. It lets you to make fast requests and save bandwith. It offers some great features out of the box like to share a socket for all HTTP/2 requests to the same host, a connection pooling mechanism when HTTP/2 is not available, transparent GZIP feature to reduce download sizes and a caching mechanism for responses.

### Synchronous Network Calls

We can create a Call object and dispatch the network request synchronously:

```java 
Response response = client.newCall(request).execute();```

Because Android disallows network calls on the main thread, you can only make synchronous calls if you do so on a separate thread or a background service. 

### Asynchronous Network Calls

We can also make asynchronous network calls too by creating a Call object, using the enqueue() method, and passing an anonymous Callback object that implements both onFailure() and onResponse().

```java 
// Get a handler that can be used to post to the main thread
client.newCall(request).enqueue(new Callback() {
    @Override
    public void onFailure(Call call, IOException e) {
        e.printStackTrace();
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
    }
}```


If you need to update any views, you will need to use runOnUiThread() or post the result back on the main thread. See this guide for more context.

```java 
client.newCall(request).enqueue(new Callback() {
    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        // ... check for failure using `isSuccessful` before proceeding

        // Read data on the worker thread
        final String responseData = response.body().string();

        // Run view-related code back on the main thread
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    TextView myTextView = (TextView) findViewById(R.id.myTextView);
                    myTextView.setText(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
       }
    }
});```


### Make a Post request

Now, you can use OkHttp to make a post request with a JSON content for example :

```java
public static final MediaType JSON
= MediaType.parse("application/json; charset=utf-8");

OkHttpClient client = new OkHttpClient();
String url = "http://www.ssaurel.com/tmp/jsonService";
String json = "{'mode' : 'test'}"; // Json Content ...

RequestBody body = RequestBody.create(JSON, json);
Request request = new Request.Builder()
  .url(url)
  .post(body)
  .build();```
