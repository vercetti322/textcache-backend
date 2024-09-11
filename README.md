<h1 align="center">
  Textcache
</h1>
<div align="center">
  <img src="https://skillicons.dev/icons?i=spring,redis,docker" />
</div>

<br/>

<p>
  Textcache is an alternative to Pastebin which allows for sharing of text snippets (with the option of password protection)  in a safe manner. The text shared is end-to-end encrypted using AES encryption - check that out at 
  <a href="https://en.wikipedia.org/wiki/Advanced_Encryption_Standard">AES wiki</a>. This repository has the backend code styled with <a href="https://spring.io/projects/spring-boot">Spring Boot</a> framework. The 
  server is hosted at <a href="https://textcache-backend.onrender.com/">Server</a>. A redis instance is also connected via internal URL hosted on render.
</p>

<h2 align="center">
  API endpoints
</h2>

1. Fetch encrypted paste JSON from frontend and put it into redis (TTL = 900s).
   
    ```http
    POST /api/pastes/new
    Content-Type: application/json
    ```
2. Send the same paste JSON with an automated path URL (where frontend will display the cache).
   
   ```http
   GET /api/pastes/{hash}
   Content-type: application/json
   ```
Following is how the encrypted json looks like (the data is encrypted so no privacy issue!)
```json
{
  "encryptedContent" : "nJ0xYEAV+Dcpk9io5bBZS6lwopoj8YIpn7C/SPvBo/QNgHKLT7lBEXIZOnvXnC"
  "iv" : "8d26b321261ee530d0be5dd389108995"
  "protected" : true
  "hash" : "xy12se"
}
```

<h2 align="center">
  Building & Running
</h2>

To build & run the project, u must have Java JDK installed on your favourite IDE (I recommend Intellij). Although u don't need to setup spring boot 
project from scratch u can check out the general setup at <a href="https://start.spring.io">spring initializer</a>.

Follow the below steps to run the project locally & subsequently build it too (not required for deployment tho)

``` bash
# clone the project to get local folder
git clone https://github.com/vercetti322/textcache-backend.git
```
After cloning, open the project in your IDE and let it resolve the pom.xml for maven dependencies (if it does not do it, then run
```mvn install``` in root directory of the project). After that you can build the project to get a ```.jar``` file.

```bash
mvn build
```

<h2 align="center">
  Deplopyment
</h2>

Just head on to vercel and create a free tier account (mention that you are hobbyist student) until u get an empty dashboard.
Follow the below steps to deploy:

1. Click on the "Add new" and select the option for "Project"
<div align="center">
  <img width="400" alt="image" src="https://github.com/user-attachments/assets/5cc403f4-4e11-4c0a-8c49-621df6b71848">
</div>

3. On the import git repository, select your react project (we have used vite here, but no issue with CRA), and simply click "Deploy"
<div align="center">
  <img width="400" alt="image" src="https://github.com/user-attachments/assets/839b3991-12a6-4626-a0a0-2563a161a945">
</div>
   
5. Also, mention any environment variables (not in our case, i mentioned the base url of hosted server directly).

6. Wait for some time untill the project builds and u get a live link!
<div align="center">
  <img width="400" alt="image" src="https://github.com/user-attachments/assets/c279898e-3e56-4394-86f8-550bd11a63d2">
</div>

<h2 align="center">
  Issues
</h2> 
This section talks about some issues u might run into while deploying:

1. Make sure u have a <a href="https://github.com/vercetti322/TextCache/blob/master/vercel.json">vercel.json</a>
 which tells vercel to route back any dynamic routes (as in our case) to ```index.html```.

2. If u get an ```npm run build error (exit 1)``` error then 99% u the ```index.html``` is not able to ref the ```main.jsx```. Other errors like capitalization of components, directory not having ```package.json```, etc. might also be the cause.
