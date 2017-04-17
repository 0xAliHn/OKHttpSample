# OKHttpSample
OkHttp is an open source project designed to be an efficient HTTP and HTTP/2 client. It lets you to make fast requests and save bandwith. It offers some great features out of the box like to share a socket for all HTTP/2 requests to the same host, a connection pooling mechanism when HTTP/2 is not available, transparent GZIP feature to reduce download sizes and a caching mechanism for responses.

### Synchronous Network Calls

We can create a Call object and dispatch the network request synchronously:

`Response response = client.newCall(request).execute();`

Because Android disallows network calls on the main thread, you can only make synchronous calls if you do so on a separate thread or a background service. 
