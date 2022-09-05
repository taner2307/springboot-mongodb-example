Docker Run
````
docker build -t springboot-mongodb-example . <br/>
docker run -p 9000:9000 springboot-mongodb-example
````

Request

``
http://localhost:9000/products/
``