built a server that can handle ongoing requests from clients over TCP (HTTP/1.1) connections. It's designed to support up to 10000000 clients at once by efficiently managing threads.


Tested with 13000 requests using Postman's spike mode, yielding an average response time of 2 milliseconds.
![Screenshot 2024-02-23 at 23 44 45](https://github.com/maheshwarang001/multiThreaded_webserver/assets/76471375/008939f7-ad96-43b1-9114-3480dee1945c)
